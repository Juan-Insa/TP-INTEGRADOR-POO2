package cazaVinchucas.Busqueda;

import java.util.ArrayList;
import java.util.List;

import cazaVinchucas.muestras.Muestra;

/**
 * Clase que modela la busqueda de muestras con el filtro OR en el patron Composite.
 * 
 * @author ivanapr
 *
 */
public class ORComposite implements BusquedaComponent {
	private BusquedaComponent filtro1;
	private BusquedaComponent filtro2;
	
	public ORComposite(BusquedaComponent filtro1, BusquedaComponent filtro2) {
		this.filtro1 = filtro1;
		this.filtro2 = filtro2;
	}
	
	@Override
	public List<Muestra> filtradas(List<Muestra> muestras) {
		List<Muestra> filtrados1 = filtro1.filtradas(muestras);
		List<Muestra> filtrados2 = filtro2.filtradas(muestras);
		List<Muestra> filtradosOr = new ArrayList<Muestra>(filtrados1); // Al resultado final se le agregan los elementos que cumplen la primera condicion
		filtrados2.removeAll(filtrados1); // Se quitan los repetidos que pueda haber entre ambas condiciones
		filtradosOr.addAll(filtrados2); // Se agregan los elementos que cumplen la segunda condicion
		return filtradosOr;
	}
}
