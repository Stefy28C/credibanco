package com.nexos.credibanco.dto;

import com.nexos.credibanco.entity.EstadoTarjeta;

public class TarjetaDTO {

	  private String numeroTarjeta;
	    private String productId;
	    private String nombreTitular;
	    private EstadoTarjeta estado;
	    private boolean bloqueada;
		public String getNumeroTarjeta() {
			return numeroTarjeta;
		}
		public void setNumeroTarjeta(String numeroTarjeta) {
			this.numeroTarjeta = numeroTarjeta;
		}
		public String getProductId() {
			return productId;
		}
		public void setProductId(String productId) {
			this.productId = productId;
		}
		public String getNombreTitular() {
			return nombreTitular;
		}
		public void setNombreTitular(String nombreTitular) {
			this.nombreTitular = nombreTitular;
		}
		public EstadoTarjeta getEstado() {
			return estado;
		}
		public void setEstado(EstadoTarjeta estado) {
			this.estado = estado;
		}
		public boolean isBloqueada() {
			return bloqueada;
		}
		public void setBloqueada(boolean bloqueada) {
			this.bloqueada = bloqueada;
		}
	
	    
	    
	    
}
