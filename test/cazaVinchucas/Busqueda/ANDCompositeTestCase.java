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

class ANDCompositeTestCase {

	private ANDComposite andc; // SUT
	private BusquedaComponent f1, f2; // DOC
	private Muestra m1, m2, m3; // DOC
	private List<Muestra> muestras = new ArrayList<Muestra>();
	
	@BeforeEach
	void setUp() throws Exception{
		f1 = new TipoInsecto(Clasificacion.VINCHUCA);
		f2 = new NivelValidacion(true);
		andc = new ANDComposite(f1, f2);
		m1 = mock(Muestra.class); m2 = mock(Muestra.class); m3 = mock(Muestra.class);
		when(m1.getResultado()).thenReturn(Clasificacion.VINCHUCA);
		when(m2.getResultado()).thenReturn(Clasificacion.NINGUNA);
		when(m3.getResultado()).thenReturn(Clasificacion.VINCHUCA);
		when(m1.esVerificada()).thenReturn(true);
		when(m2.esVerificada()).thenReturn(false);
		when(m3.esVerificada()).thenReturn(true);
		muestras.add(m1); muestras.add(m2); muestras.add(m3);
	}
	
	@Test
	void testBusquedaAND() {
		assertEquals(2, andc.filtradas(muestras).size());
		assertTrue(andc.filtradas(muestras).contains(m1));
		assertFalse(andc.filtradas(muestras).contains(m2));
		assertTrue(andc.filtradas(muestras).contains(m3));
	}

}
