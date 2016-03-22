import java.math.BigDecimal;
import java.math.BigInteger;

/* Es  necesario quesea de tipo T
 * De esta forma podemos tener números complejos enteros y decimales
 */
public class Complejo <T>{
	private T real;
	private T imaginario;
	
	public Complejo(T real, T imaginario){
		this.real = real;
		this.imaginario = imaginario;
	}
	public T getReal(){
		return this.real;
	}
	public T getImaginario(){
		return this.imaginario;
	}
	public void setValor(T real, T imaginario){
		this.real = real;
		this.imaginario = imaginario;
	}
	public String toString(){
		return this.real  + " "+ this.imaginario+ "j";
	}
	
	public static boolean equals(Complejo comp1,Complejo comp2){
		//Solo soportamos BigDecmal y BigInteger
		if(comp1.getReal()instanceof BigInteger 
				&& comp2.getReal() instanceof BigInteger 
				&& (((BigInteger)comp1.getReal()).compareTo((BigInteger)comp2.getReal())==0)
				&& (((BigInteger)comp1.getImaginario()).compareTo((BigInteger)comp2.getImaginario())==0)){
			return true;
		}else if(comp1.getReal()instanceof BigDecimal 
				&& comp2.getReal() instanceof BigDecimal 
				&& (((BigDecimal)comp1.getReal()).compareTo((BigDecimal)comp2.getReal())==0)
				&& (((BigDecimal)comp1.getImaginario()).compareTo((BigDecimal)comp2.getImaginario())==0)){
			return true;
		}else{
			return false;
		}
	}
}
