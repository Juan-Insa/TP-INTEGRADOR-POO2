package cazaVinchucas.organizaciones;

import cazaVinchucas.muestras.Muestra;

public interface ZonaObserver {

	public void updateMuestra(Muestra m);
	
	public void updateZonaNueva(ZonaDeCobertura z);
	
}
