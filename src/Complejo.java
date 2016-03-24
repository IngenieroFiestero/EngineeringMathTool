import java.math.BigDecimal;
import java.math.BigInteger;

/* Es  necesario quesea de tipo T
 * De esta forma podemos tener números complejos enteros y decimales
 */
public class Complejo implements Cloneable{
	private BigDecimal real;
	private BigDecimal imaginario;
	
	public Complejo(BigDecimal real, BigDecimal imaginario){
		this.real = real;
		this.imaginario = imaginario;
	}
	public Object clone(){
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException ex) {
			System.out.println(" no se puede duplicar");
		}
		return obj;
	}
	public BigDecimal getReal(){
		return this.real;
	}
	public BigDecimal getImaginario(){
		return this.imaginario;
	}
	public void setValor(BigDecimal real, BigDecimal imaginario){
		this.real = real;
		this.imaginario = imaginario;
	}
	public String toString(){
		return this.real  + " "+ this.imaginario+ "j";
	}
	public void setNull(){
		real = new BigDecimal("0");
		imaginario = new BigDecimal("0");
	}
	public static Complejo conjugado(Complejo comp){
		return new Complejo(comp.getReal(),comp.getImaginario().multiply(new BigDecimal("-1")));
	}
	
	public static boolean equals(Complejo comp1,Complejo comp2){
		if((((BigDecimal)comp1.getReal()).compareTo((BigDecimal)comp2.getReal())==0)
				&& (((BigDecimal)comp1.getImaginario()).compareTo((BigDecimal)comp2.getImaginario())==0)){
			return true;
		}else{
			return false;
		}
	}
}
