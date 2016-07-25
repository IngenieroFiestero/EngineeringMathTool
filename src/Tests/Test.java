package Tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;

import MathTool.InterpreteException;
import MathTool.ListaVariables;
import MathTool.MathInterprete;
import Matriz.Matriz;
import Matriz.MatrizException;
import Operaciones.ExpresionInterpretada;
import Operaciones.ListaOperaciones;
import Operaciones.Operacion;
import Operaciones.Operador;
import Operaciones.Operando;
import ValorNumerico.*;
import Variable.Variable;


public class Test {

	public static void main(String[] args) throws MatrizException {
		MathInterprete mi = new MathInterprete();
		System.out.println("---------------- Lista Variables ------------------");
		ListaVariables lv = mi.getMathContext().getVariableList();
		Variable[] list = lv.getVariables();
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i] + " " + list[i].getValue());
		}
		System.out.println("----------------------------------");
		System.out.println("--------------- Ejecutar Ops -------------------");
		try {
			//Operacion operac = mi.getOperacion("[a,b]=x+pepe([2],3,pepe2(7,9))+(4*5);", ops);
			String expresion = "a=pi*([1,2,3;4,5,5]/2)";
			System.out.println("Interpretando expresion: " + expresion);
			ExpresionInterpretada eiAux = mi.evaluarExpresion(expresion);
			String ret = mi.interpretarExpresion(eiAux);
			System.out.println(ret);
			System.out.println("----------------------------------");
			ExpresionInterpretada eiAux2 = mi.evaluarExpresion("x=atan(sin(pi/3)/cos(pi/3))");
			System.out.println(mi.interpretarExpresion(eiAux2));
			System.out.println("----------------------------------");
			eiAux2 = mi.evaluarExpresion("inte = matrixIntegration([1,2,3,2],[0.1,0.2,0.3,0.4])");
			System.out.println(mi.interpretarExpresion(eiAux2));
			System.out.println("----------------------------------");
			eiAux2 = mi.evaluarExpresion("e^(1+i)");
			System.out.println(mi.interpretarExpresion(eiAux2));
		} catch (InterpreteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("---------------- Lista Variables ------------------");
		lv = mi.getMathContext().getVariableList();
		list = lv.getVariables();
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i] + " " + list[i].getValue());
		}
		/*
		System.out.println("----------------------------------");
		System.out.println("Size: " + ops.size());
		for (int i = 0; i < ops.size(); i++) {
			Operacion op1 = ops.get(i);
			System.out.println("Operacion numero: " + op1.getId() + " " + Operador.OPERADORES[op1.getOperador()]);
			Operando[] oper = op1.getOperandos();
			System.out.println("Nº operandos: " + oper.length);
			for (int j = 0; j < oper.length; j++) {
				System.out.println(oper[j]);
			}
		}*/
	}

}
