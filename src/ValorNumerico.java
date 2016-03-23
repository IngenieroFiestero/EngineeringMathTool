import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.BitSet;
import java.util.Random;

public class ValorNumerico<T> implements Cloneable {
	/*
	 * Para enteros usar BigInteger y para decimal BigDecimal
	*/
	public static final int TIPO_BOOLEANO = 0;
	public static final int TIPO_ENTERO = 1;
	public static final int TIPO_RACIONAL = 2;
	public static final int TIPO_REAL = 3;
	public static final int TIPO_COMPLEJO = 4;
	public static final String[] nombres = new String[]{"Boolean","Integer","Rational","Real","Complex"};

	private T valor;
	private int tipo;

	public ValorNumerico(int tipo,T valor) {
		this.tipo = tipo;
		this.valor = valor;
	}
	@SuppressWarnings("unchecked")
	public Object clone(){
		Object obj=null;
		switch(tipo){
		case TIPO_BOOLEANO : 
			obj =  new ValorNumerico(this.tipo,new Boolean((boolean)valor));
			break;
		case TIPO_ENTERO : 
			obj=  new ValorNumerico(this.tipo,new BigInteger(((BigInteger)this.getValor()).toString()));
			break;
		case TIPO_RACIONAL : 
			obj=  new ValorNumerico(this.tipo,new Racional(new BigInteger(((BigInteger)((Racional)this.getValor()).getNumerador()).toString()),
					new BigInteger(((BigInteger)((Racional)this.getValor()).getDenominador()).toString())));
			break;
		case TIPO_REAL : 
			obj = new ValorNumerico(this.tipo,new BigDecimal(((BigDecimal)this.getValor()).toString()));
			break;
		case TIPO_COMPLEJO : 
			obj = new ValorNumerico(this.tipo,new Complejo(new BigDecimal(((BigDecimal)((Complejo)this.getValor()).getReal()).toString()),new BigDecimal(((BigDecimal)((Complejo)this.getValor()).getImaginario()).toString())));
			break;
		}
		return obj;
	}
	public ValorNumerico(int tipo) {
		this.tipo = tipo;
		switch(tipo){
		case TIPO_BOOLEANO : 
			valor = (T) new Boolean(false);
			break;
		case TIPO_ENTERO : 
			valor = (T) new BigInteger("0");
			break;
		case TIPO_RACIONAL : 
			valor = (T) new Racional(new BigInteger("0"),new BigInteger("0"));
			break;
		case TIPO_REAL : 
			valor = (T) new BigDecimal("0");
			break;
		case TIPO_COMPLEJO : 
			valor = (T) new Complejo(new BigDecimal("0"),new BigDecimal("0"));
			break;
		}
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
	public void setRand(){
		Random rand = new Random();
		if(valor instanceof Complejo){
			valor = (T) (new Complejo(new BigDecimal(rand.nextDouble()),new BigDecimal(rand.nextDouble())));
		}else if(valor instanceof Racional){
			valor = (T) new Racional(new BigInteger(8,rand),new BigInteger(8,rand));
		}else if(valor instanceof BigInteger){
			valor = (T) new BigInteger(8,rand);
		}else if(valor instanceof BigDecimal){
			valor = (T)new BigDecimal(rand.nextDouble());
		}else{
			valor = (T) new Boolean(rand.nextBoolean());
		}
	}
	public static ValorNumerico suma(ValorNumerico val1,ValorNumerico val2) throws ValorNumericoException{
		if(val1.getTipo()> val2.getTipo()){
			val2.castear(val1.getTipo());
		}else{
			val1.castear(val2.getTipo());
		}
		int tipo = val1.getTipo();
		switch(tipo){
		case TIPO_BOOLEANO : 
			return new ValorNumerico(new Boolean((Boolean)val1.getValor() || (Boolean)val2.getValor()));
		case TIPO_ENTERO : 
			return new ValorNumerico(((BigInteger)val1.getValor()).add((BigInteger)val2.getValor()));
		case TIPO_RACIONAL : 
			BigInteger numerador = ((Racional)val1.getValor()).getNumerador().multiply(((Racional)val2.getValor()).getNumerador())
				.add(((Racional)val2.getValor()).getNumerador().multiply(((Racional)val1.getValor()).getNumerador()));
			BigInteger denominador = ((Racional)val1.getValor()).getDenominador().multiply(((Racional)val2.getValor()).getDenominador());
			return new ValorNumerico(new Racional(numerador,denominador));
		case TIPO_REAL : 
			return new ValorNumerico(((BigDecimal)val1.getValor()).add(((BigDecimal)val2.getValor())));
		case TIPO_COMPLEJO : 
			return new ValorNumerico(new Complejo(((Complejo)val1.getValor()).getReal().add(((Complejo)val2.getValor()).getReal()),
					((Complejo)val1.getValor()).getImaginario().add(((Complejo)val2.getValor()).getImaginario())));
		default : throw new ValorNumericoException(ValorNumericoException.generarErrorSuma(new int[]{val1.getTipo()}));
		}
	}
	public static ValorNumerico multiplicar(ValorNumerico val1,ValorNumerico val2) throws ValorNumericoException{
		if(val1.getTipo()> val2.getTipo()){
			val2.castear(val1.getTipo());
		}else{
			val1.castear(val2.getTipo());
		}
		int tipo = val1.getTipo();
		switch(tipo){
		case TIPO_BOOLEANO : 
			return new ValorNumerico(new Boolean((Boolean)val1.getValor() && (Boolean)val2.getValor()));
		case TIPO_ENTERO : 
			return new ValorNumerico(((BigInteger)val1.getValor()).multiply((BigInteger)val2.getValor()));
		case TIPO_RACIONAL : 
			BigInteger numerador = ((Racional)val1.getValor()).getNumerador().multiply(((Racional)val2.getValor()).getNumerador());
			BigInteger denominador = ((Racional)val1.getValor()).getDenominador().multiply(((Racional)val2.getValor()).getDenominador());
			return new ValorNumerico(new Racional(numerador,denominador));
		case TIPO_REAL : 
			return new ValorNumerico(((BigDecimal)val1.getValor()).multiply(((BigDecimal)val2.getValor())));
		case TIPO_COMPLEJO : 
			BigDecimal real = ((Complejo)val1.getValor()).getReal().multiply(((Complejo)val2.getValor()).getReal()).subtract(
					((Complejo)val1.getValor()).getImaginario().multiply(((Complejo)val2.getValor()).getImaginario()));
			BigDecimal imag = ((Complejo)val1.getValor()).getReal().multiply(((Complejo)val2.getValor()).getImaginario()).add(
					((Complejo)val2.getValor()).getReal().multiply(((Complejo)val1.getValor()).getImaginario()));
			return new ValorNumerico(new Complejo(real,imag));
		default : throw new ValorNumericoException(ValorNumericoException.generarErrorSuma(new int[]{val1.getTipo()}));
		}
	}
	
	public void castear(int tipo)throws ValorNumericoException{
		if(this.tipo != tipo){
			switch(this.tipo){
			case TIPO_BOOLEANO : 
				switch(tipo){
				case TIPO_ENTERO : 
					valor = (T) ValorNumericoCast.bool2Entero((Boolean)this.valor);
					break;
				case TIPO_RACIONAL : 
					valor = (T) ValorNumericoCast.bool2Racional((Boolean)this.valor);
					break;
				case TIPO_REAL : 
					valor = (T) ValorNumericoCast.bool2Real((Boolean)this.valor);
					break;
				case TIPO_COMPLEJO : 
					valor = (T) ValorNumericoCast.bool2Complejo((Boolean)this.valor);
					break;
				}
				break;
			case TIPO_ENTERO : 
				switch(tipo){
				case TIPO_BOOLEANO : 
					valor = (T) ValorNumericoCast.entero2Bool((BigInteger)this.valor);
					break;
				case TIPO_RACIONAL : 
					valor = (T) ValorNumericoCast.entero2Racional((BigInteger)this.valor);
					break;
				case TIPO_REAL : 
					valor = (T) ValorNumericoCast.entero2Real((BigInteger)this.valor);
					break;
				case TIPO_COMPLEJO : 
					valor = (T) ValorNumericoCast.entero2Complejo((BigInteger)this.valor);
					break;
				}
				break;
			case TIPO_RACIONAL : 
				switch(tipo){
				case TIPO_BOOLEANO : 
					valor = (T) ValorNumericoCast.racional2Bool((Racional)this.valor);
					break;
				case TIPO_ENTERO : 
					valor = (T) ValorNumericoCast.racional2Entero((Racional)this.valor);
					break;
				case TIPO_REAL : 
					valor = (T) ValorNumericoCast.racional2Real((Racional)this.valor);
					break;
				case TIPO_COMPLEJO : 
					valor = (T) ValorNumericoCast.racional2Complejo((Racional)this.valor);
					break;
				}
				break;
			case TIPO_REAL : 
				switch(tipo){
				case TIPO_BOOLEANO : 
					valor = (T) ValorNumericoCast.real2Bool((BigDecimal)this.valor);
					break;
				case TIPO_ENTERO : 
					valor = (T) ValorNumericoCast.real2Entero((BigDecimal)this.valor);
					break;
				case TIPO_RACIONAL : 
					valor = (T) ValorNumericoCast.real2Racional((BigDecimal)this.valor);
					break;
				case TIPO_COMPLEJO : 
					valor = (T) ValorNumericoCast.real2Complejo((BigDecimal)this.valor);
					break;
				}
				break;
			case TIPO_COMPLEJO : 
				switch(tipo){
				case TIPO_BOOLEANO : 
					valor = (T) ValorNumericoCast.complejo2Bool((Complejo)this.valor);
					break;
				case TIPO_ENTERO : 
					valor = (T) ValorNumericoCast.complejo2Entero((Complejo)this.valor);
					break;
				case TIPO_REAL : 
					valor = (T) ValorNumericoCast.complejo2Real((Complejo)this.valor);
					break;
				case TIPO_RACIONAL : 
					valor = (T) ValorNumericoCast.complejo2Racional((Complejo)this.valor);
					break;
				}
				break;
			}
		}
	}
}
