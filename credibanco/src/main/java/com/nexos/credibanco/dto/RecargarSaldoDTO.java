package com.nexos.credibanco.dto;

import java.math.BigDecimal;

public class RecargarSaldoDTO {

	private String numeroTarjeta;
    private BigDecimal saldo;
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
    
    
    
	
}
