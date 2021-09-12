package com.foodhub.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.foodhub.dtos.Admin;
import com.foodhub.dtos.ItemOrdered;
import com.foodhub.dtos.Payment;
import com.foodhub.dtos.PaymentSuccess;
import com.foodhub.dtos.Product;
import com.foodhub.dtos.ShippingAddress;
import com.foodhub.dtos.User;
import com.foodhub.helpers.AdminLoginRowMapper;
import com.foodhub.helpers.LoginRowMapper;
import com.foodhub.helpers.ProductResultSetExtractor;
import com.foodhub.helpers.SuccessDetailsRowMapper;
import com.stripe.model.PaymentIntent;

@Repository
public class FoodhubDaoImpl implements FoodhubDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean insertBreakfastProductToDB(Product product) {

		String sql = "INSERT INTO PRODUCTS VALUES(default,?,?,?,?,?)";

		Object[] args = { product.getPname(), product.getPdesc(), product.getPcost(), product.getImage(),
				product.getPcategory() };
		int noOfrowsAffected = jdbcTemplate.update(sql, args);
		System.out.println(noOfrowsAffected);
		if (noOfrowsAffected == 1)
			return true;
		else
			return false;
	}

	@Override
	public List<Product> fetchAllBreakfastProductsFromDB() {

		String sql = "SELECT* FROM PRODUCTS WHERE PCATEGORY = ?";

		List<Product> products = jdbcTemplate.query(sql, new ProductResultSetExtractor(), "breakfast");

		return products;
	}

	@Override
	public boolean insertUserToDB(User user) {
		String sql = "INSERT INTO USER VALUES(default,?,?,?)";

		Object[] args = { user.getUsername(), user.getEmail(), user.getPassword() };
		int result = jdbcTemplate.update(sql, args);
		if (result == 1)
			return true;
		else
			return false;
	}

	@Override
	public User userLoginValidOrNotDB(String email, String password) {
		try {
			String sql = "SELECT*FROM USER WHERE EMAIL = ? AND PASSWORD = ?";
			User user = jdbcTemplate.queryForObject(sql, new LoginRowMapper(), email, password);
			return user;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("user");
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean insertPaymentToDB(Payment payment) {

		String sql = "INSERT INTO PAYMENTS VALUES(default,?,?,?,?)";
		try {
			float amount = payment.getAmount() / 100;
			Object[] args = { payment.getPaymentIntentId(), payment.getUid(), payment.getTimestamp(), amount };
			int res = jdbcTemplate.update(sql, args);
			if (res == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public Admin isAdminValidOrNot(String email, String password) {
		try {
			String sql = "SELECT*FROM ADMIN WHERE EMAIL = ? AND PASSWORD = ?";
			Admin admin = jdbcTemplate.queryForObject(sql, new AdminLoginRowMapper(), email, password);
			return admin;
		} catch (EmptyResultDataAccessException e) {
			System.out.println("admin");
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean deleteProductDao(int id) {
		String sql = "DELETE FROM PRODUCTS WHERE PID = ?";

		int noOfRowsUpdated = jdbcTemplate.update(sql, id);
		if (noOfRowsUpdated == 1) {
			return true;
		} else
			return false;
	}

	@Override
	public List<Product> fetchAllLunchProductsFromDB() {

		String sql = "SELECT* FROM PRODUCTS WHERE PCATEGORY = ?";
		List<Product> products = jdbcTemplate.query(sql, new ProductResultSetExtractor(), "Lunch");
		return products;
	}

	@Override
	public List<Product> fetchAllDinnerProductsFromDB() {
		String sql = "SELECT* FROM PRODUCTS WHERE PCATEGORY = ?";
		List<Product> products = jdbcTemplate.query(sql, new ProductResultSetExtractor(), "Dinner");
		return products;
	}

	@Override
	public List<Product> fetchAllProductsFromDB() {
		String sql = "SELECT* FROM PRODUCTS";
		List<Product> products = jdbcTemplate.query(sql, new ProductResultSetExtractor());
		return products;
	}

	@Override
	public int getUserIdFromDB(String email, String pass) {

		String sql = "SELECT UID FROM USER WHERE EMAIL = ? AND PASSWORD = ?";
		Object[] args = { email, pass };
		int id = jdbcTemplate.queryForObject(sql, args, Integer.class);
		System.out.println("id is " + id);
		return id;
	}

	@Override
	public void saveAllOrderedProductstoDao(List<ItemOrdered> allOrderedItems) {

		String sql = "INSERT INTO ITEMSORDERED VALUES(default,?,?,?,?,?)";

		ArrayList<Object[]> sqlargs = new ArrayList<>();

		for (ItemOrdered item : allOrderedItems) {
			Object[] itemData = { item.getUid(), item.getPid(), item.getPayid(), item.getTime(), item.getQuantity() };
			sqlargs.add(itemData);
		}
		jdbcTemplate.batchUpdate(sql, sqlargs);
	}

	@Override
	public boolean saveShippingToDB(int uid, Date date, ShippingAddress shipping, String payid) {

		try {
			String sql = "INSERT INTO SHIPPING VALUES(default,?,?,?,?,?,?,?,?,?)";

			Object[] args = { uid, payid, shipping.getHouseNo(), shipping.getBlock(), shipping.getZipcode(),
					shipping.getCity(), shipping.getState(), shipping.getCountry(), date };

			int res = jdbcTemplate.update(sql, args);
			if (res == 1) {
				return true;
			} else
				return false;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public void saveOrderToDB(int uid, String payid, Date date) {
		try {
			String sql = "INSERT INTO ORDERS VALUES (default,?,?,?,?)";
			Object[] args = { uid, payid, date, 0 };
			int res = jdbcTemplate.update(sql, args);
			if (res == 1)
				System.out.println("order placed successfully");
			else
				System.out.println("order faild");
			}catch (Exception e) {

		}

	}

	@Override
	public PaymentSuccess fetchPaymentSuccessDetailsFromDB(PaymentIntent paymentIntent) {
		String sql = "SELECT*FROM ORDERS WHERE PAYID = ?";
		String id = paymentIntent.getId();	
		PaymentSuccess paymentSuccessDetails = jdbcTemplate.queryForObject(sql,new SuccessDetailsRowMapper(),id);
		return paymentSuccessDetails;
	}
}
