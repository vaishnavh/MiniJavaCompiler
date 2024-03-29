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
public class Translator extends GJNoArguDepthFirst<String>{
	public HashMap<String, ProcedureBlock> livenesses = new HashMap<String, ProcedureBlock>(); //Develops graphs per procedure
	public ProcedureBlock currentBlock;
	public Block currentStatement;
	private boolean called;	
	boolean currTemp, currInteger;
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
	public void unset(){
		currTemp = false;
		currInteger = false;
	}
	/**
	 * f0 -> "MAIN"
	 * f1 -> StmtList()
	 * f2 -> "END"
	 * f3 -> ( Procedure() )*
	 * f4 -> <EOF>
	 */
	public String visit(Goal n) {
		currTemp = false;
		currInteger = false;
		called = false;
		String _ret=null;
		ProcedureBlock mainBlock;
		mainBlock = this.livenesses.get("MAIN");
		currentBlock = mainBlock;
		currentBlock.printSpecs();
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		System.out.println("\nEND");
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
			String label = n.node.accept(this);
			if(label!=null){
				print("\n"+this.currentBlock.procedureName+"_"+label);
			}
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
		currentBlock.printSpecs();
		//Empty statement for parameters passed
		//currentBlock.initializeArguments(n.f2.f0.toString());

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
		Register r1 = this.currentBlock.obtainRegister(n.f1.accept(this), false, true);
		if(r1 == null){
			return null;
		}
		Register r2 = this.currentBlock.obtainRegister(n.f3.accept(this), true, true);	//Either 	some s/t or v1

		print("\nHSTORE");
		print(r1.toString());

		print(n.f2.accept(this));
		print(r2.toString());
		return null;
	}

	/**
	 * f0 -> "HLOAD"
	 * f1 -> Temp()
	 * f2 -> Temp()
	 * f3 -> IntegerLiteral()
	 */
	public String visit(HLoadStmt n) {
		Register r1 = this.currentBlock.obtainRegister(n.f1.accept(this), false, false);// s or t or SPILLED
		if(r1 == null){
			return null;
		}
		Register r2 = this.currentBlock.obtainRegister(n.f2.accept(this), true, true); //v1 or s or t
		if(r1.type == Type.SPILLED){

			print("\nHLOAD v0 ");
			print(r2.toString());
			print(n.f3.accept(this));
			Register v = new Register(0, Type.V);
			Register.move(r1, v);
		}else{
			print("\nHLOAD");
			print(r1.toString());
			print(r2.toString());
			print(n.f3.accept(this));
		}
		return null;
	}

	/**
	 * f0 -> "MOVE"
	 * f1 -> Temp()
	 * f2 -> Exp()
	 */

	public String visit(MoveStmt n) {
		String temp = n.f1.accept(this);
		Register r = this.currentBlock.obtainRegister(temp, true,  false); //SPILLED or s or t
		String  ret = n.f2.accept(this);
		if(r==null){ //Useless move
			return null;
		}
		if(r.type == Type.SPILLED){
			print("\nMOVE v1"); print(ret);
			Register.move(r, new Register(1,Type.V));		
		}else{
			print("\nMOVE "+r.toString()+" "+ret); //ret is either v0 or  HALLOCATE 4 or PLUS s0 s1
		}
		return null;
	}

	/**
	 * f0 -> "PRINT"
	 * f1 -> SimpleExp()
	 */
	public String visit(PrintStmt n) {			
		String ret = n.f1.accept(this);		
		print("\nPRINT");
		print(ret);

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


	/**
	 * f0 -> "CALL"
	 * f1 -> SimpleExp()
	 * f2 -> "("
	 * f3 -> ( Temp() )*
	 * f4 -> ")"
	 */
	public String visit(Call n) {
		this.currentBlock.saveCallerRegisters();
		called = true;
		n.f3.accept(this);
		called = false;		
		String ret = n.f1.accept(this);
		print("\nCALL "+ret);
		this.currentBlock.loadCallerRegisters();
		this.currTemp = false;
		return "v0";
	}


	public String visit(NodeListOptional n) {
		if ( n.present() ) {
			int _count=0;	         
			for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
				String ret = e.nextElement().accept(this);
				if(called){
					Register r = this.currentBlock.obtainRegister(ret, false, true);
					if(_count<4){
						Register.move(new Register(_count, Type.A), r);
					}else{
						int x = _count-3;
						print("\nPASSARG "+x +" "+r.toString());
					}
				}	            
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
		String ret = n.f1.accept(this);
		return "HALLOCATE "+ret;
	}

	/**
	 * f0 -> Operator()
	 * f1 -> Temp()
	 * f2 -> SimpleExp()
	 */
	public String visit(BinOp n) {

		String ret = n.f2.accept(this);
		Register r = this.currentBlock.obtainRegister(n.f1.accept(this), true, true);
		String op ="";
		switch(n.f0.f0.which){
		case 0: op = "LT"; break;
		case 1: op ="PLUS"; break;
		case 2: op ="MINUS"; break;
		case 3: op ="TIMES";break;
		}
		return op+" "+r.toString()+" "+ret;
	}


	/**
	 * f0 -> Temp()
	 *       | IntegerLiteral()
	 *       | Label()
	 */
	public String visit(SimpleExp n) {	
		unset();
		String ret = n.f0.accept(this);
		if(currTemp){
			Register r = this.currentBlock.obtainRegister(ret, false, true); //Always be used for loading; If storing we wouldn't have called this?
			ret = r.toString();
		}
		unset();
		return ret;
	}

	/**
	 * f0 -> "TEMP"
	 * f1 -> IntegerLiteral()
	 */
	public String visit(Temp n) {		
		this.currTemp = true;
		return n.f1.f0.toString();
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public String visit(IntegerLiteral n) {		
		this.currInteger = true;		
		return n.f0.toString();
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public String visit(Label n) {
		return n.f0.toString();
	}



	/**
	 * f0 -> "CJUMP"
	 * f1 -> Temp()
	 * f2 -> Label()
	 */
	public String visit(CJumpStmt n) {
		n.f1.accept(this);
		Register r = this.currentBlock.obtainRegister(n.f1.accept(this),true, true);
		System.out.println("\nCJUMP "+r.toString()+" "+this.currentBlock.procedureName+"_"+n.f2.f0.toString());

		return null;
	}

	/**
	 * f0 -> "JUMP"
	 * f1 -> Label()
	 */
	public String visit(JumpStmt n) {
		//this.currentBlock.jumpTo(n.f1.f0.toString());
		// this.currentBlock.snapFlow(); 
		System.out.println("\nJUMP " +this.currentBlock.procedureName+"_"+ n.f1.f0.toString());
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
		currentBlock.saveCalleeRegisters();
		currentBlock.movArguments();		


		n.f1.accept(this);
		n.f2.accept(this);
		//Return statements
		// this.currentBlock.unsetLabel();
		this.beginNewStatement();	    

		String ret=n.f3.accept(this);
		print("\nMOVE v0 "+ret);
		currentBlock.loadCalleeRegisters();
		print("\nEND");
		return null;
	}




	/**
	 * f0 -> "NOOP"
	 */
	public String visit(NoOpStmt n) {
		System.out.println("NOOP");
		return null;
	}

	/**
	 * f0 -> "ERROR"
	 */
	public String visit(ErrorStmt n) {
		System.out.println("ERROR");
		return null;
	}

}
