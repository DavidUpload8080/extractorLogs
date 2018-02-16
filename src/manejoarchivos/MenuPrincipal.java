package manejoarchivos;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;

public class MenuPrincipal {

	private JFrame menuPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal window = new MenuPrincipal();
					window.menuPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		menuPrincipal = new JFrame();
		menuPrincipal.getContentPane().setLayout(null);
		menuPrincipal.setSize(400, 300);
		menuPrincipal.setLocationRelativeTo(null);
		
		
		JButton btnProcesoDiario = new JButton("Logs Proceso Diario F01C");
		btnProcesoDiario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JOptionPane.showInputDialog(null, "Indique fecha de proceso");
				GenerarLog generarLog = new GenerarLog();
				generarLog.setVisible(true);
				menuPrincipal.setVisible(false);
				
			}
		});
		
		btnProcesoDiario.setBounds(106, 101, 192, 23);
		menuPrincipal.getContentPane().add(btnProcesoDiario);
		
		JButton btnNewButton_1 = new JButton("Logs Proceso Mensual MX");
		btnNewButton_1.setBounds(106, 146, 192, 23);
		menuPrincipal.getContentPane().add(btnNewButton_1);
		
		JLabel lblSeleccioneUnaOpcin = new JLabel("Seleccione una Opci\u00F3n");
		lblSeleccioneUnaOpcin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccioneUnaOpcin.setBounds(106, 67, 151, 23);
		menuPrincipal.getContentPane().add(lblSeleccioneUnaOpcin);
		
		JLabel lblExtractorDeLogs = new JLabel("Extractor de LOGs SIGIR");
		lblExtractorDeLogs.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExtractorDeLogs.setBounds(96, 11, 183, 45);
		menuPrincipal.getContentPane().add(lblExtractorDeLogs);
		
		JMenuBar menuBar = new JMenuBar();
		menuPrincipal.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo\r\n");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmAbrirArchivo = new JMenuItem("Directorio Origen Logs\r\n");
		mnArchivo.add(mntmAbrirArchivo);
		
		JMenu mnDirectorioDestino = new JMenu("Directorio Destino");
		mnArchivo.add(mnDirectorioDestino);
		
		JMenuItem mntmProcesoDiario = new JMenuItem("Proceso Diario");
		mnDirectorioDestino.add(mntmProcesoDiario);
		
		JMenuItem mntmProcesoMensual = new JMenuItem("Proceso Mensual");
		mnDirectorioDestino.add(mntmProcesoMensual);
		
		JMenuItem mntmPersonalizado_1 = new JMenuItem("Personalizado");
		mnDirectorioDestino.add(mntmPersonalizado_1);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		JMenu mnCargarListados = new JMenu("Importar Listados Interfaces\r\n");
		menuBar.add(mnCargarListados);
		
		JMenuItem mntmListadoProcesoDiario = new JMenuItem("Reporte F01C");
		mntmListadoProcesoDiario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				menuPrincipal.setVisible(false);
				
				ListadoF01C listF01C = new ListadoF01C();
				listF01C.setVisible(true);
				
			}
		});
		
		mnCargarListados.add(mntmListadoProcesoDiario);
		
		JMenuItem mntmProcesoMensual_1 = new JMenuItem("Reportes MX");
		mnCargarListados.add(mntmProcesoMensual_1);
		
		JMenuItem mntmPersonalizado = new JMenuItem("Personalizado");
		mnCargarListados.add(mntmPersonalizado);
		
		
	}
	
	public void setVisible(boolean opcion) {
		MenuPrincipal window = new MenuPrincipal();
		window.menuPrincipal.setVisible(opcion);
	}
}
