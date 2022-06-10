package cazaVinchucas.Busqueda;

import java.util.List;
import java.util.stream.Collectors;

import cazaVinchucas.muestras.Muestra;

/**
 * Clase que modela la busqueda de muestras con el filtro de nivel de validacion.
 * 
 * @author ivanapr
 *
 */
public class NivelValidacion implements BusquedaComponent {

	private boolean validacion;
	
	/**
	 * @param validacion es un booleano que determina en el constructor si la busqueda va a ser de muestras verificadas o no
	 */
	public NivelValidacion(boolean validacion) {
		this.validacion = validacion;
	}
	@Override
	public List<Muestra> filtradas(List<Muestra> muestras) {
		return muestras.stream().filter(m -> (m.esVerificada() == validacion)).collect(Collectors.toList());
	}

}
