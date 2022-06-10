package cazaVinchucas.Busqueda.criterios;

import java.time.LocalDate;

public interface Criterio {

	public boolean comparar(LocalDate fecha, LocalDate fecha2);

}
