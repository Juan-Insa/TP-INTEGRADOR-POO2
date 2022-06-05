 package cazaVinchucas.muestras;

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
 * @author Juan Cruz y Fernando
 *
 */
public class Muestra {
	private Ubicacion ubicacion;
	private String foto;
    private Clasificacion resultado = Clasificacion.NINGUNA;
    private List<Opinion> opiniones = new ArrayList<>();
    private int id; //Hay que ver si agregar getter?
    private EstadoMuestra estado;
    
    /**
     * Constructor de muestra
     * 
     * @param ubicacion donde se tomo la muestra
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
    public void agregarOpinion(Opinion opinion) {
    	int idOpinador = opinion.getUsuario().getId();
    	if (!this.hayOpinionDe(idOpinador)) {
    	    this.estado.agregarOpinion(opinion, this);
    	}   
    }
    
	/**
	 * indica si el usuario con id dado opinó en la muestra.
	 * @param idOpinador, es el id a saber si opinó.
	 * @param muestra, es la muestra a saber si tiene la opinion.
	 * @return verdadero si hay una opinion con el id dado, falso de lo contrario 
	 */
	boolean hayOpinionDe(int idOpinador) {
		Stream<Integer> ids = this.getOpiniones().stream().map(o -> o.getUsuario().getId());
 		return ids.anyMatch(id -> id.equals(idOpinador));
	}


	/**
	 * Devuelve la lista de opiniones de la muestra
	 * @return una lista de opiniones
	 */
	List<Opinion> getOpiniones(){
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
		return opiniones.get(opiniones.size()-1).getValor();
	}

	/**
	 * indica si la muestra esta verificada.
	 * @return, verdadero si la muestra está verificada, falso de lo contrario.
	 */
	public boolean esVerificada() {
		return resultado != Clasificacion.NINGUNA ;
	}

}