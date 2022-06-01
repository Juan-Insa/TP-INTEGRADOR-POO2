package cazaVinchucas.muestras;

import cazaVinchucas.Opinion;
import cazaVinchucas.Opinion.Clasificacion;

public class Verificada extends EstadoMuestra{
	
	@Override
	// Siempre retorna el resultado de la muestra verificada.
	Clasificacion getResultadoActual(Muestra muestra) {
		return muestra.getResultado();
	}

	
	@Override
	// No se agrega nada porque la muestra ya está verificada
	void agregarOpinion(Opinion opinion, Muestra muestra) {
		//Tirar mensaje de no se pueden agregar opiniones.
		//Con catch?
	}

}
