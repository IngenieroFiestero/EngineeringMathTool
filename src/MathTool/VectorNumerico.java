package MathTool;
import java.lang.reflect.Array;

public class VectorNumerico <T>{
	private T[] vector;
	
	public VectorNumerico(Class<T> c,int length){
		vector = (T[]) Array.newInstance(c, length);
	}
	public void set(int i,T dato){
		vector[i] = dato;
	}
	public T get(int i){
		return vector[i];
	}
	public int length(){
		return vector.length;
	}
	public String toString(){
		String ret = "";
		for (int i = 0; i < vector.length; i++) {
			ret = ret +" "+ vector[i];
		}
		return ret;
	}
}
