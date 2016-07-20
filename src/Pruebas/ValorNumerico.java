package Pruebas;

import java.util.ArrayList;
import java.util.LinkedList;

import ValorNumerico.ValorNumericoException;

public class ValorNumerico{
	public static final String[] TOKENS = new String[] { " ", "+", "-", "i" };
	/**
	 * Esta clase nos ayudara a no perder tanta precision usando doubles pues 5.1 - 2 = 0.09999999993
	 */
	private long real;
	private long realDenominador;
	private long imaginario;
	private long imaginarioDenominador;
	
	public ValorNumerico(){
		this.real = new Long(0);
		this.imaginario = new Long(0);
		this.realDenominador = new Long(1);
		this.imaginarioDenominador = new Long(1);
	}
	public ValorNumerico(long real,long imaginario){
		this.real = real;
		this.imaginario = imaginario;
		this.realDenominador = new Long(1);
		this.imaginarioDenominador = new Long(1);
	}
	public ValorNumerico(long real,long imaginario,long realDen,long imagDen){
		this.real = real;
		this.imaginario = imaginario;
		this.realDenominador = realDen;
		this.imaginarioDenominador = imagDen;
	}
	public ValorNumerico(String numero) throws ValorNumericoException {
		String[] valores = spliter(numero);
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		boolean valid = true;
		String imagin = "";
		String real = "";
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
			imagin = "";
		} else {
			for (int i = 0; i < numeros.size(); i++) {
				int pos = numeros.get(i);
				if ((pos + 1) < valores.length && valores[pos + 1].equals("i")) {
					// HEMOS ENCONTRADO EL NUMERO IMAGINARIO
					if ((pos - 1) >= 0 && isSign(valores[pos - 1])) {
						imagin = valores[pos - 1] + valores[pos];
					} else {
						imagin = valores[pos];
					}
				} else {
					if ((pos - 1) >= 0 && isSign(valores[pos - 1])) {
						real = valores[pos - 1] + valores[pos];
					} else {
						real = valores[pos];
					}
				}
			}
		}
		if (!valid) {
			throw new ValorNumericoException("Not Valid");
		}
		long[]rel = getLongFromDouble(real);
		long[]img = getLongFromDouble(imagin);
		this.real = rel[0];
		this.imaginario = img[0];
		this.realDenominador = rel[1];
		this.imaginarioDenominador = img[1];
	}
	public String toString(){
		return ((this.realDenominador == 1)? this.real :this.real + "/"+this.realDenominador) +" " +(this.imaginario == 0 ? "":((this.imaginarioDenominador == 1)? this.imaginario + "i":this.imaginario + "/"+this.imaginarioDenominador + "i") );
	}
	public ValorNumerico suma(ValorNumerico val){
		try{
			long realDen = mcm(this.realDenominador, val.realDenominador);
			long real = (this.real)*(realDen/this.realDenominador) + (val.real)*(realDen/val.realDenominador);
			long imagDen = mcm(this.imaginarioDenominador, val.imaginarioDenominador);
			long imag = (this.imaginario)*(imagDen/this.imaginarioDenominador) + (val.imaginario)*(imagDen/val.imaginarioDenominador);
			return new ValorNumerico(real,imag,realDen,imagDen);
		}catch(Exception e){
			e.printStackTrace();
			simplificarReal();
			simplificarImaginario();
			val.simplificarReal();
			val.simplificarImaginario();
			long realDen = mcm(this.realDenominador, val.realDenominador);
			long real = (this.real)*(realDen/this.realDenominador) + (val.real)*(realDen/val.realDenominador);
			long imagDen = mcm(this.imaginarioDenominador, val.imaginarioDenominador);
			long imag = (this.imaginario)*(imagDen/this.imaginarioDenominador) + (val.imaginario)*(imagDen/val.imaginarioDenominador);
			return new ValorNumerico(real,imag,realDen,imagDen);
		}
	}
	public ValorNumerico resta(ValorNumerico val){
		try{
			long realDen = mcm(this.realDenominador, val.realDenominador);
			long real = (this.real)*(realDen/this.realDenominador) - (val.real)*(realDen/val.realDenominador);
			long imagDen = mcm(this.imaginarioDenominador, val.imaginarioDenominador);
			long imag = (this.imaginario)*(imagDen/this.imaginarioDenominador) - (val.imaginario)*(imagDen/val.imaginarioDenominador);
			return new ValorNumerico(real,imag,realDen,imagDen);
		}catch(Exception e){
			simplificarReal();
			simplificarImaginario();
			val.simplificarReal();
			val.simplificarImaginario();
			long realDen = mcm(this.realDenominador, val.realDenominador);
			long real = (this.real)*(realDen/this.realDenominador) - (val.real)*(realDen/val.realDenominador);
			long imagDen = mcm(this.imaginarioDenominador, val.imaginarioDenominador);
			long imag = (this.imaginario)*(imagDen/this.imaginarioDenominador) - (val.imaginario)*(imagDen/val.imaginarioDenominador);
			return new ValorNumerico(real,imag,realDen,imagDen);
		}
	}
	public void simplificarReal(){
		long[] sim = simplificarNum(real, realDenominador);
		this.real = sim[0];
		this.realDenominador = sim[1];
	}
	public void simplificarImaginario(){
		long[] sim = simplificarNum(imaginario, imaginarioDenominador);
		this.imaginario = sim[0];
		this.imaginarioDenominador = sim[1];
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
	public static long[] getLongFromDouble(String txt){
		if(txt.length() > 1 && (txt.charAt(0) == '+' || txt.charAt(0) == '-')){
			txt = txt.substring(1);
		}
		int integerPlaces = txt.indexOf('.');
		int decimalPlaces = 0;
		if (integerPlaces != -1) {
			decimalPlaces = txt.length() - integerPlaces - 1;
			
		}
		long num = 1;
		if(decimalPlaces >0){
			num = new Long(txt.substring(0, integerPlaces) + txt.substring(integerPlaces+1));
		}else if(txt.length() == 0){
			num = 0;
		}else{
			num = new Long(txt);
		}
		long den = 1;
		for (int i = 0; i < decimalPlaces; i++) {
			den = den*10;
		}
		return new long[]{num,den};
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
	public static long mcm(long num1,long num2){
		LinkedList<Long> div1 = divisores(num1);
		LinkedList<Long> div2 = divisores(num2);
		long ret = 1;
		int lng = div1.size();
		for (int i = 0; i < lng; i++) {
			Long val1 = div1.getFirst();
			ret = ret*val1;
			div2.remove(val1);
			div1.remove(val1);
		}
		lng = div2.size();
		for (int i = 0; i < lng; i++) {
			Long val1 = div2.getFirst();
			ret = ret*val1;
			div2.remove(val1);
		}
		return ret;
	}
	
	public static LinkedList<Long> divisores(long num){
		LinkedList<Long> divisores = new LinkedList<Long>();
		long numAux = num;
		boolean salir = false;
		for (long i = 2; i <= num/2 && !salir; i++) {
			while(numAux%i==0 && !salir){
                if(num%i == 0){
    				divisores.add(i);
    				numAux=numAux/i;
    			}
                
                 if(numAux==1){
                    salir = true;
                }
                 
            }
			if(i > 2){
				i++;
			}
			
		}
		return divisores;
	}
	public static long[] simplificarNum(long num, long denNum){
		LinkedList<Long> div1 = divisores(num);
		LinkedList<Long> div2 = divisores(denNum);
		int lng = div1.size();
		for (int i = 0; i < lng; i++) {
			Long val1 = div1.getFirst();
			if(div2.remove(val1)){
				div1.remove(val1);
			}
		}
		long ret1 = 1;
		if(num == 0){
			ret1 = 0;
		}
		long ret2 = 1;
		for (int i = 0; i < div1.size(); i++) {
			ret1=ret1*div1.get(i);
		}
		for (int i = 0; i < div2.size(); i++) {
			ret2=ret2*div2.get(i);
		}
		return new long[]{ret1,ret2};
	}
}
