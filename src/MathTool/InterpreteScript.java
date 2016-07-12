package MathTool;

import java.util.concurrent.Executor;

public class InterpreteScript implements Runnable{
	private Script sc;
	private GestorScript gs;
	private Executor exec;
	private MathInterprete mi;
	/**
	 * Se usa principalmente para o interpretar o compilar un script, funcion etc
	 * Las funciones a las que se llaman dentro de otro script es posible compilarlas y evaluarlas en paralelo
	 * @param sc
	 * @param mc
	 * @param exec
	 * @throws InterpreteException
	 */
	public InterpreteScript(Script sc,ContextoMatematico mc,Executor exec) throws InterpreteException{
		this.mi = new MathInterprete(mc);
		this.sc = sc;
		gs =  new GestorScript(sc);
		this.exec =	exec;
	}
	@Override
	public void run() {
		//Ejecuta inicialmente exta tarea
		InterpreteLinea il = new InterpreteLinea(mi, gs,exec);
		exec.execute(il);
	}
}
