package juego;
import java.util.Arrays;
/** Modelo del tablero 8-puzzle. El cero representa la casilla vacía. */
public class Puzzle8 {
    private int[][] puzzle8;
    private int x, y;
    /** Tablero inicial fijo indicado en la práctica. */
    public Puzzle8() {
        this(new int[][] {
                {1, 2, 3},
                {8, 0, 4},
                {7, 6, 5}
        });
    }
    /** Crea una copia del tablero recibido y localiza el cero. */
    public Puzzle8(int[][] tablero) {
        if (tablero == null || tablero.length != 3)
            throw new IllegalArgumentException("El tablero debe ser de 3x3.");
        puzzle8 = new int[3][3];
        boolean[] vistos = new boolean[9];
        boolean encontroCero = false;
        for (int i = 0; i < 3; i++) {
            if (tablero[i] == null || tablero[i].length != 3)
                throw new IllegalArgumentException("El tablero debe ser de 3x3.");
            for (int j = 0; j < 3; j++) {
                int valor = tablero[i][j];
                if (valor < 0 || valor > 8 || vistos[valor])
                    throw new IllegalArgumentException("El tablero debe contener una sola vez los números del 0 al 8.");
                vistos[valor] = true;
                puzzle8[i][j] = valor;
                if (valor == 0) { x = i; y = j; encontroCero = true; }
            }
        }
        if (!encontroCero) throw new IllegalArgumentException("Falta la casilla vacía (0).");
    }

    /** Devuelve una copia para evitar que se modifique el estado internamente. */
    public int[][] getEstado() {
        int[][] copia = new int[3][3];
        for (int i = 0; i < 3; i++) copia[i] = Arrays.copyOf(puzzle8[i], 3);
        return copia;
    }

    public boolean esEstadoFinal() {
        int[][] finalEsperado = {{1,2,3},{4,5,6},{7,8,0}};
        return Arrays.deepEquals(puzzle8, finalEsperado);
    }

    /**
     * Mueve el espacio vacío: W=arriba, S=abajo, A=izquierda, D=derecha.
     * Devuelve false si el movimiento no es válido.
     */
    public boolean mover(char direccion) {
        int nx = x, ny = y;
        switch (Character.toUpperCase(direccion)) {
            case 'W': case '8': nx--; break;
            case 'S': case '2': nx++; break;
            case 'A': case '4': ny--; break;
            case 'D': case '6': ny++; break;
            default: return false;
        }
        if (nx < 0 || nx >= 3 || ny < 0 || ny >= 3) return false;
        puzzle8[x][y] = puzzle8[nx][ny];
        puzzle8[nx][ny] = 0;
        x = nx; y = ny;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(puzzle8[i][j] == 0 ? "  " : String.format("%2d", puzzle8[i][j]));
                if (j < 2) sb.append(" |");
            }
            if (i < 2) sb.append("\n---+---+---\n");
        }
        return sb.toString();
    }
}
