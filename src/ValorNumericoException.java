
public class ValorNumericoException extends Exception{

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
}
