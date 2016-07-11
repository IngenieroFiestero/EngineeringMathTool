package Operaciones;

import java.util.HashMap;

public class OperacionException extends Exception{
	public static final String SPLITER = "%%";
	private static HashMap<Integer,String> codigos = defMensajes();
	//Este tipo de excepcion empiezan por el 1
	private static HashMap<Integer,String> defMensajes(){
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		map.put(1, "More than 2 Operandos ");
		map.put(2, "More than 2 Operators " + SPLITER);
		map.put(3, "Cant multiply " + SPLITER);
		return map;
	}
	public OperacionException(String msg){
		super(msg);
	}
	public OperacionException(String msg,Exception e){
		super(msg,e);
	}
	public OperacionException(Exception e){
		super(e);
	}
	public static String generarErrorDemasiadosOperandos(){
		return (String)codigos.get(1);
	}
	public static String generarErrorSuma(int[] tipo){
		String[] vec = ((String) codigos.get(2)).split(SPLITER);
		String ret = "";
		for (int i = 0; i < vec.length; i++) {
			ret = ret + vec[i];
		}
		return ret;
	}
}
