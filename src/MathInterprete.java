
public class MathInterprete {
	private MathContext contexto;
	//Si no se ha usado nunca
	public MathInterprete(){
		contexto = new MathContext();
	}
	//Si ya se ha usado y hay un contexto se puede cargar
	public MathInterprete(MathContext contexto){
		this.contexto = contexto;
	}
}
