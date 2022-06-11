package cazaVinchucas.muestras;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
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

class SinOpinionExpertoTest {
	private Muestra m;                       // DOC muestra
	private List<Opinion> ops;               // DOC opiniones de la muestra
	private Opinion op1, op2, op3, op4, op5; // DOC opiniones
	private Usuario ub1, ub2, ub3, ub4, ue1; // DOC usuarios (ub = usuario basico, ue = usuario experto)
	private SinOpinionExperto noExperto;     // SUT

	
	@BeforeEach
	void setUp() throws Exception {
		noExperto = new SinOpinionExperto();
		
		// mock de muestra
		m = mock(Muestra.class);
		
		// mocks de usuario
		ub1 = mock(Usuario.class); ub2 = mock(Usuario.class); ub3 = mock(Usuario.class); ue1 = mock(Usuario.class); 
		ub4 = mock(Usuario.class);
		
		// mocks de opinion 
		op1 = mock(Opinion.class); op2 = mock(Opinion.class); op3 = mock(Opinion.class); op4 = mock(Opinion.class);
		op5 = mock(Opinion.class);
			
		// retornos de opiniones a getUsuario
	    when(op1.getUsuario()).thenReturn(ue1); when(op2.getUsuario()).thenReturn(ub2); 
	    when(op3.getUsuario()).thenReturn(ub3); when(op4.getUsuario()).thenReturn(ub4); 
	    when(op5.getUsuario()).thenReturn(ub1);
			
	    // retornos de opiniones a getValor 
	    when(op1.getValor()).thenReturn(Clasificacion.CHINCHEFOLIADA);
		when(op2.getValor()).thenReturn(Clasificacion.CHINCHEFOLIADA);
		when(op3.getValor()).thenReturn(Clasificacion.VINCHUCA);
		when(op4.getValor()).thenReturn(Clasificacion.VINCHUCA);
		when(op5.getValor()).thenReturn(Clasificacion.VINCHUCA);
		
		// retornos de usuarios para esExperto
        when(ue1.esExperto()).thenReturn(true); when(ub2.esExperto()).thenReturn(false); 
        when(ub3.esExperto()).thenReturn(false); when(ub1.esExperto()).thenReturn(false); 
        when(ub4.esExperto()).thenReturn(false);
        
        // opiniones de la muestra
        ops = new ArrayList<Opinion>();
        ops.add(op5);
        
        // retornos de muestra
        when(m.getUsuario()).thenReturn(ub1); when(m.getOpiniones()).thenReturn(ops);
	}

	@Test //agregarOpinion
	void agregarOpinionLeEnviaALaMuestraQueAgregueLaOpinion() {
		noExperto.agregarOpinion(op2, m);
		verify(m, times(1)).addOpinion(op2);
	}
	
	@Test //getResultadoActual
	void getResultadoActualDevuelveVINCHUCAPorSerMasVotada() {   
		ops.add(op2); ops.add(op3); ops.add(op4); 
		assertEquals(Clasificacion.VINCHUCA, noExperto.getResultadoActual(m));  
	}
	
	@Test //getResultadoActualSiHayEmpate
	void getResultadoActualDevuelveNINGUNAPorSerEmpate() {
		ops.add(op2);                        
		assertEquals(Clasificacion.NINGUNA, noExperto.getResultadoActual(m));  
	}

}
