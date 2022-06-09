package cazaVinchucas.muestras;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Opinion;
import cazaVinchucas.Ubicacion;
import cazaVinchucas.Usuario;
import cazaVinchucas.Opinion.Clasificacion;

class MuestraTestCase {
	private Muestra m;                        // SUT
	private Opinion op2, op3, op4, op5;       // DOC opiniones
	private Usuario ub1, ub2, ue1, ue2, ue3;  // DOC usuarios (ub = usuario basico, ue = usuario experto)
	private Ubicacion dummyUbicacion;     
	

	@BeforeEach
	void setUp() throws Exception {
		
		// mocks de usuario
		ub1 = mock(Usuario.class); ub2 = mock(Usuario.class); ue1 = mock(Usuario.class); 
		ue2 = mock(Usuario.class); ue3 = mock(Usuario.class);
		
		// mocks de opinion 
		op2 = mock(Opinion.class); op3 = mock(Opinion.class); op4 = mock(Opinion.class);
		op5 = mock(Opinion.class);
		
		// retornos de opiniones a getUsuario
        when(op2.getUsuario()).thenReturn(ub2); when(op3.getUsuario()).thenReturn(ue1); 
        when(op4.getUsuario()).thenReturn(ue2); when(op5.getUsuario()).thenReturn(ue3);
		
		// retornos de opiniones a getValor 
		when(op2.getValor()).thenReturn(Clasificacion.VINCHUCA);
		when(op3.getValor()).thenReturn(Clasificacion.PHTIACHINCHE);
		when(op4.getValor()).thenReturn(Clasificacion.PHTIACHINCHE);
		when(op5.getValor()).thenReturn(Clasificacion.POCOCLARA);
		
		// retornos de opiniones a getValor
		when(op2.getUsuario()).thenReturn(ub2); when(op3.getUsuario()).thenReturn(ue1); 
		when(op4.getUsuario()).thenReturn(ue2); when(op5.getUsuario()).thenReturn(ue3); 
		
		//retornos de ids de Usuario
		when(ub1.getId()).thenReturn(01); when(ub2.getId()).thenReturn(02); when(ue1.getId()).thenReturn(03);
		when(ue2.getId()).thenReturn(04); when(ue3.getId()).thenReturn(05);
		
		// retornos de usuarios para esExperto
		when(ub1.esExperto()).thenReturn(false); when(ub2.esExperto()).thenReturn(false);
	    when(ue1.esExperto()).thenReturn(true);  when(ue2.esExperto()).thenReturn(true);
	    when(ue3.esExperto()).thenReturn(true);
		
		// la muestra se inicia con la opinion de ub1 y su opinion op1.
		m = new Muestra(dummyUbicacion, ub1, "chinche.jpg", Clasificacion.CHINCHEFOLIADA);
	}

	@Test // addOpinion
	void addOpinionAgregaLaOpinionALaMuestra(){
		m.addOpinion(op2);
		assertTrue(m.getOpiniones().contains(op2));
	}
	
	@Test //agregarOpinion
	void agregarOpinionDelegaASuEstadoYAgregaLaOpinion() {
		m.agregarOpinion(op2);
		assertTrue(m.getOpiniones().contains(op2));
	}
	
	@Test //agregarOpinion
	void agregarOpinionNoAgregaLaOpinionDeUsuarioQueYaOpino() {
		m.agregarOpinion(op2);
		assertEquals(2, m.getOpiniones().size());
		m.agregarOpinion(op2);
		assertEquals(2, m.getOpiniones().size());
	}
	
	@Test //hayOpinionDe
	void hayOpinionDe() {
		assertTrue(m.hayOpinionDe(01));
		assertFalse(m.hayOpinionDe(02));
	}
	
	@Test //getResultado
	void getResultadoDevuelvePHTIACHINCHE(){
		assertEquals(Clasificacion.NINGUNA, m.getResultado());
		m.agregarOpinion(op3); m.agregarOpinion(op4);
		assertEquals(Clasificacion.PHTIACHINCHE, m.getResultado());
	}
	
	
	@Test //setResultado
	void setResultadoLePasoVINCHUCA() {
		assertEquals(Clasificacion.NINGUNA, m.getResultado());
		m.setResultado(Clasificacion.VINCHUCA);
		assertEquals(Clasificacion.VINCHUCA, m.getResultado());
	}
	
	@Test //getUltimoResultado
	void getUltimoResultadoDevuelvePOCOCLARA() {
		m.agregarOpinion(op2); m.agregarOpinion(op5);
		assertEquals(Clasificacion.POCOCLARA, m.getUltimoResultado());
	}
	
	@Test //agregarOpinion
	void getOpiniones() {
		m.agregarOpinion(op2); m.agregarOpinion(op3);
		ArrayList<Opinion> mismasOpiniones = new ArrayList<Opinion>();
		mismasOpiniones.add(m.getOpiniones().get(0));
		mismasOpiniones.add(op2);
		
		assertFalse(mismasOpiniones.containsAll(m.getOpiniones())); //faltaria op4
		
		mismasOpiniones.add(op3); // se agrega op4
		
		assertTrue(mismasOpiniones.containsAll(m.getOpiniones())); // son iguales
		
	}
	
}
