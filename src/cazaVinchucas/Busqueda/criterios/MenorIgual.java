package cazaVinchucas.Busqueda.criterios;

import java.time.LocalDate;

public class MenorIgual implements Criterio {
	
	public boolean comparar(LocalDate fecha1, LocalDate fecha2){
		return fecha1.isBefore(fecha2) || fecha1.isEqual(fecha2);
	}
}
