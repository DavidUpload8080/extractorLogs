package utileria;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propiedades {
	
	public static File configFile = new File("rutas.properties");
	public static  Properties configProps;
	

	public Propiedades() {}
	
	
	public static String showProperties(String clave) throws Exception {
	   
	   String valor =  loadProperties().getProperty(clave);
	   return valor;
	}
	
	public static Properties loadProperties() throws IOException {
		Properties defaultProps = new Properties();
		configProps = new Properties(defaultProps);
		
		// loads properties from file
		InputStream inputStream = new FileInputStream(configFile);
		configProps.load(inputStream);
		inputStream.close();
		
		return configProps;
	}
	

	public static String getUnidadDisco() {
		
		String user_home = System.getProperty("user.home");		
		String unidad = user_home.substring(0, 3);
				
		return unidad;
	}
	
	/**
	 * Método para crear todos los directorios y archivos (por defecto).
	 */
	public static void setDirectorios() {

		//seteo las propiedades del archivo rutas.properties, para definir las rutas y archivos de destino.
	
		try {
			
			Properties p = new Properties();
					
			InputStream is = Propiedades.class.getResourceAsStream("/rutas.properties");
			
			p.load(is);
			//obtengo la unidad del disco.
			
			//rutas de destino.
			
			is.close();
			
			FileOutputStream out = new FileOutputStream("rutas.properties");
			p.setProperty("dir.destino.logs.f01c", "Texo Ejemplo" );
			p.store(out, "demo");
			
			out.close();
				
	
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		

	}
	

}

	
