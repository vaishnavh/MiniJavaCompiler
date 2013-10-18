package visitor;

import java.util.Enumeration;
import java.util.HashMap;

import syntaxtree.BinOp;
import syntaxtree.CJumpStmt;
import syntaxtree.Call;
import syntaxtree.Exp;
import syntaxtree.Goal;
import syntaxtree.HAllocate;
import syntaxtree.HLoadStmt;
import syntaxtree.HStoreStmt;
import syntaxtree.IntegerLiteral;
import syntaxtree.JumpStmt;
import syntaxtree.Label;
import syntaxtree.MoveStmt;
import syntaxtree.Node;
import syntaxtree.NodeListOptional;
import syntaxtree.NodeOptional;
import syntaxtree.Operator;
import syntaxtree.PrintStmt;
import syntaxtree.Procedure;
import syntaxtree.SimpleExp;
import syntaxtree.Stmt;
import syntaxtree.StmtExp;
import syntaxtree.StmtList;
import syntaxtree.Temp;
import datastore.Block;
import datastore.ProcedureBlock;

public class LivenessAnalyser extends GJNoArguDepthFirst<String>{
	public HashMap<String, ProcedureBlock> livenesses = new HashMap<String, ProcedureBlock>(); //Develops graphs per procedure
	public ProcedureBlock currentBlock;
	public Block currentStatement;
	private boolean called;
	public void beginNewStatement(){
		//Assumes currentBlock has been set.
		this.currentStatement = currentBlock.createNewStatement();
	}

	/**
	 * f0 -> "MAIN"
	 * f1 -> StmtList()
	 * f2 -> "END"
	 * f3 -> ( Procedure() )*
	 * f4 -> <EOF>
	 */
	public String visit(Goal n) {
		called = false;
		String _ret=null;
		ProcedureBlock mainBlock = new ProcedureBlock();
		this.livenesses.put("MAIN", mainBlock);
		currentBlock = mainBlock;
		mainBlock.procedureName = "MAIN";
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		return _ret;
	}

	/**
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public String visit(StmtList n) {
		//Assumes that currentBlock is set			   
		String _ret=null;
		n.f0.accept(this);
		return _ret;
	}

	//Only in case of a label
	public String visit(NodeOptional n) {
		if ( n.present() ){
			this.currentBlock.beginLabel(n.node.accept(this));
			return null;
		}
		else{
			this.currentBlock.unsetLabel();
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
		ProcedureBlock newBlock = new ProcedureBlock();
		this.livenesses.put(n.f0.f0.toString(), newBlock);
		currentBlock = newBlock;
	    newBlock.procedureName = n.f0.f0.toString();
		//Empty statement for parameters passed
		currentBlock.initializeArguments(n.f2.f0.toString());
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
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
	 * f0 -> "HSTORE"
	 * f1 -> Temp()
	 * f2 -> IntegerLiteral()
	 * f3 -> Temp()
	 */
	public String visit(HStoreStmt n) {
		this.currentBlock.use(n.f1.accept(this));
		this.currentBlock.use(n.f3.accept(this));
		String _ret=null;
		return _ret;
	}

	/**
	 * f0 -> "HLOAD"
	 * f1 -> Temp()
	 * f2 -> Temp()
	 * f3 -> IntegerLiteral()
	 */
	public String visit(HLoadStmt n) {
		String _ret=null;
		this.currentBlock.define(n.f1.accept(this));
		this.currentBlock.use(n.f2.accept(this));
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "MOVE"
	 * f1 -> Temp()
	 * f2 -> Exp()
	 */
	public String visit(MoveStmt n) {
		this.currentBlock.define(n.f1.accept(this));
		n.f2.accept(this);
		return null;
	}

	/**
	 * f0 -> "PRINT"
	 * f1 -> SimpleExp()
	 */
	public String visit(PrintStmt n) {	
		this.currentBlock.use(n.f1.accept(this));
		return null;
	}

	/**
	 * f0 -> Call()
	 *       | HAllocate()
	 *       | BinOp()
	 *       | SimpleExp()
	 */
	public String visit(Exp n) {
		this.currentBlock.use(n.f0.accept(this));
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
		String _ret=null;
		n.f0.accept(this);
		this.currentBlock.use(n.f1.accept(this));
		n.f2.accept(this);
		called = true;
		n.f3.accept(this);
		called = false;
		n.f4.accept(this);
		return _ret;
	}


	   public String visit(NodeListOptional n) {
	      if ( n.present() ) {
	         int _count=0;	         
	         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
	        	 String ret = e.nextElement().accept(this);
	        	if(called){
	        		this.currentBlock.use(ret);
	        	}	            
	            _count++;
	         }
	         if(called){
	        	 this.currentBlock.setMaxArg(_count);
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
		this.currentBlock.use(n.f1.accept(this));
		return null;
	}

	/**
	 * f0 -> Operator()
	 * f1 -> Temp()
	 * f2 -> SimpleExp()
	 */
	public String visit(BinOp n) {
		this.currentBlock.use(n.f1.accept(this));
		this.currentBlock.use(n.f2.accept(this));
		return null;
	}


	/**
	 * f0 -> Temp()
	 *       | IntegerLiteral()
	 *       | Label()
	 */
	public String visit(SimpleExp n) {
		this.currentBlock.use(n.f0.accept(this));		
		return null;
	}

	/**
	 * f0 -> "TEMP"
	 * f1 -> IntegerLiteral()
	 */
	public String visit(Temp n) {
		String _ret=null;
		n.f0.accept(this);		
		return n.f1.f0.toString();
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public String visit(IntegerLiteral n) {
		n.f0.accept(this);
		return null;
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public String visit(Label n) {
		String _ret=null;
		n.f0.accept(this);
		return n.f0.toString();
	}

	

	   /**
	    * f0 -> "CJUMP"
	    * f1 -> Temp()
	    * f2 -> Label()
	    */
	   public String visit(CJumpStmt n) {
	      this.currentBlock.use(n.f1.accept(this));
	      this.currentBlock.jumpTo(n.f2.f0.toString());
	      return null;
	   }

	   /**
	    * f0 -> "JUMP"
	    * f1 -> Label()
	    */
	   public String visit(JumpStmt n) {
		  this.currentBlock.jumpTo(n.f1.f0.toString());
		  this.currentBlock.snapFlow(); 
	      return null;
	   }

	   
	   /**
	    * f0 -> "BEGIN"
	    * f1 -> StmtList()
	    * f2 -> "RETURN"
	    * f3 -> SimpleExp()
	    * f4 -> "END"
	    */
	   public String visit(StmtExp n) {
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      //Return statements
	      this.currentBlock.unsetLabel();
	      this.beginNewStatement();	      
	      n.f3.accept(this);
	      return null;
	   }

}
