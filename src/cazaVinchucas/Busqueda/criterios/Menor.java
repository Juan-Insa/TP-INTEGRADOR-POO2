package cazaVinchucas.Busqueda.criterios;

import java.time.LocalDate;

/**
 * Clase concreteStrategy de criterio.
 * Implementa comparar y se fijará si la primer fecha es menor a la segunda.
 * @author fercho
 *
 */
public class Menor implements Criterio {
	
	/**
	 * Indica verdadero si la primer fecha es anterior a la segunda.
	 */
	public boolean comparar(LocalDate fecha1, LocalDate fecha2){
		return fecha1.isBefore(fecha2);
	}
}
