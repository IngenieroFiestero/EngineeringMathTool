
public class Operando <T>{
	
	private T matriz;
	public Variable variable;
	
	public Operando(T matriz){
		this.matriz = matriz;
	}
	public Operando(T matriz, Variable variable){
		this.matriz = matriz;
		this.variable = variable;
	}
}
