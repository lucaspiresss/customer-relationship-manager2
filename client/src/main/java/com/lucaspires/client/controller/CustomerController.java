package com.lucaspires.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lucaspires.client.model.Customer;
import com.lucaspires.client.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService theCustomerService) {
		customerService = theCustomerService;
	}
	
	@GetMapping("/listCustomers")
	public String listCustomers(Model theModel) {
		List<Customer> theCustomers = customerService.getCustomers();
		
		theModel.addAttribute("customers", theCustomers);
		
		return "customers/list-customers";
	}
}