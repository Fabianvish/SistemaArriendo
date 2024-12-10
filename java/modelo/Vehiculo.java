package modelo;

public class Vehiculo {
    private String patente;
    private char condicion; // 'D' = Disponible, 'A' = Arrendado

    public Vehiculo(String patente, char condicion) {
        if (!patente.matches("^[A-Z0-9]{6}$")) {
            throw new IllegalArgumentException("Patente inválida.");
        }
        this.patente = patente;
        this.condicion = condicion;
    }

    public String getPatente() {
        return patente;
    }

    public char getCondicion() {
        return condicion;
    }

    public void setCondicion(char condicion) {
        this.condicion = condicion;
    }

    @Override
    public String toString() {
        return patente + " - " + (condicion == 'D' ? "Disponible" : "Arrendado");
    }
}
