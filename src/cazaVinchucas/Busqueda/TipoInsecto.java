package cazaVinchucas.Busqueda;

import java.util.List;
import java.util.stream.Collectors;

import cazaVinchucas.Opinion.Clasificacion;
import cazaVinchucas.muestras.Muestra;

/**
 * Clase que modela la busqueda de muestras con el filtro de tipo de insecto.
 * 
 * @author ivanapr
 *
 */
public class TipoInsecto implements BusquedaComponent  {
	private Clasificacion tipo; // Puede ser VINCHUCA, CHINCHEFOLIADA, PHTIACHINCHE, NINGUNA, POCOCLARA
	public TipoInsecto(Clasificacion clasificacion) {
		this.tipo = clasificacion;
	}
	@Override
	public List<Muestra> filtradas(List<Muestra> muestras) {
		List<Muestra> buscadas = muestras.stream().filter(m -> m.getResultado().equals(tipo)).collect(Collectors.toList());
		return buscadas;
	}

}
