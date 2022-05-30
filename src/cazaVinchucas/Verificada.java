package cazaVinchucas;

import cazaVinchucas.Opinion.Clasificacion;

public class Verificada extends EstadoMuestra{
	
	@Override
	// no hace nada, ya no se pueden agregar opiniones con este estado.
	void agregarOpinion(Opinion opinion, Muestra muestra) {}

	@Override
	// siempre retorna el resultado de la muestra verificada.
	Clasificacion getResultadoActual(Muestra muestra) {
		return muestra.getResultado();
	}

	@Override
	void chequearResultado(Opinion opinion, Muestra muestra) {}

}
