package manejoarchivos;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

import com.toedter.calendar.JDateChooser;

import utileria.Archivos;
import utileria.Propiedades;

public class GenerarLog extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4854844716897693007L;
	private JPanel contentPane;
	private JDateChooser dateChooser;

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
		
	
		
		JLabel lblFechaDeProceso = new JLabel("Seleccionar Fecha de Proceso: ");
		lblFechaDeProceso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFechaDeProceso.setBounds(66, 54, 190, 20);
		contentPane.add(lblFechaDeProceso);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(266, 54, 95, 20);
		contentPane.add(dateChooser);
		
		JButton btnNewButton = new JButton("Extraer Registros LOG");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				dateChooser.getDate();
		        SimpleDateFormat formateador = new SimpleDateFormat("yyMMdd");
		        SimpleDateFormat formateador2 = new SimpleDateFormat("yyyy-MM-dd");
		        
		        //Obtener los directorios de los archivos.
		        
		        
		        
				String fecha_proceso = formateador.format(dateChooser.getDate());
				String fecha_proceso_2 = formateador2.format(dateChooser.getDate());
				//dir.destino.logs.f01c=C\:\\LOGS\\LOGS_F01C\\LOGS_F01C
			    //dir.nombre.archivo.f01c=contenedorlogsF01C.txt
				try {
					String listadoF01C = Propiedades.showProperties("dir.archivo.listado.f01c");
					String ruta_destino_logs = Propiedades.showProperties("dir.destino.logs.f01c");
					String nombre_archivoF01C = Propiedades.showProperties("dir.nombre.archivo.f01c");
					String dir_archivos_origen = Propiedades.showProperties("dir.archivo.origen.logs");
				//concatenar destinoLogs + archivoF01C
					
					String ruta_archivo_f01c = null;
					ruta_archivo_f01c = ruta_destino_logs;
					JOptionPane.showMessageDialog(null, fecha_proceso);
					
					Archivos.extraerLogs(listadoF01C, 
							ruta_archivo_f01c, 
										dir_archivos_origen, 
										fecha_proceso, fecha_proceso_2);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
				
			
			}
		});
		
		btnNewButton.setBounds(97, 104, 232, 23);
		contentPane.add(btnNewButton);
		
		
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
