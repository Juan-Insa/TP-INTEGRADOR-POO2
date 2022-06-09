package cazaVinchucas;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cazaVinchucas.Usuario;
import cazaVinchucas.muestras.Muestra;
/**
 * Clase que modela el sistema. Es patron singleton
 * 
 * @author ivanapr
 *
 */
public final class Sistema {
	private static Sistema sistema;
	private List<Muestra> muestras;
	private List<Opinion> opiniones;
	
	private Sistema() {
		this.muestras = new ArrayList<Muestra>();
		this.opiniones = new ArrayList<Opinion>();
	}
	public static Sistema getSistema() {
		if(sistema == null) {
			sistema = new Sistema();
		}
		return sistema;
	}
	
	public List<Muestra> getMuestrasDe(Usuario usuario) {
		List<Muestra> muestrasDe = muestras.stream().filter(m -> m.getUsuario().equals(usuario)).collect(Collectors.toList());
		return muestrasDe;
	}
	public List<Opinion> getOpinionesDe(Usuario usuario) {
		List<Opinion> opinionesDe = opiniones.stream().filter(o -> o.getUsuario().equals(usuario)).collect(Collectors.toList());
		return opinionesDe;
	}
	public void agregarMuestra(Muestra muestra) {
		muestras.add(muestra);
	}
	public void agregarOpinion(Opinion opinion) {
		opiniones.add(opinion);
	}
}