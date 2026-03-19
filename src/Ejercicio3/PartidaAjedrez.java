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
    private String turnoActual = BLANCA;
    private int movimientosSinCaptura = 0;
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

        this.jugadorBlancas = jugadorBlancas;
        this.jugadorNegras = jugadorNegras;

        movimientos.clear();
        tablero.clear();

        IO.println("\nJugador 1: [" + jugadorBlancas + "]");
        IO.println("\nJugador 2: [" + jugadorNegras + "]");

        inicializarTablero();
        mostrarTablero();
    }

    public void realizarMovimiento(String movimiento) {
        String[] movimientoRealizado = movimiento.split("-");
        if (movimientoRealizado.length != 2) {
            IO.println("Formato de movimiento no válido. Use [posicionInicial(e2)-posicionFinal(e4)]");
        }

        String origen = movimientoRealizado[0];
        String destino = movimientoRealizado[1];

        Pieza piezaOrigen = tablero.get(origen);
        Pieza piezaDestino = tablero.get(destino);

        if (piezaOrigen == null) {
            IO.println("Error -> Pieza no encontrada en: " + origen);
            return;
        }

        if (!turnoActual.equals(piezaOrigen.getColor())) {
            IO.println("Error -> Es el turno de las: " + turnoActual + "s.");
            return;
        }

        if (piezaDestino != null) {
            if (piezaOrigen.getColor().equals(piezaDestino.getColor())) {
                IO.println("Error -> No puedes comer una pieza del mismo color de las tuyas.");
                return;
            }
        }

        if (tablero.containsKey(destino)) {
            movimientosSinCaptura = 0;
        } else {
            movimientosSinCaptura++;
        }

        tablero.remove(origen);
        tablero.put(destino, piezaOrigen);

        movimientos.add(movimiento);

        turnoActual = (turnoActual.equals(BLANCA)) ? NEGRA : BLANCA;

        IO.println("Movimiento realizado con éxito");

        mostrarTablero();
        IO.println("Turno de: " + turnoActual);
    }

    public Pieza obtenerPiezaEnPosicion(String posicion) {
        if (posicion == null || posicion.length() != 2) {
            return null;
        }

        return tablero.get(posicion);
    }

    public LinkedList<String> obtenerMovimientosPosibles(String posicion) {
        LinkedList<String> posibles = new LinkedList<>();
        Pieza pieza = obtenerPiezaEnPosicion(posicion);
        if (pieza == null) return posibles;

        char col = posicion.charAt(0);
        int fila = Character.getNumericValue(posicion.charAt(1));

        switch (pieza.getTipo()) {
            case PEON:
                agregarLogicaPeon(posibles, fila, col, pieza);
                break;

            case TORRE:
                agregarMovimientosEnLinea(posibles, fila, col, new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}, pieza);
                break;

            case ALFIL:
                agregarMovimientosEnLinea(posibles, fila, col, new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}, pieza);
                break;

            case CABALLO:
                agregarMovimientosSaltando(posibles, fila, col, new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}}, pieza);
                break;

            case QUEEN:
                agregarMovimientosEnLinea(posibles, fila, col, new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}, pieza);
                break;

            case KING:
                agregarMovimientosSaltando(posibles, fila, col, new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}}, pieza);
                break;
        }
        return posibles;
    }

    public boolean esEmpate() {
        if (movimientosSinCaptura >= 50) return true;
        if (tablero.size() == 2) return true;

        if (!tieneMovimientosLegales(turnoActual) && !estaEnJaque(turnoActual)) {
            IO.println("Empate: Rey ahogado.");
            return true;
        }
        return false;
    }

    public boolean esJaqueMate() {
        if (!estaEnJaque(turnoActual)) {
            return false;
        }

        for (Map.Entry<String, Pieza> entrada : tablero.entrySet()) {
            String posOrigen = entrada.getKey();
            Pieza pieza = entrada.getValue();

            if (pieza.getColor().equals(turnoActual)) {
                LinkedList<String> movimientosPosibles = obtenerMovimientosPosibles(posOrigen);

                for (String posDestino : movimientosPosibles) {
                    Pieza piezaComida = tablero.get(posDestino);

                    tablero.put(posDestino, pieza);
                    tablero.remove(posOrigen);

                    boolean sigueEnJaque = estaEnJaque(turnoActual);

                    tablero.put(posOrigen, pieza);
                    if (piezaComida != null) {
                        tablero.put(posDestino, piezaComida);
                    } else {
                        tablero.remove(posDestino);
                    }

                    if (!sigueEnJaque) {
                        return false;
                    }
                }
            }
        }

        IO.println("¡Jaque Mate! El jugador de las " + (turnoActual.equals(BLANCA) ? NEGRA : BLANCA) + "s gana.");
        return true;
    }

    public LinkedList<String> obtenerMovimientos() {
        return this.movimientos;
    }

    private void agregarMovimientosEnLinea(LinkedList<String> posibles, int fila, char col, int[][] direcciones, Pieza piezaOriginal) {
        for (int[] dir : direcciones) {
            int nFila = fila + dir[0];
            char nCol = (char) (col + dir[1]);

            while (nFila >= 1 && nFila <= 8 && nCol >= 'a' && nCol <= 'h') {
                String destino = "" + nCol + nFila;
                Pieza poisbleDestino = tablero.get(destino);

                if (poisbleDestino == null) {
                    posibles.add(destino);
                } else {
                    if (!poisbleDestino.getColor().equals(piezaOriginal.getColor())) {
                        posibles.add(destino);
                    }
                    break;
                }
                nFila += dir[0];
                nCol = (char) (nCol + dir[1]);
            }
        }
    }

    private void agregarMovimientosSaltando(LinkedList<String> posibles, int fila, char col, int[][] saltos, Pieza piezaOriginal) {
        for (int[] s : saltos) {
            int nFila = fila + s[0];
            char nCol = (char) (col + s[1]);

            if (nFila >= 1 && nFila <= 8 && nCol >= 'a' && nCol <= 'h') {
                String destino = "" + nCol + nFila;
                Pieza posibleDestino = tablero.get(destino);
                if (posibleDestino == null || !posibleDestino.getColor().equals(piezaOriginal.getColor())) {
                    posibles.add(destino);
                }
            }
        }
    }

    private void agregarLogicaPeon(LinkedList<String> posibles, int fila, char col, Pieza piezaOriginal) {
        int avance = piezaOriginal.getColor().equals(BLANCA) ? 1 : -1;
        String adelante = "" + col + (fila + avance);
        if (fila + avance >= 1 && fila + avance <= 8) {
            if (tablero.get(adelante) == null) {
                posibles.add(adelante);

                boolean estaEnElInicio = (piezaOriginal.getColor().equals(BLANCA) && fila == 2) ||
                        (piezaOriginal.getColor().equals(NEGRA) && fila == 7);
                String adelanteDoble = "" + col + (fila + (avance * 2));

                if (estaEnElInicio && tablero.get(adelanteDoble) == null) {
                    posibles.add(adelanteDoble);
                }
            }

            // Capturas diagonales
            char[] columnaDiagonal = {(char) (col - 1), (char) (col + 1)};
            for (char cDiag : columnaDiagonal) {
                if (cDiag >= 'a' && cDiag <= 'h') {
                    String posicionDiagonal = "" + cDiag + (fila + avance);
                    Pieza pEnemiga = tablero.get(posicionDiagonal);

                    if (pEnemiga != null && !pEnemiga.getColor().equals(piezaOriginal.getColor())) {
                        posibles.add(posicionDiagonal);
                    }
                }
            }
        }
    }

    private boolean tieneMovimientosLegales(String color) {
        for (Map.Entry<String, Pieza> casilla : tablero.entrySet()) {
            Pieza p = casilla.getValue();
            if (p.getColor().equals(color)) {
                if (!obtenerMovimientosPosibles(casilla.getKey()).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean estaEnJaque(String colorRey) {
        String posRey = "";
        for (Map.Entry<String, Pieza> entry : tablero.entrySet()) {
            if (entry.getValue().getTipo().equals(KING) && entry.getValue().getColor().equals(colorRey)) {
                posRey = entry.getKey();
                break;
            }
        }

        String colorEnemigo = colorRey.equals(BLANCA) ? NEGRA : BLANCA;
        for (Map.Entry<String, Pieza> entry : tablero.entrySet()) {
            if (entry.getValue().getColor().equals(colorEnemigo)) {
                if (obtenerMovimientosPosibles(entry.getKey()).contains(posRey)) {
                    return true;
                }
            }
        }
        return false;
    }
}
