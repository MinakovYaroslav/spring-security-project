package com.minakov.springsecurityproject.repository;

import com.minakov.springsecurityproject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yaroslav Minakov
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("FROM Customer c JOIN FETCH c.projects WHERE c.id =:customerId")
    Customer findByIdFetchProjects(@Param("customerId") Long customerId);

    @Query("FROM Customer c JOIN FETCH c.projects")
    List<Customer> findAllFetchProjects();
}