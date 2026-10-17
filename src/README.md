# Práctica No. 1 — Implementación de Pilas

## Contenido
- `src/Interffaces/ed/ito/Pila.java`: interfaz genérica de la pila.
- `src/clases/ed/ito/PilaEstatica.java`: implementación mediante arreglo y capacidad fija.
- `src/clases/ed/ito/PilaDinamica.java`: implementación mediante nodos enlazados.
- `src/clases/ed/ito/Nodo.java`: nodo usado por la pila dinámica.
- `src/excepciones/ed/ito/`: excepciones de pila vacía y desbordamiento.
- `src/juego/Puzzle8.java`: modelo del tablero.
- `src/juego/Principal.java`: juego de consola con undo/redo.

## Compilar y ejecutar
Desde la carpeta `Puzzle8`:

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out juego.Principal
```

En Windows (PowerShell), también puede compilarse desde la raíz con:
```powershell
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName } | Set-Content fuentes.txt
javac -encoding UTF-8 -d out @fuentes.txt
java -cp out juego.Principal
```

## Controles
- W: mover el espacio vacío hacia arriba.
- A: moverlo hacia la izquierda.
- S: moverlo hacia abajo.
- D: moverlo hacia la derecha.
- U: deshacer (undo).
- R: rehacer (redo).
- X: salir.

El estado final definido en la práctica es:
`1 2 3 / 4 5 6 / 7 8 0`.

## Observaciones para el reporte
1. Se revisó el enunciado y se identificó que se requería una API de pilas estática y dinámica, además del juego Puzzle8 con undo y redo.
2. Se conservó la interfaz genérica `Pila<E>` y las clases/excepciones que ya estaban empezadas, corrigiendo validaciones y detalles de implementación.
3. Se completó `Puzzle8` con constructor por defecto, constructor que recibe una matriz, copia del estado, comprobación del estado final y movimiento de la casilla vacía.
4. Se agregó `Principal`, una aplicación de consola que registra estados en dos pilas para deshacer y rehacer movimientos. Al hacer un movimiento nuevo se borra la pila redo.
5. Se incluyó manejo de pila vacía, desbordamiento y salida normal del juego.
6. Pruebas recomendadas: compilar todos los archivos; probar un movimiento válido e inválido; usar U y R; intentar U/R cuando las pilas están vacías; completar el tablero objetivo; salir con X.

## Referencias
- Guía de la asignatura: *Práctica No. 1 — Implementación de Pilas* (documento proporcionado para la práctica).
- Apuntes de clase sobre pilas, arreglos, nodos enlazados y excepciones .
