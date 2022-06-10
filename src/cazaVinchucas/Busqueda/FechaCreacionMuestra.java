package cazaVinchucas.Busqueda;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import cazaVinchucas.Busqueda.criterios.Criterio;
import cazaVinchucas.muestras.Muestra;

/**
 * Clase que modela la busqueda de muestras con el filtro de creacion de muestra.
 * 
 * @author ivanapr
 *
 */
public class FechaCreacionMuestra implements BusquedaComponent {

	private LocalDate fecha;
	private Criterio criterio;
	public FechaCreacionMuestra(LocalDate fecha, Criterio criterio) {
		this.fecha = fecha;
		this.criterio = criterio;
	}
	@Override
	public List<Muestra> filtradas(List<Muestra> muestras) {
		return muestras.stream().filter(m -> criterio.comparar(m.getFecha(),fecha))
				.collect(Collectors.toList());
	}

}
