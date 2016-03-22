import java.math.BigInteger;
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
	
}
