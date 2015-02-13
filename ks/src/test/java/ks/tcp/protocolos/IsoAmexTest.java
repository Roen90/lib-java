package ks.tcp.protocolos;

import junit.framework.TestCase;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class IsoAmexTest extends TestCase {

    private String Encabezado;
    private String Tipo;
    private String Primario;
    private String Secundario;
    private String BitMapPrimario;
    private String BitMapSecundario;
    public String Campos[];

    //Constructor
    public IsoAmexTest() {
        Encabezado = "";
        Tipo = "";
        Primario = "";
        Secundario = "";
        Campos = new String[127];
    }

    public void testGetEncabezado() throws Exception {

    }

    public void testGetTipo() throws Exception {

    }

    public void testGetPrimario() throws Exception {

    }

    public void testGetSecundario() throws Exception {

    }

    public void testGetBitMapPrimario() throws Exception {

    }

    public void testMapeo() throws Exception {

    }

    public void testProcesarISO() throws Exception {
        String Transaccion = "";

        int i = 0, VLintValor, VLintPosicion, VLintLongitud;

        NumberFormat VLobjFormat = new DecimalFormat("00000000");
        try {
            this.Encabezado = "";
            this.Tipo = Transaccion.substring(0, 4);
            VLintPosicion = 4;
            this.BitMapPrimario = Transaccion.substring(4, 12);
            for (i = 0; i < 8; i++)
            {
                char VLcharCaracter = Transaccion.charAt(VLintPosicion);
                VLintValor = (int) VLcharCaracter;
                this.Primario += VLobjFormat.format(Long.parseLong(Integer.toBinaryString(VLintValor)));
                VLintPosicion ++;
            }
            if (this.Primario.startsWith("1"))
            {
                for (i = 0; i < 8; i++)
                {
                    char VLcharCaracter = Transaccion.charAt(VLintPosicion);
                    VLintValor = Character.getNumericValue(VLcharCaracter);
                    this.Secundario += VLobjFormat.format(Long.parseLong(Integer.toBinaryString(VLintValor)));
                    VLintPosicion ++;
                }
            }
            for (i = 1; i < this.Primario.length(); i++)
            {
                if (this.Primario.substring(i, i + 1).equals("1"))
                {
                    switch (i + 1)
                    {
                        case 2:
                            VLintLongitud = 2 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 2));
                            break;
                        case 3:
                            VLintLongitud = 6;
                            break;
                        case 4:
                            VLintLongitud = 12;
                            break;
                        case 7:
                            VLintLongitud = 10;
                            break;
                        case 11:
                            VLintLongitud = 6;
                            break;
                        case 12:
                            VLintLongitud = 12;
                            break;
                        case 13:
                            VLintLongitud = 4;
                            break;
                        case 14:
                            VLintLongitud = 4;
                            break;
                        case 15:
                            VLintLongitud = 6;
                            break;
                        case 17:
                            VLintLongitud = 4;
                            break;
                        case 18:
                            VLintLongitud = 4;
                            break;
                        case 19:
                            VLintLongitud = 3;
                            break;
                        case 22:
                            VLintLongitud = 12;
                            break;
                        case 23:
                            VLintLongitud = 3;
                            break;
                        case 24:
                            VLintLongitud = 3;
                            break;
                        case 25:
                            VLintLongitud = 4;
                            break;
                        case 26:
                            VLintLongitud = 4;
                            break;
                        case 27:
                            VLintLongitud = 1;
                            break;
                        case 30:
                            VLintLongitud = 24;
                            break;
                        case 31:
                            VLintLongitud = 2 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 2));
                            break;
                        case 32:
                            VLintLongitud = 2 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 2));
                            break;
                        case 33:
                            VLintLongitud = 2 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 2));
                            break;
                        case 34:
                            VLintLongitud = 2 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 2));
                            break;
                        case 35:
                            VLintLongitud = 2 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 2));
                            break;
                        case 37:
                            VLintLongitud = 12;
                            break;
                        case 38:
                            VLintLongitud = 6;
                            break;
                        case 39:
                            VLintLongitud = 3;
                            break;
                        case 41:
                            VLintLongitud = 8;
                            break;
                        case 42:
                            VLintLongitud = 15;
                            break;
                        case 43:
                            VLintLongitud = 2 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 2));
                            break;
                        case 44:
                            VLintLongitud = 2 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 2));
                            break;
                        case 45:
                            VLintLongitud = 2 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 2));
                            break;
                        case 47:
                            VLintLongitud = 3 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 3));
                            break;
                        case 48:
                            VLintLongitud = 3 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 3));
                            break;
                        case 49:
                            VLintLongitud = 3;
                            break;
                        case 52:
                            VLintLongitud = 8;
                            break;
                        case 53:
                            VLintLongitud = 2 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 2));
                            break;
                        case 54:
                            VLintLongitud = 3 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 3));
                            break;
                        case 55:
                            VLintLongitud = 3 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 3));
                            break;
                        case 60:
                            VLintLongitud = 3 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 3));
                            break;
                        case 61:
                            VLintLongitud = 3 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 3));
                            break;
                        case 62:
                            VLintLongitud = 3 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 3));
                            break;
                        case 63:
                            VLintLongitud = 3 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 3));
                            break;
                        case 64:
                            VLintLongitud = 8;
                            break;
                        case 90:
                            VLintLongitud = 42;
                            break;
                        default:
                            continue;
                    }
                    try
                    {
                        this.Campos[i + 1] = Transaccion.substring(VLintPosicion, VLintPosicion + VLintLongitud);
                        VLintPosicion += VLintLongitud;
                    }
                    catch (Exception ex)
                    {
                        this.Campos[i + 1] = Transaccion.substring(VLintPosicion);
                        System.out.println("Transaccion incompleta, problema al obtener el ISO: " + ex.getMessage() + "\nTransaccion: " + Transaccion + "\nCampo: " + i + 1);
                        break;
                    }
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("Problema al obtener el ISO: " + ex.getMessage() + "\nTransaccion: " + Transaccion + "\nCampo: " + i + 1);
        }
    }

    public void testLongitudISO() throws Exception {

    }

    public void testLongitudISO1() throws Exception {

    }

    public void testObtenerLongitud() throws Exception {

    }
}