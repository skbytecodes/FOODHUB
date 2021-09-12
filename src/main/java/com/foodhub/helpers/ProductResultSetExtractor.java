package com.foodhub.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.foodhub.dtos.Product;



public class ProductResultSetExtractor implements ResultSetExtractor<List<Product>> {

	@Override
	public List<Product> extractData(ResultSet rs) throws SQLException, DataAccessException {
		
		List<Product> products = new ArrayList<>();
		
		while(rs.next())
		{
			Product product = new Product();
			product.setPid(rs.getInt("pid"));
			product.setPname(rs.getString("pname"));
			product.setPcost(Double.parseDouble(rs.getString("pcost")));
			product.setPdesc(rs.getString("pdesc"));
			byte[] image = rs.getBytes("pimage");
			product.setImage(image);
			String realimage = Base64.getEncoder().encodeToString(image);
			product.setRealImage(realimage);
			product.setPcategory(rs.getString("pcategory"));
			products.add(product);
		}
		
		return products;
	}


}
