package USQL;

import USQL.Objetos.Parametro;
import BaseDeDatos.*;
import static USQL.Variables.nivelAmbito;
import static USQL.Variables.pilaAmbito;
import java.util.ArrayList;

/**
 *
 * @author aylin
 */
public class RecorridoSQL {

    public static ArrayList parametros = new ArrayList();
    public static ArrayList vacia = new ArrayList();
    public static String valorSwitch;
    public static Boolean retornar = false, continuar = false, salir = false;

    public Object Recorrido(Nodo raiz) throws CloneNotSupportedException {
        Object result = null;
        // Nodo nodo = null;f
        if (raiz != null) {
            switch (raiz.texto) {
                case "INICIO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "PAQUETE":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "SENTENCIA":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[0]);
                            if (salir == false && continuar == false && retornar == false) {
                                result = Recorrido(raiz.hijos[1]);
                            }
                            break;
                    }

                    break;
                case "SENTENCIAP":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[0]);
                            if (salir == false && continuar == false && retornar == false) {
                                result = Recorrido(raiz.hijos[1]);
                            }
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
                                    salir = true;
                                    break;
                                case "CONTAR":
                                    break;
                                default: //LLAMADA
                                    break;
                            }
                            break;
                        case 3:
                            switch (raiz.hijos[0].texto.toLowerCase()) {
                                case "retorno":
                                    result = Recorrido(raiz.hijos[1]);
                                    retornar = true;
                                    break;
                                default: //llamada
                                    result = Recorrido(raiz.hijos[1]);
                                    RegistroProcedure p = new RegistroProcedure();
                                    result = p.ejecutarProcedure(raiz.hijos[0].texto, (ArrayList) result);
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
                        case 1:
                            ArrayList l2 = new ArrayList();
                            l2.add(raiz.hijos[0].texto.toLowerCase());
                            result = l2;
                            break;
                        case 2:
                            ArrayList l3 = (ArrayList) Recorrido(raiz.hijos[1]);
                            l3.add(raiz.hijos[0].texto.toLowerCase());
                            result = l3;
                            break;
                        case 3:
                            ArrayList l4 = new ArrayList();
                            l4.add(new Parametro(raiz.hijos[1].texto.toLowerCase(), raiz.hijos[2].texto.toLowerCase()));
                            result = l4;
                            break;
                        case 4:
                            ArrayList l5 = (ArrayList) Recorrido(raiz.hijos[3]);
                            l5.add(new Parametro(raiz.hijos[1].texto.toLowerCase(), raiz.hijos[2].texto.toLowerCase()));
                            result = l5;
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
                            ArrayList param = (ArrayList) Recorrido(raiz.hijos[3]);
                            RegistroDB.agregarTabla(raiz.hijos[1].texto, BaseDeDatos.voltear(param));
                            break;
                        case "objeto":
                            ArrayList param1 = (ArrayList) Recorrido(raiz.hijos[3]);
                            RegistroObjeto.agregarObjeto(raiz.hijos[1].texto, param1);
                            break;
                        case "procedimiento":
                            if (raiz.cantidadHijos == 6) {
                                RegistroProcedure.agregarProcedure(raiz.hijos[1].texto, raiz.hijos[4], vacia, "proc");
                            } else {
                                ArrayList param2 = (ArrayList) Recorrido(raiz.hijos[3]);
                                RegistroProcedure.agregarProcedure(raiz.hijos[1].texto, raiz.hijos[5], param2, "proc");
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
                        case "usuario":
                            String pass = raiz.hijos[3].texto.replace("\"", "");
                            RegistroUsuario.agregarUsuario(raiz.hijos[1].texto, pass);
                            break;
                    }
                    break;

                case "PARAMETROS":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Recorrido(raiz.hijos[0]);
                            ArrayList r = new ArrayList();
                            r.add(new Parametro(raiz.hijos[1].texto, result.toString(), vacia));
                            result = r;
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[0]);
                            ArrayList l = (ArrayList) Recorrido(raiz.hijos[2]);
                            ArrayList r2 = new ArrayList();
                            if (raiz.hijos[2].texto.equals("COMPLEMENTO")) {
                                r2.add(new Parametro(raiz.hijos[1].texto, result.toString(), l));
                                result = r2;
                            } else {
                                l.add(new Parametro(raiz.hijos[1].texto, result.toString(), vacia));
                                result = l;
                            }
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
                            list1.add(new Parametro(raiz.hijos[2].texto, result.toString(), vacia));
                            result = list1;
                            break;
                        case 4:
                            ArrayList list2 = new ArrayList();
                            result = Recorrido(raiz.hijos[1]);

