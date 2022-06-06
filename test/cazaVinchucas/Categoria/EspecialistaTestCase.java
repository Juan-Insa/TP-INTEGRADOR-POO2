package cazaVinchucas.Categoria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Sistema;
import cazaVinchucas.Usuario;

class EspecialistaTestCase {
	private Sistema s;
	private Usuario us1;

	@BeforeEach
	void setUp() throws Exception {
		us1 = new Usuario(123, true);
	}
	@Test
	void especialistaEsExperto() {
		assertEquals(true, us1.esExperto());
	}

}
