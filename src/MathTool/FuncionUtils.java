package MathTool;

import Matriz.Matriz;
import Matriz.MatrizException;
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
	public static final int ABS = 12;
	public static final int POW = 13;
	public static final int LOG = 14;
	public static final int LN = 14;
	public static final int LOG10 = 15;
	public static final int FACTORIAL = 16;
	public static final int TRANSPOSE = 17;
	public static final int MATRIX_INT = 18;
	
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
		case LOG:
			return log(ops);
		case LOG10:
			return log10(ops);
		case ABS:
			return abs(ops);
		case POW:
			return pow(ops);
		case FACTORIAL:
			return factorial(ops);
		case TRANSPOSE:
			return transpose(ops);
		case MATRIX_INT:
			return matrixIntegration(ops);
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
	public static Object log(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				try {
					return ((ValorNumerico)ops[1].getValor()).log();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				try {
					return ((Matriz)ops[1].getValor()).log();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else{
				throw new InterpreteException("Crash Error in log()");
			}
		}else{
			throw new InterpreteException("Needed only 1 argument");
		}
	}
	public static Object log10(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				try {
					return ((ValorNumerico)ops[1].getValor()).log10();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				try {
					return ((Matriz)ops[1].getValor()).log10();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else{
				throw new InterpreteException("Crash Error in log10()");
			}
		}else{
			throw new InterpreteException("Needed only 1 argument");
		}
	}
	public static Object abs(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				return ((ValorNumerico)ops[1].getValor()).abs();
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				try {
					return ((Matriz)ops[1].getValor()).abs();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else{
				throw new InterpreteException("Crash Error in abs()");
			}
		}else{
			throw new InterpreteException(InterpreteException.generateArgumentNumberError("1"));
		}
	}
	public static Object pow(Operando[] ops) throws InterpreteException{
		if(ops.length == 3){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO && ops[2].getTipo() == Operando.VALOR_NUMERICO){
				try {
					return ((ValorNumerico)ops[1].getValor()).pow((ValorNumerico)ops[2].getValor());
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				throw new InterpreteException(InterpreteException.notImplementedError() + " pow([])");
			}else{
				throw new InterpreteException("Crash Error in pow()");
			}
		}else{
			throw new InterpreteException(InterpreteException.generateArgumentNumberError("2"));
		}
	}
	public static Object factorial(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				try {
					return ((ValorNumerico)ops[1].getValor()).factorial();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				try {
					return ((Matriz)ops[1].getValor()).factorial();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else{
				throw new InterpreteException("Crash Error in factorial()");
			}
		}else{
			throw new InterpreteException(InterpreteException.generateArgumentNumberError("1"));
		}
	}
	public static Object transpose(Operando[] ops) throws InterpreteException{
		if(ops.length == 2){
			if(ops[1].getTipo() == Operando.VALOR_NUMERICO){
				
				return ((ValorNumerico)ops[1].getValor());
			}else if(ops[1].getTipo() == Operando.MATRIZ){
				try {
					
					return ((Matriz)ops[1].getValor()).transpose();
				} catch (ValorNumericoException e) {
					throw new InterpreteException(e.getMessage());
				}
			}else{
				throw new InterpreteException("Crash Error in transpose()");
			}
		}else{
			throw new InterpreteException(InterpreteException.generateArgumentNumberError("1"));
		}
	}
	public static Object matrixIntegration(Operando[] ops) throws InterpreteException{
		if(ops.length >= 3 && ops.length <=4){
			boolean operar = true;
			for (int i = 1; i < ops.length; i++) {
				if(ops[i].getTipo() != Operando.MATRIZ){
					operar = false;
				}
			}
			Matriz matY = (Matriz) ops[1].getValor();
			Matriz matX = (Matriz) ops[2].getValor();
			ValorNumerico[] pos = new ValorNumerico[2];
			//Obtener intervalo de integracion
			if(ops.length == 4){
				Matriz matInt = (Matriz) ops[3].getValor();
				pos[0] = matInt.get(new int[]{0,0});
				pos[0] = matInt.get(new int[]{0,matInt.dimensions()[1]-1});
			}else{
				pos[0] = matX.get(new int[]{0,0});
				pos[0] = matX.get(new int[]{0,matX.dimensions()[1]-1});
			}
			ValorNumerico ret = new ValorNumerico(0);
			for (int i = 0; i < matX.dimensions()[1]-1; i++) {
				int[] position = new int[]{0,i};
				try {
					Matriz valX = matX.getSubMatriz(new int[]{0,1,0}, new int[]{i,1,i+1});
					Matriz valY = matY.getSubMatriz(new int[]{0,1,0}, new int[]{i,1,i+1});
					ret = ret.add(
							(matX.get(new int[]{0,i+1}).substract((matX.get(position))))
							.multiply(
									((valY.max().substract(valY.min()))
									.divide(new ValorNumerico(2)))
									.add((valY.min()))));
				} catch (Exception e) {
					throw new InterpreteException(e.getMessage());
				}
			}
			return ret;
		}else{
			throw new InterpreteException(InterpreteException.generateArgumentNumberError("2"));
		}
	}
}
