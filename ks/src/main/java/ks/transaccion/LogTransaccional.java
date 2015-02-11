package ks.transaccion;

public interface LogTransaccional
{
    abstract void setRuta(String ruta);
    abstract void setNomenclatura(String nombre);

    abstract void abrir();
    abstract void agregar(String mensaje);
}
