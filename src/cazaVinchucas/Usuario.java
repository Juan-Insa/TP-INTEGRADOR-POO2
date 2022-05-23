package cazaVinchucas;

/**
 * Clase que modela el usuario del sistema.
 * 
 * @author ivanapr
 *
 */

public class Usuario {

	/**
	 * Un id para identificar al usuario.
	 */
	private int id;
	Categoria categoria;
	/**
	 * Constructor. Crea una instancia de Usuario.
	 * Con id.
	 */
	public Usuario(int id) {
		this.setId(id);
	}

	/**
	 * Devuelve el id del usuario.
	 * @return
	 */
	int getId() {
		return id;
	}

	/**
	 * Modifica el id del usuario.
	 */
	void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Agrega muestra a la lista de muestras del sistema.
	 * @param muestraEnviada es la muestra a agregar
	 */
	public void enviarMuestra(Muestra muestraEnviada) {
		
	}
	
	/**
	 * Agrega opinion a la lista de opiniones de una muestra del sistema.
	 * @param opinionDada es la opinion a agregar
	 */
	//public void opinar(Opinion opinionDada) {
	//	
	//}
	
	/**
	 * Actualiza la categoria de un usuario del sistema
	 */
	public void recategorizar() {
		this.categoria.setCategoria();
	}
}
