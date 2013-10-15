//
// Generated by JTB 1.3.2
//

package syntaxtree;

/**
 * Grammar production:
 * f0 -> "PASSARG"
 * f1 -> IntegerLiteral()
 * f2 -> Reg()
 */
public class PassArgStmt implements Node {
   public NodeToken f0;
   public IntegerLiteral f1;
   public Reg f2;

   public PassArgStmt(NodeToken n0, IntegerLiteral n1, Reg n2) {
      f0 = n0;
      f1 = n1;
      f2 = n2;
   }

   public PassArgStmt(IntegerLiteral n0, Reg n1) {
      f0 = new NodeToken("PASSARG");
      f1 = n0;
      f2 = n1;
   }

   public void accept(visitor.Visitor v) {
      v.visit(this);
   }
   public <R,A> R accept(visitor.GJVisitor<R,A> v, A argu) {
      return v.visit(this,argu);
   }
   public <R> R accept(visitor.GJNoArguVisitor<R> v) {
      return v.visit(this);
   }
   public <A> void accept(visitor.GJVoidVisitor<A> v, A argu) {
      v.visit(this,argu);
   }
}

