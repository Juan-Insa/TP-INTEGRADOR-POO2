package cazaVinchucas.Busqueda.criterios;

import java.time.LocalDate;

/**
 * Clase concreteStrategy de criterio.
 * Implementa comparar y se fijará si la primer fecha es mayor a la segunda.
 * @author fercho
 *
 */
public class Mayor implements Criterio {
	
	/**
	 * Indica verdadero si la primer fecha es posterior a la segunda.
	 */
	public boolean comparar(LocalDate fecha1, LocalDate fecha2){
		return fecha1.isAfter(fecha2);
	}
}
