package Operaciones;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ListaOperaciones implements Serializable{

	private static final long serialVersionUID = 7283495046132364708L;
	
	private Map<Integer, Operacion> operaciones;
	private Integer position;
	
	public ListaOperaciones(){
		operaciones = new TreeMap<Integer, Operacion>();
		position = 0;
	}
	public Operacion get(Integer id){
		return operaciones.get(id);
	}
	public int insert(Operacion op){
		int ret = position.intValue();
		operaciones.put(position, op);
		op.setId(ret);
		position++;
		return ret;
	}
	public int size(){
		return operaciones.size();
	}
	public static ListaOperaciones load(InputStream is) throws IOException{
		DataInputStream dis = new DataInputStream(is);
		ListaOperaciones list = new ListaOperaciones();
		int size = dis.readInt();
		dis.close();
		for (int i = 0; i < size; i++) {
			list.insert(Operacion.load(is));
		}
		return list;
	}
	/**
	 * Este guardado no guarda la ultima posicion en la que se quedo la lista de operaciones, pues el objetivo es tener un objeto inamovible
	 * para poder interpretar operaciones almacenadas en disco
	 * @param os
	 * @throws IOException
	 */
	public void save(OutputStream os) throws IOException{
		DataOutputStream daos = new DataOutputStream(os);
		daos.writeInt(operaciones.size());
		daos.close();
		for (int i = 0; i < operaciones.size(); i++) {
			operaciones.get(new Integer(i)).save(os);
		}
	}
	public byte[] getBytes() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.save(baos);
		return baos.toByteArray();
	}
}
