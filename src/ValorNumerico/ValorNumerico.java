package ValorNumerico;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

import MathTool.ContextoMatematico;
import MathTool.MathInterprete;
import MathTool.MathSerialize;
import Variable.Variable;

public class ValorNumerico implements Cloneable, Serializable {
	private static final long serialVersionUID = -2942022796658879818L;
	private double real;
	private double imaginario;
	private double denominador;
	public static final String IMAGINARIO = "i";
	public static final String[] TOKENS = new String[] { " ", "+", "-", "i" };
	public static final ValorNumerico I = new ValorNumerico(0.0, 1.0);
	public static final ValorNumerico ONE = new ValorNumerico(1.0, 0.0);
	public static final ValorNumerico ZERO = new ValorNumerico(0.0, 0.0);
	public static final ValorNumerico E = new ValorNumerico(Math.E);
	public static final ValorNumerico PI = new ValorNumerico(Math.PI);
	public static final String[] SIGNOS = new String[] { "", "", "+" };

	public ValorNumerico(double real) {
		this(real, 0.0);
	}

	public ValorNumerico(double real, double imaginario) {
		this.real = real;
		this.imaginario = imaginario;
		this.denominador = 1;
	}

	public ValorNumerico(double real, double imaginario, double denominador) {
		this.real = real;
		this.imaginario = imaginario;
		this.denominador = denominador;
	}

	public ValorNumerico(String numero) throws ValorNumericoException {
		String[] valores = spliter(numero);
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		boolean valid = true;
		double imagin = 0;
		double real = 0;
		for (int i = 0; i < valores.length; i++) {
			if (isNumber(valores[i])) {
				numeros.add(new Integer(i));
			} else if (valores[i].equals("i") || valores[i].equals("+") || valores[i].equals("-")
					|| valores[i].equals(" ")) {
			} else {
				valid = false;
			}
		}
		if (valores.length == 1 && valores[0].equals("i")) {
			imagin = 1;
		} else {
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
		}
		if (!valid) {
			throw new ValorNumericoException("Not Valid");
		}
		this.real = real;
		this.imaginario = imagin;
		this.denominador = 1;
	}

	public String toString() {
		if(denominador == 1){
			if (this.imaginario == 0) {
				return this.real + "";
			} else if (this.real == 0 && this.imaginario == 1) {
				return "i";
			} else if (this.real == 0) {
				return this.imaginario + "i";
			} else {
				return this.real + " " + (this.imaginario > 0 ? "+" : "") + this.imaginario + "i";
			}
		}else{
			if (this.imaginario == 0) {
				return this.real/denominador + "";
			} else if (this.real == 0 && this.imaginario == 1) {
				return (1/denominador) + "i";
			} else if (this.real == 0) {
				return (this.imaginario/denominador) + "i";
			} else {
				return this.real/denominador + " " + (this.imaginario > 0 ? "+" : "") + this.imaginario/denominador + "i";
			}
		}
		
	}

	public double getReal() {
		return this.real;
	}

	public double getImaginario() {
		return this.imaginario;
	}

	public double getDenominador() {
		return this.denominador;
	}

	public ValorNumerico add(ValorNumerico val) {
		if (denominador == 1 && val.getDenominador() == 1) {
			return new ValorNumerico(this.real + val.getReal(), this.imaginario + val.getImaginario(),
					this.denominador);
		} else {
			return new ValorNumerico(this.real * val.getDenominador() + val.getReal() * this.denominador,
					this.imaginario * val.getDenominador() + val.getImaginario() * this.denominador,
					this.denominador * val.getDenominador());
		}
	}

	public ValorNumerico substract(ValorNumerico val) {
		if (denominador == 1 && val.getDenominador() == 1) {
			return new ValorNumerico(this.real - val.getReal(), this.imaginario + val.getImaginario(),
					this.denominador);
		} else {
			return new ValorNumerico(this.real * val.getDenominador() - val.getReal() * this.denominador,
					this.imaginario * val.getDenominador() - val.getImaginario() * this.denominador,
					this.denominador * val.denominador);
		}
	}

	public ValorNumerico conjugate() {
		return new ValorNumerico(this.real, -this.imaginario, denominador);
	}

