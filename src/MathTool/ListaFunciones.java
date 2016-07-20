package MathTool;
import java.util.ArrayList;

public class ListaFunciones {
	ArrayList<Funcion> funcionList;
	
	public ListaFunciones(ArrayList<Funcion> lista){
		this.funcionList = lista;
	}
	public ListaFunciones(){
		funcionList = new ArrayList<Funcion>();
	}
	/**
	 * Busca una funcion dentro del contexto utilizando para ello un nombre(String).
	 * @param name Nombre de la funcion
	 * @return Funcion
	 * @throws InterpreteException
	 */
	public Funcion findFuncionByName(String name) throws InterpreteException{
		for (int i = 0; i < funcionList.size(); i++) {
			if(funcionList.get(i).getName().equals(name)){
				return funcionList.get(i);
			}
		}
		throw new InterpreteException(InterpreteException.generateCantFindFunctionError(name));
	}
	
	/**
	 * Lista de funciones globales por defecto
	 * @return ListaFunciones globales por defeto
	 */
	public static ListaFunciones listaPorDefecto(){
		ArrayList<Funcion> lista = new ArrayList<Funcion>();
		lista.add(new Funcion("sqrt",FuncionUtils.SQRT));
		lista.add(new Funcion("max",FuncionUtils.MAX));
		lista.add(new Funcion("min",FuncionUtils.MIN));
		lista.add(new Funcion("length",FuncionUtils.LENGTH));
		lista.add(new Funcion("sin",FuncionUtils.SIN));
		lista.add(new Funcion("cos",FuncionUtils.COS));
		lista.add(new Funcion("tan",FuncionUtils.TAN));
		lista.add(new Funcion("asin",FuncionUtils.ASIN));
		lista.add(new Funcion("acos",FuncionUtils.ACOS));
		lista.add(new Funcion("atan",FuncionUtils.ATAN));
		lista.add(new Funcion("pow",FuncionUtils.POW));
		lista.add(new Funcion("factorial",FuncionUtils.FACTORIAL));
		lista.add(new Funcion("log",FuncionUtils.LOG));
		lista.add(new Funcion("ln",FuncionUtils.LN));
		lista.add(new Funcion("log10",FuncionUtils.LOG10));
		lista.add(new Funcion("abs",FuncionUtils.ABS));
		lista.add(new Funcion("transpose",FuncionUtils.TRANSPOSE));
		lista.add(new Funcion("matrixIntegration",FuncionUtils.MATRIX_INT));
		return new ListaFunciones(lista);
	}
	public void addFuncion(Funcion fun){
		try {
			findFuncionByName(fun.getName());
		} catch (InterpreteException e) {
			this.funcionList.add(fun);
		}
		
	}
}
