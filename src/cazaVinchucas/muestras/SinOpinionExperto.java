package cazaVinchucas.muestras;

import java.util.EnumMap;
import java.util.Map;

import cazaVinchucas.Clasificacion;
import cazaVinchucas.Opinion;

/**
 * Clase encargada de representar el comportamiento particular de una 
 * muestra que aún no tiene una opinion de experto.
 * 
 * @author Juan Cruz y Fernando
 *
 */
public class SinOpinionExperto extends EstadoMuestra{

    /**
     * Agrega la opinion a la muestra y le cambia el estado si es opinion
     * de un experto.
     */
	@Override
	void agregarOpinion(Opinion opinion, Muestra muestra) {
		super.agregarOpinion(opinion, muestra);
		this.cambiarDeEstadoSiEsExperto(opinion, muestra);
	}
	
	/**
	 * Cambia el estado de la muestra si la opinion es de un experto.
	 * @param opinion, la opinion a determinar si es de experto.
	 * @param muestra, la muestra a cambiar de estado.
	 */
	private void cambiarDeEstadoSiEsExperto(Opinion opinion, Muestra muestra) {
		if (opinion.getUsuario().esExperto()) {
		    muestra.setEstado(new ConOpinionExperto());	
		}
	}

	/**
	 * Obtiene la clasificación con más iteraciones entre las opiniones de la muestra.
	 * @param muestra, la muestra a obtener el resultado actual.
	 * @return Una Clasificacion con el resultado actual según corresponde.
	 */
	@Override
	Clasificacion getResultadoActual(Muestra muestra) {
		// map donde la clave es la clasificación de una opinion y el valor es su cantidad de iteraciones.
		Map<Clasificacion, Integer> map = new EnumMap<Clasificacion, Integer>(Clasificacion.class);
		
		//Le cargo para cada clasificación la cantidad de veces que esta en las opiniones
		muestra.getOpiniones().forEach(o -> {
			//Obtengo la clasificación a contar
			Clasificacion key = o.getValor();
			System.out.println(o.getValor().toString());
			//Lo inicializa si no esta en el map
			Integer oldValue = map.putIfAbsent(key, 1);
			//Si ya estaba en el map le suma uno
			if(oldValue != null) {
				map.put(key, oldValue+1);
			}
		});
		
		Map.Entry<Clasificacion, Integer> maxC = null;   // variable para almacenar la opinion mas votada
		Map.Entry<Clasificacion, Integer> subMaxC = null;   // variable para almacenar la segunda opinion mas votada
		
		for (Map.Entry<Clasificacion, Integer> e : map.entrySet()) { // utilizo Map.Entry para iterar sobre los pares
			
			//Inicializa el segundo maximo, si el primero ya esta inicializado.
			//Esto solo sucede en el segundo elemento de la iteracion
			if (subMaxC == null && maxC != null) {
				subMaxC = e;
			}
			
			// si la clasificación actual tiene mas votos
			if (maxC == null || e.getValue() > maxC.getValue()) {
				//El que era primero ahora es segundo
				subMaxC = maxC;
				//Actualiza el primero
				maxC = e;
			}	                                  	
		}
		
		//Chequea que haya mas una clasificacion
		if(subMaxC == null) {
			return maxC.getKey();
		}
		
		//Si el primero y el segundo tienen la misma cantidad, devuelve ninguna, si no devuelve al primero
		return maxC.getValue() == subMaxC.getValue() ? Clasificacion.NINGUNA : maxC.getKey();                                   
	}

}
