package manejoarchivos;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;


/**
 * This program demonstrates using java.util.Properties class to read and write
 * settings for Java application.
 * @author www.codejava.net
 *
 */
public class ConfigSwingDemo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5337009801050296613L;
	private File configFile = new File("rutas.properties");
	private Properties configProps;
	private JButton btnOrigenLogs = new JButton("Origen Logs");
	private JButton btnDestinoLogs = new JButton("Destino Logs");
	private JLabel labelHost = new JLabel("Origen LOGS: ");
	

	private JLabel labelPort = new JLabel("Destino Reportes Logs: ");
	
	private JTextField textHost = new JTextField(20);
	private JTextField textPort = new JTextField(20);
	
	private JButton buttonSave = new JButton("Save");
	
	public ConfigSwingDemo() {
		super("Properties Configuration Demo");
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(10, 10, 5, 10);
		constraints.anchor = GridBagConstraints.WEST;
		
		
		add(labelHost, constraints);
		
		constraints.gridx = 1;
		add(textHost, constraints);
		
		constraints.gridy = 1;
		constraints.gridx = 0;
		add(labelPort, constraints);
		
		constraints.gridx = 1;
		add(textPort, constraints);

				
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		add(buttonSave, constraints);
		
		buttonSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					saveProperties();
					JOptionPane.showMessageDialog(ConfigSwingDemo.this, 
							"!Directorios grabados Exitosamente!");		
				} catch (IOException ex) {
					JOptionPane.showMessageDialog(ConfigSwingDemo.this, 
							"Error guardando los directorios: " + ex.getMessage());		
				}
			}
		});
		
		
		
		btnOrigenLogs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Select an image");
				jfc.setAcceptAllFileFilterUsed(false);			
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jfc.showSaveDialog(jfc);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					System.out.println(jfc.getSelectedFile().getPath());
				}
			}
		});
		
		btnDestinoLogs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				jfc.setDialogTitle("Select an image");
				jfc.setAcceptAllFileFilterUsed(false);			
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnValue = jfc.showSaveDialog(jfc);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					System.out.println(jfc.getSelectedFile().getPath());
				}
			}
		});
		
	
	
		//setLayout(new FlowLayout());
		btnOrigenLogs.setBounds(22, 54, 91, 23);
		add(btnOrigenLogs);
		btnDestinoLogs.setBounds(44, 104, 91, 23);
		add(btnDestinoLogs);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		try {
			loadProperties();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "El archivo rutas.properties no existe, se cargo el por .properties por defecto.");
		}
		textHost.setText(configProps.getProperty("dir.archivo.origen.logs"));
		textPort.setText(configProps.getProperty("dir.destino.logs"));
		
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
		
		System.out.println(ruta_destinoF01C);
		nom_archivo_f01c = "\\contenedorlogsF01C.txt";
		nom_carpeta = "\\LOGS_F01C";
		String destino = ruta_destinoF01C + "\\ContenedorLogSIGIR"+ nom_carpeta;
		String archivo = ruta_destinoF01C +"\\ContenedorLogSIGIR"+ nom_carpeta + nom_archivo_f01c;
		//CREO EL DIRECTORIO
		System.out.println("ruta archivo: " + ruta_destinoF01C + "\\ContenedorLogSIGIR"+ nom_carpeta);
		File destino_f01c = new File(destino);
		
		//String archivo = ruta_destinoF01C +"\\ContenedorLogSIGIR"+ nom_carpeta + nom_archivo_f01c;
		//ARCHIVO F01C
		File archivo_destino_f01c = new File(archivo);
		
		//si no existe el directorio que lo cree.
		if(!destino_f01c.exists()) {
			destino_f01c.mkdirs();
			}
		
		
        BufferedWriter bw2;
        //si no existe creo el archivo.
        if(!archivo_destino_f01c.exists()) {
        	
           	System.out.println(archivo);
            bw2 = new BufferedWriter(new FileWriter(archivo));
           // bw.write("El directorio y archivo .txt, ya estaba creado.");
            bw2.close();
        }
           
        //modifico el directorio donde está el log destino f01c
        //configProps.setProperty("dir.destino.logs.f01c", archivo_destino_f01c + nom_archivo_f01c);
       
		System.out.println("Contenido Propiedad: " + configProps.getProperty("dir.destino.logs.f01c"));
		System.out.println(destino_f01c );
		System.out.println(nom_archivo_f01c);
		System.out.println("concatenado " + destino_f01c  );
		configProps.setProperty("dir.destino.logs.f01c", archivo);
		configProps.setProperty("dir.destino.logs", ruta_destinoF01C);
		OutputStream outputStream1 = new FileOutputStream(configFile);
		configProps.store(outputStream1, "logs destino setttings");
		
		System.out.println("Contenido Propiedad: " + configProps.getProperty("dir.destino.logs.f01c"));
		outputStream1.close();
		
		
	}

	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ConfigSwingDemo();
			}
		});
	}
}