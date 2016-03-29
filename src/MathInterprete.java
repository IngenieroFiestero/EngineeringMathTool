import java.util.ArrayList;

public class MathInterprete {
	/**
	 * Funcion para crear nueva funcion Expresion para resolverla ASIGNACION
	 * para asignar a una variable un valor EVALUACION para evaluar comandos
	 * rapidos
	 * 
	 * @author admin
	 *
	 */
	public static enum TIPO {
		FUNCION, EXPRESION, ASIGNACION, EVALUACION
	};

	private MathContext contexto;

	// Si no se ha usado nunca
	public MathInterprete() {
		contexto = new MathContext();
	}

	// Si ya se ha usado y hay un contexto se puede cargar
	public MathInterprete(MathContext contexto) {
		this.contexto = contexto;
	}

	public String evaluar(String txt) {
		TIPO tipo = analizar(txt);
		switch (tipo) {
		case FUNCION:
			break;
		case ASIGNACION:
			break;
		case EVALUACION:
			return evaluacion(txt);
		case EXPRESION:
			break;
		default:
			break;
		}
		return "";// Borrar despues
	}

	private String evaluacion(String txt) {
		try {
			int[][] pos = findMatriz(txt);
			for (int i = 0; i < pos.length; i++) {
				Matriz mat = new Matriz(txt.substring(pos[0][i], pos[1][i]));
				System.out.println(mat);
			}
		} catch (InterpreteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
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

	/**
	 * Analiza la cadena para saber que hacer con ella. Si es una funcion, si es
	 * una expresion para resolver, si asigna a una variable cierto valor, si
	 * evalua la expresion
	 * 
	 * @param txt
	 *            Cadena a analizar
	 * @return TIPO
	 */
	public TIPO analizar(String txt) {
		String[] tokens = txt.split(" ");
		if (tokens[0].equals(MathToken.FUNCION)) {
			return TIPO.FUNCION;
		} else if (tokens[0].equals(MathToken.ASIGNACION)) {
			return TIPO.ASIGNACION;
		} else if (txt.contains(MathToken.ASIGNACION)) {
			return TIPO.EXPRESION;
		} else {
			return TIPO.EVALUACION;
		}
	}

}
