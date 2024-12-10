package modelo;

public class CuotaArriendo {
    private int numCuota;
    private int valorCuota;
    private boolean pagada;

    public CuotaArriendo(int numCuota, int valorCuota) {
        this.numCuota = numCuota;
        this.valorCuota = valorCuota;
        this.pagada = false;
    }

    public int getNumCuota() {
        return numCuota;
    }

    public int getValorCuota() {
        return valorCuota;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void pagarCuota() {
        this.pagada = true;
    }

    @Override
    public String toString() {
        return "Cuota #" + numCuota + ": $" + valorCuota + " - " + (pagada ? "Pagada" : "Pendiente");
    }
}
