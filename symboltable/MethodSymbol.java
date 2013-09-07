package symboltable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import visitor.TypeCheckVisitor;



public class MethodSymbol {
	public String name, returnType;
	public Vector<VariableSymbol> formals = new Vector<VariableSymbol>();
	HashMap<String, VariableSymbol> variables = new HashMap<String, VariableSymbol>() ;	
	
	
	public void addLocalVariable(String type, String variableName){		
		if(variables.containsKey(variableName)){
			TypeCheckVisitor.error("Redeclaration of "+variableName);
		}
		VariableSymbol newVariable = new VariableSymbol(type, variableName);
		variables.put(variableName, newVariable);
	}
	
	public void addParameter(String type, String variableName){
		if(variables.containsKey(variableName)){
			TypeCheckVisitor.error("Redeclaration of "+variableName);
		}
		VariableSymbol newVariable = new VariableSymbol(type, variableName);
		formals.add(newVariable);
		variables.put(variableName, newVariable);
	}
	
	public MethodSymbol(String name, String returnType){
		this.name = name;
		this.returnType = returnType;
	}
	
	public void print(){
		System.out.println("Method : "+name+" Type : "+returnType);
		System.out.println("Formals : "+formals.size());
	    Iterator<VariableSymbol> iter = formals.iterator();
	    while(iter.hasNext()){
	    	VariableSymbol v = iter.next();
	    	v.print();
	    }
	    System.out.println("Variables :  "+variables.size());
	    Iterator<String> it = variables.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			variables.get(key).print();
		}
	}
}
