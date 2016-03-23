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
