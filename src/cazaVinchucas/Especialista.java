package cazaVinchucas;

public class Especialista extends Categoria {

	public Especialista() {
	}
	/**
	 * Devuelve un booleano que indica si es usuario experto.
	 * @return
	 */
	public boolean esExperto() {
		// El usuario especialista siempre es experto.
		return true;
	}
	/**
	 * Devuelve un booleano que indica si es usuario especialista.
	 * @return
	 */
	public boolean esEspecialista() {
		return true;
	}
	/**
	 * Actualiza la categoria del usuario
	 */
	public void recategorizar() {
		// No cambia su categoria porque el usuario especialista es siempre especialista
	}

}
