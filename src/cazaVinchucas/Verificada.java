package cazaVinchucas;

import cazaVinchucas.Opinion.Clasificacion;

public class Verificada extends EstadoMuestra{
	
	@Override
	// siempre retorna el resultado de la muestra verificada.
	Clasificacion getResultadoActual(Muestra muestra) {
		return muestra.getResultado();
	}

	@Override
	void chequearResultado(Opinion opinion, Muestra muestra) {}

	
	@Override
	// siempre va a ser falso porque la muestra ya está verificada
	boolean pudedeOpinar(Opinion opinion) {
		return false;
	}

}
