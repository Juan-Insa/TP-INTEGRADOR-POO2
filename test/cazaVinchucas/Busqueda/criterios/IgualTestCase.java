package cazaVinchucas.Busqueda.criterios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IgualTestCase {

	private Igual igual;
	
	@BeforeEach
	void setUp() throws Exception{
		igual = new Igual();
	}
	@Test
	void testCriterioFechasIguales() {
		assertTrue(igual.comparar(LocalDate.parse("2022-06-10"), LocalDate.parse("2022-06-10")));
	}
	void testCriterioFechasIgualesNoSon() {
		assertFalse(igual.comparar(LocalDate.parse("2022-06-10"), LocalDate.parse("2022-06-11")));
	}
}
