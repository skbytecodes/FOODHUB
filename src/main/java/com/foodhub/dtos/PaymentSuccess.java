package com.foodhub.dtos;

public class PaymentSuccess {

	private int orderid;
	private int userid;
	private String paymentid;
	private String date;
	private Long amount;

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPaymentid() {
		return paymentid;
	}

	public void setPaymentid(String paymentid) {
		this.paymentid = paymentid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PaymentSuccess [orderid=" + orderid + ", userid=" + userid + ", paymentid=" + paymentid + ", date="
				+ date + ", amount=" + amount + "]";
	}

}
