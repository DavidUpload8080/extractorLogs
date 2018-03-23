package manejoarchivos;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;

public class ModificarRutas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8404160548740925048L;
	private File configFile = new File("rutas.properties");
	private Properties configProps;
	private JPanel contentPane;
	private JTextField textHost;
	private JTextField textPort;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificarRutas frame = new ModificarRutas();
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
	public ModificarRutas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 218);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JLabel lblNewLabel = new JLabel("Origen LOGS: ");
		lblNewLabel.setBounds(36, 50, 91, 24);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 12));
		contentPane.add(lblNewLabel);
		
		JLabel lblDestinoLogs = new JLabel("Destino LOGS:");
		lblDestinoLogs.setBounds(36, 101, 91, 24);
		lblDestinoLogs.setFont(new Font("Arial", Font.BOLD, 12));
		contentPane.add(lblDestinoLogs);
		
		textHost = new JTextField();
		textHost.setBounds(137, 53, 285, 20);
		contentPane.add(textHost);
		textHost.setColumns(10);
		
		textPort = new JTextField();
		textPort.setBounds(137, 104, 285, 20);
		contentPane.add(textPort);
		textPort.setColumns(10);
		
		JLabel lblDirectoriosDelSistema = new JLabel("Directorios del Sistema");
		lblDirectoriosDelSistema.setFont(new Font("Arial", Font.BOLD, 14));
		lblDirectoriosDelSistema.setBounds(137, 11, 163, 18);
		contentPane.add(lblDirectoriosDelSistema);
		
		JButton btnOrigenLogs = new JButton("Origen Logs");
		btnOrigenLogs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Selecciona el directorio de Origen de LOGS");
				jfc.setAcceptAllFileFilterUsed(false);			
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jfc.showSaveDialog(jfc);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					System.out.println(jfc.getSelectedFile().getPath());
					textHost.setText(jfc.getSelectedFile().getPath());
				}
				
				
			}
		});
		btnOrigenLogs.setBounds(445, 52, 134, 23);
		contentPane.add(btnOrigenLogs);
		
		JButton btnDestinoLogs = new JButton("Destino Logs");
		btnDestinoLogs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Selecciona el directorio de destino de los LOG");
				jfc.setAcceptAllFileFilterUsed(false);			
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jfc.showSaveDialog(jfc);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					System.out.println(jfc.getSelectedFile().getPath());
					textPort.setText(jfc.getSelectedFile().getPath());
				}
			}
		});
		btnDestinoLogs.setBounds(445, 103, 134, 23);
		contentPane.add(btnDestinoLogs);
		
		JButton buttonSave = new JButton("Guardar");
		buttonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					saveProperties();
					JOptionPane.showMessageDialog(ModificarRutas.this, 
							"!Directorios grabados Exitosamente!");		
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(ModificarRutas.this, 
							"Error guardando los directorios: " + ex.getMessage());		
				}
			}
		});
		
		try {
			loadProperties();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "El archivo rutas.properties no existe, se cargo el por .properties por defecto.");
		}
		textHost.setText(configProps.getProperty("dir.archivo.origen.logs"));
		textPort.setText(configProps.getProperty("dir.destino.logs"));
		
		
		
		buttonSave.setBounds(178, 157, 91, 23);
		contentPane.add(buttonSave);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
