package cazaVinchucas.ZonaDeCobertura;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Ubicacion;
import cazaVinchucas.muestras.Muestra;
import cazaVinchucas.organizaciones.Organizacion;

class ZonaDeCoberturaTest {

	ZonaDeCobertura unqYAlrededores, gba, españa; //SUT
	Ubicacion unq, capital, madrid, estacionBernal, utnBa; //DOC
	ArrayList<Muestra> muestrasCapital; //DOC
	ArrayList<ZonaDeCobertura> conUnq; //DOC
	Muestra m1,m2,m3; //DOC
	Organizacion o1, o2;
	
	@BeforeEach
	void setUp() throws Exception {
		//SetUp
		unq = mock(Ubicacion.class); 
		when(unq.getLatitud()).thenReturn(-34.707023d);
		when(unq.getLongitud()).thenReturn(-58.279492d);
		
		utnBa = mock(Ubicacion.class); 
		when(utnBa.getLatitud()).thenReturn(-34.5986183d);
		when(utnBa.getLongitud()).thenReturn(-58.4206545d);
		
		capital = mock(Ubicacion.class); 
		when(capital.getLatitud()).thenReturn(-34.628066d);
		when(capital.getLongitud()).thenReturn(-58.379954d);
		
		madrid = mock(Ubicacion.class); 
		when(madrid.getLatitud()).thenReturn(40.415188d);
		when(madrid.getLongitud()).thenReturn(-3.7070433d);
		
		m1 = mock(Muestra.class);
		when(m1.getUbicacion()).thenReturn(utnBa);
		when(m1.esVerificada()).thenReturn(false);
		
		m2 = mock(Muestra.class);
		when(m2.getUbicacion()).thenReturn(capital);
		when(m2.esVerificada()).thenReturn(true);
		
		m3 = mock(Muestra.class);
		when(m3.getUbicacion()).thenReturn(madrid);
		when(m3.esVerificada()).thenReturn(false);
		
		o1 = mock(Organizacion.class);
		
		o2 = mock(Organizacion.class);
		
		//Crea zonas de cobertura sin muestras previas o zonas solapadas.
		unqYAlrededores = new ZonaDeCobertura(unq, "Unqui", 0.3d);
		españa = new ZonaDeCobertura(madrid, "España", 470d);
		
		muestrasCapital = new ArrayList<Muestra>();
		muestrasCapital.add(m1);
		muestrasCapital.add(m2);
		
		conUnq = new ArrayList<ZonaDeCobertura>();
		conUnq.add(unqYAlrededores);
		
		//Crea el gba con la zona de unq ya dentro de él y algunas muestras.
		gba = new ZonaDeCobertura(capital, "Gran Buenos Aires", 50d, conUnq, muestrasCapital);
	}

	@Test
	void testGetNombre() {
		//Verify
		assertEquals("Unqui", unqYAlrededores.getNombre());
		assertEquals("España", españa.getNombre());
		assertEquals("Gran Buenos Aires", gba.getNombre());
	}

	@Test
	void testGetEpicentro() {
		//Verify
		assertEquals(unq, unqYAlrededores.getEpicentro());
		assertEquals(madrid, españa.getEpicentro());
		assertEquals(capital, gba.getEpicentro());
	}
	
	@Test
	void testGetRadio() {
		//Verify
		assertEquals(50d, gba.getRadio());
		assertEquals(0.3d, unqYAlrededores.getRadio());
		assertEquals(470d, españa.getRadio());
	}
	
	@Test
	void testSolapadas() {
		//Como gba ya es inicializado con unq dentro
		//Ya debería estar solapado. Pero unq aún no fue
		//notificado de gba.
		//Verify
		assertTrue(gba.getZonasSolapadas().containsAll(conUnq));
		assertFalse(unqYAlrededores.getZonasSolapadas().contains(gba));
		
		//Notifico a todas las zonas de las demas.
		//Excercise
		unqYAlrededores.updateZonaNueva(gba);
		unqYAlrededores.updateZonaNueva(españa);
		españa.updateZonaNueva(gba);
		españa.updateZonaNueva(unqYAlrededores);
		gba.updateZonaNueva(unqYAlrededores);
		
		//Ahora tanto gba como unq se deben contener y ninguna debe
		//tener a españa.
		//Verify
		
		//Por tanto las zonas solapadas de españa son ninguna.
		assertTrue(españa.getZonasSolapadas().isEmpty());
		
		//Unq solo tiene a gba y no a españa.
		assertTrue(unqYAlrededores.getZonasSolapadas().contains(gba));
		assertFalse(unqYAlrededores.getZonasSolapadas().contains(españa));
		
		//Gba solo tiene a unq y no a españa.
		assertTrue(gba.getZonasSolapadas().contains(unqYAlrededores));
		assertFalse(gba.getZonasSolapadas().contains(españa));
	}
	
	void testNotificandoOrganizaciones() {
		//Excersice
		//Se registran dos organizaciones, pero luego una se la quita. De modo
		//que solo la primera debe ser notificada.
		gba.registrar(o1);
		gba.registrar(o2);
		gba.desRegistrar(o2);
		
		//Fingimos que m2 se verifica
		//Por lo tanto al estar dentro del territorio de gba lo notifica
		gba.updateMuestra(m2);
		//Fingimos que agregamos m3.
		//Como esta fuera del territorio, no debería pasar nada.
		gba.updateMuestra(m3);
		
		//Verify
		verify(o1, times(1)).activarEvento(gba, m2);
		verify(o1, never()).activarEvento(gba, m3);
		verify(o2, never()).activarEvento(gba, m2);
		verify(o2, never()).activarEvento(gba, m3);
	}
}
