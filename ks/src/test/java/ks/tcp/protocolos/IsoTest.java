package ks.tcp.protocolos;

import junit.framework.TestCase;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class IsoTest extends TestCase {
    private String Encabezado;
    private String Tipo;
    private String Primario;
    private String Secundario;
    public String Campos[];

    //Constructor
    public IsoTest() {
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

    public void testMapeo() throws Exception {
        String VLstrRespuesta = "";
        String Mapeo = "";
        int i, VLintValor, VLintLongitud;

        if (Mapeo.startsWith("1")) {
            VLintLongitud = Mapeo.length();
        } else {
            VLintLongitud = Mapeo.length() / 2;
        }
        for (i = 0; i < VLintLongitud; i += 4) {
            VLintValor = Integer.parseInt(Mapeo.substring(i, i + 4), 2);
            VLstrRespuesta += Integer.toHexString(VLintValor);
        }
        System.out.println(VLstrRespuesta.toUpperCase());

    }

    public void testProcesarISO() throws Exception {
        String Transaccion = "";
        int i = 0, VLintValor, VLintPosicion, VLintLongitud;
        NumberFormat VLobjFormat = new DecimalFormat("0000");

        try {

            this.Encabezado = Transaccion.substring(0, 12);
            this.Tipo = Transaccion.substring(12, 16);
            for (i = 0; i < 16; i++) {
                VLintValor = Integer.parseInt(Transaccion.substring(16 + i, 17 + i), 16);
                this.Primario += VLobjFormat.format(Long.parseLong(Integer.toBinaryString(VLintValor)));
            }
            VLintPosicion = 32;
            if (this.Primario.startsWith("1")) {
                for (i = 0; i < 16; i++) {
                    VLintValor = Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 1), 16);
                    this.Secundario += VLobjFormat.format(Long.parseLong(Integer.toBinaryString(VLintValor)));
                    VLintPosicion++;
                }
            }
            for (i = 1; i < this.Primario.length(); i++) {
                if (this.Primario.substring(i, i + 1).equals("1")) {
                    switch (i + 1) {
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
                            VLintLongitud = 6;
                            break;
                        case 13:
                            VLintLongitud = 4;
                            break;
                        case 15:
                            VLintLongitud = 4;
                            break;
                        case 17:
                            VLintLongitud = 4;
                            break;
                        case 18:
                            VLintLongitud = 4;
                            break;
                        case 22:
                            VLintLongitud = 3;
                            break;
                        case 23:
                            VLintLongitud = 3;
                            break;
                        case 32:
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
                            VLintLongitud = 2;
                            break;
                        case 41:
                            VLintLongitud = 16;
                            break;
                        case 43:
                            VLintLongitud = 40;
                            break;
                        case 48:
                            VLintLongitud = 3 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 3));
                            break;
                        case 49:
                            VLintLongitud = 3;
                            break;
                        case 54:
                            VLintLongitud = 3 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 3));
                            break;
                        case 55:
                            VLintLongitud = 3 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 3));
                            break;
                        case 63:
                            VLintLongitud = 3 + Integer.parseInt(Transaccion.substring(VLintPosicion, VLintPosicion + 3));
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
                        break;
                    }
                }
            }
        }catch (NumberFormatException ex){
            System.out.println(ex);
        }
    }

    public void testLongitudISO() throws Exception {

    }

    public void testLongitudISO1() throws Exception {

    }

    public void testObtenerLongitud() throws Exception {

    }
}