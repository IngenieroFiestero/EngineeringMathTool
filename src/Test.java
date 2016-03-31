
public class Test {

	public static void main(String[] args) {
		ValorNumerico val = new ValorNumerico("+5.2-2.5i");
		System.out.println(val);
		val = new ValorNumerico("-5.2 2.5i");
		System.out.println(val);
		val = new ValorNumerico("-5.2 ");
		System.out.println(val);
		val = new ValorNumerico("4 -2.5i ");
		System.out.println(val);
		ValorNumerico val2 = new ValorNumerico("6i");
		ValorNumerico val3 = new ValorNumerico("2i");
		System.out.println(val + " / " + val2);
		try {
			ValorNumerico res = val.divide(val2);
			System.out.println("Numerador = "+res.getReal() + " " + res.getImaginario() + "i ,divisor:" + res.getDivisor());
			System.out.println(res);
		} catch (ValorNumericoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try{
			Matriz mat = new Matriz("1,2,3");
			System.out.println(mat.toString());
			mat = new Matriz("1,2,3;4,5,2 - 6i");
			System.out.println(mat.toString());
			System.out.println(mat.toStringInLine());
			Matriz mat2 = new Matriz("1,2,3;4,5,2");
			Matriz res = mat2.sumar(mat);
			System.out.println(res);
			System.out.println("Obtener Submatriz-------------------");
			System.out.println(res.getSubMatriz(new int[]{0,1,1}, new int[]{1,1,2}));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
