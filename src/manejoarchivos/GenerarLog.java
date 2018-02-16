package manejoarchivos;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

public class GenerarLog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4854844716897693007L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerarLog frame = new GenerarLog();
					frame.setTitle("Extracción de Registros LOG");
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
	public GenerarLog() {
		
		setTitle("Extracci\u00F3n de Registros LOG\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Extraer Registros LOG");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(97, 104, 232, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblFechaDeProceso = new JLabel("Seleccionar Fecha de Proceso: ");
		lblFechaDeProceso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaDeProceso.setBounds(66, 54, 190, 20);
		contentPane.add(lblFechaDeProceso);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(266, 54, 95, 20);
		contentPane.add(dateChooser);
		
		JButton btnVolverAlMen = new JButton("Volver al Men\u00FA Principal");
		btnVolverAlMen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
//				GenerarLog frame = new GenerarLog();
				
				setVisible(false);
				
				
				
			}
		});
		btnVolverAlMen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnVolverAlMen.setBounds(192, 228, 232, 23);
		contentPane.add(btnVolverAlMen);
	}
}
