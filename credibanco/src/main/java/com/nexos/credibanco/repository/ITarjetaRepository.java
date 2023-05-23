package com.nexos.credibanco.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexos.credibanco.entity.Tarjeta;

@Repository
public interface ITarjetaRepository extends JpaRepository<Tarjeta, Long> {
	Optional<Tarjeta> findByNumeroTarjeta(String numeroTarjeta);
}
