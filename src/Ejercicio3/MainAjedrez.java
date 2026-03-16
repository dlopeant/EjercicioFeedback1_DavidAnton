import Ejercicio3.PartidaAjedrez;

void main() {
    PartidaAjedrez partidaAjedrez = new PartidaAjedrez("David Antón", "Carlos Ruiz");
    String jugadorBlancas = partidaAjedrez.getJugadorBlancas();
    String jugadorNegras = partidaAjedrez.getJugadorNegras();

    partidaAjedrez.mostrarTablero();

    partidaAjedrez.iniciarPartida(jugadorBlancas, jugadorNegras);
}