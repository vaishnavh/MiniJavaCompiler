import java.util.Iterator;

import syntaxtree.Node;
import visitor.IRGeneratorVisitor;
import visitor.SymbolTableVisitor;
import visitor.TypeCheckVisitor;


public class IRGenerator {
	public static void main(String [] args){
		try {
	         Node root = new MiniJavaParser(System.in).Goal();	         
	         SymbolTableVisitor stv = new SymbolTableVisitor();
	         root.accept(stv); // Your assignment part is invoked here.
	         int max = MiniJavaTypeChecker.merge(stv.classes);
	         //print(stv);
	         IRGeneratorVisitor irg = new IRGeneratorVisitor();
	         irg.tempKey=max+1;
	         irg.classes = stv.classes;
	         root.accept(irg);
	      }
	      catch (ParseException e) {
	         System.out.println(e.toString());
	      }	
	}
	
	public static void print(SymbolTableVisitor stv){
		Iterator<String> iter = stv.classes.keySet().iterator();
		while(iter.hasNext()){
			String key = iter.next();
			stv.classes.get(key).print();
		}
	}
}
