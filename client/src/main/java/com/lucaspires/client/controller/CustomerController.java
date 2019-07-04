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
	// Define a field for the customer service
	private CustomerService customerService;
	
	// Set up constructor injection
	@Autowired
	public CustomerController(CustomerService theCustomerService) {
		customerService = theCustomerService;
	}
	
	// Add mapping for GET /deleteCustomer?customerId={customerId} - delete an existing customer
	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam("customerId") int customerId) {
		// Call a method to delete the customer
		customerService.deleteCustomer(customerId);
		
		// Redirect to the mapping /listCustomers to reload the customers list (without the deleted customer)
		return "redirect:/customers/listCustomers";
	}
	
	// Add mapping for GET /formForAddCustomer - redirect to an empty customer form
	@GetMapping("/formForAddCustomer")
	public String formForAddCustomer(Model theModel) {
		// Create an empty customer object
		Customer theCustomer = new Customer();
		
		// Add the empty customer object to the spring model
		theModel.addAttribute("customer", theCustomer);
		
		// Redirect to the form file
		return "customers/customer-form";
	}
	
	// Add mapping for GET /formForUpdateCustomer - redirect to a populated customer form
	@GetMapping("/formForUpdateCustomer")
	public String formForUpdateCustomer(@RequestParam("customerId") int customerId, Model theModel) {
		// Call a method to get the customers's informations
		Customer theCustomer = customerService.getCustomer(customerId);
		
		// Add the populated customer object to the spring model
		theModel.addAttribute("customer", theCustomer);
		
		// Redirect to the form file
		return "customers/customer-form";
	}
	
	// Add mapping for GET /listCustomers - redirect to a list of customers
	@GetMapping("/listCustomers")
	public String listCustomers(Model theModel) {
		// Call a method to get the list of customers
		List<Customer> theCustomers = customerService.getCustomers();
		
		// Add the populated list to the spring model
		theModel.addAttribute("customers", theCustomers);
		
		// Redirect to the customers list file
		return "customers/list-customers";
	}
	
	// Add a mapping for POST /saveCustomer - save informations about a costumer
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		// Call a method to save the informations
		customerService.saveCustomer(theCustomer);
		
		// Redirect to the mapping /listCustomers to reload the customers list (with the new customers's informations)
		return "redirect:/customers/listCustomers";
	}
}