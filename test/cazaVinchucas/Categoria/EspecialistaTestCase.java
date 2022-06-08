package cazaVinchucas.Categoria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EspecialistaTestCase {
	
	private Categoria cat; // SUT

	@BeforeEach
	void setUp() throws Exception {
		cat = new Especialista();
	}
	@Test
	void especialistaEsExperto() {
		assertEquals(true, cat.esExperto()); //Verify
	}

}
