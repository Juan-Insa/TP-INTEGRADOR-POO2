package cazaVinchucas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Opinion.Clasificacion;
import cazaVinchucas.Categoria.Basico;
import cazaVinchucas.Categoria.Especialista;
import cazaVinchucas.Categoria.Experto;

import static org.mockito.Mockito.*;

class UsuarioTestCase {

	Usuario usba1, usex2, uses3; // SUT (usba = usuario basico, usex = usuario experto, uses = usuario especialista)
	Basico catus;
	Experto catex;
	Especialista cates; // DOC (catus = categoria basico, catex = categoria experto, cates = categoria especialista)
	Sistema sist; // DOC
	Ubicacion dummyUbicacion;
	Clasificacion dummyClasificacion;
	String dummyFoto;
	
	@BeforeEach
	void setUp() throws Exception {
		usba1 = new Usuario(1, false);
		usex2 = new Usuario(2, false);
		uses3 = new Usuario(3, true);
		catus = new Basico();
		catex = new Experto();
		cates = new Especialista();
		usex2.setCategoria(catex);
	}
	
	@Test
	void testGetId() {
		assertEquals(1, usba1.getId());
		assertEquals(2, usex2.getId());
		assertEquals(3, uses3.getId());
	}
	@Test
	void testSetId() {
		usba1.setId(4);
		assertEquals(4, usba1.getId());	
	}
	@Test
	void testEsExperto() {
		assertEquals(false, usba1.esExperto());
		assertEquals(true, usex2.esExperto());
		assertEquals(true, uses3.esExperto());
	}
	@Test
	void testRecategorizarABasico() {
		usex2.enviarMuestra(dummyUbicacion, dummyFoto, dummyClasificacion);
		usex2.recategorizar(sist);
		assertEquals(false, usex2.esExperto());
	}

}
