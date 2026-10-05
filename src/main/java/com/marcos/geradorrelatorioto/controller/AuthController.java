package com.marcos.geradorrelatorioto.controller;

import com.marcos.geradorrelatorioto.business.AuthService;
import com.marcos.geradorrelatorioto.business.dto.in.LoginRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.LoginResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") // URL base: http://localhost:8080/auth
@RequiredArgsConstructor

@Tag(name = "Autenticação", description = "Login e geração de token JWT")

public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")

    @Operation(summary = "Login", description = "Autentica a terapeuta Ocupacional e retorna o token JWT")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")

    public LoginResponseDTO login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return  authService.login(loginRequestDTO);
    }
}
