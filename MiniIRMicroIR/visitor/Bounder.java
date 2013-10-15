package visitor;

import syntaxtree.Goal;
import syntaxtree.IntegerLiteral;
import syntaxtree.Procedure;
import syntaxtree.Temp;

public class Bounder extends  GJNoArguDepthFirst<Integer>{
	
	boolean arg;
	

	private void print(Integer message) {
		System.out.print(message);
		System.out.print(' ');
	}

	public Bounder(){
		Translator.tempKey = 0;
	}


	   /**
	    * f0 -> "MAIN"
	    * f1 -> StmtList()
	    * f2 -> "END"
	    * f3 -> ( Procedure() )*
	    * f4 -> <EOF>
	    */
	   public Integer visit(Goal n) {
		   n.f1.accept(this);
	      Integer _ret=null;	      
	      n.f3.accept(this);      
	      return 0;
	   }

	   /**
	    * f0 -> Label()
	    * f1 -> "["
	    * f2 -> IntegerLiteral()
	    * f3 -> "]"
	    * f4 -> StmtExp()
	    */
	   public Integer visit(Procedure n) {
	      Integer _ret=null;
	      arg = true;
	      Integer newInt = n.f2.accept(this); 	      
	      arg = false;
	      n.f4.accept(this);
	      return null;
	   }
	   
	   
	   /**
	    * f0 -> <INTEGER_LITERAL>
	    */
	   public Integer visit(IntegerLiteral n) {
		   n.f0.accept(this);
		   if(arg){
			   int newInt = Integer.parseInt(n.f0.toString());
			   if(newInt>Translator.tempKey){
			    	  Translator.tempKey = newInt;
			      }
		   }
	      return 0;
	   }
	   
	   

	   /**
	    * f0 -> "TEMP"
	    * f1 -> IntegerLiteral()
	    */
	   public Integer visit(Temp n) {
	      arg = true;
	      n.f1.accept(this);
	      
	      arg = false;
	      return 0;
	   }
	  


}
