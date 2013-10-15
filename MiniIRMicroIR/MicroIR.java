import syntaxtree.Node;
import visitor.Bounder;
import visitor.Translator;


public class MicroIR {
	public static void main(String [] args){
		try {
	         Node root = new MiniIRParser(System.in).Goal();	         
	         root.accept(new Bounder());
	         root.accept(new Translator());
	      }
	      catch (ParseException e) {
	         System.out.println(e.toString());
	      }	
	}
}
