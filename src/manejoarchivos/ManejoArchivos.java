package manejoarchivos;

//realizo un import de todos los métodos estáticos
import static utileria.Archivos.leerArchivoPesado;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManejoArchivos {

//	private static final String NOMBRE_ARCHIVO = "/home/david/Escritorio/logs/logejemplo2.txt";
	private static  String DIR_ARCHIVOS_ORIGEN = "C:\\Users\\David\\Desktop\\logs\\ERR_OP07171127.TCL.LOG";
	private static  String DIR_ARCHIVOS_ORIGEN2 = "C:\\Users\\David\\Desktop\\logs";
	private static final String NOM_ARCHIVO_LISTADO = "C:\\Users\\David\\Desktop\\logs\\listado_F01.txt";
	private static final String NOMBRE_ARCHIVO_DESTINO = "C:\\Users\\David\\Desktop\\logs\\contenedorlogs.txt";
	public static void main(String[] args) {
		
		//crear un archivo
		//crearArchivo(NOMBRE_ARCHIVO);
		
		//escribir el archivo
		//escribirArchivo(NOMBRE_ARCHIVO);
		
		//leer de un archivo
		//leerArchivo(NOMBRE_ARCHIVO_ORIGEN);
		
		FileInputStream inputListado = null; 
		Scanner sc = null;
		try {
			
			inputListado = new FileInputStream(NOM_ARCHIVO_LISTADO);
			
			List<String> listado = new ArrayList<String>();
			
			sc = new Scanner(inputListado, "UTF-8");
			
			BufferedWriter bw;
			bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO_DESTINO));
			bw.write("");
			bw.close();
			
			while (sc.hasNextLine()) {
				
				String interfaz = sc.nextLine();
				String directorio =  DIR_ARCHIVOS_ORIGEN2 + "\\ERR_" + interfaz +"180213"+".TCL.LOG";
				//listado.add(archivoOrigen);
				System.out.println(directorio);
				System.out.println(DIR_ARCHIVOS_ORIGEN);
						
					//ejecuto el m�todo para llenar el contenedor de logs.
					leerArchivoPesado(directorio, interfaz);
			
			}// fin while
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
	
		//Anexar información a un archivo
		//anexarArchivo(NOMBRE_ARCHIVO);
		
		//leer otra vez el archivo
		//leerArchivo(NOMBRE_ARCHIVO);
		
	}

	
	
}
