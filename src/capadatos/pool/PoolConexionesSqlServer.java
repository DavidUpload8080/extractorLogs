package capadatos.pool;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class PoolConexionesSqlServer {

	public static DataSource getDataSource() {
		
		BasicDataSource ds = new BasicDataSource();
		
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		ds.setUsername("");
//		ds.setPassword("");
		ds.setUrl("jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=testDB");
		
		//defino el tamaño de las conexiones iniciales.
		ds.setInitialSize(5);
//		Connection conn = DriverManager.getConnection("jdbc:sqlserver://<server>;user=<user>;password=<password>;");
//		System.out.println("Driver version: " + conn.getMetaData().getDriverVersion());
			
		
		return ds;
	}
	
}
