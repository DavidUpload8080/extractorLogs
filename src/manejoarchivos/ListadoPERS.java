package manejoarchivos;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import utileria.Propiedades;

public class ListadoPERS extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7813361138047963707L;
	/**
	 * 
	 */
	
	private JPanel contentPane;	
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoPERS frame = new ListadoPERS();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public ListadoPERS() throws Exception {
		setTitle("Carga de Listado Reporte Personalizado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 468, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
			}
		});
		btnVolver.setBounds(313, 239, 119, 23);
		contentPane.add(btnVolver);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 36, 293, 226);

		JScrollPane scroll = new JScrollPane (textArea);
		scroll.setBounds(10, 36, 293, 226);
		contentPane.add(scroll);
		
		//Lleno el textArea con el m�todo leerListado
		String nombre_archivo_pers = Propiedades.showProperties("dir.archivo.listado.pers");
		System.out.println("listado: " + nombre_archivo_pers);  
		leerListado(nombre_archivo_pers, textArea);
	
		
		JButton btnNewButton = new JButton("Guardar Cambios\r\n");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				try {
					String nombre_archivo_mx = Propiedades.showProperties("dir.archivo.listado.pers");
					
					//actualizo el archivo listado F01C.				
					cargarListado(nombre_archivo_mx , textArea.getText(), textArea);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
									
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(313, 110, 137, 55);
		contentPane.add(btnNewButton);
		
		JLabel lblListadoInterfacesFc = new JLabel("Listado Interfaces Personalizado");
		lblListadoInterfacesFc.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblListadoInterfacesFc.setBounds(80, 11, 223, 14);
		contentPane.add(lblListadoInterfacesFc);
		
		
	}
	
	
	public void leerListado(String nombreArchivo, JTextArea textArea) {

		//File archivo = new File(nombreArchivo);
		
		
		textArea.setText("");
		// lectura = entrada.readLine();

		try {
			
			BufferedReader entrada;
			System.out.println(nombreArchivo);
			//HAY QUE OCUPAR EL getResourceAsStream para llamar .txt dentro del jar
			//entrada = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(nombreArchivo)));
		
			
		//	InputStream inputStream = new FileInputStream(nombreArchivo);
			
		//	entrada = new BufferedReader(new InputStreamReader(inputStream));
			entrada = new BufferedReader(new InputStreamReader(new FileInputStream(nombreArchivo)));

			String lectura = "";
			
			
			
			while ((lectura = entrada.readLine()) != null) {
				
				textArea.append(lectura + "\n");	
				
				
			}
		
			entrada.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}// fin m�todo.
	
	/**
	 * M�todo para cargar o modificar el lisado.
	 * 
	 * @param String nombreArchivo (Directorio)
	 * @param String texto
	 */
	public void cargarListado(String nombreArchivo, String texto, JTextArea textArea) {
		
		
		try {
			
			BufferedWriter bw;
			bw = new BufferedWriter(new FileWriter(nombreArchivo));
			bw.write("");
			bw.close();
			
			//abro el archivo y lo defino en true para poder ir apilando cada registro.
			PrintWriter salida = new PrintWriter(new FileWriter(nombreArchivo, false));

			salida.println(texto);
			salida.close(); 			
			
			JOptionPane.showMessageDialog(null, "Listado Modificado Exitosamente.");
			
			//lleno el textArea con los nuevos registros.
			leerListado(nombreArchivo, textArea);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
