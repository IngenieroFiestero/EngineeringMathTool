
public class MensajesExceptions extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2761071443267940664L;
	/**
	 * Carga paquetes de idiomas para que cada excepcion pueda ser mostrada en el idioma del usuario
	 * @param lang Array 2D de longitud[n,2] con la primera columna siendo claves y la segunda valores
	 */
	public static void cargarPaqueteIdioma(String[][] lang){
		
	}
	public MensajesExceptions(String mensaje){
		super(mensaje);
	}
}
