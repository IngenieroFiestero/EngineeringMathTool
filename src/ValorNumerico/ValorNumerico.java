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

public class ValorNumerico implements Cloneable,Serializable {
	private static final long serialVersionUID = -2942022796658879818L;
	private BigDecimal real;
	private BigDecimal imaginario;
	public static final String IMAGINARIO = "i";
	public static final String[] TOKENS = new String[] { " ", "+", "-", "i" };
	public static final ValorNumerico I = new ValorNumerico(0.0, 1.0);
	public static final ValorNumerico ONE = new ValorNumerico(1.0, 0.0);
	public static final ValorNumerico ZERO = new ValorNumerico(0.0, 0.0);
	public static final ValorNumerico E = new ValorNumerico(Math.E);
	public static final ValorNumerico PI = new ValorNumerico(Math.PI);
	public static final String[] SIGNOS = new String[] {"","" ,"+"};
	public ValorNumerico(double real) {
		this(real, 0.0);
	}

	public ValorNumerico(double real, double imaginario) {
		this.real = new BigDecimal(real,ContextoMatematico.MATH_CONTEXT);
		this.imaginario = new BigDecimal(imaginario,ContextoMatematico.MATH_CONTEXT);
	}
	public ValorNumerico(BigDecimal real) {
		this.real = real;
		this.imaginario = BigDecimal.ZERO;
	}
	public ValorNumerico(BigDecimal real, BigDecimal imaginario) {
		this.real = real;
		this.imaginario = imaginario;
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
			}else if(valores[i].equals("i") || valores[i].equals("+") || valores[i].equals("-") || valores[i].equals(" ")){
			}else{
				valid = false;
			}
		}
		if(valores.length == 1 && valores[0].equals("i")){
			imagin = 1;
		}else{
			for (int i = 0; i < numeros.size(); i++) {
				int pos = numeros.get(i);
				if ((pos + 1) < valores.length && valores[pos + 1].equals(IMAGINARIO)) {
					// HEMOS ENCONTRADO EL NUMERO IMAGINARIO
					if ((pos - 1) >= 0 && isSign(valores[pos - 1])) {
						imagin = Double.parseDouble(valores[pos - 1] + valores[pos]);
					} else {
						imagin = Double.parseDouble(valores[pos]);
					}
				}else {
					if ((pos - 1) >= 0 && isSign(valores[pos - 1])) {
						real = Double.parseDouble(valores[pos - 1] + valores[pos]);
					} else {
						real = Double.parseDouble(valores[pos]);
					}
				}
			}
		}
		if(!valid){
			throw new ValorNumericoException("Not Valid");
		}
		this.real = new BigDecimal(real,ContextoMatematico.MATH_CONTEXT);
		this.imaginario = new BigDecimal(imagin,ContextoMatematico.MATH_CONTEXT);
	}

	public String toString() {
		if (this.imaginario.doubleValue() == 0) {
			return this.real.toString();
		} else if (this.real.doubleValue() == 0 && this.imaginario.doubleValue() == 1) {
			return "i";
		}else if(this.real.doubleValue() == 0){
			return this.imaginario.toString() + "i";
		} else {
			return SIGNOS[this.real.signum()+1]+this.real.toString() + " " +SIGNOS[this.imaginario.signum()+1]+ this.imaginario.toString() + "i";
		}
	}

	public BigDecimal getReal() {
		return this.real;
	}

	public BigDecimal getImaginario() {
		return this.imaginario;
	}

	public ValorNumerico add(ValorNumerico val) {
		return new ValorNumerico(this.real.add(val.getReal()), this.imaginario.add(val.getImaginario()));
	}

	public ValorNumerico substract(ValorNumerico val) {
		return new ValorNumerico(this.real.subtract(val.getReal()), this.imaginario.subtract(val.getImaginario()));
	}

	public ValorNumerico conjugate() {
		return new ValorNumerico(this.real, this.imaginario.negate());
	}

	public ValorNumerico divide(ValorNumerico divisor) throws ValorNumericoException {
		checkNotNull(divisor);
		BigDecimal denominador = (divisor.getReal().multiply(divisor.getReal())
				.add(divisor.getImaginario().multiply(divisor.getImaginario())));
		return new ValorNumerico((this.real.multiply(divisor.getReal())
				.add(this.imaginario.multiply(divisor.getImaginario()))).divide(denominador),
				(this.real.multiply(divisor.getImaginario())
						.add(this.imaginario.multiply(divisor.getReal()))).divide(denominador));
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (other instanceof ValorNumerico) {
			ValorNumerico c = (ValorNumerico) other;
			return ((BigDecimal)((ValorNumerico)other).getReal()).compareTo(this.real) == 0 &&
					((BigDecimal)((ValorNumerico)other).getImaginario()).compareTo(this.imaginario) == 0;
		}
		return false;
	}

	public ValorNumerico multiply(ValorNumerico factor) throws ValorNumericoException {
		checkNotNull(factor);
		return new ValorNumerico(real.multiply(factor.getReal()).subtract(imaginario.multiply(factor.getImaginario())),
				real.multiply(factor.getImaginario()).add(imaginario.multiply(factor.getReal())));
	}
	public Object clone(){
		return new ValorNumerico(this.real,this.imaginario);
	}
	public ValorNumerico negate() {
		return new ValorNumerico(real.negate(), imaginario.negate());
	}
	public void setRand(){
		this.real = new BigDecimal(Math.random(),ContextoMatematico.MATH_CONTEXT);
		this.imaginario = new BigDecimal(Math.random(),ContextoMatematico.MATH_CONTEXT);
	}
	public void setNull(){
		this.real = BigDecimal.ZERO;
		this.imaginario = BigDecimal.ZERO;
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
	public ValorNumerico modulo(ValorNumerico val) throws ValorNumericoException{
		if(val.getImaginario().compareTo(BigDecimal.ZERO) != 0 || imaginario.compareTo(BigDecimal.ZERO) != 0){
			throw new ValorNumericoException("Arguments must be real");
		}else{
			BigDecimal rem = this.real.divideToIntegralValue(val.getReal());
			return new ValorNumerico(returnAproxNumber(this.real.subtract(rem.multiply(val.getReal()))));
		}
	}
	/**
	 * Recorre la cadena y elimina zeros de gratis. Es muy lento hay que buscar una forma mejor o utilizarlo en el ContextoMatematico
	 * @param bd
	 * @return
	 */
	private BigDecimal returnAproxNumber(BigDecimal bd){
		String cadena = bd.toString();
		int firstZero = 0;
		int lastZero = 0;
		int count = 0;
		int lengthMin = Math.max((int) (cadena.length()*0.05),1);
		for (int i = 0; i < cadena.length(); i++) {
			if(cadena.charAt(i) == '0'){
				if(count == 0){
					firstZero = i;
				}
				count++;
			}else{
				if(count >= lengthMin){
					lastZero = i+1;
					return new BigDecimal(cadena.substring(0,firstZero));
				}else{
					count = 0;
				}
			}
		}
		return bd;
	}
	/**
	 * Utilidad para trabajar con divisores, puesto que una de las variables que se guardan son los divisores
	 * @param num
	 * @return
	 */
	public static long[] divisores(double num){
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
	public int compareTo(ValorNumerico val){
		return absolute().compareTo(val.absolute());
	}
	public BigDecimal absolute(){
		return new BigDecimal(Math.sqrt(real.multiply(real).add(imaginario.multiply(imaginario)).doubleValue()),ContextoMatematico.MATH_CONTEXT);
	}
	public static void checkNotNull(ValorNumerico val) throws ValorNumericoException {
		if (val.equals(ValorNumerico.ZERO)) {
			throw new ValorNumericoException(ValorNumericoException.generarErrorDenominadorNulo());
		}
	}
	public static ValorNumerico load(InputStream is) throws IOException{
		DataInputStream dis = new DataInputStream(is);
		BigDecimal real = new BigDecimal(dis.readUTF(),ContextoMatematico.MATH_CONTEXT);
		BigDecimal imaginario = new BigDecimal(dis.readUTF(),ContextoMatematico.MATH_CONTEXT);
		dis.close();
		return new ValorNumerico(real,imaginario);
	}
	public void save(OutputStream os) throws IOException{
		DataOutputStream dos = new DataOutputStream(os);
		//Escribir primero el tipo de informacion que vamos a almacenar
		dos.writeUTF(real.toString());
		dos.writeUTF(imaginario.toString());
		dos.close();//Siempre mandar la informacion directamente al OutputStream al acabar
	}
	public byte[] getBytes() throws IOException{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		this.save(baos);
		return baos.toByteArray();
	}
	public ValorNumerico sqrt() throws ValorNumericoException{
		if(imaginario.compareTo(BigDecimal.ZERO) != 0){
			throw new ValorNumericoException(ValorNumericoException.generarNotSupported("sqrt()"));
		}
		if(real.compareTo(BigDecimal.ZERO) == 1){
			return new ValorNumerico(sqrt(real));
		}else if(real.compareTo(BigDecimal.ZERO) == -1){
			//Si el real es negativo entonces la raiz cuadrada es compleja
			return new ValorNumerico(BigDecimal.ZERO,sqrt(real.negate()));
		}else{
			return ZERO;
		}
	}
	private static BigDecimal sqrt(BigDecimal value) {
		BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
	    return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
	}
}
