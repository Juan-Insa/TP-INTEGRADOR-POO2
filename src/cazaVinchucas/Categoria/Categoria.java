package cazaVinchucas.Categoria;

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
	 */
	public abstract void recategorizar();
}
