package visitor;

import java.util.Enumeration;
import java.util.HashMap;

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
import syntaxtree.NodeOptional;
import syntaxtree.PrintStmt;
import syntaxtree.Procedure;
import syntaxtree.SimpleExp;
import syntaxtree.Stmt;
import syntaxtree.StmtExp;
import syntaxtree.StmtList;
import syntaxtree.Temp;
import datastore.Block;
import datastore.ProcedureBlock;
import datastore.Register;
import datastore.Register.Type;
//TODO
//Label Renaming
public class RegisterSaver extends GJNoArguDepthFirst<String>{
	public HashMap<String, ProcedureBlock> livenesses = new HashMap<String, ProcedureBlock>(); //Develops graphs per procedure
	public ProcedureBlock currentBlock;
	public Block currentStatement;
	public void beginNewStatement(){
		//Assumes currentBlock has been set.
		this.currentStatement = currentBlock.getNextStatement();
	}
	public void print(int x){
		System.out.print(x+" ");
	}
	public void print(String x){
		System.out.print(x+" ");
	}

	/**
	 * f0 -> "MAIN"
	 * f1 -> StmtList()
	 * f2 -> "END"
	 * f3 -> ( Procedure() )*
	 * f4 -> <EOF>
	 */
	public String visit(Goal n) {
		String _ret=null;
		ProcedureBlock mainBlock;
		mainBlock = this.livenesses.get("MAIN");
		currentBlock = mainBlock;
		currentBlock.makeSpecs();
		n.f1.accept(this);
		n.f3.accept(this);
		mainBlock.clear();
		return _ret;
	}

	/**
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public String visit(StmtList n) {
		//Assumes that currentBlock is set			   
		n.f0.accept(this);
		return null;
	}

	//Only in case of a label
	public String visit(NodeOptional n) {
		if ( n.present() ){
			return null;
		}
		else{
			
			return null;
		}
	}
	/**
	 * f0 -> Label()
	 * f1 -> "["
	 * f2 -> IntegerLiteral()
	 * f3 -> "]"
	 * f4 -> StmtExp()
	 */
	public String visit(Procedure n) {
		ProcedureBlock newBlock = this.livenesses.get(n.f0.f0.toString());
		currentBlock = newBlock;
		currentBlock.makeSpecs();
		//Empty statement for parameters passed
		//currentBlock.initializeArguments(n.f2.f0.toString());
		
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		currentBlock.clear();
		return null;
	}

	/**
	 * f0 -> NoOpStmt()
	 *       | ErrorStmt()
	 *       | CJumpStmt()
	 *       | JumpStmt()
	 *       | HStoreStmt()
	 *       | HLoadStmt()
	 *       | MoveStmt()
	 *       | PrintStmt()
	 */
	public String visit(Stmt n) {
		this.beginNewStatement();
		n.f0.accept(this);
		return null;
	}


	
	/**
	 * f0 -> "CALL"
	 * f1 -> SimpleExp()
	 * f2 -> "("
	 * f3 -> ( Temp() )*
	 * f4 -> ")"
	 */
	public String visit(Call n) {
		this.currentBlock.enumerateCallerRegisters();
	

		return null;
	}


	   public String visit(NodeListOptional n) {
	      if ( n.present() ) {
	         int _count=0;	         
	         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
	        	 String ret = e.nextElement().accept(this);
           
	            _count++;
	         }
	         return null;
	      }
	      else
	         return null;
	   }
	
	/**
	 * f0 -> "HALLOCATE"
	 * f1 -> SimpleExp()
	 */
	public String visit(HAllocate n) {				
		return null;
	}

	/**
	 * f0 -> Operator()
	 * f1 -> Temp()
	 * f2 -> SimpleExp()
	 */
	public String visit(BinOp n) {
		return null;
	}


	/**
	 * f0 -> Temp()
	 *       | IntegerLiteral()
	 *       | Label()
	 */
	public String visit(SimpleExp n) {	
		
		return null;
	}

	/**
	 * f0 -> "TEMP"
	 * f1 -> IntegerLiteral()
	 */
	public String visit(Temp n) {		
		
		return n.f1.f0.toString();
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public String visit(IntegerLiteral n) {		
		return n.f0.toString();
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public String visit(Label n) {
		return n.f0.toString();
	}

	

	   
	   /**
	    * f0 -> "BEGIN"
	    * f1 -> StmtList()
	    * f2 -> "RETURN"
	    * f3 -> SimpleExp()
	    * f4 -> "END"
	    */
	   public String visit(StmtExp n) {
	

		
	      n.f1.accept(this);
	      n.f2.accept(this);
	      //Return statements
	     // this.currentBlock.unsetLabel();
	      this.beginNewStatement();	    
	    
	      String ret=n.f3.accept(this);
	      return null;
	   }


	   /**
	    * f0 -> "CJUMP"
	    * f1 -> Temp()
	    * f2 -> Label()
	    */
	   public String visit(CJumpStmt n) {
		  
	      return null;
	   }

	   /**
	    * f0 -> "JUMP"
	    * f1 -> Label()
	    */
	   public String visit(JumpStmt n) {

	      return null;
	   }

	   
	   
	/**
	    * f0 -> "NOOP"
	    */
	   public String visit(NoOpStmt n) {

		      return null;
	   }

	   /**
	    * f0 -> "ERROR"
	    */
	   public String visit(ErrorStmt n) {
		      return null;
	   }


		/**
		 * f0 -> "HSTORE"
		 * f1 -> Temp()
		 * f2 -> IntegerLiteral()
		 * f3 -> Temp()
		 */
		public String visit(HStoreStmt n) {

			return null;
		}

		/**
		 * f0 -> "HLOAD"
		 * f1 -> Temp()
		 * f2 -> Temp()
		 * f3 -> IntegerLiteral()
		 */
		public String visit(HLoadStmt n) {

			return null;
		}

		/**
		 * f0 -> "MOVE"
		 * f1 -> Temp()
		 * f2 -> Exp()
		 */

		public String visit(MoveStmt n) {

			return null;
		}

		/**
		 * f0 -> "PRINT"
		 * f1 -> SimpleExp()
		 */
		public String visit(PrintStmt n) {			

			return null;
		}

		/**
		 * f0 -> Call()
		 *       | HAllocate()
		 *       | BinOp()
		 *       | SimpleExp()
		 */
		public String visit(Exp n) {
			return  n.f0.accept(this);
		}

}
