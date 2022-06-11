package cazaVinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Categoria.Usuario;
import cazaVinchucas.ZonaDeCobertura.ZonaDeCobertura;
import cazaVinchucas.muestras.Muestra;

class SistemaTestCase {

	private Sistema sist; // SUT
	private Muestra m1, m2, m3, m4; // DOC
	private Opinion o1, o2, o3, o4; // DOC
	private Usuario u1, u2; // DOC
	private ZonaDeCobertura z1, z2; // DOC
	
	@BeforeEach
	void setUp() throws Exception {
		sist = new Sistema();
		m1 = mock(Muestra.class); m2 = mock(Muestra.class); m3 = mock(Muestra.class);
		m4 = mock(Muestra.class);
		o1 = mock(Opinion.class); o2 = mock(Opinion.class); o3 = mock(Opinion.class);
		o4 = mock(Opinion.class);
		u1 = mock(Usuario.class); u2 = mock(Usuario.class);
		
		z1 = mock(ZonaDeCobertura.class); z2 = mock(ZonaDeCobertura.class);
		
		when(m1.getUsuario()).thenReturn(u1);
		when(m2.getUsuario()).thenReturn(u1);
		when(m3.getUsuario()).thenReturn(u2);
		when(m4.getUsuario()).thenReturn(u2);
		when(o1.getUsuario()).thenReturn(u1);
		when(o2.getUsuario()).thenReturn(u2);
		when(o3.getUsuario()).thenReturn(u2);
		when(o4.getUsuario()).thenReturn(u2);
		
		when(m4.esVerificada()).thenReturn(true);
		
		sist.agregarMuestra(m1); sist.agregarMuestra(m2); sist.agregarMuestra(m3);
		sist.agregarOpinion(o1, m1); sist.agregarOpinion(o2, m2); sist.agregarOpinion(o3, m3);
		sist.agregarUsuario(u1); sist.agregarUsuario(u2);
		sist.agregarZona(z1); sist.agregarZona(z2);
	}
	
	@Test
	void test() {
		assertEquals(2, sist.getMuestrasDe(u1).size());
		assertEquals(1, sist.getOpinionesDe(u1).size());
		assertEquals(1, sist.getMuestrasDe(u2).size());
		assertEquals(2, sist.getOpinionesDe(u2).size());
	}
	
	@Test
	void sistEnviaNotificacionesALasZonasPorMuestraVerificada() {
		sist.agregarOpinion(o4, m4);
		verify(z1, times(1)).updateMuestra(m4);
		verify(z2, times(1)).updateMuestra(m4);
	}

}
