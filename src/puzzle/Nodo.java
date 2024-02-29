package puzzle;

import java.util.ArrayList;

public class Nodo {
    int [][]estado;
    ArrayList<Nodo> hijos = new ArrayList<Nodo>();
    Nodo padre;
    public Nodo(int[][] estado){
        this.estado = estado;
        hijos = null;
        padre = null;
    }

    public int[][] getEstado() {
        return estado;
    }

    public void setEstado(int[][] estado) {
        this.estado = estado;
    }

    public ArrayList<Nodo> getHijo() {
        return hijos;
    }

    public void setHijos(ArrayList<Nodo> hijos) {
        this.hijos = hijos;
        if(hijos != null){
            for(Nodo h: hijos){
                h.padre = this;
            }
        }
    }

    public Nodo getpadre() {
        return padre;
    }

    public void setpadre(Nodo padre) {
        this.padre = padre;
    }
}
