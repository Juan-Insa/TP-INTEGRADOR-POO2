package cazaVinchucas;

import java.util.stream.Stream;

import cazaVinchucas.Opinion.Clasificacion;

public class ConOpinionExperto extends EstadoMuestra {

	@Override
	// es verdadera solamente si la muestra dada no es verificada.
	boolean puedeOpinar(Usuario usuario, Muestra muestra) {	
		return !muestra.esVerificada();
	}

	@Override
	// devuelve la clasificación de la última opinion de la muestra. Por qué? no hay porqué.
	Clasificacion getResultadoActual(Muestra muestra) {
		return muestra.ultimaOpinion().getValor();
	}
    
	/**
	 * establece el resultado final de la muestra si con la opinión que se agrega
	 * se verifica la muestra (o sea, si hay otra opinion de experto que coincida).
	 * @param opinion, la opinion a chequear si coincide con otra.
	 * @param muestra, la muestra a chequear si se verifica.
     */
	@Override
	void chequearResultado(Opinion opinion,  Muestra muestra) {
		if (opinion.getUsuario().esExperto() && this.sePuedeVerificar(opinion.getValor(), muestra)) {
			// setea el valor de la opinion como resultado de la muestra
			muestra.setResultado(opinion.getValor());  
			
			// cambia el estado de la muestra a verificada
			muestra.setEstado(new Verificada());
	    }
	}
	
    /**
     * indica si la muestra se puede verificar si se agrega la clasificación.
     * @param c, es la nueva  clasificación a comparar con las actuales.
     * @param muestra, es la muestra a determinar si se verifica.
     */
    private boolean sePuedeVerificar(Clasificacion c, Muestra muestra) {
		Stream<Clasificacion> clasifDeExpertos = muestra.getOpiniones().stream()
				                                                       .filter(o -> o.getUsuario().esExperto())
				                                                       .map(o -> o.getValor());
		return clasifDeExpertos.anyMatch(v -> v.equals(c));
	}
    
    

}
