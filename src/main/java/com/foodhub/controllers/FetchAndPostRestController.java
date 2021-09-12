package com.foodhub.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.foodhub.dtos.Product;
import com.foodhub.service.FoodhubServiceImpl;


@RestController
public class FetchAndPostRestController {

	@Autowired
	private FoodhubServiceImpl service;
	
	@GetMapping("/breakfastproducts")
	public List<Product> getAllBreakfastProducts()
	{
		System.out.println("breakfast");
		List<Product> products = service.retrieveAllBreakfastProducts();
		return products;
	}
	
	
	@GetMapping("/lunchproducts")
	public List<Product> getAllLunchProducts()
	{
		System.out.println("lunch");
		List<Product> products = service.retrieveAllLunchProducts();
		return products;
	}
	
	
	@GetMapping("/dinnerproducts")
	public List<Product> getAllDinnerProducts()
	{
		List<Product> products = service.retrieveAllDinnerProducts();
		return products;
	}
	
	
	@GetMapping("/products")
	public List<Product> getAllProducts()
	{
		System.out.println("products");
		List<Product> products = service.retrieveAllProducts();
		return products;
	}
	
	
	@PostMapping("/addProduct")
	public void postProduct(@ModelAttribute("product") Product product)throws IOException
	{
		boolean result = service.insertBreakfastProduct(product);
		System.out.println(result);
		if(result)
			System.out.println("successfull");
			System.out.println(product.getPname());
	}
	
	@PostMapping("/deleteProduct")
	public void deleteProduct(@RequestBody int id) {
		
		boolean result = service.deleteProductService(id);
		if(result) {
			System.out.println("product deleted successfully");
		}
	}
	
}
