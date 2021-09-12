package com.foodhub.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.foodhub.dtos.ItemOrdered;
import com.foodhub.dtos.Payment;
import com.foodhub.dtos.ShippingAddress;
import com.foodhub.service.FoodhubServiceImpl;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Address;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;

@RestController
public class StripeWebhookController {

	@Autowired
	private FoodhubServiceImpl service;
	@Autowired
	private HomeController homeController;
	@Autowired
	private Payment payment;
	
	@Value("${stripe.webhook.endpoint}")
	private String endpointSecret;
	
	
	@PostMapping("/foodhub/endpoint")
	public String handleStripeEvents(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader,HttpServletResponse res) {
				

				if(sigHeader == null) {
					return "";
				}
				
				Event event; 
                // Only verify the event if you have an endpoint secret defined.
                // Otherwise use the basic event deserialized with GSON.
                try {
                    event = Webhook.constructEvent(
                        payload, sigHeader, endpointSecret
                    );
                } catch (SignatureVerificationException e) {
                    // Invalid signature
                    System.out.println("Webhook error while validating signature.");
                    System.out.println(e);
                    return "";
                }
                
            // Deserialize the nested object inside the event
            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = null;
            if (dataObjectDeserializer.getObject().isPresent()) {
                stripeObject = dataObjectDeserializer.getObject().get();
            } else {
                // Deserialization failed, probably due to an API version mismatch.
                // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
                // instructions on how to handle this case, or return an error here.
            }
            // Handle the event
            switch (event.getType()) {
                case "payment_intent.succeeded":
                    PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                    System.out.println("Payment succeeded for   "+paymentIntent.getAmount());
                    try {
                    	System.out.println("name    "+paymentIntent.getShipping().getName());
                    	System.out.println("houseno "+paymentIntent.getShipping().getAddress().getLine1());
                    	System.out.println("block    "+paymentIntent.getShipping().getAddress().getLine2());
                    	System.out.println("city     "+paymentIntent.getShipping().getAddress().getCity());
                    	System.out.println("state    "+paymentIntent.getShipping().getAddress().getState());
                    	System.out.println("zipcode  "+paymentIntent.getShipping().getAddress().getPostalCode());
                    	System.out.println("country  "+paymentIntent.getShipping().getAddress().getCountry());
                    	
                    }catch(Exception e) {
                    	System.out.println("eception occurred "+e.getMessage());
                    }
                    homeController.setPaymentIntent(paymentIntent);
                    //handle this using new thread
                    handlePaymentIntentSucceeded(paymentIntent);
                    // Then define and call a method to handle the successful payment intent.
                    break;
                default:
                    System.out.println("Unhandled event type: " + event.getType());
                    break;
            }
            return "200";
	}


	private void handlePaymentIntentSucceeded(PaymentIntent paymentIntent) {
		
		System.out.println("handling");
		int uid=0;
		Date date = new Date();
		
		Map<String, String> productDetails = paymentIntent.getMetadata();
		for(int i=1 ;i<=productDetails.size(); i++) {
			String product= productDetails.get("product"+i);
			String[] idQuantity = product.split("-"); 
			uid = Integer.parseInt(idQuantity[0]);
		}
		
		System.out.println("user id is :"+uid);
		
		savePaymentsToDB(paymentIntent,uid,date);
		saveOrderedItemsToDB(paymentIntent,uid,date,productDetails);
		saveShippingtoDB(uid, date, paymentIntent);
		saveOrdersToDB(uid,paymentIntent,date);
	}
	
	
	private void savePaymentsToDB(PaymentIntent paymentIntent,int uid,Date date) {
		payment.setAmount(paymentIntent.getAmount());
		payment.setPaymentIntentId(paymentIntent.getId());
		payment.setUid(uid);
		payment.setTimestamp(date+"");
		service.takePaymentToDao(payment);
	}
	
	private void saveOrderedItemsToDB(PaymentIntent paymentIntent,int uid,Date date,Map<String, String> productDetails) {
		List<ItemOrdered> allOrderedItems = new ArrayList<>();
		
		for(int i=1 ;i<=productDetails.size(); i++) {
			ItemOrdered item = new ItemOrdered();
			String product= productDetails.get("product"+i);
			String[] idQuantity = product.split("-"); 
			uid = Integer.parseInt(idQuantity[0]);
			int quantity = Integer.parseInt(idQuantity[2]);
			int pid = Integer.parseInt(idQuantity[1]);
			item.setUid(uid);
			item.setPayid(paymentIntent.getId());
			item.setPid(pid);
			item.setTime(date+"");
			item.setQuantity(quantity);
			allOrderedItems.add(item);
		}
		service.takeOrderedItemstoDao(allOrderedItems);
	}
	
	private void saveShippingtoDB(int uid,Date date,PaymentIntent paymentIntent) {
		ShippingAddress shippingAddress = new ShippingAddress();
		Address address = paymentIntent.getShipping().getAddress();
		shippingAddress.setHouseNo(Integer.parseInt(address.getLine1()));
		shippingAddress.setBlock(address.getLine2());
		shippingAddress.setZipcode(Integer.parseInt(address.getPostalCode()));
		shippingAddress.setCity(address.getCity());
		shippingAddress.setState(address.getState());
		shippingAddress.setCountry(address.getCountry());
		String payid = paymentIntent.getId();
		
		service.takeShippingToDao(uid, date,shippingAddress,payid);
	}

	private void saveOrdersToDB(int uid,PaymentIntent paymentIntent, Date date) {
		
		String payid = paymentIntent.getId();
		service.takeOrdertoDao(uid,payid,date);
		
	}
}
