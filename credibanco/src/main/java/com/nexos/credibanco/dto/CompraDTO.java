package com.nexos.credibanco.dto;

import java.math.BigDecimal;

public class CompraDTO {
	
	private String numeroTarjeta;
    private BigDecimal Monto;
    
    
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}
	public BigDecimal getMonto() {
		return Monto;
	}
	public void setMonto(BigDecimal monto) {
		Monto = monto;
	}
    
    
    

}
