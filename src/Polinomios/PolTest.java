package Polinomios;

import Matriz.Matriz;
import Matriz.MatrizException;
import ValorNumerico.ValorNumerico;

public class PolTest {
	public static void main(String[] args){
		try {
			Polinomio pol1 = new Polinomio(new Matriz("[2,2,2]"));
			Polinomio pol2 = new Polinomio(new Matriz("[1,1+i]"));
			//System.out.println(pol1.sustract(pol2));
			//System.out.println(pol1.multiply(pol2));
			ValorNumerico val = new ValorNumerico("3-i");
			System.out.println(val);
			//System.out.println(pol1.eval(val));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
