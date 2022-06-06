package cazaVinchucas.organizaciones;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.muestras.Muestra;

class OrganizacionTest {
	private Organizacion o;    // SUT
	private FuncionalidadExterna fNueva1, fVerif1, fNueva2, fVerif2;
	private ZonaDeCobertura zona1, zona2;
	private Muestra m1, m2;

	@BeforeEach
	void setUp() throws Exception {
		
		// mocks de FuncionalidadExterna
		fNueva1 = mock(FuncionalidadExterna.class); fVerif1 = mock(FuncionalidadExterna.class);
		fNueva2 = mock(FuncionalidadExterna.class); fVerif2 = mock(FuncionalidadExterna.class);
	
	    // mocks de ZonaDeCobertura
		zona1 = mock(ZonaDeCobertura.class); zona2 = mock(ZonaDeCobertura.class);
	
	    // mocks de muestras
		m1 = mock(Muestra.class); m2 = mock(Muestra.class);
		
	    // retornos de esVerificada para muestra
		when(m1.esVerificada()).thenReturn(true); when(m2.esVerificada()).thenReturn(false);  
	    
		o = new Organizacion("Gubernamental", 60, fNueva1, fVerif1);
	}

	@Test // activarEvento
	void activarEventoEnviaUnMensajeALaFuncionalidadExternaDeMuestraVerificada() {
		o.activarEvento(zona1, m1);
		verify(fNueva1, never()).nuevoEvento(o, zona1, m1);
		verify(fVerif1, times(1)).nuevoEvento(o, zona1, m1);
	}
	
	@Test // activarEvento
	void activarEventoEnviaUnMensajeALaFuncionalidadExternaDeMuestraNueva() {
		o.activarEvento(zona1, m2);
		verify(fVerif1, never()).nuevoEvento(o, zona1, m2);
		verify(fNueva1, times(1)).nuevoEvento(o, zona1, m2);
	}
	
	@Test // registrar
	void registrarAgregaLaZonaALaOrganizacion() {
		o.registrar(zona1);
		assertTrue(o.getZonasCubiertas().contains(zona1));
	}
	
	@Test // desRegistrar
	void desRegistrarEliminaLaZonaALaOrganizacion() {
		o.registrar(zona1);
		assertTrue(o.getZonasCubiertas().contains(zona1));
		
		o.desRegistrar(zona1);
		assertFalse(o.getZonasCubiertas().contains(zona1));
	}
	
	@Test // getCantidadEmpleados
	void getCantidadEmpleadosDevuelve60() {
		assertEquals(60, o.getCantidadEmpleados());
	}
	
	@Test // setCantidadEmpleados
	void setCantidadEmpleadosCambiaLaCantidadA100() {
		o.setCantidadEmpleados(100);
		assertEquals(100, o.getCantidadEmpleados());
	}
	
	@Test // setEventoNuevaMuestraC
	void setEventoNuevaMuestraCambiaElEventoDeLaNuevaMuestra() {
		o.setEventoNuevaMuestra(fNueva2);
		o.activarEvento(zona1, m2);
		verify(fNueva2, times(1)).nuevoEvento(o, zona1, m2);
	}
	
	@Test // setEventoMuestraValidada
	void setEventoMuestraValidadaCambiaElEventoDeLaMuestraValidada() {
		o.setEventoMuestraValidada(fVerif2);
		o.activarEvento(zona1, m1);
		verify(fVerif2, times(1)).nuevoEvento(o, zona1, m1);
	}
	
	@Test // getZonasCubiertas
	void getZonasCubiertas() {
		ArrayList<ZonaDeCobertura> zonas = new ArrayList<ZonaDeCobertura>();
		zonas.add(zona1); zonas.add(zona2);
		
		assertTrue(o.getZonasCubiertas().isEmpty());
		
		o.registrar(zona1);
		o.registrar(zona2);
		
		assertTrue(zonas.containsAll(o.getZonasCubiertas()));
	}

}
