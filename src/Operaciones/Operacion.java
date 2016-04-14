package Operaciones;

public class Operacion extends Thread{
	private Resultado resultado;
	private Operador operador;
	private Operando[] operandos;
	private Operacion[] operaciones;
	/**
	 * Una operación se ejecuta en paralelo. Utiliza una lista de operandos y un operador. La cantidad de operandos depende del operador.
	 * @param operandos
	 * @param operador
	 */
	public Operacion(Operando[] operandos, Operador operador){
		this.operandos = operandos;
		this.operador = operador;
	}
	/**
	 * Este método permite crear operaciones que se ejecutan tras otras operaciones
	 * @param operandos
	 * @param operador
	 * @param operaciones
	 */
	public Operacion(Operando[] operandos, Operador operador,Operacion[] operaciones){
		this(operandos,operador);
		this.operaciones = operaciones;
	}
	public void run(){
		for (int i = 0; i < operaciones.length; i++) {
			try {
				operaciones[i].join();
			} catch (InterruptedException e) {}
		}
		this.resultado = operador.operar(operandos);
	}
}
