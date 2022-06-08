package cazaVinchucas;

/**
 * Clase encargada de describir una ubicacion de la
 * tierra en base a su latitud y longitud.
 * @author fercho
 *
 */
public class Ubicacion {

	private double latitud, longitud;
	
	/**
	 * Constructor de Ubicación.
	 * 
	 * Precondición: Los grados deben ser validos para valores de latitudes (min -90°/max 90°)
	 * y longitudes (min -180°/max 180°).
	 * Ademas, los grados deben ser expresados como grados decimales.
	 * Si 30°11'50" entonces se debe expresar como 30.1972.
	 * obtenido del calculo:
	 * g+(m/60)+(s/3600)
	 * Donde g son los grados, m los minutos y s los segundos.
	 * 
	 * @param lat la latitud de la ubicación dada expresada como un double.
	 * @param lon la longitud de la ubicación dada expresada como un double.  
	 */
	public Ubicacion (double lat, double lon) throws Exception {
		//Errores
		if(!this.esLatitudValida(lat)) {
			throw new Exception ("Latitud invalida.");
		}
		if(!this.esLongitudValida(lon)) {
			throw new Exception ("Longitud invalida.");
		}
		
		//Constructor valido
		latitud = lat;
		longitud = lon;
	}

	/**
	 * Indica si la longitud dada se corresponde a una longitud del planeta Tierra.
	 * @param lon un double que representa un grado decimal.
	 * @return True si el valor dado se corresponde a una longitud del planeta Tierra.
	 */
	private boolean esLongitudValida(double lon) {
		return -180d <= lon && lon <= 180d;
	}

	/**
	 * Indica si la longitud dada se corresponde a una latitud del planeta Tierra.
	 * @param lat un double que representa un grado decimal.
	 * @return True si el valor dado se corresponde a una latitud del planeta Tierra.
	 */
	private boolean esLatitudValida(double lat) {
		return -90d <= lat && lat <= 90d;
	}

	/**
	 * Describe la latitud de esta ubicación.
	 * @return Un double con la latitud en grados decimales de esta ubicación.
	 */
	public double getLatitud() {
		return latitud;
	}

	/**
	 * Describe la longitud de esta ubicación.
	 * @return Un double con la longitud en grados decimales de esta ubicación.
	 */
	public double getLongitud() {
		return longitud;
	}
}
