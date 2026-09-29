package com.marcos.geradorrelatorioto.controller;

import com.marcos.geradorrelatorioto.business.AuthService;
import com.marcos.geradorrelatorioto.business.dto.in.LoginRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.LoginResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") // URL base: http://localhost:8080/auth
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return  authService.login(loginRequestDTO);
    }
}
