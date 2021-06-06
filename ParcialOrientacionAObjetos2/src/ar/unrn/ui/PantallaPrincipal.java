package ar.unrn.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.unrn.modelo.Combustible;
import ar.unrn.modelo.EstacionDeServicio;
import ar.unrn.modelo.Observer;
import ar.unrn.modelo.RepositorioCombustible;

import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Point;

public class PantallaPrincipal extends JFrame {

	private JPanel contentPane;
	private List<Observer> monitores;
	EstacionDeServicio estacionDeServicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PantallaPrincipal(EstacionDeServicio estacionDeServicio,List<Observer> monitores) {
		this.monitores = monitores;
		this.estacionDeServicio = estacionDeServicio;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(SystemColor.activeCaption);
		contentPane.add(contentPane_1, BorderLayout.CENTER);

		JButton boton_cargar_combustible = new JButton("Cargar de Combustible");
		boton_cargar_combustible.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PantallaCargaCombustible(estacionDeServicio,monitores).setVisible(true);
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Informacion", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		boton_cargar_combustible.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
		boton_cargar_combustible.setBounds(115, 25, 206, 81);
		contentPane_1.add(boton_cargar_combustible);

		JButton boton_consultar_ventas = new JButton("Consultar Ventas");
		boton_consultar_ventas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PantallaConsultaVentas(estacionDeServicio).setVisible(true);
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Informacion", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		boton_consultar_ventas.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
		boton_consultar_ventas.setBounds(129, 136, 176, 81);
		contentPane_1.add(boton_consultar_ventas);
		this.setLocationRelativeTo(null);
	}

}
