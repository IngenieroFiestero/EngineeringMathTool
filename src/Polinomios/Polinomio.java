package Polinomios;

import Matriz.Matriz;
import Matriz.MatrizException;
import ValorNumerico.ValorNumerico;
import ValorNumerico.ValorNumericoException;

public class Polinomio {
	private Matriz polinomio;
	
	/**
	 * 1 +2x^2 +3x^3
	 * @param mat
	 * @throws MatrizException
	 */
	public Polinomio(Matriz mat) throws MatrizException{
		if(mat.dimensions()[0] != 1){
			throw new MatrizException("Not valid dimension");
		}
		System.out.println(mat);
		this.polinomio = mat;
	}
	public Matriz getPolinomio(){
		return this.polinomio;
	}
	public Polinomio add(Polinomio pol) throws MatrizException, ValorNumericoException{
		Matriz polMat = pol.getPolinomio();
		int polLength = polMat.dimensions()[1];
		int polThis = polinomio.dimensions()[1];
		Matriz ret = new Matriz(new int[]{1,Math.max(polLength,polThis)});
		if(polThis > polLength){
			for (int i = 0; i < polLength; i++) {
				ret.set(new int[]{0,i}, polMat.get(new int[]{0,i}));
			}
			ret = ret.sumar(polinomio);
		}else{
			for (int i = 0; i < polThis; i++) {
				ret.set(new int[]{0,i}, polinomio.get(new int[]{0,i}));
			}
			ret = ret.sumar(polMat);
		}
		return new Polinomio(ret);
	}
	public Polinomio sustract(Polinomio pol) throws MatrizException, ValorNumericoException{
		Matriz polMat = pol.getPolinomio();
		int polLength = polMat.dimensions()[1];
		int polThis = polinomio.dimensions()[1];
		Matriz ret = new Matriz(new int[]{1,Math.max(polLength,polThis)});
		if(polThis > polLength){
			//El mas pequeño se redimensiona
			for (int i = 0; i < polLength; i++) {
				ret.set(new int[]{0,i}, polMat.get(new int[]{0,i}));
			}
			return new Polinomio(polinomio.restar(ret));
		}else{
			for (int i = 0; i < polThis; i++) {
				ret.set(new int[]{0,i}, polinomio.get(new int[]{0,i}));
			}
			return new Polinomio(ret.restar(polMat));
		}
	}
	public Polinomio multiply(Polinomio pol) throws MatrizException, ValorNumericoException{
		Matriz polMat = pol.getPolinomio();
		int polMatr = polMat.dimensions()[1];
		int polThis = polinomio.dimensions()[1];
		Matriz ret = new Matriz(new int[]{1,polMatr+polThis-1});
		for (int i = 0; i < polThis; i++) {
			Matriz aux = new Matriz(new int[]{1,polMatr+polThis-1});
			aux.set(new int[]{0, i}, pol.polinomio.multiplicar(new Matriz(this.polinomio.get(new int[]{0,i}))));
			ret = ret.sumar(aux);
		}
		return new Polinomio(ret);
	}
	public ValorNumerico eval(ValorNumerico val) throws ValorNumericoException{
		System.out.println("Val: " + val);
		ValorNumerico ret = ValorNumerico.ZERO;
		for (int i = 0; i < polinomio.dimensions()[1]; i++) {
			ret = ret.add(polinomio.get(new int[]{0,i}).multiply(val.pow(new ValorNumerico(i))));
			System.out.println(val.pow(new ValorNumerico(i)));
		}
		return ret;
	}
	public String toString(){
		return polinomio.toString();
	}
}
