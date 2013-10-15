package visitor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import syntaxtree.BinOp;
import syntaxtree.CJumpStmt;
import syntaxtree.Call;
import syntaxtree.ErrorStmt;
import syntaxtree.Exp;
import syntaxtree.Goal;
import syntaxtree.HAllocate;
import syntaxtree.HLoadStmt;
import syntaxtree.HStoreStmt;
import syntaxtree.IntegerLiteral;
import syntaxtree.JumpStmt;
import syntaxtree.Label;
import syntaxtree.MoveStmt;
import syntaxtree.NoOpStmt;
import syntaxtree.Node;
import syntaxtree.NodeListOptional;
import syntaxtree.Operator;
import syntaxtree.PrintStmt;
import syntaxtree.Procedure;
import syntaxtree.Stmt;
import syntaxtree.StmtExp;
import syntaxtree.StmtList;
import syntaxtree.Temp;

public class Translator extends  GJNoArguDepthFirst<String>{
	
	static int tempKey;
	boolean arg;
	/*
	
	public String setNewLabel(String key){
		String map = key;
		while(labels.containsKey(map)){
			map = "a"+map;
		}
		labels.put(key, map);
		return map;
	}
	
*/
	
	public int getNewTemp(){
		
		return ++tempKey;
	}
	
	private void print(String message) {
		System.out.print(message);
		System.out.print(' ');
	}

	private void print(int message) {
		System.out.print(message);
		System.out.print(' ');
	}


	   /**
	    * f0 -> "MAIN"
	    * f1 -> StmtList()
	    * f2 -> "END"
	    * f3 -> ( Procedure() )*
	    * f4 -> <EOF>
	    */
	   public String visit(Goal n) {
		  arg = true; 
	      String _ret=null;
	      print("MAIN");
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      print("\nEND");
	      n.f3.accept(this);
	      
	      n.f4.accept(this);	      
	      return _ret;
	   }

	   /**
	    * f0 -> Label()
	    * f1 -> "["
	    * f2 -> IntegerLiteral()
	    * f3 -> "]"
	    * f4 -> StmtExp()
	    */
	   public String visit(Procedure n) {
	      print("\n");

	     n.f0.accept(this);
	      //print(this.setNewLabel(n.f0.accept(this)));
	      print("["); print(n.f2.accept(this)); print("]\nBEGIN");
	      
	      String result = n.f4.accept(this);
	      print("\nRETURN"); print(result);
	      print("\nEND");
	      return null;
	   }
	   
	   
	   /**
	    * f0 -> "NOOP"
	    */
	   public String visit(NoOpStmt n) {
	      String _ret=null;
	      print("\nNOOP");
	      n.f0.accept(this);
	      return _ret;
	   }

	   /**
	    * f0 -> "ERROR"
	    */
	   public String visit(ErrorStmt n) {
	      String _ret=null;
	      n.f0.accept(this);
	      print("\nERROR");
	      return _ret;
	   }
	   
	   

	   /**
	    * f0 -> <IDENTIFIER>
	    */
	   public String visit(Label n) {
	      String _ret=n.f0.toString();
	      	if(arg){
	    	  print(_ret);
	    	  
	    	 }else{
	    		 int newTemp = getNewTemp();
	    		 print("\nMOVE TEMP"); print(newTemp); print(_ret);
	    		 return "TEMP "+newTemp;
	    	 }
	     
	      return null;
	   }
	   
	   
	   /**
	    * f0 -> <INTEGER_LITERAL>
	    */
	   public String visit(IntegerLiteral n) {
	      String _ret=n.f0.toString();
	      n.f0.accept(this);
	      return _ret;
	   }
	   
	   

	   /**
	    * f0 -> "CJUMP"
	    * f1 -> Exp()
	    * f2 -> Label()
	    */
	   public String visit(CJumpStmt n) {
	      String _ret=null;
	      
	      String newTemp = n.f1.accept(this);
	      print("\nCJUMP"); print(newTemp);
	      
	      n.f2.accept(this);
	      
	      //print(labels.get(n.f2.accept(this)));
	     
	      return _ret;
	   }


	   /**
	    * f0 -> "JUMP"
	    * f1 -> Label()
	    */
	   public String visit(JumpStmt n) {
	      String _ret=null;
	      
	      print("\nJUMP"); n.f1.accept(this);
	      
	      return _ret;
	   }
	   
	   

	   /**
	    * f0 -> "HSTORE"
	    * f1 -> Exp()
	    * f2 -> IntegerLiteral()
	    * f3 -> Exp()
	    */
	   public String visit(HStoreStmt n) {
	      String _ret=null;
	      
	      
	      String offset = n.f2.accept(this);
	      String from = n.f3.accept(this);	      
	      String pos = n.f1.accept(this);
	      print("\nHSTORE"); print(pos); print(offset);  print(from);
	      
	      return _ret;
	   }

	   
	   
