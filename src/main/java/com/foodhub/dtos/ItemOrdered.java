package com.foodhub.dtos;

public class ItemOrdered {

	private int uid;
	private int pid;
	private String payid;
	private String time;
	private int quantity;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPayid() {
		return payid;
	}

	public void setPayid(String payid) {
		this.payid = payid;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ItemOrdered [uid=" + uid + ", pid=" + pid + ", payid=" + payid + ", time=" + time + ", quantity="
				+ quantity + "]";
	}

}
