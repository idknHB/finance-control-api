package com.renan.financecontrol.controller;

import com.renan.financecontrol.dto.SaldoDTO;
import com.renan.financecontrol.entity.Lancamento;
import com.renan.financecontrol.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/tipo/{tipo}")
    public List<Lancamento> buscarPorTipo(
            @PathVariable String tipo
    ){
        return service.buscarPorTipo(tipo);
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

    @GetMapping("/filtro")
    public List<Lancamento> buscarPorFiltro(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String descricao
    ){
        return service.buscarComFiltro(
                tipo,
                descricao
        );
    }

    @GetMapping("/paginado")
    public Page<Lancamento> listarPaginado(
            Pageable pageable
    ){
        return service.listarPaginado(pageable);
    }

    @GetMapping("/periodo")
    public List<Lancamento> buscarPorPeriodo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim
    )
    {
        return service.buscarPorPeriodo(inicio, fim);
    }


    @GetMapping("/saldo")
    public SaldoDTO saldo()
    {
        return service.calcularSaldo();
    }
}
