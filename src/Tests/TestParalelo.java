package Tests;

import Matriz.Matriz;
import Operaciones.Resultado;

public class TestParalelo extends Thread{
	Resultado res;
	public TestParalelo(Resultado res){
		this.res = res;
	}
	public void run(){
		System.out.println("Test Paralelo Duerme");
		System.out.println("Test Paralelo: " + ((Matriz)res.getValor()).toString());
	}
}