	   /**
	    * f0 -> "HLOAD"
	    * f1 -> Temp()
	    * f2 -> Exp()
	    * f3 -> IntegerLiteral()
	    */
	   public String visit(HLoadStmt n) {
	      String _ret=null;
	      
	      String from = n.f2.accept(this);
	      String offset = n.f3.accept(this);
	      String pos = n.f1.accept(this);
	      print("\nHLOAD"); print(pos); print(from); print(offset);
	      return _ret;
	   }
	   

	   /**
	    * f0 -> "TEMP"
	    * f1 -> IntegerLiteral()
	    */
	   public String visit(Temp n) {
	      
	      return "TEMP "+n.f1.accept(this);
	   }
	   
	   

	   /**
	    * f0 -> "MOVE"
	    * f1 -> Temp()
	    * f2 -> Exp()
	    */
	   public String visit(MoveStmt n) {
	      String _ret=null;
	      String from = n.f2.accept(this);
	      
	      String to = n.f1.accept(this);

	      print("\nMOVE"); print(to); print(from);
	      return _ret;
	   }
	   
	   
	   

	   /**
	    * f0 -> "PRINT"
	    * f1 -> Exp()
	    */
	   public String visit(PrintStmt n) {
	      String toPrint = n.f1.accept(this);
	      print("\nPRINT"); print(toPrint);
	      return null;
	   }
	   
	   
	   /**
	    * f0 -> "LT"
	    *       | "PLUS"
	    *       | "MINUS"
	    *       | "TIMES"
	    */
	   public String visit(Operator n) {
	      return n.f0.choice.toString();
	      
	   }
	   
	   /**
	    * f0 -> Operator()
	    * f1 -> Exp()
	    * f2 -> Exp()
	    */
	   public String visit(BinOp n) {

		  String left =     n.f1.accept(this);
		  String right =    n.f2.accept(this);
		  int newTemp = this.getNewTemp();
		  print("\nMOVE TEMP"); print(newTemp); print(left);
	      String op = n.f0.accept(this);
	      int result = this.getNewTemp();
	      print("\nMOVE TEMP"); print(result); print(op); print("TEMP"); print(newTemp); print(right);
	      return "TEMP "+result;
	   }


	   /**
	    * f0 -> "HALLOCATE"
	    * f1 -> Exp()
	    */
	   public String visit(HAllocate n) {
	      String result = n.f1.accept(this);
	      int newTemp = this.getNewTemp();
	      print("\nMOVE TEMP"); print(newTemp); print("HALLOCATE"); print(result);
	      return "TEMP "+newTemp;
	   }
	   

	   /**
	    * f0 -> "BEGIN"
	    * f1 -> StmtList()
	    * f2 -> "RETURN"
	    * f3 -> Exp()
	    * f4 -> "END"
	    */
	   public String visit(StmtExp n) {
	      String _ret=null;

	      n.f1.accept(this);
	      String toReturn = n.f3.accept(this);
	      int newTemp = this.getNewTemp();
	      print("\nMOVE TEMP"); print(newTemp); print(toReturn);
	      
	      return "TEMP "+newTemp;
	   }
	   
	   

	   /**
	    * f0 -> StmtExp()
	    *       | Call()
	    *       | HAllocate()
	    *       | BinOp()
	    *       | Temp()
	    *       | IntegerLiteral()
	    *       | Label()
	    */
	   public String visit(Exp n) {
	      arg = false;
	      String ret = n.f0.accept(this);
	      arg = true;
	      return ret;
	   }
	   
	   

	   /**
	    * f0 -> "CALL"
	    * f1 -> Exp()
	    * f2 -> "("
	    * f3 -> ( Exp() )*
	    * f4 -> ")"
	    */
	   public String visit(Call n) {
	      int newTemp = this.getNewTemp();
	      
	      String toCall = n.f1.accept(this);
	      
	      
	      String called = n.f3.accept(this);
	      print("\nMOVE TEMP"); print(newTemp);
	      print("\nCALL"); print(toCall);
	      print("("); 
	      print(called);
	      print(")");
	      return "TEMP "+newTemp;
	   }

	   
	   public String visit(NodeListOptional n) {
		      if ( n.present() ) {
		    	String  called = "";
		         int _count=0;
		         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
		            called = called + " "+(e.nextElement().accept(this));
		            _count++;
		         }
		         return called;
		      }
		      else
		         return null;
		   }
	   
	   
	   /**
	    * f0 -> ( ( Label() )? Stmt() )*
	    */
	   public String visit(StmtList n) {
		  	      n.f0.accept(this);
	      
	      return null;
	   }

	   


}
