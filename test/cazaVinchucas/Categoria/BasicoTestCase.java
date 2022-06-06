package cazaVinchucas.Categoria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Sistema;
import cazaVinchucas.Usuario;

class BasicoTestCase {

	private Sistema s;
	private Usuario us1;

	@BeforeEach
	void setUp() throws Exception {
		us1 = new Usuario(123, false);
	}
	@Test
	void basicoNoEsExperto() {
		assertEquals(false, us1.esExperto());
	}

}
