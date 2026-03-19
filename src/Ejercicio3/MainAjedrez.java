import Ejercicio3.PartidaAjedrez;
import Ejercicio3.Pieza;

void main() {
    PartidaAjedrez partidaAjedrez = new PartidaAjedrez("David Antón", "Carlos Ruiz");
    String jugadorBlancas = partidaAjedrez.getJugadorBlancas();
    String jugadorNegras = partidaAjedrez.getJugadorNegras();

    partidaAjedrez.mostrarTablero();

    partidaAjedrez.iniciarPartida(jugadorBlancas, jugadorNegras);

    partidaAjedrez.realizarMovimiento("e2-e4");

    Pieza piezaEnPosicion = partidaAjedrez.obtenerPiezaEnPosicion("g1");
    if (piezaEnPosicion != null) {
        IO.println("Pieza encontrada: " + piezaEnPosicion.getTipo() + " " + piezaEnPosicion.getColor());
    } else {
        IO.println("Casilla vacía");
    }

    IO.println("Movimientos realizados:\n" + partidaAjedrez.obtenerMovimientos());

    IO.println("Movimiento posibles:\n" + partidaAjedrez.obtenerMovimientosPosibles("e1"));

    IO.println("¿Es empate? -> " + partidaAjedrez.esEmpate());
    IO.println("¿Jaque mate? -> " + partidaAjedrez.esJaqueMate());
}