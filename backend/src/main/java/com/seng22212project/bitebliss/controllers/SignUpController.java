package com.seng22212project.bitebliss.controllers;

import com.seng22212project.bitebliss.dtos.ApiResponseDto;
import com.seng22212project.bitebliss.dtos.SignUpRequestDto;
import com.seng22212project.bitebliss.exceptions.UserAlreadyExistsException;
import com.seng22212project.bitebliss.exceptions.UserNotFoundException;
import com.seng22212project.bitebliss.exceptions.UserServiceLogicException;
import com.seng22212project.bitebliss.exceptions.UserVerificationFailedException;
import com.seng22212project.bitebliss.services.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/bitebliss/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class SignUpController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<?>> registerUser(@RequestBody SignUpRequestDto signUpRequestDto)
            throws MessagingException, UnsupportedEncodingException, UserAlreadyExistsException, UserServiceLogicException {
        return userService.save(signUpRequestDto);
    }

    @GetMapping("/signup/verify")
    public ResponseEntity<ApiResponseDto<?>> verifyUserRegistration(@Param("code") String code)
            throws UserVerificationFailedException {
        return userService.verifyVerificationCode(code);
    }

    @GetMapping("/signup/resend")
    public ResponseEntity<ApiResponseDto<?>> resendVerificationCode(@Param("email") String email)
            throws UserNotFoundException, MessagingException, UnsupportedEncodingException, UserServiceLogicException {
        return userService.resendVerificationCode(email);
    }
}
