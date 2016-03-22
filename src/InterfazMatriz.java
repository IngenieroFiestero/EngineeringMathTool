
public interface InterfazMatriz {
	public int[] dimensions();
	public void set(int[] i,ValorNumerico val);
	public ValorNumerico get(int[] i);
	public void setNull();
	public static InterfazMatriz sumar(InterfazMatriz mat1, InterfazMatriz mat2){
		return null;
	}
}
