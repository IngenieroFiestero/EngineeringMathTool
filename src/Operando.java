
public class Operando{

	private InterfazMatriz matriz;
	public Variable variable;
	public int tipo;
	
	public Operando(InterfazMatriz matriz){
		this.matriz = matriz;
		if(matriz instanceof Matriz){
			this.tipo = Matriz.TIPO;
		}else if(matriz instanceof Matriz3D){
			this.tipo = Matriz3D.TIPO;
		}
		this.variable = null;
	}
	public Operando(InterfazMatriz matriz, Variable variable){
		this.matriz = matriz;
		this.variable = variable;
	}
	public void setNull(){
		matriz.setNull();
		this.variable = null;
	}
	public ValorNumerico getDeterminante(){
		return null;
	}
	public void inverse(){
		
	}
}
