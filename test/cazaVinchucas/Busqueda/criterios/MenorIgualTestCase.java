package cazaVinchucas.Busqueda.criterios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MenorIgualTestCase {

	private MenorIgual menorigual;
	
	@BeforeEach
	void setUp() throws Exception{
		menorigual = new MenorIgual();
	}
	@Test
	void testCriterioFechaMenorIgualEsMayor() {
		assertTrue(menorigual.comparar(LocalDate.parse("2022-06-09"), LocalDate.parse("2022-06-10")));
	}
	@Test
	void testCriterioFechaMenorIgualNoEs() {
		assertFalse(menorigual.comparar(LocalDate.parse("2022-06-10"), LocalDate.parse("2022-06-09")));
	}
	@Test
	void testCriterioFechaMenorIgualEsIgual() {
		assertTrue(menorigual.comparar(LocalDate.parse("2022-06-10"), LocalDate.parse("2022-06-10")));
	}

}
