package cazaVinchucas.Busqueda;

import java.util.List;

import cazaVinchucas.muestras.Muestra;

/**
 * Clase que modela la busqueda de muestras con el filtro AND en el patron Composite.
 * 
 * @author ivanapr
 *
 */
public class ANDComposite implements BusquedaComponent {

	private BusquedaComponent filtro1;
	private BusquedaComponent filtro2;
	
	public ANDComposite(BusquedaComponent filtro1, BusquedaComponent filtro2) {
		this.filtro1 = filtro1;
		this.filtro2 = filtro2;
	}
	
	@Override
	public List<Muestra> filtradas(List<Muestra> muestras){
		List<Muestra> filtrados1 = filtro1.filtradas(muestras);
		List<Muestra> filtrados2 = filtro2.filtradas(filtrados1);
		return filtrados2;
	}
}
