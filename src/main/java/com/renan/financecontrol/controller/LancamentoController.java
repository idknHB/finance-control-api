package com.renan.financecontrol.controller;

import com.renan.financecontrol.entity.Lancamento;
import com.renan.financecontrol.service.LancamentoService;
import jakarta.validation.Valid;
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
            @Valid @RequestBody Lancamento lancamento
    ) {
        return service.salvar(lancamento);
    }

    @GetMapping
    public List<Lancamento> listar()
    {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Lancamento buscarPorId(
            @PathVariable Long id
    ){
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(
            @PathVariable long id
    ){
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public Lancamento atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Lancamento lancamento
    ){
        return service.atualizar(
                id,
                lancamento
        );
    }


}
