package cazaVinchucas;

/**
 * Clase que representa la opinion dada por un usuario sobre
 * una determinada muestra.
 * @author fercho
 *
 */
public class Opinion {
	
	/**
	 * enum para representar los diferentes valores que
	 * puede tomar una opinion. Siendo:
	 * VINCHUCA: Vinchuca.
	 * CHINCHEFOLIADA: Chinche foliada.
	 * PHTIACHINCHE: Phtia-Chinche.
	 * NINGUNA: Ninguna.
	 * POCOCLARA: Imagen poco clara.
	 * @author fercho
	 *
	 */
	public enum Clasificacion {
		VINCHUCA,CHINCHEFOLIADA, PHTIACHINCHE,NINGUNA,POCOCLARA
	}

	Usuario opinador;
	Clasificacion valor;
	
	/**
	 * Constructor de una Opinion.
	 * @param u el Usuario que da la opinion.
	 * @param c el valor que le asigna a su opinion.
	 */
	public Opinion (Usuario u, Clasificacion c) {
		opinador = u;
		valor = c;
	}

	/**
	 * Describe el usuario que dió la opinion.
	 * @return el Usuario que dió la opinion. 
	 */
	public Usuario getUsuario() {
		return opinador;
	}

	/**
	 * Describe el valor asignado a la opinion.
	 * @return la Clasificación que representa el valor de
	 * la opinion.
	 */
	public Clasificacion getValor() {
		return valor;
	}
	
}
