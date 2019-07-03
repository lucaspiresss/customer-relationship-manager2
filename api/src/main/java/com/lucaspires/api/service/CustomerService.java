package com.lucaspires.api.service;

import java.util.List;

import com.lucaspires.api.entity.Customer;

public interface CustomerService {
	public Customer getCustomer(int customerId);
	public List<Customer> getCustomers();
	public void saveCustomer(Customer theCustomer);
}