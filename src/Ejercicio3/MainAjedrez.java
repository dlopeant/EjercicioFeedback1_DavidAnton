package Ejercicio3;

import java.util.Scanner;

public class MainAjedrez {

    static void main() {
        Scanner sc = new Scanner(System.in);

        IO.println("=== BIENVENIDO A DANLOPEZ CHESS ===");

        IO.print("Nombre del jugador de BLANCAS: ");
        String jugadorBlancas = sc.nextLine();
        IO.print("Nombre del jugador de NEGRAS: ");
        String jugadorNegras = sc.nextLine();

        PartidaAjedrez partida = new PartidaAjedrez(jugadorBlancas, jugadorNegras);

        boolean partidaActiva = true;

        partida.iniciarPartida(jugadorBlancas, jugadorNegras);

        while (partidaActiva) {
            String nombreActual = (partida.getTurnoActual().equals(PartidaAjedrez.BLANCA))
                    ? partida.getJugadorBlancas()
                    : partida.getJugadorNegras();
            IO.println("Turno de: " + (nombreActual));
            IO.print("Introduce movimiento (ej: e2-e4) o 'salir': ");
            String entrada = sc.nextLine().toLowerCase();

            if (entrada.equals("salir")) {
                IO.println("Partida abandonada.");
                break;
            }

            // Comprobaciones de fin de partida
            if (partida.realizarMovimiento(entrada)) {
                partidaActiva = false;
            }
        }

        partida.finalizarPartida();
        sc.close();
    }
}