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
		Matriz matP = new Matriz("[2]");
		System.out.println(matP.toString());
		System.out.println("----------------------------------");
		ValorNumerico val = new ValorNumerico(15,12);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ByteArrayInputStream is = null;
		ValorNumerico val2 = null;
		try {
			val.save(os);
			is = new ByteArrayInputStream(os.toByteArray());
			val2 = ValorNumerico.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(val);
		System.out.println(val2);
		Matriz mat1 = new Matriz("[1,2,3;4,5,6]");
		Integer intg = new Integer(4);
		Operacion op = new Operacion(new Operando[]{new Operando("paco"),new Operando(val2)}, Operador.SUMA);
		ByteArrayOutputStream os2 = new ByteArrayOutputStream();
		ByteArrayInputStream is2 = null;
		Operacion op2 = null;
		try {
			op.save(os2);
			is2 = new ByteArrayInputStream(os2.toByteArray());
			op2 = Operacion.load(is2);
			System.out.println(op);
			System.out.println(op2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("----------------------------------");
		MathInterprete mi = new MathInterprete();
		String[] spl = MathInterprete.spliter("[a,b]=x+pepe(2,3)+(4*5)");
		for (int i = 0; i < spl.length; i++) {
			System.out.println(spl[i]);
		}
		spl = MathInterprete.spliter("mifuncion(4,5,x),6+7");
		for (int i = 0; i < spl.length; i++) {
			System.out.println(spl[i]);
		}
		spl = MathInterprete.getArgs("mifuncion(4 , 5 , x, funcion1(2, 3, 4)),6+7");
		for (int i = 0; i < spl.length; i++) {
			System.out.println(spl[i]);
		}
		
		System.out.println("---------------- Modulo ------------------");
		try {
			ValorNumerico val11 = new ValorNumerico("5.2");
			ValorNumerico val22 = new ValorNumerico("5");
			System.out.println("Modulo " + val11.modulo(val22));
			System.out.println("---------------- SQRT ------------------");
			System.out.println("sqrt " + val11.sqrt());
		} catch (ValorNumericoException e) {
		}
		
		System.out.println("---------------- Lista Variables ------------------");
		ListaVariables lv = mi.getMathContext().getVariableList();
		Variable[] list = lv.getVariables();
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i] + " " + list[i].getValue());
		}
		System.out.println("----------------------------------");
		
		ListaOperaciones ops = new ListaOperaciones();
		ExpresionInterpretada ei = null;
		try {
			//Operacion operac = mi.getOperacion("[a,b]=x+pepe([2],3,pepe2(7,9))+(4*5);", ops);
			ei = mi.evaluarExpresion("[a,b]=x+pepe([2],3,pepe2(7,9))+(4*5);");
			ops=ei.getListaOperaciones();
		} catch (InterpreteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("--------------- Ejecutar Ops -------------------");
		try {
			//Operacion operac = mi.getOperacion("[a,b]=x+pepe([2],3,pepe2(7,9))+(4*5);", ops);
			String expresion = "a=sqrt(4)+sqrt(sqrt(16)+4)+i";
			System.out.println("Interpretando expresion: " + expresion);
			ExpresionInterpretada eiAux = mi.evaluarExpresion(expresion);
			String ret = mi.interpretarExpresion(eiAux);
			System.out.println(ret);
		} catch (InterpreteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
