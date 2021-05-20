package ar.unrn.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.unrn.modelo.Combustible;
import ar.unrn.modelo.CombustibleComun;
import ar.unrn.modelo.CombustibleSuper;
import ar.unrn.modelo.EstacionDeServicio;
import ar.unrn.modelo.RegistroCarga;
import ar.unrn.modelo.RepositorioCombustible;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaCargaCombustible extends JFrame {

	private JPanel contentPane;

	JSpinner spinner;
	EstacionDeServicio estacionDeServicio = new EstacionDeServicio(null);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaCargaCombustible frame = new PantallaCargaCombustible(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaCargaCombustible(EstacionDeServicio estacionDeServicio) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		this.estacionDeServicio = estacionDeServicio;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Tipo combustible");
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		lblNewLabel.setForeground(SystemColor.textHighlightText);
		lblNewLabel.setBounds(40, 97, 151, 27);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Cantidad de Litros");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		lblNewLabel_1.setBounds(40, 40, 151, 27);
		contentPane.add(lblNewLabel_1);

		this.spinner = new JSpinner();
		spinner.setBounds(174, 40, 70, 20);
		contentPane.add(spinner);

		JComboBox comboBox = new JComboBox();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
//
//				if (comboBox.getSelectedItem().toString().equals("Super"))
//					combustible = new CombustibleSuper(comboBox.getSelectedItem().toString(), 90);
//				else if (comboBox.getSelectedItem().toString().equals("Comun"))
//					combustible = new CombustibleComun(comboBox.getSelectedItem().toString(), 70);
//
			}
		});
		comboBox.setBounds(174, 96, 92, 22);
		contentPane.add(comboBox);

		List<String> tiposDecombustibles;

		tiposDecombustibles = this.estacionDeServicio.obtenerTiposCombustibles();

		for (String tiposcombustible : tiposDecombustibles) {
			comboBox.addItem(tiposcombustible.toString());
		}

		JButton btnNewButton = new JButton("Confirmar Pago");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String tipoCombustible = comboBox.getSelectedItem().toString();
					int cantidadLitros = Integer.parseInt(spinner.getValue().toString());

					estacionDeServicio.realizarVenta(cantidadLitros, tipoCombustible);

					JOptionPane.showMessageDialog(null, "CARGA EXITOSA", "Mensaje confirmacion",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(81, 179, 125, 23);
		contentPane.add(btnNewButton);

		JButton btnConsultarMontoTotal = new JButton("Consultar Monto Total");
		btnConsultarMontoTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipoCombustible = comboBox.getSelectedItem().toString();
				int cantidadLitros = Integer.parseInt(spinner.getValue().toString());
				try {
					JOptionPane.showMessageDialog(null,
							" \n - Combustible: " + comboBox.getSelectedItem().toString() + "\n - Monto Total: "
									+ estacionDeServicio.montoTotalCombustible(cantidadLitros, tipoCombustible),
							"Informacion Monto Total", JOptionPane.INFORMATION_MESSAGE);
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}

		});
		btnConsultarMontoTotal.setBounds(223, 179, 168, 23);
		contentPane.add(btnConsultarMontoTotal);
		this.setLocationRelativeTo(null);
	}

}
