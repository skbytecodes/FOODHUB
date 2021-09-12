package com.foodhub.dtos;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class Product {

	private int pid;
	private String pname;
	private double pcost;
	private String pdesc;
	private MultipartFile file;
	private byte[] image;
	private String realImage;
	private String pcategory;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getPcost() {
		return pcost;
	}

	public void setPcost(double pcost) {
		this.pcost = pcost;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getRealImage() {
		return realImage;
	}

	public void setRealImage(String realImage) {
		this.realImage = realImage;
	}

	public String getPcategory() {
		return pcategory;
	}

	public void setPcategory(String pcategory) {
		this.pcategory = pcategory;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", pcost=" + pcost + ", pdesc=" + pdesc + ", file=" + file
				+ ", image=" + Arrays.toString(image) + ", realImage=" + realImage + ", pcategory=" + pcategory + "]";
	}

}
