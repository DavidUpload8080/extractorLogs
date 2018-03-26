package capadatos.pool;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class PoolConexionesSqlServer {

	public static DataSource getDataSource() {
		
		BasicDataSource ds = new BasicDataSource();
		
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ds.setUrl("jdbc:sqlserver://localhost:1433;databaseName=logsDB;integratedSecurity=true;");
		
		//defino el tama�o de las conexiones iniciales.
		ds.setInitialSize(5);
		
		return ds;
	}
	
	public static Connection getConexion() throws SQLException{		
		return getDataSource().getConnection();
	}
	public static Connection getConexionCheck() throws SQLException {
		return TestConnection().getConnection();
	}
	
	/***
	 * M�todo para checkear la conexi�n sin seleccionar base de datos.
	 * @return
	 */
	public static DataSource TestConnection() {
		
		BasicDataSource ds = new BasicDataSource();
		
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ds.setUrl("jdbc:sqlserver://localhost:1433;integratedSecurity=true;");
		
		//defino el tama�o de las conexiones iniciales.
		ds.setInitialSize(5);
		
		return ds;
		
	}
	
	
}
