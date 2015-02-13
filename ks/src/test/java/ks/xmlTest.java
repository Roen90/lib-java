package ks;

import junit.framework.TestCase;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class xmlTest extends TestCase {

    private static xml VMobjLog;
    private static String VMstrConfiguracion;
    private Queue<String> VMcolaMensajes;
    private Date VMdateFecha;
    private SimpleDateFormat VMstrFecha;
    private String VMstrPath;
    private testEscribir VMhiloEscribir;

    public xmlTest()
    {
        if ( VMhiloEscribir == null )
        {
            VMcolaMensajes = new LinkedList<String>();
            VMhiloEscribir = new testEscribir();
            VMhiloEscribir.setDaemon(true);
            VMhiloEscribir.setName("Escribiendo log de transacciones");
            VMstrFecha = new SimpleDateFormat("yyMMdd");
            if (configuracion.getRuta().contains("/"))
            {
                this.VMstrPath = (configuracion.getRuta() + "/log/");
            }
            else
            {
                this.VMstrPath = (configuracion.getRuta() + "\\log\\");
            }
            File VLioCarpeta = new File (VMstrPath);
            if (!VLioCarpeta.exists())
                VLioCarpeta.mkdir();
        }
    }

    public void testGetInstancia() throws Exception {

    }

    public void testSetConfiguracion() throws Exception {

    }

    public void testAgregar() throws Exception {

    }

    public class testEscribir extends Thread{
        @Override
        public void run ()
        {

            String VLstrMensaje, VLstrArchivo;
            FileOutputStream VLioArchivo;
            boolean VLblnEscribi;
            try
            {
                VLstrArchivo = VMstrConfiguracion + "-" + VMstrFecha.format(new Date()) + ".xml";
                File VLioExiste = new File(VMstrPath + VLstrArchivo);
                if ( !VLioExiste.exists() )
                {
                    VLioArchivo = new FileOutputStream (VMstrPath + VLstrArchivo, true );
                    String VLstrEncabezado = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
                    VLioArchivo.write(VLstrEncabezado.getBytes());
                }
                else
                    VLioArchivo = new FileOutputStream (VMstrPath + VLstrArchivo, true );
                VMdateFecha = new Date();
                do
                {
                    try
                    {
                        synchronized (VMcolaMensajes)
                        {
                            VLstrMensaje = VMcolaMensajes.poll() + "\n";
                        }
                        VLblnEscribi = false;
                        do
                        {
                            try
                            {
                                VLioArchivo.write(VLstrMensaje.getBytes());
                                VLioArchivo.flush();
                                VLblnEscribi = true;
                            } catch (IOException ex) {
                                System.out.println("No se puede agregar el mensaje al archivo xml: " + ex.getMessage() + "\nMensaje: " + VLstrMensaje);
                                VLioArchivo.flush();
                                VLioArchivo.close();
                                Thread.sleep(500);
                                VLioArchivo = new FileOutputStream (VMstrPath + VLstrArchivo, true );
                            }
                        } while (!VLblnEscribi);
                    }
                    catch ( IOException ex )
                    {
                        System.out.println("No se puede agregar el mensaje al archivo xml: " + ex.getMessage());
                        break;
                    } catch (InterruptedException ex) {
                        System.out.println("No se puede agregar el mensaje al archivo xml: " + ex.getMessage());
                        break;
                    }
                } while ( VMcolaMensajes.size() >= 1 );
                VLioArchivo.close();
                VMdateFecha = null;
            }
            catch(IOException ex)
            {
                System.out.println("Problema al guardar los mensaje de la cola al xml: " + ex.getMessage());
            }
        }

    }

}