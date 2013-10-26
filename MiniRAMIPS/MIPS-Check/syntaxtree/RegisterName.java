//
// Generated by JTB 1.3.2
//

package syntaxtree;

/**
 * Grammar production:
 * f0 -> "zero"
 *       | "at"
 *       | "v0"
 *       | "v1"
 *       | "a0"
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
 *       | "k0"
 *       | "k1"
 *       | "gp"
 *       | "sp"
 *       | "s8"
 *       | "fp"
 *       | "ra"
 */
public class RegisterName implements Node {
   public NodeChoice f0;

   public RegisterName(NodeChoice n0) {
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

