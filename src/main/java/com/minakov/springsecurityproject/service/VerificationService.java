package com.minakov.springsecurityproject.service;

import com.minakov.springsecurityproject.model.User;
import com.minakov.springsecurityproject.model.VerificationResult;

/**
 * @author Yaroslav Minakov
 */
public interface VerificationService {

    VerificationResult startVerification(User user);
}
