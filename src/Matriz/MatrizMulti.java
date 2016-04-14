package Matriz;
import java.lang.reflect.Array;

import MathTool.VectorNumerico;

public class MatrizMulti {
	VectorNumerico vec;
	Array array;
	public MatrizMulti(int[] leng){
		//La idea es crear una matriz multidimensional solo pasando argumentos
		VectorNumerico[] origin = new VectorNumerico[leng.length -1];
		vec = new VectorNumerico(VectorNumerico.class,leng[0]);
		
		int operaciones = operaciones(leng);
		origin[0] = vec;
		int[] pos = new int[leng.length];
		while(operaciones(pos) < operaciones(leng)){
			//Dimension actual
			int turno = 0;
			origin[turno] = new VectorNumerico(VectorNumerico.class,leng[turno]);
			for (int d = 0; d < leng[d]; d++) {
				origin[turno].set(pos[turno], new VectorNumerico(VectorNumerico.class,leng[turno]));
				
			}
			turno++;
			pos[0]++;
			
			//array = (Array) Array.newInstance(VectorNumerico.class, length);
			pos[0]++;
			for(int i = 0;i < pos.length; i++){
				if(pos[i] >= leng[i]){
					pos[i] = 0;
					origin[i]=(VectorNumerico) origin[i-1].get(pos[i]);
				}
			}
			if(turno >= leng.length){
				turno = 0;
			}
			
		}
		
	}
	public static int operaciones2(int[] dimension){
		int ret = 1;
		for (int j = 1; j < dimension.length; j++) {
			ret = ret*dimension[j];
		}
		return ret;
	}
	public static int operaciones(int[] dimension){
		int ret = 1;
		for (int j = 0; j < dimension.length; j++) {
			ret = ret*dimension[j];
		}
		return ret;
	}
}
