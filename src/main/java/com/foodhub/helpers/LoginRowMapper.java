package com.foodhub.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foodhub.dtos.User;


public class LoginRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user = new User();
		
		user.setUsername(rs.getString("uname"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		
		return user;
	}

}
