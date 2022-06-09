package cazaVinchucas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cazaVinchucas.Usuario;
import cazaVinchucas.muestras.Muestra;
import cazaVinchucas.organizaciones.ZonaDeCobertura;
/**
 * Clase que modela el sistema. Es patron singleton
 * 
 * @author ivanapr
 *
 */
public class Sistema {
	/**
	 * Un sistema a representar.
	 * Una lista de todas las muestras del sistema.
	 * Una lista de todas las opiniones del sistema.
	 * Una lista de todos los usuarios del sistema.
	 */
	private static Sistema sistema;
	private List<Muestra> muestras;
	private List<Opinion> opiniones;
	private List<Usuario> usuarios;
	private List<ZonaDeCobertura> zonas;
	
	/**
	 * Constructor. Crea una unica instancia de Sistema.
	 */
	private Sistema() {
		this.muestras = new ArrayList<Muestra>();
		this.opiniones = new ArrayList<Opinion>();
		this.usuarios = new ArrayList<Usuario>();
		this.zonas = new ArrayList<ZonaDeCobertura>();
	}
	/**
	 * Obtiene la unica instancia posible de Sistema, si no existe la crea.
	 * @return
	 */
	public static Sistema getSistema() {
		if(sistema == null) {
			sistema = new Sistema();
		}
		return sistema;
	}
	
	/**
	 * Obtiene la unica instancia posible de Sistema, si no existe la crea.
	 * @param id es la identificacion que se busca en el Sistema.
	 * @return boolean que representa si ya existe algun Usuario con el id dado en el Sistema.
	 */
	public boolean existeIdUsuario(int id) {
		Stream<Usuario> users = usuarios.stream(); 
		boolean existeId = users.anyMatch(u -> u.getId() == id);
		return existeId;
	}
	
	/**
	 * Obtiene la lista de Muestras de un Usuario que hay en el Sistema.
	 * @param usuario es el Usuario del que se quieren obtener las muestras.
	 * @return una lista de Muestras de algun Usuario dado en el Sistema.
	 */
	public List<Muestra> getMuestrasDe(Usuario usuario) {
		List<Muestra> muestrasDe = muestras.stream().filter(m -> this.getUsuario(m.getId()).equals(usuario)).collect(Collectors.toList());
		return muestrasDe;
	}
	
	/**
	 * Precondición: El id corresponde a un usuario existente.
	 * @param i
	 * @return
	 */
	public Usuario getUsuario(int i) {
		return usuarios.stream().filter(u -> u.getId() == i).toList().get(0);
	}
	/**
	 * Obtiene la lista de Opiniones de un Usuario que hay en el Sistema.
	 * @param usuario es el Usuario del que se quieren obtener las opiniones.
	 * @return una lista de Opiniones de algun Usuario dado en el Sistema.
	 */
	public List<Opinion> getOpinionesDe(Usuario usuario) {
		List<Opinion> opinionesDe = opiniones.stream().filter(o -> o.getUsuario().equals(usuario)).collect(Collectors.toList());
		return opinionesDe;
	}
	
	/**
	 * Agrega una Muestra al Sistema.
	 * @param muestra es la Muestra a agregar al Sistema.
	 */
	public void agregarMuestra(Muestra muestra) {
		muestras.add(muestra);
	}
	
	/**
	 * Agrega una Opinion al Sistema.
	 * @param opinion es la Opinion a agregar al Sistema.
	 */
	public void agregarOpinion(Opinion opinion, Muestra muestra) {
		opiniones.add(opinion);
		
		//PREGUNTAR EN CLASES
		if(muestra.esVerificada()) {
			zonas.forEach(z -> z.updateMuestraValidada(muestra));
		}
	}
	
	/**
	 * Agrega un Usuario al Sistema.
	 * @param usuario es el Usuario a agregar al Sistema.
	 */
	public void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
}