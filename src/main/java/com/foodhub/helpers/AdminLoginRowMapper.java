package com.foodhub.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foodhub.dtos.Admin;

public class AdminLoginRowMapper  implements RowMapper<Admin>{

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Admin admin = new Admin();
		
		admin.setId(rs.getInt("id"));
		admin.setEmail(rs.getString("email"));
		admin.setPassword(rs.getString("password"));
		
		return admin;
	}

}
