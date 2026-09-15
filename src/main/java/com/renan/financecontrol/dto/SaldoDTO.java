package com.renan.financecontrol.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaldoDTO {

    private double receitas;
    private double despesas;
    private double saldo;

    public SaldoDTO(Double receitas, Double despesas, Double saldo) {
        this.receitas = receitas;
        this.despesas = despesas;
        this.saldo = saldo;
    }

    public double getReceitas() {
        return receitas;
    }

    public double getDespesas() {
        return despesas;
    }

    public double getSaldo() {
        return saldo;
    }
}