package Pruebas;

import ValorNumerico.ValorNumericoException;

public class Test {
	
	public static void main(String[] args){
		ValorNumerico val = new ValorNumerico(10,0,40,1);
		System.out.println(val);
		ValorNumerico val2 = new ValorNumerico(1,0,30,1);
		val.simplificarReal();
		System.out.println(val.toString());
		System.out.println("Resultado suma: " + val.suma(val2));
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
