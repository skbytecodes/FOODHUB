package com.foodhub.paymentdto;

import java.util.Arrays;

import com.foodhub.dtos.JsonItem;

public class CreatePayment {
	private JsonItem[] items;
	private String amount;

	public JsonItem[] getItems() {
		return items;
	}

	public void setItems(JsonItem[] items) {
		this.items = items;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CreatePayment [items=" + Arrays.toString(items) + ", amount=" + amount + "]";
	}

}
