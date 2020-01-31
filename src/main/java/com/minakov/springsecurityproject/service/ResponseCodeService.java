package com.minakov.springsecurityproject.service;

import com.minakov.springsecurityproject.model.ResponseCode;

/**
 * @author Yaroslav Minakov
 */
public interface ResponseCodeService {

    ResponseCode findByUserId(Long userId);
}
