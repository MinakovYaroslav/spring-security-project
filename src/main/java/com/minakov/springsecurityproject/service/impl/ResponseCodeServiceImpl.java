package com.minakov.springsecurityproject.service.impl;

import com.minakov.springsecurityproject.model.ResponseCode;
import com.minakov.springsecurityproject.repository.ResponseCodeRepository;
import com.minakov.springsecurityproject.service.ResponseCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Yaroslav Minakov
 */
@Service
@Slf4j
public class ResponseCodeServiceImpl implements ResponseCodeService {

    private final ResponseCodeRepository responseCodeRepository;

    @Autowired
    public ResponseCodeServiceImpl(ResponseCodeRepository responseCodeRepository) {
        this.responseCodeRepository = responseCodeRepository;
    }

    @Override
    public ResponseCode findByUserId(Long userId) {
        ResponseCode result = this.responseCodeRepository.findByUserId(userId);
        log.info("IN findByUserId - response code: {}", result);
        return result;
    }
}
