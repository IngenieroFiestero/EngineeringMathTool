package Operaciones;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ListaOperaciones implements Serializable{

	private static final long serialVersionUID = 7283495046132364708L;
	
	private Map<Integer, Operacion> operaciones;
	private Integer position;
	
	/**
	 * Crea una lista de operaciones enlazadas. Esto nos permite poder almacenar, sin necesidad de referencias de java, las operaciones
	 * utilizando identificadores en los operandos para luego buscar dicho Rsultado en la operacion con el ID deseado.
	 */
	public ListaOperaciones(){
		operaciones = new TreeMap<Integer, Operacion>();
		position = 0;
	}
	public Operacion get(Integer id){
		return operaciones.get(id);
	}
	public synchronized int insert(Operacion op){
		int ret = position.intValue();
		operaciones.put(position, op);
		op.setId(ret);
		position++;
		return ret;
	}
	public synchronized int size(){
		return operaciones.size();
	}
	public static ListaOperaciones load(InputStream is) throws IOException{
		DataInputStream dis = new DataInputStream(is);
		ListaOperaciones list = new ListaOperaciones();
		int size = dis.readInt();
		for (int i = 0; i < size; i++) {
			list.insert(Operacion.load(is));
		}
		return list;
	}
	private synchronized void reordenarLista(){
		//Recorremos toda la lista
		ArrayList<String> al = new ArrayList<String>();
		int elementos = 0;
		while(elementos < operaciones.size()){
			elementos++;
			Operacion op = operaciones.get(elementos);
			Operando[] ops = op.getOperandos();
			int firstOp = Integer.MAX_VALUE;//Valor que indica la operacion que debe realizarse antes que las demas
			for (int i = 0; i < ops.length; i++) {
				if(ops[i].getTipo() == Operando.RESULTADO){
					int val = 0;
					if((val = ((Integer)ops[i].getValor()).intValue()) < firstOp){
						//Si el ultimo
						firstOp = val;
					}
				}
			}
		}
	}
	/**
	 * Este guardado no guarda la ultima posicion en la que se quedo la lista de operaciones, pues el objetivo es tener un objeto inamovible
	 * para poder interpretar operaciones almacenadas en disco
	 * @param os
	 * @throws IOException
	 */
	public synchronized void save(OutputStream os) throws IOException{
		DataOutputStream daos = new DataOutputStream(os);
		daos.writeInt(operaciones.size());
		for (int i = 0; i < operaciones.size(); i++) {
			operaciones.get(new Integer(i)).save(os);
		}
	}
	public synchronized byte[] getBytes() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.save(baos);
		return baos.toByteArray();
	}
}
