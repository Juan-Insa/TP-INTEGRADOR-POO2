package cazaVinchucas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DistanciasTest {

	Ubicacion unq, constitucionCapital, madridEspaña, cairoEgipto;
	
	@BeforeEach
	void setUp() throws Exception {
		unq = new Ubicacion(-34.707023d, -58.279492d);
		constitucionCapital = new Ubicacion(-34.628066d,-58.379954d);
		madridEspaña = new Ubicacion(40.415188d,-3.7070433d);
		cairoEgipto = new Ubicacion(30.047279d,31.224818);
	}

	@Test
	void testDistancias() {
		//Las distancias tomadas estan basadas en google maps.
		//El error de presicion de 500m puede estar dado por diferencias en los radios usados.
		assertEquals(CalculadoraDistancias.distanciaUbicaciones(unq, madridEspaña), 10047.53d, .5d);
		assertEquals(CalculadoraDistancias.distanciaUbicaciones(madridEspaña, unq), 10047.53d, .5d);
		assertEquals(CalculadoraDistancias.distanciaUbicaciones(unq, constitucionCapital), 12.64d, .5d);
		assertEquals(CalculadoraDistancias.distanciaUbicaciones(unq, cairoEgipto), 11808.35d, .5d);
		assertEquals(CalculadoraDistancias.distanciaUbicaciones(madridEspaña, cairoEgipto), 3349.81d, .5d);
	}

	@Test
	void testSetRadios() {
		//SetUp
		Exception e = null;
		Exception e2 = null;
		Exception e3 = null;
		
		//Excersice
		try {
			CalculadoraDistancias.setRadioTierra(6370d);
		}catch (Exception error){
			e = error;
		}
		try {
			CalculadoraDistancias.setRadioTierra(6379d);
		}catch (Exception error){
			e2 = error;
		}
		try {
			CalculadoraDistancias.setRadioTierra(6375d);
		}catch (Exception error){
			e3 = error;
		}
		
		//Verify
		assertEquals(e.getMessage(), "Radio invalido para la tierra.");
		assertEquals(e2.getMessage(), "Radio invalido para la tierra.");
		assertEquals(e3, null);
		
		//Teardown
		CalculadoraDistancias.setRadioEcuatorialTierra();
	}
}
