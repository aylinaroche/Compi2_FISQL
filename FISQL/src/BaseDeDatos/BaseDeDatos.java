package BaseDeDatos;

import java.io.IOException;

/**
 *
 * @author aylin
 */
public class BaseDeDatos {

    public static String DBActual = "";

    public void limpiar() {
        RegistroMaestro.listaDB.clear();
        RegistroDB.listaTabla.clear();
    }
    public void cargarBD() throws IOException {
        RegistroDB rd = new RegistroDB();
        
        RegistroProcedure rp = new RegistroProcedure();
        rp.cargarProcedure();

        RegistroObjeto ro = new RegistroObjeto();
        ro.cargarObject();

        RegistroTabla rt = new RegistroTabla();
    }

    public static void crearArchivos() {
        RegistroMaestro rm = new RegistroMaestro();
        rm.generarArchivo();
        RegistroProcedure rp = new RegistroProcedure();
        rp.generarArchivo();
        RegistroObjeto ro = new RegistroObjeto();
        ro.generarArchivo();
    }

}
