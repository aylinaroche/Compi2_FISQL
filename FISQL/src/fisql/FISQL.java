package fisql;

import AnalizadorSQL.*;
import BaseDeDatos.RegistroDB;
import BaseDeDatos.RegistroMaestro;
import BaseDeDatos.RegistroObjeto;
import BaseDeDatos.RegistroProcedure;
import BaseDeDatos.RegistroTabla;
import BaseDeDatos.RegistroUsuario;
import USQL.*;
import java.io.IOException;

/**
 *
 * @author aylin
 */
public class FISQL {

    //public static parserSQL parser = null;
    public static void main(String[] args) throws ParseException, IOException, AnalizadorXML.ParseException {
        // try {
        Variables.pilaAmbito.push("Global");
//            RegistroMaestro m = new RegistroMaestro();
//            m.cargarBD();
//            RegistroUsuario u = new RegistroUsuario();
//            u.cargarBD();
//
//            Nodo nodo = parserSQL.compilar(textoSQL1());
//            RecorridoSQL r = new RecorridoSQL();
//            r.Recorrido(nodo);
//            Nodo nodo = parserXML.compilar(textoXML());
//            RecorridoXML r = new RecorridoXML();
//            r.Recorrido(nodo);
        Interfaz i = new Interfaz();
        i.setVisible(true);
        i.textCodigo.setText(textoSQL1());

        //imprimirDatos();
        //       BaseDeDatos.BaseDeDatos.crearArchivos();
//        } catch (CloneNotSupportedException ex) {
//            Logger.getLogger(FISQL.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public static String textoSQL1() {

        String texto = ""
                + "crear base_datos Proyecto1;\n"
                + "crear base_datos db1;\n"
                + "\n"
                + "USAR Proyecto1;"
                + "\n"
                + "crear tabla Curso (\n"
                + "     INTEGER id Llave_Primaria Autoincrementable, \n"
                + "     TEXT Nombre No Nulo);\n"
                + "\n"
                + "crear tabla Estudiante (\n"
                + "     INTEGER id Llave_Primaria Autoincrementable, \n"
                + "     TEXT Nombre No Nulo, \n"
                + "     DATE fh_nac No Nulo,\n "
                + "     BOOL trabaja,\n"
                + "     INTEGER curso Llave_Foranea Curso id nulo);\n"
                + "\n"
                //                + "crear objeto Empresa (INTEGER codigo, TEXT nombre, TEXT telefono);\n"
                //                + "\n"
                //                + "CREAR PROCEDIMIENTO set_Salario (DOUBLE @salario, DOUBLE\n"
                //                + "@comision, INTEGER @codigo){\n"
                //                + "	actualizar tabla Estudiante (salario) VALORES (@salario);\n"
                //                + "}\n"
                //                + "\n"
                //                + "CREAR FUNCION suma (INTEGER @var1, INTEGER @var2) INTEGER{\n"
                //                + "	@var_ejemplo = suma(10,15);\n"
                //                + "	RETORNO @var1 * @var2;\n"
                //                + "}\n"
                //                + "\n"
                //                + ""
                //                + "CREAR USUARIO usuario1 COLOCAR password = \"holaMundo\";"
                //                + "\n"
                //                + "ALTERAR TABLA Estudiante AGREGAR (INTEGER edad, TEXT direccion);\n"
                //                + "ALTERAR TABLA Estudiante QUITAR trabaja;\n"
                //                + "\n"
                //                + "ALTERAR OBJETO empresa AGREGAR (INTEGER nit);\n"
                //                + "ALTERAR OBJETO empresa QUITAR telefono;\n"
                //                + "\n"
                //                + "ALTERAR USUARIO usuario1 CAMBIAR password = \"1234\";\n"
                //                + "\n"
                //                + "ELIMINAR TABLA estudiante;\n"
                //                + "ELIMINAR BASE_DATOS Proyecto1;\n"
                //                + "ELIMINAR OBJETO empresa;\n"
                //                + "ELIMINAR USUARIO usuario1;\n"
                //                + "\n"
                //                + "INSERTAR EN TABLA estudiante (\"Jose\", 10-10-1994,1);\n"
                //                + "INSERTAR EN TABLA estudiante (\"Aylin\", 10-10-1994,1);\n"
                //                + "INSERTAR EN TABLA estudiante (\"Alejandro\", 10-10-1994,1);\n"
                //                + "INSERTAR EN TABLA estudiante (\"Luis\", 10-10-1994,1);\n"
                + "INSERTAR EN TABLA Curso (\"Lenguajes\");\n"
                + "INSERTAR EN TABLA Curso (\"Matematica\");\n"
                + ""
                + "INSERTAR EN TABLA estudiante (\"Juan\", '10-10-1994', 0,1);\n"
                + "INSERTAR EN TABLA estudiante (\"Maria\", '10-10-1996', 0,1);\n"
                + "INSERTAR EN TABLA estudiante (\"Elisa\", '20-10-1994', 0,1);\n"
                + "INSERTAR EN TABLA estudiante (\"Diana\", '10-01-1994', 1,1);\n"
                + "INSERTAR EN TABLA estudiante (\"Sucely\", '11-10-1994', 1,1);\n"
                // + "\n"
                // + "INSERTAR EN TABLA estudiante (Nombre, curso, fh_nac) VALORES (\"Jose Luis Figueroa\", 2, 10-10-1994);\n"
                //+ "\n"
                //                + "ACTUALIZAR TABLA estudiante (nombre, trabaja) VALORES (\"Puan\",1)"
                //                + " DONDE nombre == \"Juan\" && trabaja == 0;\n"
                //                + ""
                //                      + "ACTUALIZAR TABLA estudiante (curso) VALORES (2);\n"
                //                + "\n"
                //  + "SELECCIONAR nombre, fh_nac DE estudiante DONDE id < 10;\n"
                + ""
                                + "	SELECCIONAR curso.nombre, estudiante.nombre \n"
                + "	DE curso, estudiante \n"
                + "	DONDE estudiante.curso == curso.id;"
                // + "BORRAR EN TABLA estudiante DONDE nombre == \"Maria\";\n"
                //                + "&& trabaja == 0;\n"
                //                + "BORRAR EN TABLA estudiante;\n"
                //                + "\n"
                //                + "SELECCIONAR nombre, fh_nac DE estudiante DONDE id > 10 ORDENAR POR\n"
                //                + "fh_nac asc;\n"
                //                //                + "SELECCIONAR * DE estudiante DONDE estudiante.id == \n"
                //                + "asignacion.id_estudiante;\n"
                //                + "SELECCIONAR nombre, fh_nac DE estudiante DONDE id < 10;\n"
                // + "SELECCIONAR * DE estudiante;\n"
                + "SELECCIONAR * DE estudiante DONDE id < 10  ORDENAR POR\n"
                + "fh_nac desc;\n"
                //                + "SELECCIONAR nombre, fh_nac DE estudiante;"
                //                + "SELECCIONAR nombre, fh_nac DE estudiante DONDE id > 3 ORDENAR POR\n"
                //                + "id asc;\n"
                //                + "OTORGAR PERMISOS usuario1, Proyecto1.estudiante;\n"
                //                + "OTORGAR PERMISOS usuario1, Proyecto1.curso;\n"
                //                + "OTORGAR PERMISOS usuario1, Proyecto1.*;\n"
                //                + "\n"
                //                + "DENEGAR PERMISOS usuario1, Proyecto1.estudiante;\n"
                //                + "DENEGAR PERMISOS usuario1, Proyecto1.*;\n"
                //                + "\n"
                //                + "CREAR PROCEDIMIENTO set_Salario (DOUBLE @salario, DOUBLE\n"
                //                + "@comision, INTEGER @codigo){\n"
                //                + "\n"
                //                //                + "	DECLARAR @variable1, @variable2 INTEGER = 23;\n"
                //                //                + "	DECLARAR @ejemplo TEXT = \"Hola mundo\";\n"
                //                + "	DECLARAR @empresa1 Empresa;\n"
                //                + "	DECLARAR @empresa2 Empresa;\n"
                //                //                + "	DECLARAR @varBool BOOL = !(falso);\n"
                //                + "     declarar @a, @b INTEGER = 5 ;"
                //                + "\n"
                //                // + "	@a = 3 * 23;\n"
                //                //+ "	@ejemplo = \"Hola\";\n"
                //                + "\n"
                //                + "	@empresa1.telefono = 11111111;\n"
                //                + "	@empresa2.telefono = 22222222;\n"
                //                + "	imprimir(\" EMPRESA1 = \" + @empresa1.telefono);\n"
                //                + "	imprimir(\" EMPRESA2 = \" + @empresa2.telefono);\n"
                //                + "     @a = suma(5,4);"
                //                + "	imprimir(\" MULT = \" + @a);\n"
                //                //   + "suma(5,4);"
                //                + "\n"
                //                + "	SI( @a > 10 ){\n"
                //                + "		@b = 23;\n"
                //                + "	}\n"
                //                + "\n"
                //                + "	SI( @a < 10 ){\n"
                //                + "		@a = 2;\n"
                //                + "	}SINO{\n"
                //                + "		@a = 1;\n"
                //                + "	}\n"
                //                
                //                + "	DECLARAR @date DATE = 05-10-2010;\n"
                //                + "	DECLARAR @dateTime DATETIME = 20-09-2011 02:01:20;\n"
                //                + "\n"
                //                + "	SELECCIONA ( @a * 15 ){\n"
                //                + "		CASO 10 :\n"
                //                + "			@b = 23;\n"
                //                + "		CASO 5 :\n"
                //                + "			@b = 50;\n"
                //                + "		DEFECTO :\n"
                //                + "			@b = 30;\n"
                //                + "	}\n"
                //                + "\n"
                //                + "	PARA(DECLARAR @i INTEGER = 10; @i > 0; --){\n"
                //                + "         IMPRIMIR(\"PARA= \" + @i);\n"
                //                + "	}\n"
                // + "\n"
                //                + "	MIENTRAS( @a < 5 ){\n"
                //                + "             IMPRIMIR(\"MIENTRAS= \" + @a);\n"
                //                + "		@a = @a + 1;\n"
                //                + "		DETENER;\n"
                //                + "             IMPRIMIR(\"MIENTRAS= \" + @a);\n"
                //                + "	}\n"
                //                + "\n"
                //                + "	imprimir(\"Fecha = \"+ FECHA());\n"
                //                + "	imprimir(\"Hora = \"+ FECHA_HORA());\n"
                //                + "	DECLARAR @fecha1 Date = '10-02-2010';\n"
                //                + "	DECLARAR @fecha2 Date = '20-02-2010';\n"
                //                + "	DECLARAR @fecha3 Date = '10-02-2011';\n"
                //                + "	SI( @fecha1 > @fecha2 || @fecha1 < @fecha2 ||@fecha1 >= @fecha2 ||@fecha1 <= @fecha2|| @fecha1 ==  @fecha2  ||@fecha1 != @fecha2   ){\n"
                //                + "	imprimir(\"Fecha = \"+ FECHA());\n"
                //                + "	}\n"
                //                + "	BACKUP USQLDUMP Proyecto1 Proyecto1_backup;\n"
                //                + "	BACKUP COMPLETO Proyecto1 Proyecto1_backup2;\n"
                //                + "\n"
                //                + "	RESTAURAR USQLDUMP \"ruta_archivo\";\n"
                //                + "	RESTAURAR COMPLETO \"/home/usuario/backups/backup_prueba.zip\";\n"
                //                + "	\n"
                //                + "	CONTAR(<<SELECCIONAR * DE estudiante>>);\n"
                // + "}"
                + ""
                //   + "	Set_Salario(8500.00,964.50,1);\n"
                + ""
                + "";
        return texto;
    }

