package MathTool;
import java.util.ArrayList;

import ValorNumerico.ValorNumerico;
import Variable.Variable;
/**
 * Lista de variables
 * @author admin
 *
 */
public class ListaVariables {
	ArrayList<Variable> variableList = new ArrayList<Variable>();
	
	public ListaVariables(ArrayList<Variable> lista){
		this.variableList = lista;
	}
	/**
	 * Busca una variable dentro del contexto utilizando para ello un nombre(String)
	 * @param name Nombre de la variable
	 * @return Variable
	 * @throws InterpreteException
	 */
	public Variable findVariableByName(String name) throws InterpreteException{
		for (int i = 0; i < variableList.size(); i++) {
			if(variableList.get(i).getName().equals(name)){
				return variableList.get(i);
			}
		}
		throw new InterpreteException(InterpreteException.generateCantFindError(name));
	}
	public void addVariable(Variable var)throws InterpreteException{
		if(variableList.contains(var)){
			throw new InterpreteException(InterpreteException.generateAlredyExistsError(var.getName()));
		}else{
			variableList.add(var);
		}
	}
	/**
	 * Aqui se obtienen las funciones por defecto
	 * @return ListaVariables por defecto, osea ninguna
	 */
	public static ListaVariables listaPorDefecto(){
		ArrayList<Variable> lista = new ArrayList<Variable>();
		lista.add(new Variable("e",ValorNumerico.E,true));
		lista.add(new Variable("pi",ValorNumerico.PI,true));
		return new ListaVariables(lista);
	}
	public Variable[] getVariables(){
		return variableList.toArray(new Variable[variableList.size()]);
	}
}
