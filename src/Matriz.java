import java.math.BigInteger;
import java.util.Arrays;
import java.math.BigDecimal;
public class Matriz implements InterfazMatriz{
	public static final int TIPO = 1;
	ValorNumerico[][] matriz;
	
	public Matriz(int[] length){
		matriz = new ValorNumerico[length[0]][length[1]];
		init();
	}
	public void set(int[] i,ValorNumerico val){
		matriz[i[0]][i[1]] = val;
	}
	public ValorNumerico get(int[] i){
		return matriz[i[0]][i[1]];
	}
	public int[] dimensions(){
		return new int[]{matriz.length,matriz[0].length};
	}
	private void init(){
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j] = new ValorNumerico(new BigInteger("0"));
			}
		}
	}
	public void setNull(){
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matriz[i][j].setNull();
			}
		}
	}
	public String toString(){
		String ret = "";
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				ret = ret + " " + matriz[i][j].getValor();
			}
			ret = ret + "\n";
		}
		return ret;
	}
	public static Matriz sumar(Matriz mat1, Matriz mat2) throws MatrizException{
		int[] dim1 = mat1.dimensions();
		int[] dim2 = mat2.dimensions();
		Matriz ret = new Matriz(dim1);
		if(Arrays.equals(dim1,dim2)){
			for (int i = 0; i < dim1[0]; i++) {
				for (int j = 0; j < dim1[1]; j++) {
					ret.set(new int[]{i, j},ValorNumerico.suma(mat1.get(new int[]{i,j}), mat2.get(new int[]{i,j})));
				}
			}
			return ret;
		}else{
			throw new MatrizException("");
		}
	}
	
}
