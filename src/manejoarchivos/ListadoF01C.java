package manejoarchivos;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ListadoF01C extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6546916840195465570L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListadoF01C frame = new ListadoF01C();
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
	 */
	public ListadoF01C() {
		setTitle("Carga de Listado Reporte F01C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 36, 293, 226);
		contentPane.add(textArea);
		
		textField = new JTextField();
		textField.setToolTipText("Ruta del Archivo..");
		textField.setBounds(10, 5, 293, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnSeleccionar = new JButton("Seleccionar");
		btnSeleccionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Creamos el objeto JFileChooser
				JFileChooser fc=new JFileChooser();
				
				//Creamos el filtro
				FileNameExtensionFilter filtroTxt = new FileNameExtensionFilter("*.TXT", "txt");
				FileNameExtensionFilter filtroLOG = new FileNameExtensionFilter("*.LOG", "log");
				
				//Le indicamos el filtro
				fc.setFileFilter(filtroTxt);
				fc.setFileFilter(filtroLOG);

				//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
				int seleccion=fc.showOpenDialog(contentPane);
				
				//Si el usuario, pincha en aceptar
				if(seleccion==JFileChooser.APPROVE_OPTION){
				 
				    //Seleccionamos el fichero
				    File fichero=fc.getSelectedFile();
				 
				    //Ecribe la ruta del fichero seleccionado en el campo de texto
				    textField.setText(fichero.getAbsolutePath());
				 
				    try(FileReader fr=new FileReader(fichero)){
				        String cadena="";
				        int valor=fr.read();
				        while(valor!=-1){
				            cadena=cadena+(char)valor;
				            valor=fr.read();
				        }
				        textArea.setText(cadena);
				    } catch (IOException e1) {
				        e1.printStackTrace();
				    }
				}
				
				
				
			}
		});
		btnSeleccionar.setBounds(313, 4, 119, 23);
		contentPane.add(btnSeleccionar);
		
		JButton btnNewButton = new JButton("Cargar\r\n");
		btnNewButton.setBounds(313, 110, 119, 55);
		contentPane.add(btnNewButton);
	}

}
