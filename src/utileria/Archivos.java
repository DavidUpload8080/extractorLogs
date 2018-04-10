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
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import capadatos.pool.PoolConexionesSqlServer;


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
			String DIR_ARCHIVOS_ORIGEN, String FECHA_PROCESO, String fecha_proceso_2, String Tabla) {
		
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
					
					try {
						BulkAndCreateTableLogs(Tabla ,DIR_ARCHIVO_DESTINO_LOGS);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}// fin while
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}//fin método extraer logs.
	
	@SuppressWarnings("unused")
	public static void BulkAndCreateTableLogs(String tabla, String archivoLog) throws Exception {
		
		Connection conn = null;
		Connection connDB = null;
		Connection connDB2 = null;
		PreparedStatement stmt = null;
		PreparedStatement stmtVaciarTabla = null;
		PreparedStatement stmtCrearTabla = null;
		ResultSet rs = null;
		
		
		try {
			//me conecto a SQL SERVER sin base de datos
			conn = PoolConexionesSqlServer.getConexionCheck();
			
			System.out.println("Estado Conexión B " + conn );
			
			DatabaseMetaData meta = conn.getMetaData();
			System.out.println("Meta: " + meta);
			rs = meta.getCatalogs(); // guardo los nombres de las bases de datos en el rs.
			System.out.println("Pasoo al array");
			 ArrayList<String> listDB = new ArrayList<String>();
			 ArrayList<String> listTB = new ArrayList<String>();
			
			 
			//check if exist data base or create if not exist and table.
				while(rs.next()) {
						
					String listado = rs.getString("TABLE_CAT");				
					listDB.add(listado);	
				}			
				
				System.out.println("base de datos: " + listDB.contains("logsDB"));
			 
			//Cheking if table exist
//			connDB = PoolConexionesSqlServer.getConexion();				
//			System.out.println("Conexión con base de datos"  + connDB);				
//			DatabaseMetaData metaTables = connDB.getMetaData();
//			ResultSet rsTables = metaTables.getTables(null, null, tabla, null);
//			 
			
			
			if(listDB.contains("logsDB") == false) {
				
				System.out.println("base de d creada: " + listDB.contains("logsDB") );
				//Código para crear la tabla 
				System.out.println("Entró a la creación de la base de datos ");
				
				Connection conn1 = PoolConexionesSqlServer.getConexionCheck();
				stmtCrearTabla = conn1.prepareStatement("CREATE DATABASE logsDB");				
				System.out.println("CREANDO LA BASE DE DATOS " + stmtCrearTabla );				
				
//				System.out.println(stmtCrearTabla.executeUpdate());
				
				System.out.println("Resultado create database: " + stmtCrearTabla.executeUpdate());
				
				//creo la tabla sólo si esta no existe.
				crearTabla(tabla);				
				//creo el bulk
				bulkTabla(tabla, archivoLog);
				
			}// Fin Checking DataBase logsDB
			
			else  {
				crearTabla(tabla );
				bulkTabla(tabla, archivoLog);
			}//fin else final
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			rs.close();			
			conn.close();
			
		}
		
		
	}//fin método bulkandcreate
	
	public static void crearTabla(String tabla) {
		
		Connection conn= null;		
		PreparedStatement stmt = null;	
		DatabaseMetaData metaTables = null;
		ResultSet rsTables = null;
		//Cheking if table exist
		try {
			conn = PoolConexionesSqlServer.getConexion();
			System.out.println("Conexión con base de datos"  + conn);	
			
			metaTables = conn.getMetaData();
			rsTables = metaTables.getTables(null, null, tabla, null);
			
			if(rsTables.next() == false) {
				
				String crearTabla = "CREATE TABLE " + tabla  
						+ " ( " 
						+ "[FECHA_PROCESO] [varchar](max) NULL,"
						+ "[SRC_ID] [varchar](max) NULL,"
						+ "	[FILA] [varchar](max) NULL,"
						+ "	[N_COLUMNA] [varchar](max) NULL,"
						+ "	[ERROR_LOG] [varchar](max) NULL,"
						+ "	[Column 1] [varchar](max) NULL,"
						+ "	[Column 2] [varchar](max) NULL,"
						+ "	[Column 3] [varchar](max) NULL,"
						+ "	[Column 4] [varchar](max) NULL,"
						+ "	[Column 5] [varchar](max) NULL,"
						+ "	[Column 6] [varchar](max) NULL,"
						+ "	[Column 7] [varchar](max) NULL,"
						+ "	[Column 8] [varchar](max) NULL,"
						+ "	[Column 9] [varchar](max) NULL,"
						+ "		[Column 10] [varchar](max) NULL,"
						+ "		[Column 11] [varchar](max) NULL,"
						+ "		[Column 12] [varchar](max) NULL,"
						+ "		[Column 14] [varchar](max) NULL,"
						+ "		[Column 15] [varchar](max) NULL,"
						+ "		[Column 16] [varchar](max) NULL,"
						+ "		[Column 17] [varchar](max) NULL,"
						+ "		[Column 18] [varchar](max) NULL,"
						+ "		[Column 19] [varchar](max) NULL,"
						+ "		[Column 20] [varchar](max) NULL,"
						+ "		[Column 21] [varchar](max) NULL,"
						+ "		[Column 22] [varchar](max) NULL,"
						+ "		[Column 23] [varchar](max) NULL,"
						+ "		[Column 24] [varchar](max) NULL,"
						+ "		[Column 25] [varchar](max) NULL,"
						+ "		[Column 26] [varchar](max) NULL,"
						+ "		[Column 27] [varchar](max) NULL,"
						+ "		[Column 28] [varchar](max) NULL,"
						+ "		[Column 29] [varchar](max) NULL,"
						+ "		[Column 30] [varchar](max) NULL,"
						+ "		[Column 31] [varchar](max) NULL,"
						+ "		[Column 32] [varchar](max) NULL,"
						+ "		[Column 33] [varchar](max) NULL,"
						+ "		[Column 34] [varchar](max) NULL,"
						+ "		[Column 35] [varchar](max) NULL,"
						+ "		[Column 36] [varchar](max) NULL,"
						+ "		[Column 37] [varchar](max) NULL,"
						+ "		[Column 38] [varchar](max) NULL,"
						+ "		[Column 39] [varchar](max) NULL,"
						+ "		[Column 40] [varchar](max) NULL,"
						+ "		[Column 41] [varchar](max) NULL,"
						+ "		[Column 42] [varchar](max) NULL,"
						+ "		[Column 43] [varchar](max) NULL,"
						+ "		[Column 44] [varchar](max) NULL,"
						+ "		[Column 45] [varchar](max) NULL,"
						+ "		[Column 46] [varchar](max) NULL,"
						+ "		[Column 47] [varchar](max) NULL,"
						+ "		[Column 48] [varchar](max) NULL,"
						+ "		[Column 50] [varchar](max) NULL,"
						+ "		[Column 51] [varchar](max) NULL,"
						+ "		[Column 52] [varchar](max) NULL,"
						+ "		[Column 53] [varchar](max) NULL,"
						+ "		[Column 54] [varchar](max) NULL,"
						+ "		[Column 55] [varchar](max) NULL,"
						+ "		[Column 56] [varchar](max) NULL,"
						+ "		[Column 57] [varchar](max) NULL,"
						+ "		[Column 58] [varchar](max) NULL,"
						+ "		[Column 59] [varchar](max) NULL,"
						+ "		[Column 60] [varchar](max) NULL,"
						+ "		[Column 61] [varchar](max) NULL,"
						+ "		[Column 62] [varchar](max) NULL,"
						+ "		[Column 63] [varchar](max) NULL,"
						+ "		[Column 64] [varchar](max) NULL,"
						+ "		[Column 65] [varchar](max) NULL,"
						+ "		[Column 66] [varchar](max) NULL,"
						+ "		[Column 67] [varchar](max) NULL,"
						+ "		[Column 68] [varchar](max) NULL,"
						+ "		[Column 69] [varchar](max) NULL,"
						+ "		[Column 70] [varchar](max) NULL,"
						+ "		[Column 71] [varchar](max) NULL,"
						+ "		[Column 72] [varchar](max) NULL,"
						+ "		[Column 73] [varchar](max) NULL,"
						+ "		[Column 74] [varchar](max) NULL,"
						+ "		[Column 75] [varchar](max) NULL,"
						+ "		[Column 76] [varchar](max) NULL,"
						+ "		[Column 77] [varchar](max) NULL,"
						+ "		[Column 78] [varchar](max) NULL,"
						+ "		[Column 79] [varchar](max) NULL,"
						+ "		[Column 80] [varchar](max) NULL,"
						+ "		[Column 81] [varchar](max) NULL,"
						+ "		[Column 82] [varchar](max) NULL,"
						+ "		[Column 83] [varchar](max) NULL,"
						+ "		[Column 84] [varchar](max) NULL,"
						+ "		[Column 85] [varchar](max) NULL,"
						+ "		[Column 86] [varchar](max) NULL,"
						+ "		[Column 87] [varchar](max) NULL,"
						+ "		[Column 88] [varchar](max) NULL,"
						+ "		[Column 89] [varchar](max) NULL,"
						+ "		[Column 90] [varchar](max) NULL,"
						+ "		[Column 91] [varchar](max) NULL,"
						+ "		[Column 92] [varchar](max) NULL,"
						+ "		[Column 93] [varchar](max) NULL,"
						+ "		[Column 94] [varchar](max) NULL,"
						+ "		[Column 95] [varchar](max) NULL,"
						+ "		[Column 96] [varchar](max) NULL,"
						+ "		[Column 97] [varchar](max) NULL,"
						+ "		[Column 98] [varchar](max) NULL,"
						+ "		[Column 99] [varchar](max) NULL,"
						+ "		[Column 100] [varchar](max) NULL,"
						+ "		[Column 101] [varchar](max) NULL,"
						+ "		[Column 103] [varchar](max) NULL,"
						+ "		[Column 104] [varchar](max) NULL,"
						+ "		[Column 105] [varchar](max) NULL,"
						+ "		[Column 106] [varchar](max) NULL,"
						+ "		[Column 107] [varchar](max) NULL"
						+ "	) ON [PRIMARY] ";
				
				System.out.println(crearTabla);
				
				stmt = conn.prepareStatement(crearTabla);				
				int result = stmt.executeUpdate();
				System.out.println("Resultado: " + result);
				System.out.println("--- **** tabla creada ****---");
			}	
			
		} catch (SQLException e) {
				
			e.printStackTrace();
		}				
		
		
	}//fin método crearTabla()

	
	public static void bulkTabla(String tabla, String archivoLog) throws SQLException {
		
		Connection conn= null;		
		PreparedStatement stmt = null;
		ResultSet rsTables = null;
		
			try {
				conn = PoolConexionesSqlServer.getConexion();
			
				System.out.println("Conexión con base de datos"  + conn);				
				DatabaseMetaData metaTables = conn.getMetaData();
				rsTables = metaTables.getTables(null, null, tabla, null);
				
//				System.out.println("tabla existente pal bulk, antes del if " + rsTables.next());
//				System.out.println("tabla existente pal bulk, antes del if 2" + rsTables.next());
				System.out.println(" archivo log " + archivoLog );
				
				System.out.println(" Tabla existente: " + tabla);
				
				if(rsTables.next()) {
					
//					rsTables.close();//cierro este rs para que no de error null pointer exception	
//					System.out.println("dentro del if  bulk " + rsTables.next());
					tabla = "logsDB.." + tabla;
					//Creo el script sql bulk
					String sql = "BULK INSERT " + tabla + " FROM "
								+ "'" + archivoLog +"'" 
								+ " WITH ("
								+ "FIELDTERMINATOR = '\\t', "
								+ "ROWTERMINATOR = '\\n' "
								+ ");";
							
				System.out.println("--- Inicio de Llenado de Tabla ---");
									
				//vacío la tabla destino
				stmt = conn.prepareStatement("TRUNCATE TABLE " + tabla); 
				int resultadoVaciar = stmt.executeUpdate();
				System.out.println("Resultado Vaciar Tabla: " + resultadoVaciar);
									
				//ejecuto el bulk los nuevos registros.
				stmt = conn.prepareStatement(sql);
			
				int result = stmt.executeUpdate();
				System.out.println("Resultado: " + result);
				
			}//fin if
				else {
					System.out.println("la tabla no existe");
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				rsTables.close();
			}
			rsTables.close();
	}// fin método bulk

}
