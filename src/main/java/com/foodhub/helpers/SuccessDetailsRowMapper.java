package com.foodhub.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.foodhub.dtos.PaymentSuccess;

public class SuccessDetailsRowMapper implements RowMapper<PaymentSuccess> {

	@Override
	public PaymentSuccess mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PaymentSuccess paymentSuccess = new PaymentSuccess();
		paymentSuccess.setOrderid(rs.getInt("oid"));
		paymentSuccess.setPaymentid(rs.getString("payid"));
		paymentSuccess.setUserid(rs.getInt("uid"));
		paymentSuccess.setDate(rs.getString("otime"));
		
		return paymentSuccess;
	}
}
