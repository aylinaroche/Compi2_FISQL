package fisql;

public class Nodo {

    public String texto; //texto del token o No Terminal
    public String valor; //atributo valor que toman los tokens y que iran tomando los no terminales al sintetizar
    public int numHijos; //entero auxiliar para saber el numero de hijos de un Nodo
    public Nodo padre; //Nodo padre
    public Nodo hijos[]; //arreglo de hijos
    public String tipo; //tipo de los terminales de la produccion F
    //Atributos que no son utilizados 
    public Nodo nodoHeredado; //hermanos o padre

    public Nodo(String texto) {
        this.texto = texto;
        this.valor = texto;
        this.tipo = texto;
        this.numHijos = 0;

    }

    public Nodo(String texto, String tipo) {
        this.texto = texto;
        this.valor = texto;
        this.tipo = tipo;
        this.numHijos = 0;
    }

    public Nodo() {
    }

    public void insertar(Nodo nuevo) {
        if (nuevo == null) {
            return; //si el nodo nuevo es nulo, no se creo en la gramatica y no debe ser agregado por lo tanto
        }
        //Se declara un nodo auxiliar para contener a los hijos
        Nodo aux[] = new Nodo[this.numHijos + 1];

        //se copian los hijos del nodo al auxiliar
        for (int i = 0; i < this.numHijos; i++) {
            aux[i] = this.hijos[i];
            aux[i].padre = this;
        }
        //Se indica el padre del nuevo nodo y se inserta al arreglo de hijos
        nuevo.padre = this;
        aux[this.numHijos] = nuevo;

        //se asigna los hijos en el arreglo auxiliar al Nodo y aumenta el numero de hijos
        this.hijos = aux;
        this.numHijos++;

    }
}
