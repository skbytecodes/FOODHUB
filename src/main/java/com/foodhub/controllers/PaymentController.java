package com.foodhub.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodhub.dtos.JsonItem;
import com.foodhub.dtos.ShippingAddress;
import com.foodhub.paymentdto.CreatePayment;
import com.foodhub.paymentdto.CreatePaymentResponse;
import com.foodhub.service.FoodhubServiceImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentIntentCreateParams.Shipping.Address;


@RestController
public class PaymentController {

	@Autowired
	private FoodhubServiceImpl service;
	
	Address address;
    String carrier;
    Map<String, Object> extraParams = new HashMap<>(); 
    String name = "";
    String phone;
    String trackingNumber;
    
    private String city;
    private String country;
    private Map<String, Object> extraParam = new HashMap<>();
    private String line1;
    private String line2;
    private String postalCode;
    private String state;
    
    
	
	@PostMapping("/create-payment-intent")
	public CreatePaymentResponse createPaymentIntent(@RequestBody CreatePayment createPayment, HttpServletRequest req)
			throws StripeException {
	
		Map<String, String> products = new HashMap<>();
		int i=1;
		
		HttpSession session =  req.getSession();
		String email = (String)session.getAttribute("email");
		String password = (String)session.getAttribute("password");
		int uid = service.getUSerId(email, password);
		
		ShippingAddress shippingAddress =  (ShippingAddress)session.getAttribute("shipping");
		city = shippingAddress.getCity();
		country = shippingAddress.getCountry();
		postalCode = shippingAddress.getZipcode()+"";
		line1 = shippingAddress.getHouseNo()+"";
		line2 = shippingAddress.getBlock();
		state = shippingAddress.getState();
		
		address = PaymentIntentCreateParams.Shipping.Address.builder()
				.setCity(city).setCountry(country)
				.putAllExtraParam(extraParam).setLine1(line1)
				.setLine2(line2).setPostalCode(postalCode).setState(state).build();
		
		
		System.out.println("some of the address inside the payment intent controller "+address.getCity());
		
		
		JsonItem[] jsonItems = createPayment.getItems();
		for(JsonItem item:jsonItems) {
			System.out.println(item.toString());
			products.put("product"+i, uid+"-"+item.getItemid()+"-"+item.getQuantity());
			i++;
		}
		
		Float amount = Float.parseFloat(createPayment.getAmount());
		System.out.println(amount.longValue() * 100l);
		PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
				.setCurrency("INR")
				.setAmount(amount.longValue() * 100L)
				.setDescription("software development project")
				.putAllMetadata(products)
				.setShipping(PaymentIntentCreateParams.Shipping.builder().setAddress(address).setCarrier(carrier).putAllExtraParam(extraParams)
						.setName(name).setPhone(phone).setTrackingNumber(trackingNumber).build())
				.build();

		PaymentIntent intent = PaymentIntent.create(createParams);
		CreatePaymentResponse paymentResponse = new CreatePaymentResponse(intent.getClientSecret());
		return paymentResponse;
	}
}
