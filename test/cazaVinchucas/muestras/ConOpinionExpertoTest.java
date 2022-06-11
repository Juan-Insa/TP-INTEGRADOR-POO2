package cazaVinchucas.muestras;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Clasificacion;
import cazaVinchucas.Opinion;
import cazaVinchucas.Categoria.Usuario;

class ConOpinionExpertoTest {
	private Muestra m;                   // DOC muestra
	private List<Opinion> ops;               // DOC opiniones de la muestra
	private Opinion op1, op2, op3, op4;  // DOC opiniones
	private Usuario ub1, ue1, ue2, ue3;  // DOC usuarios (ub = usuario basico, ue = usuario experto)
	private ConOpinionExperto opExperto; // SUT
	
	@BeforeEach
	void setUp() throws Exception {
		opExperto = new ConOpinionExperto();
		
		// mock de muestra
		m = mock(Muestra.class);
		
		// mocks de usuario
		ue1 = mock(Usuario.class); ue2 = mock(Usuario.class); ue3 = mock(Usuario.class); 
		ub1 = mock(Usuario.class);
		
		// mocks de opinion
		op1 = mock(Opinion.class); op2 = mock(Opinion.class); op3 = mock(Opinion.class);
		op4 = mock(Opinion.class);
		
		// retornos de opiniones a getUsuario
		when(op1.getUsuario()).thenReturn(ue1); when(op2.getUsuario()).thenReturn(ue2);
		when(op3.getUsuario()).thenReturn(ue3); when(op4.getUsuario()).thenReturn(ub1); 
			
	    // retornos de opiniones a getValor 
		when(op1.getValor()).thenReturn(Clasificacion.VINCHUCA);
		when(op2.getValor()).thenReturn(Clasificacion.CHINCHEFOLIADA);
		when(op3.getValor()).thenReturn(Clasificacion.VINCHUCA);
		when(op4.getValor()).thenReturn(Clasificacion.CHINCHEFOLIADA);
		
		// retornos de usuarios para esExperto
        when(ue1.esExperto()).thenReturn(true); when(ue2.esExperto()).thenReturn(true); 
        when(ue3.esExperto()).thenReturn(true); when(ub1.esExperto()).thenReturn(false); 
	
        // opiniones de la muestra
        ops = new ArrayList<Opinion>();
        ops.add(op1);
        
        Clasificacion ultimoRes = ops.get(ops.size() - 1).getValor();
        Opinion ultimaOp = ops.get(ops.size() - 1);
        
        // retornos de muestra
        when(m.getUsuario()).thenReturn(ue1); when(m.getOpiniones()).thenReturn(ops);
        when(m.getUltimaOpinion()).thenReturn(ultimaOp);
        when(m.getUltimoResultado()).thenReturn(ultimoRes);
        
  
	}

	@Test //agregarOpinion
	void agregarOpinionLaAgregaPorqueEsDeExperto() {
		opExperto.agregarOpinion(op2, m);
		verify(m, times(1)).addOpinion(op2);
	}
	
	@Test //agregarOpinion
	void agregarOpinionNoLaAgregaPorSerDeUsuarioBasico() {
		opExperto.agregarOpinion(op4, m);
		verify(m, never()).addOpinion(op4);
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
