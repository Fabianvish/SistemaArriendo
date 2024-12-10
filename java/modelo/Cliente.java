package modelo;

public class Cliente {
    private String cedula; // RUT
    private String nombre;
    private boolean vigente;

    public Cliente(String cedula, String nombre, boolean vigente) {
        if (!validarRUT(cedula)) {
            throw new IllegalArgumentException("RUT inválido.");
        }
        this.cedula = cedula;
        this.nombre = nombre;
        this.vigente = vigente;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isVigente() {
        return vigente;
    }

    private boolean validarRUT(String rut) {
        if (!rut.matches("^\\d{7,8}-[\\dkK]$")) {
            return false;
        }
        String[] partes = rut.split("-");
        String numero = partes[0];
        String dv = partes[1].toUpperCase();

        int suma = 0, multiplicador = 2;
        for (int i = numero.length() - 1; i >= 0; i--) {
            suma += Character.getNumericValue(numero.charAt(i)) * multiplicador;
            multiplicador = (multiplicador == 7) ? 2 : multiplicador + 1;
        }
        String dvCalculado = (11 - (suma % 11)) == 10 ? "K" : String.valueOf((11 - (suma % 11)) % 11);
        return dv.equals(dvCalculado);
    }

    @Override
    public String toString() {
        return cedula + " - " + nombre;
    }
}
