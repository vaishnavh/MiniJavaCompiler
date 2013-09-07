package visitor;

import java.util.HashMap;

import symboltable.ClassSymbol;
import syntaxtree.ArrayType;
import syntaxtree.BooleanType;
import syntaxtree.ClassDeclaration;
import syntaxtree.ClassExtendsDeclaration;
import syntaxtree.FormalParameter;
import syntaxtree.Identifier;
import syntaxtree.IntegerType;
import syntaxtree.MainClass;
import syntaxtree.MethodDeclaration;
import syntaxtree.Type;
import syntaxtree.VarDeclaration;

public class SymbolTableVisitor extends  GJNoArguDepthFirst<String> {
	
	String className, methodName;
	boolean inMethod;

	public HashMap<String, ClassSymbol> classes = new HashMap<String, ClassSymbol>();	//Hash map of classes
	public boolean correct;
	
	ClassSymbol getClass(String name){
		return classes.get(name);
	}   
	
	
	/**
	    * f0 -> "class"
	    * f1 -> Identifier()
	    * f2 -> "{"
	    * f3 -> "public"
	    * f4 -> "static"
	    * f5 -> "void"
	    * f6 -> "main"
	    * f7 -> "("
	    * f8 -> "String"
	    * f9 -> "["
	    * f10 -> "]"
	    * f11 -> Identifier()
	    * f12 -> ")"
	    * f13 -> "{"
	    * f14 -> PrintStatement()
	    * f15 -> "}"
	    * f16 -> "}"
	    */
	   public String visit(MainClass n) {
	      String _ret=null;
	      className = n.f1.f0.toString();
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      n.f3.accept(this);
	      n.f4.accept(this);
	      n.f5.accept(this);
	      n.f6.accept(this);
	      inMethod = true;
	      methodName = "main";
	      n.f7.accept(this);
	      n.f8.accept(this);
	      n.f9.accept(this);
	      n.f10.accept(this);
	      n.f11.accept(this);
	      n.f12.accept(this);
	      n.f13.accept(this);
	      n.f14.accept(this);
	      n.f15.accept(this);
	      inMethod = false;
	      n.f16.accept(this);
	      return _ret;
	   }

	
	   /**
	    * f0 -> "class"
	    * f1 -> Identifier()
	    * f2 -> "{"
	    * f3 -> ( VarDeclaration() )*
	    * f4 -> ( MethodDeclaration() )*
	    * f5 -> "}"
	    */
	public String visit(ClassDeclaration n) {
		inMethod = false;		
		  className = n.f1.f0.toString();
		  ClassSymbol newClassSymbol = new ClassSymbol(className);
		  classes.put(className, newClassSymbol);
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      n.f3.accept(this);
	      n.f4.accept(this);
	      n.f5.accept(this);
	      return null;
	   }
	   
	   /**
	    * f0 -> "class"
	    * f1 -> Identifier()
	    * f2 -> "extends"
	    * f3 -> Identifier()
	    * f4 -> "{"
	    * f5 -> ( VarDeclaration() )*
	    * f6 -> ( MethodDeclaration() )*
	    * f7 -> "}"
	    */
	   
	public String visit(ClassExtendsDeclaration n) { 
		inMethod = false;
		  className = n.f1.f0.toString();
		  ClassSymbol newClassSymbol = new ClassSymbol(className);
		  newClassSymbol.parentName = n.f3.f0.toString();
		  classes.put(className, newClassSymbol);
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      n.f3.accept(this);
	      n.f4.accept(this);
	      n.f5.accept(this);
	      n.f6.accept(this);
	      n.f7.accept(this);
	      return null;
	   }
	
	   /**
	    * f0 -> Type()
	    * f1 -> Identifier()
	    * f2 -> ";"
	    */
	   public String visit(VarDeclaration n) {
		   
		   if(inMethod){
			   getClass(className).addLocalVariableToMethod(n.f0.accept(this), n.f1.f0.toString(), methodName);
			}else{
			   getClass(className).addVariable(n.f0.accept(this), n.f1.f0.toString());
			}
	      
	      n.f1.accept(this);
	      n.f2.accept(this);
	      return null;
	   }
	

	   /**
	    * f0 -> "public"
	    * f1 -> Type()
	    * f2 -> Identifier()
	    * f3 -> "("
	    * f4 -> ( FormalParameterList() )?
	    * f5 -> ")"
	    * f6 -> "{"
	    * f7 -> ( VarDeclaration() )*
	    * f8 -> ( Statement() )*
	    * f9 -> "return"
	    * f10 -> Expression()
	    * f11 -> ";"
	    * f12 -> "}"
	    */
	   
	   public String visit(MethodDeclaration n) {
		   inMethod = true;
		   methodName = n.f2.f0.toString();
		      n.f0.accept(this);
		      getClass(className).addMethod(methodName, n.f1.accept(this));		      
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
		      n.f12.accept(this);
		      inMethod = false;
		      return null;
		   }
	   
	   /**
	    * f0 -> Type()
	    * f1 -> Identifier()
	    */
	   public String visit(FormalParameter n) {
	      getClass(className).addParameterToMethod(n.f0.accept(this), n.f1.f0.toString(), methodName);
	      n.f1.accept(this);
	      return null;
	   }
	   

	   /**
	    * f0 -> ArrayType()
	    *       | BooleanType()
	    *       | IntegerType()
	    *       | Identifier()
	    */
	   public String visit(Type n) {
	      // TA CODE:
	      return n.f0.accept(this);
	   }

	   /**
	    * f0 -> "int"
	    * f1 -> "["
	    * f2 -> "]"
	    */
	   public String visit(ArrayType n) {
	      //TA CODE:
		   return (String)"int[]";
	   }

	   /**
	    * f0 -> "boolean"
	    */
	   public String visit(BooleanType n) {
		      //TA CODE:
			   return (String)"boolean";
	   }

	   /**
	    * f0 -> "int"
	    */
	   public String visit(IntegerType n) {
		      //TA CODE:
			   return (String)"int";
	   }   
	   
	   
	   /**
	    * f0 -> <IDENTIFIER>
	    */
	   public String visit(Identifier n) {
	      String _ret=n.f0.toString();
	      n.f0.accept(this);
	      return _ret;
	   }

}
