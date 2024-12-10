package modelo;

import java.util.ArrayList;
import java.util.List;

public class ArriendoCuota {

    private int numArriendo;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private int dias;
    private double precioDia;
    private List<CuotaArriendo> cuotas;

    public ArriendoCuota(int numArriendo, Cliente cliente, Vehiculo vehiculo, int dias, double precioDia) {
        this.numArriendo = numArriendo;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.dias = dias;
        this.precioDia = precioDia;
        this.cuotas = new ArrayList<>();
    }

    public int getNumArriendo() {
        return numArriendo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public int getDias() {
        return dias;
    }

    public double getPrecioDia() {
        return precioDia;
    }

    public List<CuotaArriendo> getCuotas() {
        return cuotas;
    }

    public void generarCuotas(int cantidadCuotas) {
        double montoTotal = dias * precioDia;
        int valorCuota = (int) (montoTotal / cantidadCuotas);
        for (int i = 1; i <= cantidadCuotas; i++) {
            cuotas.add(new CuotaArriendo(i, valorCuota));
        }
    }

    @Override
    public String toString() {
        return "Arriendo #" + numArriendo;
    }
}
