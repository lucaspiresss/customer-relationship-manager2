package com.lucaspires.client.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int customerId) {
		customerService.deleteCustomer(customerId);
		
		return "redirect:/customers/listCustomers";
	}
	
	@GetMapping("/formForAddCustomer")
	public String formForAddCustomer(Model theModel) {
		Customer theCustomer = new Customer();
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customers/customer-form";
	}
	
	@GetMapping("/formForUpdateCustomer")
	public String formForUpdateCustomer(@RequestParam("customerId") int customerId, Model theModel) {
		Customer theCustomer = customerService.getCustomer(customerId);
		
		theModel.addAttribute("customer", theCustomer);
		
		return "customers/customer-form";
	}
	
	@GetMapping("/listCustomers")
	public String listCustomers(Model theModel) {
		List<Customer> theCustomers = customerService.getCustomers();
		
		theModel.addAttribute("customers", theCustomers);
		
		return "customers/list-customers";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customers/listCustomers";
	}
}