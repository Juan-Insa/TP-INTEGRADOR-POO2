package cazaVinchucas.muestras;

import java.util.stream.Stream;

import cazaVinchucas.Opinion;
import cazaVinchucas.Opinion.Clasificacion;

/**
 * Clase abstracta que heredan los diferentes estados
 * de una muestra.
 * @author fercho
 *
 */
public abstract class EstadoMuestra {

	/**
	 * Agrega la opinion dada a la muestra 
	 * @param opinion, la opinion a agregar a la muesta.
	 * @param muestra, la muestra a agregar la opinión.
	 */
	void agregarOpinion(Opinion opinion, Muestra muestra) {
		muestra.addOpinion(opinion);
    }
	
	/**
	 * Describe la clasificación mas valida hasta el momento a partir
	 * de los lineamientos que cada estado de muestra tiene.
	 * @param muestra La muestra a obtener el resultado actual.
	 * @return la clasificacion mas valida hasta el momento según
	 * corresponda.
	 */
	abstract Clasificacion getResultadoActual(Muestra muestra);

}
