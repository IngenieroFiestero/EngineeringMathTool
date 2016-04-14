package Matriz;

public class MatrizUtils {
	/**
	 * Fila1 indica las dimensiones de la matriz original, fila2 es un vector de dimension 3 que indica como se cogen llos valores de filas o columnas de una matriz
	 * @param fila1
	 * @param fila2
	 * @return
	 */
	public static boolean validDimension(int fila1, int[] fila2){
		//Si el numero de fila donde se empieza es menor que el numero de filas 
		if(fila2[0] > fila1 || fila2[2] > fila1){
			return false;
		}else{
			return true;
		}
	}

}
