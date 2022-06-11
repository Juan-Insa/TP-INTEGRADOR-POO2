package cazaVinchucas.Busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.muestras.Muestra;

class NivelValidacionTestCase {
	
	private NivelValidacion validada, votada; // SUT
	private Muestra m1, m2, m3; // DOC
	private List<Muestra> muestras = new ArrayList<Muestra>();
	
	@BeforeEach
	void setUp() throws Exception {
		m1 = mock(Muestra.class); m2 = mock(Muestra.class); m3 = mock(Muestra.class);
		validada = new NivelValidacion(true);
		votada = new NivelValidacion(false);
		when(m1.esVerificada()).thenReturn(true);
		when(m2.esVerificada()).thenReturn(false);
		when(m3.esVerificada()).thenReturn(true);
		muestras.add(m1); muestras.add(m2); muestras.add(m3);
	}
	
	@Test
	void testNivelValidacionVerificadas() {
		assertTrue(validada.filtradas(muestras).contains(m1));
		assertFalse(validada.filtradas(muestras).contains(m2));
		assertTrue(validada.filtradas(muestras).contains(m3));
		assertEquals(2, validada.filtradas(muestras).size());
	}
	
	@Test
	void testNivelValidacionVotadas() {
		assertFalse(votada.filtradas(muestras).contains(m1));
		assertTrue(votada.filtradas(muestras).contains(m2));
		assertFalse(votada.filtradas(muestras).contains(m3));
		assertEquals(1, votada.filtradas(muestras).size());
	}

}