    public static String textoSQL2() {

        String texto = "CREAR BASE_DATOS Ejemplo1;\n"
                + "USAR Ejemplo1;\n"
                + "\n"
                + "CREAR OBJETO Direccion(INTEGER avenida, INTEGER calle, TEXT nombre, TEXT descripcion);\n"
                + "\n"
                + "CREAR TABLA Estudiante ( INTEGER id Llave_Primaria Autoincrementable, TEXT Nombre No Nulo,\n"
                + "DATE fh_nac No Nulo, BOOL trabaja, Direccion direccion);\n"
                + "\n"
                + "CREAR TABLA Curso( INTEGER id Llave_Primaria Autoincrementable,TEXT Nombre No Nulo,\n"
                + "INTEGER creditos No Nulo);\n"
                + "\n"
                + "CREAR TABLA Asignacion( INTEGER id Llave_Primaria Autoincrementable, DATETIME fh_Asignacion No Nulo,INTEGER id_estudiante Llave_Foranea Estudiante,\n"
                + "INTEGER id_curso Llave_Foranea Curso);\n"
                + "\n"
                + "CREAR PROCEDIMIENTO Asignar (INTEGER @id_e, INTEGER @id_c){\n"
                + "\n"
                + "	DECLARAR @estudiante INTEGER= CONTAR(<<SELECCIONAR * DE estudiante DONDE id == @id_e >>);\n"
                + "\n"
                + "	DECLARAR @curso INTEGER= CONTAR(<<SELECCIONAR * DE curso DONDE id == @id_c >>);\n"
                + "	\n"
                + "	SI (@curso > 0){\n"
                + "		SI(@estudiante > 0){\n"
                + "			INSERTAR EN TABLA asignacion(id_estudiante, id_curso) \n"
                + "			VALORES (@id_e, @id_c);\n"
                + "		}SINO{\n"
                + "			IMPRIMIR(\"El usuario ingresado no existe\");\n"
                + "		}\n"
                + "	}SINO{\n"
                + "			IMPRIMIR(\"El curso ingresado no existe\");\n"
                + "	}\n"
                + "}\n"
                + "\n"
                + "CREAR FUNCION crearDireccion (INTEGER @calle, INTEGER @avenida, TEXT @nombre, TEXT @descripcion) Direccion{\n"
                + "	\n"
                + "	DECLARAR @direc DIRECCION;\n"
                + "	@direc.calle = @calle;\n"
                + "	@direc.avenida = @avenida;\n"
                + "	@direc.nombre = @nombre;\n"
                + "	@direc.descripcion = @descripcion;\n"
                + "	RETORNO @direc;\n"
                + "}";
        return texto;
    }

