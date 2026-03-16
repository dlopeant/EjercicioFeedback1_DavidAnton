package Ejercicio1_Ejercicio2;

public class Pila {

    private final int[] pila;
    private int tope;
    private static final int tamañoPorDefecto = 10;

    public Pila(int capacidad) {
        this.pila = new int[capacidad];
        this.tope = -1;
    }

    public Pila() {
        this(tamañoPorDefecto);
    }

    public int tamañoPila() {
        return tope + 1;
    }

    public boolean pilaVacia() {
        return tope == -1;
    }

    public int elementoEnLaCima() {
        if (pilaVacia()) {
            IO.println("Pila vacía.");
            return 0;
        }
        return pila[tope];
    }

    public void insertarElemento(int elemento) {
        if (tope < pila.length - 1) {
            tope++;
            pila[tope] = elemento;
        } else {
            IO.println("Error: Pila llena");
        }
    }

    public void eliminarElemento() {
        if (!pilaVacia()) {
            tope--;
        } else {
            IO.println("Error: Pila vacía");
        }
    }

    public void mostrarPila() {
        if (pilaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }

        IO.print("CIMA -> ");
        for (int i = tope; i >= 0; i--) {
            IO.print("[" + pila[i] + "]");
        }
        IO.println(" <- FINAL");
    }

}
