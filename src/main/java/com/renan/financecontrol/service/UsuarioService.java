package com.renan.financecontrol.service;

import com.renan.financecontrol.dto.LoginRequest;
import com.renan.financecontrol.dto.LoginResponse;
import com.renan.financecontrol.dto.RegisterRequest;
import com.renan.financecontrol.entity.Usuario;
import com.renan.financecontrol.exception.CredenciaisInvalidasException;
import com.renan.financecontrol.exception.EmailJaCadastradoException;
import com.renan.financecontrol.exception.ResouceNotFoundException;
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

        return new LoginResponse(
                "Login realizado com sucesso"
        );
    }

}
