import Ejercicio1_Ejercicio2.Pila;

void main() {
    Pila pila = new Pila(5);
    pila.insertarElemento(3);
    pila.insertarElemento(5);
    pila.insertarElemento(6);

    pila.mostrarPila();

    pila.eliminarElemento();
    IO.println("El elemento en la cima después de eliminar es: " + pila.elementoEnLaCima());

    IO.println("El tamaño de la pila es: " + pila.tamañoPila());
    IO.println("¿La pila está vacía?: " + pila.pilaVacia());

    pila.mostrarPila();

}