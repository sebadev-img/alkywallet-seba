package com.alkemy.wallet.controller;


import com.alkemy.wallet.dto.request.LoginRequestDto;
import com.alkemy.wallet.dto.request.RegisterRequestDto;
import com.alkemy.wallet.dto.response.JwtAuthenticationResponseDto;
import com.alkemy.wallet.service.AuthServiceImpl;
import com.alkemy.wallet.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/register/admin")
    public ResponseEntity<JwtAuthenticationResponseDto> registerAdmin(@Valid @RequestBody RegisterRequestDto registerRequest){
        JwtAuthenticationResponseDto token = authService.registerAdmin(registerRequest);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponseDto> registerUser(@Valid @RequestBody RegisterRequestDto registerRequest){
        JwtAuthenticationResponseDto token = authService.registerUser(registerRequest);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

   @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponseDto> loginUser(@Valid @RequestBody LoginRequestDto loginRequest){
        JwtAuthenticationResponseDto token = authService.loginUser(loginRequest);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
