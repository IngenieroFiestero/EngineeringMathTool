
public class Matriz3D implements InterfazMatriz{
	public static final int TIPO = 2;
	ValorNumerico[][][] matriz;
	
	public Matriz3D(int[] length){
		matriz = new ValorNumerico[length[0]][length[1]][length[2]];
	}
	public void set(int[] i,ValorNumerico val){
		matriz[i[0]][i[1]][i[2]] = val;
	}
	public ValorNumerico get(int[] i){
		return matriz[i[0]][i[1]][i[2]];
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
