package com.nexos.credibanco.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexos.credibanco.entity.Tarjeta;
import com.nexos.credibanco.entity.Transaccion;
import com.nexos.credibanco.repository.ITarjetaRepository;
import com.nexos.credibanco.repository.ITransaccionRepository;

@Service
public class TransaccionService {
	  @Autowired
	    private ITransaccionRepository iTransaccionRepository;

	    @Autowired
	    private ITarjetaRepository iTarjetaRepository;

	    public Transaccion realizarCompra(String numeroTarjeta, BigDecimal monto) {
	        Optional<Tarjeta> tarjetaOptional = iTarjetaRepository.findByNumeroTarjeta(numeroTarjeta);
	        Tarjeta tarjeta = tarjetaOptional.orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

	        Transaccion transaccion = new Transaccion(null, tarjeta, LocalDateTime.now(), monto, false);
	        String validationResult = transaccion.validateTransaction();
	        if (validationResult != null) {
	            throw new RuntimeException(validationResult);
	        }

	        tarjeta.setSaldo(tarjeta.getSaldo().subtract(transaccion.getMonto()));
	        iTarjetaRepository.save(tarjeta);
	        return iTransaccionRepository.save(transaccion);
	    }

	    public Transaccion consultarTransaccion(Long transactionId) {
	        return iTransaccionRepository.findById(transactionId).orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
	    }

	    public Transaccion anularTransaccion(String numeroTarjeta, Long transactionId) {
	        Optional<Tarjeta> tarjetaOptional = iTarjetaRepository.findByNumeroTarjeta(numeroTarjeta);
	        Tarjeta tarjeta = tarjetaOptional.orElseThrow(() -> new RuntimeException("Tarjeta no encontrada"));

	        Transaccion transaccion = iTransaccionRepository.findById(transactionId).orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

	        if (!transaccion.getTarjeta().getId().equals(tarjeta.getId())) {
	            throw new RuntimeException("La transacción no pertenece a la tarjeta");
	        }

	        LocalDateTime now = LocalDateTime.now();
	        if (transaccion.getFechaTransaccion().plusHours(24).isBefore(now)) {
	            throw new RuntimeException("La transacción no se puede anular, ya pasaron más de 24 horas");
	        }

	        transaccion.setAnulada(true);
	        tarjeta.setSaldo(tarjeta.getSaldo().add(transaccion.getMonto()));
	        iTarjetaRepository.save(tarjeta);
	        return iTransaccionRepository.save(transaccion);
	    }
	}

