package com.renan.financecontrol.repository;

import com.renan.financecontrol.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoRepository
        extends JpaRepository<Lancamento, Long> {
}
