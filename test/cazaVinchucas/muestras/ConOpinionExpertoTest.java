package cazaVinchucas.muestras;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Opinion;
import cazaVinchucas.Ubicacion;
import cazaVinchucas.Usuario;
import cazaVinchucas.Opinion.Clasificacion;

class ConOpinionExpertoTest {
	private Muestra m; 
	private Ubicacion dummyUbicacion; 
	private Opinion op2, op3, op4;       // DOC opiniones
	private Usuario ub1, ue1, ue2, ue3;  // DOC usuarios (ub = usuario basico, ue = usuario experto)
	private ConOpinionExperto opExperto; // SUT
	
	@BeforeEach
	void setUp() throws Exception {
		opExperto = new ConOpinionExperto();
		
		// mocks de usuario
		ue1 = mock(Usuario.class); ue2 = mock(Usuario.class); ue3 = mock(Usuario.class); 
		ub1 = mock(Usuario.class);
		
		// mocks de opinion
		op2 = mock(Opinion.class); op3 = mock(Opinion.class); op4 = mock(Opinion.class);
		
		// retornos de opiniones a getUsuario
	    when(op2.getUsuario()).thenReturn(ue2); when(op3.getUsuario()).thenReturn(ue3);
	    when(op4.getUsuario()).thenReturn(ub1); 
			
	    // retornos de opiniones a getValor 
		when(op2.getValor()).thenReturn(Clasificacion.CHINCHEFOLIADA);
		when(op3.getValor()).thenReturn(Clasificacion.VINCHUCA);
		when(op4.getValor()).thenReturn(Clasificacion.CHINCHEFOLIADA);
		
		// retornos de usuarios para esExperto
        when(ue1.esExperto()).thenReturn(true); when(ue2.esExperto()).thenReturn(true); 
        when(ue3.esExperto()).thenReturn(true); when(ub1.esExperto()).thenReturn(false); 
	
		// la muestra se inicia con la opinion de ue1 y su opinion con valor VINCHUCA.
		m = new Muestra(dummyUbicacion, ue1, "VINCHUCA.jpg", Clasificacion.VINCHUCA);
	}

	@Test //agregarOpinion
	void agregarOpinionLaAgregaPorqueEsDeExperto() {
		opExperto.agregarOpinion(op2, m);
		assertTrue(m.getOpiniones().contains(op2));
	}
	
	@Test //agregarOpinion
	void agregarOpinionNoLaAgregaPorSerDeUsuarioBasico() {
		opExperto.agregarOpinion(op4, m);
		assertFalse(m.getOpiniones().contains(op4));
	}
	
	@Test //chequearResultado
	void chequearResultadoVerificaLaMuestra() {
		assertEquals(Clasificacion.NINGUNA, m.getResultado());
		opExperto.chequearResultado(op3, m); // queda verificada como VINCHUCA
		assertEquals(Clasificacion.VINCHUCA, m.getResultado());
	}
	
	@Test //sePuedeVerificar
	void sePuedeVerificarEsVerdaderoParaVINCHUCA(){
		assertTrue(opExperto.sePuedeVerificar(Clasificacion.VINCHUCA, m));
	}
	
	@Test //sePuedeVerificar
	void sePuedeVerificarEsFalsoParaPOCOCLARA(){
		assertFalse(opExperto.sePuedeVerificar(Clasificacion.POCOCLARA, m));
	}
	
	@Test //getResultadoActual
	void getResultadoActualDevuelveLaUltimaOpinionExperta(){
		assertEquals(Clasificacion.VINCHUCA, opExperto.getResultadoActual(m));
	}
	
	@Test //getResultadoActual
	void getResultadoActualDevuelveNINGUNAPorEmpate(){
		opExperto.agregarOpinion(op2, m);
		assertEquals(Clasificacion.NINGUNA, opExperto.getResultadoActual(m));
	}
	

}
