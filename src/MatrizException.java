import java.util.HashMap;

public class MatrizException extends Exception{
	public static final String SPLITER = "%";
	public static final int TIPO = 2;
	private static HashMap codigos = defMensajes();
	private static final long serialVersionUID = 4417246156437107684L;
	
	public MatrizException(String msg){
		super(msg);
	}
	public MatrizException(String msg,Exception exception){
		super(msg,exception);
	}
	public MatrizException(Exception exception){
		super(exception);
	}
	private static HashMap defMensajes(){
		HashMap map = new HashMap<Integer,String>();
		map.put(1, "Matrix dimensions must agree: [" + SPLITER +","+SPLITER+"],["+ SPLITER+"," + SPLITER + "]");
		map.put(2, "Cant add " + SPLITER);
		return map;
	}
	public static String generateErrorDimensions(int[] dim){
		String algo = (String) codigos.get(1);
		String[] vec = algo.split("%");
		String ret = "";
		for (int i = 0; i < vec.length; i++) {
			if(i < dim.length){
				ret = ret + vec[i] + dim[i];
			}
		}
		return ret;
	}
	
}
