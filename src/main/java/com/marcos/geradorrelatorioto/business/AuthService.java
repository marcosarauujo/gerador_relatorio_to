package com.marcos.geradorrelatorioto.business;

import com.marcos.geradorrelatorioto.business.dto.in.LoginRequestDTO;
import com.marcos.geradorrelatorioto.business.dto.out.LoginResponseDTO;
import com.marcos.geradorrelatorioto.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequestDTO.getEmail(), loginRequestDTO.getSenha())
        );
        String token = "Bearer " + jwtUtil.generateToken(loginRequestDTO.getEmail()
        );
        return new LoginResponseDTO(token);
    }


}
