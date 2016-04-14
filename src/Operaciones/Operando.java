package Operaciones;

import MathTool.*;
import Matriz.Matriz;

public class Operando {
	private Matriz matriz;
	public Operando(Matriz matriz){
		this.matriz = matriz;
	}
	public Matriz getValor(){
		return this.matriz;
	}
}
