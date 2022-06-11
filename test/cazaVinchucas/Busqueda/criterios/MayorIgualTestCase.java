package cazaVinchucas.Busqueda.criterios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MayorIgualTestCase {

	private MayorIgual mayorigual;
	@BeforeEach
	void setUp() throws Exception{
		mayorigual = new MayorIgual();
	}
	@Test
	void testCriterioFechaMayorIgualEsMayor() {
		assertTrue(mayorigual.comparar(LocalDate.parse("2022-06-11"), LocalDate.parse("2022-06-10")));
	}
	@Test
	void testCriterioFechaMayorIgualNoEs() {
		assertFalse(mayorigual.comparar(LocalDate.parse("2022-06-09"), LocalDate.parse("2022-06-10")));
	}
	@Test
	void testCriterioFechaMayorIgualEsIgual() {
		assertTrue(mayorigual.comparar(LocalDate.parse("2022-06-10"), LocalDate.parse("2022-06-10")));
	}
}
