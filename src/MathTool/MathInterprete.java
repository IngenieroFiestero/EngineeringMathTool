package MathTool;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Executor;

import Matriz.Matriz;
import Matriz.MatrizException;
import Matriz.MatrizExpresion;
import Operaciones.ExpresionInterpretada;
import Operaciones.ListaOperaciones;
import Operaciones.Operacion;
import Operaciones.Operador;
import Operaciones.Operando;
import Operaciones.Resultado;
import ValorNumerico.ValorNumericoException;
import Variable.Variable;
import Variable.VariableException;

public class MathInterprete {
	public static final int ASIGNACION = 0;
	public static final int EVALUACION = 1;
	public static final int FINAL = 2;
	public static final int INICIO_BUCLE = 3;
	public static final int INICIO_CONDICION = 4;
	public static final int INICIO_SUBCONDICION = 5;
	public static final int FUNCION = 6;
	public static final int THREAD = 7;
	public static final int JOIN_THREADS = 7;
	public static final char[] separadores = new char[]{'=','+','-','*','/','^','%','\'',';',','};//El simbolo ' se utiliza para transponer matrices
	public static final int[] IMPORTANCIA = new int[]{6,4,4,3,3,2,2,1,7,5};
	public static final char[] SEPARADOR_IMPORTANCIA = new char[]{';',',','+','-','*','/','%','^','\''};//El simbolo ' se utiliza para transponer matrices
	public static final char[] SEPRADOR_NIVEL = new char[]{'[',']','{','}','(',')'};//Separadores en niveles
	/**
	 * Funcion para crear nueva funcion, Expresion para resolverla, ASIGNACION
	 * para asignar a una variable un valor, EVALUACION para evaluar comandos
	 * rapidos
	 * 
	 * @author admin
	 *
	 */
	public static enum TIPO {
		FUNCION, SCRIPT, ASIGNACION, EVALUACION
	};

	private ContextoMatematico contexto;

	// Si no se ha usado nunca
	public MathInterprete() {
		contexto = new ContextoMatematico();
	}

	// Si ya se ha usado y hay un contexto se puede cargar
	public MathInterprete(ContextoMatematico contexto) {
		this.contexto = contexto;
	}
	
