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
		List<Muestra> buscadas = muestras.stream().map(m -> {
			m.getOpiniones().stream().filter(o -> criterio.esValido(o.getFecha(),fecha));})
				.collect(Collectors.toList());
		return buscadas;
	}

}
