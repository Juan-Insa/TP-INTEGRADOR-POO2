package cazaVinchucas.Categoria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExpertoTestCase {

	private Categoria cat; // SUT
	
	@BeforeEach
	void setUp() throws Exception {
		cat = new Experto();
	}
	@Test
	void expertoEsExperto() {
		assertEquals(true, cat.esExperto()); //Verify
	}

}
