package Matriz;

import java.util.ArrayList;
import java.util.LinkedList;

import MathTool.MathInterprete;


public class MatrizExpresion {
	private String[] vector;
	private MathInterprete mi;
	private ArrayList<Object[]> lista;
	private Integer[] colYfil;
	/**
	 * Constructor que permite guardar una matriz con llamadas a variables y funciones
	 * @param txt
	 * @throws MatrizException
	 */
	public MatrizExpresion(String txt) throws MatrizException{
		txt.replaceFirst("\n", "");
		txt.replaceFirst("\t", "");
		txt.replaceFirst(" ", "");
		ArrayList<String> ret = new ArrayList<String>();
		//Encontrar las dimensiones provisionales
		LinkedList<Integer> cols  = new LinkedList<Integer>();
		cols.add(1);
		int lvl = 0;
		int lastPos = -1;
		char[] separadores = new char[]{'[',']','(',')','{','}',',',';'};
		//Cada uno de estos separadores corresponde a una operacion matematica o expresion para acceder a funciones matrices o objetos
		boolean encontrado = false;
		String last = "";
		for (int i = 0; i < txt.length(); i++) {
			encontrado = false;
			//Buscar separadores en cada caracter
			for (int j = 0; j < separadores.length && !encontrado; j++) {
				if(txt.charAt(i) == separadores[j]){
					if(j >=6 && lvl == 0){
						encontrado = true;
						if(lastPos < i-1){
							ret.add(last);
							last = "";
							ret.add( ""+txt.charAt(i));
							lastPos = i;
						}
						if(j==6){
							int ultimo = cols.getLast().intValue()+1;
							cols.set(cols.size()-1, new Integer(ultimo));
						}else{
							cols.add(1);
						}
					}else if(j < 6){
						if(j%2 == 1){
							lvl--;
						}else if(j%2 == 0){
							lvl++;
						}
					}
				}
			}
			if(!encontrado){
				last = last + txt.charAt(i);
			}
		}
		colYfil = cols.toArray(new Integer[cols.size()]);
		ret.add(last);
		vector = ret.toArray(new String[ret.size()]);
	}
	public void setMathInterprete(MathInterprete mi){
		this.mi = mi;
	}
	public String toString(){
		String ret = "";
		for (int i = 0; i < vector.length; i++) {
			ret=ret + vector[i]+"\n";
		}
		return ret;
	}
	/**
	 * Obtiene una matriz que al interpretar permite obtener sus valores,porque sus valores en realidad pueden ser funciones o variables a los que hay que acceder para obtener sus dimensiones
	 */
	public void compilar(){
		lista = new ArrayList<Object[]>();
		for (int i = 0; i < colYfil.length; i++) {
			lista.add(new Object[((Integer)colYfil[i]).intValue()]);
		}
		int col = 0;
		int fil = 0;
		for (int i = 0; i < vector.length; i++) {
			if(vector[i].equals(",")){
				col++;
			}else if(vector[i].equals(";")){
				fil++;
				col = 0;
			}else{
				Object[] tmp = lista.get(fil);
				//tmp[col] = mi.evaluarExpresion(vector[i]);
			}
		}
		for (int i = 0; i < lista.size(); i++) {
			Object[] vals = lista.get(i);
			for (int j = 0; j < vals.length; j++) {
				System.out.println((String)vals[j]);
			}
		}
	}
	
}
