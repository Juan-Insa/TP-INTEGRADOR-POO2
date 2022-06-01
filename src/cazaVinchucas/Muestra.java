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
	private String foto;
    private Clasificacion resultado = Clasificacion.NINGUNA;
    private List<Opinion> opiniones = new ArrayList<>();
    private int id; //Hay que ver si agregar getter?
    private EstadoMuestra estado;
    
    /**
     * 
     * @param ubicacion
     * @param user Usuario que saco la foto
     * @param foto Un string con el valor de la foto.
     * @param especie La clasificacion que hace el fotografo sobre la foto.
     * @param id Un identificador de la muestra.
     * @param estado El estado actual de la muestra.
     */
    public Muestra(Ubicacion ubicacion, Usuario user, String foto, Clasificacion especie) {
		
    	//Inicializa colaboradores internos;
    	this.ubicacion = ubicacion;
		this.resultado = Clasificacion.NINGUNA;
		this.id = user.getId();
		this.foto = foto;
		
    	//Inicializa el estado y agrega la opinion
    	//Del que saco la foto
    	this.estado = new SinOpinionExperto();
		Opinion opinionDeFotografo = new Opinion(user, especie);
		this.agregarOpinion(opinionDeFotografo);
		
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
	
	public Clasificacion getUltimoResultado() {
		return opiniones.get(opiniones.size()-1).getValor();
	}

}
