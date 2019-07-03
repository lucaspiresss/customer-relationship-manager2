package com.lucaspires.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucaspires.api.dao.CustomerRepository;
import com.lucaspires.api.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	private CustomerRepository customerRepository;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository theCustomerRepository) {
		customerRepository = theCustomerRepository;
	}

	@Override
	public void deleteCustomer(Customer theCustomer) {
		 customerRepository.delete(theCustomer);	
	}
	
	@Override
	public Customer getCustomer(int customerId) {
		Optional<Customer> result = customerRepository.findById(customerId);
		
		Customer theCustomer = null;
		
		if (result.isPresent()) {
			theCustomer = result.get();
		}
		
		return theCustomer;
	}
	
	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		customerRepository.save(theCustomer);
	}
}