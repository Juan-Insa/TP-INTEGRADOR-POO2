package cazaVinchucas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cazaVinchucas.Opinion.Clasificacion;
import static org.mockito.Mockito.*;

class OpinionTest {

	Opinion a,b,c; //SUT
	Usuario dummy, dummy2; //DOC
	
	@BeforeEach
	void setUp() throws Exception {
		//setUp
		dummy  = mock(Usuario.class);
		dummy2 = mock(Usuario.class);
		
		a = new Opinion(dummy, Clasificacion.VINCHUCA);
		b = new Opinion(dummy2, Clasificacion.NINGUNA);
		c = new Opinion(dummy2, Clasificacion.POCOCLARA);
	}

	@Test
	void testOpinionA() {
		//Verify
		assertEquals(Clasificacion.VINCHUCA, a.getValor());
		assertEquals(dummy, a.getUsuario());
	}

	@Test
	void testOpinionB() {
		//Verify
		assertEquals(Clasificacion.NINGUNA, b.getValor());
		assertEquals(dummy2, b.getUsuario());
	}
	
	@Test
	void testOpinionC() {
		//Verify
		assertEquals(Clasificacion.POCOCLARA, c.getValor());
		assertEquals(dummy2, c.getUsuario());
	}
}
