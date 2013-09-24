package symboltable;

import java.util.HashMap;
import java.util.Iterator;

import syntaxtree.PrintStatement;
import visitor.IRGeneratorVisitor;




public class ClassSymbol {
	public int methodOffset;
	public int variableOffset;
	public String name;
	public String parentName;
	public boolean isMerged, isClosed;
	public HashMap<String, VariableSymbol> variables = new HashMap<String, VariableSymbol>();
	public HashMap<String, MethodSymbol> methods = new HashMap<String, MethodSymbol>();
	public HashMap<String, ClassSymbol> parents = new HashMap<String, ClassSymbol>();
	
	//public HashMap<String, Integer> methodOffsets = new HashMap<String, Integer>();
	//public HashMap<String, Integer> variableOffsets = new HashMap<String, Integer>();
	public ClassSymbol(String name){
		this.name = name;
		isMerged = false;
		isClosed = false;
		parentName = "";
		methodOffset = 1;
		variableOffset = 1;
	}
	
	public void addVariable(String type, String variableName){		
		//We'll add variable names with the class name
		VariableSymbol newVariable = new VariableSymbol(type, this.name+variableName, false);
		variables.put(variableName, newVariable);
		newVariable.offset = variableOffset++;
	}
	
	public void addLocalVariableToMethod(String type, String variableName, String methodName){
		methods.get(methodName).addLocalVariable(type, variableName);
	}
	
	public void addParameterToMethod(String type, String variableName, String methodName){
		methods.get(methodName).addParameter(type, variableName);
	}
	
	public void addMethod(String methodName, String returnType){
		MethodSymbol newMethod = new MethodSymbol(methodName, name, returnType);
		methods.put(methodName, newMethod);
		newMethod.offset = methodOffset++;
	}
	
	
	public int merge(HashMap<String, ClassSymbol> h){	
		int max = 0;
		if(!this.isMerged){
			
			if(!parentName.isEmpty()){
				ClassSymbol parent = h.get(parentName);
				parent.merge(h);
				int addOffset = parent.variables.size();
				
				Iterator<String> iter = variables.keySet().iterator();
				//Add offset to the current variables 
				while(iter.hasNext()){
					String key = iter.next();
					variables.get(key).offset += addOffset;
				}
				
				iter = parent.variables.keySet().iterator();
				while(iter.hasNext()){
					String key = iter.next();
					if(!variables.containsKey(key)){
						variables.put(key, parent.variables.get(key));				
						}	
				}
				
				
				iter = methods.keySet().iterator();
				//Set  offset of the current variables to 0 
				while(iter.hasNext()){
					String key = iter.next();
					methods.get(key).offset = 0;
				}
				
				//Take care of overriding				
				iter = parent.methods.keySet().iterator();
				while(iter.hasNext()){
					String key = iter.next();
					MethodSymbol m = parent.methods.get(key);
					if(!methods.containsKey(m.name)){
						methods.put(key, parent.methods.get(key));							
					}else{						
						methods.get(m.name).offset = parent.methods.get(key).offset;					
					}					
				}
				
				int lastOffset = parent.methods.size()+1;
				iter = methods.keySet().iterator();
				//Set  zero offset variables to non-zero value
				while(iter.hasNext()){
					String key = iter.next();
					MethodSymbol m = methods.get(key);
					if(m.offset == 0)
						m.offset = lastOffset++;
				}
				isMerged = true;
			}
			
		}
		Iterator<String> iter = methods.keySet().iterator();
		//Add offset to the current variables 
		while(iter.hasNext()){
			String key = iter.next();
			int z = methods.get(key).formals.size();
			if(z>max) max =z;
		}
		return max;		
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

			return variables.get(this.name+variableName); //a global variable
		}
	}
	
	public int getSize(){
		return this.variables.size()*4+4;
	}
	
	public int getFunctionTableSize(){	
		return this.methods.size()*4;
	}
	
	public String loadVariable(String varName, String methodName, int classPos, int begin){
		MethodSymbol m = methods.get(methodName);
		//System.out.println("Searching for "+varName+" in "+methodName+ " in "+this.name);
		if(m.variables.containsKey(varName)){
			return "TEMP "+m.getVariablePosition(varName, begin);
		}
		int newKey = IRGeneratorVisitor.getNewKey();
		//System.out.print("Trying to find "+this.name+varName);
		return "BEGIN\nHLOAD TEMP "+newKey + " TEMP "+classPos+" "+variables.get(varName).offset*4+"\nRETURN TEMP "+newKey+"\nEND\n";
	}
	
	public String storeVariable(String varName, String methodName, int classPos, int begin){
		MethodSymbol m = methods.get(methodName);
		//System.out.println("Searching for "+varName+" in "+methodName+ " in "+this.name);
		if(m.variables.containsKey(varName)){
			return "MOVE TEMP "+m.getVariablePosition(varName, begin);
		}
		int newKey = IRGeneratorVisitor.getNewKey();
		return "HSTORE TEMP"+newKey + " TEMP "+classPos+" "+variables.get(varName).offset*4;
	}
	
	
	public boolean isClassAttribute(String varName, String methodName){
		MethodSymbol m = methods.get(methodName);
		//System.out.println("Searching for "+varName+" in "+methodName+ " in "+this.name);
		if(m.variables.containsKey(varName)){
			return false;
		}
		return true;
	}
	

}
