//
// Generated by JTB 1.3.2
//

package syntaxtree;

/**
 * Grammar production:
 * f0 -> ".alias" Register() "," Register()
 *       | ".bgnb" Expr()
 *       | ".endb" Expr()
 *       | ".file" Constant() <STRING>
 *       | ".galive"
 *       | ".gjaldef"
 *       | ".gjrlive"
 *       | ".lab" <IDENTIFIER>
 *       | ".livereg" Expr() "," Expr()
 *       | ".noalias" Register() "," Register()
 *       | ".option" "flag"
 *       | ".verstamp" Constant() Constant()
 *       | ".vreg" Expr() "," Expr()
 */
public class CompilerDir implements Node {
   public NodeChoice f0;

   public CompilerDir(NodeChoice n0) {
      f0 = n0;
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
