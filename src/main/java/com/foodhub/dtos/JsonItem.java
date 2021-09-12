package com.foodhub.dtos;

public class JsonItem {
	private String itemid;
	private String itemname;
	private String itemcost;
	private String itemdesc;
	private String quantity;
	private String itemimg;

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getItemname() {
		return itemname;
	}

	public void setItemname(String itemname) {
		this.itemname = itemname;
	}

	public String getItemcost() {
		return itemcost;
	}

	public void setItemcost(String itemcost) {
		this.itemcost = itemcost;
	}

	public String getItemdesc() {
		return itemdesc;
	}

	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getItemimg() {
		return itemimg;
	}

	public void setItemimg(String itemimg) {
		this.itemimg = itemimg;
	}

	@Override
	public String toString() {
		return "JsonItem [itemid=" + itemid + ", itemname=" + itemname + ", itemcost=" + itemcost + ", itemdesc="
				+ itemdesc + ", quantity=" + quantity + ", itemimg=" + itemimg + "]";
	}

}
