package cazaVinchucas.Busqueda.criterios;

import java.time.LocalDate;

/**
 * Clase strategy para aplicar un criterio logico especifico
 * en base a lo que la busqueda esta queriendo hacer.
 * @author fercho
 *
 */
public interface Criterio {

	/**
	 * Dada dos fechas las compara en base a un criterio especifico
	 * que tienen las clases concreta.
	 * @param fecha, la primer fecha de la comparación
	 * @param fecha2, la segunda fecha de la comparación.
	 * @return un valor de verdad en base al criterio de comparación.
	 */
	public boolean comparar(LocalDate fecha, LocalDate fecha2);

}
