package vista;

import modelo.ArriendoCuota;
import modelo.Cliente;
import modelo.Vehiculo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VistaArriendo extends JFrame {

    private JComboBox<Cliente> cmbClientes;
    private JComboBox<Vehiculo> cmbVehiculos;
    private JFormattedTextField txtFecha;
    private JTextField txtDias, txtPrecioDia, txtCuotas;
    private JTable tablaCuotas;
    private JButton btnGuardarArriendo, btnNuevoCliente, btnPagarPrimeraCuota;
    private List<Cliente> clientes;
    private List<Vehiculo> vehiculos;
    private List<ArriendoCuota> arriendos;

    public VistaArriendo() {
        // Inicialización de datos
        clientes = new ArrayList<>();
        vehiculos = inicializarVehiculos();
        arriendos = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        setTitle("Sistema de Gestión de Arriendos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Arriendos con Cuotas");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(200, 10, 400, 30);
        add(lblTitulo);

        JLabel lblCliente = new JLabel("Seleccione Cliente:");
        lblCliente.setBounds(30, 70, 150, 30);
        add(lblCliente);

        cmbClientes = new JComboBox<>(clientes.toArray(new Cliente[0]));
        cmbClientes.setBounds(200, 70, 200, 30);
        add(cmbClientes);

        btnNuevoCliente = new JButton("Ingresar Nuevo Cliente");
        btnNuevoCliente.setBounds(450, 70, 200, 30);
        add(btnNuevoCliente);
        btnNuevoCliente.addActionListener(this::ingresarNuevoCliente);

        JLabel lblVehiculo = new JLabel("Seleccione Automóvil:");
        lblVehiculo.setBounds(30, 120, 150, 30);
        add(lblVehiculo);

        cmbVehiculos = new JComboBox<>(vehiculos.toArray(new Vehiculo[0]));
        cmbVehiculos.setBounds(200, 120, 200, 30);
        add(cmbVehiculos);

        JLabel lblFecha = new JLabel("Fecha de Arriendo:");
        lblFecha.setBounds(30, 170, 150, 30);
        add(lblFecha);

        txtFecha = new JFormattedTextField(new SimpleDateFormat("dd-MM-yyyy"));
        txtFecha.setBounds(200, 170, 200, 30);
        txtFecha.setValue(new Date()); // Establece la fecha actual como predeterminada
        add(txtFecha);

        JLabel lblDias = new JLabel("Días:");
        lblDias.setBounds(30, 220, 150, 30);
        add(lblDias);

        txtDias = new JTextField();
        txtDias.setBounds(200, 220, 200, 30);
        add(txtDias);

        JLabel lblPrecioDia = new JLabel("Precio por Día:");
        lblPrecioDia.setBounds(30, 270, 150, 30);
        add(lblPrecioDia);

        txtPrecioDia = new JTextField();
        txtPrecioDia.setBounds(200, 270, 200, 30);
        add(txtPrecioDia);

        JLabel lblCuotas = new JLabel("Cantidad de Cuotas:");
        lblCuotas.setBounds(30, 320, 150, 30);
        add(lblCuotas);

        txtCuotas = new JTextField();
        txtCuotas.setBounds(200, 320, 200, 30);
        add(txtCuotas);

        btnGuardarArriendo = new JButton("Guardar Arriendo y Mostrar Cuotas");
        btnGuardarArriendo.setBounds(450, 320, 250, 30);
        add(btnGuardarArriendo);
        btnGuardarArriendo.addActionListener(this::guardarArriendoYmostrarCuotas);

        tablaCuotas = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"Número", "Valor", "¿Pagada?"}
        ));
        JScrollPane scrollTabla = new JScrollPane(tablaCuotas);
        scrollTabla.setBounds(200, 370, 400, 200);
        add(scrollTabla);

        btnPagarPrimeraCuota = new JButton("Pagar Primera Cuota");
        btnPagarPrimeraCuota.setBounds(450, 580, 200, 30);
        add(btnPagarPrimeraCuota);
        btnPagarPrimeraCuota.addActionListener(this::pagarPrimeraCuota);

        setLocationRelativeTo(null);
    }

    private List<Vehiculo> inicializarVehiculos() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Vehiculo("ABC123", 'D'));
        vehiculos.add(new Vehiculo("DEF456", 'D'));
        vehiculos.add(new Vehiculo("GHI789", 'D'));
        vehiculos.add(new Vehiculo("JKL012", 'D'));
        vehiculos.add(new Vehiculo("MNO345", 'D'));
        vehiculos.add(new Vehiculo("PQR678", 'D'));
        vehiculos.add(new Vehiculo("STU901", 'D'));
        vehiculos.add(new Vehiculo("VWX234", 'D'));
        vehiculos.add(new Vehiculo("YZA567", 'D'));
        vehiculos.add(new Vehiculo("BCD890", 'D'));
        return vehiculos;
    }

    private void ingresarNuevoCliente(ActionEvent e) {
        VistaClientes vistaClientes = new VistaClientes(clientes);
        vistaClientes.setVisible(true);
        vistaClientes.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                actualizarClientes();
            }
        });
    }

    private void actualizarClientes() {
        cmbClientes.removeAllItems();
        for (Cliente cliente : clientes) {
            cmbClientes.addItem(cliente);
        }
        JOptionPane.showMessageDialog(this, "Lista de clientes actualizada.");
    }

    private static int numArriendoCorrelativo = 1;

    private void guardarArriendoYmostrarCuotas(ActionEvent e) {
        Cliente cliente = (Cliente) cmbClientes.getSelectedItem();
        Vehiculo vehiculo = (Vehiculo) cmbVehiculos.getSelectedItem();
        int dias = Integer.parseInt(txtDias.getText());
        double precioDia = Double.parseDouble(txtPrecioDia.getText());
        int cuotas = Integer.parseInt(txtCuotas.getText());

        if (cliente == null || vehiculo == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente y un vehículo.");
            return;
        }

        if (vehiculo.getCondicion() != 'D') {
            JOptionPane.showMessageDialog(this, "El vehículo no está disponible.");
            return;
        }

        String fecha = txtFecha.getText();

        // Crear arriendo con número correlativo
        ArriendoCuota arriendo = new ArriendoCuota(numArriendoCorrelativo++, cliente, vehiculo, dias, precioDia);
        arriendo.generarCuotas(cuotas);
        arriendos.add(arriendo);

        vehiculo.setCondicion('A'); // Marcar el vehículo como arrendado

        DefaultTableModel model = (DefaultTableModel) tablaCuotas.getModel();
        model.setRowCount(0);
        for (int i = 0; i < arriendo.getCuotas().size(); i++) {
            model.addRow(new Object[]{i + 1, arriendo.getCuotas().get(i).getValorCuota(), false});
        }

        JOptionPane.showMessageDialog(this, "Arriendo registrado con éxito.\nFecha: " + fecha);
    }

    private void pagarPrimeraCuota(ActionEvent e) {
        if (tablaCuotas.getRowCount() > 0) {
            boolean pagada = (boolean) tablaCuotas.getValueAt(0, 2);
            if (!pagada) {
                tablaCuotas.setValueAt(true, 0, 2);
                JOptionPane.showMessageDialog(this, "Primera cuota pagada con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "La primera cuota ya está pagada.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay cuotas para pagar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VistaArriendo().setVisible(true);
        });
    }
}
