package com.lucaspires.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucaspires.api.entity.Customer;
import com.lucaspires.api.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	// Define a field for the customer service
	private CustomerService customerService;
	
	// Set up constructor injection
	@Autowired
	public CustomerRestController(CustomerService theCustomerService) {
		customerService = theCustomerService;
	}
	
	// Expose "/customers" and return a list of customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	// Add mapping for POST /customers - create a new customer
	@PostMapping("/customers")
	public String addCustomer(@RequestBody Customer theCustomer) {
		// If an id is passed in the JSON, set the id to 0 to force to save a new customer, instead of update
		theCustomer.setId(0);
		
		// Call a method to save the customer
		customerService.saveCustomer(theCustomer);
		
		// Return success message
		return "Customer added successfully";
	}
	
	// Add mapping for DELETE /customers/{customerId} - delete an existing customer
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {
		// Call a method to get the customer who will be deleted
		Customer theCustomer = customerService.getCustomer(customerId);
		
		// If the customer could not be found, throw an exception
		if (theCustomer == null) {
			throw new RuntimeException ("Customer id not found - " + customerId);
		}
		
		// Call a method to delete the customer
		customerService.deleteCustomer(theCustomer);
		
		// Return success message
		return "Customer " + customerId + " deleted successfully";
	}
	
	// Add mapping for GET /customers/{customerId} - return a single customer
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {
		// Call a method to get the customer
		Customer theCustomer = customerService.getCustomer(customerId);
		
		// If the customer could not be found, throw an exception
		if (theCustomer == null) {
			throw new RuntimeException ("Customer id not found - " + customerId);
		}
		
		// Return the customer
		return theCustomer; 
	}
	
	// Add mapping for PUT /customers - update an existing customer
	@PutMapping("/customers")
	public String updateCustomer(@RequestBody Customer theCustomer) {
		// Call a method to save the customer
		customerService.saveCustomer(theCustomer);
		
		// Return success message
		return "Customer updated successfully";
	}
}