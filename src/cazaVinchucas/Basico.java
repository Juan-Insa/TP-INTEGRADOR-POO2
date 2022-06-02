package cazaVinchucas;

public class Basico extends Categoria {

	public Basico() {
	}
	/**
	 * Devuelve un booleano que indica si es usuario experto.
	 * @return
	 */
	public boolean esExperto() {
		return false;
	}
	/**
	 * Devuelve un booleano que indica si es usuario especialista.
	 * @return
	 */
	public boolean esEspecialista() {
		return false;
	}
	/**
	 * Actualiza la categoria del usuario
	 */
	public void recategorizar() {
		//Básico: para aquellas personas que recién comienzan a participar. Un participante nuevo posee nivel básico.
		//Experto: son personas que durante los últimos 30 días desde la fecha actual han realizado más de 10 envíos y más de 20 revisiones.
	}

}
