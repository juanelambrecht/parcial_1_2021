package ar.unrn.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.SystemColor;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

import ar.unrn.modelo.RegistroCarga;
import ar.unrn.modelo.RepositorioCombustible;
import java.util.Locale;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class PantallaConsultaVentas extends JFrame {

	private JPanel contentPane;
	RepositorioCombustible repositorio;
	private JTable table;
	DefaultTableModel modelo;
	JDateChooser dateChooser;
	JDateChooser dateChooser_1;
	DefaultTableModel modeloAct;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				PantallaConsultaVentas frame = new PantallaConsultaVentas(null);
				frame.setVisible(true);

			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaConsultaVentas(RepositorioCombustible repositorio) {
		setLocale(new Locale("es", "AR"));
		setTitle("Consulta De Ventas ");
		setLocationRelativeTo(null);
		table = new JTable();
		table.setGridColor(Color.WHITE);

		this.repositorio = repositorio;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 613, 366);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(123, 11, 122, 20);
		contentPane.add(dateChooser);
		dateChooser.setDate(new Date());
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(351, 11, 122, 20);
		contentPane.add(dateChooser_1);
		dateChooser_1.setDate(new Date());
		dateChooser_1.setDateFormatString("yyyy-MM-dd");

		JLabel lblNewLabel = new JLabel("Fecha inicio");
		lblNewLabel.setForeground(SystemColor.text);
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		lblNewLabel.setBounds(30, 11, 99, 25);
		contentPane.add(lblNewLabel);

		JLabel lblFechaFin = new JLabel("Fecha fin");
		lblFechaFin.setForeground(SystemColor.text);
		lblFechaFin.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		lblFechaFin.setBounds(270, 11, 83, 25);
		contentPane.add(lblFechaFin);
		String[] titulos = { "FECHA", "LITROS CARGADOS", "MONTO FACTURADO", "TIPO COMBUSTIBLE" };
		modelo = new DefaultTableModel(new Object[][] {}, titulos);
		table.setModel(modelo);
		JButton btnNewButton = new JButton("Filtrar");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (sonFechasValidas() && verificarFecha()) {

						ActualizarTabla(repositorio.devolverRegistroDeVentas(
								new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate()),
								new SimpleDateFormat("yyyy-MM-dd").format(dateChooser_1.getDate())), titulos);
					}
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton.setBounds(498, 8, 89, 23);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(UIManager.getBorder("Button.border"));
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(0, 43, 619, 273);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(table);
		this.setLocationRelativeTo(null);

	}

	private void ActualizarTabla(List<RegistroCarga> listaRegistro, String titulos[]) {
		modeloAct = new DefaultTableModel(new Object[][] {}, titulos) {

			public boolean isCellEditable(int row, int column) {
				return false;

			}
		};

		for (RegistroCarga registro : listaRegistro) {

			modeloAct.addRow(new Object[] { registro.obtenerFechaCarga(), registro.obtenerCantidadLitros(),
					registro.obtenerMontoTotal(), registro.obtenerNombreCombustible() });
		}
		table.setModel(modeloAct);
	}

	private boolean verificarFecha() {
		if (!dateChooser.getDate().before(dateChooser_1.getDate())) {
			JOptionPane.showMessageDialog(null, "La fecha de inicio debe ser menor que la fecha fin", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	private boolean sonFechasValidas() {

		if (dateChooser.getDate() == null) {
			JOptionPane.showMessageDialog(null, "La fecha de inicio no puede estar vacia", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (dateChooser_1.getDate() == null) {
			JOptionPane.showMessageDialog(null, "La fecha de fin no puede estar vacia", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}
