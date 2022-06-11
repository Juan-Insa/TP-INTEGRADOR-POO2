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

class ORCompositeTestCase {

	private ORComposite orc; // SUT
	private BusquedaComponent f1, f2; // DOC
	private Muestra m1, m2, m3, m4; // DOC
	private List<Muestra> muestras = new ArrayList<Muestra>();
	
	@BeforeEach
	void setUp() throws Exception{
		f1 = new TipoInsecto(Clasificacion.VINCHUCA);
		f2 = new NivelValidacion(true);
		orc = new ORComposite(f1, f2);
		m1 = mock(Muestra.class); m2 = mock(Muestra.class); m3 = mock(Muestra.class); m4 = mock(Muestra.class);
		when(m1.getResultado()).thenReturn(Clasificacion.VINCHUCA);
		when(m2.getResultado()).thenReturn(Clasificacion.NINGUNA);
		when(m3.getResultado()).thenReturn(Clasificacion.VINCHUCA);
		when(m4.getResultado()).thenReturn(Clasificacion.PHTIACHINCHE);
		when(m1.esVerificada()).thenReturn(true);
		when(m2.esVerificada()).thenReturn(false);
		when(m3.esVerificada()).thenReturn(true);
		when(m4.esVerificada()).thenReturn(true);
		muestras.add(m1); muestras.add(m2); muestras.add(m3); muestras.add(m4);
	}
	
	@Test
	void testBusquedaOR() {
		assertEquals(3, orc.filtradas(muestras).size());
		assertTrue(orc.filtradas(muestras).contains(m1));
		assertFalse(orc.filtradas(muestras).contains(m2));
		assertTrue(orc.filtradas(muestras).contains(m3));
		assertTrue(orc.filtradas(muestras).contains(m4));
	}

}
