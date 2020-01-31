package com.minakov.springsecurityproject.rest;

import com.minakov.springsecurityproject.model.ResponseCode;
import com.minakov.springsecurityproject.model.User;
import com.minakov.springsecurityproject.model.UsernamePasswordAuthenticationRequest;
import com.minakov.springsecurityproject.model.VerificationResult;
import com.minakov.springsecurityproject.security.jwt.JwtTokenProvider;
import com.minakov.springsecurityproject.service.ResponseCodeService;
import com.minakov.springsecurityproject.service.UserService;
import com.minakov.springsecurityproject.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yaroslav Minakov
 */
@RestController
@RequestMapping("/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final VerificationService verificationService;
    private final ResponseCodeService responseCodeService;

    @Autowired
    public AuthenticationRestControllerV1(UserService userService,
                                          AuthenticationManager authenticationManager,
                                          JwtTokenProvider jwtTokenProvider,
                                          VerificationService verificationService,
                                          ResponseCodeService responseCodeService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.verificationService = verificationService;
        this.responseCodeService = responseCodeService;
    }

    @PostMapping(value = "login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody UsernamePasswordAuthenticationRequest authenticationRequest) throws Exception {
        try {

            if (authenticationRequest.getPassword() == null || authenticationRequest.getUsername() == null) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }

            String username = authenticationRequest.getUsername();
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException(String.format("Username %s not found", username));
            }

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, authenticationRequest.getPassword()));

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }
    }

    @GetMapping(value = "verify/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity verify(@PathVariable("userId") Long userId) {

        User user = this.userService.findById(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        VerificationResult result = verificationService.startVerification(user);

        if(result.isValid()) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            Map<Object, Object> response = new HashMap<>();
            response.put("message", String.join("\n", result.getErrors()));
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PostMapping(value = "verify/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity verify(@PathVariable("userId") Long userId, @RequestBody Map<String, String> body) {

        String code = body.get("code");

        if (code.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.findById(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ResponseCode responseCode = this.responseCodeService.findByUserId(userId);

        if (responseCode == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!code.equals(responseCode.getCode())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        user.setPhoneVerified(true);
        this.userService.save(user);

        return new ResponseEntity(HttpStatus.OK);
    }
}