//				GenerarLog frame = new GenerarLog();
				
				setVisible(false);
			}
		});
		btnVolver.setBounds(346, 157, 91, 23);
		contentPane.add(btnVolver);
		
		
		
	}

	private void loadProperties() throws IOException {
		Properties defaultProps = new Properties();
		// sets default properties
		//defaultProps.setProperty("dir.archivo.origen.logs", "www.codejava.net");
		//defaultProps.setProperty("dir.destino.logs.f01c", "");
		
		configProps = new Properties(defaultProps);
		
		// loads properties from file
		InputStream inputStream = new FileInputStream(configFile);
		configProps.load(inputStream);
		inputStream.close();
	}
	
	private void saveProperties() throws IOException {
		
		configProps.setProperty("dir.archivo.origen.logs", textHost.getText());
		configProps.setProperty("dir.destino.logs", textPort.getText());
		
		OutputStream outputStream = new FileOutputStream(configFile);
		configProps.store(outputStream, "host setttings");
		outputStream.close();
		
		String destino_logs_F01C = configProps.getProperty("dir.destino.logs");
		//creo los directorios.
		createDirectories(destino_logs_F01C);
		
		
	}
	
	/**
	 * Método para crear los directorios, carpetas y los archivos, según cada reporte.
	 * @throws IOException 
	 */
	public void createDirectories(String ruta_destinoF01C) throws IOException {
		
		
		
		String nom_archivo_f01c = null;
		String nom_carpeta = null;
		
		String nom_archivo_mx = null;
		String nom_carpeta_mx = null;
		
		String nom_archivo_pers = null;
		String nom_carpeta_pers = null;
		
		
		System.out.println(ruta_destinoF01C);
		
		//nombres f01c ARCHIVO Y CARPETA.
		nom_archivo_f01c = "\\contenedorlogsF01C.txt";
		nom_carpeta = "\\LOGS_F01C";
		
		//nombres MX ARCHIVO Y CARPETA.
		nom_archivo_mx = "\\contenedorlogsMX.txt";
		nom_carpeta_mx = "\\LOGS_MX";
		
		//nombres Personalizado ARCHIVO Y CARPETA.
		nom_archivo_pers = "\\contenedorlogsPERS.txt";
		nom_carpeta_pers = "\\LOGS_PERS";
		
		//destino f01c
		String destino = ruta_destinoF01C + "\\ContenedorLogSIGIR"+ nom_carpeta;
		//destino MX
		String destinoMX = ruta_destinoF01C + "\\ContenedorLogSIGIR"+ nom_carpeta_mx;
		//destino Personalizado
		String destinoPer = ruta_destinoF01C + "\\ContenedorLogSIGIR"+ nom_carpeta_pers;
		
		String archivo = ruta_destinoF01C +"\\ContenedorLogSIGIR"+ nom_carpeta + nom_archivo_f01c;
		String archivoMX = ruta_destinoF01C +"\\ContenedorLogSIGIR"+ nom_carpeta_mx + nom_archivo_mx;
		String archivoPERS = ruta_destinoF01C +"\\ContenedorLogSIGIR"+ nom_carpeta_pers + nom_archivo_pers;
		
		//CREO EL DIRECTORIO
		System.out.println("ruta archivo: " + ruta_destinoF01C + "\\ContenedorLogSIGIR"+ nom_carpeta);
		System.out.println("ruta archivo: " + ruta_destinoF01C + "\\ContenedorLogSIGIR"+ nom_carpeta_mx);
		System.out.println("ruta archivo: " + ruta_destinoF01C + "\\ContenedorLogSIGIR"+ nom_carpeta_pers);
		
		File destino_f01c = new File(destino);
		File destino_MX = new File(destinoMX);
		File destino_PERS = new File(destinoPer);


		//ARCHIVO F01C
		File archivo_destino_f01c = new File(archivo);
		File archivo_destino_MX = new File(archivoMX);
		File archivo_destino_PERS = new File(archivoPERS);
		
		//si no existe el directorio que lo cree.
		if(!destino_f01c.exists()) {
			destino_f01c.mkdirs();
		}
		if(!destino_MX.exists()) {
			destino_MX.mkdirs();
		}
		if(!destino_PERS.exists()) {
			destino_PERS.mkdirs();
		}
        
		BufferedWriter bw1;
		BufferedWriter bw2;
		BufferedWriter bw3;

		
			
		
		
		//si no existe creo el archivo.
        if(!archivo_destino_f01c.exists()) {
        	
           	System.out.println(archivo);
            bw1 = new BufferedWriter(new FileWriter(archivo));
           // bw.write("El directorio y archivo .txt, ya estaba creado.");
            bw1.close();
            
        }
        if(!archivo_destino_MX.exists()) {
        	
           	System.out.println(archivoMX);
            bw2 = new BufferedWriter(new FileWriter(archivoMX));
           // bw.write("El directorio y archivo .txt, ya estaba creado.");
            bw2.close();
            
        }
        if(!archivo_destino_PERS.exists()) {
 	
	    	System.out.println(archivoPERS);
	    	bw3 = new BufferedWriter(new FileWriter(archivoPERS));
	    	// bw.write("El directorio y archivo .txt, ya estaba creado.");
	    	bw3.close();
     
        }
           
        //modifico el directorio donde está el log destino f01c        
		configProps.setProperty("dir.destino.logs.f01c", archivo);
		configProps.setProperty("dir.destino.logs.mx", archivoMX);
		configProps.setProperty("dir.destino.logs.pers", archivoPERS);		
		configProps.setProperty("dir.destino.logs", ruta_destinoF01C);
		
		OutputStream outputStream1 = new FileOutputStream(configFile);
		configProps.store(outputStream1, "logs destino setttings");
		
		System.out.println("Contenido Propiedad: " + configProps.getProperty("dir.destino.logs.f01c"));
		System.out.println("Contenido Propiedad: " + configProps.getProperty("dir.destino.logs.mx"));
		System.out.println("Contenido Propiedad: " + configProps.getProperty("dir.destino.logs.pers"));
		outputStream1.close();
		
	//	JOptionPane.showMessageDialog(null, "Rutas y carpetas creadas!");

	}
	
}
