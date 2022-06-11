package cazaVinchucas.Categoria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Sistema;

class EspecialistaTestCase {
	
	private Categoria cat; // SUT
	private Sistema sist;
	private Usuario user; // DOC

	@BeforeEach
	void setUp() throws Exception {
		cat = new Especialista(); // SUT
		sist = mock(Sistema.class); // DOC
		user = mock(Usuario.class);
	}
	@Test
	void especialistaEsExperto() {
		assertEquals(true, cat.esExperto()); //Verify
	}
	@Test
	void recategorizarAEspecialista() {
		cat.recategorizar(sist, user); // Exercise
		assertTrue(cat.esExperto()); //Verify
	}

}
