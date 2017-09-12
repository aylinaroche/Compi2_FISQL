package XML;

import BaseDeDatos.BaseDeDatos;
import BaseDeDatos.RegistroDB;
import BaseDeDatos.RegistroMaestro;
import BaseDeDatos.RegistroObjeto;
import BaseDeDatos.RegistroProcedure;
import BaseDeDatos.RegistroTabla;
import BaseDeDatos.RegistroUsuario;
import java.util.ArrayList;
import USQL.Nodo;
import USQL.Objetos.Parametro;
import java.io.IOException;

/**
 *
 * @author aylin
 */
public class RecorridoXML {

    public static ArrayList parametros = new ArrayList();
    public static int tipo = 0;
    public static String nombreTabla = "";

    public Object Recorrido(Nodo raiz) {
        Object result = null;

        if (raiz != null) {
            switch (raiz.texto) {
                case "INICIO":
                    result = Recorrido(raiz.hijos[0]);
                    break;
                case "ETIQUETA":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            ArrayList lista = new ArrayList();
                            lista.add(result);
                            result = BaseDeDatos.voltear(lista);
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[0]);
                            ArrayList lista2 = (ArrayList) Recorrido(raiz.hijos[1]);
                            lista2.add(result);
                            result = BaseDeDatos.voltear(lista2);
                            break;
                    }
                    break;
                case "ETIQUETAP":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            ArrayList lista1 = new ArrayList();
                            lista1.add(Recorrido(raiz.hijos[0]));
                            result = lista1;
                            break;
                        case 2:
                            ArrayList lista = (ArrayList) Recorrido(raiz.hijos[1]);
                            lista.add(Recorrido(raiz.hijos[0]));
                            result = lista;
                            break;
                    }
                    break;
                case "ETQ":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            switch (raiz.hijos[0].texto.toLowerCase()) {
                                //MAESTRO
                                case "path":
                                case "nombre":
                                case "integer":
                                case "text":
                                case "double":
                                case "bool":
                                case "date":
                                case "datetime":
                                case "atr":
                                case "params":
                                case "src":
                                case "campo":
                                case "complemento":
                                case "pass":
                                case "foranea":
                                case "atributo":
                                    result = Recorrido(raiz.hijos[1]);
                                    result = new Etq(raiz.hijos[0].texto, result);
                                    break;
                                case "bd":
                                    ArrayList lista = (ArrayList) Recorrido(raiz.hijos[1]);

                                    try {
                                        Etq e1 = (Etq) lista.get(0); //nombre
                                        Etq e2 = (Etq) lista.get(1); //path
                                        RegistroMaestro.agregarBD(e1.valor.toString(), e2.valor.toString());
                                    } catch (Exception e) {
                                        System.out.println("Error en XML bd = " + e);
                                    }
                                    break;
                                //USUARIOS
                                case "usuario":
                                    ArrayList listaUsr = (ArrayList) Recorrido(raiz.hijos[1]);
                                    try {
                                        Etq nombre = (Etq) listaUsr.get(0);
                                        Etq pass = (Etq) listaUsr.get(1);
                                        RegistroUsuario.agregarUsuario(nombre.valor.toString(), pass.valor.toString());
                                    } catch (Exception e) {
                                        System.out.println("Error en XML usr = " + e);
                                    }
                                    break;
                                //OBJETOS
                                case "obj":
                                    ArrayList listaObj = (ArrayList) Recorrido(raiz.hijos[1]);
                                    try {
                                        Etq nombre = (Etq) listaObj.get(0);
                                        ArrayList atr = new ArrayList();
                                        Etq l = (Etq) listaObj.get(1);
                                        ArrayList lObj = (ArrayList) l.valor;
                                        for (int i = 0; i < lObj.size(); i++) {
                                            Etq e = (Etq) lObj.get(i);
                                            Parametro p = new Parametro(e.nombre, e.valor.toString());
                                            atr.add(p);
                                        }
                                        RegistroObjeto.agregarObjeto(nombre.valor.toString(), atr);
                                    } catch (Exception e) {
                                        System.out.println("Error en XML obj = " + e);
                                    }
                                    break;
                                //PROCEDIMIENTO
                                case "proc":
                                    ArrayList listaProc = (ArrayList) Recorrido(raiz.hijos[1]);
                                    try {
                                        Etq nombre = (Etq) listaProc.get(0);
                                        ArrayList atr = new ArrayList();
                                        Etq src = (Etq) listaProc.get(listaProc.size() - 1);
                                        if (listaProc.size() > 2) {
                                            Etq l = (Etq) listaProc.get(1);
                                            ArrayList lProc = (ArrayList) l.valor;
                                            for (int i = 0; i < lProc.size(); i++) {
                                                Etq e = (Etq) lProc.get(i);
                                                Parametro p = new Parametro(e.nombre, e.valor.toString());
                                                atr.add(p);
                                            }
                                        }
                                        RegistroProcedure.agregarProcedure(nombre.valor.toString(), src.valor.toString(), atr, "void");
                                    } catch (Exception e) {
                                        System.out.println("Error en XML obj = " + e);
                                    }
                                    break;
                                //BD
                                case "tabla":
                                    ArrayList listaBD = (ArrayList) Recorrido(raiz.hijos[1]);
                                    try {
                                        Etq nombre = (Etq) listaBD.get(0);
                                        Etq path = (Etq) listaBD.get(1);
                                        ArrayList atr = new ArrayList();

                                        for (int i = 2; i < listaBD.size(); i++) {
                                            Etq campo = (Etq) listaBD.get(i);
                                            ArrayList atrib = (ArrayList) campo.valor;
                                            Etq nomCampo = (Etq) atrib.get(0);
                                            ArrayList comp = new ArrayList();
                                            if (atrib.size() > 1) {
                                                // Etq eComp = (Etq) atrib.get(1);
                                                for (int j = 1; j < atrib.size(); j++) {
                                                    Etq eC = (Etq) atrib.get(j);
                                                    if (eC.valor instanceof ArrayList) {
                                                        ArrayList foranea = (ArrayList) eC.valor;
                                                        Etq l = (Etq) foranea.get(0);
                                                        ArrayList foran = (ArrayList) l.valor;
                                                        Etq n = (Etq) foran.get(0);
                                                        Etq a = (Etq) foran.get(1);
                                                        Parametro f = new Parametro(n.valor.toString(), a.valor.toString());
                                                        comp.add(f);
                                                    } else {
                                                        comp.add(eC.valor.toString());
                                                    }
                                                }

                                            }

                                            Parametro p = new Parametro(nomCampo.valor.toString(), nomCampo.nombre, comp);
                                            atr.add(p);
                                        }
                                        RegistroDB.agregarTablaXML(nombre.valor.toString(), atr);
                                        nombreTabla = nombre.valor.toString();
                                        BaseDeDatos.cargar(path.valor.toString());
//
                                    } catch (IOException e) {
                                        System.out.println("Error en XML obj = " + e);
                                    }
                                    break;
                                case "procedure":
                                case "object":
                                    break;
                                // REGISTRO
                                case "registro":
                                    ArrayList listaRegis = (ArrayList) Recorrido(raiz.hijos[1]);
                                    try {
                                        ArrayList atr = new ArrayList();

                                        for (int i = 0; i < listaRegis.size(); i++) {
                                            Etq e = (Etq) listaRegis.get(i);
                                            atr.add(e.valor);

                                        }
                                        RegistroTabla.insertarDesdeXML(nombreTabla, atr);
                                    } catch (Exception e) {
                                        System.out.println("Error en XML registro = " + e);
                                    }
                                    break;
                                default:
                                    result = Recorrido(raiz.hijos[1]);
                                    result = new Etq(raiz.hijos[0].texto, result);
                                    break;
                            }

                            break;
                    }
                    break;
                case "OPCION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "CONTENIDO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            if ("id".equals(raiz.hijos[0].tipo)) {
                                result = raiz.hijos[0].texto;
                            } else {
                                String cadena = raiz.hijos[0].texto.replace("\"", "");
                                result = cadena;
                            }
                            break;
                        case 2:
                            if ("id".equals(raiz.hijos[0].tipo)) {
                                result = raiz.hijos[0].texto + " nulo";
                                break;
                            }
                    }
                    break;
            }
        }
        return result;
    }

}

class Etq {

    String nombre;
    Object valor;

    public Etq(String n, Object v) {
        this.nombre = n;
        this.valor = v;
    }
}
