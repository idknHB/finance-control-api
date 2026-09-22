package com.renan.financecontrol.controller;

import com.renan.financecontrol.dto.LoginRequest;
import com.renan.financecontrol.dto.LoginResponse;
import com.renan.financecontrol.dto.RegisterRequest;
import com.renan.financecontrol.entity.Usuario;
import com.renan.financecontrol.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService service;

    public AuthController(
            UsuarioService service
    ){
        this.service = service;
    }

    @PostMapping("/register")
    public Usuario cadastrar(
            @Valid @RequestBody RegisterRequest request
    ){
        return service.cadastrar(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ){
        return service.login(request);
    }

}