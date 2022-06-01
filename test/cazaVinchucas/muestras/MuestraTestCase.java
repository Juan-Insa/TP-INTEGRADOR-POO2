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
	private Opinion op1, op2, op3, op4, op5, op6;  // DOC opiniones
	private Usuario ub1, ub2, ub3, ue1, ue2, ue3;  // DOC usuarios (ub = usuario basico, ue = usuario experto)
	private Ubicacion dummyUbicacion;     
	

	@BeforeEach
	void setUp() throws Exception {
		
		// mocks de usuario
		ub1 = mock(Usuario.class); ub2 = mock(Usuario.class); ub3 = mock(Usuario.class);
		ue1 = mock(Usuario.class); ue2 = mock(Usuario.class); ue3 = mock(Usuario.class);
		
		// opiniones
		op2 = new Opinion(ub2, Clasificacion.VINCHUCA); 
		op3 = new Opinion(ub3, Clasificacion.VINCHUCA); 
		op4 = new Opinion(ue1, Clasificacion.PHTIACHINCHE);
		op5 = new Opinion(ue2, Clasificacion.PHTIACHINCHE); 
		op6 = new Opinion(ue3, Clasificacion.POCOCLARA); 
		
		/*
		// retornos de opiniones a getUsuario
		when(op1.getUsuario()).thenReturn(ub1); when(op2.getUsuario()).thenReturn(ub2);
		when(op3.getUsuario()).thenReturn(ub3); when(op4.getUsuario()).thenReturn(ue1);
		when(op5.getUsuario()).thenReturn(ue2);
		
		// retornos de opiniones a getValor
		when(op1.getValor()).thenReturn(Clasificacion.CHINCHEFOLIADA); 
		when(op2.getValor()).thenReturn(Clasificacion.VINCHUCA);
		when(op3.getValor()).thenReturn(Clasificacion.VINCHUCA);
		when(op4.getValor()).thenReturn(Clasificacion.PHTIACHINCHE);
		when(op5.getValor()).thenReturn(Clasificacion.PHTIACHINCHE);
		when(op6.getValor()).thenReturn(Clasificacion.POCOCLARA);
		
		// retornos de opiniones a getValor
		when(op1.getUsuario()).thenReturn(ub1); when(op2.getUsuario()).thenReturn(ub2);
		when(op3.getUsuario()).thenReturn(ub3); when(op4.getUsuario()).thenReturn(ue1);
		when(op5.getUsuario()).thenReturn(ue2); when(op6.getUsuario()).thenReturn(ue3);
		*/
		
		//retornos de ids de Usuario
		when(ub1.getId()).thenReturn(01); when(ub2.getId()).thenReturn(02); 
		when(ub3.getId()).thenReturn(03); when(ue1.getId()).thenReturn(04);
		when(ue2.getId()).thenReturn(05); when(ue3.getId()).thenReturn(06);
		
		// retornos de usuarios para esExperto
		when(ub1.esExperto()).thenReturn(false); when(ub2.esExperto()).thenReturn(false);
		when(ub3.esExperto()).thenReturn(false); when(ue1.esExperto()).thenReturn(true);
		when(ue2.esExperto()).thenReturn(true);  when(ue3.esExperto()).thenReturn(true);
		
		// la muestra se inicia con la opinion de ub1 y su opinion op1.
		m = new Muestra(dummyUbicacion, ub1, "chinche.jpg", Clasificacion.CHINCHEFOLIADA);
	}

	@Test // addOpinion
	void addOpinionAgregaLaOpinionALaMuestra(){
		assertEquals(1, m.getOpiniones().size()); // comienza con 1 por la opinion del creador de la muestra
		m.addOpinion(op1);
		assertEquals(2, m.getOpiniones().size());
	}
	
	@Test //agregarOpinion
	void agregarOpinionDelegaASuEstadoYAgregaLaOpinion() {
		assertEquals(1, m.getOpiniones().size());
		m.agregarOpinion(op2);
		assertEquals(2, m.getOpiniones().size());
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
		m.agregarOpinion(op4); m.agregarOpinion(op5);
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
		m.agregarOpinion(op2); m.agregarOpinion(op6);
		assertEquals(Clasificacion.POCOCLARA, m.getUltimoResultado());
	}
	
	@Test //agregarOpinion
	void getOpiniones() {
		m.agregarOpinion(op2); m.agregarOpinion(op4);
		ArrayList<Opinion> mismasOpiniones = new ArrayList<Opinion>();
		mismasOpiniones.add(m.getOpiniones().get(0));
		mismasOpiniones.add(op2);
		
		assertFalse(mismasOpiniones.containsAll(m.getOpiniones())); //faltaria op4
		
		mismasOpiniones.add(op4); // se agrega op4
		
		assertTrue(mismasOpiniones.containsAll(m.getOpiniones())); // son iguales
		
	}
	
	/*
	@Test //agregarOpinion
	void test() {
		assertEquals(0, m.getOpiniones().size());
	}
	
	
	@Test // sePuedeVerificar
	void esVerificableDaVerdaderoPoqueLaClasificacionTieneUnaCoincidencia(){
		// op4 tiene como valor PHTIACHINCHE
		m.agregarOpinion(op1); m.agregarOpinion(op2); m.agregarOpinion(op4); 
		
		assertEquals(true, m.sePuedeVerificar(Clasificacion.PHTIACHINCHE));
	}
	
	@Test // chequearResultado
	void chequeaElResultadoYDevuelveQueEsPHTIACHINCHE(){
		// op4 y op5 tiene como valor PHTIACHINCHE
		m.agregarOpinion(op1); m.agregarOpinion(op2); m.agregarOpinion(op4); 
		
		assertEquals(Clasificacion.PHTIACHINCHE, m.chequearResultado(op5));
	}
	
	@Test // chequearResultado
	void chequeaElResultadoYDevuelveNullPorqueLaOpinionNoCoincideConOtraDeExperto(){
		// op4 tiene como valor PHTIACHINCHE
		m.agregarOpinion(op1); m.agregarOpinion(op2); 
		
		assertEquals(null, m.chequearResultado(op4));
	}
	
	
	@Test // esVerificada
	void laMuestraNoEsVerificadaAlNoTenerDosOpinionesIgualesDeExpertos() {
		m.agregarOpinion(op1); m.agregarOpinion(op2);
		
		assertEquals(false, m.esVerificada());
	}
	
	@Test // esVerificada
	void laMuestraEsVerificada() {
		// op4 t op5 tiene como valor PHTIACHINCHE
		m.agregarOpinion(op1); m.agregarOpinion(op4);  m.agregarOpinion(op5);
		
		assertEquals(true, m.esVerificada());
	}
	
	@Test // agregarOpinion 
	void noAgregaLaOpinionDeUsuarioBasicoPorqueUnHayOpinionDeExperto() {
		// la lista de opiniones deberí tener solo 2.
		m.agregarOpinion(op1); m.agregarOpinion(op4); 
		
		m.agregarOpinion(op3);
	
		assertEquals(2, m.getOpiniones().size());
	}
	
	@Test // agregarOpinion 
	void noAgregaLaOpinionDeUsuarioExpertoPorqueLaMuestraEstaVerificada() {
		// la lista de opiniones deberí tener solo 3.
		m.agregarOpinion(op1); m.agregarOpinion(op4); m.agregarOpinion(op5);
		
		m.agregarOpinion(op6);
		
		assertEquals(3, m.getOpiniones().size());
	}
	
	@Test // hayOpinionDeExperto 
	void hayExpertoDevuelveTruePorqueHayExperto() {
		m.agregarOpinion(op1); m.agregarOpinion(op4); m.agregarOpinion(op5);		
		assertEquals(true, m.hayOpinionDeExperto());
	}
	
	@Test // hayOpinionDeExperto 
	void hayExpertoDevuelveFalsePorqueNoHayExperto() {
		// op1, op2, op3 son opinines de basicos
		m.agregarOpinion(op1); m.agregarOpinion(op2); m.agregarOpinion(op3);		
		assertEquals(false, m.hayOpinionDeExperto());
	}
	
	@Test // puedeOpinar
	void noPuedeOpinarPorqueEsOpinionDeUserBasicoYHayOpinionDeExperto() {
		// op4 es opinion de experto y ub3 es user basico
		m.agregarOpinion(op1); m.agregarOpinion(op4);	
		
		assertEquals(false, m.puedeOpinar(ub3));
	}
	
	@Test // puedeOpinar
	void puedeOpinarPorqueEsOpinionDeUserBasicoYNoHayOpinionDeExperto() {
		// op1,op2 es opinion de basicos y ub3 es user basico.
		m.agregarOpinion(op1); m.agregarOpinion(op2);	
		
		assertEquals(true, m.puedeOpinar(ub3));
	}
	
	@Test // puedeOpinar
	void puedeOpinarPorqueEsUserExpertoYLaMuestraNoEsVerificada() {
		// op1,op2 es opinion de basicos y ue1 es user experto
		m.agregarOpinion(op1); m.agregarOpinion(op2);	
		
		assertEquals(true, m.puedeOpinar(ue1));
	}
	
	@Test // puedeOpinar
	void noPuedeOpinarPorqueEsUserExpertoYLaMuestraEsVerificada() {
		// op4, op5 son opiniones expertas y ue3 es user experto
		m.agregarOpinion(op1); m.agregarOpinion(op4); m.agregarOpinion(op5);
		
		assertEquals(false, m.puedeOpinar(ue3));
	}
	
	@Test // getResultadoActual
	void elResultadoActualDeUsersBasicosEsVinchucaPorqueEsElMasVotado() {
		// el valor de op1 es CHINCHEFOLIADA y el de op2, op3 VINCHUCA.
		m.agregarOpinion(op1); m.agregarOpinion(op2); m.agregarOpinion(op3);
		
		assertEquals(Clasificacion.VINCHUCA, m.getResultadoActual());
	}
	
	@Test // getResultadoActual
	void elResultadoActualEsPOCOCLARAPorqueEsLaUnicaOpinionDeExperto() {
		// el valor de op2 y op3 VINCHUCA, y el de op6 POCOCLARA
		m.agregarOpinion(op2); m.agregarOpinion(op3); m.agregarOpinion(op6);
		
		assertEquals(Clasificacion.POCOCLARA, m.getResultadoActual());
	}
	
	@Test // getResultadoActual
	void elResultadoEsPHTIACHINCHEPorqueEsLaVerificacionDeLaMuestra() {
		// el valor de op2 es  VINCHUCA, op4 y op5 PHTIACHINCHE, y el de op6 POCOCLARA
		m.agregarOpinion(op2); m.agregarOpinion(op4); m.agregarOpinion(op6); m.agregarOpinion(op5);
		
		assertEquals(Clasificacion.PHTIACHINCHE, m.getResultadoActual());
	}
	*/
	
	
}
