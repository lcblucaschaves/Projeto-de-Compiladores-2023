package datastructures;

public class IsiVariable extends IsiSymbol {
	
	public static final int NUMBER  = 0;
	public static final int TEXT    = 1;
	public static final int BOOLEAN = 2;
	
	private int type;
	private String value;
	private boolean attributed;
	
	public IsiVariable(String name, int type, String value) {
		super(name);
		this.type = type;
		this.value = value;
		this.attributed = false;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean getAttributed() {
		return this.attributed;
	}

	public void setAttributed(boolean condition) {
		this.attributed = condition;
	}

	@Override
	public String toString() {
		return "IsiVariable [name=" + name + ", type=" + type + ", value=" + value + "]";
	}
	
	public String generateJavaCode() {
		String str;
		if (type == TEXT) {
			str = "String ";
		}
		else if (type == NUMBER) {
			str = "double ";
		} else {
			str = "boolean ";
		}
		return str + " "+super.name+";";
	}	
}
