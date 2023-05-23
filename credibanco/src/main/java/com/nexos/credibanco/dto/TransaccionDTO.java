package com.nexos.credibanco.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransaccionDTO {
    private Long id;
    private Long tarjetaId;
    private LocalDateTime fechaTransaccion;
    private BigDecimal monto;
    private boolean anulada;

    public TransaccionDTO(Long id, Long tarjetaId, LocalDateTime fechaTransaccion, BigDecimal monto, boolean anulada) {
        this.id = id;
        this.tarjetaId = tarjetaId;
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

	public Long getTarjetaId() {
		return tarjetaId;
	}

	public void setTarjetaId(Long tarjetaId) {
		this.tarjetaId = tarjetaId;
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

    // Getters y setters aquí
    
    
}
