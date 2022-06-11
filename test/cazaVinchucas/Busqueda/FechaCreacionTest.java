package cazaVinchucas.Busqueda;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Busqueda.criterios.Criterio;
import cazaVinchucas.muestras.Muestra;

class FechaCreacionTest {

	FechaCreacionMuestra f; //SUT
	Criterio c; //DOC
	ArrayList<Muestra> ms; //DOC
	Muestra m1,m2,m3; //DOC
	
	@BeforeEach
	void setUp() throws Exception {
		c = mock(Criterio.class);
		f = new FechaCreacionMuestra(LocalDate.now().minusWeeks(2), c);
		
		m1= mock(Muestra.class);
		m2= mock(Muestra.class);
		m3= mock(Muestra.class);

		ms = new ArrayList<Muestra>();
		ms.add(m1);ms.add(m2);ms.add(m3);
	}

	@Test
	void test() {
		//Excersice
		
		//Mockeamos el criterio, porque solo queremos ver si filtra
		//en base al valor booleano del criterio.
		when(c.comparar(eq(LocalDate.now()), any())).thenReturn(true);
		when(c.comparar(eq(LocalDate.now().minusMonths(1)), any())).thenReturn(false);
		
		//Mockeamos las muestras para que 2 de ellas cumplan el criterio mockeado.
		when(m1.getFecha()).thenReturn(LocalDate.now());
		when(m2.getFecha()).thenReturn(LocalDate.now());
		when(m3.getFecha()).thenReturn(LocalDate.now().minusMonths(1));
		
		//Verify
		
		//En este caso pasarían las muestras 1 y 2, no la 3.
		assertEquals(f.filtradas(ms).size(), 2);
		assertTrue(f.filtradas(ms).contains(m1));
		assertTrue(f.filtradas(ms).contains(m2));
		assertFalse(f.filtradas(ms).contains(m3));
	}

}
