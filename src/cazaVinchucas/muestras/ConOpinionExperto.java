package cazaVinchucas.muestras;

import java.util.stream.Stream;

import cazaVinchucas.Opinion;
import cazaVinchucas.Opinion.Clasificacion;

/**
 * Clase encargada de representar el comportamiento particular de una 
 * muestra que ya tiene una opinion de experto, pero, aún no esta 
 * verificada.
 * 
 * @author Juan Cruz y Fernando
 *
 */
public class ConOpinionExperto extends EstadoMuestra {
	
	/**
	 * Agrega la opinion a la muestra y le cambia el estado si corresponde.
	 * Nota: Solo agregará la opinion si corresponde a un experto.
	 */
	@Override
	void agregarOpinion(Opinion opinion, Muestra muestra) {
		if(opinion.getUsuario().esExperto()) {
			super.agregarOpinion(opinion, muestra);
			this.chequearResultado(opinion, muestra);
		}
	}

	/**
	 * Establece el resultado final de la muestra si con la opinión que se agrega
	 * se verifica la muestra (o sea, si hay otra opinion de experto que coincida).
	 * @param opinion, la opinion a chequear si coincide con otra.
	 * @param muestra, la muestra a chequear si se verifica.
     */
	void chequearResultado(Opinion opinion,  Muestra muestra) {
		if (this.sePuedeVerificar(opinion.getValor(), muestra)) {
			// setea el valor de la opinion como resultado de la muestra
			muestra.setResultado(opinion.getValor());  
			
			// cambia el estado de la muestra a verificada
			muestra.setEstado(new Verificada());
	    }
	}
	
    /**
     * Indica si la muestra se puede verificar si se agrega la clasificación.
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
	/*Nota: Inconcluso.*/
	Clasificacion getResultadoActual(Muestra muestra) {
		return muestra.getUltimoResultado();
	}

}
