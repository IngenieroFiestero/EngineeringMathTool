import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.BitSet;

public class ValorNumerico<T> {
	/*
	 * Para enteros usar BigInteger y para decimal BigDecimal
	*/
	public static final int TIPO_BOOLEANO = 0;
	public static final int TIPO_ENTERO = 1;
	public static final int TIPO_RACIONAL = 2;
	public static final int TIPO_REAL = 3;
	public static final int TIPO_COMPLEJO = 4;

	private T valor;
	private int tipo;

	public ValorNumerico(int tipo,T valor) {
		this.tipo = tipo;
		this.valor = valor;
	}
	public ValorNumerico(T valor) {
		if(valor instanceof Complejo){
			this.tipo = TIPO_COMPLEJO;
		}else if(valor instanceof Racional){
			this.tipo = TIPO_RACIONAL;
		}else if(valor instanceof BigInteger){
			this.tipo = TIPO_ENTERO;
		}else if(valor instanceof BigDecimal){
			this.tipo = TIPO_REAL;
		}else{
			this.tipo = TIPO_BOOLEANO;
		}
		this.valor = valor;
	}
	public int getTipo(){
		return this.tipo;
	}
	public T getValor(){
		return this.valor;
	}
	public void setValor(T val){
		this.valor = val;
	}
	public void setNull(){
		if(valor instanceof Complejo){
			((Complejo) valor).setNull();
		}else if(valor instanceof Racional){
			((Racional) valor).setNull();
		}else if(valor instanceof BigInteger){
			valor = (T) new BigInteger("0");
		}else if(valor instanceof BigDecimal){
			valor = (T) new BigDecimal("0");
		}else{
			valor = (T) new Boolean(false);
		}
	}
}
