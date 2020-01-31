package com.minakov.springsecurityproject.repository;

import com.minakov.springsecurityproject.model.ResponseCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Yaroslav Minakov
 */
@Repository
public interface ResponseCodeRepository extends JpaRepository<ResponseCode, Long> {

    ResponseCode findByUserId(Long userId);
}
