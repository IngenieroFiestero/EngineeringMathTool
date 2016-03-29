import java.util.ArrayList;
import java.util.Arrays;

public class Matriz implements  Cloneable {
	public static final int TIPO = 1;
	ValorNumerico[][] matriz;

	public Matriz(int[] length) {
		matriz = new ValorNumerico[length[0]][length[1]];
		init();
	}
	public Matriz(String txt) {
		int columnas = 0;
		int filas = 0;
		int lastPos = 0;//Indica la posicion del anterior separador
		ArrayList<String> valores = new ArrayList<String>();
		for (int i = 0; i < txt.length(); i++) {
			if(txt.charAt(i) == MathToken.MATRIX_COLUMNA_SEPARATOR_1.charAt(0)){
				valores.add(txt.substring(lastPos+1, i-1));
				lastPos = i;
				columnas++;
			}else if(txt.charAt(i) == MathToken.MATRIX_FILA_SEPARATOR.charAt(0)){
				valores.add(txt.substring(lastPos+1, i-1));
				lastPos = i;
				filas++;
			}
		}
		matriz = new ValorNumerico[filas][columnas];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = new ValorNumerico(valores.get(i+j));
			}
		}
	}
	public boolean equals(Matriz mat) throws ValorNumericoException{
		if(mat.dimensions()[0] !=matriz.length && mat.dimensions()[1] !=matriz[0].length){
			return false;
		}else{
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz[0].length; j++) {
					int[] pos = new int[]{i,j};
					if(!mat.get(pos).equals(matriz[i][j])){
						return false;
					}
				}
			}
			return true;
		}
	}
	//Implementacion de clone
	public Object clone(){
		Matriz obj = new Matriz(new int[]{this.matriz.length,this.matriz[0].length});
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				obj.set(new int[]{i,j},(ValorNumerico) this.matriz[i][j].clone());
			}
		}
		return (Object)obj;
	}

	public void set(int[] i, ValorNumerico val) {
		matriz[i[0]][i[1]] = val;
	}

	public ValorNumerico get(int[] i) {
		return matriz[i[0]][i[1]];
	}

	public int[] dimensions() {
		return new int[] { matriz.length, matriz[0].length };
	}


	private void init() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = new ValorNumerico(0,0);
			}
		}
	}

	public void setNull() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j].setNull();
			}
		}
	}
	/**
	 * Matriz Identidad
	 * @throws MatrizException
	 */
	public void setIdentidad()throws MatrizException{
		if(matriz.length == matriz[0].length){
			for (int i = 0; i < matriz.length; i++) {
				matriz[i][i] = new ValorNumerico(1);
			}
		}else{
			throw new MatrizException(MatrizException.generateErrorNotSquareMatrix(this.dimensions()));
		}
	}

	public void setRand() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j].setRand();
			}
		}
	}

	public String toString() {
		String ret = "";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				ret = ret + " " + matriz[i][j];
			}
			ret = ret + "\n";
		}
		return ret;
	}
	public Matriz sumar(Matriz mat) throws MatrizException, ValorNumericoException {
		int[] dim1 = this.dimensions();
		int[] dim2 = mat.dimensions();
		Matriz ret = new Matriz(dim1);
		if (Arrays.equals(dim1, dim2)) {
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					ret.set(new int[] { i, j },matriz[i][j].add(mat.get(new int[] { i, j })));
				}
			}
			return ret;
		} else {
			throw new MatrizException(MatrizException.generateErrorDimensions(dim1,dim2));
		}
	}
	/*
	public Matriz invertir() throws MatrizException, ValorNumericoException{
		int[] dim1 = this.dimensions();
		Matriz ret = new Matriz(dim1);
		if (dim1[0] == 1 && dim1[1] == 1) {
			//Si es solo un elemento se calcula 1/elemento
			this.matriz[0][0] = new ValorNumerico(1,0).divide(matriz[0][0]);
			return this;
		}else if(dim1[0] == dim1[1]){
			ret.setIdentidad();
			//Si es cuadrada y mas de dimension 1,1
			ValorNumerico val = (ValorNumerico)aux.get(new int[]{0,0}).clone();
			if(val.equals(new ValorNumerico(new BigInteger("1")))){
			}else{
				aux.set(new int[]{0,0}, new ValorNumerico(new BigInteger("1")));
				ret.set(new int[]{0,0},ValorNumerico.dividir((ValorNumerico) ret.get(new int[]{0,0}).clone(), val));
				for (int j = 1; j < dim1[1]; j++) {
					int[] pos = new int[]{0, j};
					aux.set(pos,ValorNumerico.dividir((ValorNumerico) aux.get(pos).clone(), val));
					ret.set(pos,ValorNumerico.dividir((ValorNumerico) ret.get(pos).clone(), val));
				}
			}
			//Operar
			//i comulnas,j filas,k columnas
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					//Se empieza primero por los elementos de las columnas
					ValorNumerico multi = ValorNumerico.dividir((ValorNumerico)aux.get(new int[]{j,i}).clone(), (ValorNumerico)aux.get(new int[]{i,i}).clone());
					if(i!= j && !multi.equals(new ValorNumerico(new BigInteger("0")))){
						//Si un elemento que no es de la diagonal principal es distinto de 0 se resta a toda la fila
						for (int k = 0; k < dim1[1]; k++) {
							//System.out.println(aux.get(new int[]{j,k}).getValor());
							//System.out.println(ret.get(new int[]{j,k}).getValor());
							aux.set(new int[]{j,k}, ValorNumerico.resta(aux.get(new int[]{j,k}), 
									ValorNumerico.multiplicar(multi, aux.get(new int[]{i,k}))));
							ret.set(new int[]{j,k}, ValorNumerico.resta(ret.get(new int[]{j,k}), 
									ValorNumerico.multiplicar(multi, ret.get(new int[]{i,k}))));
						}
					}
				}
			}
			for (int i = 1; i < dim1[1]; i++) {
				ValorNumerico val2 = (ValorNumerico)aux.get(new int[]{i,i}).clone();
				if(!val2.equals(new ValorNumerico(new BigDecimal("1")))){
					for (int j = 0; j < dim1[0]; j++) {
						int[] pos = new int[]{i, j};
						aux.set(pos,ValorNumerico.dividir(aux.get(pos), val2));
						ret.set(pos,ValorNumerico.dividir(ret.get(pos), val2));
					}
				}
			}
			return ret;
		} else {
			throw new MatrizException(MatrizException.generateErrorDimensions(new int[]{dim1[0]},new int[]{dim1[1]}));
		}
	}*/
	public Matriz multiplicarP2P(Matriz mat) throws MatrizException, ValorNumericoException {
		int[] dim1 = this.dimensions();
		int[] dim2 = mat.dimensions();
		Matriz ret = new Matriz(dim1);
		if (Arrays.equals(dim1, dim2)) {
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					ret.set(new int[] { i, j },matriz[i][j].multiply(mat.get(new int[] { i, j })));
				}
			}
			return ret;
		} else {
			throw new MatrizException(MatrizException.generateErrorDimensions(dim1,dim2));
		}
	}
	
	public Matriz multiplicar(Matriz mat) throws MatrizException, ValorNumericoException {
		int[] dim1 = this.dimensions();
		int[] dim2 = mat.dimensions();
		if (dim1[1] == dim2[0]) {
			Matriz ret = new Matriz(dim1);
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					ValorNumerico val = new ValorNumerico(0);
					for (int k = 0; k < dim1[1]; k++) {
						ret.set(new int[] { i, j },mat.get(new int[] { i, j }).add(matriz[i][k].multiply(mat.get(new int[] { k, j }))));
					}
					ret.set(new int[]{i,j},val);
				}
			}
			return ret;
		}else if(dim1[0] == 1 && dim1[1] == 1){
			//Caso de una sola dimension
			Matriz ret = new Matriz(dim2);
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					ret.set(new int[]{i,j},matriz[0][0].multiply(mat.get(new int[]{i,j})));
				}
			}
			return ret;
		}else if(dim2[0] == 1 && dim2[1] == 1){
			//Caso de una sola dimension
			Matriz ret = new Matriz(dim1);
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					ret.set(new int[]{i,j},matriz[i][j].multiply(mat.get(new int[]{0,0})));
				}
			}
			return ret;
		}else {
			throw new MatrizException(MatrizException.generateErrorDimensions(dim1,dim2));
		}
	}

}
