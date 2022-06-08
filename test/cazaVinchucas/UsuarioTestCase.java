package cazaVinchucas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Opinion.Clasificacion;
import cazaVinchucas.Categoria.Basico;
import cazaVinchucas.Categoria.Especialista;
import cazaVinchucas.Categoria.Experto;
import cazaVinchucas.muestras.Muestra;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

class UsuarioTestCase {

	private Usuario usba1, usex2, uses3; // SUT (usba = usuario basico, usex = usuario experto, uses = usuario especialista)
	private Basico catus; // DOC
	private Experto catex; // DOC
	private Especialista cates; // DOC (catus = categoria basico, catex = categoria experto, cates = categoria especialista)
	private Sistema sist; // DOC
	private Ubicacion dummyUbicacion; // DOC
	private Clasificacion dummyClasificacion; //DOC
	private Opinion dummyOpinion;
	private String dummyFoto; // DOC
	private Muestra m1, m2; // DOC
	
	@BeforeEach
	void setUp() throws Exception {
		sist = new Sistema();
		usba1 = new Usuario(1, false);
		usex2 = new Usuario(2, false);
		uses3 = new Usuario(3, true);
		catus = new Basico();
		catex = new Experto();
		cates = new Especialista();
		usex2.setCategoria(catex);
		m1 = new Muestra(dummyUbicacion, usex2, dummyFoto, dummyClasificacion);
		m2 = new Muestra(dummyUbicacion, usex2, dummyFoto, dummyClasificacion);
		sist.agregarMuestra(m1);
		sist.agregarMuestra(m2);
		sist.agregarOpinion(dummyOpinion);
	}
	
	@Test
	void testGetId() {
		assertEquals(1, usba1.getId()); //Verify
		assertEquals(2, usex2.getId()); //Verify
		assertEquals(3, uses3.getId()); //Verify
	}
	@Test
	void testSetId() {
		usba1.setId(4); // Exercise se setea un nuevo id al usuario
		assertEquals(4, usba1.getId());	//Verify se chequea que el usuario devuelva su correspondiente id
	}
	@Test
	void testEsExperto() {
		assertEquals(false, usba1.esExperto()); //Verify da false porque es Basico
		assertEquals(true, usex2.esExperto()); //Verify da true porque es Experto
		assertEquals(true, uses3.esExperto()); //Verify da true porque es Especialista
	}
	@Test
	void testRecategorizarABasico() {
		assertEquals(true, usex2.esExperto()); //Este usuario fue seteado como Experto
		usex2.recategorizar(sist); // Exercise
		assertEquals(false, usex2.esExperto()); //Verify como el metodo esExperto() da false, este usuario pasa a ser Basico
	}

}
