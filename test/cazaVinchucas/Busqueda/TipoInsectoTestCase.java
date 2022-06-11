package cazaVinchucas.Busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Clasificacion;
import cazaVinchucas.muestras.Muestra;

class TipoInsectoTestCase {

	private TipoInsecto tipo; // SUT
	private Muestra m1, m2; // DOC
	private List<Muestra> muestras = new ArrayList<Muestra>();
	
	@BeforeEach
	void setUp() throws Exception {
		m1 = mock(Muestra.class); m2 = mock(Muestra.class);
		tipo = new TipoInsecto(Clasificacion.VINCHUCA);
		when(m1.getResultado()).thenReturn(Clasificacion.VINCHUCA);
		when(m2.getResultado()).thenReturn(Clasificacion.NINGUNA);
		muestras.add(m1); muestras.add(m2);
	}
	
	@Test
	void testTipoInsectoContieneMuestraConTipo() {
		assertTrue(tipo.filtradas(muestras).contains(m1));
		assertEquals(1, tipo.filtradas(muestras).size());
	}

	@Test
	void testTipoInsectoNoContieneMuestraConTipo() {
		muestras.remove(m1);
		assertFalse(tipo.filtradas(muestras).contains(m1));
		assertEquals(0, tipo.filtradas(muestras).size());
	}
}
