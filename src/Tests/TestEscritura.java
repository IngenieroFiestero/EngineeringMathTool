package Tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import MathTool.InterpreteException;
import MathTool.MathInterprete;
import Operaciones.ExpresionInterpretada;

public class TestEscritura {
	public static void main(String[] args){
		MathInterprete mi = new MathInterprete();
		try {
			ExpresionInterpretada eiAux2 = mi.evaluarExpresion("x=atan(sin(pi/3)/cos(pi/3))");
			File file = new File("prueba.ei");
			FileOutputStream os;
			try {
				os = new FileOutputStream(file);
				eiAux2.save(os);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InterpreteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
