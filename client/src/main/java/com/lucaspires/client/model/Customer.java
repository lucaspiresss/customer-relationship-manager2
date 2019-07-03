package com.lucaspires.client.model;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	
	// Constructor method
	public Customer() {
		
	}

	// Getters and setters methods
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}