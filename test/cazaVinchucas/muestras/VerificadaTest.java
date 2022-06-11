package cazaVinchucas.muestras;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Clasificacion;
import cazaVinchucas.Opinion;
import cazaVinchucas.Categoria.Usuario;

class VerificadaTest {
	private Muestra m;                // DOC muestra
	private Opinion op2, op3;         // DOC opiniones
	private Usuario ue1, ue2, ue3;    // DOC usuarios (ub = usuario basico, ue = usuario experto)
	private Verificada v;             // SUT
	
	
	
	@BeforeEach
	void setUp() throws Exception {
		v = new Verificada();
		
		// mock de Muestra
		m = mock(Muestra.class);
		
		// mocks de usuario
		ue1 = mock(Usuario.class); ue2 = mock(Usuario.class); ue3 = mock(Usuario.class);
		
		// opiniones 
		op2 = mock(Opinion.class); op3 = mock(Opinion.class);
		
		// retornos de muestra
		when(m.getResultado()).thenReturn(Clasificacion.PHTIACHINCHE);
		
		// retornos de opiniones a getValor
		when(op2.getValor()).thenReturn(Clasificacion.PHTIACHINCHE);
		when(op3.getValor()).thenReturn(Clasificacion.POCOCLARA);
		
		// retornos de opiniones a getUsuario
		when(op2.getUsuario()).thenReturn(ue2);
		when(op3.getUsuario()).thenReturn(ue3);
		
		// retornos de usuarios para esExperto
        when(ue1.esExperto()).thenReturn(true); when(ue2.esExperto()).thenReturn(true); when(ue3.esExperto()).thenReturn(true);
	
	}

	@Test //getResultadoActual
	void getResultadoActualLePreguntaALaMuestraSuResultado(){
		v.getResultadoActual(m);
		verify(m, times(1)).getResultado();
	}
	
	@Test //agregarOpinion
	void agregarOpinionNoAgregaLaOpinion() throws Exception{	
		v.agregarOpinion(op3, m);
		verify(m, never()).addOpinion(op3);
	}

}
