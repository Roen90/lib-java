package ks;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class depuracionTest extends TestCase {

    private static depuracion _Instancia;
    private List VMcolaMensajes;
    private Date VMdateFecha;
    private SimpleDateFormat VMstrFecha;
    private SimpleDateFormat VMstrFechaHora;
    private Thread VMhiloEscribir;
    private String VMstrPath;
    private static String VMstrConfiguracion;

    public depuracionTest()
    {
        if (this.VMhiloEscribir == null)
        {
            this.VMhiloEscribir = new Escribir();
            this.VMstrFecha = new SimpleDateFormat("yyMMdd");
            this.VMstrFechaHora = new SimpleDateFormat("yyMMdd HH:mm:ss");
            this.VMcolaMensajes = new LinkedList();
            String VLstrPath = depuracion.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            VLstrPath = VLstrPath.replaceAll("%20", " ");
            VLstrPath = VLstrPath.substring(0, VLstrPath.lastIndexOf("/"));
            this.VMstrPath = (VLstrPath + "/log/");
            VMstrConfiguracion = "";
            File VLioCarpeta = new File(this.VMstrPath);
            if (!VLioCarpeta.exists())
                VLioCarpeta.mkdir();
        }
    }

    public void testGetInstancia() throws Exception {

    }

    public void testSetConfiguracion() throws Exception {

    }

    public void testAgregarMensaje() throws Exception {
        String Mensaje = "";
        try
        {
            VMcolaMensajes.add(Mensaje);
            if ( !VMhiloEscribir.isAlive() )
            {
                VMhiloEscribir = new Escribir();
                VMhiloEscribir.setName("Mensaje depuracion");
                VMhiloEscribir.setDaemon(true);
                VMhiloEscribir.start();
            }
        }
        catch ( Exception ex )
        {
            System.out.println("Error al agregar mensaje: " + ex);
        }
    }

    private class Escribir extends Thread
    {
        public void run ()
        {
            String VLstrMensaje;
            boolean VLblnEscribi;
            FileOutputStream VLioArchivo;

            try
            {
                VMdateFecha = new Date();
                VLioArchivo = new FileOutputStream (VMstrPath + VMstrConfiguracion + "-" + VMstrFecha.format(VMdateFecha) + ".log", true );
                do
                {
                    try{
                        synchronized (VMcolaMensajes)
                        {
                            VLstrMensaje = VMstrFechaHora.format(VMdateFecha).toString() + ":" + (String) VMcolaMensajes.get(0) + "\n";
                            VMcolaMensajes.remove(0);
                        }
                        VLblnEscribi = false;
                        do
                        {
                            try
                            {
                                VLioArchivo.write(VLstrMensaje.getBytes());
                                VLioArchivo.flush();
                                VLblnEscribi = true;
                            }
                            catch (Exception ex)
                            {
                                if (VLioArchivo != null)
                                {
                                    VLioArchivo.flush();
                                    VLioArchivo.close();
                                }
                                Thread.sleep(500);
                                VLioArchivo = new FileOutputStream(VMstrPath + VMstrConfiguracion + "-" + VMstrFecha.format(VMdateFecha) + ".log", true );
                            }
                        } while (!VLblnEscribi);
                    }
                    catch ( Exception ex )
                    {
                        break;
                    }
                }while ( VMcolaMensajes.size() >= 1 );
                VLioArchivo.close();
                VLioArchivo = null;
                VMdateFecha = null;
            }
            catch( Exception ex){
                System.out.println("Error al escribir: " + ex);

            }
        }
    }
}