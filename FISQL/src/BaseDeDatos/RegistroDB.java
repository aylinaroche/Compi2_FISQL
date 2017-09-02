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

    public static ArrayList<tabla> listaTabla = new ArrayList();

    public void generarArchivo(String nombre) {
        File fichero = new File("/home/aylin/NetBeansProjects/FISQL/BD/" + nombre + "/DB.xml");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.write("<procedure>\n");
            bw.write("  \"/home/aylin/NetBeansProjects/FISQL/BD/" + nombre + "/proc.xml\"\n");
            bw.write("</procedure>\n");
            bw.write("<object>\n");
            bw.write("  \"/home/aylin/NetBeansProjects/FISQL/BD/" + nombre + "object.xml\"\n");
            bw.write("</object>\n");

            for (int i = 0; i < listaTabla.size(); i++) {
                tabla tb = listaTabla.get(i);

                bw.write("<tabla>\n");
                bw.write("  <nombre>" + tb.nombre + "</nombre>\n");
                bw.write("  <path>\"" + tb.path + "\"</path>\n");
                for (int j = 0; j < tb.tuplas.size(); j++) {
                    tupla tp = tb.tuplas.get(j);
                    bw.write("  <tupla>\n");
                    bw.write("      <" + tp.tipo + ">\n" + tp.nombre + "</" + tp.tipo + ">\n");
                    bw.write("  </tupla>\n");
                }

                bw.write("</tabla>\n");

            }
            bw.close();
        } catch (IOException ex) {
            System.out.println("ERROR al generar el archivo db: "+ex);
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
