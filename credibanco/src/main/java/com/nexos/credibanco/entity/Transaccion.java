package com.nexos.credibanco.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.nexos.credibanco.validation.*;
import jakarta.persistence.*;

@Validate(method = "validateTransaction", message = "{Transaction.invalid.message}")
@Entity
@Table(name = "Transaccion")

public class Transaccion {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_transaccion")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_transaccion_tarjeta")
	private Tarjeta tarjeta;

	@Column(name = "fecha_transaccion")
	private LocalDateTime fechaTransaccion;

	@Column(name = "monto")
	private BigDecimal monto;

	@Column(name = "anulada")
	private boolean anulada;

    public Transaccion() {
    }

	
	//metodo para validar la transaccion 
	public String validateTransaction() {
	    // Verificar saldo suficiente
	    if (tarjeta.getSaldo().compareTo(monto) < 0) {
	        return "Saldo insuficiente";
	    }
	    
	    // Verificar fecha de vencimiento
        if (fechaTransaccion.toLocalDate().isAfter(tarjeta.getFechaVencimiento())) {
        	 return "Tarjeta vencida";
        }
	    
	    // Verificar si la tarjeta está activada
	    if (!tarjeta.isActivada()) {
	    	  return "Tarjeta no activada";
	    }
	    
	    // Verificar si la tarjeta está bloqueada
	    if (tarjeta.isBloqueada()) {
	    	 return "Tarjeta bloqueada";
	    }
	    
	    return null;
	}

	public Transaccion(Long id, Tarjeta tarjeta, LocalDateTime fechaTransaccion, BigDecimal monto, boolean anulada) {
		super();
		this.id = id;
		this.tarjeta = tarjeta;
		this.fechaTransaccion = fechaTransaccion;
		this.monto = monto;
		this.anulada = anulada;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public LocalDateTime getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(LocalDateTime fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public boolean isAnulada() {
		return anulada;
	}

	public void setAnulada(boolean anulada) {
		this.anulada = anulada;
	}

	

}
