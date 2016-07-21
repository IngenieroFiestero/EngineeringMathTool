package Operaciones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import Matriz.Matriz;
import ValorNumerico.ValorNumerico;

/**
 * La clase resultado es un monitor en si misma pues un operando no se puede obtener el valor de un resultado hasta que no se llene con uno
 * @author Samuel Garces Marin
 *
 */
public class Resultado {
	private Object valor;
	private boolean valorado;
	
	public Resultado(){
		this.valor = null;
		this.valorado = false;
	}
	public Resultado(ValorNumerico valor){
		this.valor = valor;
		this.valorado = true;
	}
	public Resultado(Matriz valor){
		this.valor = valor;
		this.valorado = true;
	}
	public synchronized Object getValor(){
		while(!this.valorado ){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		//El this.valorado = false es importante porque si volvemos a ejecutar el script 
		//el que tiene que obtener el resultado de aqui volvera a esperar a pesar de que hay un valor
		this.valorado = false;
		return this.valor;
	}
	public synchronized void setValor(Object valor){
		this.valor = valor;
		this.valorado = true;
		notifyAll();
	}
	/**
	 * Este comando nos permite, en el caso de que ninguna linea se haya modificado hasta en la que nos encontramos (incluida),
	 * saltarnos calculos al decirle al resultado que el valor anteriormente calculado es valido, de forma que las lineas siguientes puedan acceder a nuestros valores directamente.
	 */
	public void mantenerValor(){
		this.valorado = true;
	}
	public String toString(){
		return ""+valor;
	}
}
