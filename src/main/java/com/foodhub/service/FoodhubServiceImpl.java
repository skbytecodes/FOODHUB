package com.foodhub.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodhub.controllers.HomeController;
import com.foodhub.dao.FoodhubDaoImpl;
import com.foodhub.dtos.Admin;
import com.foodhub.dtos.ItemOrdered;
import com.foodhub.dtos.Payment;
import com.foodhub.dtos.PaymentSuccess;
import com.foodhub.dtos.Product;
import com.foodhub.dtos.ShippingAddress;
import com.foodhub.dtos.User;
import com.stripe.model.PaymentIntent;


@Service
public class FoodhubServiceImpl implements FoodhubService{

	@Autowired
	private FoodhubDaoImpl foodhubDao;
	
	@Override
	public boolean insertBreakfastProduct(Product product) throws IOException {
		
		byte[] byteFile = product.getFile().getBytes();
		product.setImage(byteFile);
		boolean result = foodhubDao.insertBreakfastProductToDB(product);
		if(result)
			return true;
		else
			return false;
	}

	@Override
	public List<Product> retrieveAllBreakfastProducts() {
		
		List<Product> productsList = foodhubDao.fetchAllBreakfastProductsFromDB();
		
		return productsList;
	}

	@Override
	public boolean insertUser(User user) {
		
		boolean result = foodhubDao.insertUserToDB(user);
		if(result == true)
			return true;
		else
			return false;
	}

	@Override
	public String loginValidOrNot(String email, String password) {
		User user = foodhubDao.userLoginValidOrNotDB(email,password);
		Admin admin = foodhubDao.isAdminValidOrNot(email,password);
		if(user!=null) 
		{
			if(user.getEmail().equals(email) && user.getPassword().equals(password))
			{
				return "user";
			}
			return "false";
		}
		if(admin!=null) {
			if(admin.getEmail().equals(email) && admin.getPassword().equals(password))
			{
				return "admin";
			}
			return "false";
		}
		return "false";
	}

	@Override
	public void takePaymentToDao(Payment payment) {
		boolean result =foodhubDao.insertPaymentToDB(payment);
		if(result)
			System.out.println("payment successfully inserted");
		else
			System.out.println("payment not inserted");
	}


	@Override
	public boolean deleteProductService(int id) {
		boolean result = foodhubDao.deleteProductDao(id);
		if(result)
			return true;
		else
			return false;
	}

	@Override
	public List<Product> retrieveAllLunchProducts() {
		
		List<Product> productsList = foodhubDao.fetchAllLunchProductsFromDB();
		return productsList;
	}


	@Override
	public List<Product> retrieveAllDinnerProducts() {
		
		List<Product> productsList = foodhubDao.fetchAllDinnerProductsFromDB();
		return productsList;
	}

	@Override
	public List<Product> retrieveAllProducts() {
		List<Product> productsList = foodhubDao.fetchAllProductsFromDB();
		return productsList;
	}

	@Override
	public int getUSerId(String email, String pass) {
		int id = foodhubDao.getUserIdFromDB(email,pass);
		return id;
	}

	@Override
	public void takeOrderedItemstoDao(List<ItemOrdered> allOrderedItems) {
		
		foodhubDao.saveAllOrderedProductstoDao(allOrderedItems);
		
	}

	@Override
	public boolean takeShippingToDao(int uid, Date date, ShippingAddress shipping,String payid) {
		boolean res = foodhubDao.saveShippingToDB(uid,date,shipping,payid);
		if(res)
			return true;
		else
			return false;
	}

	@Override
	public void takeOrdertoDao(int uid, String payid, Date date) {
	
		foodhubDao.saveOrderToDB(uid,payid,date);
		
	}

	@Override
	public PaymentSuccess getpaymentSuccessDetails(PaymentIntent paymentIntent) {
		PaymentSuccess paymentSuccessDetails =  foodhubDao.fetchPaymentSuccessDetailsFromDB(paymentIntent);
		return paymentSuccessDetails;
	}

}
