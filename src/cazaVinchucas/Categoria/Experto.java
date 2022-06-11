package cazaVinchucas.Categoria;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import cazaVinchucas.Opinion;
import cazaVinchucas.Sistema;
import cazaVinchucas.muestras.Muestra;

/**
 * Clase encargada de representar el comportamiento particular de una 
 * categoria de usuario experto.
 * 
 * @author ivanapr
 *
 */

public class Experto extends Categoria {
	/**
	 * Devuelve un booleano que indica si es usuario experto.
	 * @return
	 */
	public boolean esExperto() {
		return true;
	}
	/**
	 * Actualiza la categoria del usuario
	 * @param sistema es el sistema donde esta el usuario
	 * @param usuario es el usuario actual
	 */
	public void recategorizar(Sistema sistema, Usuario usuario) {
		LocalDate ultimos30Dias = LocalDate.now().minusDays(30);
		List<Muestra> muestrasConsideradas = sistema.getMuestrasDe(usuario).stream().filter(m -> (m.getFecha().isAfter(ultimos30Dias))).collect(Collectors.toList());
		List<Opinion> opinionesConsideradas = sistema.getOpinionesDe(usuario).stream().filter(o -> (o.getFecha().isAfter(ultimos30Dias))).collect(Collectors.toList());
		//Basico: para aquellas personas que recien comienzan a participar. Un participante nuevo posee nivel basico.
		//Experto: son personas que durante los utimos 30 dias desde la fecha actual han realizado mas de 10 envios y mas de 20 revisiones.
		if (!((muestrasConsideradas.size() > 10) && (opinionesConsideradas.size() > 20))) {
			usuario.setCategoria(new Basico());
		}
	}

}
