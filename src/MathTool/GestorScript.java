package MathTool;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import Operaciones.ExpresionInterpretada;

public class GestorScript {
	private Script script;
	private LinkedList<ExpresionInterpretada> expresiones;
	/**
	 * Sencillo sistema para obtener las ExpresionesInterpretadas de un script
	 * @param script
	 */
	public GestorScript(Script script){
		this.script = script;
		this.expresiones = new LinkedList<ExpresionInterpretada>();
		ExpresionInterpretada[] expres = this.script.getExpresiones();
		for (int i = 0; i < expres.length; i++) {
			expresiones.add(expres[i]);
		}
	}
	public synchronized ExpresionInterpretada getNext(){
		ExpresionInterpretada ret = null;
		try{
			ret = expresiones.getFirst();
			expresiones.removeFirst();
		}catch(NoSuchElementException e){}
		return ret;
	}
	public synchronized boolean hasNext(){
		return expresiones.isEmpty();
	}
	public synchronized Script originScript(){
		return this.script;
	}
}
