package cazaVinchucas;

import cazaVinchucas.Opinion.Clasificacion;

public abstract class EstadoMuestra {
	abstract void agregarOpinion(Opinion opinion, Muestra muestra);
	abstract boolean puedeOpinar(Usuario usuario);
	abstract Clasificacion getResultadoActual(Muestra muestra);
	
	// boolean hayOpinionDeExperto(Muestra muestra); este va en una concreta
	// chequearResultado(Opinion opinion, Muestra muestra);
	
	
	
	
}
