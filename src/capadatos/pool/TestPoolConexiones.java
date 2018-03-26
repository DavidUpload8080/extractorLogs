package capadatos.pool;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utileria.Propiedades;

public class TestPoolConexiones {

	public static void main(String[] args) throws Exception {

		Connection conn = null;
		Connection connDB = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String ruta_destino_logs = Propiedades.showProperties("dir.destino.logs.f01c");
		
		try {
			//me conecto a SQL SERVER sin base de datos
			conn = PoolConexionesSqlServer.getConexionCheck();
			
			System.out.println("Estado Conexión B " + conn );
			
			DatabaseMetaData meta = conn.getMetaData();  
			rs = meta.getCatalogs(); // guardo los nombres de las bases de datos en el rs.
			
			System.out.println("--- Listado de bases de datos ---");
			
			//recorro el listado.
			while(rs.next()) {

					if(rs.getString("TABLE_CAT").equals("logsDB")) {
						
						
						//Creo el script sql bulk
						String sql = "BULK INSERT dbo.contenedorlogsF01C FROM "
								+ "'" + ruta_destino_logs +"'" 
								+ " WITH ("
								+ "FIELDTERMINATOR = '\\t', "
								+ "ROWTERMINATOR = '\\n' "
								+ ");";
						System.out.println(sql);
						
						//Me conecto a la base de datos logDB
						connDB = PoolConexionesSqlServer.getConexion();
						
						System.out.println("Conexión con base de datos"  + connDB);
						
						DatabaseMetaData metaTables = connDB.getMetaData();
						String tabla = "contenedorlogsF01Css";
						ResultSet rsTables = metaTables.getTables(null, null, tabla, null);
						
						
						int i = 0;
						if(rsTables != null) {
							
						while(rsTables.next() ) {
							i++;
							System.out.println("--- Si existe la tabla ---");

									stmt = connDB.prepareStatement(sql);
									
									int result = stmt.executeUpdate();
									System.out.println("Resultado: " + result);
									
								break;
							}
							if(i == 0) {
								System.out.println("la tabla no existe");
								
								String crearTabla = "CREATE TABLE contenedorlogsF01C( "
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
								stmt = connDB.prepareStatement(crearTabla);
								
								int result = stmt.executeUpdate();
								System.out.println("Resultado: " + result);
								System.out.println("--- **** tabla creada ****---");
								
							}
						}
						rsTables.close();
					}//fin if tables
					
						
					}
				
			
			rs.close();				
			
			conn.close();
			connDB.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		
		
	}

}