                            if (raiz.hijos[3].texto.equals("COMPLEMENTO")) {
                                ArrayList l2 = (ArrayList) Recorrido(raiz.hijos[3]);
                                list2.add(new Parametro(raiz.hijos[2].texto, result.toString(), l2));
                            } else {
                                list2 = (ArrayList) Recorrido(raiz.hijos[3]);
                                list2.add(new Parametro(raiz.hijos[2].texto, result.toString(), vacia));
                            }
                            result = list2;
                            break;
                        case 5:
                            ArrayList list = (ArrayList) Recorrido(raiz.hijos[4]);
                            result = Recorrido(raiz.hijos[1]);
                            ArrayList l = (ArrayList) Recorrido(raiz.hijos[3]);
                            list.add(new Parametro(raiz.hijos[2].texto, result.toString(), l));
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
                            result = Recorrido(raiz.hijos[2]);
                            RegistroDB.alterarTabla(raiz.hijos[1].texto, raiz.hijos[2].hijos[0].texto, (ArrayList) result);
                            break;
                        case "objeto":
                            result = Recorrido(raiz.hijos[2]);
                            RegistroObjeto.alterarObjeto(raiz.hijos[1].texto, raiz.hijos[2].hijos[0].texto, (ArrayList) result);
                            break;
                        case "usuario":
                            RegistroUsuario.alterarUsuario(raiz.hijos[1].texto, raiz.hijos[3].texto.replace("\"", ""));
                            break;

                    }
                    break;
                case "TIPOALTERAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "agregar":
                            result = Recorrido(raiz.hijos[2]);
                            break;
                        case "quitar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "ELIMINAR":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "tabla":
                            RegistroDB.eliminarTabla(raiz.hijos[1].texto);
                            break;
                        case "base_datos":
                            RegistroMaestro.eliminarBD(raiz.hijos[1].texto);
                            break;
                        case "objeto":
                            RegistroObjeto.eliminarObjeto(raiz.hijos[1].texto);
                            break;
                        case "usuario":
                            RegistroUsuario.eliminarUsuario(raiz.hijos[1].texto);
                            break;
                    }
                    break;
                case "DML":
                    switch (raiz.hijos[0].texto.toLowerCase()) {
                        case "insertar":
                            switch (raiz.cantidadHijos) {
                                case 7: //Insertar Normal
                                    result = Recorrido(raiz.hijos[4]);
                                    RegistroTabla.insertarRegistro(raiz.hijos[2].texto, (ArrayList) result);
                                    break;
                                case 8: //Insertar especial
                                    result = ObtenerID(raiz.hijos[4]);
                                    Object obj = Recorrido(raiz.hijos[6]);
                                    RegistroTabla.insertarEspecial(raiz.hijos[2].texto, (ArrayList) result, (ArrayList) obj);
                                    break;
                            }
                            break;
                        case "actualizar":
                            ArrayList campos = (ArrayList) Recorrido(raiz.hijos[4]);
                            ArrayList valores = (ArrayList) Recorrido(raiz.hijos[6]);
                            switch (raiz.cantidadHijos) {
                                case 9:
                                    RegistroTabla.actualizarTabla(raiz.hijos[2].texto, campos, valores, null);
                                    break;
                                case 10:
                                    RegistroTabla.actualizarTabla(raiz.hijos[2].texto, campos, valores, raiz.hijos[8]);
                                    break;
                            }
                            break;
                        case "borrar":
                            RegistroTabla.borrarEnTabla(raiz.hijos[2].texto, raiz.hijos[3]);
                            break;
                        case "seleccionar":
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "TIPOINSERTAR":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "LISTACAMPOS":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            ArrayList l = new ArrayList();
                            l.add(raiz.hijos[0].texto);
                            result = l;
                            break;
                        case 2:
                            if (raiz.hijos[1].texto.equals("LISTACAMPOSP")) {
                                ArrayList l3 = (ArrayList) Recorrido(raiz.hijos[1]);
                                result = BaseDeDatos.voltearAgregar(raiz.hijos[0].texto, l3);
                            } else {
                                result = Recorrido(raiz.hijos[1]);
                                result = raiz.hijos[0].texto + result.toString();
                            }
                            break;
                        case 3:
                            ArrayList l3 = (ArrayList) Recorrido(raiz.hijos[2]);
                            result = Recorrido(raiz.hijos[1]);
                            result = BaseDeDatos.voltearAgregar(raiz.hijos[0].texto + result.toString(), l3);
                            break;
                    }
                    break;
                case "LISTACAMPOSP":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            ArrayList l1 = new ArrayList();
                            l1.add(raiz.hijos[1].texto);
                            result = l1;
                            break;
                        case 3:
                            if (raiz.hijos[1].texto.equals("LISTACAMPOSP")) {
                                ArrayList l2 = (ArrayList) Recorrido(raiz.hijos[2]);
                                l2.add(raiz.hijos[2].texto);
                                result = l2;
                            } else {
                                ArrayList l3 = new ArrayList();
                                result = Recorrido(raiz.hijos[2]);
                                l3.add(raiz.hijos[0].texto + result.toString());
                                result = l3;
                            }
                            break;
                        case 4:
                            ArrayList l2 = (ArrayList) Recorrido(raiz.hijos[3]);
                            result = Recorrido(raiz.hijos[2]);
                            l2.add(raiz.hijos[1].texto + result.toString());
                            result = l2;
                            break;
                    }
                    break;
                case "LISTAVALORES":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            ArrayList l1 = new ArrayList();
                            l1.add(Recorrido(raiz.hijos[0]));
                            result = l1;
                            break;
                        case 2:
                            result = Recorrido(raiz.hijos[0]);
                            ArrayList l3 = (ArrayList) Recorrido(raiz.hijos[1]);
                            result = BaseDeDatos.voltearAgregar(result, l3);
                            break;
                    }
                    break;
                case "LISTAVALORESP":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            ArrayList l1 = new ArrayList();
                            l1.add(Recorrido(raiz.hijos[1]));
                            result = l1;
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            ArrayList l3 = (ArrayList) Recorrido(raiz.hijos[2]);
                            l3.add(result);
                            result = l3;
                            break;
                    }
                    break;
                case "DONDE":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "SELECCIONAR":
                    if (raiz.hijos[0].texto.equals("*")) {
                        switch (raiz.cantidadHijos) {
                            case 3:
                                result = RegistroTabla.seleccionar("*", raiz.hijos[2].texto, null, vacia);
                                break;
                            case 4:
                                if (raiz.hijos[3].texto.equals("DONDE")) {
                                    result = RegistroTabla.seleccionar("*", raiz.hijos[2].texto, raiz.hijos[3], vacia);
                                } else { //Ordenar
                                    Object ordenar = Recorrido(raiz.hijos[3]);
                                    result = RegistroTabla.seleccionar("*", raiz.hijos[2].texto, null, (ArrayList) ordenar);
                                }
                                break;
                            case 5:
                                Object ordenar = Recorrido(raiz.hijos[4]);
                                result = RegistroTabla.seleccionar("*", raiz.hijos[2].texto, raiz.hijos[3], (ArrayList) ordenar);
                                break;
                        }
                        break;
                    } else {
                        result = Recorrido(raiz.hijos[0]);
                        ArrayList ids = (ArrayList) Recorrido(raiz.hijos[2]);
                        if (ids.size() == 1) {
                            String id = (String) ids.get(0);
                            switch (raiz.cantidadHijos) {
                                case 3:
                                    result = RegistroTabla.seleccionar(result, id, null, vacia);
                                    break;
                                case 4:
                                    if (raiz.hijos[3].texto.equals("DONDE")) {
                                        result = RegistroTabla.seleccionar(result, id, raiz.hijos[3], vacia);
                                    } else { //Ordenar
                                        Object ordenar = Recorrido(raiz.hijos[3]);
                                        result = RegistroTabla.seleccionar(result, id, null, (ArrayList) ordenar);
                                    }
                                    break;
                                case 5:
                                    Object ordenar = Recorrido(raiz.hijos[4]);
                                    result = RegistroTabla.seleccionar(result, id, raiz.hijos[3], (ArrayList) ordenar);
                                    break;
                            }
                            break;
                        } else {
                            System.out.println("Varios hijos");
                            switch (raiz.cantidadHijos) {
                                case 3:
                                    result = RegistroTabla.seleccionarVarias((ArrayList) result, ids, null, vacia);
                                    break;
                                case 4:
                                    if (raiz.hijos[3].texto.equals("DONDE")) {
                                        result = RegistroTabla.seleccionarVarias((ArrayList) result, ids, raiz.hijos[3], vacia);
                                    } else { //Ordenar
                                        Object ordenar = Recorrido(raiz.hijos[3]);
                                        result = RegistroTabla.seleccionarVarias((ArrayList) result, ids, null, (ArrayList) ordenar);
                                    }
                                    break;
                                case 5:
                                    Object ordenar = Recorrido(raiz.hijos[4]);
                                    result = RegistroTabla.seleccionarVarias((ArrayList) result, ids, raiz.hijos[3], (ArrayList) ordenar);
                                    break;
                            }
                        }
                    }
                case "ORDENAR":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            ArrayList l = new ArrayList();
                            l.add(raiz.hijos[1].texto);
                            l.add(Recorrido(raiz.hijos[2]));
                            result = l;
                            break;
                    }
                    break;
                case "TIPOORDENAR":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = raiz.hijos[0].texto;
                            break;
                    }
                    break;
                case "DCL":
                    switch (raiz.cantidadHijos) {
                        case 8:
                            switch (raiz.hijos[0].texto.toLowerCase()) {
                                case "otorgar":
                                    result = Recorrido(raiz.hijos[6]);
                                    RegistroUsuario.otorgarPermiso(raiz.hijos[2].texto, raiz.hijos[4].texto, result.toString());
                                    break;
                                case "denegar":
                                    result = Recorrido(raiz.hijos[6]);
                                    RegistroUsuario.denegarPermiso(raiz.hijos[2].texto, raiz.hijos[4].texto, result.toString());
                                    break;
                            }
                            break;
                    }
                    break;
                case "LISTAVARIABLES":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            ArrayList l = new ArrayList();
                            l.add(raiz.hijos[1].texto);
                            result = l;
                            break;
                        case 3:
                            ArrayList l2 = (ArrayList) Recorrido(raiz.hijos[2]);
                            l2.add(raiz.hijos[1].texto);
                            result = l2;
                            break;
                    }
                    break;
                case "DECLARAR":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            Variables.crearVariable(Recorrido(raiz.hijos[2]).toString(), raiz.hijos[1].texto);
                            break;
                        case 5:
                            if (raiz.hijos[2].texto.equals("LISTAVARIABLES")) {
                                ArrayList vars = (ArrayList) Recorrido(raiz.hijos[2]);
                                String t = Recorrido(raiz.hijos[3]).toString();
                                vars.add(raiz.hijos[1].texto);
                                for (int i = 0; i < vars.size(); i++) {
                                    Variables.crearVariable(Recorrido(raiz.hijos[2]).toString(), vars.get(i).toString());
                                }
                            } else {
                                String t = Recorrido(raiz.hijos[2]).toString();
                                result = Recorrido(raiz.hijos[3]);
                                Variables.crearVariable(t, raiz.hijos[1].texto, result);
                            }

                            break;
                        case 6:
                            ArrayList vars = (ArrayList) Recorrido(raiz.hijos[2]);
                            String t = Recorrido(raiz.hijos[3]).toString();
                            vars.add(raiz.hijos[1].texto);
                            result = Recorrido(raiz.hijos[4]);
                            for (int i = 0; i < vars.size(); i++) {
                                Variables.crearVariable(t, vars.get(i).toString(), result);
                            }
                            break;
                    }
                    break;
                case "DECLARARP":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "ASIGNACION":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            result = Recorrido(raiz.hijos[2]);
                            Variables.asignarValor(raiz.hijos[0].texto, result);
                            break;
                        case 5:
                            result = Recorrido(raiz.hijos[3]);
                            Variables.asignarAtributo(raiz.hijos[0].texto, Recorrido(raiz.hijos[1]).toString(), result);
                            break;
                    }
                    break;
                case "VAR":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = raiz.hijos[1].texto;
                            break;
                    }
                    break;
                case "SI":
                    pilaAmbito.push("if");
                    nivelAmbito += 1;
                    switch (raiz.cantidadHijos) {
                        case 7:
                            try {
                                Boolean cond = (Boolean) Recorrido(raiz.hijos[2]);
                                if (cond == true) {
                                    result = Recorrido(raiz.hijos[5]);
                                }
                            } catch (Exception e) {
                                System.out.println("Error al castear bool = " + e);
                            }
                            break;
                        case 8:
                            Boolean cond2 = (Boolean) Recorrido(raiz.hijos[2]);
                            if (cond2 == true) {
                                result = Recorrido(raiz.hijos[5]);
                            } else {
                                result = Recorrido(raiz.hijos[7]);
                            }
                            break;
                    }
                    Variables.eliminarVariables();
                    pilaAmbito.pop();
                    nivelAmbito -= 1;
                    break;
                case "SINO":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            result = Recorrido(raiz.hijos[2]);
                            break;
                    }
                    break;
                case "SELECCIONA":
                    pilaAmbito.push("switch");
                    nivelAmbito += 1;
                    switch (raiz.cantidadHijos) {
                        case 5: //Ningun caso
                            break;
                        case 6:
                            valorSwitch = Recorrido(raiz.hijos[2]).toString();
                            result = Recorrido(raiz.hijos[4]);
                            break;
                    }
                    pilaAmbito.pop();
                    Variables.eliminarVariables();
                    nivelAmbito -= 1;
                    salir = false;
                    break;
                case "CASO":
                    switch (raiz.cantidadHijos) {
                        case 4:
                            String val = Recorrido(raiz.hijos[1]).toString();
                            result = "false";
                            if (val.equals(valorSwitch)) {
                                Recorrido(raiz.hijos[3]);
                                result = "true";
                            }
                            break;
                        case 5:
                            if (raiz.hijos[4].texto.equals("CASO")) {
                                result = Recorrido(raiz.hijos[4]).toString();
                                if (result.equals("false")) {
                                    val = Recorrido(raiz.hijos[1]).toString();
                                    if (val.equals(valorSwitch)) {
                                        Recorrido(raiz.hijos[3]);
                                        result = "true";
                                    }
                                }
                            } else {
                                val = Recorrido(raiz.hijos[1]).toString();
                                if (val.equals(valorSwitch)) {
                                    Recorrido(raiz.hijos[3]);
                                    result = "true";
                                } else {
                                    Recorrido(raiz.hijos[4]);
                                    result = "true";
                                }
                            }
                            break;
                        case 6:
                            result = Recorrido(raiz.hijos[4]).toString();
                            if (result.equals("false")) {
                                val = Recorrido(raiz.hijos[1]).toString();
                                if (val.equals(valorSwitch)) {
                                    Recorrido(raiz.hijos[3]);
                                    result = "true";
                                    return result;
                                }
                            }
                            Recorrido(raiz.hijos[5]);
                            break;
                    }
                    break;
                case "DEFECTO":
                    Recorrido(raiz.hijos[2]);
                    break;
                case "TIPOCASO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = raiz.hijos[0].texto;
                            break;
                    }
                    break;
                case "PARA":
                    pilaAmbito.push("For");
                    Boolean f = false;
                    switch (raiz.cantidadHijos) {
                        case 9:
                            Recorrido(raiz.hijos[2]);
                            nivelAmbito += 1;

                            String condicion = Recorrido(raiz.hijos[3]).toString();
                            f = "true".equals(condicion);
                            int limite = 0;
                            String var = "";
                            while (f) {
                                salir = false;
                                continuar = false;
                                if (limite == 0) {
                                    var = Variables.listaVariables.get(Variables.listaVariables.size() - 1).nombre;
                                }
                                result = Recorrido(raiz.hijos[7]);//accion
                                int i = (int) Recorrido(raiz.hijos[5]); //i++
                                Variables.incrementar(var, i);
                                condicion = Recorrido(raiz.hijos[3]).toString();
                                f = "true".equals(condicion);
                                limite += 1;
                                if (limite == 1000) {
                                    System.out.println("Limite!");
                                    break;
                                }
                                if (salir == true || retornar == true) {
                                    f = false;
                                }
                                Variables.eliminarVariables();
                            }
                            salir = false;
                            continuar = false;
                            Variables.eliminarVariables();
                            pilaAmbito.pop();
                            nivelAmbito -= 1;

                            break;
                    }
                    break;
                case "PARAP":
                    switch (raiz.hijos[0].texto) {
                        case "DECLARAR":
                            result = Recorrido(raiz.hijos[0]);
                            break;
                        case "ASIGNACION":
                            result = Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "OPCIONPARA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            int valor = 0;
                            if (raiz.hijos[0].texto.equals("+")) {
                                valor = 1;
                            } else {
                                valor = -1;
                            }
                            result = valor;
                            break;
                    }
                    break;
                case "MIENTRAS":
                    pilaAmbito.push("Mientras");
                    nivelAmbito += 1;
                    switch (raiz.cantidadHijos) {
                        case 6:
                            boolean w = false;
                            result = Recorrido(raiz.hijos[2]);
                            w = result.toString().equals("true");
                            while (w) {
                                continuar = false;
                                Recorrido(raiz.hijos[4]);
                                result = Recorrido(raiz.hijos[2]);
                                w = result.toString().equals("true");
                                if (w == false) {
                                    break;
                                }
                                if (salir == true || retornar == true) {
                                    w = false;
                                }
                            }
                            break;
                    }
                    salir = false;
                    continuar = false;
                    pilaAmbito.pop();
                    Variables.eliminarVariables();
                    nivelAmbito -= 1;

                    break;

                case "IMPRIMIR":
                    switch (raiz.cantidadHijos) {
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            if (result != null) {
                                BaseDeDatos.listaImprimir.add(" : " + result.toString());
                                System.out.println(result.toString());
                            }
                            break;
                    }
                    break;
                case "CONTAR":
                    result = 0;
                    switch (raiz.cantidadHijos) {
                        case 4:
                            result = Recorrido(raiz.hijos[3]);
                            try {
                                ArrayList l = (ArrayList) result;
                                result = l.size();
                            } catch (Exception e) {
                            }
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
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "LLAMADA":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                        case 3:
                            result = Recorrido(raiz.hijos[1]);
                            break;
                    }
                    break;
                case "OBJETO":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = raiz.hijos[0].texto;
                            break;
                    }
                    break;
                case "OPERACION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = Recorrido(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "E":
                    result = Operacion.resolverOperacion(raiz);
                    break;
            }
        }
        return result;
    }

    public Object ObtenerID(Nodo raiz) {
        Object result = null;

        if (raiz != null) {
            switch (raiz.texto) {
                case "LISTAVALORES":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            ArrayList l1 = new ArrayList();
                            l1.add(raiz.hijos[0].texto);
                            result = l1;
                            break;
                        case 2:
                            result = ObtenerID(raiz.hijos[0]);
                            ArrayList l3 = (ArrayList) ObtenerID(raiz.hijos[1]);
                            result = BaseDeDatos.voltearAgregar(result, l3);
                            break;
                    }
                    break;
                case "LISTAVALORESP":
                    switch (raiz.cantidadHijos) {
                        case 2:
                            ArrayList l1 = new ArrayList();
                            l1.add(ObtenerID(raiz.hijos[1]));
                            result = l1;
                            break;
                        case 3:
                            ArrayList l3 = (ArrayList) ObtenerID(raiz.hijos[2]);
                            l3.add(ObtenerID(raiz.hijos[1]));
                            result = l3;
                            break;
                    }
                    break;
                case "OPERACION":
                    switch (raiz.cantidadHijos) {
                        case 1:
                            result = ObtenerID(raiz.hijos[0]);
                            break;
                    }
                    break;
                case "E":
                    result = raiz.hijos[0].texto;
                    break;
            }

        }
        return result;
    }
}
