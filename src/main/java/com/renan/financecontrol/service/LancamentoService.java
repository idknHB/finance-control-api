package com.renan.financecontrol.service;

import com.renan.financecontrol.dto.SaldoDTO;
import com.renan.financecontrol.entity.Lancamento;
import com.renan.financecontrol.exception.ResouceNotFoundException;
import com.renan.financecontrol.repository.LancamentoRepository;
import jakarta.validation.ConstraintValidatorContext;
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

    public Lancamento buscarOuFalhar(Long id)
    {
        return repository.findById(id)
                .orElseThrow(
                        () -> new ResouceNotFoundException(
                                "Transaction not found with id: " + id
                        )
                );
    }
    public Lancamento salvar(
            Lancamento lancamento
    ){
        return repository.save(lancamento);
    }

    public List<Lancamento> listarTodos(){
        return repository.findAll();
    }

    public Lancamento buscarPorId(Long id){
        return buscarPorId(id);
    }

    public void deletar(long Id){
        repository.delete(buscarOuFalhar(Id));
    }

    public Lancamento atualizar(
            Long id,
            Lancamento novoLancamento
    ){
        Lancamento lancamentoExistente = buscarOuFalhar(id);

        lancamentoExistente.setDescricao(
                novoLancamento.getDescricao()
        );

        lancamentoExistente.setValor(
                novoLancamento.getValor()
        );

        lancamentoExistente.setTipo(
                novoLancamento.getTipo()
        );

        lancamentoExistente.setData(
                novoLancamento.getData()
        );

        return repository.save(
                lancamentoExistente
        );
    }

    public SaldoDTO calcularSaldo(){

        double receitas = 0.0;
        double despesas = 0.0;

        List<Lancamento> lancamentos = repository.findAll();

        for(Lancamento l : lancamentos){

            if(l.getTipo().equals("Receita")){
                receitas += l.getValor().doubleValue();
            }else{
                despesas += l.getValor().doubleValue();
            }
        }

        return new SaldoDTO(
          receitas,
          despesas,
          receitas - despesas
        );
    }
}
