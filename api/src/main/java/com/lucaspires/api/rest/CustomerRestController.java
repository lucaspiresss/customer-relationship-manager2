package com.lucaspires.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucaspires.api.entity.Customer;
import com.lucaspires.api.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	private CustomerService customerService;
	
	@Autowired
	public CustomerRestController(CustomerService theCustomerService) {
		customerService = theCustomerService;
	}
	
	@PostMapping("/customers")
	public String addCustomer(@RequestBody Customer theCustomer) {
		theCustomer.setId(0);
		
		customerService.saveCustomer(theCustomer);
		
		return "Customer added successfully";
	}
	
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		Customer theCustomer = customerService.getCustomer(customerId);
		
		if (theCustomer == null) {
			throw new RuntimeException ("Customer id not found - " + customerId);
		}
		
		return theCustomer; 
	}
	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
}