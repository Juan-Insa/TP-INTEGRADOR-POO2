package cazaVinchucas.organizaciones;

import cazaVinchucas.muestras.Muestra;

public interface ZonaObserver {

	public void updateMuestraNueva(Muestra m);
	
	public void updateMuestraValidada(Muestra m);
	
	public void updateZonaNueva(ZonaDeCobertura z);
	
}
