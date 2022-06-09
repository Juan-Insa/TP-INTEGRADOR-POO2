package cazaVinchucas.Busqueda;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import cazaVinchucas.muestras.Muestra;

/**
 * Clase que modela la busqueda de muestras con el filtro de fecha de ultima votacion.
 * 
 * @author ivanapr
 *
 */
public class FechaUltimaVotacion implements BusquedaComponent {

	private LocalDate fecha;
	private Criterio criterio;
	public FechaUltimaVotacion(LocalDate fecha, Criterio criterio) {
		this.fecha = fecha;
		this.criterio = criterio;
	}
	@Override
	public List<Muestra> filtradas(List<Muestra> muestras) {
		return muestras.stream().filter(m -> criterio.comparar(m.getUltimaOpinion().getFecha(),fecha))
				.collect(Collectors.toList());
	}

}
