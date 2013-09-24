//TODO
//Subtyping at all places
//& on int
//Overloading

import java.util.HashMap;
import java.util.Iterator;

import symboltable.ClassSymbol;
import syntaxtree.Node;
import visitor.SymbolTableVisitor;
import visitor.TypeCheckVisitor;

public class MiniJavaTypeChecker {
	public static void main(String [] args) {
	      try {
	         Node root = new MiniJavaParser(System.in).Goal();
	         
	         SymbolTableVisitor stv = new SymbolTableVisitor();
	         root.accept(stv); // Your assignment part is invoked here.
	         transitiveClosure(stv.classes);
	         merge(stv.classes);
	         TypeCheckVisitor tcv = new TypeCheckVisitor();
	         tcv.classes = stv.classes;
	         root.accept(tcv, null);
	         System.out.println("Program type checked successfully");
	      }
	      catch (ParseException e) {
	         System.out.println(e.toString());
	      }
	   }
	
	public static void transitiveClosure(HashMap<String, ClassSymbol> h){
		Iterator<String> iter = h.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			//h.get(key).transitiveClosure(h);	
		}
	}
	
	public static int merge(HashMap<String, ClassSymbol> h){
		Iterator<String> iter = h.keySet().iterator();
		int max = 0;
		while(iter.hasNext()){
			String key = iter.next();
			int z = h.get(key).merge(h);	
			if(z>max) max=z;
		}
		return max;
	}
	
	
}
