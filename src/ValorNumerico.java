import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class ValorNumerico implements Cloneable {
	private double real;
	private double imaginario;
	public static final String IMAGINARIO = "i";
	public static final String[] TOKENS = new String[] { " ", "+", "-", "i" };
	public static final ValorNumerico I = new ValorNumerico(0.0, 1.0);
	public static final ValorNumerico NaN = new ValorNumerico(Double.NaN, Double.NaN);
	public static final ValorNumerico INF = new ValorNumerico(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
	public static final ValorNumerico ONE = new ValorNumerico(1.0, 0.0);
	public static final ValorNumerico ZERO = new ValorNumerico(0.0, 0.0);
	private final transient boolean isNaN;
	private final transient boolean isInfinite;

	public ValorNumerico(double real) {
		this(real, 0.0);
	}

	public ValorNumerico(double real, double imaginario) {
		this.real = real;
		this.imaginario = imaginario;
		isNaN = Double.isNaN(real) || Double.isNaN(imaginario);
		isInfinite = !isNaN && (Double.isInfinite(real) || Double.isInfinite(imaginario));
	}

	public ValorNumerico(String numero) {
		String[] valores = spliter(numero);
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		double imagin = 0;
		double real = 0;
		for (int i = 0; i < valores.length; i++) {
			if (isNumber(valores[i])) {
				numeros.add(new Integer(i));
			}
		}
		for (int i = 0; i < numeros.size(); i++) {
			int pos = numeros.get(i);
			if ((pos + 1) < valores.length && valores[pos + 1].equals(IMAGINARIO)) {
				// HEMOS ENCONTRADO EL NUMERO IMAGINARIO
				if ((pos - 1) >= 0 && isSign(valores[pos - 1])) {
					imagin = Double.parseDouble(valores[pos - 1] + valores[pos]);
				} else {
					imagin = Double.parseDouble(valores[pos]);
				}
			} else {
				if ((pos - 1) >= 0 && isSign(valores[pos - 1])) {
					real = Double.parseDouble(valores[pos - 1] + valores[pos]);
				} else {
					real = Double.parseDouble(valores[pos]);
				}
			}
		}
		this.real = real;
		this.imaginario = imagin;
		isNaN = Double.isNaN(real) || Double.isNaN(imaginario);
		isInfinite = !isNaN && (Double.isInfinite(real) || Double.isInfinite(imaginario));
	}

	public String toString() {
		if (this.imaginario == 0) {
			return this.real + "";
		} else if (this.real == 0) {
			return this.imaginario + "i";
		} else {
			return this.real + " " + this.imaginario + "i";
		}
	}

	public double getReal() {
		return this.real;
	}

	public double getImaginario() {
		return this.imaginario;
	}

	public boolean isInfinite() {
		return this.isInfinite;
	}

	public boolean isNaN() {
		return this.isNaN;
	}

	public ValorNumerico add(ValorNumerico val) {
		if (isNaN || val.isNaN()) {
			return NaN;
		} else {
			return new ValorNumerico(this.real + val.getReal(), this.imaginario + val.getImaginario());
		}

	}

	public ValorNumerico substract(ValorNumerico val) {
		if (isNaN || val.isNaN()) {
			return NaN;
		} else {
			return new ValorNumerico(this.real - val.getReal(), this.imaginario - val.getImaginario());
		}
	}

	public ValorNumerico conjugate() {
		if (isNaN) {
			return NaN;
		}

		return new ValorNumerico(this.real, -this.imaginario);
	}

	public ValorNumerico divide(ValorNumerico divisor) throws ValorNumericoException {
		checkNotNull(divisor);
		if (isNaN || divisor.isNaN) {
			return NaN;
		}
		final double c = divisor.getReal();
		final double d = divisor.getImaginario();
		if (c == 0.0 && d == 0.0) {
			return NaN;
		}

		if (divisor.isInfinite() && !isInfinite()) {
			return ZERO;
		}

		if (Math.abs(c) < Math.abs(d)) {
			double q = c / d;
			double denominador = c * q + d;
			return new ValorNumerico((real * q + imaginario) / denominador, (imaginario * q - real) / denominador);
		} else {
			double q = d / c;
			double denominador = d * q + c;
			return new ValorNumerico((imaginario * q + real) / denominador, (imaginario - real * q) / denominador);
		}
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other instanceof ValorNumerico) {
			ValorNumerico c = (ValorNumerico) other;
			if (c.isNaN) {
				return isNaN;
			} else {
				return new Double(real).equals(new Double(c.getReal()))
						&& new Double(imaginario).equals(new Double(c.getImaginario()));
			}
		}
		return false;
	}

	public ValorNumerico multiply(ValorNumerico factor) throws ValorNumericoException {
		checkNotNull(factor);
		if (isNaN || factor.isNaN) {
			return NaN;
		}
		if (Double.isInfinite(real) || Double.isInfinite(imaginario) || Double.isInfinite(factor.real)
				|| Double.isInfinite(factor.imaginario)) {
			return INF;
		}
		return new ValorNumerico(real * factor.real - imaginario * factor.imaginario,
				real * factor.imaginario + imaginario * factor.real);
	}
	public Object clone(){
		return new ValorNumerico(this.real,this.imaginario);
	}
	public ValorNumerico negate() {
		if (isNaN) {
			return NaN;
		}

		return new ValorNumerico(-real, -imaginario);
	}
	public void setRand(){
		this.real = Math.random();
		this.imaginario = Math.random();
	}
	public void setNull(){
		this.real = 0;
		this.imaginario = 0;
	}

	public static boolean isSign(String sign) {
		if (sign.length() == 1) {
			if (sign.charAt(0) == '+' || sign.charAt(0) == '-') {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static boolean isNumber(String number) {
		for (int i = 0; i < number.length(); i++) {
			char letra = number.charAt(i);
			if (!(letra == '.' || (letra >= '0' && letra <= '9'))) {
				return false;
			}
		}
		return true;
	}

	public static String[] spliter(String txt) {
		ArrayList<String> tokens = new ArrayList<String>();
		int pos = 0;
		boolean analiz = true;
		for (int i = 0; i < txt.length(); i++) {
			for (int j = 0; j < TOKENS.length; j++) {
				analiz = true;
				String token = TOKENS[j];
				for (int k = 0; k < token.length() && analiz; k++) {
					if (txt.charAt(i + k) != token.charAt(k)) {
						analiz = false;
					}
				}
				if (analiz) {// Se ha encontrado el token
					tokens.add(txt.substring(pos, i));
					tokens.add(txt.substring(i, i + 1));
					pos = i + 1;
				}
			}
		}
		tokens.add(txt.substring(pos, txt.length()));
		int posF = tokens.size();
		for (int i = 0; i < posF;) {
			if (tokens.get(i).equals("") || tokens.get(i).equals(" ")) {
				tokens.remove(i);
				posF = tokens.size();
			} else {
				i++;
			}
		}
		String[] ret = new String[tokens.size()];
		for (int i = 0; i < tokens.size(); i++) {
			ret[i] = tokens.get(i);
		}

		return ret;
	}

	public static void checkNotNull(ValorNumerico val) throws ValorNumericoException {
		if (val.equals(ValorNumerico.ZERO)) {
			throw new ValorNumericoException(ValorNumericoException.generarErrorDenominadorNulo());
		}
	}
}
