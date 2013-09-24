package symboltable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import visitor.TypeCheckVisitor;



public class MethodSymbol {
	public int offset, localOffset;
	public String className;
	public String name, returnType;
	public Vector<VariableSymbol> formals = new Vector<VariableSymbol>();
	HashMap<String, VariableSymbol> variables = new HashMap<String, VariableSymbol>() ;	

	public void addLocalVariable(String type, String variableName){		

		VariableSymbol newVariable = new VariableSymbol(type, variableName, true);
		variables.put(variableName, newVariable);
		newVariable.offset = localOffset++; 
	}
	
	public void addParameter(String type, String variableName){

		VariableSymbol newVariable = new VariableSymbol(type, variableName,false);
		formals.add(newVariable);
		newVariable.offset = formals.size();
		variables.put(variableName, newVariable);
	}
	
	public MethodSymbol(String name, String className, String returnType){
		this.name = name;
		this.className = className; //To which class does this belong to?
		this.returnType = returnType;
		this.localOffset = 1;
	}
	
	public void print(){
		System.out.println("Method : "+name+" Type : "+returnType+"  @offset = "+offset);
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
	
	public boolean matchSignatures(MethodSymbol m){
		if(formals.size()!=m.formals.size()){
			return false;
		}
		for(int i=0; i<formals.size(); i++){
			if(formals.elementAt(i).type.compareTo(m.formals.elementAt(i).type)!=0){
				//Even if there's subtyping it implies overloading
				return false;
			}
		}
		return true;
	}
	
	
	public int getOffset(){
		return 4*this.offset-4;
	}
	
	public String getName(){
		return this.className + "_"+this.name;
	}
	
	public int getVariablePosition(String varName, int begin){
		VariableSymbol v = this.variables.get(varName);
		if(v.isLocal){
			return begin+(v.offset-1);
		}
		return v.offset;
	}
	
	public  int localSize(){
		return this.variables.size() - this.formals.size();
	}
}
