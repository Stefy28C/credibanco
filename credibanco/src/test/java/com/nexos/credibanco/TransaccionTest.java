package com.nexos.credibanco;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import com.nexos.credibanco.dto.AnulacionDTO;
import com.nexos.credibanco.dto.CompraDTO;
import com.nexos.credibanco.entity.Tarjeta;
import com.nexos.credibanco.entity.Transaccion;
import com.nexos.credibanco.repository.ITarjetaRepository;
import com.nexos.credibanco.repository.ITransaccionRepository;
import com.nexos.credibanco.resource.TransaccionResource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransaccionTest {

	  @InjectMocks
	    private TransaccionResource transaccionResource;

	    @Mock
	    private ITarjetaRepository iTarjetaRepository;

	    @Mock
	    private ITransaccionRepository iTransaccionRepository;

	    private Tarjeta tarjeta;
	    private Transaccion transaccion;
	    private CompraDTO compraDTO;
	    private AnulacionDTO anulacionDTO;

	    @BeforeEach
	    public void setUp() {
	        tarjeta = new Tarjeta();
	        // Inicializa los atributos de la tarjeta

	        transaccion = new Transaccion();
	        // Inicializa los atributos de la transacción

	        compraDTO = new CompraDTO();
	        // Inicializa los atributos del DTO de compra

	        anulacionDTO = new AnulacionDTO();
	        // Inicializa los atributos del DTO de anulación
	    }

	    @Test
	    public void testRealizarCompra() {
	        // Configura el comportamiento de los mocks
	        when(iTarjetaRepository.findByNumeroTarjeta(any(String.class))).thenReturn(Optional.of(tarjeta));
	        when(iTransaccionRepository.save(any(Transaccion.class))).thenReturn(transaccion);
	        when(iTarjetaRepository.save(any(Tarjeta.class))).thenReturn(tarjeta);

	        // Llama al método que se va a probar
	        ResponseEntity<?> response = transaccionResource.realizarCompra(compraDTO);

	        // Verifica el resultado
	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals("Transacción realizada con éxito", response.getBody());
	    }
	    
	    @Test
	    public void testRealizarCompraTarjetaNoEncontrada() {
	        when(iTarjetaRepository.findByNumeroTarjeta(any(String.class))).thenReturn(Optional.empty());

	        assertThrows(RuntimeException.class, () -> transaccionResource.realizarCompra(compraDTO));
	    }

	    @Test
	    public void testRealizarCompraSaldoInsuficiente() {
	        tarjeta.setSaldo(BigDecimal.valueOf(100));
	        compraDTO.setMonto(BigDecimal.valueOf(200));
	        when(iTarjetaRepository.findByNumeroTarjeta(any(String.class))).thenReturn(Optional.of(tarjeta));

	        assertThrows(RuntimeException.class, () -> transaccionResource.realizarCompra(compraDTO));
	    }
	    @Test
	    public void testRealizarCompraTarjetaVencida() {
	        tarjeta.setFechaVencimiento(LocalDateTime.now().minusDays(1).toLocalDate());
	        when(iTarjetaRepository.findByNumeroTarjeta(any(String.class))).thenReturn(Optional.of(tarjeta));

	        assertThrows(RuntimeException.class, () -> transaccionResource.realizarCompra(compraDTO));
	    }
	    
	    @Test
	    public void testRealizarCompraTarjetaBloqueada() {
	        tarjeta.setBloqueada(true);
	        when(iTarjetaRepository.findByNumeroTarjeta(any(String.class))).thenReturn(Optional.of(tarjeta));

	        assertThrows(RuntimeException.class, () -> transaccionResource.realizarCompra(compraDTO));
	    }



}
