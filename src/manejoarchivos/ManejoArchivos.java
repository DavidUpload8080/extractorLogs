package manejoarchivos;

//realizo un import de todos los métodos estáticos
import static utileria.Archivos.leerArchivoPesado;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManejoArchivos {

//	private static final String NOMBRE_ARCHIVO = "/home/david/Escritorio/logs/logejemplo2.txt";
	//private static  String DIR_ARCHIVOS_ORIGEN = "C:\\Users\\David\\Desktop\\logs\\ERR_OP07171127.TCL.LOG";
	private static  String DIR_ARCHIVOS_ORIGEN = "C:\\Users\\David\\Desktop\\logs";
	private static final String DIR_ARCHIVO_LISTADO_F01C = "C:\\Users\\David\\Desktop\\logs\\listado_F01.txt";
	private static final String DIR_ARCHIVO_DESTINO_LOGS = "C:\\Users\\David\\Desktop\\logs\\contenedorlogs.txt";
	private static String FECHA_PROCESO	= "";
	
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
			
			inputListado = new FileInputStream(DIR_ARCHIVO_LISTADO_F01C);
			
			List<String> listado = new ArrayList<String>();
			
			sc = new Scanner(inputListado, "UTF-8");
			
			BufferedWriter bw;
			bw = new BufferedWriter(new FileWriter(DIR_ARCHIVO_DESTINO_LOGS));
			bw.write("");
			bw.close();
			
			while (sc.hasNextLine()) {
				
				String interfaz = sc.nextLine();
				String directorio =  DIR_ARCHIVOS_ORIGEN + "\\ERR_" + interfaz + FECHA_PROCESO +".TCL.LOG";
				//listado.add(archivoOrigen);
				System.out.println(directorio);
				//System.out.println(DIR_ARCHIVOS_ORIGEN);
						
					//ejecuto el m�todo para llenar el contenedor de logs.
					leerArchivoPesado(directorio, interfaz, DIR_ARCHIVO_DESTINO_LOGS,FECHA_PROCESO);
			
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
