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
	public static final int MAX = 3;
	public static final int MIN = 4;
	public static final int LENGTH = 5;
	public static final int SIN = 6;
	public static final int COS = 7;
	public static final int TAN = 8;
	public static final int ASIN = 9;
	public static final int ACOS = 10;
	public static final int ATAN = 11;
	
	public static Object ejecutar(int id,Operando[] ops) throws InterpreteException{
		switch(id){
		case IS_PRIME:
			break;
		case SQRT:
			return sqrt(ops);
		case MAX:
			return max(ops);
		case MIN:
			return min(ops);
		case LENGTH:
			return length(ops);
		case SIN:
			return sin(ops);
		case COS:
			return cos(ops);
		case TAN:
			return tan(ops);
		case ASIN:
			return asin(ops);
		case ACOS:
			return acos(ops);
		case ATAN:
			return atan(ops);
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
	/**
	 * Obtiene el valor Numerico maximo
	 * @param ops
	 * @return
	 * @throws InterpreteException
	 */
	public static Object max(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				return ((ValorNumerico)ops[1].getValor());
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				return ((Matriz)ops[1].getValor()).max();
			}else{
				throw new InterpreteException("Crash Error in max()");
			}
		}else{
			throw new InterpreteException("Needed only 1 argument");
		}
	}
	/**
	 * Obtiene el valor Numerico Minimo
	 * @param ops
	 * @return
	 * @throws InterpreteException
	 */
	public static Object min(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				return ((ValorNumerico)ops[1].getValor());
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				return ((Matriz)ops[1].getValor()).min();
			}else{
				throw new InterpreteException("Crash Error in min()");
			}
		}else{
			throw new InterpreteException("Needed only 1 argument");
		}
	}
	public static Object length(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				return ValorNumerico.ONE;
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				Matriz mat = ((Matriz)ops[1].getValor());
				Matriz ret = new Matriz(new int[]{1,2});
				ret.set(new int[]{0, 0 }, new ValorNumerico(mat.dimensions()[0]));
				ret.set(new int[]{0, 1 }, new ValorNumerico(mat.dimensions()[1]));
				return ret;
			}else{
				throw new InterpreteException("Crash Error in length()");
			}
		}else{
			throw new InterpreteException("Needed only 1 argument");
		}
	}
	public static Object cos(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				try {
					return ((ValorNumerico)ops[1].getValor()).cos();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				try {
					return ((Matriz)ops[1].getValor()).cos();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else{
				throw new InterpreteException("Crash Error in cos()");
			}
		}else{
			throw new InterpreteException("Needed only 1 argument");
		}
	}
	public static Object acos(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				try {
					return ((ValorNumerico)ops[1].getValor()).acos();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				try {
					return ((Matriz)ops[1].getValor()).acos();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else{
				throw new InterpreteException("Crash Error in acos()");
			}
		}else{
			throw new InterpreteException("Needed only 1 argument");
		}
	}
	public static Object sin(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				try {
					return ((ValorNumerico)ops[1].getValor()).sin();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				try {
					return ((Matriz)ops[1].getValor()).sin();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else{
				throw new InterpreteException("Crash Error in sin()");
			}
		}else{
			throw new InterpreteException("Needed only 1 argument");
		}
	}
	public static Object asin(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				try {
					return ((ValorNumerico)ops[1].getValor()).asin();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				try {
					return ((Matriz)ops[1].getValor()).asin();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else{
				throw new InterpreteException("Crash Error in asin()");
			}
		}else{
			throw new InterpreteException("Needed only 1 argument");
		}
	}
	public static Object tan(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				try {
					return ((ValorNumerico)ops[1].getValor()).tan();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				try {
					return ((Matriz)ops[1].getValor()).tan();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else{
				throw new InterpreteException("Crash Error in tan()");
			}
		}else{
			throw new InterpreteException("Needed only 1 argument");
		}
	}
	public static Object atan(Operando[] ops) throws InterpreteException{
		ValorNumerico val = (ValorNumerico)ops[1].getValor();
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				try {
					return ((ValorNumerico)ops[1].getValor()).atan();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				try {
					return ((Matriz)ops[1].getValor()).atan();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else{
				throw new InterpreteException("Crash Error in atan()");
			}
		}else{
			throw new InterpreteException("Needed only 1 argument");
		}
	}
}
