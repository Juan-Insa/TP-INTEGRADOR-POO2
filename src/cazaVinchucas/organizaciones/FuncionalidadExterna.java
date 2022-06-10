package cazaVinchucas.organizaciones;

import cazaVinchucas.muestras.Muestra;

public interface FuncionalidadExterna {
    public void nuevoEvento(Organizacion organizacion, ZonaDeCobertura zona,  Muestra muestra);
}
