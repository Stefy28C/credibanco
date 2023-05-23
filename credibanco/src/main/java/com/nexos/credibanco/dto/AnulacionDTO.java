package com.nexos.credibanco.dto;

public class AnulacionDTO {

	  private String numeroTarjeta;
	    private Long transactionId;
		public String getNumeroTarjeta() {
			return numeroTarjeta;
		}
		public void setNumeroTarjeta(String cardId) {
			this.numeroTarjeta = cardId;
		}
		public Long getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(Long transactionId) {
			this.transactionId = transactionId;
		}
	    
	    
	    
	
}
