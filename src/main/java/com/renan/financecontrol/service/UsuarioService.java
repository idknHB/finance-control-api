package com.renan.financecontrol.service;

import com.renan.financecontrol.dto.RegisterRequest;
import com.renan.financecontrol.entity.Usuario;
import com.renan.financecontrol.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
            UsuarioRepository repository,
            PasswordEncoder passwordEncoder
    ){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(
            RegisterRequest request
    ){
        if(repository.findByEmail(request.email()).isPresent()){
            throw new RuntimeException("Email ja cadastrado");
        }

        Usuario usuario = new Usuario();

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());

        usuario.setSenha(
                passwordEncoder.encode(
                        request.senha()
                )
        );

        return repository.save(usuario);
    }
}
