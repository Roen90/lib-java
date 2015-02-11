package ks;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class km {

    private String VMstrFecha;
    private String VMstrEntrada;
    private String VMstrSalida;
    private String VMstrReentrada;
    private String VMstrResalida;
    private String VMstrTipoMensaje;
    private String VMstrTipoTran;
    private String VMstrTarjeta;
    private String VMstrMonto;
    private String VMstrRespuesta;
    private String VMstrAprobacion;
    private String VMstrTransaccion;
    private String VMstrIP;
    private String VMstrEstado;
    private String VMstrAfiliacion;
    private String VMstrReferencia;

    private static SimpleDateFormat VMobjHora;
    private static SimpleDateFormat VMobjFecha;
    private static escribir VMhiloAgregar;
    private static List VMcolaMensajes;
    private static String VMstrRuta;
    private static depuracion VMobjDepuracion;


    public km ()
    {
        VMstrFecha = "";
        VMstrEntrada = "";
        VMstrSalida = "";
        VMstrReentrada = "";
        VMstrResalida = "";
        VMstrTipoMensaje = "";
        VMstrTipoTran = "";
        VMstrTarjeta = "";
        VMstrMonto = "";
        VMstrRespuesta = "";
        VMstrAprobacion = "";
        VMstrTransaccion = "";
        VMstrIP = "";
        VMstrEstado = "";
        VMstrAfiliacion = "";
        if ( VMobjHora == null)
            VMobjHora = new SimpleDateFormat ("HHmmssSSS");
        if ( VMobjFecha == null)
            VMobjFecha = new SimpleDateFormat ("yyMMdd");
        SimpleDateFormat VLobjFecha = new SimpleDateFormat("yyyyMMdd");
        VMstrFecha = VLobjFecha.format(new Date());
        if (VMhiloAgregar == null)
        {
            VMhiloAgregar = new escribir();
            VMhiloAgregar.setName("Agregando a KM");
            VMhiloAgregar.setDaemon(true);
        }
        if (VMcolaMensajes == null)
            VMcolaMensajes = new LinkedList();
        VMstrEntrada = VMobjHora.format(new Date());
        VMobjDepuracion = depuracion.getInstancia();
    }

    public void setHoraSalida ()
    {
        VMstrSalida = VMobjHora.format(new Date());
    }

    public void setHoraReentrada ()
    {
        VMstrReentrada = VMobjHora.format(new Date());
    }

    public void setHoraResalida ()
    {
        VMstrResalida = VMobjHora.format(new Date());
    }

    public void setTipoMensaje(String tipoMensaje)
    {
        VMstrTipoMensaje = tipoMensaje;
    }

    public void setTipoTran(String tipoTran)
    {
        VMstrTipoTran = tipoTran;
    }

    public void setTarjeta(String tarjeta)
    {
        if (tarjeta != null)
            VMstrTarjeta = tarjeta.substring(tarjeta.length() - 4, tarjeta.length());
    }

    public void setMonto(String monto)
    {
        if ( monto != null )
        {
            if ( monto.length() == 12)
                VMstrMonto = monto;
            else
                VMstrMonto = rellenar(12 - monto.length(), "0") + monto;
        }
        else
            VMstrMonto = rellenar(12, "0");
    }

    public void setCodigoRespuesta(String respuesta)
    {
        if (respuesta != null)
            VMstrRespuesta = respuesta;
    }

    public void setAprovacion(String aprobacion)
    {
        if (aprobacion != null)
            VMstrAprobacion = aprobacion;
    }

    public void setIdTransaccion(String Id)
    {
        if (Id != null)
        {
            if (Id.length() == 12)
                VMstrTransaccion = Id;
            else
                VMstrTransaccion = Id + rellenar(12 - Id.length(), " ");
        }
    }

    public void setIP(String IP)
    {
        if (VMstrIP.length() == 15)
            VMstrIP = IP;
        else
            VMstrIP = IP + rellenar(15 - IP.length()," ");
    }

    public void setEstado(String estado)
    {
        VMstrEstado = estado;
    }

    public void setAfiliacion(String afiliacion)
    {
        VMstrAfiliacion = afiliacion;
    }

    public static void setRuta(String ruta)
    {
        VMstrRuta = ruta;
    }

    private String rellenar(int Numero, String caracter)
    {
        String VLstrRespuesta = "";
        int i;
        for(i = 0; i < Numero; i++)
            VLstrRespuesta += caracter;
        return VLstrRespuesta;
    }

    public void agregar()
    {
        String VLstrMensaje;
        if (VMstrFecha.length() < 8)
            VMstrFecha += rellenar(8 - VMstrFecha.length(), " ");
        if (this.VMstrSalida.length() < 9)
            VMstrSalida += rellenar(9 - VMstrSalida.length(), "0");
        if (this.VMstrReentrada.length() < 9)
            VMstrReentrada += rellenar(9 - VMstrReentrada.length(), "0");
        if (this.VMstrResalida.length() < 9)
            VMstrResalida += rellenar(9 - VMstrResalida.length(), "0");
        if (this.VMstrTipoMensaje.length() < 2)
            VMstrTipoMensaje += rellenar(2 - VMstrTipoMensaje.length(), " ");
        if (this.VMstrTipoTran.length() < 2)
            VMstrTipoTran += rellenar(2 - VMstrTipoTran.length(), " ");
        if (this.VMstrTarjeta.length() < 4)
            VMstrTarjeta += rellenar(4 - VMstrTarjeta.length(), " ");
        if (this.VMstrMonto.length() < 12)
            VMstrMonto += rellenar(12 - VMstrMonto.length(), "0");
        if (this.VMstrRespuesta.length() < 2)
            VMstrRespuesta += rellenar(2 - VMstrRespuesta.length(), " ");
        if (this.VMstrAprobacion.length() < 6)
            VMstrAprobacion += rellenar(6 - VMstrAprobacion.length(), " ");
        if (this.VMstrTransaccion.length() < 12)
            VMstrTransaccion += rellenar(12 - VMstrTransaccion.length(), " ");
        if (this.VMstrIP.length() < 15)
            VMstrIP += rellenar(15 - VMstrIP.length(), " ");
        if (this.VMstrEstado.length() < 2)
            VMstrEstado += rellenar(8 - VMstrEstado.length(), " ");
        if (this.VMstrAfiliacion.length() < 7)
            VMstrAfiliacion += rellenar(7 - VMstrAfiliacion.length(), " ");
        VLstrMensaje = VMstrFecha +
                this.VMstrEntrada +
                this.VMstrSalida +
                this.VMstrReentrada +
                this.VMstrResalida +
                this.VMstrTipoMensaje +
                this.VMstrTipoTran +
                this.VMstrTarjeta +
                this.VMstrMonto +
                this.VMstrRespuesta +
                this.VMstrAprobacion +
                this.VMstrTransaccion +
                this.VMstrIP +
                this.VMstrEstado +
                this.VMstrAfiliacion;
        if ( VLstrMensaje.length() == 108)
        {
            synchronized (VMcolaMensajes)
            {
                VMcolaMensajes.add(VLstrMensaje);
            }
        }
        else
        {
            VMobjDepuracion.agregarMensaje("Mensaje de KM no coincide con la longitud \n" + VLstrMensaje);
        }

        if ( !VMhiloAgregar.isAlive() )
        {
            try
            {
                VMhiloAgregar = new escribir();
                VMhiloAgregar.setName("Agregando a KM");
                VMhiloAgregar.setDaemon(true);
                VMhiloAgregar.start();
            } catch ( Exception ex ) {

            }
        }
    }

    private class escribir extends Thread
    {
        public void run()
        {
            String VLstrMensaje;
            FileOutputStream VLioArchivo;
            File VLioSiguiente;
            Calendar VLobjCalendario;

            try
            {
                VLioArchivo = new FileOutputStream (VMstrRuta + "/MW" + VMobjFecha.format(new Date()), true);
                VLobjCalendario = Calendar.getInstance();
                VLobjCalendario.add(Calendar.DATE, 1);
                VLioSiguiente = new File(VMstrRuta + "/MW" + VMobjFecha.format(VLobjCalendario.getTime()));
                if (!VLioSiguiente.exists())
                    VLioSiguiente.createNewFile();
                VLobjCalendario.add(Calendar.DATE, 1);
                VLioSiguiente = new File(VMstrRuta + "/MW" + VMobjFecha.format(VLobjCalendario.getTime()));
                if (!VLioSiguiente.exists())
                    VLioSiguiente.createNewFile();
                do
                {
                    try
                    {
                        synchronized (VMcolaMensajes)
                        {
                            VLstrMensaje = VMcolaMensajes.get(0).toString();
                            VMcolaMensajes.remove(0);
                        }
                        VLioArchivo.write(VLstrMensaje.getBytes());
                        VLioArchivo.flush();
                    } catch (Exception ex) {
                        VMobjDepuracion.agregarMensaje("Problema al escribir en el archivo de KM: " + ex.getMessage());
                    }
                } while (VMcolaMensajes.size() > 0);
                VLioArchivo.flush();
                VLioArchivo.close();
            }
            catch ( Exception ex )
            {
                VMobjDepuracion.agregarMensaje("Problema al abrir el archivo de KM: " + ex.getMessage());
            }
        }
    }
}