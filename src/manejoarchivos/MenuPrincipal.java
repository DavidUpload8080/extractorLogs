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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

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
		menuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPrincipal.getContentPane().setLayout(null);
		menuPrincipal.setSize(400, 300);
		menuPrincipal.setLocationRelativeTo(null);
		
		
		JButton btnProcesoDiario = new JButton("Logs Proceso Diario F01C");
		btnProcesoDiario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				GenerarLog generarLog = new GenerarLog();
				generarLog.setVisible(true);
				menuPrincipal.setVisible(false);
				
			}
		});
		
		btnProcesoDiario.setBounds(106, 101, 192, 23);
		menuPrincipal.getContentPane().add(btnProcesoDiario);
		
		JButton btnNewButton_1 = new JButton("Logs Proceso Mensual MX");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				GenerarLogMX generarLogMX = new GenerarLogMX();
				generarLogMX.setVisible(true);
				menuPrincipal.setVisible(false);
			}
		});
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
		
		JButton btnNewButton = new JButton("Logs Personalizado");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				GenerarLogPERS generarLogPers = new GenerarLogPERS();
				generarLogPers.setVisible(true);
				menuPrincipal.setVisible(false);
			}
		});
		btnNewButton.setBounds(106, 184, 192, 23);
		menuPrincipal.getContentPane().add(btnNewButton);
		
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TestConnection test = new TestConnection();
				test.setVisible(true);
				menuPrincipal.setVisible(false);
				
			}
		});
		btnTest.setBounds(116, 218, 91, 23);
		menuPrincipal.getContentPane().add(btnTest);
		
		JMenuBar menuBar = new JMenuBar();
		menuPrincipal.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo\r\n");
		menuBar.add(mnArchivo);
		
		JMenu mnConfigurar = new JMenu("Configurar\r\n");
		mnArchivo.add(mnConfigurar);
		
		JMenuItem mntmRutas = new JMenuItem("Rutas Sistema");
		mntmRutas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ModificarRutas config = new ModificarRutas();
				config.setVisible(true);
				menuPrincipal.setVisible(false);
				
			}
		});
		mnConfigurar.add(mntmRutas);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		mnArchivo.add(mntmSalir);
		
		JMenu mnCargarListados = new JMenu("Importar Listados Interfaces\r\n");
		menuBar.add(mnCargarListados);
		
		JMenuItem mntmListadoProcesoDiario = new JMenuItem("Reporte F01C");
		mntmListadoProcesoDiario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				menuPrincipal.setVisible(false);
				
				ListadoF01C listF01C;
				try {
					listF01C = new ListadoF01C();
					
					listF01C.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		mnCargarListados.add(mntmListadoProcesoDiario);
		
		JMenuItem mntmProcesoMensual_1 = new JMenuItem("Reportes MX");
		mntmProcesoMensual_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				menuPrincipal.setVisible(false);
				
				ListadoMX listMX;
				try {
					listMX = new ListadoMX();
					
					listMX.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		mnCargarListados.add(mntmProcesoMensual_1);
		
		JMenuItem mntmPersonalizado = new JMenuItem("Personalizado");
		mntmPersonalizado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				menuPrincipal.setVisible(false);
				
				ListadoPERS listPERS;
				try {
					listPERS = new ListadoPERS();
					
					listPERS.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnCargarListados.add(mntmPersonalizado);
		
		
	}
	
	public void setVisible(boolean opcion) {
		MenuPrincipal window = new MenuPrincipal();
		window.menuPrincipal.setVisible(opcion);
	}
}