    public static String textoSQL3() {

        String texto = "CREAR BASE_DATOS Ejemplo2;\n"
                + "\n"
                + "USAR Ejemplo2;\n"
                + "\n"
                + "CREAR TABLA Proveedor(\n"
                + "	INTEGER id Llave_Primaria Autoincrementable,\n"
                + "	TEXT nombre No Nulo,\n"
                + "	TEXT direccion No Nulo\n"
                + ");\n"
                + "CREAR TABLA Producto(\n"
                + "	INTEGER id Llave_Primaria Autoincrementable,\n"
                + "	TEXT nombre No Nulo,\n"
                + "	DOUBLE precio No Nulo,\n"
                + "	INTEGER id_proveedor Llave_Foranea Proveedor\n"
                + ");\n"
                + "CREAR PROCEDIMIENTO llenarTablas(){\n"
                + "	DECLARAR @producto INTEGER = 0;\n"
                + "	#inserta 10 registros en la tabla proveedor\n"
                + "	\n"
                + "	PARA(DECLARAR @i INTEGER = 0; @i < 10; ++){\n"
                + "		INSERTAR EN TABLA Proveedor(id, nombre, direccion)\n"
                + "		VALORES (@i, \"Proveedor\" + @i, \"Direccion\" + @i);\n"
                + "		#inserta 20 productos por proveedor\n"
                + "		\n"
                + "		PARA(DECLARAR @j INTEGER = 0; @j < 20; ++){\n"
                + "			DECLARAR @precio DOUBLE = generarPrecio(@producto);\n"
                + "			INSERTAR EN TABLA Producto\n"
                + "			(@producto, \"Producto\" + @producto, @precio, @i);\n"
                + "			@producto = @producto + 1;\n"
                + "		}\n"
                + "	}\n"
                + "}\n"
                + "\n"
                + "CREAR FUNCION generarPrecio(INTEGER @a) INTEGER{\n"
                + "	DECLARAR @retorno DOUBLE = 1.5;\n"
                + "	RETORNO @retorno * @a;\n"
                + "}\n"
                + "\n"
                + "#llenar las tablas\n"
                + "llenarTablas();\n"
                + "#imprimir cantidad de registros de la tabla Producto\n"
                + "\n"
                + "IMPRIMIR(contar(<<SELECCIONAR * DE Producto>>));\n"
                + "#el resultado de la instrucción debería ser:\n"
                + "#200\n"
                + "#borrar todos los productos del proveedor con id 1\n"
                + "BORRAR EN TABLA Producto DONDE id_proveedor == 1;\n"
                + "#imprimir cantidad de registros de la tabla Producto\n"
                + "IMPRIMIR(contar(<<SELECCIONAR * DE Producto>>));";
        return texto;
    }

