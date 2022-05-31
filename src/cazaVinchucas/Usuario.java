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
	public void enviarMuestra(Ubicacion ubicacion, String foto, Clasificacion especie) {
		Muestra muestra = new Muestra(ubicacion, this, foto, especie);
		Sistema.agregar(muestra);
	}
	
	/**
	 * Agrega opinion a la lista de opiniones de una muestra del sistema.
	 * @param especie es la clasificacion de vinchuca que opina el usuario
	 * @param muestra es la muestra sobre la que opina el usuario
	 */
	public void opinar(Clasificacion especie, Muestra muestra) {
		unaOpinion = new Opinion(this, especie);
		muestra.agregarOpinion(unaOpinion);
	}
	
	/**
	 * Actualiza la categoria de un usuario del sistema
	 */
	public void recategorizar() {
		this.categoria.setCategoria();
	}
}
