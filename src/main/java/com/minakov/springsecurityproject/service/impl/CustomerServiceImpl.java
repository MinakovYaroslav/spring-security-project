package com.minakov.springsecurityproject.service.impl;

import com.minakov.springsecurityproject.model.Customer;
import com.minakov.springsecurityproject.repository.CustomerRepository;
import com.minakov.springsecurityproject.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findById(Long customerId) {
        Customer result = this.customerRepository.findById(customerId).orElse(null);
        log.info("IN findById - customer: {} found by id: {}", result, customerId);
        return result;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> result = this.customerRepository.findAll();
        log.info("IN findAll - customers: {}", result);
        return result;
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        Customer result = this.customerRepository.save(customer);
        log.info("IN save - customer: {} ", result);
        return result;
    }

    @Override
    public Customer findByIdFetchProjects(Long customerId) {
        Customer result = this.customerRepository.findByIdFetchProjects(customerId);
        log.info("IN findByIdFetchProjects - customer: {} found by id: {}", result, customerId);
        return result;
    }

    @Override
    public List<Customer> findAllFetchProjects() {
        List<Customer> result = this.customerRepository.findAllFetchProjects();
        log.info("IN findAllFetchProjects - customers: {}", result);
        return result;
    }
}