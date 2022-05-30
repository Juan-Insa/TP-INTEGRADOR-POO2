package cazaVinchucas;

import cazaVinchucas.Opinion.Clasificacion;

public abstract class EstadoMuestra {

	/**
	 * agrega la opinion dada a la muestra 
	 * @param opinion, la opinion a agregar a la muesta.
	 * @param muestra, la muestra a agregar la opinión.
	 */
	void agregarOpinion(Opinion opinion, Muestra muestra) {
		if(this.pudedeOpinar(opinion)) {
			muestra.agregarOpinion(opinion);
			this.chequearResultado(opinion, muestra);
		}
    }
	
	abstract boolean pudedeOpinar(Opinion opinion);
    abstract void chequearResultado(Opinion opinion, Muestra muestra);
	abstract Clasificacion getResultadoActual(Muestra muestra);

}
