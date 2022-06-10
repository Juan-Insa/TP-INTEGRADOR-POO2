package cazaVinchucas.ZonaDeCobertura;

import java.util.ArrayList;
import java.util.List;

import cazaVinchucas.CalculadoraDistancias;
import cazaVinchucas.Ubicacion;
import cazaVinchucas.muestras.Muestra;
import cazaVinchucas.organizaciones.OrganizacionObserver;

public class ZonaDeCobertura implements ZonaDeCoberturaSubject, ZonaObserver {
	
    private List<OrganizacionObserver> organizaciones;
    private List<Muestra> muestrasConocidas;
    private Ubicacion epicentro;
    private String nombre;
    private double radio;
    private List<ZonaDeCobertura> zonasSolapadas;
    
    /**
     * Constructor de una Zona de Cobertura nueva.
     * Precondición: Al momento de crear la zona dada no existen muestras
     * previas en la zona dada ni zonas de cobertura solapadas, o de existir 
     * deben ser notificadas tras construirse. 
     * Si no se desea tener que verificar las zonas o muestras, usar el otro constructor.
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
    	zonasSolapadas = new ArrayList<ZonaDeCobertura>();
    }
    
    /**
     * Constructor de una Zona de Cobertura nueva.
     * Precondición: La lista de muestras dada se corresponde a todas las
     * muestras en el sistema y la lista de zonas de cobertura debe corresponder 
     * a todas las zonas de cobertura en el sistema al momento al momento de 
     * construir esta Zona de cobertura. 
     * @param e la ubicacion del epicentro de la zona a construir.
     * @param n el nombre de la zona a construir.
     * @param r el radio de la zona a construir.
     * @param muestrasViejas
     */
    public ZonaDeCobertura(Ubicacion e, String n, double r, List<ZonaDeCobertura> zonasViejas, List<Muestra> muestrasViejas) {
    	this(e,n,r);
    	muestrasViejas.forEach(m -> {
    		this.notificar(m);
    		this.ingresarMuestra(m);
    	});
    	zonasViejas.forEach(z -> this.updateZonaNueva(z));
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
    @Override
    public void updateMuestra(Muestra m) { //string s
    	if(CalculadoraDistancias.estanDentroDelRadio(radio, epicentro, m.getUbicacion())){
    		if(!m.esVerificada()) { // s == "Nueva"
    			this.ingresarMuestra(m);
    		}
    		this.notificar(m);
    	}
    }

    /**
     * Ingresa una muestra a la zona de cobertura.
     * Precondición: Se verificó que la muestra dada esta en la zona de cobertura.
     * @param muestra, es la muestra a registrar y notificar
     */
    private void ingresarMuestra(Muestra muestra) {
    	this.muestrasConocidas.add(muestra);
    }

    /**
     * Envía una notificación a todas las organizaciónes registradas sobre el ingreso
     * o validación de una muestra de la zona. 
     * @param zona, es la zonaDeCobertura misma.
     * @param muestra, es la muestra que ingresó o se validó.
     */
    @Override
	public void notificar(Muestra muestra) {
		for (OrganizacionObserver organizacion : organizaciones) {
			organizacion.activarEvento(this, muestra);
		}
	}

    /**
     * Recibe notificación de que hay una nueva zona de cobertura.
     * Verifica que esta se encuentra dentro de su radio de cobertura.
     * Si se encuentra dentro del radio, la agrega. Si no, la desestima.
     * @param z una ZonaDeCobertura nueva.
     */
	@Override
	public void updateZonaNueva(ZonaDeCobertura z) {
		if(CalculadoraDistancias.estanRadiosSuperpuestos(radio, epicentro, z.getRadio(), z.getEpicentro())){
			this.addZonaSolapada(z);
		}
	}
	
	/**
	 * Agrega la zona dada a la lista de zonas solapadas.
	 * Precondición: La zona dada DEBE estar solapada a esta.
	 * @param z una ZonaDeCobertura que esta solapada a esta.
	 */
	private void addZonaSolapada(ZonaDeCobertura z) {
		zonasSolapadas.add(z);
	}
}