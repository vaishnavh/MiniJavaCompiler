package symboltable;

import java.util.HashMap;
import java.util.Iterator;

import visitor.TypeCheckVisitor;




public class ClassSymbol {
	public String name;
	public String parentName;
	public boolean isMerged, isClosed;
	public HashMap<String, VariableSymbol> variables = new HashMap<String, VariableSymbol>();
	public HashMap<String, MethodSymbol> methods = new HashMap<String, MethodSymbol>();
	public HashMap<String, ClassSymbol> parents = new HashMap<String, ClassSymbol>();
	public ClassSymbol(String name){
		this.name = name;
		isMerged = false;
		isClosed = false;
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
			if(!parentName.isEmpty()){
				ClassSymbol parent = h.get(parentName);
				parent.merge(h);
				Iterator<String> iter = parent.variables.keySet().iterator();
				while(iter.hasNext()){
					String key = iter.next();
					if(!variables.containsKey(key)){
						variables.put(key, parent.variables.get(key));							
					}else{
						//Just override
						//TypeCheckVisitor.error("Name conflict with parent");
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
						//System.out.println("Comparing" + parentReturnType + " and "+childReturnType);
						if(parentReturnType.compareTo(childReturnType)==0){
							
						}
						else if(!h.containsKey(childReturnType)){
							TypeCheckVisitor.error("Return type either not found or doesn't match with overridden function");
						}
						else if(!h.get(childReturnType).parents.containsKey(parentReturnType)){
							TypeCheckVisitor.error("Overriding of function returns");
						}else if(!parent.methods.get(key).matchSignatures(methods.get(key))){
							TypeCheckVisitor.error("Overloading of parent function");
						}
					}
				}
				isMerged = true;
			}
			
		}
	}
	
	public void transitiveClosure(HashMap<String, ClassSymbol> h){
		if(!this.isClosed){
			if(!parentName.isEmpty()){
				ClassSymbol parent = h.get(parentName);
				parent.transitiveClosure(h);				
				parents.put(parentName, parent);
				parents.putAll(parent.parents);		
//				Iterator<String> iter = parents.keySet().iterator();
//				while(iter.hasNext()){
//					String key = iter.next();
//					System.out.print(key+" ");
//				}
//				System.out.println("belong to class "+this.name);
			}
			isClosed = true;
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
