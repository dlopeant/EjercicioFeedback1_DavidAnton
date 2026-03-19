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

        while (partidaActiva) {
            partida.mostrarTablero();

            IO.println("Turno de: " + (partida.getTurnoActual().equals(PartidaAjedrez.BLANCA) ? jugadorBlancas : jugadorNegras));
            IO.print("Introduce movimiento (ej: e2-e4) o 'salir': ");
            String entrada = sc.nextLine().toLowerCase();

            if (entrada.equals("salir")) {
                partidaActiva = false;
                IO.println("Partida abandonada.");
                break;
            }

            // 3. Ejecutar movimiento
            partida.realizarMovimiento(entrada);

            // 4. Comprobaciones de fin de partida
            if (partida.esJaqueMate()) {
                IO.println("¡JAQUE MATE! La partida ha terminado.");
                partidaActiva = false;
            } else if (partida.esEmpate()) {
                IO.println("La partida ha terminado en EMPATE.");
                partidaActiva = false;
            }
        }

        partida.finalizarPartida();
        sc.close();
    }
}