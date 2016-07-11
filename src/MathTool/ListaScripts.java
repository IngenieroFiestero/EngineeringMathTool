package MathTool;

import java.util.ArrayList;

public class ListaScripts {
	ArrayList<Script> list;
	
	public ListaScripts(){
		this.list = new ArrayList<Script>();
	}
	public synchronized void addScript(Script script){
		list.add(script);
	}
	public synchronized Script findScriptByName(String name){
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(name)){
				return this.list.get(i);
			}
		}
		return null;
	}
}
