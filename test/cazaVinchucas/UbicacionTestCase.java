package cazaVinchucas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UbicacionTestCase {

	double lon, lat; //DOC
	Ubicacion u1, u2, uErrorLat, uErrorLon; //SUT
	
	@BeforeEach
	void setUp() throws Exception {
		
		//Set up
		
		//Longitud 30° 
		lon = 30d;
		//Latitud -20°31'48"
		lat = -20.53d;
		u1 = new Ubicacion(lat,lon);
		
		//Longitud -25°19'48" 
		lon = -25.33d;
		//Latitud 0°00'00"
		lat = 0d;
		u2 = new Ubicacion(lat,lon);
		
		//Longitud invalida en la tierra
		//180°0'01"
		lon = 180.00028d;

		//Latitud invalida en la tierra
		//90°0'01"
		lat = 90.00028d;
	}

	@Test
	void testGetters() {
		//Verify
		assertEquals(30d,u1.getLongitud(),0d);
		assertEquals(-20.53d,u1.getLatitud(),0d);
		
		//Verify
		assertEquals(-25.33d,u2.getLongitud(),0d);
		assertEquals(0d,u2.getLatitud(),0d);
	}
	
	@Test
	void testLatitudInvalida() {
		//Exercise
		Exception e = null;
		try {
			u1 = new Ubicacion(lat,0);
		}catch(Exception error) {
			e = error;
		}
		//Verify
		assertEquals("Latitud invalida.",e.getMessage());
	}
	
	@Test
	void testLongitudInvalida() {
		//Exercise
		Exception e = null;
		try {
			u1 = new Ubicacion(0,lon);
		}catch(Exception error) {
			e = error;
		}
		//Verify
		assertEquals("Longitud invalida.",e.getMessage());
	}
}
