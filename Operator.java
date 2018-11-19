public class Operator {
	protected static Operator currentOperator;
	private String type;
	private int code;

	Operator(){
		this.type = "Customer";
		this.code = 0000;
	}

	Operator(String type, int code){
		this.type = type;
		this.code = code;
	}
}
