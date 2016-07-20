package Polinomios;

import Matriz.Matriz;
import Matriz.MatrizException;

public class PolTest {
	public static void main(String[] args){
		try {
			Polinomio pol1 = new Polinomio(new Matriz("[1,1,1]"));
			Polinomio pol2 = new Polinomio(new Matriz("[1,1]"));
			System.out.println(pol1.sustract(pol2));
			System.out.println(pol1.multiply(pol2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
