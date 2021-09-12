package com.foodhub.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foodhub.dtos.PaymentSuccess;
import com.foodhub.dtos.ShippingAddress;
import com.foodhub.dtos.User;
import com.foodhub.service.FoodhubServiceImpl;
import com.stripe.model.PaymentIntent;

@Controller
public class HomeController {
	
	@Autowired
	private FoodhubServiceImpl service;
	HttpSession session;
	private PaymentIntent paymentIntent;
	
	public void setPaymentIntent(PaymentIntent paymentIntent) {
		this.paymentIntent = paymentIntent;
	}
	
	@GetMapping("/")
	public String homePage() {
		return "index";
	}
	
	@GetMapping("/home")
	public String showHomePage() {
		return "index";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("email") String email,@RequestParam("password") String password,HttpServletRequest request) {
		
		String result = service.loginValidOrNot(email, password);
		if(result == "user") {
			if(session == null) {
				session = request.getSession();
			}
			session.setAttribute("email", email);
			session.setAttribute("password", password);
			return "redirect:/"+(String)session.getAttribute("lastPage");
		}
		if(result == "admin") {
			return "redirect:/admin";
		}
		else
			return "redirect:/login";
	}
	
	@GetMapping("/register")
	public String registerPage(User user) {
		return "register";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("user") User user,HttpServletRequest request) {
		boolean result = service.insertUser(user);
		if(result) {
			if(session == null) {
				session = request.getSession();
				session.setAttribute("email", user.getEmail());
				session.setAttribute("password", user.getPassword());
			}
			return "redirect:/";
		}
		else
			return "register";
	}
	
	@GetMapping("/breakfast")
	public String breakfastPage(HttpServletRequest req) {
		session = req.getSession();
		session.setAttribute("lastPage", "breakfast");
		if(session.getAttribute("email") == null || session.getAttribute("password") == null)
		{
			return "redirect:/login";
		}
		else
			return "breakfast";
	}
	
	@GetMapping("/lunch")
	public String lunchPage(HttpServletRequest req) {
		session = req.getSession();
		session.setAttribute("lastPage", "lunch");
		if(session.getAttribute("email") == null || session.getAttribute("password") == null)
		{
			return "redirect:/login";
		}
		else
			return "lunch";
	}
	
	@GetMapping("/dinner")
	public String dinnerPage(HttpServletRequest req) {
		session = req.getSession();
		session.setAttribute("lastPage", "dinner");
		if(session.getAttribute("email") == null || session.getAttribute("password") == null)
		{
			return "redirect:/login";
		}
		else
			return "dinner";
	}
	
	@GetMapping("/cart")
	public String cartPage() {
		if(session==null ||(session.getAttribute("email") == null || session.getAttribute("password") == null))
		{
			return "redirect:/login";
		}else
			return "cart";
	}
	
	@GetMapping("/shipping")
	public String shippingPage(@ModelAttribute("shipping") ShippingAddress address) {
		if(session==null ||(session.getAttribute("email") == null || session.getAttribute("password") == null))
		{
			return "redirect:/login";
		}else
			return "shipping";
	}
	
	@PostMapping("/shipping")
	public String shipping(@Valid @ModelAttribute("shipping") ShippingAddress address,BindingResult result,HttpServletRequest request){
		if(result.hasErrors())
		{
			return "shipping";
		}
		else
		{
			System.out.println("my shipping city is "+ address);
			HttpSession session = request.getSession();
			session.setAttribute("shipping", address);
		}
		return "checkout";
	}
	
	@GetMapping("/addProduct")
	public String addProduct() {
		return "addProduct";
	}
	
	@GetMapping("/admin")
	public String adminPage() {
		return "admin";
	}
	
	@GetMapping("/paymentsuccess")
	public String paymentSuces(Model model) {
		if(session==null ||(session.getAttribute("email") == null || session.getAttribute("password") == null))
		{
			return "redirect:/login";
		}else {
			try 
			{
				PaymentSuccess paymentSuccessDetails =  service.getpaymentSuccessDetails(paymentIntent);
				model.addAttribute("paymentSuccessDetails",paymentSuccessDetails);
				model.addAttribute("amount",(paymentIntent.getAmount())/100);
				session.removeAttribute("email");
				session.removeAttribute("password");
			}catch(NullPointerException e) {
				System.out.println("null pointer exception in paymentsuccess method....");
			}
			return "payment-success";
		}
	}
	
	@RequestMapping("/logout")
	public String logoutuser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("email")!=null && session.getAttribute("password")!=null) {
			session.removeAttribute("email");
			session.removeAttribute("password");
			
			return "redirect:/login";
		}
		else {
			return "redirect:/login";
		}
	}
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, "name", stringTrimmerEditor);
	}
}
