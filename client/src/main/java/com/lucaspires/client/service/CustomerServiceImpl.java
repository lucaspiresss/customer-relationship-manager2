package com.lucaspires.client.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lucaspires.client.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	// Define a field for the REST template
	private RestTemplate restTemplate;
	
	// Define a field for the API URL
	private String apiUrl;
	
	// Set up constructor injection
	@Autowired
	public CustomerServiceImpl(RestTemplate theRestTemplate, @Value("${api.url}") String theApiUrl) {
		restTemplate = theRestTemplate;
		apiUrl = theApiUrl;
	}
	
	// Method to delete customers: calls DELETE api/customers/{customerId} 
	@Override
	public void deleteCustomer(int customerId) {
		restTemplate.delete(apiUrl + "/" + customerId);
	}
	
	// Method to get customers by id: calls GET api/customers/{customerId}
	@Override
	public Customer getCustomer(int customerId) {
		Customer theCustomer = restTemplate.getForObject(apiUrl + "/" + customerId, Customer.class);
		
		return theCustomer;
	}
	
	// Method to get a list of customers: calls GET api/customers
	@Override
	public List<Customer> getCustomers() {
		ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {});

		List<Customer> customers = responseEntity.getBody();

		return customers;
	}

	// Method to save informations about a customer
	@Override
	public void saveCustomer(Customer theCustomer) {
		if (theCustomer.getId() == 0) {
			// Insert new informations (create a new customer): calls POST api/customers
			restTemplate.postForEntity(apiUrl, theCustomer, String.class);
		} else {
			// Update existing informations about a customer: calls PUT api/customers
			restTemplate.put(apiUrl, theCustomer);
		}
	}
}