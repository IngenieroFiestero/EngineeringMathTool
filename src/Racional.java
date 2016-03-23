import java.math.BigDecimal;
import java.math.BigInteger;

public class Racional implements Cloneable{
	private BigInteger numerador;
	private BigInteger denominador;
	
	public Racional(BigInteger numerador, BigInteger denominador){
		this.numerador = numerador;
		this.denominador = denominador;
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
	public BigInteger getNumerador(){
		return this.numerador;
	}
	public BigInteger getDenominador(){
		return this.denominador;
	}
	public void setValor(BigInteger numerador, BigInteger denominador){
		this.numerador = numerador;
		this.denominador = denominador;
	}
	public String toString(){
		return this.numerador  + "/"+ this.denominador;
	}
	public void setNull(){
		numerador =  new BigInteger("0");
		denominador = new BigInteger("1");
	}
	public static boolean equals(Racional comp1,Racional comp2){
		if((((BigInteger)comp1.getNumerador()).compareTo((BigInteger)comp2.getNumerador())==0)
				&& (((BigInteger)comp1.getDenominador()).compareTo((BigInteger)comp2.getDenominador())==0)){
			return true;
		}else{
			return false;
		}
	}
}
