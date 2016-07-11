package MathTool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import Operaciones.ExpresionInterpretada;

public class Script {
	private String name;
	ArrayList<String> script;//Busquedas rapidas dentro del script
	private int[] changes;
	private ExpresionInterpretada[] compiledLine;
	/**
	 * Crea un nuevo script asignandole un nombre y el texto del script.
	 * @param name
	 * @param script
	 */
	public Script(String name,String script){
		this.name = name;
		String[] split = script.split("\n");
		changes = new int[split.length];
		this.script = new ArrayList<String>();
		String add = "";
		int lvl = 0;
		for (int i = 0; i < split.length; i++) {
			add = add + split[i];
			lvl = lvl + getLineLevel(split[i]);
			if(lvl == 0){
				System.out.println(add);
				this.script.add(add);
				add = "";
				changes[i]=i;//Hacer como si no hubiera cambiado nada de la anterior a esta
			}
		}
	}
	/**
	 * Busca que lineas se mantienen y que linea ocupan ahora
	 * @param script
	 */
	public void setScript(String script){
		setNullArray(this.script.size());
		String[] split = script.split("\n");
		for (int i = 0; i < split.length; i++) {
			int index = this.script.indexOf(split[i]);
			if(index >=0){
				changes[index] = i;
			}
		}
	}
	/**
	 * Devuelve la lista de expresiones
	 * @return
	 */
	public ExpresionInterpretada[] getExpresiones(){
		return this.compiledLine;
	}
	/**
	 * Devuelve la linea que ocupa ahora cada una de las lineas de la anterior actualizacion tras el cambio.
	 * Si un valor es -1 es que se ha eliminado dicha linea.
	 * @return
	 */
	public int[] getLineChanges(){
		return this.changes;
	}
	private void setNullArray(int n){
		changes = new int[n];
		for (int i = 0; i < changes.length; i++) {
			changes[i] = -1;
		}
	}
	public void saveScript(OutputStream os) throws IOException{
		for (int i = 0; i < script.size(); i++) {
			os.write(script.get(i).getBytes());
		}
	}
	public static  String[] loadScript(InputStream is) throws IOException{
		String data = "";
		byte[] buff = new byte[1024];
		int count = 0;
		while ((count = is.read(buff)) > -1) {
			data = data + new String(buff,0,count);
		}
		return data.split("\n");
	}
	public String getName(){
		return this.name;
	}
	public static int getLineLevel(String txt){
		int lvl = 0;
		for (int i = 0; i < txt.length(); i++) {
			switch (txt.charAt(i)) {
			case '[':
				lvl++;
				break;
			case '(':
				lvl++;
				break;
			case '{':
				lvl++;
				break;
			case ']':
				lvl--;
				break;
			case ')':
				lvl--;
				break;
			case '}':
				lvl--;
				break;
			default:
				break;
			}
		}
		return lvl;
	}
}
