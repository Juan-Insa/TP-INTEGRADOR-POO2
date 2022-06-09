package cazaVinchucas.Busqueda;

import java.util.List;

import cazaVinchucas.muestras.Muestra;

/**
 * Interfaz que modela la busqueda de muestras con distintos filtros con un patron Composite.
 * 
 * @author ivanapr
 *
 */
public interface BusquedaComponent {

	public List<Muestra> filtradas(List<Muestra> muestras);
}
