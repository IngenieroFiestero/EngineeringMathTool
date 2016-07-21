package Operaciones;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import MathTool.Funcion;
import MathTool.MathInterprete;
import Matriz.Matriz;
import Matriz.MatrizException;
import Matriz.MatrizExpresion;
import ValorNumerico.ValorNumerico;
import ValorNumerico.ValorNumericoException;
import Variable.Variable;

public class Operando {
	public static final int VARIABLE = 0;//Hay que llamar a la variable para obtener su valor
	public static final int VALOR_NUMERICO = 1;//O tener el valor directamente
	public static final int MATRIZ = 2;//Obtener el valor directamente
	public static final int EXPRESION = 3;//Hay que evaluar una expresion primero
	public static final int RESULTADO = 4;//Hay que obtener el resultado de una operacion
	public static final int MATRIZ_EXPRESION = 5;//Seguramente sea una matriz de variables
	public static final int FUNCION = 6;//Guarda el nombre de una funcion para ejecutarla
	
	private Object operando;
	private int tipo;
	/**
	 * El operando es un contenedor que permite almacenar un ValorNumerico, una Matriz, 
	 * una variable, una referencia a una operacion que es necesaria realizar previamente para obtener su resultado
	 * asi como una expresion que es necesario interpretar posteriormente. Por ejemplo una matriz cuyo componente 1,5 sea una variable o haga llamadas auna funcion
	 * @param operando
	 * @param mi
	 */
	public Operando(Object operando){
		this.operando = operando;
		if(operando instanceof Variable){
			this.tipo = VARIABLE;
		}else if(operando instanceof ValorNumerico){
			this.tipo = VALOR_NUMERICO;
		}else if(operando instanceof Matriz){
			this.tipo = MATRIZ;
		}else if(operando instanceof String){
			String txt = (String)operando;
			if(MathInterprete.validVariableName(txt)){
				this.tipo = VARIABLE;
				this.operando = new Variable(txt);
			}else{
				try {
					Matriz mat = new Matriz(txt);
					this.operando = mat;
					this.tipo = MATRIZ;
				} catch (MatrizException e1) {
					try {
						this.operando = new ValorNumerico(txt);
						this.tipo = VALOR_NUMERICO;
					} catch (ValorNumericoException e) {
						this.tipo = EXPRESION;
						this.operando = operando;
					}
					
					/*try {
						MatrizExpresion me = new MatrizExpresion(txt);
						this.operando = me;
						this.tipo = MATRIZ_EXPRESION;
					} catch (MatrizException e2) {
						this.tipo = EXPRESION;
						this.operando = operando;
					}*/
				}
				
			}
		}else if(operando instanceof Integer){
			this.tipo = RESULTADO;
		}else if(operando instanceof Operacion){
			this.tipo = RESULTADO;
			this.operando = new Integer(((Operacion)operando).getId());
		}else if(operando instanceof MatrizExpresion){
			this.tipo = MATRIZ_EXPRESION;
		}else if(operando instanceof Funcion){
			this.tipo = FUNCION;
		}
	}
	public int getTipo(){
		return this.tipo;
	}
	public Object getValor(){
		return this.operando;
	}
	public String toString(){
		return ""+this.tipo + " operando: " + this.operando;
	}
	public static Operando load(InputStream is) throws IOException{
		DataInputStream dis = new DataInputStream(is);
		int tipo = dis.readInt();
		Operando ret = null;
		//Cerramos este InputStream antes de continuar
		if(tipo == EXPRESION){
			ret = new Operando(dis.readUTF());
		}else if(tipo == RESULTADO){
			ret = new Operando(new Integer(dis.readInt()));
		}
		switch (tipo) {
		case VALOR_NUMERICO:
			ret = new Operando(ValorNumerico.load(is));
			break;
		case VARIABLE:
			ret = new Operando(Variable.load(is));
			break;
		case MATRIZ:
			ret = new Operando(Matriz.load(is));
			break;
		case FUNCION:
			ret = new Operando(Funcion.load(is));
			break;
		case RESULTADO:
			ret = new Operando(new Integer(dis.readInt()));
			break;
		default:
			break;
		}
		return ret;
	}
	public void save(OutputStream os) throws IOException{
		DataOutputStream dos = new DataOutputStream(os);
		//Escribir primero el tipo de informacion que vamos a almacenar
		dos.writeInt(this.tipo);
		//Al guardar en disco si es de tipo Resultado entonces cogemos el identificador de 
		//operacion de linea y lo guardamos para al evaluar la operacion cargarlo correctamente
		//Si es una expresion lo guardamos como utf8
		if(this.tipo == RESULTADO){
			dos.writeInt(((Integer) this.operando).intValue());
		}else if(this.tipo == EXPRESION){
			dos.writeUTF(((String) this.operando));
		}
		switch (tipo) {
		case VARIABLE:
			((Variable) this.operando).save(os);
			break;
		case VALOR_NUMERICO:
			((ValorNumerico) this.operando).save(os);
			break;
		case MATRIZ:
			((Matriz) this.operando).save(os);
			break;
		case FUNCION:
			((Funcion)this.operando).save(os);
			break;
		case RESULTADO:
			dos.writeInt(((Integer)this.operando).intValue());
			break;
		default:
			break;
		}
	}
	public byte[] getBytes() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.save(baos);
		return baos.toByteArray();
	}
}
