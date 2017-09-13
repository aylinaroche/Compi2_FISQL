package USQL;

public class Nodo {

    public String texto;
    public String valor;
    public int cantidadHijos;
    public Nodo padre; 
    public Nodo hijos[];
    public String tipo; 
    //public Nodo heredado; 

    public Nodo(String texto) {
        this.texto = texto;
        this.valor = texto;
        this.tipo = texto;
        this.cantidadHijos = 0;

    }

    public Nodo(String texto, String tipo) {
        this.texto = texto;
        this.valor = texto;
        this.tipo = tipo;
        this.cantidadHijos = 0;
    }

    public Nodo() {
    }

    public void insertar(Nodo nuevo) {
        if (nuevo == null) {
            return; //si el nodo nuevo es nulo, no se creo en la gramatica y no debe ser agregado por lo tanto
        }
        //Se declara un nodo auxiliar para contener a los hijos
        Nodo aux[] = new Nodo[this.cantidadHijos + 1];

        //se copian los hijos del nodo al auxiliar
        for (int i = 0; i < this.cantidadHijos; i++) {
            aux[i] = this.hijos[i];
            aux[i].padre = this;
        }
        //Se indica el padre del nuevo nodo y se inserta al arreglo de hijos
        nuevo.padre = this;
        aux[this.cantidadHijos] = nuevo;

        //se asigna los hijos en el arreglo auxiliar al Nodo y aumenta el numero de hijos
        this.hijos = aux;
        this.cantidadHijos++;

    }
}
