package cazaVinchucas;

/**
 * Clase que se utiliza para calcular distancias en km
 * a partir de Ubicaciones que utilizan puntos cardinales 
 * (longitud y latitud).
 * 
 * @author fercho
 *
 */
public class CalculadoraDistancias {

	/**
	 * Valor por default del radio de tierra.
	 * Se puede setear a otros valores validos con
	 * setRadioTierra(radio).
	 */
	private static double radioTierra = 6371;
	
	private CalculadoraDistancias() {
	}
	
	/**
	 * Describe la distancia aproximada entre dos puntos de
	 * la Tierra.
	 * Para esta tarea se utilizó la fórmula del Haversine:
	 * R = radio de la Tierra
	 * Δlat = lat2− lat1
	 * Δlong = long2− long1
	 * a = sin²(Δlat/2) + cos(lat1) · cos(lat2) · sin²(Δlong/2)
	 * c = 2 · atan2(√a, √(1−a))
	 * d = R · c
	 * 
	 * Cabe destacar que al no ser la tierra una esfera perfecta
	 * el radio de esta varia dependiendo de la latitud. Para resultados
	 * mas exactos ajustar el radio previo al calculo.
	 *
	 */
	public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {  
		//Diferencial de las lastitudes.
		double dLat = Math.toRadians(lat2 - lat1);
		//Diferencial de las longitudes.
        double dLng = Math.toRadians(lng2 - lng1);
        //val = sin²(dlat/2)+cos(lat1).cos(lat2).sin²(dlng/2)
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        //va2 = 2 * atan2(√val, √(1-val))
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        //d = R . va2
        double distancia = radioTierra * va2;  
   
        return distancia;  
    }
	
	/**
	 * Describe la distancia aproximada entre dos ubicaciones.
	 * Para ello utiliza la formula de Haversine.
	 * @param ub1
	 * @param ub2
	 * @return una double que representa la distancia en km.
	 */
	public static double distanciaUbicaciones(Ubicacion ub1, Ubicacion ub2) {
		return CalculadoraDistancias.distanciaCoord(ub1.getLatitud(), ub1.getLongitud(), ub2.getLatitud(), ub2.getLongitud());
	}
	
	/**
	 * Se puede especificar otros radios de la tierra, si se desea mas exactitud para ciertas ubicaciones especificas.
	 * Si los puntos tienden a los meridianos (longitudes similares) utilizar el radio polar (6378km), si tienden a los 
	 * paralelos (latitudes similares) utilizar el radio ecuatorial (6371km).
	 * @param r es un double que representa un radio valido para la tierra (valor entre 6371d y 6378d.
	 * @throws Exception 
	 */
	public static void setRadioTierra(double r) throws Exception {
		if (r < 6371d || 6378d < r) {
			throw new Exception ("Radio invalido para la tierra.");
		}
		radioTierra = r;
	}
	
	/**
	 * Cambia el valor del radio de calculo actual al valor del radio ecuatorial de la tierra.
	 */
	public static void setRadioEcuatorialTierra() {
		radioTierra = 6371;
	}

	/**
	 * Cambia el valor del radio de calculo actual al valor del radio polar de la tierra.
	 */
	public static void setRadioPolarTierra() {
		radioTierra = 6378;
	}
}
