package cazaVinchucas.Categoria;

import cazaVinchucas.Opinion;
import cazaVinchucas.Sistema;
import cazaVinchucas.Ubicacion;
import cazaVinchucas.Opinion.Clasificacion;
import cazaVinchucas.muestras.Muestra;

/**
 * Clase que modela el usuario del sistema.
 * 
 * @author ivanapr
 *
 */

public class Usuario {

	/**
	 * Una categoria para saber si es usuario basico, especialista o experto.
	 * El sistema al que pertenecen los usuarios.
	 */
	private Categoria categoria;
	private Sistema sistema;
	
	/**
	 * Constructor. Crea una instancia de Usuario.
	 * Un participante nuevo posee nivel basico excepto que se indique que es especialista.
	 * @param esEspecialista porque existen algunos usuarios que poseen conocimiento validado en forma externa.
	 */
	public Usuario(boolean esEspecialista){
		if(esEspecialista) {
			categoria = new Especialista();
		} else {
			categoria = new Basico();
		}
		sistema.agregarUsuario(this);
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
		try {
			muestra.agregarOpinion(unaOpinion);
			sistema.agregarOpinion(unaOpinion, muestra);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Actualiza la categoria de un usuario del sistema
	 * @param sistema es el sistema donde esta el usuario
	 */
	public void recategorizar(Sistema sistema) {
		this.categoria.recategorizar(sistema, this);
	}
    /**
     * Establece un cambio de la categoria del usuario.
     * @param nuevaCategoria es la categoria a la que cambia el usuario.
     */
	void setCategoria(Categoria nuevaCategoria) {
		this.categoria = nuevaCategoria;
	}
}
