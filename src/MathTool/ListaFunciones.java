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
