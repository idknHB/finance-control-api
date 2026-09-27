package com.renan.financecontrol.service;

import com.renan.financecontrol.dto.LoginRequest;
import com.renan.financecontrol.dto.LoginResponse;
import com.renan.financecontrol.dto.RegisterRequest;
import com.renan.financecontrol.entity.Usuario;
import com.renan.financecontrol.exception.CredenciaisInvalidasException;
import com.renan.financecontrol.exception.EmailJaCadastradoException;
import com.renan.financecontrol.exception.ResouceNotFoundException;
import com.renan.financecontrol.repository.UsuarioRepository;
import com.renan.financecontrol.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UsuarioService(
            UsuarioRepository repository,
            PasswordEncoder passwordEncoder, JwtService jwtService
    ){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public Usuario cadastrar(
            RegisterRequest request
    ){
        if(repository.findByEmail(request.email()).isPresent()){
            throw new EmailJaCadastradoException("Email ja cadastrado");
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

    public LoginResponse login(
            LoginRequest request
    ){
        Usuario usuario = repository.findByEmail(
                request.email()
        ).orElseThrow(
                () -> new ResouceNotFoundException(
                        "Usuário não encontrado"
                )
        );

        if(!passwordEncoder.matches(
                request.senha(),
                usuario.getSenha()
        )){
            throw new CredenciaisInvalidasException(
                    "Senha inválida"
            );
        }

        String token =
                jwtService.generateToken(
                        usuario.getEmail()
        );

        return new LoginResponse(
                token
        );
    }
}
