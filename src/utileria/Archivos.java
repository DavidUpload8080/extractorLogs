package utileria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Archivos {

	
	private static final String NOMBRE_ARCHIVO_DESTINO = "C:\\Users\\David\\Desktop\\logs\\contenedorlogs.txt";
	private static  final String DIR_ARCHIVOS_ORIGEN = "C:\\Users\\David\\Desktop\\logs\\ERR_CL01171127.TCL.LOG";
	public static void crearArchivo(String nombreArchivo) {

		File archivo = new File(nombreArchivo);

		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo));
			salida.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public static void escribirArchivo(String nombreArchivo, String contenido) {
		
		File archivo = new File(nombreArchivo);

		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo));

			// inserto el contenido igolgi
			salida.println(contenido);

			salida.println();

			// salida.println("Fin de escritura");

			salida.close();

			// System.out.println("Se ha escrito correctamente el archivo\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void leerArchivo(String nombreArchivo) {
		List<String> lines = new ArrayList<String>();
		List<String> linesjuntas = new ArrayList<String>();
		// leo lineas
		try {
			
		
			
			File archivo = new File(nombreArchivo);

			BufferedReader entrada = new BufferedReader(new FileReader(archivo));

			String lectura = "";

			// lectura = entrada.readLine();

			while ((lectura = entrada.readLine()) != null) {
				
				// llena todos los registros en memoria.
				lines.add(lectura);

				// System.out.println(lines);
			}

			entrada.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

		int sum = 0;

		// junto la linea del error con el registro completo y lo guardo en otro array.
		for (int i = 0; i < lines.size(); i++) {

			if ((i + 1) < lines.size() && (sum + 1) < lines.size()) {

				// System.out.println(" Linea: [" + i + "] " + lines.get(i));
				linesjuntas.add(lines.get(sum).replaceAll(",", "\t").replaceAll(":", "\t").replaceAll("@#", "")
						.replaceAll("#@", "") + "\t" + lines.get(sum + 1));
				System.out.println(linesjuntas.get(i));
				sum = sum + 2;
			}

		}

	}

	public static void leerArchivoPesado(String nombreArchivo, String interfaz) throws FileNotFoundException {

		FileInputStream inputStream = null;
		Scanner sc = null;
		List<String> lines = new ArrayList<String>();
		List<String> linesjuntas = new ArrayList<String>();
		
		try {
			inputStream = new FileInputStream(nombreArchivo);
			
//			BufferedWriter bw = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO_DESTINO));
//			bw.write("");
//			bw.close();
//			System.out.println(inputStream);
			sc = new Scanner(inputStream, "UTF-8");
//			System.out.println("scanner" +sc);
			int sum = 0;
			
			// recorro cada l�nea sin guardar los registros en memoria.
			while (sc.hasNextLine()) {
				
				String line = sc.nextLine();
				lines.add(line);
				System.out.println(line);
				sum++;
				
				
				//abro el archivo y lo defino en true para poder ir apilando cada registro.
				PrintWriter salida = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO_DESTINO, true));
				
				

				if (sum == 2 && null != line ) {

					// Junto linea 1 con linea 2
					int linea = 0;
					// junto la linea del error con el registro completo y lo guardo en otro array.
					for (int i = 0; i < lines.size(); i++) {

						if ((i + 1) < lines.size() && (linea + 1) < lines.size()) {

							linesjuntas.add(interfaz + "\t" + lines.get(linea).replaceAll(",", "\t").replaceAll(":", "\t")
									.replaceAll("@#", "").replaceAll("#@", "") + "\t" + lines.get(linea + 1));
							// System.out.println(linesjuntas.get(i));
//							escribirArchivo(NOMBRE_ARCHIVO_DESTINO, linesjuntas.get(i));
							
							// inserto el contenido
							salida.println(linesjuntas.get(i));

//							salida.println();
						}

					}

					// limpio el array lines.
					lines.clear(); ;
					linesjuntas.clear();
					sum = 0;
					
					// limpio el array linesjuntas
					// Escribo el archivo

				} 
				salida.close(); 
			
				
//				System.out.println("Terminó de escribir");

			}//fin while

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void anexarArchivo(String nombreArchivo) {

		File archivo = new File(nombreArchivo);

		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));

			String contenido = "Anexando información al archivo";

			salida.println(contenido);

			salida.println();

			salida.println("Fin de anexar");

			salida.close();

			System.out.println("Se ha anexado la información correctamente\n");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
