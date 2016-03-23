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
			return new Complejo(new BigDecimal("1"), new BigDecimal("1"));
		} else {
			return new Complejo(new BigDecimal("0"), new BigDecimal("1"));
		}
	}

	// Enteros
	public static Boolean entero2Bool(BigInteger entero) throws ValorNumericoException {
		if (entero.intValue() == 1) {
			return new Boolean(true);
		} else if (entero.intValue() == 0) {
			return new Boolean(false);
		} else {
			throw new ValorNumericoException(ValorNumericoException.generarErrorCasteo(new int[]{ValorNumerico.TIPO_ENTERO,ValorNumerico.TIPO_BOOLEANO}));
		}
	}

	public static Racional entero2Racional(BigInteger entero) {
		return new Racional(entero, new BigInteger("1"));
	}

	public static BigDecimal entero2Real(BigInteger entero) {
		return new BigDecimal(entero);
	}

	public static Complejo entero2Complejo(BigInteger entero) {
		return new Complejo(new BigDecimal(entero), new BigDecimal("0"));
	}

	// Racionales
	public static Boolean racional2Bool(Racional racional) throws ValorNumericoException{
		if(((BigInteger)racional.getDenominador()).intValue()==0){
			if(((BigInteger)racional.getNumerador()).intValue()==1){
				return new Boolean(true);
			}else if(((BigInteger)racional.getNumerador()).intValue()==0){
				return new Boolean(false);
			}else{
				throw new ValorNumericoException(ValorNumericoException.generarErrorCasteo(new int[]{ValorNumerico.TIPO_RACIONAL,ValorNumerico.TIPO_BOOLEANO}));
			}
		}else{
			throw new ValorNumericoException(ValorNumericoException.generarErrorCasteo(new int[]{ValorNumerico.TIPO_RACIONAL,ValorNumerico.TIPO_BOOLEANO}));
		}
	}
	public static BigInteger racional2Entero(Racional racional) throws ValorNumericoException{
		if(((BigInteger)racional.getDenominador()).intValue()==1){
			return (BigInteger)racional.getNumerador();
		}else{
			throw new ValorNumericoException(ValorNumericoException.generarErrorCasteo(new int[]{ValorNumerico.TIPO_RACIONAL,ValorNumerico.TIPO_ENTERO}));
		}
	}
	public static BigDecimal racional2Real(Racional racional){
		return (new BigDecimal((BigInteger)racional.getNumerador()).divide(new BigDecimal((BigInteger)racional.getDenominador())));
	}
	public static Complejo racional2Complejo(Racional racional){
		return new Complejo(new BigDecimal((BigInteger)racional.getNumerador()).divide(new BigDecimal((BigInteger)racional.getDenominador())),new BigDecimal("0"));
	}
	//Reales
	public static Boolean real2Bool(BigDecimal real)throws ValorNumericoException{
		if((real).intValue()==0){
			return new Boolean(false);
		}else if((real).intValue()==1){
			return new Boolean(true);
		}else{
			throw new ValorNumericoException(ValorNumericoException.generarErrorCasteo(new int[]{ValorNumerico.TIPO_REAL,ValorNumerico.TIPO_BOOLEANO}));
		}
	}
	public static BigInteger real2Entero(BigDecimal real)throws ValorNumericoException{
		return real.toBigIntegerExact();
	}
	public static Racional real2Racional(BigDecimal real){
		return new Racional(real.toBigIntegerExact(),BigInteger.ZERO);
	}
	public static Complejo real2Complejo(BigDecimal real){
		return new Complejo(real,BigDecimal.ZERO);
	}
	//Complejos

	public static Boolean complejo2Bool(Complejo complejo) throws ValorNumericoException{
		if(((BigDecimal)complejo.getImaginario()).intValue()==0){
			if(((BigDecimal)complejo.getReal()).intValue()==1){
				return new Boolean(true);
			}else if(((BigDecimal)complejo.getReal()).intValue()==0){
				return new Boolean(false);
			}else{
				throw new ValorNumericoException(ValorNumericoException.generarErrorCasteo(new int[]{ValorNumerico.TIPO_COMPLEJO,ValorNumerico.TIPO_COMPLEJO}));
			}
		}else{
			throw new ValorNumericoException(ValorNumericoException.generarErrorCasteo(new int[]{ValorNumerico.TIPO_COMPLEJO,ValorNumerico.TIPO_COMPLEJO}));
		}
	}
	public static BigInteger complejo2Entero(Complejo complejo) throws ValorNumericoException{
		if(((BigDecimal)complejo.getImaginario()).intValue()==0){
			return ((BigDecimal)complejo.getReal()).toBigIntegerExact();
		}else{
			throw new ValorNumericoException(ValorNumericoException.generarErrorCasteo(new int[]{ValorNumerico.TIPO_COMPLEJO,ValorNumerico.TIPO_ENTERO}));
		}
	}
	public static Racional complejo2Racional(Complejo complejo) throws ValorNumericoException{
		if(((BigDecimal)complejo.getImaginario()).intValue()==0){
			return new Racional(complejo.getReal().toBigIntegerExact(),new BigInteger("0"));
		}else{
			throw new ValorNumericoException(ValorNumericoException.generarErrorCasteo(new int[]{ValorNumerico.TIPO_COMPLEJO,ValorNumerico.TIPO_RACIONAL}));
		}
	}
	public static BigDecimal complejo2Real(Complejo complejo) throws ValorNumericoException{
		if(((BigDecimal)complejo.getImaginario()).intValue()==0){
			return complejo.getReal();
		}else{
			throw new ValorNumericoException(ValorNumericoException.generarErrorCasteo(new int[]{ValorNumerico.TIPO_COMPLEJO,ValorNumerico.TIPO_REAL}));
		}
	}
	
}
