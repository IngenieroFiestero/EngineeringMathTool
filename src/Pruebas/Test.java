package Pruebas;

import ValorNumerico.ValorNumericoException;

public class Test {
	
	public static void main(String[] args){
		ValorNumerico val = null;
		try {
			val = new ValorNumerico("5.1");
		} catch (ValorNumericoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(val);
		ValorNumerico val2 = null;
		try {
			val2 = new ValorNumerico("2");
		} catch (ValorNumericoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		val.simplificarReal();
		System.out.println(val.toString());
		System.out.println("Resultado suma: " + val.resta(val2));
		ValorNumerico prueba = null;
		try {
			prueba = new ValorNumerico("1.0000001 5.111113i");
		} catch (ValorNumericoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(prueba);
	}
}
