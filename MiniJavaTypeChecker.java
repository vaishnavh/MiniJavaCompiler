

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
	
	public static void merge(HashMap<String, ClassSymbol> h){
		Iterator<String> iter = h.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			h.get(key).merge(h);	
			h.get(key).print();
		}
	}
	
	
}
