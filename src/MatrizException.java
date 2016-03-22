
public class MatrizException extends Exception{

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
}
