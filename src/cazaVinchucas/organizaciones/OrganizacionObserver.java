package cazaVinchucas.organizaciones;

import cazaVinchucas.ZonaDeCobertura.ZonaDeCobertura;
import cazaVinchucas.muestras.Muestra;

public interface OrganizacionObserver {
    public void activarEvento(ZonaDeCobertura zona, Muestra muestra);
}
