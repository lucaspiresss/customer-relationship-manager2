package com.lucaspires.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucaspires.api.dao.CustomerRepository;
import com.lucaspires.api.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	// Define a field for the customer repository
	private CustomerRepository customerRepository;
	
	// Set up constructor injection
	@Autowired
	public CustomerServiceImpl(CustomerRepository theCustomerRepository) {
		customerRepository = theCustomerRepository;
	}

	// Method to delete customers
	@Override
	public void deleteCustomer(Customer theCustomer) {
		 customerRepository.delete(theCustomer);	
	}
	
	// Method to get customers by id
	@Override
	public Customer getCustomer(int customerId) {
		// Call a method to find the customer by id
		Optional<Customer> result = customerRepository.findById(customerId);
		
		// Create an empty customer object
		Customer theCustomer = null;
		
		// If a customer was found, put it into the customer object
		if (result.isPresent()) {
			theCustomer = result.get();
		}
		
		// Return customer object
		return theCustomer;
	}
	
	// Method to get a list of customers
	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	// Method to save customers
	@Override
	public void saveCustomer(Customer theCustomer) {
		customerRepository.save(theCustomer);
	}
}