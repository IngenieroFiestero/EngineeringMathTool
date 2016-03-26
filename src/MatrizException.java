import java.util.HashMap;

public class MatrizException extends Exception{
	public static final String SPLITER = "%%";
	public static final int TIPO = 2;
	private static HashMap<Integer,String> codigos = defMensajes();
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
	private static HashMap<Integer,String> defMensajes(){
		HashMap<Integer,String> map = new HashMap<Integer,String>();
		map.put(1, "Matrix dimensions must agree: [" + SPLITER +"],["+ SPLITER + "]");
		map.put(2, "Cant add " + SPLITER);
		map.put(3, "Not Implemented");
		map.put(4, "Not Square matrix " + SPLITER);
		return map;
	}
	
	public static String generateErrorDimensions(int[] dim1,int[] dim2){
		String algo = (String) codigos.get(1);
		String[] vec = algo.split(SPLITER);
		String ret = vec[0]+arrayToString(dim1,dim1.length)+vec[1]+arrayToString(dim2,dim2.length)+vec[2];
		return ret;
	}
	public static String generateErrorNotSquareMatrix(int[] dim1){
		String algo = (String) codigos.get(1);
		String[] vec = algo.split(SPLITER);
		String ret = vec[0]+arrayToString(dim1,dim1.length);
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
