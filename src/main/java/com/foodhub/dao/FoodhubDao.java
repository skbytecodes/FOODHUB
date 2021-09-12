package com.foodhub.dao;

import java.util.Date;
import java.util.List;

import com.foodhub.dtos.Payment;
import com.foodhub.dtos.PaymentSuccess;
import com.foodhub.dtos.Product;
import com.foodhub.dtos.ShippingAddress;
import com.foodhub.dtos.User;
import com.stripe.model.PaymentIntent;
import com.foodhub.dtos.Admin;
import com.foodhub.dtos.ItemOrdered;

public interface FoodhubDao {
	public boolean insertBreakfastProductToDB(Product product);
	public List<Product> fetchAllBreakfastProductsFromDB();
	public List<Product> fetchAllLunchProductsFromDB();
	public List<Product> fetchAllDinnerProductsFromDB();
	public List<Product> fetchAllProductsFromDB();
	public boolean insertUserToDB(User user);
	public User userLoginValidOrNotDB(String email,String password);
	public boolean insertPaymentToDB(Payment payment);
	public boolean saveShippingToDB(int uid,Date date,ShippingAddress shipping,String payid);
	public Admin isAdminValidOrNot(String email,String password);
	public boolean deleteProductDao(int id);
	public int getUserIdFromDB(String user, String pass);
	public void saveAllOrderedProductstoDao(List<ItemOrdered> allOrderedItems);
	public void saveOrderToDB(int uid, String payid, Date date);
	public PaymentSuccess fetchPaymentSuccessDetailsFromDB(PaymentIntent paymentIntent);
}
