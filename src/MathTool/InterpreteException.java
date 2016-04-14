package MathTool;
import java.util.HashMap;

public class InterpreteException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2786794359961702701L;
	public static final String SPLITER = "%%";
	public static final int TIPO = 2;
	private static HashMap<Integer,String> codigos = defMensajes();
	
	public InterpreteException(String msg){
		super(msg);
	}
	public InterpreteException(String msg,Exception e){
		super(msg,e);
	}
	public InterpreteException(Exception e){
		super(e);
	}
	private static HashMap<Integer,String> defMensajes(){
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		map.put(1, "Cant find Variable: " + SPLITER);
		map.put(2, "Cant find Function: " + SPLITER);
		map.put(3, "Not Implemented");
		return map;
	}
	
	public static String generateCantFindError(String name){
		String algo = (String) codigos.get(1);
		String[] vec = algo.split(SPLITER);
		String ret = vec[0]+name;
		return ret;
	}
	public static String notImplementedError(){
		return codigos.get(3);
	}
	
	public static String arrayToString(int[] val,int j){
		String ret = "";
		for (int i = 0; i < val.length && i < j; i++) {
			ret = ret + val[i] + ",";
		}
		return ret;
	}
}
