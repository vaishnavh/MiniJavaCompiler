package symboltable;

public class VariableSymbol {
	public String name, type; 
	public VariableSymbol(String type, String name){
		this.type = type;
		this.name = name;
	}
	
	public void print(){
		System.out.println(name + " "+type);		
	}
	
	public boolean isType(String check){
		if(type.compareTo(check)==0) return true;
		return false;
	}
}
