package com.renan.financecontrol.service;

import com.renan.financecontrol.entity.Lancamento;
import com.renan.financecontrol.repository.LancamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService {

    private final LancamentoRepository repository;

    public LancamentoService(
            LancamentoRepository repository
    ){
        this.repository = repository;
    }

    public Lancamento salvar(
            Lancamento lancamento
    ){
        return repository.save(lancamento);
    }

    public List<Lancamento> listarTodos(){
        return repository.findAll();
    }

}
