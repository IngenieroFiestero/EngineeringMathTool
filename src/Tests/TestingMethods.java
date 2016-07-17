package Tests;

import Matriz.Matriz;
import Operaciones.Operando;
import ValorNumerico.ValorNumerico;
import ValorNumerico.ValorNumericoException;

public class TestingMethods {
	public static boolean testCompletoValorNumerico(){
		boolean test = true;
		try{
			ValorNumerico val = new ValorNumerico("+5.2-2.5i");
			if(!val.equals(new ValorNumerico(5.2,-2.5))){
				test=false;
			}
			val = new ValorNumerico("-5.2 2.5i");
			if(!val.equals(new ValorNumerico(-5.2,2.5))){
				test=false;
			}
			val = new ValorNumerico("-5.2 ");
			if(!val.equals(new ValorNumerico(-5.2,0))){
				test=false;
			}
			val = new ValorNumerico("4 -2.5i ");
			if(!val.equals(new ValorNumerico(4,-2.5))){
				test=false;
			}
			ValorNumerico val2 = new ValorNumerico("6i");
			ValorNumerico val3 = new ValorNumerico("2i");
			ValorNumerico res = val.divide(val2);
		} catch (ValorNumericoException e) {
			test=false;
		}
		return test;
	}
	public static boolean testCompletoMatriz(){
		boolean test = true;
		System.out.println("-----Test Matriz-----");
		try{
			Matriz mat = new Matriz("1,2,3");
			System.out.println(mat.toString());
			mat = new Matriz("1,2,3;4,5,2 - 6i");
			System.out.println(mat.toString());
			System.out.println(mat.toStringInLine());
			Matriz mat2 = new Matriz("1,2,3;4,5,2");
			Matriz res = mat2.sumar(mat);
			System.out.println(res);
			System.out.println("Obtener Submatriz de " + mat2.toStringInLine());
			Matriz sub = mat2.getSubMatriz(new int[]{0,1,1}, new int[]{1,1,2});
			System.out.println(sub);
			if(!sub.equals(new Matriz("2,3;5,2"))){
				test = false;
			}
		}catch(Exception e){
			test = false;
		}
		return test;
	}
	public static boolean testCompletoOperando(){
		boolean test = true;
		System.out.println("-----Test Operando-----");
		try{
			Matriz mat = new Matriz("1,2,3;4,5,2 - 6i");
			Operando op = new Operando(mat);
			Operando op2 = new Operando("1,2,3;4,5,2 - 6i");
			if(op.getTipo() != op2.getTipo()){
				test = false;
			}
		}catch(Exception e){
			test = false;
		}
		return test;
	}

}
