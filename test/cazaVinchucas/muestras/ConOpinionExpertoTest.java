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
	private Opinion op2, op3, op4;
	private Usuario ub1, ue1, ue2, ue3;  // DOC usuarios (ub = usuario basico, ue = usuario experto)
	private ConOpinionExperto opExperto; // SUT
	
	@BeforeEach
	void setUp() throws Exception {
		opExperto = new ConOpinionExperto();
		
		// mocks de usuario
		ue1 = mock(Usuario.class); ue2 = mock(Usuario.class); ue3 = mock(Usuario.class); 
		ub1 = mock(Usuario.class);
		
		// opiniones
		op2 = new Opinion(ue2, Clasificacion.CHINCHEFOLIADA); 
		op3 = new Opinion(ue3, Clasificacion.VINCHUCA); 
		op4 = new Opinion(ub1, Clasificacion.CHINCHEFOLIADA);
		

		//retornos de ids de Usuario
		when(ue1.getId()).thenReturn(04); when(ue2.getId()).thenReturn(05); when(ue3.getId()).thenReturn(06);
		
		// retornos de usuarios para esExperto
        when(ue1.esExperto()).thenReturn(true); when(ue2.esExperto()).thenReturn(true); 
        when(ue3.esExperto()).thenReturn(true); when(ub1.esExperto()).thenReturn(false); 
	
		// la muestra se inicia con la opinion de ue1 y su opinion con valor VINCHUCA.
		m = new Muestra(dummyUbicacion, ue1, "VINCHUCA.jpg", Clasificacion.VINCHUCA);
	}

	@Test //agregarOpinion
	void agregarOpinionLaAgregaPorqueEsDeExpertoYLaMuestraNoEstaVerificada() {
		assertEquals(1, m.getOpiniones().size());
		opExperto.agregarOpinion(op2, m);
		assertEquals(2, m.getOpiniones().size());
	}
	
	@Test //agregarOpinion
	void agregarOpinionNoLaAgregaPorSerDeUsuarioBasico() {
		assertEquals(1, m.getOpiniones().size());
		opExperto.agregarOpinion(op4, m);
		assertEquals(1, m.getOpiniones().size());
	}
	
	@Test //chequearResultado
	void chequearResultadoVerificaLaMuestra() {
		assertEquals(Clasificacion.NINGUNA, m.getResultado());
		opExperto.agregarOpinion(op3, m); // queda verificada como VINCHUCA
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
