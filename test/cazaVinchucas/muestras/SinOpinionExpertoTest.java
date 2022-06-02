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

class SinOpinionExpertoTest {
	private Muestra m; 
	private Ubicacion dummyUbicacion; 
	private Opinion op1, op2, op3, op4;
	private Usuario ub1, ub2, ub3, ub4, ue1;  // DOC usuarios (ub = usuario basico, ue = usuario experto)
	private SinOpinionExperto noExperto; // SUT
	
	@BeforeEach
	void setUp() throws Exception {
		noExperto = new SinOpinionExperto();
		
		// mocks de usuario
		ub1 = mock(Usuario.class); ub2 = mock(Usuario.class); ub3 = mock(Usuario.class); ue1 = mock(Usuario.class); 
		ub4 = mock(Usuario.class);
		
		// opiniones
		op4 = new Opinion(ub4, Clasificacion.VINCHUCA);
		op2 = new Opinion(ub2, Clasificacion.CHINCHEFOLIADA); 
		op3 = new Opinion(ub3, Clasificacion.VINCHUCA); 
		op1 = new Opinion(ue1, Clasificacion.CHINCHEFOLIADA);
		
		// retornos de usuarios para esExperto
        when(ue1.esExperto()).thenReturn(true); when(ub2.esExperto()).thenReturn(false); 
        when(ub3.esExperto()).thenReturn(false); when(ub1.esExperto()).thenReturn(false); 
        when(ub4.esExperto()).thenReturn(false);
	
		// la muestra se inicia con la opinion de ue1 y su opinion con valor VINCHUCA.
		m = new Muestra(dummyUbicacion, ub1, "VINCHUCA.jpg", Clasificacion.VINCHUCA);
	}

	@Test //agregarOpinion
	void agregarOpinionLaAgregaALaMuestra() {
		noExperto.agregarOpinion(op2, m);
		assertTrue(m.getOpiniones().contains(op2));
	}

	@Test //cambiarDeEstadoSiEsExperto
	void cambiarDeEstadoSiEsExpertoLoCambia() {
		noExperto.cambiarDeEstadoSiEsExperto(op1, m); // cambia de estado porque op1 es de experto
		m.agregarOpinion(op2);                        // intenta agregar op2 de usuario basico
		assertFalse(m.getOpiniones().contains(op2));  // chequea que no agregó op2
	}
	
	@Test //getResultadoActual
	void getResultadoActualDevuelveVINCHUCAPorSerMasVotada() {
		noExperto.agregarOpinion(op2, m); noExperto.agregarOpinion(op3, m); noExperto.agregarOpinion(op4, m);                         
		assertEquals(Clasificacion.VINCHUCA, noExperto.getResultadoActual(m));  
	}
	
	

}
