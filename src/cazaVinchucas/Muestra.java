 package cazaVinchucas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cazaVinchucas.Opinion.Clasificacion;

public class Muestra {
	private Ubicacion ubicacion;
    private String especie;
    private List<Opinion> opiniones = new ArrayList<>();
    private int id; 
    private Clasificacion resultado;
    private EstadoMuestra estado;
    
    /**
     * constructor de Muestra
     */
    public Muestra(Ubicacion ubicacion, String especie, int id, EstadoMuestra estado) {
		super();
		this.ubicacion = ubicacion;
		this.especie = especie;
		this.id = id;
		this.resultado = Clasificacion.NINGUNA;
		this.estado = estado;
	}

	/**
     * indica si la muestra tiene un resultado.
     */
    boolean esVerificada() {
    	return this.resultado != Clasificacion.NINGUNA;
    }
    
    /**
     * agrega una opinion a la lista de opiniones.
     * @param opinion, es la opinion a agregar a la lista.
     */
    void addOpinion(Opinion opinion) {
    	this.opiniones.add(opinion);
    }
    
    /**
     * delega al estado de la muestra el proceso de agregar una nueva opinion.
     * @param opinion, es la opinion a agregar.
     * Nota: acá se hace cambio de estado en el caso de que la opinion ingresada
     * sea de experto, salvo que ya esté verificada la muestra.
     */
    void agregarOpinion(Opinion opinion) {
    	this.estado.agregarOpinion(opinion, this);
    }

	/**
	 * devuelve la lista de opiniones de la muestra
	 */
	List<Opinion> getOpiniones(){
		return this.opiniones;
	}

	/**
	 * indica el resultado verificado de la muestra.
	 * @return el resultado de la muestra
	 */
	public Clasificacion getResultado() {
		return this.resultado;
	}

	/**
	 * establece el resultado verificado de la muestra.
	 * @param valor, es la clasificación a establecer como resultado.
	 */
    void setResultado(Clasificacion valor) {
	    this.resultado = valor;	
	}


	void setEstado(EstadoMuestra estado) {
		this.estado = estado;
	}

	public EstadoMuestra getEstado() {
		return this.estado;
	}
}
