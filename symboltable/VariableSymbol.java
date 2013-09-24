package symboltable;

public class VariableSymbol {
	public int offset;
	public boolean isLocal;
	public String name, type; 
	public VariableSymbol(String type, String name, boolean isLocal){
		this.type = type;
		this.name = name;
		this.isLocal = isLocal;
	}
	
	public void print(){
		System.out.println(name + " "+type+"  @offset = "+offset);		
	}
	
	public boolean isType(String check){
		if(type.compareTo(check)==0) return true;
		return false;
	}
}
