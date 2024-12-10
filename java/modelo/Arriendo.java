package modelo;

import java.io.Serializable;

public class Arriendo implements Serializable {
    private static final long serialVersionUID = 1L;
    private int numArriendo;
    private String fecArr;
    private int diasArriendo;

    public Arriendo(int numArriendo, String fecArr, int diasArriendo) {
        this.numArriendo = numArriendo;
        this.fecArr = fecArr;
        this.diasArriendo = diasArriendo;
    }

    public int obtenerMontoApagar(int precioDia) {
        return diasArriendo * precioDia;
    }

    public boolean evaluarArriendo(Cliente cliente, Vehiculo vehiculo) {
        return cliente.isVigente() && vehiculo.getCondicion() == 'D';
    }

    public int getNumArriendo() {
        return numArriendo;
    }

    public String getFecArr() {
        return fecArr;
    }

    public int getDiasArriendo() {
        return diasArriendo;
    }

    @Override
    public String toString() {
        return "Arriendo #" + numArriendo + " - Fecha: " + fecArr;
    }
}
