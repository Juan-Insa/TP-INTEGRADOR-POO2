package cazaVinchucas;

import cazaVinchucas.Opinion.Clasificacion;

public class Verificada extends EstadoMuestra{

	@Override
	// retorna siempre false porque no se puede opinar en muestra verificadas.
	boolean puedeOpinar(Usuario usuario, Muestra muestra) {
		return false;
	}

	@Override
	// siempre retorna el resultado de la muestra verificada.
	Clasificacion getResultadoActual(Muestra muestra) {
		return muestra.getResultado();
	}

	@Override
	void chequearResultado(Opinion opinion, Muestra muestra) {}

}
