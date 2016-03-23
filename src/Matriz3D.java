import java.util.Arrays;

public class Matriz3D implements InterfazMatriz{
	public static final int TIPO = 2;
	@SuppressWarnings("rawtypes")
	ValorNumerico[][][] matriz;
	
	public Matriz3D(int[] length){
		matriz = new ValorNumerico[length[0]][length[1]][length[2]];
	}
	@SuppressWarnings("rawtypes")
	public void set(int[] i,ValorNumerico val){
		matriz[i[0]][i[1]][i[2]] = val;
	}
	@SuppressWarnings("rawtypes")
	public ValorNumerico get(int[] i){
		return matriz[i[0]][i[1]][i[2]];
	}
	public static Matriz3D sumar(Matriz3D mat1, Matriz3D mat2) throws MatrizException, ValorNumericoException {
		int[] dim1 = mat1.dimensions();
		int[] dim2 = mat2.dimensions();
		Matriz3D ret = new Matriz3D(dim1);
		if (Arrays.equals(dim1, dim2) && dim1.length == 3) {
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					for (int k = 0; k < dim2.length; k++) {
						ret.set(new int[] { i, j,k},
								ValorNumerico.suma(mat1.get(new int[] { i, j, k}), mat2.get(new int[] { i, j, k})));
					}
				}
			}
			return ret;
		} else {
			throw new MatrizException(MatrizException.generateErrorDimensions(dim1,dim2));
		}
	}
	//Solo se permite la multiplicacion punto a punto
	public static Matriz3D multiplicarP2P(Matriz3D mat1, Matriz3D mat2) throws MatrizException, ValorNumericoException {
		int[] dim1 = mat1.dimensions();
		int[] dim2 = mat2.dimensions();
		Matriz3D ret = new Matriz3D(dim1);
		if (Arrays.equals(dim1, dim2) && dim1.length == 3) {
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					for (int k = 0; k < dim2.length; k++) {
						ret.set(new int[] { i, j,k},
								ValorNumerico.multiplicar(mat1.get(new int[] { i, j, k}), mat2.get(new int[] { i, j, k})));
					}
				}
			}
			return ret;
		} else {
			throw new MatrizException(MatrizException.generateErrorDimensions(dim1,dim2));
		}
	}
	public static Matriz3D multiplicar(Matriz3D mat1, Matriz3D mat2) throws MatrizException, ValorNumericoException {
		int[] dim1 = mat1.dimensions();
		int[] dim2 = mat2.dimensions();
		if(dim1[0] == 1 && dim1[1] == 1 && dim1[2] == 1){
			//Caso de una sola dimension
			Matriz3D ret = new Matriz3D(dim2);
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					for (int k = 0; k < dim1[2]; k++) {
						ret.set(new int[]{i,j},ValorNumerico.multiplicar(mat1.get(new int[]{0,0,0}), mat2.get(new int[]{i,j,k})));
					}
				}
			}
			return ret;
		}else if(dim2[0] == 1 && dim2[1] == 1 && dim2[2] == 1){
			//Caso de una sola dimension
			Matriz3D ret = new Matriz3D(dim2);
			for (int i = 0; i < dim2[0]; i++) {
				for (int j = 0; j < dim2[1]; j++) {
					for (int k = 0; k < dim2[2]; k++) {
						ret.set(new int[]{i,j},ValorNumerico.multiplicar(mat1.get(new int[]{i,j,k}), mat2.get(new int[]{0,0})));
					}
				}
			}
			return ret;
		}else {
			throw new MatrizException(MatrizException.generateErrorDimensions(dim1, dim2));
		}
	}
	public void setNull(){
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				for (int j2 = 0; j2 < matriz[0][0].length; j2++) {
					matriz[i][j][j2].setNull();
				}
			}
		}
	}
	public String toString(){
		String ret = "";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				for (int j2 = 0; j2 < matriz[0][0].length; j2++) {
					ret = ret + " " + matriz[i][j][j2];
				}
				ret = ret + "\n";
			}
			ret = ret + "\n";
		}
		return ret;
	}
	public int[] dimensions() {
		return new int[]{matriz.length,matriz[0].length,matriz[0][0].length};
	}
}
