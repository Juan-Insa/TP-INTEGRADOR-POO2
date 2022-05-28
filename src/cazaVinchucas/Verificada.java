package cazaVinchucas;

import cazaVinchucas.Opinion.Clasificacion;

public class Verificada extends EstadoMuestra{

	@Override
	// no hace nada porque la muestra ya está verificada.
	void agregarOpinion(Opinion opinion, Muestra muestra) {}

	@Override
	// retorna siempre false porque no se puede opinar en muestra verificadas.
	boolean puedeOpinar(Usuario usuario) {
		return false;
	}

	@Override
	// siempre retorna el resultado de la muestra verificada.
	Clasificacion getResultadoActual(Muestra muestra) {
		return muestra.getResultado();
	}

}
