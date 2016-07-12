package MathTool;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

import Variable.Variable;

public class ContextoMatematico implements Serializable{
	private static final long serialVersionUID = -8069695659613918920L;
	
	private ListaVariables variableList;
	private ListaFunciones funcionList;
	private ListaScripts scriptList;
	public static MathContext MATH_CONTEXT = new MathContext(MathContext.UNLIMITED.getPrecision(),RoundingMode.UP);
	
	public ContextoMatematico(){
		this.variableList = ListaVariables.listaPorDefecto();
		this.funcionList = ListaFunciones.listaPorDefecto();
		this.scriptList = new ListaScripts();
		MATH_CONTEXT = new MathContext(MathContext.UNLIMITED.getPrecision(),RoundingMode.HALF_EVEN);
	}
	/**
	 * Contexto matematico, contiene las funciones y variables que usar el interprete para poder
	 * Dar valores a las expresiones y clasificar las expresiones. Es serializable con lo cual se 
	 * puede guardar para poder asi trabajar con diferentes espacios de trabajo.
	 */
	public ContextoMatematico(ListaVariables variableList,ListaFunciones funcionList,ListaScripts scriptList){
		this.variableList = variableList;
		this.funcionList = funcionList;
		this.scriptList = scriptList;
	}
	
	/**
	 * Lee desde un InputStream un objeto de tipo MathContext
	 * Utilizamos InputStream para poder utilizar cualquier sistema de lectura, desde un string hasta un socket pasando por el directorio de archivos
	 * @param is InputStream desde donde leer el MathContext
	 * @return Un contexto
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static ContextoMatematico loadMathContext(InputStream is) throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(is);
		ContextoMatematico ret = (ContextoMatematico) ois.readObject();
		return ret;
	}
	/**
	 * Busca una variable dentro del contexto utilizando para ello un nombre(String)
	 * @param name Nombre de la variable
	 * @return Variable
	 * @throws InterpreteException
	 */
	public Variable findVariableByName(String name) throws InterpreteException{
		return variableList.findVariableByName(name);
	}
	/**
	 * Busca una funcion dentro del contexto utilizando para ello un nombre(String) y devuelve si existe o si no existe
	 * @param name Nombre de la funcion
	 * @return Boolean
	 * @throws InterpreteException
	 */
	public boolean existsFuncion(String name){
		try {
			Funcion fun = funcionList.findFuncionByName(name);
			
			return true;
		} catch (InterpreteException e) {
			return false;
		}
	}
	/**
	 * Busca una variable dentro del contexto utilizando para ello un nombre(String) y devuelve si existe o si no existe
	 * @param name Nombre de la variable
	 * @return Boolean
	 * @throws InterpreteException
	 */
	public boolean existsVariable(String name){
		try {
			Variable var = variableList.findVariableByName(name);
			return true;
		} catch (InterpreteException e) {
			return false;
		}
	}
	/**
	 * Busca una funcion dentro del contexto utilizando para ello un nombre(String).
	 * @param name Nombre de la funcion
	 * @return Funcion
	 * @throws InterpreteException
	 */
	public Funcion findFuncionByName(String name) throws InterpreteException{
		return funcionList.findFuncionByName(name);
	}
	public void addVariable(Variable var){
		try {
			variableList.addVariable(var);
		} catch (InterpreteException e) {}
	}
	public void addFuncion(Funcion fun){
		funcionList.addFuncion(fun);
	}
	public ListaVariables getVariableList(){
		return this.variableList;
	}

}
