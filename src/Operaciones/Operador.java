package Operaciones;

import MathTool.Funcion;
import MathTool.GestorScript;
import MathTool.InterpreteException;
import MathTool.InterpreteScript;
import MathTool.MathContext;
import MathTool.MathInterprete;
import Matriz.Matriz;
import Matriz.MatrizException;
import ValorNumerico.ValorNumerico;
import ValorNumerico.ValorNumericoException;
import Variable.Variable;
import Variable.VariableException;

public class Operador {
	public static final int SUMA = 0;
	public static final int MULTIPLICACION = 1;
	public static final int RESTA = 2;
	public static final int DIVISION = 3;
	public static final int MODULO = 4;
	public static final int MULTIPLICACION_P2P = 5;
	public static final int DIVISION_P2P = 6;
	public static final int MODULO_P2P = 7;
	public static final int EXPONENTE = 8;
	public static final int EXPONENTE_P2P = 9;
	public static final int TRASPONER = 10;
	public static final int ASIGNACION = 11;
	public static final int EJECUTAR_FUNCION = 12;
	public static final int CIERRE_LINEA = 13;
	public static final int EVALUACION = 14;
	public static final String[] OPERADORES = new String[]{"+","*","-","/","%",".*","./",".%","^",".^","'","=","function",";","eval"};
	
	public static Object suma(Operando op1, Operando op2) throws MatrizException, ValorNumericoException{
		if(op1.getTipo() == Operando.VALOR_NUMERICO && op2.getTipo() == Operando.VALOR_NUMERICO){
			return (((ValorNumerico)op1.getValor()).add((ValorNumerico)op2.getValor()));
		}else if(op1.getTipo() == Operando.MATRIZ && op2.getTipo() == Operando.MATRIZ){
			return (((Matriz)op1.getValor()).sumar((Matriz)op2.getValor()));
		}else if(op1.getTipo() == Operando.VALOR_NUMERICO && op2.getTipo() == Operando.MATRIZ){
			return (new Matriz((ValorNumerico)op1.getValor())).sumar((Matriz)op2.getValor());
		}else if(op2.getTipo() == Operando.VALOR_NUMERICO && op1.getTipo() == Operando.MATRIZ){
			return (new Matriz((ValorNumerico)op2.getValor())).sumar((Matriz)op1.getValor());
		}else{
			return null;
		}
	}
	public static Object multiplicacion(Operando op1, Operando op2) throws MatrizException, ValorNumericoException{
		if(op1.getTipo() == Operando.VALOR_NUMERICO && op2.getTipo() == Operando.VALOR_NUMERICO){
			return (((ValorNumerico)op1.getValor()).multiply((ValorNumerico)op2.getValor()));
		}else if(op1.getTipo() == Operando.MATRIZ && op2.getTipo() == Operando.MATRIZ){
			return (((Matriz)op1.getValor()).multiplicar((Matriz)op2.getValor()));
		}else if(op1.getTipo() == Operando.VALOR_NUMERICO && op2.getTipo() == Operando.MATRIZ){
			return (new Matriz((ValorNumerico)op1.getValor())).multiplicar((Matriz)op2.getValor());
		}else if(op2.getTipo() == Operando.VALOR_NUMERICO && op1.getTipo() == Operando.MATRIZ){
			return (new Matriz((ValorNumerico)op2.getValor())).multiplicar((Matriz)op1.getValor());
		}else{
			return null;
		}
	}
	public static Object evaluacion(Operando op1,MathInterprete mi) throws VariableException, InterpreteException{
		if(op1.getTipo() == Operando.VARIABLE){
			return mi.getMathContext().findVariableByName(((Variable)op1.getValor()).getName()).getValue();
		}else{
			throw new VariableException("Not valid");
		}
	}
	public static Object ejecutarFuncion(Operando[] ops,MathInterprete mi) throws VariableException, InterpreteException{
		if(ops.length >= 1){
			if(ops[0].getTipo() == Operando.FUNCION){
				Funcion fun = null;
				if((fun = mi.getMathContext().findFuncionByName(((Funcion)ops[0].getValor()).getName())) != null){
					MathContext mc = new MathContext();
					String[] args = null;
					if(ops.length-1 > (args = fun.getArgs()).length){
						throw new InterpreteException("Excess of function arguments");
					}
					//Añadir las variables al contexto para que la funcion pueda ejecutarse
					for (int i = 1; i < ops.length; i++) {
						mc.addVariable(new Variable(args[i-1],ops[i]));
					}
					//Ejecutar script:
				}else{
					throw new InterpreteException("Cant find function in context");
				}
			}else{
				throw new InterpreteException("Not valid function operation");
			}
		}else{
			throw new InterpreteException("Cant find function name");
		}
		return null;
	}
}
