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

class VerificadaTest {
	private Muestra m; 
	private Ubicacion dummyUbicacion; 
	private Opinion op2, op3;         // DOC opiniones
	private Usuario ue1, ue2, ue3;    // DOC usuarios (ub = usuario basico, ue = usuario experto)
	private Verificada v;             // SUT
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		v = new Verificada();
		
		// mocks de usuario
		ue1 = mock(Usuario.class); ue2 = mock(Usuario.class); ue3 = mock(Usuario.class);
		
		// opiniones 
		op2 = mock(Opinion.class); op3 = mock(Opinion.class);
		
		// retornos de opiniones a getValor
		when(op2.getValor()).thenReturn(Clasificacion.PHTIACHINCHE);
		when(op3.getValor()).thenReturn(Clasificacion.POCOCLARA);
		
		// retornos de opiniones a getUsuario
		when(op2.getUsuario()).thenReturn(ue2);
		when(op3.getUsuario()).thenReturn(ue3);

		//retornos de ids de Usuario
		when(ue1.getId()).thenReturn(04); when(ue2.getId()).thenReturn(05); when(ue3.getId()).thenReturn(06);
		
		// retornos de usuarios para esExperto
        when(ue1.esExperto()).thenReturn(true); when(ue2.esExperto()).thenReturn(true); when(ue3.esExperto()).thenReturn(true);
	
		// la muestra se inicia con la opinion de ue1 y su opinion con valor PHTIACHINCHE.
		m = new Muestra(dummyUbicacion, ue1, "PHTIACHINCHE.jpg", Clasificacion.PHTIACHINCHE);
	}

	@Test //getResultadoActual
	void getResultadoActualDevuelvePHTIACHINCHE(){
		// ahora queda verificada como PHTIACHINCHE.
		m.agregarOpinion(op2); 
		assertEquals(Clasificacion.PHTIACHINCHE, v.getResultadoActual(m));
	}
	
	@Test //agregarOpinion
	void agregarOpinionNoAgregaLaOpinion(){
		m.setEstado(v);
		
		v.agregarOpinion(op3, m);
		
		assertEquals(1, m.getOpiniones().size());
	}

}
