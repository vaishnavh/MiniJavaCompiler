package visitor;

import java.util.Enumeration;

import syntaxtree.ALoadStmt;
import syntaxtree.AStoreStmt;
import syntaxtree.BinOp;
import syntaxtree.CJumpStmt;
import syntaxtree.CallStmt;
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
import syntaxtree.NodeList;
import syntaxtree.NodeListOptional;
import syntaxtree.NodeOptional;
import syntaxtree.NodeSequence;
import syntaxtree.NodeToken;
import syntaxtree.Operator;
import syntaxtree.PassArgStmt;
import syntaxtree.PrintStmt;
import syntaxtree.Procedure;
import syntaxtree.Reg;
import syntaxtree.SimpleExp;
import syntaxtree.SpilledArg;
import syntaxtree.Stmt;
import syntaxtree.StmtList;
import syntaxtree.VariablePackingInfo;

public class Translator extends GJNoArguDepthFirst<String>{


	public void print(String  x){
		System.out.print(x+" ");
	}
	public void print(int x){
		System.out.print(x+" ");
	}

	public void instruct(String x){
		System.out.print("\n\t"+x);
	}
	//
	// Auto class visitors--probably don't need to be overridden.
	//
	public String visit(NodeList n) {
		String _ret=null;
		int _count=0;
		for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
			e.nextElement().accept(this);
			_count++;
		}
		return _ret;
	}

	public String visit(NodeListOptional n) {
		if ( n.present() ) {
			String _ret=null;
			int _count=0;
			for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
				e.nextElement().accept(this);
				_count++;
			}
			return _ret;
		}
		else
			return null;
	}

	public String visit(NodeOptional n) {
		if ( n.present() )
			return n.node.accept(this);
		else
			return null;
	}

	public String visit(NodeSequence n) {
		String _ret=null;
		int _count=0;
		for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
			e.nextElement().accept(this);
			_count++;
		}
		return _ret;
	}

	public String visit(NodeToken n) { return null; }

	//
	// User-generated visitor methods below
	//

	/**
	 * f0 -> "MAIN"
	 * f1 -> "["
	 * f2 -> IntegerLiteral()
	 * f3 -> "]"
	 * f4 -> "["
	 * f5 -> IntegerLiteral()
	 * f6 -> "]"
	 * f7 -> "["
	 * f8 -> IntegerLiteral()
	 * f9 -> "]"
	 * f10 -> StmtList()
	 * f11 -> "END"
	 * f12 -> ( Procedure() )*
	 * f13 -> VariablePackingInfo()
	 * f14 -> <EOF>
	 */
	public String visit(Goal n) {
		print("\t.text\n\t.globl\tmain\nmain:\n\tmove $fp, $sp");

		String _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		n.f8.accept(this);
		n.f9.accept(this);
		n.f10.accept(this);
		n.f11.accept(this);
		instruct("j $ra");
		n.f12.accept(this);
		n.f13.accept(this);
		n.f14.accept(this);

		return _ret;
	}

	/**
	 * f0 -> ( ( Label() )? Stmt() )*
	 */
	public String visit(StmtList n) {
		String _ret=null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> Label()
	 * f1 -> "["
	 * f2 -> IntegerLiteral()
	 * f3 -> "]"
	 * f4 -> "["
	 * f5 -> IntegerLiteral()
	 * f6 -> "]"
	 * f7 -> "["
	 * f8 -> IntegerLiteral()
	 * f9 -> "]"
	 * f10 -> StmtList()
	 * f11 -> "END"
	 */
	public String visit(Procedure n) {
		Integer stackSize = 4*Integer.parseInt(n.f5.f0.toString());
		Integer argSize = 4*Integer.parseInt(n.f2.f0.toString());
		Integer maxArgSize = 4*Integer.parseInt(n.f8.toString());
		Integer moveSP = 8+stackSize; //ra - fp - stackSize
		print("\n\t.text\n\t.globl\t");
		String procName = n.f0.f0.toString();
		print(procName);
		print("\n");
		print(procName);
		print(":");
		instruct("sw $fp, -8($sp)");
		instruct("move $fp, $sp");
		instruct("subu $sp, $sp, "+moveSP);
		instruct("sw $ra, -4($fp)");
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		n.f8.accept(this);
		n.f9.accept(this);
		n.f10.accept(this);
		n.f11.accept(this);
		instruct("lw $ra, -4($fp)");
		instruct("lw $fp, "+stackSize+"($sp)");
		instruct("addu $sp, $sp, "+moveSP);
		instruct("j $ra");
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
	 *       | ALoadStmt()
	 *       | AStoreStmt()
	 *       | PassArgStmt()
	 *       | CallStmt()
	 */
	public String visit(Stmt n) {
		String _ret=null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "NOOP"
	 */
	public String visit(NoOpStmt n) {
		instruct("nop");
		return null;
	}

	/**
	 * f0 -> "ERROR"
	 */
	public String visit(ErrorStmt n) {          
        instruct("li $v0, 4");
		instruct("la $a0, str_er");
		instruct("syscall");
		instruct("li $v0, 10");
		instruct("syscall");

		return null;
	}

	/**
	 * f0 -> "CJUMP"
	 * f1 -> Reg()
	 * f2 -> Label()
	 */
	public String visit(CJumpStmt n) {
		instruct("beqz $"+n.f1.f0.toString()+" "+n.f2.f0.toString());
		return null;
	}

	/**
	 * f0 -> "JUMP"
	 * f1 -> Label()
	 */
	public String visit(JumpStmt n) {
		instruct("b "+n.f1.f0.toString());
		return null;
	}

	/**
	 * f0 -> "HSTORE"
	 * f1 -> Reg()
	 * f2 -> IntegerLiteral()
	 * f3 -> Reg()
	 */
	public String visit(HStoreStmt n) {
		instruct("sw $"+n.f3.f0.toString()+" "+n.f2.f0.toString()+"($"+n.f1.f0.toString()+")");
		return null;
	}

	/**
	 * f0 -> "HLOAD"
	 * f1 -> Reg()
	 * f2 -> Reg()
	 * f3 -> IntegerLiteral()
	 */
	public String visit(HLoadStmt n) {
		instruct("lw $"+n.f1.f0.toString()+" "+n.f3.f0.toString()+"($"+n.f3.f0.toString()+")");
		return null;
	}

	/**
	 * f0 -> "MOVE"
	 * f1 -> Reg()
	 * f2 -> Exp()
	 */
	public String visit(MoveStmt n) {
		String _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "PRINT"
	 * f1 -> SimpleExp()
	 */
	public String visit(PrintStmt n) {
		String _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "ALOAD"
	 * f1 -> Reg()
	 * f2 -> SpilledArg()
	 */
	public String visit(ALoadStmt n) {
		String _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "ASTORE"
	 * f1 -> SpilledArg()
	 * f2 -> Reg()
	 */
	public String visit(AStoreStmt n) {
		String _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "PASSARG"
	 * f1 -> IntegerLiteral()
	 * f2 -> Reg()
	 */
	public String visit(PassArgStmt n) {
		String _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "CALL"
	 * f1 -> SimpleExp()
	 */
	public String visit(CallStmt n) {
		String _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}

	/**
	 * f0 -> HAllocate()
	 *       | BinOp()
	 *       | SimpleExp()
	 */
	public String visit(Exp n) {
		String _ret=null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "HALLOCATE"
	 * f1 -> SimpleExp()
	 */
	public String visit(HAllocate n) {
		String _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}

	/**
	 * f0 -> Operator()
	 * f1 -> Reg()
	 * f2 -> SimpleExp()
	 */
	public String visit(BinOp n) {
		String _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "LT"
	 *       | "PLUS"
	 *       | "MINUS"
	 *       | "TIMES"
	 *       | "BITOR"
	 *       | "BITAND"
	 *       | "LSHIFT"
	 *       | "RSHIFT"
	 *       | "BITXOR"
	 */
	public String visit(Operator n) {
		String _ret=null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "SPILLEDARG"
	 * f1 -> IntegerLiteral()
	 */
	public String visit(SpilledArg n) {
		String _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		return _ret;
	}

	/**
	 * f0 -> Reg()
	 *       | IntegerLiteral()
	 *       | Label()
	 */
	public String visit(SimpleExp n) {
		String _ret=null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "a0"
	 *       | "a1"
	 *       | "a2"
	 *       | "a3"
	 *       | "t0"
	 *       | "t1"
	 *       | "t2"
	 *       | "t3"
	 *       | "t4"
	 *       | "t5"
	 *       | "t6"
	 *       | "t7"
	 *       | "s0"
	 *       | "s1"
	 *       | "s2"
	 *       | "s3"
	 *       | "s4"
	 *       | "s5"
	 *       | "s6"
	 *       | "s7"
	 *       | "t8"
	 *       | "t9"
	 *       | "v0"
	 *       | "v1"
	 */
	public String visit(Reg n) {
		String _ret=null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> <INTEGER_LITERAL>
	 */
	public String visit(IntegerLiteral n) {
		String _ret=null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> <IDENTIFIER>
	 */
	public String visit(Label n) {
		String _ret=null;
		n.f0.accept(this);
		return _ret;
	}

	/**
	 * f0 -> "// Number of  vars after packing ="
	 * f1 -> IntegerLiteral()
	 * f2 -> "; Number of Spilled vars ="
	 * f3 -> IntegerLiteral()
	 */
	public String visit(VariablePackingInfo n) {
		String _ret=null;
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		n.f3.accept(this);
		return _ret;
	}

}