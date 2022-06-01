package cazaVinchucas;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cazaVinchucas.Opinion.Clasificacion;

public class SinOpinionExperto extends EstadoMuestra{

    /**
     * agrega la opinion a la muestra y le cambia el estado si es opinion
     * de un experto.
     */
	@Override
	void agregarOpinion(Opinion opinion, Muestra muestra) {
		super.agregarOpinion(opinion, muestra);
		this.cambiarDeEstadoSiEsExperto(opinion, muestra);
	}
	
	/**
	 * cambia el estado de la muestra si la opinion es de un experto.
	 * @param opinion, la opinion a determinar si es de experto.
	 * @param muestra, la muestra a cambiar de estado.
	 */
	void cambiarDeEstadoSiEsExperto(Opinion opinion, Muestra muestra) {
		if (opinion.getUsuario().esExperto()) {
		    muestra.setEstado(new ConOpinionExperto());	
		}
	}

	/**
	 * obtiene la clasificación con más iteraciones entre las opiniones de la muestra.
	 * @param muestra, la muestra a obtener el resultado actual.
	 */
	@Override
	Clasificacion getResultadoActual(Muestra muestra) {
		// map donde la clave es la clasificación de una opinion y el valor es su cantidad de iteraciones.
		Map<Clasificacion, Integer> map = new HashMap<Clasificacion, Integer>();
		                                                           
		// lista de las clasificaciones de las opiniones.
		List<Clasificacion> clasificaciones = muestra.getOpiniones().stream()
				                                                    .map(o -> o.getValor())
				                                                    .collect(Collectors.toList()); 
		
		// recorro cada clasificación
		for (Clasificacion c : clasificaciones) {
			Integer votos = map.get(c);                  // obtengo la cant de votos de la clasificación.
			map.put(c, votos == null ? 1 : votos + 1);   // si no tiene votos le asigna 1, sino agrega un voto a la cant.
		}
		
		Map.Entry<Clasificacion, Integer> maxC = null;   // variable para almacenar la opinion mas votada
		
		for (Map.Entry<Clasificacion, Integer> e : map.entrySet()) { // utilizo Map.Entry para iterar sobre los pares
			if (maxC == null || e.getValue() > maxC.getValue()) {    // si la clasificación actual tiene mas votos
				maxC = e;                                            // lo reemplazo
			}	                                  	
		}
		
		return maxC.getKey();   // obtengo la clasificación más votada                                   
	}

}
