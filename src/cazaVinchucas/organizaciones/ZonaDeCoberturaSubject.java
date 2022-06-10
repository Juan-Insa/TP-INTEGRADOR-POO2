package cazaVinchucas.organizaciones;

import cazaVinchucas.muestras.Muestra;

public interface ZonaDeCoberturaSubject {
    public void registrar(OrganizacionObserver org);
    public void desRegistrar(OrganizacionObserver org);
    public void notificar(Muestra muestra);
}
