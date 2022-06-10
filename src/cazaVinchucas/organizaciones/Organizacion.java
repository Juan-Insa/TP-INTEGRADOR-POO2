package cazaVinchucas.organizaciones;

import java.util.ArrayList;
import java.util.List;

import cazaVinchucas.muestras.Muestra;

public class Organizacion implements OrganizacionObserver{
	private String tipo;
	private int cantidadEmpleados;
	private List<ZonaDeCobertura> zonasCubiertas;
	private FuncionalidadExterna eventoNuevo;
	private FuncionalidadExterna eventoVerificado;
	
	/**
	 * constructor de Organización
	 * @param tipo, es el tipo de organización
	 * @param cantidadEmpleados, la cant de empleados que trabaja en la organización.
	 * @param eventoNuevo, la funcionalidad externa cuando hay nueva muestra.
	 * @param eventoVerificado, la funcionalidad externa cuando hay muestra verificada.
	 */
	public Organizacion(String tipo, int cantidadEmpleados, FuncionalidadExterna eventoNuevo, 
			FuncionalidadExterna eventoVerificado) {
		this.tipo = tipo;
		this.cantidadEmpleados = cantidadEmpleados;
		this.zonasCubiertas = new ArrayList<ZonaDeCobertura>();
		this.eventoNuevo = eventoNuevo;
		this.eventoVerificado = eventoVerificado;
	}

	/**
	 * Describe un getter del tipo
	 * @return un String equivalente al tipo de la organizacion.
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * activa el evento de la funcionalidad externa.
	 * @param zona, es la zona que notificó la muestra.
	 * @param muestra,  
	 */
	@Override
	public void activarEvento(ZonaDeCobertura zona, Muestra muestra) {
		if (muestra.esVerificada()) {
			this.eventoVerificado.nuevoEvento(this, zona, muestra);
		} else {
			this.eventoNuevo.nuevoEvento(this, zona, muestra);
		}
	}
	
	/**
	 * agrega una zona de cobertura a la lista zonasCubiertas.
	 * @param zona, es la zona a agregar a la lista zonasCubiertas.
	 */
	void registrar(ZonaDeCobertura zona) {
		this.zonasCubiertas.add(zona);
		zona.registrar(this);
	}
	
	/**
	 * elimina la zona de cobertura de la lista de zonasCubiertas.
	 * @param zona, es la zona a eliminar de la lista zonasCubiertas.
	 */
	void desRegistrar(ZonaDeCobertura zona) {
		this.zonasCubiertas.remove(zona);
		zona.desRegistrar(this);
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
	void setEventoNuevaMuestra(FuncionalidadExterna fNueva) {
		this.eventoNuevo = fNueva;
	}

	/**
	 * establece la funcionalidadExterna para las muestras verificadas.
	 * @param fVerificada, la funcionalidad externa a establecer.
	 */
	void setEventoMuestraValidada(FuncionalidadExterna fVerificada) {
		this.eventoVerificado = fVerificada;
	}

	/**
	 * devuelve las zonas cubiertas por la organización.
	 * @return
	 */
	List<ZonaDeCobertura> getZonasCubiertas() {
		return zonasCubiertas;
	}
	
	

}
