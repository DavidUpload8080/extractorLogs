package utileria;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class Propiedades {

	
	private static final String destino_logs = "dir.destino.logs.f01c";
	private static String configFilePath = "/rutas.properties";
//	public static  = null;
	public Propiedades() {}
	
	
	public static String showProperties(String clave) throws Exception {
	   Properties p = new Properties();
	   InputStream is = Propiedades.class.getResourceAsStream("/rutas.properties");
	   p.load(is);
	   is.close();
	 
	   String valor = p.getProperty(clave);
	   
	   
	 
	   //System.out.println("destino: "+ destino);
	  
	   return valor;
	}

	public static String getUnidadDisco() {
		
		String user_home = System.getProperty("user.home");		
		String unidad = user_home.substring(0, 3);
				
		return unidad;
	}
	
	/**
	 * M�todo para crear todos los directorios y archivos (por defecto).
	 */
	public static void setDirectorios() {

		//seteo las propiedades del archivo rutas.properties, para definir las rutas y archivos de destino.
	
		try {
			
			Properties p = new Properties();
					
			InputStream is = Propiedades.class.getResourceAsStream("/rutas.properties");
			
			p.load(is);
			//obtengo la unidad del disco.
			String unidad = getUnidadDisco();
			//rutas de destino.
			String destino_f01c = unidad + "logs\\contenedoreslogs\\contenedorlogsF01C.txt";
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

	