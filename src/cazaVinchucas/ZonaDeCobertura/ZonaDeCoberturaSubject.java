package cazaVinchucas.ZonaDeCobertura;

import cazaVinchucas.muestras.Muestra;
import cazaVinchucas.organizaciones.OrganizacionObserver;

public interface ZonaDeCoberturaSubject {
    public void registrar(OrganizacionObserver org);
    public void desRegistrar(OrganizacionObserver org);
    public void notificar(Muestra muestra);
}
