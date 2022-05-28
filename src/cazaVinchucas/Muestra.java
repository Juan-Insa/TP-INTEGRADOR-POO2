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
    
    /*
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

	/*
     * recorre las opiniones indicando si hay dos expertos que coinciden con sus opiniones 
     */
    boolean esVerificada() {
    	return this.resultado != Clasificacion.NINGUNA;
    }
    
    /**
     * agrega una opinion a la lista de opiniones.
     * @param opinion, es la opinion a agregar a la lista.
     */
    void agregarOpinion(Opinion opinion) {
    	this.opiniones.add(opinion);
    	
    	/*
    	if (this.estado.puedeOpinar(opinion.getUsuario(), this)) {
    		this.estado.agregarOpinion(opinion, this);
    		this.chequearResultado(opinion);
    	};
    	
        if (this.puedeOpinar(opinion.getUsuario())) {
            this.chequearResultado(opinion);
            this.opiniones.add(opinion);
        }
        */
    }
    
    /**
     * delega al estado de la muestra el proceso de agregar una nueve opinion.
     * @param opinion, es la opinion a agregar.
     */
    void agregarOpinionRequest(Opinion opinion) {
    	// acá hago el cambio de estado si la opinion dada es de experto.
    	if (opinion.getUsuario().esExperto()) {
    		this.setEstado(new ConOpinionExperto());
    	}
    	this.estado.agregarOpinion(opinion, this);
    }
    
    /*
     * indica si el usuario dado puede opinar en la muestra
     * si es un usuario basico, no tiene que haber opinion de un experto y la muestra no
     * debe estar verificada.
     * si es experto, la muestra no debe estar verificada.
     */
    boolean puedeOpinar(Usuario user) {
    	return !this.esVerificada() && 
    		   (user.esExperto()  || !this.hayOpinionDeExperto()); // si user es experto true, si no lo es es true
    					                                           // si no hay una opinion de un experto en la muestra
    					
    }
    
    /*
     * indica si en la lista de opiniones hay alguna de usuario experto.
     */
    boolean hayOpinionDeExperto() {
    	Stream<Usuario> usersQueOpinaron = this.opiniones.stream().map(o -> o.getUsuario());
      	
		return usersQueOpinaron.anyMatch(u -> u.esExperto());
	}

	/*
     * recorre la lista de opiniones y define si hay un resultado para la verificación,
     * o sea, si la opinion dada es de un experto, busca si hay otra opinion de experto con
     * la misma clasificación.
     */
    Clasificacion chequearResultado(Opinion opinion) {
		if (opinion.getUsuario().esExperto() && this.sePuedeVerificar(opinion.getValor())) {
			this.resultado = opinion.getValor();
			return this.resultado;
		}
		else {
			return Clasificacion.NINGUNA;
		}
	}
    
    /**
     * indica dada una nueva clasificación si la muestra ya es verificable.
     * @param c es la clasificación nueva a comparar con las actuales.
     */
    boolean sePuedeVerificar(Clasificacion c) {
		Stream<Clasificacion> clasifDeExpertos = this.opiniones.stream()
				                                               .filter(o -> o.getUsuario().esExperto())
				                                               .map(o -> o.getValor());
		return clasifDeExpertos.anyMatch(v -> v.equals(c));
	}

	/*
     * da el resultado actual de la muestra. Si es verificada devuelve 
     * directamente el resultado de la muestra, si no lo es y hay opinion de 
     * un experto, devuelve la clasificación de esa opinion. De no haber opinion
     * de experto devuelve la clasificación con mayor cantidad de votos. 
     */
    Clasificacion getResultadoActual() {
		if (this.esVerificada()) {
			return this.resultado;
		}
		else if (this.hayOpinionDeExperto()){
			return this.clasificacionDeExperto();
		}
		else {
			return this.clasificacionMasVotada();
		}
    }

	/*
	 * devuelve la clasificación de la opinion del primer experto que encuentre.
	 * precon: tiene que haber una opinion de experto en la muestra.
	 */
	Clasificacion clasificacionDeExperto() {
		Optional<Opinion> opinionExperta = this.opiniones.stream()
				                                           .filter(o -> o.getUsuario().esExperto())
				                                           .findFirst();
		
        return opinionExperta.get().getValor();
	}
	
	/*
	 * devuelve la clasificación con más iteraciones entre las opiniones de la muestra. 
	 */
	Clasificacion clasificacionMasVotada() {
		// map donde la clave es la clasificación de una opinion y el valor es su cantidad de iteraciones.
		Map<Clasificacion, Integer> map = new HashMap<Clasificacion, Integer>(); 
		                                                           
		// lista de las clasificaciones de las opiniones.
		List<Clasificacion> clasificaciones = this.opiniones.stream()
				                                            .map(o -> o.getValor())
				                                            .collect(Collectors.toList()); 
		
		// recorro cada clasificación
		for (Clasificacion c : clasificaciones) {
			Integer votos = map.get(c);                  // obtengo la cant de votos de la clasificación.
			map.put(c, votos == null ? 1 : votos + 1);   // si no tiene votos le asigna 1, sino agrega un voto a la cant.
		}
		
		Map.Entry<Clasificacion, Integer> maxC = null; // variable para almacenar la opinion mas votada
		
		for (Map.Entry<Clasificacion, Integer> e : map.entrySet()) { // utilizo Map.Entry para iterar sobre los pares
			if (maxC == null || e.getValue() > maxC.getValue()) {    // si la clasificación actual tiene mas votos
				maxC = e;                                            // lo reemplazo
			}	                                  	
		}
		return maxC.getKey();   // obtengo la clasificación más votada                                   
	}
	
	/*
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

	
    void setResultado(Clasificacion valor) {
	    this.resultado = valor;	
	}

    /**
     * establece el estado de la muestra
     * @param estado, es el estado a establecer.
     */
	void setEstado(EstadoMuestra estado) {
		this.estado = estado;
	}
	
	Opinion ultimaOpinion() {
		int ultimoIndex = this.opiniones.size() - 1;
		return this.opiniones.get(ultimoIndex);
	}
}
