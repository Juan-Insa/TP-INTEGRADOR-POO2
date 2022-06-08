package cazaVinchucas;

import cazaVinchucas.Opinion.Clasificacion;
import cazaVinchuzas.muestras.Muestra;
import cazaVinchucas.Categoria.Basico;
import cazaVinchucas.Categoria.Categoria;
import cazaVinchucas.Categoria.Especialista;

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
	private Categoria categoria;
	Sistema sistema;
	
	/**
	 * Constructor. Crea una instancia de Usuario.
	 * Un participante nuevo posee nivel básico excepto que se indique que es especialista.
	 * @param id.
	 * @param esEspecialista porque existen algunos usuarios que poseen conocimiento validado en forma externa.
	 */
	public Usuario(int id, boolean esEspecialista) {
		this.setId(id);
		if(esEspecialista) {
			categoria = new Especialista();
		} else {
			categoria = new Basico();
		}
	}

	/**
	 * Devuelve el id del usuario.
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Modifica el id del usuario.
	 * @param id
	 */
	void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Devuelve un booleano que indica si es usuario experto.
	 * @return
	 */
	public boolean esExperto() {
		return categoria.esExperto();
	}
	
	/**
	 * Agrega muestra a la lista de muestras del sistema.
	 * @param ubicacion es la ubicacion de la muestra a enviar
	 * @param foto es la foto de la muestra a enviar
	 * @param especie es la clasificacion de la muestra a enviar
	 */
	public void enviarMuestra(Ubicacion ubicacion, String foto, Clasificacion especie) {
		Muestra muestra = new Muestra(ubicacion, this, foto, especie);
		sistema.agregarMuestra(muestra);
	}
	
	/**
	 * Agrega opinion a la lista de opiniones de una muestra del sistema.
	 * @param especie es la clasificacion de vinchuca que opina el usuario
	 * @param muestra es la muestra sobre la que opina el usuario
	 */
	public void opinar(Clasificacion especie, Muestra muestra) {
		Opinion unaOpinion = new Opinion(this, especie);
		muestra.agregarOpinion(unaOpinion);
	}
	
	/**
	 * Actualiza la categoria de un usuario del sistema
	 * @param sistema es el sistema donde esta el usuario
	 * @param usuario es el usuario actual
	 */
	public void recategorizar(Sistema sistema) {
		this.categoria.recategorizar(sistema, this);
	}
    /**
     * Establece un cambio de la categoria del usuario.
     * @param nuevaCategoria es la categoria a la que cambia el usuario.
     */
	public void setCategoria(Categoria nuevaCategoria) {
		this.categoria = nuevaCategoria;
	}

}
