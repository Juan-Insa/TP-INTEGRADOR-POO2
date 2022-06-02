package cazaVinchucas.organizaciones;

import cazaVinchucas.muestras.Muestra;

public interface FuncionalidadExterna {
    public void nuevoEvento(Organizacion organizacion, Muestra muestra, ZonaDeCobertura zona);
}
