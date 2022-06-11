package cazaVinchucas.Categoria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Opinion;
import cazaVinchucas.Sistema;
import cazaVinchucas.muestras.Muestra;

class BasicoTestCase {

	private Categoria cat; // SUT
	private Sistema sistema; //DOC
	private Usuario us; //DOC
	private Muestra m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12;
	private Opinion o1,o2,o3,o4,o5,o6,o7,o8,o9,o10,o11,o22,
					o12,o13,o14,o15,o16,o17,o18,o19,o20,o21;
	private ArrayList<Muestra> conMas, conMenos, conMasPeroVieja;
	private ArrayList<Opinion> conMasO, conMenosO, conMasOPeroVieja;
	
	@BeforeEach
	void setUp() throws Exception {
		
		sistema = mock(Sistema.class);
		us = mock(Usuario.class);
		conMas = new ArrayList<Muestra>();
		conMasPeroVieja = new ArrayList<Muestra>();
		conMenos = new ArrayList<Muestra>();
		conMasO = new ArrayList<Opinion>();
		conMasOPeroVieja = new ArrayList<Opinion>();
		conMenosO = new ArrayList<Opinion>();
		
		cat = new Basico();
		
		//Muestras
		m1= mock(Muestra.class);
		when(m1.getFecha()).thenReturn(LocalDate.now());
		conMas.add(m1);conMenos.add(m1);conMasPeroVieja.add(m1);
		m2= mock(Muestra.class);
		when(m2.getFecha()).thenReturn(LocalDate.now());
		conMas.add(m2);conMenos.add(m2);conMasPeroVieja.add(m2);
		m3= mock(Muestra.class);
		when(m3.getFecha()).thenReturn(LocalDate.now());
		conMas.add(m3);conMenos.add(m3);conMasPeroVieja.add(m3);
		m4= mock(Muestra.class);
		when(m4.getFecha()).thenReturn(LocalDate.now());
		conMas.add(m4);conMenos.add(m4);conMasPeroVieja.add(m4);
		m5= mock(Muestra.class);
		when(m5.getFecha()).thenReturn(LocalDate.now());
		conMas.add(m5);conMenos.add(m5);conMasPeroVieja.add(m5);
		m6= mock(Muestra.class);
		when(m6.getFecha()).thenReturn(LocalDate.now());
		conMas.add(m6);conMenos.add(m6);conMasPeroVieja.add(m6);
		m7= mock(Muestra.class);
		when(m7.getFecha()).thenReturn(LocalDate.now());
		conMas.add(m7);conMenos.add(m7);conMasPeroVieja.add(m7);
		m8= mock(Muestra.class);
		when(m8.getFecha()).thenReturn(LocalDate.now());
		conMas.add(m8);conMenos.add(m8);conMasPeroVieja.add(m8);
		m9= mock(Muestra.class);
		when(m9.getFecha()).thenReturn(LocalDate.now());
		conMas.add(m9);conMenos.add(m9);conMasPeroVieja.add(m9);
		m10= mock(Muestra.class);
		when(m10.getFecha()).thenReturn(LocalDate.now());
		conMas.add(m10);conMenos.add(m10);conMasPeroVieja.add(m10);
		m11 = mock(Muestra.class);
		when(m11.getFecha()).thenReturn(LocalDate.now());
		conMas.add(m11);
		m12 = mock(Muestra.class);
		when(m12.getFecha()).thenReturn(LocalDate.now().minusDays(31));
		conMasPeroVieja.add(m12);
		
		//Opiniones
		o1= mock(Opinion.class);
		when(o1.getFecha()).thenReturn(LocalDate.now());
		conMasO.add(o1);conMenosO.add(o1);conMasOPeroVieja.add(o1);
		o2= mock(Opinion.class);
		conMasO.add(o2);conMenosO.add(o2);conMasOPeroVieja.add(o2);
		when(o2.getFecha()).thenReturn(LocalDate.now());
		o3= mock(Opinion.class);
		conMasO.add(o3);conMenosO.add(o3);conMasOPeroVieja.add(o3);
		when(o3.getFecha()).thenReturn(LocalDate.now());
		o4= mock(Opinion.class);
		conMasO.add(o4);conMenosO.add(o4);conMasOPeroVieja.add(o4);
		when(o4.getFecha()).thenReturn(LocalDate.now());
		o5= mock(Opinion.class);
		conMasO.add(o5);conMenosO.add(o5);conMasOPeroVieja.add(o5);
		when(o5.getFecha()).thenReturn(LocalDate.now());
		o6= mock(Opinion.class);
		conMasO.add(o6);conMenosO.add(o6);conMasOPeroVieja.add(o6);
		when(o6.getFecha()).thenReturn(LocalDate.now());
		o7= mock(Opinion.class);
		conMasO.add(o7);conMenosO.add(o7);conMasOPeroVieja.add(o7);
		when(o7.getFecha()).thenReturn(LocalDate.now());
		o8= mock(Opinion.class);
		conMasO.add(o8);conMenosO.add(o8);conMasOPeroVieja.add(o8);
		when(o8.getFecha()).thenReturn(LocalDate.now());
		o9= mock(Opinion.class);
		conMasO.add(o9);conMenosO.add(o9);conMasOPeroVieja.add(o9);
		when(o9.getFecha()).thenReturn(LocalDate.now());
		o10= mock(Opinion.class);
		conMasO.add(o10);conMenosO.add(o10);conMasOPeroVieja.add(o10);
		when(o10.getFecha()).thenReturn(LocalDate.now());
		o11= mock(Opinion.class);
		conMasO.add(o11);conMenosO.add(o11);conMasOPeroVieja.add(o11);
		when(o11.getFecha()).thenReturn(LocalDate.now());
		o12= mock(Opinion.class);
		conMasO.add(o12);conMenosO.add(o12);conMasOPeroVieja.add(o12);
		when(o12.getFecha()).thenReturn(LocalDate.now());
		o13= mock(Opinion.class);
		conMasO.add(o13);conMenosO.add(o13);conMasOPeroVieja.add(o13);
		when(o13.getFecha()).thenReturn(LocalDate.now());
		o14= mock(Opinion.class);
		conMasO.add(o14);conMenosO.add(o14);conMasOPeroVieja.add(o14);
		when(o14.getFecha()).thenReturn(LocalDate.now());
		o15= mock(Opinion.class);
		conMasO.add(o15);conMenosO.add(o15);conMasOPeroVieja.add(o15);
		when(o15.getFecha()).thenReturn(LocalDate.now());
		o16= mock(Opinion.class);
		conMasO.add(o16);conMenosO.add(o16);conMasOPeroVieja.add(o16);
		when(o16.getFecha()).thenReturn(LocalDate.now());
		o17= mock(Opinion.class);
		conMasO.add(o17);conMenosO.add(o17);conMasOPeroVieja.add(o17);
		when(o17.getFecha()).thenReturn(LocalDate.now());
		o18= mock(Opinion.class);
		conMasO.add(o18);conMenosO.add(o18);conMasOPeroVieja.add(o18);
		when(o18.getFecha()).thenReturn(LocalDate.now());
		o19= mock(Opinion.class);
		conMasO.add(o19);conMenosO.add(o19);conMasOPeroVieja.add(o19);
		when(o19.getFecha()).thenReturn(LocalDate.now());
		o20= mock(Opinion.class);
		conMasO.add(o20);conMenosO.add(o20);conMasOPeroVieja.add(o20);
		when(o20.getFecha()).thenReturn(LocalDate.now());
		o21= mock(Opinion.class);
		conMasO.add(o21);
		when(o21.getFecha()).thenReturn(LocalDate.now());
		o22= mock(Opinion.class);
		conMasOPeroVieja.add(o22);
		when(o22.getFecha()).thenReturn(LocalDate.now().minusDays(31));
		
	}
	
