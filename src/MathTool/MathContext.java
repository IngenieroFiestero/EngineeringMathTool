package MathTool;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Variable.Variable;

public class MathContext implements Serializable{
	private static final long serialVersionUID = -8069695659613918920L;
	
	private ListaVariables variableList;
	private ListaFunciones funcionList;
	
	public MathContext(){
		this.variableList = variableList;
		this.funcionList = funcionList;
	}
	/**
	 * Contexto matematico, contiene las funciones y variables que usará el intérprete para poder
	 * Dar valores a las expresiones y clasificar las expresiones. Es serializable con lo cual se 
	 * puede guardar para poder asi trabajar con diferentes espacios de trabajo.
	 */
	public MathContext(ListaVariables variableList,ListaFunciones funcionList){
		this.variableList = variableList;
		this.funcionList = funcionList;
	}
	
	/**
	 * Lee desde un InputStream un objeto de tipo MathContext
	 * Utilizamos InputStream para poder utilizar cualquier sistema de lectura, desde un string hasta un socket pasando por el directorio de archivos
	 * @param is InputStream desde donde leer el MathContext
	 * @return Un contexto
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static MathContext loadMathContext(InputStream is) throws IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(is);
		MathContext ret = (MathContext) ois.readObject();
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
	 * Busca una funcion dentro del contexto utilizando para ello un nombre(String).
	 * @param name Nombre de la funcion
	 * @return Funcion
	 * @throws InterpreteException
	 */
	public Funcion findFuncionByName(String name) throws InterpreteException{
		return funcionList.findFuncionByName(name);
	}

}
