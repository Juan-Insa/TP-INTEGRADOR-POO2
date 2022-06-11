package cazaVinchucas.Busqueda.criterios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenorTestCase {

	private Menor menor;
	
	@BeforeEach
	void setUp() throws Exception{
		menor = new Menor();
	}
	@Test
	void testCriterioFechaMenor() {
		assertTrue(menor.comparar(LocalDate.parse("2022-06-09"), LocalDate.parse("2022-06-10")));
	}
	@Test
	void testCriterioFechaMenorNoEs() {
		assertFalse(menor.comparar(LocalDate.parse("2022-06-09"), LocalDate.parse("2022-06-09")));
	}

}