	@Test
	void basicoNoEsExperto() {
		assertEquals(false, cat.esExperto()); //Verify
	}
	
	@Test
	void conMuestrasYOpionesSuficientes() {
		//Excercise
		when(sistema.getMuestrasDe(us)).thenReturn(conMas);
		when(sistema.getOpinionesDe(us)).thenReturn(conMasO);
		cat.recategorizar(sistema, us);
		
		//Verify
		verify(us, times(1)).setCategoria(any());
		//assertTrue(us.esExperto());
	}
	
	@Test
	void conMuestrasInsuficientes() {
		//Excercise
		when(sistema.getMuestrasDe(us)).thenReturn(conMenos);
		when(sistema.getOpinionesDe(us)).thenReturn(conMasO);
		cat.recategorizar(sistema, us);
		
		//Verify
		verify(us, never()).setCategoria(any());
		//assertFalse(us.esExperto());
	}
	
	@Test
	void conMuestrasSuficientesPeroViejas() {
		//Excercise
		when(sistema.getMuestrasDe(us)).thenReturn(conMasPeroVieja);
		when(sistema.getOpinionesDe(us)).thenReturn(conMasO);
		cat.recategorizar(sistema, us);
		
		//Verify
		verify(us, never()).setCategoria(any());
		//assertFalse(us.esExperto());
	}
	
	@Test
	void sinOpinionesSuficientes() {
		//Excercise
		when(sistema.getMuestrasDe(us)).thenReturn(conMas);
		when(sistema.getOpinionesDe(us)).thenReturn(conMenosO);
		cat.recategorizar(sistema, us);
		
		//Verify
		verify(us, never()).setCategoria(any());
		//assertFalse(us.esExperto());
	}
	
	@Test
	void conOpinionesSuficientesPeroViejas() {
		//Excercise
		when(sistema.getMuestrasDe(us)).thenReturn(conMas);
		when(sistema.getOpinionesDe(us)).thenReturn(conMasOPeroVieja);
		cat.recategorizar(sistema, us);
		
		//Verify
		verify(us, never()).setCategoria(any());
		//assertFalse(us.esExperto());
	}
}
