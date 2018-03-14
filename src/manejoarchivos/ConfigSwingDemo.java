package manejoarchivos;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * This program demonstrates using java.util.Properties class to read and write
 * settings for Java application.
 * @author www.codejava.net
 *
 */
public class ConfigSwingDemo extends JFrame {
	private File configFile = new File("rutas.properties");
	private Properties configProps;
	
	private JLabel labelHost = new JLabel("Origen LOGS: ");
	private JLabel labelPort = new JLabel("Destino Reporte Logs F01C: ");
	
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
		textPort.setText(configProps.getProperty("dir.destino.logs.f01c"));
		
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
		configProps.setProperty("dir.destino.logs.f01c", textPort.getText());
		
		OutputStream outputStream = new FileOutputStream(configFile);
		configProps.store(outputStream, "host setttings");
		outputStream.close();
		
		String destino_logs_F01C = configProps.getProperty("dir.destino.logs.f01c");
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
       
		//CREO EL DIRECTORIO
		System.out.println("ruta archivo: " + ruta_destinoF01C + nom_carpeta);
		File destino_f01c = new File(ruta_destinoF01C + nom_carpeta);
		
		//ARCHIVO F01C
		File archivo_destino_f01c = new File(ruta_destinoF01C + nom_carpeta + nom_archivo_f01c);
		
		//si no existe el directorio que lo cree.
		if(!destino_f01c.exists()) {
			destino_f01c.mkdirs();
			}
		
		
        BufferedWriter bw2;
        //si no existe creo el archivo.
        if(!archivo_destino_f01c.exists()) {
        	
           	System.out.println(ruta_destinoF01C + nom_carpeta + nom_archivo_f01c);
            bw2 = new BufferedWriter(new FileWriter(ruta_destinoF01C + nom_carpeta + nom_archivo_f01c));
           // bw.write("El directorio y archivo .txt, ya estaba creado.");
            bw2.close();
        }
           
        //modifico el directorio donde está el log destino f01c
        //configProps.setProperty("dir.destino.logs.f01c", archivo_destino_f01c + nom_archivo_f01c);
       
		System.out.println("Contenido Propiedad: " + configProps.getProperty("dir.destino.logs.f01c"));
		System.out.println(destino_f01c );
		System.out.println(nom_archivo_f01c);
		System.out.println("concatenado " + destino_f01c  );
		configProps.setProperty("dir.destino.logs.f01c", ruta_destinoF01C + nom_carpeta);
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