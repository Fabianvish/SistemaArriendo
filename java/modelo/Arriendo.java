package modelo;

public class Arriendo {
    private int diasArriendo;
    private double precioDia;

    public Arriendo(int diasArriendo, double precioDia) {
        this.diasArriendo = diasArriendo;
        this.precioDia = precioDia;
    }

    public int getDiasArriendo() {
        return diasArriendo;
    }

    public void setDiasArriendo(int diasArriendo) {
        this.diasArriendo = diasArriendo;
    }

    public double getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(double precioDia) {
        this.precioDia = precioDia;
    }

    public int obtenerMontoAPagar() {
        return (int) (diasArriendo * precioDia);
    }
}

