package cazaVinchucas.Busqueda;

import java.time.LocalDate;

enum Criterio {
	MENOR{
		boolean comparar(LocalDate fecha1, LocalDate fecha2){
		return fecha1.isBefore(fecha2);
	}},
	MAYOR{
		boolean comparar(LocalDate fecha1, LocalDate fecha2){
			return fecha1.isAfter(fecha2);
	}}, 
	MENORIGUAL{
		boolean comparar(LocalDate fecha1, LocalDate fecha2){
			return fecha1.isBefore(fecha2) || fecha1.isEqual(fecha2);
	}}, 
	MAYORIGUAL{
		boolean comparar(LocalDate fecha1, LocalDate fecha2){
			return fecha1.isAfter(fecha2) || fecha1.isEqual(fecha2);
	}}, 
	IGUAL{
		boolean comparar(LocalDate fecha1, LocalDate fecha2){
			return fecha1.isEqual(fecha2);
	}};

	boolean comparar(LocalDate fecha, LocalDate fecha2) {
		return this.comparar(fecha, fecha2);
	}
}
