package com.renan.financecontrol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardDTO {

    private double receitas;
    private double despesas;
    private double saldo;
    private long quantidadeLancamentos;

}
