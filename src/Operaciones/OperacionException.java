package Operaciones;

public class OperacionException extends Exception{
	public OperacionException(String msg){
		super(msg);
	}
	public OperacionException(String msg,Exception e){
		super(msg,e);
	}
	public OperacionException(Exception e){
		super(e);
	}
}
