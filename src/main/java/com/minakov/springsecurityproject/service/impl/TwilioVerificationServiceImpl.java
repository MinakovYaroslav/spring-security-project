package com.minakov.springsecurityproject.service.impl;

import com.minakov.springsecurityproject.model.ResponseCode;
import com.minakov.springsecurityproject.model.User;
import com.minakov.springsecurityproject.model.VerificationResult;
import com.minakov.springsecurityproject.repository.ResponseCodeRepository;
import com.minakov.springsecurityproject.service.VerificationService;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * @author Yaroslav Minakov
 */
@Service
@Slf4j
public class TwilioVerificationServiceImpl implements VerificationService {

    private final ResponseCodeRepository responseCodeRepository;

    @Value("${twilio.account_sid}")
    private String accountSid;

    @Value("${twilio.auth_token}")
    private String authToken;

    @Value("${twilio.trial_number}")
    private String trialNumber;

    @Value("${twilio.verification_service_sid}")
    private String verificationServiceSid;

    @Value("${twilio.verify_message}")
    private String verifyMessage;

    @Value("${twilio.code_pull}")
    private String codePull;

    @Value("${twilio.code_length}")
    private int codeLength;

    @Autowired
    public TwilioVerificationServiceImpl(ResponseCodeRepository responseCodeRepository) {
        this.responseCodeRepository = responseCodeRepository;
    }

    @PostConstruct
    protected void init() {
        Twilio.init(accountSid, authToken);
    }

    @Override
    @Transactional
    public VerificationResult startVerification(User user) {
        log.info("IN startVerification - user: {} ", user);
        try {
            ResponseCode responseCode = ResponseCode.builder()
                    .userId(user.getId())
                    .code(codeGenerator(codeLength, codePull))
                    .build();

            this.responseCodeRepository.save(responseCode);

            Message message = Message.creator(new PhoneNumber(user.getPhoneNumber()),
                    new PhoneNumber(trialNumber),
                    verifyMessage + responseCode.getCode())
                    .create();

            return new VerificationResult(message.getSid());
        } catch (ApiException exception) {
            return new VerificationResult(new String[] {exception.getMessage()});
        }
    }

    private String codeGenerator(int codeLength, String codePull) {
        if (codeLength < 1) throw new IllegalArgumentException();
        StringBuilder sb = new StringBuilder(codeLength);
        Random random = new Random();
        for (int i = 0; i < codeLength; i++) {

            int rndCharAt = random.nextInt(codePull.length());
            char rndChar = codePull.charAt(rndCharAt);

            sb.append(rndChar);

        }

        return sb.toString();
    }
}
