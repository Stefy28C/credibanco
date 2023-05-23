package com.nexos.credibanco.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexos.credibanco.entity.Tarjeta;
import com.nexos.credibanco.repository.ITarjetaRepository;

@Service
public class TarjetaService {
	@Autowired
	private ITarjetaRepository tarjetaRepository;

	// Métodos que implementan la lógica de negocio relacionada con Tarjeta
	// Servicio TarjetaService
	public Tarjeta crearTarjeta(String idProducto, String nombreTitular) {
		Tarjeta nuevaTarjeta = new Tarjeta(idProducto, nombreTitular);
		// Implementa la lógica para guardar la tarjeta en la base de datos si es
		// necesario
		return nuevaTarjeta;
	}

	public Tarjeta guardar(Tarjeta tarjeta) {
		return tarjetaRepository.save(tarjeta);
	}

	// activar tarjeta
	public Tarjeta activarTarjeta(Tarjeta tarjeta) {
		tarjeta.activarTarjeta();
		// Guarda la tarjeta actualizada en la base de datos
		return tarjetaRepository.save(tarjeta);
	}

	// Buscar por el numero de tarjeta
	public Optional<Tarjeta> buscarPorNumeroTarjeta(String numeroTarjeta) {
		return tarjetaRepository.findByNumeroTarjeta(numeroTarjeta);
	}

	public Tarjeta bloquearTarjeta(Tarjeta tarjeta) {
		tarjeta.bloquearTarjeta();
		// Implementa la lógica para actualizar el estado de la tarjeta en la base de
		// datos si es necesario
		return tarjetaRepository.save(tarjeta);
	}

	public Optional<Tarjeta> buscarPorId(Long cardId) {
		return tarjetaRepository.findById(cardId);
	}

	
	//desbloquear la tarjeta
	public Tarjeta desbloquearTarjeta(Tarjeta tarjeta) {
	    tarjeta.desbloquearTarjeta();
	    // Implementa la lógica para actualizar el estado de la tarjeta en la base de datos si es necesario
	    return tarjetaRepository.save(tarjeta);
	}

}
