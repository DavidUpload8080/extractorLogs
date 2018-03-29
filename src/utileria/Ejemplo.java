package utileria;

public class Ejemplo {

	

	public static void main(String[] args) {
		
		String m = null;
	
		try {
			
			
			m = Propiedades.showProperties("dir.archivo.listado.f01c");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Propiedad: " + m);
		
		//creo los directorios en rutas.propiedades.
		//Propiedades.setDirectorios();
	}
	
}
