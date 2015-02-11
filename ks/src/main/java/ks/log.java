package ks;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class log {
    private static log VMobjLog;
    private Queue<String> VMcolaMensajes;
    private SimpleDateFormat VMstrFecha;
    private SimpleDateFormat VMstrFechaHora;
    private Thread VMhiloEscribir;
    private String VMstrPath;
    private static String VMstrConfiguracion;

    private log() {
        if (this.VMhiloEscribir == null) {
            this.VMhiloEscribir = new Thread();
            VMhiloEscribir.setDaemon(true);
            this.VMstrFecha = new SimpleDateFormat("yyMMdd");
            this.VMstrFechaHora = new SimpleDateFormat("yyMMdd HH:mm:ss");
            this.VMcolaMensajes = new LinkedList<String>();
            if (configuracion.getRuta().contains("/")) {
                this.VMstrPath = (configuracion.getRuta() + "/log/");
            } else {
                this.VMstrPath = (configuracion.getRuta() + "\\log\\");
            }
            VMstrConfiguracion = configuracion.getConfiguracion();
            File VLioCarpeta = new File(this.VMstrPath);
            if (!VLioCarpeta.exists()) {
                VLioCarpeta.mkdir();
            }
        }
    }

    public static log getInstancia() {
        if (VMobjLog == null)
            VMobjLog = new log();
        return VMobjLog;
    }

    public void agregarMensaje(String Mensaje) {
        try {
            VMcolaMensajes.add(Mensaje);
            if (!VMhiloEscribir.isAlive()) {
                VMhiloEscribir = new Thread() {
                    @Override
                    public void run() {
                        String VLstrMensaje;
                        boolean VLblnEscribi;
                        FileOutputStream VLioArchivo;

                        try {
                            Date VMdateNow = new Date();
                            VLioArchivo = new FileOutputStream(VMstrPath + VMstrConfiguracion + "-" + VMstrFecha.format(VMdateNow) + ".log", true);
                            do {
                                try {
                                    synchronized (VMcolaMensajes) {
                                        VLstrMensaje = VMstrFechaHora.format(VMdateNow).toString() + ":" + VMcolaMensajes.poll() + "\n";
                                    }
                                    VLblnEscribi = false;
                                    do {
                                        try {
                                            VLioArchivo.write(VLstrMensaje.getBytes());
                                            VLioArchivo.flush();
                                            VLblnEscribi = true;
                                        } catch (Exception ex) {
                                            if (VLioArchivo != null) {
                                                VLioArchivo.flush();
                                                VLioArchivo.close();
                                            }
                                            Thread.sleep(500);
                                            VLioArchivo = new FileOutputStream(VMstrPath + VMstrConfiguracion + "-" + VMstrFecha.format(VMdateNow) + ".log", true);
                                        }
                                    } while (!VLblnEscribi);
                                } catch (Exception ex) {
                                    break;
                                }
                            } while (VMcolaMensajes.size() >= 1);
                            VLioArchivo.close();
                        } catch (Exception ex) {

                        }
                    }

                };
                VMhiloEscribir.setName("Mensaje depuracion");
                VMhiloEscribir.setDaemon(false);
                VMhiloEscribir.setPriority(1);
                VMhiloEscribir.start();
            }
        } catch (Exception ex) {

        }
    }
}