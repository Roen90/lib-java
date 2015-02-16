package ks;

import junit.framework.TestCase;

import java.io.File;

public class configuracionTest extends TestCase {
    private static String VMstrConfiguracion;
    private static String VMstrPath;
    private static String VMstrPathConf;
    private static boolean VMblnActivaLog;

    public void testGetRuta() throws Exception {
        if (VMstrPath == null) {
            //VMstrPath = configuracion.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            File currentDirectory = new File(new File(".").getAbsolutePath());
            VMstrPath = currentDirectory.getAbsolutePath();
            VMstrPath = VMstrPath.replaceAll("%20", " ");
            if (VMstrPath.contains("/")) {
                VMstrPath = VMstrPath.substring(0, VMstrPath.lastIndexOf("/")) + "/";
            } else {
                VMstrPath = VMstrPath.substring(0, VMstrPath.lastIndexOf("\\")) + "\\";
            }
        }
        System.out.println(VMstrPath);

    }

    public void testGetRutaConfiguracion() throws Exception {
        if (VMstrPathConf == null) {
            if (VMstrPath == null) {
                configuracion.getRuta();
            }
            if (VMstrPath.contains("/")) {
                VMstrPathConf = VMstrPath + "config/" + VMstrConfiguracion + ".properties";
            } else {
                VMstrPathConf = VMstrPath + "config\\" + VMstrConfiguracion + ".properties";
            }
        }
        System.out.println(VMstrPathConf);
    }

    public void testGetActivarLog() throws Exception {

    }

    public void testSetActivarLog() throws Exception {

    }

    public void testSetConfiguracion() throws Exception {

    }

    public void testGetConfiguracion() throws Exception {

    }
}