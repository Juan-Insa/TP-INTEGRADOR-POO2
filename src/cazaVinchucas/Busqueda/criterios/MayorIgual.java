package cazaVinchucas.Busqueda.criterios;

import java.time.LocalDate;

public class MayorIgual implements Criterio {
	
	public boolean comparar(LocalDate fecha1, LocalDate fecha2){
		return fecha1.isAfter(fecha2) || fecha1.isEqual(fecha2);
	}
}
