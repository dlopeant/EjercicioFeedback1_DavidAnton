package Ejercicio3;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PartidaAjedrez {

    public static final String PEON = "Peón";
    public static final String TORRE = "Torre";
    public static final String CABALLO = "Caballo";
    public static final String ALFIL = "Alfil";
    public static final String QUEEN = "Queen"; //Para distinguir Rey y Reina usamos en inglés la palabra
    public static final String KING = "King";

    public static final String NEGRA = "Negra";
    public static final String BLANCA = "Blanca";

    private String jugadorBlancas;
    private String jugadorNegras;
    private LinkedList<String> movimientos;
    private Map<String, Pieza> tablero;

    public String getJugadorBlancas() {
        return jugadorBlancas;
    }

    public void setJugadorBlancas(String jugadorBlancas) {
        this.jugadorBlancas = jugadorBlancas;
    }

    public String getJugadorNegras() {
        return jugadorNegras;
    }

    public void setJugadorNegras(String jugadorNegras) {
        this.jugadorNegras = jugadorNegras;
    }

    public PartidaAjedrez(String jugadorBlancas, String jugadorNegras) {
        this.jugadorBlancas = jugadorBlancas;
        this.jugadorNegras = jugadorNegras;
        this.movimientos = new LinkedList<>();
        this.tablero = new HashMap<>();
        inicializarTablero();
    }

    private void inicializarTablero() {
        //Colocamos peones blancos en Fila 2
        for (char letra = 'a'; letra <= 'h'; letra++) {
            tablero.put(letra + "2", new Pieza(PEON, BLANCA));
        }

        //Colocamos peones negros en Fila 7
        for (char letra = 'a'; letra <= 'h'; letra++) {
            tablero.put(letra + "7", new Pieza(PEON, NEGRA));
        }

        //Colocamos las torres en los 4 extremos
        char[] torres = {'a', 'h'};
        for (char letra : torres) {
            tablero.put(letra + "1", new Pieza(TORRE, BLANCA));
            tablero.put(letra + "8", new Pieza(TORRE, NEGRA));
        }

        //Colocamos los caballos al lado de las torres
        char[] caballos = {'b', 'g'};
        for (char letra : caballos) {
            tablero.put(letra + "1", new Pieza(CABALLO, BLANCA));
            tablero.put(letra + "8", new Pieza(CABALLO, NEGRA));
        }

        //Colocamos los alfiles al lado de los caballos
        char[] alfiles = {'c', 'f'};
        for (char letra : alfiles) {
            tablero.put(letra + "1", new Pieza(ALFIL, BLANCA));
            tablero.put(letra + "8", new Pieza(ALFIL, NEGRA));
        }

        //Colocamos reyes y reinas
        tablero.put('d' + "1", new Pieza(QUEEN, BLANCA));
        tablero.put('d' + "8", new Pieza(QUEEN, NEGRA));
        tablero.put('e' + "1", new Pieza(KING, BLANCA));
        tablero.put('e' + "8", new Pieza(KING, NEGRA));
    }

    public void mostrarTablero() {
        IO.println("\nTablero de Ajedrez");
        IO.println("----------------------------");

        for (int i = 8; i >= 1; i--) {
            IO.print(i + " | ");

            for (char letra = 'a'; letra <= 'h'; letra++) {
                String posicion = "" + letra + i;
                Pieza pieza = tablero.get(posicion);

                if (pieza == null) {
                    IO.print(" * ");
                } else {
                    String inicialTipo = pieza.getTipo().substring(0, 1);
                    String inicialColor = pieza.getColor().equals(BLANCA) ? "B" : "N";
                    IO.print(inicialTipo + inicialColor + " ");
                }
            }
            IO.println();
        }
        IO.println("----------------------------");
        IO.println("     a  b  c  d  e  f  g  h\n");
    }

    public void iniciarPartida(String jugadorBlancas, String jugadorNegras) {

        IO.println("\nIniciando partida");
        IO.println("\nJugador 1: [" + jugadorBlancas + "]");
        IO.println("\nJugador 2: [" + jugadorNegras + "]");

        movimientos.clear();
        tablero.clear();

        mostrarTablero();

    }
}
