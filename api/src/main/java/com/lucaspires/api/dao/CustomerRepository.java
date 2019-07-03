package com.lucaspires.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lucaspires.api.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}