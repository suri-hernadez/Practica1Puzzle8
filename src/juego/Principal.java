package juego;
import java.util.Scanner;
import clases.ed.ito.PilaEstatica;
import excepciones.ed.ito.ExceptionDeDesbordamientoDePila;
import excepciones.ed.ito.ExceptionDePilaVacia;
/** Aplicación de consola con funciones de deshacer (undo) y rehacer (redo). */
public class Principal {
    private static final int CAPACIDAD_HISTORIAL = 1000;
    public static void main(String[] args) {
        Puzzle8 puzzle = new Puzzle8();
        PilaEstatica<int[][]> undo = new PilaEstatica<>(CAPACIDAD_HISTORIAL);
        PilaEstatica<int[][]> redo = new PilaEstatica<>(CAPACIDAD_HISTORIAL);
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("=== Puzzle 8 ===");
            System.out.println("Mueve el espacio vacío con W (arriba), A (izquierda), S (abajo), D (derecha).");
            System.out.println("U = deshacer, R = rehacer, X = salir.");
            while (true) {
                System.out.println("\n" + puzzle);
                if (puzzle.esEstadoFinal()) {
                    System.out.println("¡Felicidades! Llegaste al estado final.");
                    break;
                }
                System.out.print("Movimiento: ");
                String entrada = sc.nextLine().trim();
                if (entrada.isEmpty()) continue;
                char op = Character.toUpperCase(entrada.charAt(0));
                if (op == 'X') {
                    System.out.println("Juego terminado por decisión del jugador.");
                    break;
                }
                if (op == 'U') {
                    if (undo.isEmpty()) {
                        System.out.println("Ya no hay estados previos para deshacer.");
                        continue;
                    }
                    redo.push(puzzle.getEstado());
                    puzzle = new Puzzle8(undo.pop());
                    continue;
                }
                if (op == 'R') {
                    if (redo.isEmpty()) {
                        System.out.println("No hay estados posteriores para rehacer.");
                        continue;
                    }
                    undo.push(puzzle.getEstado());
                    puzzle = new Puzzle8(redo.pop());
                    continue;
                }
                int[][] anterior = puzzle.getEstado();
                if (puzzle.mover(op)) {
                    undo.push(anterior);
                    while (!redo.isEmpty()) redo.pop();
                } else {
                    System.out.println("Movimiento inválido. Usa W, A, S o D y no salgas del tablero.");
                }
            }
        } catch (ExceptionDeDesbordamientoDePila e) {
            System.out.println("La pila se ha desbordado y no se puede continuar jugando. Programa terminado.");
        } catch (ExceptionDePilaVacia e) {
            System.out.println("No hay más estados en el historial. Programa terminado.");
        }
    }
}
