package cazaVinchucas;

import java.time.LocalDate;

import cazaVinchucas.Categoria.Usuario;

/**
 * Clase que representa la opinion dada por un usuario sobre
 * una determinada muestra.
 * @author fercho
 *
 */
public class Opinion {
	
	Usuario opinador;
	Clasificacion valor;
	LocalDate fecha;
	
	/**
	 * Constructor de una Opinion.
	 * @param u el Usuario que da la opinion.
	 * @param c el valor que le asigna a su opinion.
	 */
	public Opinion (Usuario u, Clasificacion c) {
		opinador = u;
		valor = c;
		fecha = LocalDate.now();
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
	
	/**
	 * Describe la fecha de creacion de la opinion.
	 * @return la fecha de creacion de la opinion.
	 */
	public LocalDate getFecha() {
		return fecha;
	}

}
