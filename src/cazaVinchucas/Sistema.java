package cazaVinchucas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cazaVinchucas.Categoria.Usuario;
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
	Sistema() {
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
	 * Obtiene la lista de Muestras de un Usuario que hay en el Sistema.
	 * @param usuario es el Usuario del que se quieren obtener las muestras.
	 * @return una lista de Muestras de algun Usuario dado en el Sistema.
	 */
	public List<Muestra> getMuestrasDe(Usuario usuario) {
		return muestras.stream().filter(m -> (m.getUsuario() == usuario)).collect(Collectors.toList());
	}

	/**
	 * Obtiene la lista de Opiniones de un Usuario que hay en el Sistema.
	 * @param usuario es el Usuario del que se quieren obtener las opiniones.
	 * @return una lista de Opiniones de algun Usuario dado en el Sistema.
	 */
	public List<Opinion> getOpinionesDe(Usuario usuario) {
		return opiniones.stream().filter(o -> (o.getUsuario() == usuario)).collect(Collectors.toList());
	}
	
	/**
	 * Agrega una Muestra al Sistema.
	 * @param muestra es la Muestra a agregar al Sistema.
	 */
	public void agregarMuestra(Muestra muestra) {
		muestras.add(muestra);
		this.notificarZonas(muestra);
	}
	
	/**
	 * Agrega una Opinion al Sistema.
	 * @param opinion es la Opinion a agregar al Sistema.
	 */
	public void agregarOpinion(Opinion opinion, Muestra muestra) {
		opiniones.add(opinion);
		
		//PREGUNTAR EN CLASES
		if(muestra.esVerificada()) {
			this.notificarZonas(muestra);
		}
	}
	
	/**
	 * Notifica a las zonas de cobertura que hubo una
	 * muestra nueva o verificada.
	 * @param m
	 */
	private void notificarZonas(Muestra m) {
		zonas.forEach(z -> z.updateMuestra(m));	
	}
	
	/**
	 * Agrega un Usuario al Sistema.
	 * @param usuario es el Usuario a agregar al Sistema.
	 */
	public void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
}