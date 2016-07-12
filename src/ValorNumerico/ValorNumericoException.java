package ValorNumerico;
import java.util.HashMap;

public class ValorNumericoException extends Exception{
	public static final String SPLITER = "%%";
	//Este tipo de excepcion empiezan por el 1
	public static final int TIPO = 1;
	/**
	 * Los posibles codigos de errores son los siguientes:
	 * 1: Problema de division por 0
	 * 2: Problema de suma
	 * 3: Problema de multiplicacion
	 */
	private static HashMap<Integer,String> codigos = defMensajes();

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
	public static String generarErrorSuma(int[] tipo){
		String[] vec = ((String) codigos.get(2)).split(SPLITER);
		String ret = "";
		for (int i = 0; i < vec.length; i++) {
			ret = ret + vec[i];
		}
		return ret;
	}
	public static String generarErrorMultiplicacion(int[] tipo){
		String[] vec = ((String) codigos.get(3)).split(SPLITER);
		String ret = "";
		for (int i = 0; i < vec.length; i++) {
			ret = ret + vec[i];
		}
		return ret;
	}
	public static String generarNotSupported(String ops){
		String[] vec = ((String) codigos.get(4)).split(SPLITER);
		String ret = "";
		for (int i = 0; i < vec.length; i++) {
			if(i == 1){
				ret = ret + ops;
			}
			ret = ret + vec[i];
		}
		return ret;
	}
	public static String generarErrorDenominadorNulo(){
		return (String)codigos.get(1);
	}
	private static HashMap<Integer,String> defMensajes(){
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		map.put(1, "Cant divide by 0 ");
		map.put(2, "Cant add " + SPLITER);
		map.put(3, "Cant multiply " + SPLITER);
		map.put(4, "Operation " + SPLITER + " not supported yet");
		return map;
	}
}
