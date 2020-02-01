package com.minakov.springsecurityproject.service;

import com.minakov.springsecurityproject.model.Customer;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
public interface CustomerService {

    Customer findById(Long customerId);
    List<Customer> findAll();
    Customer save(Customer customer);
    Customer findByIdFetchProjects(Long customerId);
    List<Customer> findAllFetchProjects();
}