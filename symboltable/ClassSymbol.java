package symboltable;

import java.util.HashMap;
import java.util.Iterator;

import visitor.TypeCheckVisitor;




public class ClassSymbol {
	public String name;
	public String parentName;
	public boolean isMerged;
	public HashMap<String, VariableSymbol> variables = new HashMap<String, VariableSymbol>();
	public HashMap<String, MethodSymbol> methods = new HashMap<String, MethodSymbol>();
	public ClassSymbol(String name){
		this.name = name;
		isMerged = false;
		parentName = "";
	}
	
	public void addVariable(String type, String variableName){		
		if(variables.containsKey(variableName)){
			TypeCheckVisitor.error("Redeclaration of "+variableName);
		} 
		VariableSymbol newVariable = new VariableSymbol(type, variableName);
		variables.put(variableName, newVariable);
	}
	
	public void addLocalVariableToMethod(String type, String variableName, String methodName){
		if(!methods.containsKey(methodName)){
			TypeCheckVisitor.error("Unknown method "+methodName);
		}
		methods.get(methodName).addLocalVariable(type, variableName);
	}
	
	public void addParameterToMethod(String type, String variableName, String methodName){
		if(!methods.containsKey(methodName)){
			TypeCheckVisitor.error("Unknown method "+methodName);
		}
		methods.get(methodName).addParameter(type, variableName);
	}
	
	public void addMethod(String methodName, String returnType){
		if(methods.containsKey(methodName)){
			TypeCheckVisitor.error("Redeclaration of method "+methodName);
		}
		MethodSymbol newMethod = new MethodSymbol(methodName, returnType);
		methods.put(methodName, newMethod);
	}
	
	
	public void merge(HashMap<String, ClassSymbol> h){		
		if(!this.isMerged){
			if(parentName.isEmpty()){
				isMerged = true;
			}else{
				ClassSymbol parent = h.get(parentName);
				parent.merge(h);
				Iterator<String> iter = parent.variables.keySet().iterator();
				while(iter.hasNext()){
					String key = iter.next();
					if(!variables.containsKey(key)){
						variables.put(key, parent.variables.get(key));							
					}
				}
				
				iter = parent.methods.keySet().iterator();
				while(iter.hasNext()){
					String key = iter.next();
					if(!methods.containsKey(key)){
						methods.put(key, parent.methods.get(key));							
					}else{
						String parentReturnType = parent.methods.get(key).returnType;
						String childReturnType = methods.get(key).returnType;
						if(parentReturnType.compareTo(childReturnType)!=0){
							TypeCheckVisitor.error("Overriding of function returns");
						}
					}
				}
				isMerged = true;
			}
			
		}
	}
	
	public void print(){
		System.out.println("Class : "+name);
		System.out.println("Variables : "+variables.size());
		Iterator<String> iter = variables.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			variables.get(key).print();
		}
		System.out.println("Methods : "+methods.size());
		Iterator<String> it = methods.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			methods.get(key).print();
		}
	}
	
	public VariableSymbol getVariable(String variableName, String methodName){
		MethodSymbol m = methods.get(methodName);
		if(m.variables.containsKey(variableName)){ //Implements scope : formal and local given more precedence
			return m.variables.get(variableName);
		}else{
			if(!variables.containsKey(variableName)){
					TypeCheckVisitor.error("Unknown variable"+variableName);
				}
			return variables.get(variableName); //a global variable
		}
	}
}
