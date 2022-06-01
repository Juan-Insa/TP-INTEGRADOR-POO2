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
	 * Precondición: Los grados deben ser expresados como grados decimales
	 * y deben ser grados validos para valores de latitudes y longitudes.
	 * Si 30°11'50" entonces se debe expresar como 30.1972.
	 * obtenido del calculo:
	 * g+(m/60)+(s/3600)
	 * Donde g son los grados, m los minutos y s los segundos.
	 * 
	 * @param lat la latitud de la ubicación dada expresada como un double.
	 * @param lon la longitud de la ubicación dada expresada como un double.  
	 */
	public Ubicacion (double lat, double lon) throws Exception {
		if(!this.esLatitudValida(lat) || !this.esLongitudValida(lon)) {
			throw new Exception ("Latitud o longitud invalida.");
		}
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

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}
}