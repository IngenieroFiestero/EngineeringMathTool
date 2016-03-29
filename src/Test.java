
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
		}
	}

}
