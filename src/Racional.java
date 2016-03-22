import java.math.BigDecimal;
import java.math.BigInteger;

public class Racional <T>{
	private T numerador;
	private T denominador;
	
	public Racional(T numerador, T denominador){
		this.numerador = numerador;
		this.denominador = denominador;
	}
	public T getNumerador(){
		return this.numerador;
	}
	public T getDenominador(){
		return this.denominador;
	}
	public void setValor(T numerador, T denominador){
		this.numerador = numerador;
		this.denominador = denominador;
	}
	public String toString(){
		return this.numerador  + "/"+ this.denominador;
	}
	public void setNull(){
		if(numerador instanceof BigInteger){
			numerador = (T) new BigInteger("0");
			denominador = (T) new BigInteger("1");
		}else{
			numerador = (T) new BigDecimal("0");
			denominador = (T) new BigDecimal("1");
		}
	}
	public static boolean equals(Racional comp1,Racional comp2){
		//Solo soportamos BigDecmal y BigInteger
		if(comp1.getNumerador()instanceof BigInteger 
				&& comp2.getNumerador() instanceof BigInteger 
				&& (((BigInteger)comp1.getNumerador()).compareTo((BigInteger)comp2.getNumerador())==0)
				&& (((BigInteger)comp1.getDenominador()).compareTo((BigInteger)comp2.getDenominador())==0)){
			return true;
		}else if(comp1.getNumerador()instanceof BigDecimal 
				&& comp2.getNumerador() instanceof BigDecimal 
				&& (((BigDecimal)comp1.getNumerador()).compareTo((BigDecimal)comp2.getNumerador())==0)
				&& (((BigDecimal)comp1.getDenominador()).compareTo((BigDecimal)comp2.getDenominador())==0)){
			return true;
		}else{
			return false;
		}
	}
}