	public ExpresionInterpretada evaluarExpresion(String txt) throws InterpreteException{
		ExpresionInterpretada ret = new ExpresionInterpretada(txt);
		ListaOperaciones ops = new ListaOperaciones();
		getOperacion(txt,ops);
		ret.setListaOperaciones(ops);
		return ret;
	}
	/**
	 * Obtiene la ultima operacion de la cadena de texto
	 * @param txt
	 * @param ops
	 * @return
	 * @throws InterpreteException 
	 */
	public Operacion getOperacion(String txt,ListaOperaciones ops) throws InterpreteException{
		System.out.println("Llamada con texto: " + txt);
		if(txt == null || txt.length() == 0){
			
		}else if(txt.charAt(txt.length()-1) == ';' && txt.length() > 1){
			//El ultimo caracter es para no imprimir por pantalla
			Operacion op = new Operacion(new Operando[]{
					new Operando(getOperacion(txt.substring(0,txt.length()-1),ops).getId())},
					Operador.CIERRE_LINEA);
			//Hemos creado la operacion ahora la insertamos y le asignamos un id
			ops.insert(op);
			return op;
		}else{
			String[] split = spliterEspecial(txt,new char[]{'='});
			if(split.length == 3 && split[1].equals("=")){
				System.out.println("Asignacion");
				//Es una operacion de asignacion
				Operando op1 = null;
				if(validVariableName(split[0])){
					//Es una unica variable
					op1 = new Operando(new Variable(split[0]));
				}else if(isMatrizVariables(split[0])){
					//Es una matrizde variables
					try {
						op1 = new Operando(new MatrizExpresion(split[0]));
					} catch (MatrizException e) {}
				}
				Operacion op = new Operacion(new Operando[]{
						op1,
						new Operando(spliter(split[2]).length > 1 || hasMoreLevels(split[2])? getOperacion(split[2],ops).getId() : split[2])},
						Operador.ASIGNACION);
				//Hemos creado la operacion ahora la insertamos y le asignamos un id
				op.setId(ops.insert(op));
				return op;
			}else{
				int lvl = 0;
				boolean salir = false;
				while(!salir){
					if(lvl == 0){
						//Separar en sumas
						split = spliterEspecial(txt, new char[]{'+','-'});
						if(split.length == 1){
							lvl++;
						}else if(split.length == 2){
							if(split[0].equals("-")){
								Operacion op = new Operacion(new Operando[]{new Operando("-1"),new Operando(split[1])},Operador.MULTIPLICACION);
								op.setId(ops.insert(op));
								return op;
							}else if(split[0].equals("+")){
								Operacion op = new Operacion(new Operando[]{new Operando(split[1])},Operador.EVALUACION);
								op.setId(ops.insert(op));
								return op;
							}else{
								throw new InterpreteException("Not valid sumando");
							}
						}else{
							System.out.println("Sumando");
							Operacion op = null;
							String op1 = "";
							String op2 = "";
							int suma = Operador.SUMA;
							boolean encontrado = false;
							for (int i = 0; i < split.length && !encontrado; i++) {
								if(split[i].equals("+")){
									encontrado = true;
									for (int j = i+1; j < split.length; j++) {
										op2 = op2 + split[j];
									}
								}else if(split[i].equals("-")){
									encontrado = true;
									for (int j = i+1; j < split.length; j++) {
										op2 = op2 + split[j];
									}
									suma = Operador.RESTA;
								}else{
									op1 = op1 + split[i];
								}
							}
							System.out.println(op1 + " .... " + op2);
							System.out.println(spliter(op1).length);
							op = new Operacion(new Operando[]{
									new Operando(spliter(op1).length > 1 || hasMoreLevels(op1) ||validVariableName(op1)  ? getOperacion(op1, ops) : op1),
									new Operando(spliter(op2).length > 1 || hasMoreLevels(op2) ||validVariableName(op2)  ? getOperacion(op2, ops) : op2)
							},suma);
							op.setId(ops.insert(op));
							return op;
						}
					}else if(lvl == 1){
						split = spliterEspecial(txt, new char[]{'*','/'});
						if(split.length < 3){
							lvl++;
						}else{
							System.out.println("Multiplicando");
							Operacion op = null;
							String op1 = "";
							String op2 = "";
							int suma = Operador.MULTIPLICACION;
							boolean encontrado = false;
							for (int i = 0; i < split.length && !encontrado; i++) {
								if(split[i].equals("*")){
									encontrado = true;
									for (int j = i+1; j < split.length; j++) {
										op2 = op2 + split[j];
									}
								}else if(split[i].equals("/")){
									encontrado = true;
									for (int j = i+1; j < split.length; j++) {
										op2 = op2 + split[j];
									}
									suma = Operador.DIVISION;
								}else if(split[i].equals(".*")){
									encontrado = true;
									for (int j = i+1; j < split.length; j++) {
										op2 = op2 + split[j];
									}
									suma = Operador.MULTIPLICACION_P2P;
								}else if(split[i].equals("./")){
									encontrado = true;
									for (int j = i+1; j < split.length; j++) {
										op2 = op2 + split[j];
									}
									suma = Operador.DIVISION_P2P;
								}else{
									op1 = op1 + split[i];
								}
							}
							op = new Operacion(new Operando[]{
									new Operando(spliter(op1).length > 1 || hasMoreLevels(op1)||validVariableName(op1) ? getOperacion(op1, ops) : op1),
									new Operando(spliter(op2).length > 1 || hasMoreLevels(op2) ||validVariableName(op2) ? getOperacion(op2, ops) : op2)
							},suma);
							op.setId(ops.insert(op));
							return op;
						}
					}else if(lvl == 2){
						split = spliterEspecial(txt, new char[]{'%','^'});
						if(split.length == 1){
							lvl++;
						}else if(split.length >= 3){
							System.out.println("Elevando o modulo");
							if(split[1].equals("%")){
								
							}else if(split[1].equals("^")){
								
							}else{
								throw new InterpreteException("Not valid argument");
							}
						}else{
							throw new InterpreteException("Not enough arguments for % or ^");
						}
					}else if(lvl == 3){
						split = spliterEspecial(txt, new char[]{'\''});
						lvl++;
					}else if(lvl == 4){
						//Funciones
						if(isFuncion(txt)){
							System.out.println("Funcion");
							Operacion op = llamarOperacion(txt,ops);
							op.setId(ops.insert(op));
							return op;
						}else{
							lvl++;
						}
					}else if(lvl == 5){
						//Variables
						if(validVariableName(txt)){
							System.out.println("Variable");
							//Creamos una funcion vacia que solo usaremos para recuperar un valor
							Operacion op = new Operacion(new Operando[]{new Operando(new Variable(txt))}, Operador.EVALUACION);
							op.setId(ops.insert(op));
							return op;
						}else{
							lvl++;
						}
					}else if(lvl == 6){
						//Operaciones dentro de parentesis etc
						String txt2 = innerExpresion(txt);
						if(txt2 == null){
							lvl++;
						}else{
							System.out.println("Parentesis");
							return getOperacion(txt2, ops);
						}
						
					}else{
						System.out.println("Es un numero");
						Operacion op = new Operacion(new Operando[]{new Operando(txt)}, Operador.EVALUACION);
						op.setId(ops.insert(op));
						return op;
					}
				}
				
			}
		}
		return null;
	}
	public Operacion llamarOperacion(String txt,ListaOperaciones op) throws InterpreteException{
		String name = getFunctionName(txt);
		if(isFuncion(txt)){
			String[] args = getArgs(txt);
			Operando[] operandos = new Operando[args.length+1];
			operandos[0] = new Operando(new Funcion(name));
			for (int i = 0; i < args.length; i++) {
				operandos[i+1] = new Operando(spliter(args[i]).length > 1 || hasMoreLevels(args[i])? getOperacion(args[i], op) : args[i]);
			}
			Operacion operacion = new Operacion(operandos, Operador.EJECUTAR_FUNCION);
			return operacion;
		}else{
			throw new InterpreteException("Not valid Function");
		}
	}
	public boolean isMatrizVariables(String txt){
		if(txt.charAt(0) == '[' && txt.charAt(txt.length() -1) == ']'){
			String[] split = spliterEspecial(txt.substring(1,txt.length()-2), new char[]{','});
			if(split.length == 1){
				return false;
			}
			for (int i = 0; i < split.length; i++) {
				if(!split[i].equals(",") && !split[i].equals("") && !validVariableName(split[i])){
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
	}
	public static boolean isFuncion(String txt){
		int inicio = -1;
		boolean encontrado = false;
		String name = "";
		for (int i = 0; i < txt.length() && !encontrado; i++) {
			if(txt.charAt(i) == '('){
				inicio = i;
				encontrado = true;
				if(i>0){
					name = txt.substring(0, inicio);
				}
			}
		}
		if(validVariableName(name)){
			return true;
		}else{
			return false;
		}
	}
	public static String getFunctionName(String txt){
		int inicio = -1;
		boolean encontrado = false;
		for (int i = 0; i < txt.length() && !encontrado; i++) {
			if(txt.charAt(i) == '('){
				inicio = i-1;
				encontrado = true;
			}
		}
		if(inicio <0){
			return "";
		}
		return txt.substring(0, inicio+1);
	}
	/**
	 * Obtiene los argumentos de un texto si este es una funcion
	 * @param txt
	 * @return
	 */
	public static String[] getArgs(String txt){
		int level = 0;
		ArrayList<String> args = new ArrayList<String>();
		int lastPos = 0;
		//Cada uno de estos separadores corresponde a una operacion matematica o expresion para acceder a funciones matrices o objetos
		boolean encontrado = false;
		for (int i = 0; i <txt.length(); i++) {
			encontrado = false;
			//Buscar separadores en cada caracter
			for (int j = 0; j < SEPRADOR_NIVEL.length && !encontrado; j++) {
				if(txt.charAt(i) == SEPRADOR_NIVEL[j]){
					encontrado = true;
					if(j%2 == 1){
						level--;
						if(level == 0){
							args.add(txt.substring(lastPos, i));
						}
					}else{
						level++;
						if(level == 1){
							lastPos = i+1;
						}
					}
				}
			}
			if(!encontrado && level == 1){
				if(txt.charAt(i) == ','){
					args.add(txt.substring(lastPos, i));
					lastPos=i+1;
				}
			}
		}
		return args.toArray(new String[args.size()]);
	}
	/**
	 * Convierte una linea de codigo en una expresion interpretada
	 * @param txt
	 * @return
	 */
	public ExpresionInterpretada compilar(String txt){
		String[] split = spliter(txt);
		ListaOperaciones lo = new ListaOperaciones();
		ExpresionInterpretada ei = new ExpresionInterpretada(txt);
		ei.setListaOperaciones(lo);
		int tipo = setTipo(txt);
		switch (tipo) {
		case FINAL:
			break;
		case FUNCION:
			break;
		case INICIO_BUCLE:
			break;
		default:
			break;
		}
		return ei;
	}
	/**
	 * Aqui esta toda la miga del pan, analiza la operacion y en funcion del tipo y los parametros que posea la ejecuta
	 * @param op
	 * @param lo
	 * @return
	 * @throws InterpreteException
	 */
	private String realizarOperacion(Operacion op,ListaOperaciones lo) throws InterpreteException{
		String ret = "";
		Operando[] opd = op.getOperandos();
		int operador = op.getOperador();
		Operando[] aux = null;
		switch (operador) {
		case Operador.ASIGNACION:
			//Operacion de asignacion
			if(opd[0].getTipo() == Operando.VARIABLE){
				String name = (String)((Variable)opd[0].getValor()).getName();
				if(validVariableName(name) && opd.length == 2){
					aux = new Operando[opd.length];
					aux[0] = opd[0];
					for (int i = 1; i < aux.length; i++) {
						aux[i] = new Operando(asignarValores(opd[i], this.contexto, lo));
					}
					Variable var = new Variable(name);
					Object valor = aux[1].getValor();
					try {
						var.setValor(valor);
						op.getResultado().setValor(valor);
						this.contexto.setVariableValue(name, valor);
					} catch (VariableException e) {
						throw new InterpreteException(e.getMessage());
					}
					this.contexto.addVariable(var);
					ret = ret + name + "\t=\t" + valor;
				}else{
					throw new InterpreteException("Not valid variable name");
				}
			}else{
				throw new InterpreteException("Variable name not found");
			}
			break;
		case Operador.SUMA:
			//Operacion suma
			opd = op.getOperandos();
			aux = new Operando[opd.length];
			for (int i = 0; i < opd.length; i++) {
				//Asignamos de verdad los valores para no tener que castear manualmente
				aux[i] = new Operando(asignarValores(opd[i], this.contexto, lo));
			}
			try {
				op.getResultado().setValor(Operador.suma(aux[0], aux[1]));
				if(op.getId() == lo.size()-1){
					ret = ret + op.getResultado().getValor()+"\n";
				}
			} catch (Exception e) {
				throw new InterpreteException(e.getMessage());
			}
			break;
		case Operador.RESTA:
			//Operacion resta
			opd = op.getOperandos();
			aux = new Operando[opd.length];
			for (int i = 0; i < opd.length; i++) {
				//Asignamos de verdad los valores para no tener que castear manualmente
				aux[i] = new Operando(asignarValores(opd[i], this.contexto, lo));
			}
			try {
				op.getResultado().setValor(Operador.resta(aux[0], aux[1]));
				if(op.getId() == lo.size()-1){
					ret = ret + op.getResultado().getValor()+"\n";
				}
			} catch (Exception e) {
				throw new InterpreteException(e.getMessage());
			}
			break;
		case Operador.MULTIPLICACION:
			//Operacion multiplicacion
			opd = op.getOperandos();
			aux = new Operando[opd.length];
			for (int i = 0; i < opd.length; i++) {
				//Asignamos de verdad los valores para no tener que castear manualmente
				aux[i] = new Operando(asignarValores(opd[i], this.contexto, lo));
			}
			try {
				op.getResultado().setValor(Operador.multiplicacion(aux[0], aux[1]));
				if(op.getId() == lo.size()-1){
					ret = ret + op.getResultado().getValor()+"\n";
				}
			} catch (Exception e) {
				throw new InterpreteException(e.getMessage());
			}
			break;
		case Operador.EJECUTAR_FUNCION:
			opd = op.getOperandos();
			aux = new Operando[opd.length];
			aux[0] = opd[0];
			for (int i = 1; i < opd.length; i++) {
				//Asignamos de verdad los valores para no tener que castear manualmente
				System.out.println(opd[i]);
				aux[i] = new Operando(asignarValores(opd[i], this.contexto, lo));
				System.out.println(aux[i]);
			}
			try {
				op.getResultado().setValor(Operador.ejecutarFuncion(aux, this.contexto));
				if(op.getId() == lo.size()-1){
					ret = ret + op.getResultado().getValor()+"\n";
				}
			} catch (Exception e) {
				throw new InterpreteException(e.getMessage());
			}
			break;
		case Operador.EVALUACION:
			opd = op.getOperandos();
			aux = new Operando[opd.length];
			for (int i = 0; i < opd.length; i++) {
				//Asignamos de verdad los valores para no tener que castear manualmente
				aux[i] = new Operando(asignarValores(opd[i], this.contexto, lo));
			}
			try {
				op.getResultado().setValor(aux[0].getValor());
				if(op.getId() == lo.size()-1){
					ret = ret + op.getResultado().getValor()+"\n";
				}
			} catch (Exception e) {
				throw new InterpreteException(e.getMessage());
			}
			break;
		case Operador.CIERRE_LINEA:
			//Operacion multiplicacion
			ret = "";
			break;
		default:
			break;
		}
		return ret;
	}
	/**
	 * Interpreta una Expresion ya compilada y devuelve la respuesta para la consola
	 * Este metodo desempaqueta todas las operaciones y las ejecuta. Podra ejecutar tareas en paralelo en un futuro
	 * @param ei
	 * @return
	 * @throws InterpreteException 
	 */
	public String interpretarExpresion(ExpresionInterpretada ei) throws InterpreteException{
		String ret = "";
		int tipo = ei.getTipo();
		ListaOperaciones lo = ei.getListaOperaciones();
		int size = lo.size();
		Operacion op = null;
		boolean returnNull = false;
		for (int i = 0; i < size; i++) {
			op = lo.get(i);
			ret = ret + realizarOperacion(op, lo);
			if(op.getOperador() == Operador.CIERRE_LINEA){
				//Si la ultima operacion es el cierre de linea entonces no escribimos nada por pantalla
				returnNull = true;
			}
			
		}
		if(returnNull){
			ret = "";
		}
		return ret;
	}
	public static int[][] findMatriz(String txt) throws InterpreteException {
		ArrayList<Integer> inicioM = null;
		ArrayList<Integer> finalM = null;
		for (int i = 0; i < txt.length(); i++) {
			inicioM = new ArrayList<Integer>();
			finalM = new ArrayList<Integer>();
			String token = MathToken.MATRIZ_INICIO;
			boolean analiz = true;
			for (int j = 0; j < token.length() && analiz; j++) {
				if (txt.charAt(i + j) != token.charAt(j)) {
					analiz = false;
				}
			}
			if (analiz) {// Se ha encontrado el token
				inicioM.add(new Integer(i));
			}
			analiz = true;
			token = MathToken.MATRIZ_FINAL;
			for (int j = 0; j < token.length(); j++) {
				if (txt.charAt(i + j) != token.charAt(j)) {
					analiz = false;
				}
			}
			if (analiz) {// Se ha encontrado el token
				finalM.add(new Integer(i));
			}
		}
		int[][] pos;
		// En este punto sabiendo la longitud de inicioM y la de finalM
		// podemos saber el numero de matrices que hay
		if (inicioM.size() == finalM.size()) {
			pos = new int[2][inicioM.size()];
			for (int j = 0; j < pos.length; j++) {
				pos[0][j] = inicioM.get(j);
			}
			for (int j = 0; j < pos.length; j++) {
				pos[1][j] = finalM.get(j);
			}
			return pos;
		} else {
			throw new InterpreteException("");
		}
	}
	public Funcion getFuncionByName(String name) throws InterpreteException{
		return contexto.findFuncionByName(name);
	}
	public ContextoMatematico getMathContext(){
		return this.contexto;
	}
	public void setMathContext(ContextoMatematico mc){
		this.contexto = mc;
	}
	/**
	 * Separa la cadena de linea de codigo en caracteres especiales para una mayor sencillez de interpretacion
	 * @param txt
	 * @return
	 */
	public static String[] spliterEspecial(String txt,char[] separadores){
		txt.replaceAll("\n", "");
		ArrayList<String> ret = new ArrayList<String>();
		int lastPos = 0;
		int lvl = 0;
		//Cada uno de estos separadores corresponde a una operacion matematica o expresion para acceder a funciones matrices o objetos
		boolean encontrado = false;
		for (int i = 0; i <txt.length(); i++) {
			encontrado = false;
			//Comprobamos primero si la letra es un separador de nivel, para lo cual si lo es subimos un nivel
			for (int j = 0; j < SEPRADOR_NIVEL.length; j++) {
				if(txt.charAt(i) == SEPRADOR_NIVEL[j]){
					//Es un separador de nivel
					if(j%2==1){
						lvl--;
					}else{
						lvl++;
					}
					encontrado = true;
				}
			}
			//Buscar separadores en cada caracter
			for (int j = 0; j < separadores.length && !encontrado && lvl == 0; j++) {
				if(txt.charAt(i) == separadores[j]){
					//El anterior era un separador
					if(lastPos >= i-1){
						//Es un punto
						encontrado = true;
						if(lastPos != i){
							ret.add(txt.substring(lastPos, i));
						}
						ret.add(txt.substring(i, i+1));
						lastPos = i+1;
					}else if(i-1 >0 && lastPos >= 0){
						if(txt.charAt(i-1) == '.'){
							ret.add(txt.substring(lastPos, i-1));
							ret.add(txt.substring(i-1, i+1));
						}else{
							ret.add(txt.substring(lastPos, i));
							ret.add(txt.substring(i, i+1));
						}
						encontrado = true;
						lastPos = i+1;
					}
				}
			}
		}
		ret.add(txt.substring(lastPos, txt.length()));
		return ret.toArray(new String[ret.size()]);
	}
	/**
	 * Separa la cadena de linea de codigo en caracteres especiales para una mayor sencillez de interpretacion
	 * @param txt
	 * @return
	 */
	public static String[] spliter(String txt){
		txt.replaceAll("\n", "");
		ArrayList<String> ret = new ArrayList<String>();
		int lastPos = 0;
		int lvl = 0;
		//Cada uno de estos separadores corresponde a una operacion matematica o expresion para acceder a funciones matrices o objetos
		boolean encontrado = false;
		for (int i = 0; i <txt.length(); i++) {
			encontrado = false;
			//Comprobamos primero si la letra es un separador de nivel, para lo cual si lo es subimos un nivel
			for (int j = 0; j < SEPRADOR_NIVEL.length; j++) {
				if(txt.charAt(i) == SEPRADOR_NIVEL[j]){
					//Es un separador de nivel
					if(j%2==1){
						lvl--;
					}else{
						lvl++;
					}
					encontrado = true;
				}
			}
			//Buscar separadores en cada caracter
			for (int j = 0; j < separadores.length && !encontrado && lvl == 0; j++) {
				if(txt.charAt(i) == separadores[j]){
					//El anterior era un separador
					if(lastPos >= i-1){
						//Es un punto
						encontrado = true;
						if(lastPos != i){
							ret.add(txt.substring(lastPos, i));
						}
						ret.add(txt.substring(i, i+1));
						lastPos = i+1;
					}else if(i-1 >0 && lastPos >= 0){
						if(txt.charAt(i-1) == '.'){
							ret.add(txt.substring(lastPos, i-1));
							ret.add(txt.substring(i-1, i+1));
						}else{
							ret.add(txt.substring(lastPos, i));
							ret.add(txt.substring(i, i+1));
						}
						encontrado = true;
						lastPos = i+1;
					}
				}
			}
		}
		ret.add(txt.substring(lastPos, txt.length()));
		return ret.toArray(new String[ret.size()]);
	}
	/**
	 * Analiza internamente la linea para obtener el tipo
	 */
	private int setTipo(String linea){
		int tipo = -1;
		String[] split = spliter(linea);//Posible optimizacion ya que no hace falta hacer un split completo
		if(split[0].equals("function")){
			tipo = FUNCION;
			//Es un script funcion
		}else if(split[0].equals("thread")){
			tipo = THREAD;
			//Es un script que se ejecuta en paralelo
		}else if(split[0].equals("wait")){
			tipo = JOIN_THREADS;
			//Es un script que se ejecuta en paralelo
		}else{
			if(validVariableName(split[0]) && split[1].equals(MathToken.ASIGNACION)){
				//El primero es una variable a la que se iguala algo (x=5)
				tipo = ASIGNACION;
			}else if(split[0].equals("for")){
				tipo = INICIO_BUCLE;
				//Un bucle for (for i=1:10)
			}else if(split[0].equals("while")){
				tipo = INICIO_BUCLE;
				//Un bucle for (for i=1:10)
			}else if(split[0].equals("if")){
				tipo = INICIO_CONDICION;
				//condicion if(if x==5)
			}else if(split[0].equals("elsif")){
				tipo = INICIO_SUBCONDICION;
				//condicion if(if x==5)
			}else if(split[0].equals("end") && split.length == 1){
				tipo = FINAL;
				//baja un nivel (end)
			}
		}
		return tipo;
	}
	/**
	 * Devuelve true si el nombre de variable es valido: solo letras y numeros y debe empezar por letras
	 * @return
	 */
	public static boolean validVariableName(String txt){
		return txt.matches("^[a-zA-Z][a-zA-Z0-9]*$") && !txt.equals("i") && !txt.equals("j");
	}
	public static boolean hasMoreLevels(String txt){
		//Cada uno de estos separadores corresponde a una operacion matematica o expresion para acceder a funciones matrices o objetos
		for (int i = 0; i <txt.length(); i++) {
			//Buscar separadores en cada caracter
			//No tenemos en cuenta las matrices solo parentesis y corchetes
			for (int j = 2; j < SEPRADOR_NIVEL.length; j++) {
				if(txt.charAt(i) == SEPRADOR_NIVEL[j]){
					return true;
				}
			}
		}
		return false;
	}
	public static int isOperador(String txt){
		int pos = 0;
		if(txt.charAt(0) == '.'){
			pos = 1;
		}
		//El ultimo es un separador(coma) para funciones etc
		for (int i = 0; i < separadores.length-1; i++) {
			if(txt.charAt(pos) == separadores[i]){
				return i;
			}
		}
		return -1;
	}
	public static String innerExpresion(String txt){
		int level = 0;
		int inicio = -1;
		int fin = -1;
		//Cada uno de estos separadores corresponde a una operacion matematica o expresion para acceder a funciones matrices o objetos
		boolean encontrado = false;
		for (int i = 0; i <txt.length() && (inicio == -1 || fin == -1); i++) {
			encontrado = false;
			//Buscar separadores en cada caracter
			for (int j = 0; j < SEPRADOR_NIVEL.length && !encontrado; j++) {
				if(txt.charAt(i) == SEPRADOR_NIVEL[j]){
					encontrado = true;
					if(j%2 == 1){
						level--;
						if(level == 0){
							fin = i;
						}
					}else{
						level++;
						if(level == 1){
							inicio = i;
						}
					}
				}
			}
		}
		if(inicio == -1 || fin == -1){
			return null;
		}else{
			return txt.substring(inicio+1, fin);
		}
	}
	/**
	 * Devuelve el verdadero valor para este operando
	 * @param op
	 * @param mc
	 * @param lo
	 * @return
	 * @throws InterpreteException
	 */
	private Object asignarValores(Operando op,ContextoMatematico mc,ListaOperaciones lo) throws InterpreteException{
		switch (op.getTipo()) {
		case Operando.VARIABLE:
			System.out.println(op);
			return mc.findVariableByName(((Variable)op.getValor()).getName()).getValue();
		case Operando.RESULTADO:
			Object valor = lo.get(((Integer)op.getValor()).intValue()).getResultado().getValor();
			return valor;
		case Operando.VALOR_NUMERICO:
			return op.getValor();
		case Operando.MATRIZ:
			return op.getValor();
		case Operando.FUNCION:
			return op;
		default:
			return op.getValor();
		}
	}
}
