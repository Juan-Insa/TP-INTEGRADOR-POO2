package cazaVinchucas.organizaciones;

import java.util.List;

import cazaVinchucas.Ubicacion;
import cazaVinchucas.muestras.Muestra;

public class ZonaDeCobertura {
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
    void registrar(OrganizacionObserver organizacion) {
    	this.organizaciones.add(organizacion);
    }
    
    /**
     * elimina una organización de la lista de organizaciones.
     * @param organizacion, la organización a eliminar de la lista.
     */
    void desRegistrar(OrganizacionObserver organizacion) {
    	this.organizaciones.remove(organizacion);
    }
    
    
    
}
