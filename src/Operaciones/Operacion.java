package Operaciones;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import MathTool.Settings;
import Matriz.Matriz;

public class Operacion{
	private Resultado resultado;
	private int operador;
	private Operando[] operandos;
	private boolean paralelizable;
	private boolean finish;
	private int id;
	/**
	 * Utiliza una lista de operandos y un operador. La cantidad de operandos depende del operador.
	 * Al llamar a la funcion operar, esta utiliza el operador para operar los operandos y obtener un resultado.
	 * @param operandos
	 * @param operador
	 */
	public Operacion(Operando[] operandos, int operador){
		this.operandos = operandos;
		this.operador = operador;
		this.paralelizable = false;
		this.finish = false;
		int[] dim = new int[]{0,0};
		for (int i = 0; i < operandos.length; i++) {
			if(operandos[i] != null && operandos[i].getValor() instanceof Matriz){
				int[] dims = ((Matriz)operandos[i].getValor()).dimensions();
				dim[0] = (dim[0] > dims[0])? dim[0]:dims[0];
				dim[1] = (dim[1] > dims[1])? dim[1]:dims[1];
			}
		}
		if(dim[0]*dim[1] >= Settings.MAX_SIZE_MATRIZ_OPERATION){
			this.paralelizable = true;
		}
		this.resultado = new Resultado();
	}
	/**
	 * Devuelve si esta operacion es posible paralelizarla al ser una operacion con matrices
	 * @return
	 */
	public boolean isParalelizable(){
		return this.paralelizable;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return this.id;
	}
	/**
	 * Para evitar ejecutar operaciones que todavia no han acabado de ejecutarse utilizamos este metodo para comprobarlo
	 * @return
	 */
	public boolean operacionFinalizada(){
		return this.finish;
	}
	public int getNumOperandos(){
		return this.operandos.length;
	}
	public Operando getOperando(int i){
		return operandos[i];
	}
	public Operando[] getOperandos(){
		return this.operandos;
	}
	public int getOperador(){
		return this.operador;
	}
	public Resultado getResultado(){
		return this.resultado;
	}
	public static Operacion load(InputStream is) throws IOException{
		DataInputStream dis = new DataInputStream(is);
		int operador = dis.readInt();
		int lngth = dis.readInt();
		int id = dis.readInt();
		//Cerramos este InputStream antes de continuar
		Operando[] operandos = new Operando[lngth];
		for (int i = 0; i < lngth; i++) {
			operandos[i] = Operando.load(is);
		}
		Operacion ret = new Operacion(operandos,operador);
		ret.setId(id);
		return ret;
	}
	public void save(OutputStream os) throws IOException{
		DataOutputStream daos = new DataOutputStream(os);
		//Escribir primero el tipo de operador
		daos.writeInt(this.operador);
		//Ahora escribimos el numero de operadores
		daos.writeInt(this.operandos.length);
		//Escribimos el identificador de Operacion
		daos.writeInt(this.id);;
		//Cerramos este OutputStream antes de continuar
		//Escribir todos los operandos
		for (int i = 0; i < operandos.length; i++) {
			operandos[i].save(os);
		}
	}
	public byte[] getBytes() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.save(baos);
		return baos.toByteArray();
	}
	public String toString(){
		String ret = "";
		ret = "Operacion " + this.operador + "\n";
		for (int i = 0; i < operandos.length; i++) {
			ret = ret + "Operando " +i + ": " + operandos[i].toString() + "\n";
		}
		return ret;
	}
}
