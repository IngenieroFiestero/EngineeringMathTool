package MathTool;

public class Funcion {
	private String name;
	private Script codigo;
	private String[] args;
	private String[] out;
	private boolean estatico;
	private int id = -1;
	
	public Funcion(String name){
		this.name = name;
		this.args = null;
		this.out = null;
		this.estatico = false;
	}
	public Funcion(String name,String codigo){
		this.name = name;
		this.codigo = new Script(name,codigo);
		this.args = null;
		this.out = null;
		this.estatico = false;
	}
	public Funcion(String name,Script codigo){
		this.name = name;
		this.codigo = codigo;
		this.args = null;
		this.out = null;
		this.estatico = false;
	}
	/**
	 * Metodo de creacion de funciones estaticas implementadas directamente en java
	 * @param name
	 * @param id
	 */
	public Funcion(String name,int id){
		this.name = name;
		this.estatico = true;
		this.id = id;
	}
	/**
	 * Devulve el nombre de la funcion
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * Devuelve el codigo script para poder interpretarlo
	 * @return
	 */
	public Script getCodigo(){
		return this.codigo;
	}
	/**
	 * Devuelve el vector de argumentos que se le pueden pasar a la funcion
	 * @return
	 */
	public String[] getArgs(){
		return this.args;
	}
	/**
	 * Devuelve los nombres de las variables de salida
	 * @return
	 */
	public String[] geOuts(){
		return this.out;
	}
	public boolean isStatic(){
		return this.estatico;
	}
	public int getId(){
		return this.id;
	}
	public String toString(){
		return this.name;
	}
}
