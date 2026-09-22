package com.renan.financecontrol.controller;

import com.renan.financecontrol.dto.DashboardDTO;
import com.renan.financecontrol.dto.RegisterRequest;
import com.renan.financecontrol.dto.SaldoDTO;
import com.renan.financecontrol.entity.Lancamento;
import com.renan.financecontrol.entity.Usuario;
import com.renan.financecontrol.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final LancamentoService service;

    public LancamentoController(
            LancamentoService service
    ) {
        this.service = service;
    }

    @PostMapping
    public Lancamento criar(
            @Valid @RequestBody Lancamento lancamento
    ) {
        return service.salvar(lancamento);
    }

    @Operation(
            summary = "List all transactions",
            description = "Return all registered transactions"
    )
    @GetMapping
    public List<Lancamento> listar() {
        return service.listarTodos();
    }

    @GetMapping("/tipo/{tipo}")
    public List<Lancamento> buscarPorTipo(
            @PathVariable String tipo
    ) {
        return service.buscarPorTipo(tipo);
    }

    @GetMapping("/{id}")
    public Lancamento buscarPorId(
            @PathVariable Long id
    ) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(
            @PathVariable long id
    ) {
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public Lancamento atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Lancamento lancamento
    ) {
        return service.atualizar(
                id,
                lancamento
        );
    }

    @GetMapping("/filtro")
    public List<Lancamento> buscarPorFiltro(
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String descricao
    ) {
        return service.buscarComFiltro(
                tipo,
                descricao
        );
    }

    @GetMapping("/paginado")
    public Page<Lancamento> listarPaginado(
            Pageable pageable
    ) {
        return service.listarPaginado(pageable);
    }

    @GetMapping("/periodo")
    public List<Lancamento> buscarPorPeriodo(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim
    ) {
        return service.buscarPorPeriodo(inicio, fim);
    }

    @Operation(
            summary = "Calculate balance",
            description = "Calculates revenues, expenses and current balance"
    )
    @GetMapping("/saldo")
    public SaldoDTO saldo() {
        return service.calcularSaldo();
    }

    @Operation(
            summary = "Financial dashboard",
            description = "Returns revenues, expenses, balance and transactions count"
    )
    @GetMapping("/dashboard")
    public DashboardDTO dashboard() {
        return service.gerarDashboard();
    }
}