package com.marcos.geradorrelatorioto.infrastructure.security;

import com.marcos.geradorrelatorioto.infrastructure.entity.TerapeutaEntity;
import com.marcos.geradorrelatorioto.infrastructure.Repository.TerapeutaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // Repositório para acessar dados de usuário no banco de dados

    private final TerapeutaRepository terapeutaRepository;

    public UserDetailsServiceImpl(TerapeutaRepository terapeutaRepository) {
        this.terapeutaRepository = terapeutaRepository;
    }

    // Implementação do método para carregar detalhes do usuário pelo e-mail
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo e-mail
        TerapeutaEntity terapeuta = terapeutaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        // Cria e retorna um objeto UserDetails com base no usuário encontrado
        return org.springframework.security.core.userdetails.User
                .withUsername(terapeuta.getEmail()) // Define o nome de usuário como o e-mail
                .password(terapeuta.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
