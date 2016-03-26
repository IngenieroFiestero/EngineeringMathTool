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
		System.out.println("------------------------Matrices Suma-------------------------");
		testMatrizSuma();
		System.out.println("------------------------Matrices Inversa Compleja-------------------------");
		testMatrizInversa();
		System.out.println("------------------------Complejos Division-------------------------");
		Complejo comp1 = new Complejo(new BigDecimal("2"),new BigDecimal("1"));
		Complejo comp2 = new Complejo(new BigDecimal("1"),new BigDecimal("1"));
		try {
			ValorNumerico comp3 = ValorNumerico.dividir(new ValorNumerico(comp1), new ValorNumerico(comp2));
			System.out.println(comp3.getValor());
		} catch (ValorNumericoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void testMatrizSuma(){
		Matriz mat1 = new Matriz(new int[]{2,2});
		mat1.set(new int[]{0,0 }, new ValorNumerico(new BigDecimal("1")));
		mat1.set(new int[]{0,1 }, new ValorNumerico(new BigDecimal("2.2")));
		mat1.set(new int[]{1,0 }, new ValorNumerico(new BigDecimal("3")));
		mat1.set(new int[]{1,1 }, new ValorNumerico(new BigDecimal("4")));
		System.out.println(mat1);
		Matriz mat2 = new Matriz(new int[]{2,2});
		mat2.set(new int[]{0,0 }, new ValorNumerico(new BigDecimal("1")));
		mat2.set(new int[]{0,1 }, new ValorNumerico(new BigDecimal("2")));
		mat2.set(new int[]{1,0 }, new ValorNumerico(new BigDecimal("3")));
		mat2.set(new int[]{1,1 }, new ValorNumerico(new BigDecimal("4")));
		System.out.println(mat2);
		try {
			Matriz matTrans = Matriz.sumar(mat1, mat2);
			System.out.println(matTrans);
		} catch (MatrizException | ValorNumericoException e) {
			System.err.println(e);
		}
	}
	public static boolean testMatrizInversa(){
		Matriz mat2 = new Matriz(new int[]{3,3});
		mat2.set(new int[]{0,0 }, new ValorNumerico(new Complejo(new BigDecimal("0"),new BigDecimal("2"))));
		mat2.set(new int[]{0,1 }, new ValorNumerico(new Complejo(new BigDecimal("1"),new BigDecimal("0"))));
		mat2.set(new int[]{0,2 }, new ValorNumerico(new Complejo(new BigDecimal("1"),new BigDecimal("0"))));
		mat2.set(new int[]{1,0 }, new ValorNumerico(new Complejo(new BigDecimal("1"),new BigDecimal("0"))));
		mat2.set(new int[]{1,1 }, new ValorNumerico(new Complejo(new BigDecimal("1"),new BigDecimal("0"))));
		mat2.set(new int[]{1,2 }, new ValorNumerico(new Complejo(new BigDecimal("0"),new BigDecimal("0"))));
		mat2.set(new int[]{2,0 }, new ValorNumerico(new Complejo(new BigDecimal("1"),new BigDecimal("0"))));
		mat2.set(new int[]{2,1 }, new ValorNumerico(new Complejo(new BigDecimal("0"),new BigDecimal("0"))));
		mat2.set(new int[]{2,2 }, new ValorNumerico(new Complejo(new BigDecimal("1"),new BigDecimal("0"))));
		Matriz mat2res = new Matriz(new int[]{3,3});
		mat2res.set(new int[]{0,0 }, new ValorNumerico(new Complejo(new BigDecimal("-0.25"),new BigDecimal("-0.25"))));
		mat2res.set(new int[]{0,1 }, new ValorNumerico(new Complejo(new BigDecimal("0.25"),new BigDecimal("0.25"))));
		mat2res.set(new int[]{0,2 }, new ValorNumerico(new Complejo(new BigDecimal("0.25"),new BigDecimal("0.25"))));
		mat2res.set(new int[]{1,0 }, new ValorNumerico(new Complejo(new BigDecimal("0.25"),new BigDecimal("0.25"))));
		mat2res.set(new int[]{1,1 }, new ValorNumerico(new Complejo(new BigDecimal("0.75"),new BigDecimal("-0.25"))));
		mat2res.set(new int[]{1,2 }, new ValorNumerico(new Complejo(new BigDecimal("-0.25"),new BigDecimal("-0.25"))));
		mat2res.set(new int[]{2,0 }, new ValorNumerico(new Complejo(new BigDecimal("0.25"),new BigDecimal("0.25"))));
		mat2res.set(new int[]{2,1 }, new ValorNumerico(new Complejo(new BigDecimal("-0.25"),new BigDecimal("-0.25"))));
		mat2res.set(new int[]{2,2 }, new ValorNumerico(new Complejo(new BigDecimal("0.75"),new BigDecimal("-0.25"))));
		System.out.println(mat2);
		try {
			Matriz matTrans = (Matriz)InterfazMatriz.invertir(mat2);
			System.out.println(matTrans);
			if(matTrans.equals(mat2res)){
				System.out.println("Son iguales");
				return true;
			}else{
				System.err.println("Son diferentes");
				return false;
			}
		} catch (MatrizException | ValorNumericoException e) {
			System.err.println(e);
			return false;
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
			System.err.println("Test Booleanos NO superado");
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
			System.err.println("Test Enteros NO superado");
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
			System.err.println("Test Enteros NO superado");
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
			System.err.println("Test Complejos NO superado");
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
			System.err.println("Test Racionales NO superado");
			return false;
		}
	}

}
