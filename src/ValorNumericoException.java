import java.util.HashMap;

public class ValorNumericoException extends Exception{
	public static final String SPLITER = "%$";
	//Este tipo de excepcion empiezan por el 1
	public static final int TIPO = 1;
	/**
	 * Los posibles codigos de errores son los siguientes:
	 * 1: Problema de casteo
	 * 2: Problema al operar
	 */
	private static HashMap codigos = defMensajes();

	private static final long serialVersionUID = -4757304349982202926L;
	public ValorNumericoException(String msg,Exception exception){
		super(msg,exception);
	}
	public ValorNumericoException(String msg){
		super(msg);
	}
	public ValorNumericoException(Exception exception){
		super(exception);
	}
	public static String generarErrorCasteo(int[] tipo){
		String[] vec = ((String) codigos.get(1)).split(SPLITER);
		String ret = "";
		for (int i = 0; i < vec.length; i++) {
			ret = ret + vec[i] + ValorNumerico.nombres[tipo[i]];
		}
		return ret;
	}
	public static String generarErrorSuma(int[] tipo){
		String[] vec = ((String) codigos.get(2)).split(SPLITER);
		String ret = "";
		for (int i = 0; i < vec.length; i++) {
			ret = ret + vec[i] + ValorNumerico.nombres[tipo[i]];
		}
		return ret;
	}
	public static String generarErrorMultiplicacion(int[] tipo){
		String[] vec = ((String) codigos.get(3)).split(SPLITER);
		String ret = "";
		for (int i = 0; i < vec.length; i++) {
			ret = ret + vec[i] + ValorNumerico.nombres[tipo[i]];
		}
		return ret;
	}
	private static HashMap defMensajes(){
		HashMap map = new HashMap<Integer,String>();
		map.put(1, "Cant cast " + SPLITER + " to ");
		map.put(2, "Cant add " + SPLITER);
		map.put(3, "Cant multiply " + SPLITER);
		return map;
	}
}
