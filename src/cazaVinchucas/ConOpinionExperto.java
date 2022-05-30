package cazaVinchucas;

import java.util.stream.Stream;

import cazaVinchucas.Opinion.Clasificacion;

public class ConOpinionExperto extends EstadoMuestra {
	
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
    
	@Override
	/**
	 * devuelve la clasificación de la última opinion de la muestra. Por qué? no hay porqué.
	 */
	Clasificacion getResultadoActual(Muestra muestra) {
		return this.ultimaOpinion(muestra).getValor();
	}
    
    /**
     * devuelve la última opinion de la muestra, si esta vacía devuelve null.
     * @param muestra
     * @return
     */
	Opinion ultimaOpinion(Muestra muestra) {
		int ultimoIndex = muestra.getOpiniones().size() - 1;
		if (ultimoIndex >= 0) {
			return muestra.getOpiniones().get(ultimoIndex);
		}
		else {
			return null;
		}
	}

	@Override
	// en este estado, sólo va a poder opinar si el que opina es experto.
	boolean pudedeOpinar(Opinion opinion) {
		return opinion.getUsuario().esExperto();
	}

}
