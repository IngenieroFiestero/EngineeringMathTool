package Tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.io.IOException;

import MathTool.InterpreteException;
import MathTool.MathInterprete;
import Matriz.Matriz;
import Matriz.MatrizException;
import Operaciones.ListaOperaciones;
import Operaciones.Operacion;
import Operaciones.Operador;
import Operaciones.Operando;
import ValorNumerico.*;


public class Test {

	public static void main(String[] args) throws MatrizException {
		ValorNumerico val = new ValorNumerico(15,12,3);
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
		Matriz mat1 = new Matriz("1,2,3;4,5,6");
		Integer intg = new Integer(4);
		Operacion op = new Operacion(new Operando[]{new Operando("paco"),new Operando(val2)}, Operador.SUMA);
		ByteArrayOutputStream os2 = new ByteArrayOutputStream();
		ByteArrayInputStream is2 = null;
		Operacion op2 = null;
		try {
			op.save(os2);
			is2 = new ByteArrayInputStream(os2.toByteArray());
			op2 = Operacion.load(is2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(op);
		System.out.println(op2);
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
		System.out.print("----------------------------------");
		System.out.println("----------------------------------");
		ListaOperaciones ops = new ListaOperaciones();
		try {
			Operacion operac = mi.getOperacion("[a,b]=x+pepe(2,3)+(4*5);", ops);
			
		} catch (InterpreteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Size: " + ops.size());
		for (int i = 0; i < ops.size(); i++) {
			Operacion op1 = ops.get(i);
			System.out.println("Operacion numero: " + op1.getId() + " " + Operador.OPERADORES[op1.getOperador()]);
			Operando[] oper = op1.getOperandos();
			System.out.println("Nº operandos: " + oper.length);
			for (int j = 0; j < oper.length; j++) {
				System.out.println(oper[j]);
			}
			
		}
	}

}
