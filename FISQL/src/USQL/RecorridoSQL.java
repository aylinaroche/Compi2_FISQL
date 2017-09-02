package USQL;

import BaseDeDatos.Parametro;
import BaseDeDatos.RegistroMaestro;
import BaseDeDatos.RegistroObjeto;
import BaseDeDatos.RegistroProcedure;
import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class RecorridoSQL {

    public static ArrayList parametros = new ArrayList();
    public static ArrayList vacia = new ArrayList();

    public Object Recorrido(Nodo raiz) {
        Object result = null;
        // Nodo nodo = null;
        if (raiz != null) {
            switch (raiz.texto) {
                case "INICIO":
                    result = Recorrido(raiz.hijos[0]);
                    break;
                case "PAQUETE":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "SENTENCIA":
                    result = Recorrido(raiz.hijos[0]);
                    result = Recorrido(raiz.hijos[1]);
                    break;
                case "SENTENCIAP":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            Recorrido(raiz.hijos[0]);
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "INSTRUCCION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            switch (raiz.hijos[0].texto.toLowerCase()) {
                                case "detener":
                                    break;
                                case "retorno":
                                    break;
                                default:
                                    break;
                            }
                            break;
                    }
                    break;
                case "TIPO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = raiz.hijos[0].texto.toLowerCase();
                            break;
                    }
                    break;
                case "COMPLEMENTO":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            ArrayList l = new ArrayList();
                            result = l;
                        case 1:
                            ArrayList l2 = new ArrayList();
                            l2.add(raiz.hijos[0].texto);
                            result = l2;
                            break;
                        case 2:
                            ArrayList l3 = (ArrayList) Recorrido(raiz.hijos[1]);
                            l3.add(raiz.hijos[0].texto);
                            result = l3;
                            break;
                        case 3:
                            ArrayList l4 = (ArrayList) Recorrido(raiz.hijos[2]);
                            l4.add(raiz.hijos[0].texto + " " + raiz.hijos[1].texto);
                            result = l4;
                            break;
                    }
                    break;
                case "DDL":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "crear":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "usar":
                            RegistroMaestro.usarBD(raiz.hijos[1].texto);
                            break;
                        case "alterar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "eliminar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "CREAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "base_datos":
                            RegistroMaestro.agregarBD(raiz.hijos[1].texto, "/home/aylin/NetBeansProjects/FISQL/BD/" + raiz.hijos[1].texto);
                            break;
                        case "tabla":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                        case "objeto":
                            ArrayList param1 = (ArrayList) Recorrido(raiz.hijos[3]);
                            RegistroObjeto.agregarObject(raiz.hijos[1].texto, param1);
                            break;
                        case "procedimiento":
                            if (raiz.cantidadHijos == 7) {
                                RegistroProcedure.agregarProcedure(raiz.hijos[1].texto, raiz.hijos[5], vacia, "proc");
                            } else {
                                ArrayList param2 = (ArrayList) Recorrido(raiz.hijos[3]);
                                RegistroProcedure.agregarProcedure(raiz.hijos[1].texto, raiz.hijos[6], param2, "proc");
                            }
                            break;
                        case "funcion":
                            if (raiz.cantidadHijos == 8) {
                                String tipo = Recorrido(raiz.hijos[4]).toString();
                                RegistroProcedure.agregarProcedure(raiz.hijos[1].texto, raiz.hijos[6], vacia, tipo);
                            } else {
                                ArrayList param3 = (ArrayList) Recorrido(raiz.hijos[3]);
                                Object tipo = Recorrido(raiz.hijos[5]);
                                RegistroProcedure.agregarProcedure(raiz.hijos[1].texto, raiz.hijos[7], param3, tipo.toString());
                            }
                            break;
                    }
                    break;

                case "PARAMETROS":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            result = Recorrido(raiz.hijos[0]);
                            ArrayList l = (ArrayList) Recorrido(raiz.hijos[2]);
                            ArrayList r = new ArrayList();
                            r.add(new Parametro(raiz.hijos[1].texto, result.toString(), l));
                            result = r;
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[0]);
                            ArrayList l2 = (ArrayList) Recorrido(raiz.hijos[2]);
                            ArrayList list = (ArrayList) Recorrido(raiz.hijos[3]);
                            list.add(new Parametro(raiz.hijos[1].texto, result.toString(), l2));
                            result = list;
                            break;
                    }
                    break;
                case "PARAMETROSP":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            ArrayList list1 = new ArrayList();
                            result = Recorrido(raiz.hijos[1]);
                            list1.add(new Parametro(raiz.hijos[1].texto, result.toString(), vacia));
                            result = list1;
                            break;
                        case 4:
                            ArrayList list2 = new ArrayList();
                            result = Recorrido(raiz.hijos[1]);

                            if (raiz.hijos[3].texto.equals("COMPLEMENTO")) {
                                ArrayList l2 = (ArrayList) Recorrido(raiz.hijos[3]);
                                list2.add(new Parametro(raiz.hijos[1].texto, result.toString(), l2));
                            } else {
                                list2 = (ArrayList) Recorrido(raiz.hijos[3]);
                                list2.add(new Parametro(raiz.hijos[1].texto, result.toString(), vacia));
                            }
                            result = list2;
                            break;
                        case 5:
                            ArrayList list = (ArrayList) Recorrido(raiz.hijos[4]);
                            result = Recorrido(raiz.hijos[1]);
                            ArrayList l = (ArrayList) Recorrido(raiz.hijos[3]);
                            list.add(new Parametro(raiz.hijos[1].texto, result.toString(), l));
                            result = list;
                            break;
                    }
                    break;
                case "PARAMETROSVAR":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            ArrayList list1 = new ArrayList();
                            list1.add(new Parametro(raiz.hijos[1].texto, Recorrido(raiz.hijos[0]).toString(), vacia));
                            result = list1;
                            break;
                        case 3:
                            ArrayList list = (ArrayList) Recorrido(raiz.hijos[2]);
                            list.add(new Parametro(raiz.hijos[1].texto, Recorrido(raiz.hijos[0]).toString(), vacia));
                            result = list;
                            break;
                    }
                    break;
                case "PARAMETROSVARP":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            ArrayList list1 = new ArrayList();
                            list1.add(new Parametro(raiz.hijos[2].texto, Recorrido(raiz.hijos[1]).toString(), vacia));
                            result = list1;
                            break;
                        case 4:
                            ArrayList list = (ArrayList) Recorrido(raiz.hijos[3]);
                            list.add(new Parametro(raiz.hijos[2].texto, Recorrido(raiz.hijos[1]).toString(), vacia));
                            result = list;
                            break;
                    }
                    break;
                case "ALTERAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "tabla":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                        case "objeto":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "usuario":
                            result = Recorrido(raiz.hijos[1]);
                            break;

                    }
                    break;
                case "TIPOALTERAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "agregar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "quitar":
                            //result = Recorrido(raiz.hijos[2]);
                            break;

                    }
                    break;
                case "ELIMINAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "tabla":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "base_datos":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                        case "objeto":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "usuario":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "DML":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "insertar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "actualizar":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                        case "borrar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "seleccionar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "TIPOINSERTAR":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "LISTACAMPOS":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                case "LISTACAMPOSP":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "LISTAVALORES":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "LISTAVALORESP":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "DONDE":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "SELECCIONAR":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "ORDENAR":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "TIPOORDENAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "asc":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "desc":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "DCL":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "otorgar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "denegar":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "LISTAVARIABLES":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "DECLARAR":
                    switch (raiz.cantidadHijos) {
                        case 6:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "DECLARARP":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "ASIGNACION":
                    switch (raiz.cantidadHijos) {
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "VAR":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "SI":
                    switch (raiz.cantidadHijos) {
                        case 7:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "SINO":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 4:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "SELECCIONA":
                    switch (raiz.cantidadHijos) {
                        case 7:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "CASO":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "TIPOCASO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            break;
                    }
                    break;
                case "PARA":
                    switch (raiz.cantidadHijos) {
                        case 7:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "PARAP":
                    switch (raiz.hijos[0].texto) {
                        case "declarar":
                            break;
                        case "asignacion":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "OPCIONPARA":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            break;
                    }
                    break;
                case "MIENTRAS":
                    switch (raiz.cantidadHijos) {
                        case 7:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "IMPRIMIR":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "CONTAR":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "FUNCION":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "backup":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "restaurar":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "BACKUP":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "usqldump":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "completo":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "RESTAURAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "usqldump":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case "completo":
                            //result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "RETORNAR":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                        case 1:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "LLAMADA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "OBJETO":
                    switch (raiz.cantidadHijos) {
                        case 0:
                            break;
                    }
                    break;
                case "OPERACION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            break;
                    }
                    break;
            }
        }
        return result;
    }

}
