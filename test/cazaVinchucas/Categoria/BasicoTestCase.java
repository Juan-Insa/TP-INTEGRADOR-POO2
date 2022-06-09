package cazaVinchucas.Categoria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasicoTestCase {

	private Categoria cat; // SUT

	@BeforeEach
	void setUp() throws Exception {
		cat = new Basico();
	}
	@Test
	void basicoNoEsExperto() {
		assertEquals(false, cat.esExperto()); //Verify
	}
}
