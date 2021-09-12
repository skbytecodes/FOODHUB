package com.foodhub.dtos;

public class Payment {

	private String paymentIntentId;
	private int uid;
	private Long amount;
	private String timestamp;
	public String getPaymentIntentId() {
		return paymentIntentId;
	}
	public void setPaymentIntentId(String paymentIntentId) {
		this.paymentIntentId = paymentIntentId;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Payment [paymentIntentId=" + paymentIntentId + ", uid=" + uid + ", amount=" + amount + ", timestamp="
				+ timestamp + "]";
	}

	

}
