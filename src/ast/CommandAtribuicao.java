package ast;

public class CommandAtribuicao extends AbstractCommand{

	private String id;
	private String expr;
	
	public CommandAtribuicao(String id, String expr) {
		this.id = id;
		this.expr = expr;
	}
	
	@Override
	public String generateJavaCode() {
		String finalExpr=this.expr;
		if (this.expr.equals("Falso")) {
			finalExpr="false";
		} else if (this.expr.equals("Verdadeiro")) {
			finalExpr="true";
		}
		return id + " = "+finalExpr+";";
	}

	@Override
	public String toString() {
		return "CommandAtribuicao [id = " + id + ", expr = " + expr + "]";
	}
	
	

}
