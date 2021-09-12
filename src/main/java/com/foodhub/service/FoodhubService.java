package com.foodhub.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.foodhub.dtos.ItemOrdered;
import com.foodhub.dtos.Payment;
import com.foodhub.dtos.PaymentSuccess;
import com.foodhub.dtos.Product;
import com.foodhub.dtos.ShippingAddress;
import com.foodhub.dtos.User;
import com.stripe.model.PaymentIntent;

public interface FoodhubService {
	public boolean insertBreakfastProduct(Product product) throws IOException;
	public List<Product> retrieveAllBreakfastProducts();
	public List<Product> retrieveAllLunchProducts();
	public List<Product> retrieveAllDinnerProducts();
	public List<Product> retrieveAllProducts();
	public boolean insertUser(User user);
	public String loginValidOrNot(String email,String passwowrd);
	public void takePaymentToDao(Payment payment);
	public boolean takeShippingToDao(int uid, Date date, ShippingAddress shipping,String payid);
	public boolean deleteProductService(int id);
	public int getUSerId(String email, String pass);
	public void takeOrderedItemstoDao(List<ItemOrdered> allOrderedItems);
	public void takeOrdertoDao(int uid,String payid, Date date);
	public PaymentSuccess getpaymentSuccessDetails(PaymentIntent paymentIntent);
}
