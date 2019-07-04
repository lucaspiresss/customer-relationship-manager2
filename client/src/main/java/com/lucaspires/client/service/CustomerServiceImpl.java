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
	private RestTemplate restTemplate;
	private String apiUrl;
	
	@Autowired
	public CustomerServiceImpl(RestTemplate theRestTemplate, @Value("${api.url}") String theApiUrl) {
		restTemplate = theRestTemplate;
		apiUrl = theApiUrl;
	}
	
	@Override
	public Customer getCustomer(int customerId) {
		Customer theCustomer = restTemplate.getForObject(apiUrl + "/" + customerId, Customer.class);
		
		return theCustomer;
	}
	
	@Override
	public List<Customer> getCustomers() {
		ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {});

		List<Customer> customers = responseEntity.getBody();

		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		if (theCustomer.getId() == 0) {
			restTemplate.postForEntity(apiUrl, theCustomer, String.class);
		} else {
			restTemplate.put(apiUrl, theCustomer);
		}
	}
}