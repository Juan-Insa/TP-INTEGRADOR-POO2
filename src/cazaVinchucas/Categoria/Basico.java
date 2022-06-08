package cazaVinchucas.Categoria;

import cazaVinchucas.Sistema;
import cazaVinchucas.Usuario;

/**
 * Clase encargada de representar el comportamiento particular de una 
 * categoria de usuario basico.
 * 
 * @author ivanapr
 *
 */

public class Basico extends Categoria {
	/**
	 * Devuelve un booleano que indica si es usuario experto.
	 * @return
	 */
	public boolean esExperto() {
		return false;
	}
	/**
	 * Actualiza la categoria del usuario
	 * @param sistema es el sistema donde esta el usuario
	 * @param usuario es el usuario actual
	 */
	public void recategorizar(Sistema sistema, Usuario usuario) {
		//Básico: para aquellas personas que recién comienzan a participar. Un participante nuevo posee nivel básico.
		//Experto: son personas que durante los últimos 30 días desde la fecha actual han realizado más de 10 envíos y más de 20 revisiones.
		if ((sistema.getMuestrasDe(usuario).size() > 10) && (sistema.getOpinionesDe(usuario).size() > 20)) {
			usuario.setCategoria(new Experto());
		}
	}

}
