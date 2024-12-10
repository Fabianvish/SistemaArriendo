package vista;

import modelo.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class VistaClientes extends JFrame {

    private JTextField txtCedula, txtNombre;
    private JCheckBox chkVigente;
    private JButton btnAgregar;
    private List<Cliente> clientes;

    public VistaClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Clientes");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Clientes");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(50, 20, 300, 30);
        add(lblTitulo);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setBounds(30, 70, 100, 30);
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(150, 70, 200, 30);
        add(txtCedula);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(30, 120, 100, 30);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(150, 120, 200, 30);
        add(txtNombre);

        JLabel lblVigente = new JLabel("¿Vigente?");
        lblVigente.setBounds(30, 170, 100, 30);
        add(lblVigente);

        chkVigente = new JCheckBox("Sí");
        chkVigente.setSelected(true);
        chkVigente.setBounds(150, 170, 100, 30);
        add(chkVigente);

        btnAgregar = new JButton("Agregar Cliente");
        btnAgregar.setBounds(150, 220, 150, 30);
        add(btnAgregar);
        btnAgregar.addActionListener(this::agregarCliente);

        setLocationRelativeTo(null);
    }

    private void agregarCliente(ActionEvent e) {
        String cedula = txtCedula.getText().trim();
        String nombre = txtNombre.getText().trim();
        boolean vigente = chkVigente.isSelected();

        if (cedula.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
            return;
        }

        try {
            Cliente cliente = new Cliente(cedula, nombre, vigente);
            clientes.add(cliente);
            JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente.");
            txtCedula.setText("");
            txtNombre.setText("");
            chkVigente.setSelected(true);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
}
