package com.nexos.credibanco.resource;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexos.credibanco.dto.AnulacionDTO;
import com.nexos.credibanco.dto.CompraDTO;
import com.nexos.credibanco.dto.TransaccionDTO;
import com.nexos.credibanco.entity.Tarjeta;
import com.nexos.credibanco.entity.Transaccion;
import com.nexos.credibanco.repository.ITarjetaRepository;
import com.nexos.credibanco.repository.ITransaccionRepository;
import com.nexos.credibanco.service.TransaccionService;

@RestController
@RequestMapping("/transaction")
public class TransaccionResource {
	@Autowired
	private TransaccionService transaccionService;

	// Hacer la compra
	@PostMapping("/purchase")
	public ResponseEntity<?> realizarCompra(@RequestBody CompraDTO compraDTO) {
		try {
			Transaccion transaccion = transaccionService.realizarCompra(compraDTO.getNumeroTarjeta(),
					compraDTO.getMonto());
			return ResponseEntity.status(HttpStatus.CREATED).body("Transacción realizada con éxito");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	// Consultar la transacción

	@GetMapping("/{transactionId}")
	public ResponseEntity<?> consultarTransaccion(@PathVariable Long transactionId) {
		try {
			Transaccion transaccion = transaccionService.consultarTransaccion(transactionId);
			TransaccionDTO transaccionDTO = new TransaccionDTO(transaccion.getId(), transaccion.getTarjeta().getId(),
					transaccion.getFechaTransaccion(), transaccion.getMonto(), transaccion.isAnulada());
			return ResponseEntity.status(HttpStatus.OK).body(transaccionDTO);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	// Anulacion

	@PostMapping("/anulation")
	public ResponseEntity<?> anularTransaccion(@RequestBody AnulacionDTO anulacionDTO) {
		try {
			Transaccion transaccion = transaccionService.anularTransaccion(anulacionDTO.getNumeroTarjeta(),
					anulacionDTO.getTransactionId());
			return ResponseEntity.status(HttpStatus.OK).body("Transacción anulada con éxito");

		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
