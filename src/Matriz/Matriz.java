package Matriz;
import java.util.ArrayList;
import java.util.Arrays;

import MathTool.MathToken;
import ValorNumerico.ValorNumerico;
import ValorNumerico.ValorNumericoException;

public class Matriz implements  Cloneable{
	public static final int TIPO = 1;
	ValorNumerico[][] matriz;

	public Matriz(int[] length) {
		matriz = new ValorNumerico[length[0]][length[1]];
		init();
	}
	public Matriz(String txt) throws MatrizException{
		int columnas = 0;
		int filas = 1;
		int lastPos = 0;//Indica la posicion del anterior separador
		int columnaActual=0;
		ArrayList<String> valores = new ArrayList<String>();
		for (int i = 0; i < txt.length(); i++) {
			if(txt.charAt(i) == MathToken.MATRIX_COLUMNA_SEPARATOR.charAt(0)){
				valores.add(txt.substring(lastPos, i));
				lastPos = i+1;
				if(filas == 1){
					columnas++;
				}
				columnaActual++;
			}else if(txt.charAt(i) == MathToken.MATRIX_FILA_SEPARATOR.charAt(0)){
				valores.add(txt.substring(lastPos, i));
				if(filas == 1){
					columnas++;
				}
				/* Si en algun momento habiendo mas de una fila, vamos a
				 *  cambiar de fila y hay m嫳 columnas de las que deberia o menos
				 *  tiramos error
				 */
				if(filas > 1 && columnaActual != columnas-1){
					throw new MatrizException("error");
				}
				lastPos = i+1;
				filas++;
				columnaActual=0;
			}else if(i == txt.length()-1){
				valores.add(txt.substring(lastPos, txt.length()));
				if(filas == 1){
					columnas++;
				}
			}
		}
		matriz = new ValorNumerico[filas][columnas];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = new ValorNumerico(valores.get(i*columnas+j));
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


	public void init() {
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
		String ret = "[";
		for (int i = 0; i < matriz.length ; i++) {
			for (int j = 0; j < matriz[0].length - 1; j++) {
				ret = ret + matriz[i][j]+ ", ";
			}
			if(i == matriz.length-1){
				ret=ret + matriz[i][matriz[0].length-1]+ "]";
			}else{
				ret = ret + matriz[i][matriz[0].length-1]+ "\n";
			}
		}
		return ret;
	}
	public String toStringInLine() {
		String ret = "[";
		for (int i = 0; i < matriz.length ; i++) {
			for (int j = 0; j < matriz[0].length - 1; j++) {
				ret = ret + matriz[i][j]+ ", ";
			}
			if(i == matriz.length-1){
				ret=ret + matriz[i][matriz[0].length-1]+ "]";
			}else{
				ret = ret + matriz[i][matriz[0].length-1]+ ";";
			}
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
	/**
	 * No existen las divisiones, solo la multiplicaci車n por el inverso para poder trabajar de igual forma con matrices
	 * @return
	 * @throws MatrizException
	 * @throws ValorNumericoException
	 */
	public Matriz invertir() throws MatrizException, ValorNumericoException{
		int[] dim1 = this.dimensions();
		Matriz ret = new Matriz(dim1);
		Matriz aux = (Matriz) this.clone();
		if (dim1[0] == 1 && dim1[1] == 1) {
			//Si es solo un elemento se calcula 1/elemento
			ret.set(new int[]{0,0},new ValorNumerico(1,0).divide(matriz[0][0]));
			return ret;
		}else if(dim1[0] == dim1[1]){
			ret.setIdentidad();
			//Si es cuadrada y mas de dimension 1,1
			ValorNumerico val = matriz[0][0];
			if(!val.equals(ValorNumerico.ONE)){
				aux.set(new int[]{0,0}, new ValorNumerico(1,0));
				ret.set(new int[]{0,0}, ret.get(new int[]{0,0}).divide(val));
				for (int j = 1; j < dim1[1]; j++) {
					int[] pos = new int[]{0, j};
					aux.set(pos,aux.get(pos).divide(val));
					ret.set(pos,ret.get(pos).divide(val));
				}
			}
			//Operar
			//i columnas,j filas,k columnas
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					//Se empieza primero por los elementos de las columnas
					ValorNumerico multi = aux.get(new int[]{j,i}).divide(aux.get(new int[]{i,i}));
					if(i!= j && !multi.equals(ValorNumerico.ZERO)){
						//Si un elemento que no es de la diagonal principal es distinto de 0 se resta a toda la fila
						for (int k = 0; k < dim1[1]; k++) {
							int[] pos = new int[]{j,k};
							aux.set(pos, aux.get(pos).substract(multi.multiply(aux.get(new int[]{j,k}))));
							ret.set(pos, ret.get(pos).substract(multi.multiply(ret.get(new int[]{j,k}))));
						}
					}
				}
			}
			for (int i = 1; i < dim1[1]; i++) {
				ValorNumerico val2 = (ValorNumerico)aux.get(new int[]{i,i}).clone();
				if(!val2.equals(ValorNumerico.ONE)){
					for (int j = 0; j < dim1[0]; j++) {
						int[] pos = new int[]{i, j};
						aux.set(pos, aux.get(pos).divide(val2));
						ret.set(pos, ret.get(pos).divide(val2));
					}
				}
			}
			return ret;
		} else {
			throw new MatrizException(MatrizException.generateErrorDimensions(new int[]{dim1[0]},new int[]{dim1[1]}));
		}
	}
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
	/**
	 * Devuelve una nueva matriz formada por valores de la matriz original. Estos valores se obtienen al utilizar la selecci車n de filas
	 * y selecci車n de columnas. Estos no son m芍s que un vector de dimension 3 donde el primer elemento indica la fila o columna desde
	 * donde empezar a coger elementos, el segundo indica el salto entre filas o columnas y el tercero la fila o columna l赤mite.
	 * Como ejemplo: matriz1[1:1:3,1:1:2] devolveria una matriz 3x2
	 * @param fila Parametro de selecci車n de columnas
	 * @param columna Par芍metro de selecci車n de filas
	 * @return
	 * @throws MatrizException 
	 */
	public Matriz getSubMatriz(int[] fila,int[] columna) throws MatrizException{
		int[] dimensiones = new int[]{(fila[2]-fila[0]+1)/fila[1],(columna[2]-columna[0]+1)/columna[1]};
		if(!MatrizUtils.validDimension(matriz.length, fila) || !MatrizUtils.validDimension(matriz[0].length, columna)){
			throw new MatrizException(MatrizException.generateErrorDimensions(this.dimensions(), dimensiones));
		}
		Matriz ret = new Matriz(dimensiones);
		int fil = 0;
		int col = 0;
		for (int i = fila[0]; i < fila[2]+1; i=i+fila[1]) {
			for (int j = columna[0]; j < columna[2]+1; j=j+columna[1]) {
				ret.set(new int[]{fil, col},(ValorNumerico) matriz[i][j].clone());
				col++;
			}
			col=0;
			fil++;
		}
		return ret;
	}

}
