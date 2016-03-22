import java.math.BigDecimal;
import java.math.BigInteger;

public class ValorNumericoCast {
	// Booleanos
	public static BigInteger bool2Entero(Boolean bol) {
		if (bol.booleanValue()) {
			return new BigInteger("1");
		} else {
			return new BigInteger("0");
		}
	}

	public static BigDecimal bool2Real(Boolean bol) {
		if (bol.booleanValue()) {
			return new BigDecimal("1");
		} else {
			return new BigDecimal("0");
		}
	}

	public static Racional bool2Racional(Boolean bol) {
		if (bol.booleanValue()) {
			return new Racional(new BigInteger("1"), new BigInteger("1"));
		} else {
			return new Racional(new BigInteger("0"), new BigInteger("1"));
		}
	}

	public static Complejo bool2Complejo(Boolean bol) {
		if (bol.booleanValue()) {
			return new Complejo(new BigInteger("1"), new BigInteger("1"));
		} else {
			return new Complejo(new BigInteger("0"), new BigInteger("1"));
		}
	}

	// Enteros
	public static Boolean entero2Bool(BigInteger entero) throws ValorNumericoException {
		if (entero.intValue() == 1) {
			return new Boolean(true);
		} else if (entero.intValue() == 0) {
			return new Boolean(false);
		} else {
			throw new ValorNumericoException("");
		}
	}

	public static Racional entero2Racional(BigInteger entero) {
		return new Racional(entero, new BigInteger("1"));
	}

	public static BigDecimal entero2Real(BigInteger entero) {
		return new BigDecimal(entero);
	}

	public static Complejo entero2Complejo(BigInteger entero) {
		return new Complejo(entero, new BigInteger("0"));
	}

	// Racionales
	public static Boolean racional2Bool(Racional racional) throws ValorNumericoException{
		if(racional.getNumerador() instanceof BigInteger){
			if(((BigInteger)racional.getDenominador()).intValue()==0){
				if(((BigInteger)racional.getNumerador()).intValue()==1){
					return new Boolean(true);
				}else if(((BigInteger)racional.getNumerador()).intValue()==0){
					return new Boolean(false);
				}else{
					throw new ValorNumericoException("");
				}
			}else{
				throw new ValorNumericoException("");
			}
		}else if(((BigDecimal)racional.getDenominador()).intValue()==0){
			if(((BigDecimal)racional.getNumerador()).intValue()==1){
				return new Boolean(true);
			}else if(((BigDecimal)racional.getNumerador()).intValue()==0){
				return new Boolean(false);
			}else{
				throw new ValorNumericoException("");
			}
		}else{
			throw new ValorNumericoException("");
		}
	}
	public static BigInteger racional2Entero(Racional racional) throws ValorNumericoException{
		if(racional.getNumerador() instanceof BigInteger){
			if(((BigInteger)racional.getDenominador()).intValue()==1){
				return (BigInteger)racional.getNumerador();
			}else{
				throw new ValorNumericoException("");
			}
		}else if(((BigDecimal)racional.getDenominador()).intValue()==1){
			return ((BigDecimal)racional.getDenominador()).toBigIntegerExact();
		}else{
			throw new ValorNumericoException("");
		}
	}
}
