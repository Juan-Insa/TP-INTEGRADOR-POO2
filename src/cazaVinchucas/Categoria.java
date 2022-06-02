package cazaVinchucas;

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
	/**
	 * Devuelve un booleano que indica si es usuario especialista.
	 * @return
	 */
	public abstract boolean esEspecialista();
}
