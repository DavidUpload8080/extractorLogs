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
				
			}

			entrada.close();

		} catch (IOException e) {

			e.printStackTrace();
		}

		int sum = 0;

		// junto la linea del error con el registro completo y lo guardo en otro array.
		for (int i = 0; i < lines.size(); i++) {

			if ((i + 1) < lines.size() && (sum + 1) < lines.size()) {

				
				linesjuntas.add(lines.get(sum).replaceAll(",", "\t").replaceAll(":", "\t").replaceAll("@#", "")
						.replaceAll("#@", "") + "\t" + lines.get(sum + 1));
				System.out.println(linesjuntas.get(i));
				sum = sum + 2;
			}

		}

	}

	public static void leerArchivoPesado(String nombreArchivo, String interfaz, String NOMBRE_ARCHIVO_DESTINO, String fecha_proceso) throws FileNotFoundException {

		FileInputStream inputStream = null;
		Scanner sc = null;
		List<String> lines = new ArrayList<String>();
		List<String> linesjuntas = new ArrayList<String>();
		
		try {
			
			File file = new File(nombreArchivo);
			
			if(file.exists()) {
				inputStream = new FileInputStream(nombreArchivo);
				

				sc = new Scanner(inputStream, "UTF-8");

				int sum = 0;
				
				// recorro cada línea sin guardar los registros en memoria.
				while (sc.hasNextLine()) {
					
					String line = sc.nextLine();
					lines.add(line);
					System.out.println(line);
					sum++;
					
					//abro el archivo y lo defino en true para poder ir apilando cada registro.
					PrintWriter salida = new PrintWriter(new FileWriter(NOMBRE_ARCHIVO_DESTINO, true));

					if (sum == 2 && null != line && interfaz != "CL01" ) {

						// Junto linea 1 con linea 2
						int linea = 0;
						// junto la linea del error con el registro completo y lo guardo en otro array.
						for (int i = 0; i < lines.size(); i++) {

							if ((i + 1) < lines.size() && (linea + 1) < lines.size()) {
								
							
								
							//cuento los tabs
							String row = fecha_proceso + "\t" + interfaz + "\t" + lines.get(linea).replaceAll(",", "\t").replaceAll(":", "\t")
								.replaceAll("@#", "").replaceAll("#@", "") + "\t" + lines.get(linea + 1); 
							
								int numTabs = row == null ? 0 : row.length() - row.replace("\t", "").length();
								int tabsFaltantes = 111 - numTabs;
								
								if(tabsFaltantes != 0) {
								//agrego la cantidad de tabs al final dinámicamente.
								String str = "\t";
								
								String tabFinal = new String(new char[tabsFaltantes]).replace("\0", str);

							
								linesjuntas.add(row + tabFinal);
						}//fin if de tabs
								else {
									linesjuntas.add(row);
								}
								// inserto el contenido
								salida.println(linesjuntas.get(i));


							}

						}

						// limpio el array lines.
						lines.clear(); ;
						linesjuntas.clear();
						sum = 0;
						
				

					} 
					
					salida.close(); 
				

				}//fin while
			}//fin if
			
			
			


		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			
		}

	}

	public static int CharCount(String string, String myString)
	{
	    int count = 0;
	    int pos = 0;
	    do
	    {
	        pos = myString.indexOf(string, pos);
	        if (pos >= 0)
	        {
	            count++;
	        }
	    } while (pos >= 0);
	    return count;
	}

	public static void anexarArchivo(String nombreArchivo) {

		File archivo = new File(nombreArchivo);

		try {
			PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));

			String contenido = "Anexando informaciÃ³n al archivo";

			salida.println(contenido);

			salida.println();

			salida.println("Fin de anexar");

			salida.close();

			System.out.println("Se ha anexado la informaciÃ³n correctamente\n");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	/**
	 * Método para extraer todos los errores logs y dejarlos en el contenedor.
	 * @param String DIR_ARCHIVO_LISTADO
	 * @param String DIR_ARCHIVO_DESTINO_LOGS
	 * @param String DIR_ARCHIVOS_ORIGEN
	 * @param String FECHA_PROCESO
	 * 
	 */
	public static void extraerLogs(String DIR_ARCHIVO_LISTADO, String DIR_ARCHIVO_DESTINO_LOGS,
			String DIR_ARCHIVOS_ORIGEN, String FECHA_PROCESO, String fecha_proceso_2) {
		
		FileInputStream inputListado = null; 
		Scanner sc = null;
		try {
			
			inputListado = new FileInputStream(DIR_ARCHIVO_LISTADO);
			
			//List<String> listado = new ArrayList<String>();
			if(inputListado != null) {
				
			}
			sc = new Scanner(inputListado, "UTF-8");
			
			BufferedWriter bw;
			bw = new BufferedWriter(new FileWriter(DIR_ARCHIVO_DESTINO_LOGS));
			bw.write("");
			bw.close();
			
			while (sc.hasNextLine()) {
				
				String interfaz = sc.nextLine();
				
				if(interfaz.isEmpty() == false  ) {
				
				System.out.println(interfaz);
				String directorio =  DIR_ARCHIVOS_ORIGEN + "\\ERR_" + interfaz + FECHA_PROCESO +".TCL.LOG";
				//listado.add(archivoOrigen);
				System.out.println(directorio);
				//System.out.println(DIR_ARCHIVOS_ORIGEN);
						
					//ejecuto el método para llenar el contenedor de logs.
					leerArchivoPesado(directorio, interfaz, DIR_ARCHIVO_DESTINO_LOGS, fecha_proceso_2);
				}
			}// fin while
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	

}
