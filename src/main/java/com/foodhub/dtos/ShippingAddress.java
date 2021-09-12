package com.foodhub.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ShippingAddress {

	@NotNull(message = "*house no can not be null")
	private Integer houseNo;

	@NotBlank(message = "*block no can not be blank")
	private String block;

	@NotNull(message = "*zipcode can not be null")
	private Integer zipcode;

	@NotBlank(message = "*city name can not be blank")
	private String city;

	@NotBlank(message = "*state name can not be blank")
	private String state;

	@NotBlank(message = "*country name can not be blank")
	private String country;

	public Integer getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(Integer houseNo) {
		this.houseNo = houseNo;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "ShippingAddress [houseNo=" + houseNo + ", block=" + block + ", zipcode=" + zipcode + ", city=" + city
				+ ", state=" + state + ", country=" + country + "]";
	}

}
