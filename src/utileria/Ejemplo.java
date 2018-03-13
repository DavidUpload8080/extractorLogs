package utileria;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class Ejemplo {

	

	public static void main(String[] args) {
		
		String m = null;
	
		try {
			m = Propiedades.showProperties("dir.destino.logs.f01c");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(m);
		
		//creo los directorios en rutas.propiedades.
		Propiedades.setDirectorios();
	}
	
}
