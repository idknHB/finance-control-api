package com.renan.financecontrol.repository;

import com.renan.financecontrol.entity.Lancamento;
import com.renan.financecontrol.enums.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LancamentoRepository
        extends JpaRepository<Lancamento, Long> {

    List<Lancamento> findByTipo(TipoLancamento tipo);
    List<Lancamento> findByDescricaoContainingIgnoreCase(String descricao);
    List<Lancamento> findByTipoAndDescricaoContainingIgnoreCase(
            TipoLancamento tipo,
            String descricao
    );
}
