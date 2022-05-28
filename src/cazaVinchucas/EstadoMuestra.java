package cazaVinchucas;

import cazaVinchucas.Opinion.Clasificacion;

public abstract class EstadoMuestra {

	void agregarOpinion(Opinion opinion, Muestra muestra) {
		if (this.puedeOpinar(opinion.getUsuario(), muestra)) {
			muestra.agregarOpinion(opinion);
			this.chequearResultado(opinion, muestra);
        }
	}
	
	abstract void chequearResultado(Opinion opinion, Muestra muestra);
	abstract boolean puedeOpinar(Usuario usuario, Muestra muestra);
	abstract Clasificacion getResultadoActual(Muestra muestra);
	
	// boolean hayOpinionDeExperto(Muestra muestra); este va en una concreta
	// chequearResultado(Opinion opinion, Muestra muestra);
	
}
