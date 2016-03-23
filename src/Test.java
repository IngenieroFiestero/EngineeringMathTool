import java.math.BigDecimal;
import java.math.BigInteger;

public class Test {
	/*
	 * Sistema de testeo
	 */
	public static void main(String[] args) {
		testBooleanos();
		testEnteros();
		testRacional();
		testReales();
		testComplejo();
		Matriz mat = new Matriz(new int[]{2,3});
		mat.set(new int[]{1,1 }, new ValorNumerico(new BigInteger("2")));
		System.out.println(mat);
		Matriz mat2 = new Matriz(new int[]{3,3});
		mat2.set(new int[]{1,1 }, new ValorNumerico(new BigInteger("3")));
		System.out.println(mat2);
		try {
			Matriz mat3 = Matriz.multiplicar(mat, mat2);
			System.out.println(mat3);
		} catch (MatrizException | ValorNumericoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mat = new Matriz(new int[]{4,4});
		
		mat.setRand();
		Matriz mat3 = (Matriz) mat.clone();//No clona
		mat.set(new int[]{1,0 }, new ValorNumerico(new Complejo(new BigDecimal("2"),new BigDecimal("5"))));
		System.out.println(mat3);
		System.out.println(mat);
		try {
			System.out.println(Matriz.sumar(mat, mat3));
		} catch (MatrizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValorNumericoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static boolean testBooleanos(){
		Boolean entero = new Boolean(true);
		ValorNumerico val = new ValorNumerico<Boolean>(new Boolean(true));
		if(val.getTipo() == ValorNumerico.TIPO_BOOLEANO
				&& (((Boolean)val.getValor()).compareTo(entero) == 0)){
			System.out.println("Test Booleanos superado");
			return true;
		}else{
			System.out.println("Test Booleanos NO superado");
			return false;
		}
	}
	public static boolean testReales(){
		String val1 = "18";
		BigDecimal entero = new BigDecimal(val1);
		ValorNumerico val = new ValorNumerico<BigDecimal>(new BigDecimal(val1));
		if(val.getTipo() == ValorNumerico.TIPO_REAL
				&& (((BigDecimal)val.getValor()).compareTo(entero) == 0)){
			System.out.println("Test Enteros superado");
			return true;
		}else{
			System.out.println("Test Enteros NO superado");
			return false;
		}
	}
	public static boolean testEnteros(){
		String val1 = "18";
		BigInteger entero = new BigInteger(val1);
		ValorNumerico val = new ValorNumerico<BigInteger>(new BigInteger(val1));
		if(val.getTipo() == ValorNumerico.TIPO_ENTERO
				&& (((BigInteger)val.getValor()).compareTo(entero) == 0)){
			System.out.println("Test Enteros superado");
			return true;
		}else{
			System.out.println("Test Enteros NO superado");
			return false;
		}
	}
	public static boolean testComplejo(){
		String val1 = "18";
		String val2 = "12";
		Complejo complejo = new Complejo(new BigDecimal(val1),new BigDecimal(val2));
		ValorNumerico val = new ValorNumerico<Complejo>(new Complejo(new BigDecimal(val1),new BigDecimal(val2)));
		if(val.getTipo() == ValorNumerico.TIPO_COMPLEJO 
				&& (Complejo.equals((Complejo)val.getValor(), complejo))){
			System.out.println("Test Complejos superado");
			return true;
		}else{
			System.out.println("Test Complejos NO superado");
			return false;
		}
	}
	public static boolean testRacional(){
		String val1 = "18";
		String val2 = "12";
		Racional complejo = new Racional(new BigInteger(val1),new BigInteger(val2));
		ValorNumerico val = new ValorNumerico<Racional>(new Racional(new BigInteger(val1),new BigInteger(val2)));
		if(val.getTipo() == ValorNumerico.TIPO_RACIONAL
				&& (Racional.equals((Racional)val.getValor(), complejo))){
			System.out.println("Test Racionales superado");
			return true;
		}else{
			System.out.println("Test Racionales NO superado");
			return false;
		}
	}

}
