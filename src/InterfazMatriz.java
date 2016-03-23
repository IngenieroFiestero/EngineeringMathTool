
public interface InterfazMatriz {
	public int[] dimensions();
	@SuppressWarnings("rawtypes")
	public void set(int[] i,ValorNumerico val);
	@SuppressWarnings("rawtypes")
	public ValorNumerico get(int[] i);
	public void setNull();
	
	public static InterfazMatriz suma(InterfazMatriz mat1,InterfazMatriz mat2) throws MatrizException, ValorNumericoException{
		if(mat1 instanceof Matriz && mat2 instanceof Matriz){
			return Matriz.sumar((Matriz)mat1,(Matriz)mat2);
		}else if(mat1 instanceof Matriz3D && mat2 instanceof Matriz3D){
			return Matriz3D.sumar((Matriz3D)mat1, (Matriz3D)mat2);
		}else{
			throw new MatrizException(MatrizException.notImplementedError());
		}
	}
	public static InterfazMatriz multiplicacion(InterfazMatriz mat1,InterfazMatriz mat2) throws MatrizException, ValorNumericoException{
		if(mat1 instanceof Matriz && mat2 instanceof Matriz){
			return Matriz.multiplicar((Matriz)mat1,(Matriz)mat2);
		}else if(mat1 instanceof Matriz3D && mat2 instanceof Matriz3D){
			return Matriz3D.multiplicar((Matriz3D)mat1, (Matriz3D)mat2);
		}else{
			throw new MatrizException(MatrizException.notImplementedError());
		}
	}
	
	public static InterfazMatriz multiplicacionP2P(InterfazMatriz mat1,InterfazMatriz mat2) throws MatrizException, ValorNumericoException{
		if(mat1 instanceof Matriz && mat2 instanceof Matriz){
			return Matriz.multiplicarP2P((Matriz)mat1,(Matriz)mat2);
		}else if(mat1 instanceof Matriz3D && mat2 instanceof Matriz3D){
			return Matriz3D.multiplicarP2P((Matriz3D)mat1, (Matriz3D)mat2);
		}else{
			throw new MatrizException(MatrizException.notImplementedError());
		}
	}
	//Used to concat dimensions of arrays to mensage exceptions
	public static int[] concat(int[] var1,int[] var2){
		int[] ret = new int[var1.length + var2.length];
		int i = 0;
		for (i = 0; i < var1.length; i++) {
			ret[i] = var1[i];
		}
		for (int j = 0; j < var2.length; j++) {
			ret[j+i] = var2[j];
		}
		return ret;
	}
}
