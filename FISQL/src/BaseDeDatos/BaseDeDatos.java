package BaseDeDatos;

import BaseDeDatos.Objetos.DB;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class BaseDeDatos {

    public ArrayList<DB> maestro = new ArrayList();

    public void agregarBD(String path, String nombre) {
        File fichero = new File("/home/aylin/NetBeansProjects/FISQL/BD/MAESTRO.xml");
        if (fichero.exists()) {
            System.out.println("EXISTE");
            cargarMaestro();
            for (int i = 0; i < maestro.size(); i++) {
                if (nombre.equals(maestro.get(i).nombre)) {
                    System.out.println("Error existe");
                    return;
                }
            }
            maestro.add(new DB(path, nombre));
            actualizarMaestro();

        } else {
            System.out.println("No existe");

        }
    }

    public void cargarMaestro() {

    }

    public void actualizarMaestro() {
        maestro.clear();
    }
}
