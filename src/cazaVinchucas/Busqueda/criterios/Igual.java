package cazaVinchucas.Busqueda.criterios;

import java.time.LocalDate;

/**
 * Clase concreteStrategy de criterio.
 * Implementa comparar y se fijará si las fechas son iguales.
 * @author fercho
 *
 */
public class Igual implements Criterio {
	
	/**
	 * Indica verdadero si ambas fechas son iguales.
	 */
	public boolean comparar(LocalDate fecha1, LocalDate fecha2){
		return fecha1.isEqual(fecha2);
	}
}
