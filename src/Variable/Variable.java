package Variable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import Matriz.Matriz;
import ValorNumerico.ValorNumerico;

public class Variable {
	private boolean estatico;
	private String name;
	private transient Object valor;
	
	public Variable(String name){
		this.name = name;
		this.valor = null;
		this.estatico = false;
	}
	public Variable(String name,ValorNumerico val){
		this.name = name;
		this.valor = val;
		this.estatico = false;
	}
	public Variable(String name,Matriz val){
		this.name = name;
		this.valor = val;
		this.estatico = false;
	}
	public Variable(String name,Object val){
		this.name = name;
		this.valor = val;
		this.estatico = false;
	}
	public Variable(String name,Object val,boolean estatico){
		this.name = name;
		this.valor = val;
		this.estatico = estatico;
	}
	public String getName(){
		return this.name;
	}
	public Object getValue(){
		return this.valor;
	}
	public String toString(){
		return this.name;
	}
	public void setValor(Object valor) throws VariableException{
		if(this.estatico){
			throw new VariableException("Cant modifie a static variable");
		}else{
			this.valor = valor;
		}
	}
	public static Variable load(InputStream is) throws IOException{
		DataInputStream dis = new DataInputStream(is);
		return new Variable(dis.readUTF());
	}
	public void save(OutputStream os) throws IOException{
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeUTF(name);//Solo nos interesa el nombre
		dos.close();
	}
	public byte[] toByte() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.save(baos);
		return baos.toByteArray();
	}
}
