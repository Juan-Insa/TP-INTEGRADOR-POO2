package cazaVinchucas.Categoria;

import cazaVinchucas.Sistema;
import cazaVinchucas.Usuario;

/**
 * Clase que modela la categoria del usuario del sistema.
 * 
 * @author ivanapr
 *
 */

public abstract class Categoria {
	/**
	 * Devuelve un booleano que indica si es usuario experto.
	 * @return
	 */
	public abstract boolean esExperto();
	/**
	 * Actualiza la categoria del usuario
	 * @param sistema es el sistema donde esta el usuario
	 * @param usuario es el usuario actual
	 */
	public abstract void recategorizar(Sistema sistema, Usuario usuario);
}
