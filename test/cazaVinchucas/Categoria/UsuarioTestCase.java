package cazaVinchucas.Categoria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Clasificacion;
import cazaVinchucas.Opinion;
import cazaVinchucas.Sistema;
import cazaVinchucas.Ubicacion;
import cazaVinchucas.Categoria.Basico;
import cazaVinchucas.Categoria.Especialista;
import cazaVinchucas.Categoria.Experto;
import cazaVinchucas.Categoria.Usuario;
import cazaVinchucas.muestras.Muestra;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

class UsuarioTestCase {

	private Usuario usba1, usex2, uses3; // SUT (usba = usuario basico, usex = usuario experto, uses = usuario especialista)
	private Basico catba; // DOC
	private Experto catex; // DOC
	private Especialista cates; // DOC (catus = categoria basico, catex = categoria experto, cates = categoria especialista)
	private Sistema sist; // DOC
	private Muestra m1, m2; // DOC
	
	@BeforeEach
	void setUp() throws Exception {
		sist = mock(Sistema.class);
		usba1 = new Usuario(false);
		usex2 = new Usuario(false);
		uses3 = new Usuario(true);
		catba = mock(Basico.class);
		catex = mock(Experto.class);
		cates = mock(Especialista.class);
		usex2.setCategoria(catex);
		m1 = mock(Muestra.class); m2 = mock(Muestra.class);
		when(m1.getUsuario()).thenReturn(usba1);
		when(m2.getUsuario()).thenReturn(usex2);
		when(catba.esExperto()).thenReturn(false);
		when(catex.esExperto()).thenReturn(true);
		when(cates.esExperto()).thenReturn(true);
	}
	
	@Test
	void testEsExperto() {
		assertEquals(false, usba1.esExperto()); //Verify da false porque es Basico
		assertEquals(true, usex2.esExperto()); //Verify da true porque es Experto
		assertEquals(true, uses3.esExperto()); //Verify da true porque es Especialista
	}
	
	@Test
	void testRecategorizar() {
		usex2.recategorizar(sist); // Exercise
		verify(catex, times(1)).recategorizar(sist, usex2); //Verify
	}

}
