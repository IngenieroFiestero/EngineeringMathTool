package Operaciones;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import MathTool.InterpreteException;
import MathTool.MathInterprete;

/**
 * Compila una línea de codigo
 *
 */
public class ExpresionInterpretada{
	//Valores para una vez analizada la cadena no tener que reanalizarla

	private String linea;//Al guardar un script ya se esan guardando todas las lineas luego esta es innecesaria
	private int tipo;
	private boolean compilado;
	private transient boolean condicion;
	private ListaOperaciones operaciones;//Lista de operaciones a interpretar(compilado)
	private String name;//El nombre de la funcion
	private String[] args;//Si es una funcion que argumentos tiene?
	//Cada entrada del arrayList es un array de operaciones que se pueden ejecutar en paralelo
	//De esta forma conseguimos compilar una vez el script y tenerlo guardado para siempre
	
	/**
	 * Representa una sola linea de codigo
	 * @param linea
	 * @param mi
	 * @throws InterpreteException 
	 */
	public ExpresionInterpretada(String linea){
		this.linea = linea;
		this.compilado = false;
		this.condicion = true;
	}
	public String getLine(){
		return this.linea;
	}
	public String getFunctionName(){
		return this.name;
	}
	/**
	 * Ejecutar las siguientes lineas que estan dentro del bulce o condicion?
	 * @return
	 */
	public boolean getCondicion(){
		return this.condicion;
	}
	public int getTipo(){
		return this.tipo;
	}
	private void evaluarCondicion(){
		
	}
	
	public void setListaOperaciones(ListaOperaciones lista){
		this.operaciones = lista;
	}
	public ListaOperaciones getListaOperaciones(){
		return this.operaciones;
	}
	public static Operacion load(InputStream is) throws IOException{
		DataInputStream dis = new DataInputStream(is);
		int operador = dis.readInt();
		int lngth = dis.readInt();
		int id = dis.readInt();
		//Cerramos este InputStream antes de continuar
		dis.close();
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
		daos.writeInt(this.tipo);
		if(this.tipo == MathInterprete.FUNCION){
			//Si es una funcion (Primera linea del script) primero se escribe el nombre de la funcion
			daos.writeUTF(this.name);
			daos.writeInt(args.length);
			for (int i = 0; i < args.length; i++) {
				daos.writeUTF(args[i]);
			}
			daos.close();
		}else{
			daos.close();
			operaciones.save(os);
		}
	}
	public byte[] getBytes() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.save(baos);
		return baos.toByteArray();
	}
	
}
