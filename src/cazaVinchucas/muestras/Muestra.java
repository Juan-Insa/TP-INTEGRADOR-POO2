 package cazaVinchucas.muestras;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cazaVinchucas.Opinion;
import cazaVinchucas.Ubicacion;
import cazaVinchucas.Usuario;
import cazaVinchucas.Opinion.Clasificacion;

/**
 * Clase encargada de representar una muestra tomada por un usuario.
 * 
 * @author Juan Cruz, Fernando e ivanapr
 *
 */
public class Muestra {
	private Ubicacion ubicacion;
	private String foto;
    private Clasificacion resultado = Clasificacion.NINGUNA;
    private List<Opinion> opiniones = new ArrayList<>();
    private Usuario usuario;
    private EstadoMuestra estado;
    private LocalDate fecha;
    
    
    /**
     * Constructor de muestra
     * 
     * @param ubicacion donde se tomo la muestra
     * @param user Usuario que saco la foto
     * @param foto Un string con el valor de la foto.
     * @param especie La clasificacion que hace el fotografo sobre la foto.
     * @param estado El estado actual de la muestra.
     */
    public Muestra(Ubicacion ubicacion, Usuario user, String foto, Clasificacion especie) {
		
    	//Inicializa colaboradores internos;
    	this.ubicacion = ubicacion;
		this.resultado = Clasificacion.NINGUNA;
		this.usuario = user;
		this.foto = foto;
		this.fecha = LocalDate.now();
		
    	//Inicializa el estado y agrega la opinion
    	//Del que saco la foto
    	this.estado = new SinOpinionExperto();
		Opinion opinionDeFotografo = new Opinion(user, especie);
		//Pregunta, estoy obligado?
		try {
			this.agregarOpinion(opinionDeFotografo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
    
    /**
     * Getter ubicacion
     * @return Devuelve la ubicacion de la muestra.
     */
    public Ubicacion getUbicacion() {
    	return ubicacion;
    }
    
    /**
     * Getter usuario
     * @return Devuelve el usuario que envio la muestra.
     */
    public Usuario getUsuario() {
    	return usuario;
    }
    
    /**
     * Getter foto
     * @return Devuelve un String correspondiente a la codificación de la foto de la muestra.
     */
    public String getFoto() {
    	return foto;
    }
    
    /**
     * Agrega una opinion a la lista de opiniones.
     * @param opinion, es la opinion a agregar a la lista.
     */
    void addOpinion(Opinion opinion) {
    	this.opiniones.add(opinion);
    }
    
    /**
     * Delega al estado de la muestra el proceso de agregar una nueva opinion.
     * @param opinion, es la opinion a agregar.
     */
    public void agregarOpinion(Opinion opinion) throws Exception {
    	if (!this.hayOpinionDe(opinion.getUsuario())) {
    	    this.estado.agregarOpinion(opinion, this);
    	} else {
    		throw new Exception("El usuario ya opino sobre esta muestra.");
    	}
    }
    
	/**
	 * indica si el usuario dado opinó en la muestra.
	 * @param opinador, es el Usuario a saber si opino.
	 * @param muestra, es la muestra a saber si tiene la opinion.
	 * @return verdadero si hay una opinion con el usuario dado, falso de lo contrario 
	 */
	boolean hayOpinionDe(Usuario opinador) {
		return this.getOpiniones().stream().anyMatch(o -> o.getUsuario() == opinador);
	}

	/**
	 * Devuelve la lista de opiniones de la muestra
	 * @return una lista de opiniones
	 */
	public List<Opinion> getOpiniones(){
		return this.opiniones;
	}

	/**
	 * Indica el resultado verificado de la muestra.
	 * @return el resultado de la muestra
	 */
	public Clasificacion getResultado() {
		return this.resultado;
	}

	/**
	 * Establece el resultado verificado de la muestra.
	 * @param valor, es la clasificación a establecer como resultado.
	 */
    void setResultado(Clasificacion valor) {
	    this.resultado = valor;	
	}


    /**
     * Establece un cambio del estado de muestra.
     * @param estado es el estado al que cambia la muestra.
     */
	void setEstado(EstadoMuestra estado) {
		this.estado = estado;
	}
	
	/**
	 * Devuelve la clasificación de la última observación recibida.
	 * @return La última clasificación recibida.
	 * Nota: Si el estado de la muestra fuese verificado, entonces, la
	 * clasificación devuelta sería equivalente al resultado final.
	 */
	public Clasificacion getUltimoResultado() {
		return this.getUltimaOpinion().getValor();
	}

	/**
	 * indica si la muestra esta verificada.
	 * @return, verdadero si la muestra está verificada, falso de lo contrario.
	 */
	public boolean esVerificada() {
		return resultado != Clasificacion.NINGUNA ;
	}
	
	/**
	 * Describe la fecha de creacion de la muestra.
	 * @return la fecha de creacion de la muestra.
	 */
	public LocalDate getFecha() {
		return fecha;
	}

	/**
	 * Devuelve la ultima opinion creada.
	 * @return opinion.
	 */
	public Opinion getUltimaOpinion() {
		return opiniones.get(opiniones.size()-1);
	}
}