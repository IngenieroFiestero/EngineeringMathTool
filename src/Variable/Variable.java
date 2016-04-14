package Variable;

import ValorNumerico.ValorNumerico;

public class Variable {
	private String name;
	private ValorNumerico valor;
	private boolean inverso;
	
	public Variable(String name){
		this.name = name;
		this.valor = null;
		this.inverso = false;
	}
	public Variable(String name,ValorNumerico val){
		this.name = name;
		this.valor = val;
		this.inverso = false;
	}
	public String getName(){
		return this.name;
	}
	public boolean getInverso(){
		return this.inverso;
	}
	public void inverse(){
		if(inverso){
			inverso = false;
		}else{
			inverso = true;
		}
		
	}
	public ValorNumerico getValue(){
		return this.valor;
	}
	public void setValor(ValorNumerico valor){
		this.valor = valor;
	}
}
