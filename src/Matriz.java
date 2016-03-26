import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class Matriz implements InterfazMatriz, Cloneable {
	public static final int TIPO = 1;
	@SuppressWarnings("rawtypes")
	ValorNumerico[][] matriz;

	public Matriz(int[] length) {
		matriz = new ValorNumerico[length[0]][length[1]];
		init();
	}
	public boolean equals(Matriz mat) throws ValorNumericoException{
		if(mat.dimensions()[0] !=matriz.length && mat.dimensions()[1] !=matriz[0].length){
			return false;
		}else{
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz[0].length; j++) {
					int[] pos = new int[]{i,j};
					if(!ValorNumerico.equals((ValorNumerico)mat.get(pos), (ValorNumerico)matriz[i][j])){
						return false;
					}
				}
			}
			return true;
		}
	}
	//Implementacion de clone
	@SuppressWarnings("rawtypes")
	public Object clone(){
		Matriz obj = new Matriz(new int[]{this.matriz.length,this.matriz[0].length});
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				obj.set(new int[]{i,j},(ValorNumerico) this.matriz[i][j].clone());
			}
		}
		return (Object)obj;
	}

	public void set(int[] i, @SuppressWarnings("rawtypes") ValorNumerico val) {
		matriz[i[0]][i[1]] = val;
	}

	@SuppressWarnings("rawtypes")
	public ValorNumerico get(int[] i) {
		return matriz[i[0]][i[1]];
	}

	public int[] dimensions() {
		return new int[] { matriz.length, matriz[0].length };
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void init() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = new ValorNumerico(new BigInteger("0"));
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
				matriz[i][i] = new ValorNumerico(new BigInteger("1"));
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
				ret = ret + " " + matriz[i][j].getValor();
			}
			ret = ret + "\n";
		}
		return ret;
	}

	public static Matriz sumar(Matriz mat1, Matriz mat2) throws MatrizException, ValorNumericoException {
		int[] dim1 = mat1.dimensions();
		int[] dim2 = mat2.dimensions();
		Matriz ret = new Matriz(dim1);
		if (Arrays.equals(dim1, dim2)) {
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					ret.set(new int[] { i, j },
							ValorNumerico.suma(mat1.get(new int[] { i, j }), mat2.get(new int[] { i, j })));
				}
			}
			return ret;
		} else {
			throw new MatrizException(MatrizException.generateErrorDimensions(dim1,dim2));
		}
	}
	public static Matriz invertir(Matriz mat1) throws MatrizException, ValorNumericoException{
		int[] dim1 = mat1.dimensions();
		Matriz ret = new Matriz(dim1);
		Matriz aux = (Matriz) mat1.clone();
		if (dim1[0] == 1 && dim1[1] == 1) {
			//Si es solo un elemento se calcula 1/elemento
			ret.set(new int[]{0,0},ValorNumerico.dividir(new ValorNumerico(new BigInteger("1")),mat1.get(new int[]{0,0})));
			return ret;
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
	}
	public static Matriz multiplicarP2P(Matriz mat1, Matriz mat2) throws MatrizException, ValorNumericoException {
		int[] dim1 = mat1.dimensions();
		int[] dim2 = mat2.dimensions();
		Matriz ret = new Matriz(dim1);
		if (Arrays.equals(dim1, dim2)) {
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					ret.set(new int[] { i, j },
							ValorNumerico.multiplicar(mat1.get(new int[] { i, j }), mat2.get(new int[] { i, j })));
				}
			}
			return ret;
		} else {
			throw new MatrizException(MatrizException.generateErrorDimensions(dim1,dim2));
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static Matriz multiplicar(Matriz mat1, Matriz mat2) throws MatrizException, ValorNumericoException {
		int[] dim1 = mat1.dimensions();
		int[] dim2 = mat2.dimensions();
		if (dim1[1] == dim2[0]) {
			Matriz ret = new Matriz(dim1);
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					ValorNumerico val = new ValorNumerico(ValorNumerico.TIPO_ENTERO);
					for (int k = 0; k < dim1[1]; k++) {
						val = ValorNumerico.suma(val,ValorNumerico.multiplicar(mat1.get(new int[]{i,k}), mat2.get(new int[]{k,j})));
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
					ret.set(new int[]{i,j},ValorNumerico.multiplicar(mat1.get(new int[]{0,0}), mat2.get(new int[]{i,j})));
				}
			}
			return ret;
		}else if(dim2[0] == 1 && dim2[1] == 1){
			//Caso de una sola dimension
			Matriz ret = new Matriz(dim1);
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					ret.set(new int[]{i,j},ValorNumerico.multiplicar(mat1.get(new int[]{i,j}), mat2.get(new int[]{0,0})));
				}
			}
			return ret;
		}else {
			throw new MatrizException(MatrizException.generateErrorDimensions(dim1,dim2));
		}
	}

}
