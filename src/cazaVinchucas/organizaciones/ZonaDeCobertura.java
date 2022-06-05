package cazaVinchucas.organizaciones;

import java.util.List;

import cazaVinchucas.Ubicacion;
import cazaVinchucas.muestras.Muestra;

public class ZonaDeCobertura implements ZonaDeCoberturaSubject{
    private List<OrganizacionObserver> organizaciones;
    private List<Muestra> muestrasConocidas;
    private Ubicacion epicentro;
    private String nombre;
    private Double radio;
    
    /**
     * agrega una organización a la lista de organizaciones para ser notificada
     * de las nuevas muestras.
     * @param organizacion, la organización a agregar a la lista.
     */
    @Override
    public void registrar(OrganizacionObserver organizacion) {
    	this.organizaciones.add(organizacion);
    }
    
    /**
     * elimina una organización de la lista de organizaciones.
     * @param organizacion, la organización a eliminar de la lista.
     */
    @Override
    public void desRegistrar(OrganizacionObserver organizacion) {
    	this.organizaciones.remove(organizacion);
    }
    
    /**
     * ingresa una muestra a la zona de cobertura y notifica a las organizaciones
     * registradas.
     * @param muestra, es la muestra a registrar y notificar
     */
    void ingresarMuestra(Muestra muestra) {
    	this.muestrasConocidas.add(muestra);
    	this.notificar(this, muestra);
    }

    /**
     * envía una notificación a todas las organizaciónes registradas sobre el ingreso
     * o validación de una muestra de la zona. 
     * @param zona, es la zonaDeCobertura misma.
     * @param muestra, es la muestra que ingresó o se validó.
     */
    @Override
	public void notificar(ZonaDeCobertura zona, Muestra muestra) {
		for (OrganizacionObserver organizacion : organizaciones) {
			organizacion.activarEvento(zona, muestra);
		}
	}
    
    
    
    
    
}
