package Variable;

import ValorNumerico.ValorNumerico;

/**
 * Al igual que un valor numerico representa un numero, un ValorVariable representa la forma de una variable. Ej: 3x^2. De forma
 * que tenemos un sistema completo de operaciones para operar con variables.
 * @author IngenieroFiestero
 *
 */
public class ValorVariable {
	private Variable variable;
	private ValorNumerico coeficiente;
	private ValorNumerico exponente;
	
	public ValorVariable(Variable var){
		this.variable = var;
		this.coeficiente = new ValorNumerico(0);
		this.exponente = new ValorNumerico(1);
	}
	public Variable getVariable(){
		return this.variable;
	}
	public ValorNumerico getCoeficiente(){
		return this.coeficiente;
	}
	public ValorNumerico getExponente(){
		return this.exponente;
	}
	public void add(ValorVariable val){
		if(this.variable == val.getVariable()){
			
		}else{
			
		}
	}
}
