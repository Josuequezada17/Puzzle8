package puzzle;

import java.util.ArrayList;
import java.util.Arrays;

public class GamePuzzle {

    public static void main(String[] args) {

        int [][] inicio = {{4,6,1}, {2,0,3}, {7,5,8}};
        int [][] solucion = {{1,2,3}, {4,5,6},{7,8,0}};
        Nodo start = new Nodo(inicio);
        Nodo sol = findSolution(start, solucion);

        while(sol.padre != null){
            printState(sol.getEstado());
            System.out.println("----------------------");
            sol = sol.padre;

        }

    }

    public static Nodo findSolution(Nodo root, int[][] solucion){
        ArrayList<Nodo> nodoArrayList = new ArrayList<Nodo>(); //Ramas del arbol
        ArrayList<Nodo> visitados = new ArrayList<Nodo>();

        nodoArrayList.add(root); //Crear el arbol, pasando la raiz inicial o padre
        int conunter = 0;

        while(nodoArrayList.size() != 0){ //Revisa todas las ramas
            Nodo check = nodoArrayList.remove(0); //Ir sacando las ramas revisadas
            printState(check.getEstado());
            int[] posicionCero = findPositionZero(check.getEstado());

            System.out.println("Numero de iteraciones " + ++conunter);

            if(Arrays.deepEquals(check.getEstado(),solucion)){
                System.out.println("Solucion");
                return check;
            }

            ArrayList<Nodo> hijos = new ArrayList<Nodo>();

            visitados.add(check);

            if(posicionCero[0] != 0){
                Nodo hijo = new Nodo(clonar(check.getEstado()));
                int up = hijo.getEstado()[posicionCero[0] - 1][posicionCero[1]];
                hijo.getEstado()[posicionCero[0]][posicionCero[1]] = up;
                hijo.getEstado()[posicionCero[0] - 1][posicionCero[1]] = 0;

                if(!estaVisitado(visitados,hijo)) {
                    nodoArrayList.add(hijo);
                }
                hijos.add(hijo);

            }

            if(posicionCero[0] != 2){
                Nodo hijo = new Nodo(clonar(check.getEstado()));
                int down = hijo.getEstado()[posicionCero[0] + 1][posicionCero[1]];
                hijo.getEstado()[posicionCero[0]][posicionCero[1]] = down;
                hijo.getEstado()[posicionCero[0] + 1][posicionCero[1]] = 0;

                if(!estaVisitado(visitados,hijo)) {
                    nodoArrayList.add(hijo);
                }
                hijos.add(hijo);

            }

            if(posicionCero[1] != 0){
                Nodo hijo = new Nodo(clonar(check.getEstado()));
                int left = hijo.getEstado()[posicionCero[0]][posicionCero[1] - 1];
                hijo.getEstado()[posicionCero[0]][posicionCero[1]] = left;
                hijo.getEstado()[posicionCero[0]][posicionCero[1] - 1] = 0;

                if(!estaVisitado(visitados,hijo)) {
                    nodoArrayList.add(hijo);
                }
                hijos.add(hijo);

            }

            if(posicionCero[1] != 2){
                Nodo hijo = new Nodo(clonar(check.getEstado()));
                int right = hijo.getEstado()[posicionCero[0]][posicionCero[1] + 1];
                hijo.getEstado()[posicionCero[0]][posicionCero[1]] = right;
                hijo.getEstado()[posicionCero[0]][posicionCero[1] + 1] = 0;

                if(!estaVisitado(visitados,hijo)) {
                    nodoArrayList.add(hijo);
                }
                hijos.add(hijo);

            }

            check.setHijos(hijos);


        }
        return null;

    }

    private static boolean estaVisitado(ArrayList<Nodo> visitados, Nodo hijo) {

        for (Nodo v:visitados ) {
            if(Arrays.deepEquals(v.getEstado(),hijo.getEstado())){
                return true;
            }
        }

        return false;
    }

    private static int[][] clonar(int[][] estado) {
        int[][] clon = new int[estado.length][estado.length];
        for (int i = 0; i < estado.length; i++){
            for (int j = 0; j < estado.length; j++){
                clon[i][j] = estado[i][j];
            }
        }
        return clon;
    }

    private static int[] findPositionZero(int[][] estado) {
        int[] position = new int[2]; //Guarda la posicion i y j
        for (int i = 0; i < estado.length; i++){
            for (int j = 0; j < estado.length; j++){
                if(estado[i][j] == 0){
                    position[0] = i;
                    position[1] = j;
                }
            }
        }
        System.out.println("Posicion de " + position[0] + " ," + position[1]);
        return position;
    }

    public static void printState(int[][] estado){
        for (int i = 0; i < estado.length; i++){
            for (int j = 0; j < estado.length; j++){
                System.out.print("[" + estado[i][j] + "]");
            }
            System.out.println("");
        }
    }

}
