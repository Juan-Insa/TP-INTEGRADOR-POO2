package cazaVinchucas.organizaciones;

import java.util.ArrayList;
import java.util.List;

import cazaVinchucas.Ubicacion;
import cazaVinchucas.muestras.Muestra;
import cazaVinchucas.organizaciones.ZonaDeCobertura.ZonaObserver;

public class ZonaDeCobertura implements ZonaDeCoberturaSubject, ZonaObserver {
	
    private List<OrganizacionObserver> organizaciones;
    private List<Muestra> muestrasConocidas;
    private Ubicacion epicentro;
    private String nombre;
    private double radio;
    
    /**
     * Constructor de una Zona de Cobertura nueva.
     * Precondición: Al momento de crear la zona dada no existen muestras
     * previas en la zona dada, o de existir deben ser notificadas. Si no
     * se desea tener que verificar las zonas, usar el otro constructor.
     * @param e la ubicacion del epicentro de la zona a construir.
     * @param n el nombre de la zona a construir.
     * @param r el radio de la zona a construir.
     */
    public ZonaDeCobertura(Ubicacion e, String n, double r) {
    	epicentro = e;
    	nombre = n;
    	radio = r;
    	organizaciones = new ArrayList<OrganizacionObserver>();
    	muestrasConocidas = new ArrayList<Muestra>();
    }
    
    /**
     * Constructor de una Zona de Cobertura nueva.
     * Precondición: La lista de muestras dada se corresponde a todas las
     * muestras en el sistema al momento de construir la Zona de cobertura.
     * @param e la ubicacion del epicentro de la zona a construir.
     * @param n el nombre de la zona a construir.
     * @param r el radio de la zona a construir.
     * @param muestrasViejas
     */
    public ZonaDeCobertura(Ubicacion e, String n, double r, List<Muestra> muestrasViejas) {
    	epicentro = e;
    	nombre = n;
    	radio = r;
    	organizaciones = new ArrayList<OrganizacionObserver>();
    	muestrasConocidas = new ArrayList<Muestra>();
    	muestrasViejas.forEach(m -> this.updateMuestraNueva(m));
    }
    
    /**
     * Getter nombre.
     * @return Devuelve un String que se corresponde con el nombre de la 
     * ZonaDeCobertura.
     */
    public String getNombre() {
    	return nombre;
    }
    
    /**
     * Getter epicentro.
     * @return Devuelve una Ubicación que se corresponde con el epicentro
     * de la ZonaDeCobertura.
     */
    public Ubicacion getEpicentro() {
    	return epicentro;
    }
    
    /**
     * Getter radio.
     * @return Devuelve un double que se corresponde con el radio de la
     * ZonaDeCobertura.
     */
    public double getRadio() {
    	return radio;
    }
    
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
     * Recibe notificación de que hay una nueva muestra.
     * Verifica que esta se encuentro dentro de su radio de cobertura.
     * Si se encuentra dentro del radio, la agrega. Si no, la desestima.
     * @param m una muestra nueva.
     */
    public void updateMuestraNueva(Muestra m) {
    	if(CalculadoraDeDistancias.estanDentroDelRadio(radio, epicentro, m.getUbicacion()){
    		this.ingresarMuestra(m);
    	}
    }
    
    /**
     * ingresa una muestra a la zona de cobertura y notifica a las organizaciones
     * registradas.
     * Precondición: Se verificó que la muestra dada esta en la zona de cobertura.
     * @param muestra, es la muestra a registrar y notificar
     */
    private void ingresarMuestra(Muestra muestra) {
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