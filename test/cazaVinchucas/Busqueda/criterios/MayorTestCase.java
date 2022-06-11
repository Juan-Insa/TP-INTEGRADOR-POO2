package cazaVinchucas.Busqueda.criterios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MayorTestCase {

	private Mayor mayor;
	
	@BeforeEach
	void setUp() throws Exception{
		mayor = new Mayor();
	}
	@Test
	void testCriterioFechaMayor() {
		assertTrue(mayor.comparar(LocalDate.parse("2022-06-11"), LocalDate.parse("2022-06-10")));
	}
	@Test
	void testCriterioFechaMayorNoEs() {
		assertFalse(mayor.comparar(LocalDate.parse("2022-06-09"), LocalDate.parse("2022-06-10")));
	}

}