	public ValorNumerico divide(ValorNumerico divisor) throws ValorNumericoException {
		if((divisor.real == 0 && divisor.imaginario == 0)){
			return new ValorNumerico(this.real*divisor.denominador,
					this.imaginario*divisor.denominador,0);
		}
		if (this.denominador == 1 && divisor.getDenominador() == 1) {
			return new ValorNumerico((this.real * divisor.getReal() - this.imaginario * divisor.getImaginario()),
					this.real * divisor.getImaginario() + this.imaginario * divisor.getReal(),
					(divisor.getReal() * divisor.getReal()
							+ divisor.getImaginario() * divisor.getImaginario()));

		} else {
			return new ValorNumerico(
					divisor.getDenominador()
							* (this.real * divisor.getReal() - this.imaginario * divisor.getImaginario()),
					divisor.getDenominador()
							* (this.real * divisor.getImaginario() + this.imaginario * divisor.getReal()),
					this.denominador * (divisor.getReal() * divisor.getReal()
							+ divisor.getImaginario() * divisor.getImaginario()));
		}
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other instanceof ValorNumerico) {
			ValorNumerico c = (ValorNumerico) other;
			return ((ValorNumerico) other).getReal() == (this.real)
					&& ((ValorNumerico) other).getImaginario() == (this.imaginario)
					&& ((ValorNumerico) other).getDenominador() == (this.denominador);
		}
		return false;
	}

	public ValorNumerico multiply(ValorNumerico factor) throws ValorNumericoException {
		return new ValorNumerico(real * factor.real - imaginario * factor.imaginario,
				real * factor.imaginario + imaginario * factor.real,
				denominador*factor.denominador);
	}

	public Object clone() {
		return new ValorNumerico(this.real, this.imaginario);
	}

	public ValorNumerico negate() {
		return new ValorNumerico(-real, -imaginario);
	}

	public void setRand() {
		this.real = Math.random();
		this.imaginario = Math.random();
		this.denominador = 1;
	}

	public void setNull() {
		this.real = 0;
		this.imaginario = 0;
		this.denominador = 1;
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

	public ValorNumerico modulo(ValorNumerico val) throws ValorNumericoException {
		if (val.getImaginario() != 0 || imaginario!= 0) {
			throw new ValorNumericoException("Arguments must be real");
		} else {
			return new ValorNumerico((this.real/denominador)/(val.getReal()/this.denominador));
		}
	}

	/**
	 * Recorre la cadena y elimina zeros de gratis. Es muy lento hay que buscar
	 * una forma mejor o utilizarlo en el ContextoMatematico
	 * 
	 * @param bd
	 * @return
	 */
	private BigDecimal returnAproxNumber(BigDecimal bd) {
		String cadena = bd.toString();
		int firstZero = 0;
		int lastZero = 0;
		int count = 0;
		int lengthMin = Math.max((int) (cadena.length() * 0.05), 1);
		for (int i = 0; i < cadena.length(); i++) {
			if (cadena.charAt(i) == '0') {
				if (count == 0) {
					firstZero = i;
				}
				count++;
			} else {
				if (count >= lengthMin) {
					lastZero = i + 1;
					return new BigDecimal(cadena.substring(0, firstZero));
				} else {
					count = 0;
				}
			}
		}
		return bd;
	}

	/**
	 * Utilidad para trabajar con divisores, puesto que una de las variables que
	 * se guardan son los divisores
	 * 
	 * @param num
	 * @return
	 */
	public static long[] divisores(double num) {
		ArrayList<Long> lista = new ArrayList<Long>();
		long i;
		for (i = 2; i <= num / 2; i++) {
			if (num % i == 0) {
				lista.add(i);
			}
		}
		long[] list = new long[lista.size()];
		for (int j = 0; j < list.length; j++) {
			list[j] = lista.get(j);
		}
		return list;
	}

	public int compareTo(ValorNumerico val) {
		double abs1= absolute();
		double abs2 = val.absolute();
		if(abs1>abs2){
			return 1;
		}else if(abs1==abs2){
			return 0;
		}else{
			return -1;
		}
	}

	public double absolute() {
		double valReal =(real*real)+(imaginario*imaginario);
		double den = denominador*denominador;
		return Math.sqrt(valReal/den);
	}

	public static void checkNotNull(ValorNumerico val) throws ValorNumericoException {
		if (val.equals(ValorNumerico.ZERO)) {
			throw new ValorNumericoException(ValorNumericoException.generarErrorDenominadorNulo());
		}
	}

	public static ValorNumerico load(InputStream is) throws IOException {
		DataInputStream dis = new DataInputStream(is);
		double real = dis.readDouble();
		double imaginario = dis.readDouble();
		double denominador = dis.readDouble();
		dis.close();
		return new ValorNumerico(real, imaginario,denominador);
	}

	public void save(OutputStream os) throws IOException {
		DataOutputStream dos = new DataOutputStream(os);
		// Escribir primero el tipo de informacion que vamos a almacenar
		dos.writeDouble(real);
		dos.writeDouble(imaginario);
		dos.writeDouble(denominador);
		dos.close();// Siempre mandar la informacion directamente al
					// OutputStream al acabar
	}

	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.save(baos);
		return baos.toByteArray();
	}

	public ValorNumerico sqrt() throws ValorNumericoException {
		if (imaginario != 0) {
			throw new ValorNumericoException(ValorNumericoException.generarNotSupported("sqrt()"));
		}
		if (real >0) {
			return new ValorNumerico(Math.sqrt(real),0,Math.sqrt(denominador));
		} else if (real < 0 ) {
			// Si el real es negativo entonces la raiz cuadrada es compleja
			return new ValorNumerico(0, Math.sqrt(-real));
		} else {
			return ZERO;
		}
	}

	private static BigDecimal sqrt(BigDecimal value) {
		BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
		return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
	}

	public ValorNumerico sin() throws ValorNumericoException {
		if (imaginario  != 0) {
			throw new ValorNumericoException(ValorNumericoException.generarNotSupported("sin()"));
		}
		if(real == 0){
			return new ValorNumerico(0.0);
		}
		if(real%Math.PI == 0){
			int num = (int) (real/Math.PI);
			denominador = denominador / num;
			real = real/num;
			num = (int) (real/Math.PI);
			if(denominador%1 == 0){
				int den = (int) denominador;
				if(num == 1){
					switch(den){
					case 6:
						return new ValorNumerico(1.0,0,2);
					case 4:
						return new ValorNumerico(Math.sqrt(2),0,2);
					case 3:
						return new ValorNumerico(Math.sqrt(3),0,2);
					case 1:
						return new ValorNumerico(0);
					default:
						break;
					}
				}
				if(den == 2){
					if(num%4 == 1){
						return new ValorNumerico(1);
					}else if(num%4 == 3){
						return new ValorNumerico(-1);
					}
				}
				
			}
			
		}
		return new ValorNumerico(Math.sin(real/denominador));
	}

	public ValorNumerico asin() throws ValorNumericoException {
		if (imaginario != 0) {
			throw new ValorNumericoException(ValorNumericoException.generarNotSupported("sin()"));
		}
		return new ValorNumerico(Math.asin(real/denominador));
	}

	public ValorNumerico cos() throws ValorNumericoException {
		if (imaginario != 0) {
			throw new ValorNumericoException(ValorNumericoException.generarNotSupported("cos()"));
		}
		if(real == 0){
			return new ValorNumerico(0.0);
		}
		if(real%Math.PI == 0){
			int num = (int) (real/Math.PI);
			denominador = denominador / num;
			real = real/num;
			num = (int) (real/Math.PI);
			if(denominador%1 == 0){
				int den = (int) denominador;
				if(den == 1){
					if(num%2 == 1){
						return new ValorNumerico(-1);
					}else if(num%2 == 0){
						return new ValorNumerico(1);
					}
				}
				if(num == 1){
					switch(den){
					case 6:
						return new ValorNumerico(Math.sqrt(3),0,2);
					case 4:
						return new ValorNumerico(1,0,Math.sqrt(2));
					case 3:
						return new ValorNumerico(1,0,2);
					default:
						break;
					}
				}
				if(den == 2){
					if(num%4 == 1 || num%4 == 3){
						return new ValorNumerico(0);
					}
				}	
			}
			
		}
		return new ValorNumerico(Math.cos(real/denominador));
	}

	public ValorNumerico acos() throws ValorNumericoException {
		if (imaginario != 0) {
			throw new ValorNumericoException(ValorNumericoException.generarNotSupported("cos()"));
		}
		return new ValorNumerico(Math.acos(real/denominador));
	}

	public ValorNumerico tan() throws ValorNumericoException {
		if (imaginario != 0) {
			throw new ValorNumericoException(ValorNumericoException.generarNotSupported("tan()"));
		}
		return new ValorNumerico(Math.tan(real/denominador));
	}

	public ValorNumerico atan() throws ValorNumericoException {
		if (imaginario != 0) {
			throw new ValorNumericoException(ValorNumericoException.generarNotSupported("tan()"));
		}
		System.out.println("ATAN: " + real + " " + imaginario + " " + denominador);
		return new ValorNumerico(Math.atan(real/denominador));
	}
}
