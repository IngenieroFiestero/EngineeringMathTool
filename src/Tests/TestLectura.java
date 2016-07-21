package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import MathTool.InterpreteException;
import MathTool.MathInterprete;
import Operaciones.ExpresionInterpretada;

public class TestLectura {

	public static void main(String[] args) {
		MathInterprete mi = new MathInterprete();
		try {
			File file = new File("prueba.ei");
			FileInputStream is = new FileInputStream(file);
			ExpresionInterpretada eiAux2 = ExpresionInterpretada.load(is);
			System.out.println(mi.interpretarExpresion(eiAux2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
