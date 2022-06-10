package cazaVinchucas.Categoria;

import cazaVinchucas.Sistema;

/**
 * Clase encargada de representar el comportamiento particular de una 
 * categoria de usuario especialista.
 * 
 * @author ivanapr
 *
 */

public class Especialista extends Categoria {
	/**
	 * Devuelve un booleano que indica si es usuario experto.
	 * @return
	 */
	public boolean esExperto() {
		// El usuario especialista siempre es experto.
		return true;
	}
	/**
	 * Actualiza la categoria del usuario
	 * @param sistema es el sistema donde esta el usuario
	 * @param usuario es el usuario actual
	 */
	public void recategorizar(Sistema sistema, Usuario usuario) {
		// No cambia su categoria porque el usuario especialista es siempre especialista
	}

}
