package es.adrigala.test.zarapp;

import java.util.ArrayList;

/**
 * Created by Adri on 10/11/2016.
 */

public class Contador {
    private int grandes = 0;
    private int peques = 0;
    private int bases = 0;
    private ArrayList<Integer> last = new ArrayList<>();


    public int getGrandes() {
        return grandes;
    }

    public void setGrandes(int grandes) {
        this.grandes = grandes;
    }

    public int getPeques() {
        return peques;
    }

    public void setPeques(int peques) {
        this.peques = peques;
    }

    public int getBases() {
        return bases;
    }

    public void setBases(int bases) {
        this.bases = bases;
    }

    public Integer getLast() {
        return last.get(last.size() - 1);
    }

    public void setLast(int last) {
        this.last.add(last);
    }

    public void clearHistory(){
        this.last.clear();
    }

    public void clearLastBox(){
        this.last.remove(last.size() - 1);
    }

    public boolean canUseHistory(){
        boolean resultado = true;
        if (this.last.size() == 0){
            resultado = false;
        }

        return resultado;
    }
}
