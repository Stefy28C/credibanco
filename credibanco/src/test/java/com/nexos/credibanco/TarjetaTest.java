package com.nexos.credibanco;

import com.nexos.credibanco.entity.Tarjeta;
import com.nexos.credibanco.repository.ITarjetaRepository;
import com.nexos.credibanco.service.TarjetaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarjetaTest {

    @Mock
    private ITarjetaRepository tarjetaRepository;

    @InjectMocks
    private TarjetaService tarjetaService;
    private Tarjeta tarjeta;
    private String numeroTarjeta;
    private Long id;
    private Optional<Tarjeta> optionalTarjeta;

    @BeforeEach
    void setUp() {
    	   tarjeta = new Tarjeta("123456", "John Doe");
           numeroTarjeta = "1234567890123456";
           id = 1L;
           optionalTarjeta = Optional.of(tarjeta);
    }


   

    @Test
    void testCrearTarjeta() {
        Tarjeta nuevaTarjeta = tarjetaService.crearTarjeta("123456", "John Doe");
        assertNotNull(nuevaTarjeta);
        assertEquals("123456", nuevaTarjeta.getIdProducto());
        assertEquals("John Doe", nuevaTarjeta.getNombreTitular());
    }
    
    @Test
    void testNumeroTarjeta(){
    	 Tarjeta tarjeta = new Tarjeta("123456", "John Doe");
    	    String numeroTarjeta = "1234567890123456";
    	    Long id = 1L;
    }
    @Test
    void testGuardar() {
        when(tarjetaRepository.save(tarjeta)).thenReturn(tarjeta);
        Tarjeta tarjetaGuardada = tarjetaService.guardar(tarjeta);
        assertEquals(tarjeta, tarjetaGuardada);
    }

    @Test
    void testActivarTarjeta() {
        when(tarjetaRepository.save(tarjeta)).thenReturn(tarjeta);
        Tarjeta tarjetaActivada = tarjetaService.activarTarjeta(tarjeta);
        assertTrue(tarjetaActivada.isActivada());
    }

    
    @Test
    void testBuscarPorNumeroTarjeta() {
        // Configura el comportamiento del repositorio mock
        when(tarjetaRepository.findByNumeroTarjeta(numeroTarjeta)).thenReturn(optionalTarjeta);

        // Ejecuta el método a probar
        Optional<Tarjeta> tarjetaEncontrada = tarjetaService.buscarPorNumeroTarjeta(numeroTarjeta);

        // Verifica que se haya encontrado la tarjeta correcta
        assertTrue(tarjetaEncontrada.isPresent());
        assertEquals(tarjeta, tarjetaEncontrada.get());
    }

    @Test
    void testBuscarPorId() {
        // Configura el comportamiento del repositorio mock
        when(tarjetaRepository.findById(id)).thenReturn(optionalTarjeta);

        // Ejecuta el método a probar
        Optional<Tarjeta> tarjetaEncontrada = tarjetaService.buscarPorId(id);

        // Verifica que se haya encontrado la tarjeta correcta
        assertTrue(tarjetaEncontrada.isPresent());
        assertEquals(tarjeta, tarjetaEncontrada.get());
    }

    @Test
    void testBloquearTarjeta() {
        // Configura el comportamiento del repositorio mock
        when(tarjetaRepository.save(tarjeta)).thenReturn(tarjeta);

        // Ejecuta el método a probar
        Tarjeta tarjetaBloqueada = tarjetaService.bloquearTarjeta(tarjeta);

        // Verifica que la tarjeta esté bloqueada
        assertTrue(tarjetaBloqueada.getBloqueada());
    }

    @Test
    void testDesbloquearTarjeta() {
        // Bloquea la tarjeta antes de probar el desbloqueo
        tarjeta.bloquearTarjeta();

        // Configura el comportamiento del repositorio mock
        when(tarjetaRepository.save(tarjeta)).thenReturn(tarjeta);

        // Ejecuta el método a probar
        Tarjeta tarjetaDesbloqueada = tarjetaService.desbloquearTarjeta(tarjeta);

        // Verifica que la tarjeta esté desbloqueada
        assertFalse(tarjetaDesbloqueada.getBloqueada());
    }
    @Test
    void testGenerarNumeroTarjeta() {
        String idProducto = "123456"; // Reemplaza esto con el valor real de idProducto que deseas utilizar
        String numeroTarjeta = Tarjeta.generarNumeroTarjeta(idProducto);
        assertNotNull(numeroTarjeta);
        assertEquals(16, numeroTarjeta.length());
    }


    @Test
    void testGenerarFechaVencimiento() {
        LocalDate fechaVencimiento = tarjeta.generarFechaVencimiento();
        assertNotNull(fechaVencimiento);
        assertEquals(LocalDate.now().plusYears(5), fechaVencimiento);
    }

    @Test
    void testActivarTarjeta1() {
        Tarjeta tarjeta = new Tarjeta("123456", "John Doe");

        // Comprueba que la tarjeta no esté activada inicialmente
        assertFalse(tarjeta.isActivada());

        // Activa la tarjeta
        tarjeta.activarTarjeta();

        // Comprueba que la tarjeta esté activada después de llamar al método activarTarjeta
        assertTrue(tarjeta.isActivada());
    }

    @Test
    void testIsActivada() {
        Tarjeta tarjeta = new Tarjeta("123456", "John Doe");

        // Comprueba que la tarjeta no esté activada inicialmente
        assertFalse(tarjeta.isActivada());

        // Activa la tarjeta
        tarjeta.activarTarjeta();

        // Comprueba que la tarjeta esté activada después de activarla
        assertTrue(tarjeta.isActivada());

        // Bloquea la tarjeta
        tarjeta.bloquearTarjeta();

        // Comprueba que la tarjeta siga activada incluso después de bloquearla
        assertTrue(tarjeta.isActivada());
    }

    

}
