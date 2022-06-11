package cazaVinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Categoria.Usuario;
import cazaVinchucas.muestras.Muestra;

class SistemaTestCase {

	private Sistema sist; // SUT
	private Muestra m1, m2, m3; // DOC
	private Opinion o1, o2, o3; // DOC
	private Usuario u1, u2; // DOC
	
	@BeforeEach
	void setUp() throws Exception {
		sist = new Sistema();
		m1 = mock(Muestra.class); m2 = mock(Muestra.class); m3 = mock(Muestra.class);
		o1 = mock(Opinion.class); o2 = mock(Opinion.class); o3 = mock(Opinion.class);
		u1 = mock(Usuario.class); u2 = mock(Usuario.class);
		when(m1.getUsuario()).thenReturn(u1);
		when(m2.getUsuario()).thenReturn(u1);
		when(m3.getUsuario()).thenReturn(u2);
		when(o1.getUsuario()).thenReturn(u1);
		when(o2.getUsuario()).thenReturn(u2);
		when(o3.getUsuario()).thenReturn(u2);
		sist.agregarMuestra(m1); sist.agregarMuestra(m2); sist.agregarMuestra(m3);
		sist.agregarOpinion(o1, m1); sist.agregarOpinion(o2, m2); sist.agregarOpinion(o3, m3);
		sist.agregarUsuario(u1); sist.agregarUsuario(u2);
	}
	
	@Test
	void test() {
		assertEquals(2, sist.getMuestrasDe(u1).size());
		assertEquals(1, sist.getOpinionesDe(u1).size());
		assertEquals(1, sist.getMuestrasDe(u2).size());
		assertEquals(2, sist.getOpinionesDe(u2).size());
	}

}
