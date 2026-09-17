package com.renan.financecontrol.repository;

import com.renan.financecontrol.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LancamentoRepository
        extends JpaRepository<Lancamento, Long> {

    List<Lancamento> findByTipoIgnoreCase(String tipo);
    List<Lancamento> findByDescricaoContainingIgnoreCase(String descricao);
    List<Lancamento> findByTipoIgnoreCaseAndDescricaoContainingIgnoreCase(String tipo, String descricao);
}
