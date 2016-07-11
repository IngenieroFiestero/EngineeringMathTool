package MathTool;

import java.util.concurrent.Executor;

import Operaciones.ExpresionInterpretada;

public class InterpreteLinea implements Runnable{
	private MathInterprete mi;
	private GestorScript gs;
	private Executor exec;//Este ejecutor es para hacer llamadas a compilar scripts de tipo thread
	
	public InterpreteLinea(MathInterprete mi,GestorScript gs,Executor exec){
		this.mi = mi;
		this.gs = gs;
		this.exec = exec;
	}
	@Override
	public void run() {
		
	}
	public MathInterprete getMathInterprete(){
		return this.mi;
	}
	
}
