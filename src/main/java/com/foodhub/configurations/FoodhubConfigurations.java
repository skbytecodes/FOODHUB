package com.foodhub.configurations;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.foodhub.dtos.Payment;
import com.stripe.Stripe;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"com.foodhub.controllers","com.foodhub.service","com.foodhub.dao"})
@PropertySource("classpath:foodhub.properties")
public class FoodhubConfigurations implements WebMvcConfigurer{

	@PostConstruct
	public void setup() {
		Stripe.apiKey = "Enter your stripe api key";
	}
	
	
	@Bean("payment")
	public Payment getPayment() {
		return new Payment();
	}
	
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
					.allowedMethods("GET","POST","PATCH","DELETE","OPTIONS").allowedOrigins("*");
	}
	
	@Bean
	InternalResourceViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler("/URLToReachResourcesFolder/**")
		.addResourceLocations("/resources/");
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(10000000);
	    return multipartResolver;
	}
	
	@Bean(name = "multipartResolver")
	public StandardServletMultipartResolver getMultipartResolver() {
	    return new StandardServletMultipartResolver();
	}
	
	@Bean
	public DataSource getDataSource()
	{
		
		String url = "jdbc:mysql://localhost:3306/app";
		String uname = "root";
		String pass = "121234";
		String driverName = "com.mysql.cj.jdbc.Driver";
		
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource(url, uname, pass);
		driverManagerDataSource.setDriverClassName(driverName);
		DataSource dataSource = driverManagerDataSource;
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		return jdbcTemplate;
	}
	
	
}
