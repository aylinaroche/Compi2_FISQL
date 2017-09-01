package BaseDeDatos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class RegistroDB {

    public static String pathProcedimiento = "";
    public static String pathObjeto = "";
    public static ArrayList listaTabla = new ArrayList();

    public void generarArchivo() {
        File fichero = new File("/home/aylin/NetBeansProjects/FISQL/BD/" + BaseDeDatos.DBActual + "/DB.xml");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.write("<procedure>");
            bw.write("  \"" + pathProcedimiento + "\"");
            bw.write("</procedure>");
            bw.write("<object>");
            bw.write("  \"" + pathObjeto + "\"");
            bw.write("</object>");

            for (int i = 0; i < listaTabla.size(); i++) {
//                DB db = listaTabla.get(i);
//
//                bw.write("<BD>\n");
//                bw.write("  <nombre>" + db.nombre + "</nombre>\n");
//                bw.write("  <path>\"" + db.path + "\"</path>\n");
//                bw.write("</BD>\n");

            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("ERROR al generar el archivo maestro");
        }

    }

}

class tupla {

    String tipo;
    String nombre;
}

class tabla {

    String nombre;
    String path;
    ArrayList<tupla> tuplas;
}
