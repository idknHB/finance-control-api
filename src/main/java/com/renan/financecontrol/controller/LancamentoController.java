package com.renan.financecontrol.controller;

import com.renan.financecontrol.entity.Lancamento;
import com.renan.financecontrol.repository.LancamentoRepository;
import com.renan.financecontrol.service.LancamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final LancamentoService service;

    public LancamentoController(
            LancamentoService service
    ){
        this.service = service;
    }

    @PostMapping
    public Lancamento criar(
            @RequestBody Lancamento lancamento
    ){
        return service.salvar(lancamento);
    }

    @GetMapping
    public List<Lancamento> listar() {
        return service.listarTodos();
    }
}
