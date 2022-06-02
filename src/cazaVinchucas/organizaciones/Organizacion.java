package cazaVinchucas.organizaciones;

import java.util.List;

import cazaVinchucas.muestras.Muestra;

public class Organizacion implements OrganizacionObserver{
	private String tipo;
	private int cantidadEmpleados;
	private List<ZonaDeCobertura> zonasCubiertas;
	private FuncionalidadExterna funcionalidadNueva;
	private FuncionalidadExterna funcionalidadVerificada;
    
	/**
	 * activa el evento de la funcionalidad externa
	 */
	@Override
	public void activarEvento(ZonaDeCobertura zona, Muestra muestra) {
		
	}
	
	/**
	 * agrega una zona de cobertura a la lista zonasCubiertas.
	 * @param zona, es la zona a agregar a la lista zonasCubiertas.
	 */
	void registrar(ZonaDeCobertura zona) {
		this.zonasCubiertas.add(zona);
	}
	
	/**
	 * elimina la zona de cobertura de la lista de zonasCubiertas.
	 * @param zona, es la zona a eliminar de la lista zonasCubiertas.
	 */
	void desRegistrar(ZonaDeCobertura zona) {
		this.zonasCubiertas.remove(zona);
	}
	
	/**
	 * devuelve la cantidad de empleados de la organización
	 * @return
	 */
	int getCantidadEmpleados() {
		return cantidadEmpleados;
	}
    
	/**
	 * establece la cantidad de empleados de la organización
	 * @param cantidadEmpleados, la cantidad de empleados a establecer.
	 */
	void setCantidadEmpleados(int cantidadEmpleados) {
		this.cantidadEmpleados = cantidadEmpleados;
	}

	/**
	 * establece la funcionalidadExterna para las nuevas muestras.
	 * @param fNueva, la funcionalidad externa a establecer.
	 */
	void setNuevaMuestra(FuncionalidadExterna fNueva) {
		this.funcionalidadNueva = fNueva;
	}

	/**
	 * establece la funcionalidadExterna para las muestras verificadas.
	 * @param fVerificada, la funcionalidad externa a establecer.
	 */
	void setMuestraValidada(FuncionalidadExterna fVerificada) {
		this.funcionalidadVerificada = fVerificada;
	}

}
