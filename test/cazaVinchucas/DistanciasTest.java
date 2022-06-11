package cazaVinchucas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DistanciasTest {

	Ubicacion unq, constitucionCapital, madridEspaña, cairoEgipto; //SUT
	
	@BeforeEach
	void setUp() throws Exception {
		
		unq = mock(Ubicacion.class); 
		when(unq.getLatitud()).thenReturn(-34.707023d);
		when(unq.getLongitud()).thenReturn(-58.279492d);
		
		constitucionCapital = mock(Ubicacion.class); 
		when(constitucionCapital.getLatitud()).thenReturn(-34.628066d);
		when(constitucionCapital.getLongitud()).thenReturn(-58.379954d);
		
		madridEspaña = mock(Ubicacion.class); 
		when(madridEspaña.getLatitud()).thenReturn(40.415188d);
		when(madridEspaña.getLongitud()).thenReturn(-3.7070433d);
		
		cairoEgipto = mock(Ubicacion.class); 
		when(cairoEgipto.getLatitud()).thenReturn(30.047279d);
		when(cairoEgipto.getLongitud()).thenReturn(31.224818);
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
	void testErrorsSetRadios() {
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
	
	@Test
	void setRadioPolarTest(){
		//Excersice
		CalculadoraDistancias.setRadioPolarTierra();
		
		//Verify
		//Se testea que los calculos de distancia den con los valores correspondientes a usar
		//el radio polar en vez del ecuatorial.
		assertEquals(CalculadoraDistancias.distanciaUbicaciones(unq, madridEspaña), 10058.87d, .5d);
		assertEquals(CalculadoraDistancias.distanciaUbicaciones(madridEspaña, unq), 10058.87d, .5d);
		assertEquals(CalculadoraDistancias.distanciaUbicaciones(unq, constitucionCapital), 12.64d, .5d);
		assertEquals(CalculadoraDistancias.distanciaUbicaciones(unq, cairoEgipto), 11821.5d, .5d);
		assertEquals(CalculadoraDistancias.distanciaUbicaciones(madridEspaña, cairoEgipto), 3353.4d, .5d);
		
		//Teardown
		CalculadoraDistancias.setRadioEcuatorialTierra();
	}
	
	@Test
	void radiosSuperpuestosTest() {
		//Para este test se trazaran 3 circulos.
		//El primero utilizará de epicentro la UNQ, el segundo capital federal y el tercero madrid.
		//El de la Unq tendra 10000 km de radio
		//El de capital tendrá 5km de radio
		//El de madrid tendrá 48km de radio
		//Como la distancia de la unq a madrid es de aprox 10047Km, el radio de madrid tocará el radio
		//de la unq, pero no el de capital. El de capital tambien tocará a la unqui. Finalmente la unqui
		//los toca a todos.
		assertTrue(CalculadoraDistancias.estanRadiosSuperpuestos(10000d , unq, 48d, madridEspaña));
		assertTrue(CalculadoraDistancias.estanRadiosSuperpuestos(48d, madridEspaña, 10000d , unq));
		assertTrue(CalculadoraDistancias.estanRadiosSuperpuestos(10000d , unq, 5d, constitucionCapital));
		assertFalse(CalculadoraDistancias.estanRadiosSuperpuestos(48d , madridEspaña, 5d, constitucionCapital));
	}
	
	@Test
	void estanDentroDelRadio() {
		//Utilizando los mismos casos que el anterior test.
		//Veremos que el circulo formado con epicentro en Quilmes y de radio de 10000km
		//debe contener dentro a la capital, pero no a madrid.
		assertFalse(CalculadoraDistancias.estanDentroDelRadio(10000d , unq, madridEspaña));
		assertTrue(CalculadoraDistancias.estanDentroDelRadio(10000d , unq, constitucionCapital));
	}
}
