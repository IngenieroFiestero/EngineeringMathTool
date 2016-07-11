package MathTool;

import java.util.NoSuchElementException;

import Operaciones.ListaOperaciones;
import Operaciones.Operacion;

public class GestorOperaciones {
	private ListaOperaciones ops;
	private int pos;
	/**
	 * Sencillo sistema para obtener las ExpresionesInterpretadas de un script
	 * @param script
	 */
	public GestorOperaciones(ListaOperaciones ops){
		this.ops = ops;
		this.pos = 0;
	}
	public synchronized Operacion getNext(){
		Operacion ret = null;
		try{
			ret = ops.get(pos);
			pos++;
		}catch(NoSuchElementException e){}
		return ret;
	}
	public synchronized boolean hasNext(){
		if(ops.size() == pos){
			return false;
		}else{
			return true;
		}
	}
	public synchronized Operacion get(int i){
		return ops.get(i);
	}
}
