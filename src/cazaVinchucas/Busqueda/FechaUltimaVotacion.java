package cazaVinchucas.Busqueda;

import java.time.LocalDate;
import java.util.List;

import cazaVinchucas.muestras.Muestra;

/**
 * Clase que modela la busqueda de muestras con el filtro de fecha de ultima votacion.
 * 
 * @author ivanapr
 *
 */
public class FechaUltimaVotacion implements BusquedaComponent {

	private LocalDate fecha;
	public FechaUltimaVotacion(LocalDate fecha) {
		this.fecha = fecha;
	}
	@Override
	public List<Muestra> filtradas(List<Muestra> muestras) {
		// TODO Auto-generated method stub
		return null;
	}

}
