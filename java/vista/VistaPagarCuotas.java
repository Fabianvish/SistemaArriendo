package vista;

import modelo.ArriendoCuota;
import modelo.Cliente;
import modelo.CuotaArriendo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class VistaPagarCuotas extends JFrame {

    private JComboBox<Cliente> cmbClientes;
    private JList<ArriendoCuota> lstArriendos;
    private JTable tablaCuotas;
    private JButton btnMostrarArriendos, btnPagarCuota;
    private List<Cliente> clientes;
    private List<ArriendoCuota> arriendos;

    public VistaPagarCuotas(List<Cliente> clientes, List<ArriendoCuota> arriendos) {
        this.clientes = clientes;
        this.arriendos = arriendos;
        initComponents();
    }

    private void initComponents() {
        setTitle("Pagar Cuotas de Arriendos");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Pagar Cuotas de Arriendos");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(200, 10, 400, 30);
        add(lblTitulo);

        JLabel lblCliente = new JLabel("Seleccione Cliente:");
        lblCliente.setBounds(30, 50, 150, 30);
        add(lblCliente);

        cmbClientes = new JComboBox<>(clientes.toArray(new Cliente[0]));
        cmbClientes.setBounds(200, 50, 200, 30);
        add(cmbClientes);

        JLabel lblArriendos = new JLabel("Arriendos:");
        lblArriendos.setBounds(30, 100, 150, 30);
        add(lblArriendos);

        lstArriendos = new JList<>();
        JScrollPane scrollArriendos = new JScrollPane(lstArriendos);
        scrollArriendos.setBounds(200, 100, 200, 150);
        add(scrollArriendos);

        btnMostrarArriendos = new JButton("Mostrar Arriendos");
        btnMostrarArriendos.setBounds(450, 50, 200, 30);
        add(btnMostrarArriendos);
        btnMostrarArriendos.addActionListener(this::mostrarArriendos);

        JLabel lblCuotas = new JLabel("Cuotas:");
        lblCuotas.setBounds(30, 300, 150, 30);
        add(lblCuotas);

        tablaCuotas = new JTable(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Número", "Valor", "¿Pagada?"}
        ));
        JScrollPane scrollCuotas = new JScrollPane(tablaCuotas);
        scrollCuotas.setBounds(200, 300, 400, 200);
        add(scrollCuotas);

        btnPagarCuota = new JButton("Pagar Cuota");
        btnPagarCuota.setBounds(650, 300, 120, 30);
        add(btnPagarCuota);
        btnPagarCuota.addActionListener(this::pagarCuota);

        setLocationRelativeTo(null);
    }

    private void mostrarArriendos(ActionEvent e) {
        Cliente cliente = (Cliente) cmbClientes.getSelectedItem();
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente.");
            return;
        }

        List<ArriendoCuota> arriendosCliente = new ArrayList<>();
        for (ArriendoCuota arriendo : arriendos) {
            if (arriendo.getCliente().equals(cliente)) {
                arriendosCliente.add(arriendo);
            }
        }

        lstArriendos.setListData(arriendosCliente.toArray(new ArriendoCuota[0]));
        lstArriendos.addListSelectionListener(e1 -> {
            if (!e1.getValueIsAdjusting()) {
                ArriendoCuota arriendoSeleccionado = lstArriendos.getSelectedValue();
                if (arriendoSeleccionado != null) {
                    cargarCuotas(arriendoSeleccionado);
                }
            }
        });
    }

    private void cargarCuotas(ArriendoCuota arriendoSeleccionado) {
        DefaultTableModel model = (DefaultTableModel) tablaCuotas.getModel();
        model.setRowCount(0);
        for (CuotaArriendo cuota : arriendoSeleccionado.getCuotas()) {
            model.addRow(new Object[]{cuota.getNumCuota(), cuota.getValorCuota(), cuota.isPagada()});
        }
    }

    private void pagarCuota(ActionEvent e) {
        int selectedRow = tablaCuotas.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una cuota para pagar.");
            return;
        }

        ArriendoCuota arriendoSeleccionado = lstArriendos.getSelectedValue();
        if (arriendoSeleccionado != null) {
            CuotaArriendo cuotaSeleccionada = arriendoSeleccionado.getCuotas().get(selectedRow);
            if (cuotaSeleccionada.isPagada()) {
                JOptionPane.showMessageDialog(this, "Esta cuota ya ha sido pagada.");
                return;
            }

            cuotaSeleccionada.pagarCuota();
            JOptionPane.showMessageDialog(this, "Cuota pagada con éxito.");
            cargarCuotas(arriendoSeleccionado);
        }
    }
}
