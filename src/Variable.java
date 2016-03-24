
public class Variable {
	private String name;
	private ValorNumerico[] valor;
	private boolean inverso;
	
	public Variable(String name){
		this.name = name;
		this.valor = null;
		this.inverso = false;
	}
	public Variable(String name,ValorNumerico val){
		this.name = name;
		this.valor = new ValorNumerico[]{val};
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
	public ValorNumerico getValue(int i) throws VariableException{
		if(i < this.valor.length){
			return this.valor[i];
		}else{
			throw new VariableException("");
		}
	}
	public void setValor(ValorNumerico valor,int i) throws VariableException{
		if(i == this.valor.length){
			ValorNumerico[] aux = new ValorNumerico[this.valor.length + 1];
			for (int j = 0; j < this.valor.length; j++) {
				aux[j] = this.valor[j];
			}
			aux[aux.length-1] = valor;
			this.valor = new ValorNumerico[aux.length];
			for (int j = 0; j < this.valor.length; j++) {
				this.valor[j] = aux[j];
			}
		}else if(i < this.valor.length){
			this.valor[i] = valor;
		}else{
			throw new VariableException("");
		}
	}
}
