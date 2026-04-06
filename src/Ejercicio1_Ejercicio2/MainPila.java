import Ejercicio1_Ejercicio2.Pila;

void main() {
    Pila pila = new Pila(5);

    IO.println(" -- PILA -- ");
    IO.println("Insertando elementos...");

    pila.insertarElemento(3);
    pila.insertarElemento(5);
    pila.insertarElemento(6);

    pila.mostrarPila();

    IO.println("\nEliminando elemento");
    pila.eliminarElemento();

    IO.println("El elemento en la cima después de eliminar es: " + pila.elementoEnLaCima());
    IO.println("\nEl tamaño de la pila es: " + pila.getTamaño());
    IO.println("¿La pila está vacía?: " + (pila.pilaVacia() ? "Sí" : "No"));

    IO.println("Estado final de la pila: ");
    pila.mostrarPila();

}