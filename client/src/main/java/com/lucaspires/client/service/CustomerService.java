package com.lucaspires.client.service;

import java.util.List;

import com.lucaspires.client.model.Customer;

public interface CustomerService {
	public Customer getCustomer(int customerId);
	public List<Customer> getCustomers();
	public void saveCustomer(Customer theCustomer);
}