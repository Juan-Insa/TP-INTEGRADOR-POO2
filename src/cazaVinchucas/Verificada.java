package cazaVinchucas;

import cazaVinchucas.Opinion.Clasificacion;

public class Verificada extends EstadoMuestra{
	
	@Override
	// siempre retorna el resultado de la muestra verificada.
	Clasificacion getResultadoActual(Muestra muestra) {
		return muestra.getResultado(); //Aca si amerita un colaborador que se setea al cambiar y quede fijo para no recalcular. Así tal cual esta ahora.
	}

	
	@Override
	// siempre va a ser falso porque la muestra ya está verificada
	void agregarOpinion(Opinion opinion, Muestra muestra) {
		//Tirar mensaje de no se pueden agregar opiniones.
		//Con catch?
	}

}
