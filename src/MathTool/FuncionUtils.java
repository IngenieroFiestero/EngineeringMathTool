package MathTool;

import Matriz.Matriz;
import Operaciones.Operacion;
import Operaciones.Operando;
import Operaciones.Resultado;
import ValorNumerico.ValorNumerico;
import ValorNumerico.ValorNumericoException;


public class FuncionUtils {
	public static final int IS_PRIME = 1;
	public static final int SQRT = 2;
	
	public static Object ejecutar(int id,Operando[] ops) throws InterpreteException{
		switch(id){
		case IS_PRIME:
			break;
		case SQRT:
			return sqrt(ops);
		}
		return null;
	}
	/**
	 * Funcion para el calculo de sqrt al llamar a una funcion
	 * @param ops
	 * @return
	 * @throws InterpreteException
	 */
	public static Object sqrt(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				try {
					return ((ValorNumerico)ops[1].getValor()).sqrt();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				try {
					return ((Matriz)ops[1].getValor()).sqrt();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else{
				throw new InterpreteException("Crash Error in sqrt()");
			}
		}else{
			throw new InterpreteException("Needed only 1 argument");
		}
	} 
}