    public static String textoXML() {
        String texto = "";
        texto = "<procedure>\n"
                + "  \"/home/aylin/NetBeansProjects/FISQL/BD/Proyecto1/proc.xml\"\n"
                + "</procedure>\n"
                + "<object>\n"
                + "  \"/home/aylin/NetBeansProjects/FISQL/BD/Proyecto1/obj.xml\"\n"
                + "</object>\n"
                + "\n"
                + "<tabla>\n"
                + "  <nombre>curso</nombre>\n"
                + "  <path>\"/home/aylin/NetBeansProjects/FISQL/BD/Proyecto1/Curso.xml\"</path>\n"
                + "  <campo>\n"
                + "      <integer>id</integer>\n"
                + "      <complemento>autoincrementable</complemento>\n"
                + "      <complemento>llave_primaria</complemento>\n"
                + "  </campo>\n"
                + "  <campo>\n"
                + "      <text>Nombre</text>\n"
                + "      <complemento>no nulo</complemento>\n"
                + "  </campo>\n"
                + "</tabla>\n"
                + "\n"
                + "<tabla>\n"
                + "  <nombre>estudiante</nombre>\n"
                + "  <path>\"/home/aylin/NetBeansProjects/FISQL/BD/Proyecto1/Estudiante.xml\"</path>\n"
                + "  <campo>\n"
                + "      <integer>id</integer>\n"
                + "      <complemento>autoincrementable</complemento>\n"
                + "      <complemento>llave_primaria</complemento>\n"
                + "  </campo>\n"
                + "  <campo>\n"
                + "      <text>Nombre</text>\n"
                + "      <complemento>no nulo</complemento>\n"
                + "  </campo>\n"
                + "  <campo>\n"
                + "      <date>fh_nac</date>\n"
                + "      <complemento>no nulo</complemento>\n"
                + "  </campo>\n"
                + "  <campo>\n"
                + "      <bool>trabaja</bool>\n"
                + "  </campo>\n"
                + "  <campo>\n"
                + "      <integer>curso</integer>\n"
                + "      <complemento>nulo</complemento>\n"
                + "      <complemento>\n"
                + "          <foranea>\n"
                + "              <nombre>curso</nombre>\n"
                + "              <atributo>id</atributo>\n"
                + "          </foranea>\n"
                + "      </complemento>\n"
                + "  </campo>\n"
                + "</tabla>\n"
                + "";
        return texto;
    }

    public static void imprimirDatos() {
        RegistroMaestro.imprimir();
        RegistroProcedure.imprimir();
        RegistroObjeto.imprimir();
        RegistroDB.imprimir();
        RegistroTabla.imprimir();
        RegistroUsuario.imprimir();
        Variables.imprimirVariables();
    }

}
