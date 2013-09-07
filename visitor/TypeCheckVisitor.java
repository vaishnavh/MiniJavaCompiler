package visitor;

import java.awt.TrayIcon.MessageType;
import java.util.HashMap;
import java.util.Iterator;

import symboltable.ClassSymbol;
import symboltable.Message.IdentifierType;
import symboltable.Message.ReturnType;

import symboltable.Message;
import symboltable.Message.ReturnType;
import symboltable.MethodSymbol;
import symboltable.VariableSymbol;
import syntaxtree.AndExpression;
import syntaxtree.ArrayAssignmentStatement;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.ArrayType;
import syntaxtree.AssignmentStatement;
import syntaxtree.BooleanType;
import syntaxtree.BracketExpression;
import syntaxtree.ClassDeclaration;
import syntaxtree.ClassExtendsDeclaration;
import syntaxtree.CompareExpression;
import syntaxtree.Expression;
import syntaxtree.ExpressionList;
import syntaxtree.ExpressionRest;
import syntaxtree.FalseLiteral;
import syntaxtree.Identifier;
import syntaxtree.IfStatement;
import syntaxtree.IntegerLiteral;
import syntaxtree.IntegerType;
import syntaxtree.MainClass;
import syntaxtree.MessageSend;
import syntaxtree.MethodDeclaration;
import syntaxtree.MinusExpression;
import syntaxtree.NotExpression;
import syntaxtree.PlusExpression;
import syntaxtree.PrimaryExpression;
import syntaxtree.PrintStatement;
import syntaxtree.ThisExpression;
import syntaxtree.TimesExpression;
import syntaxtree.TrueLiteral;
import syntaxtree.Type;
import syntaxtree.VarDeclaration;
import syntaxtree.WhileStatement;

public class TypeCheckVisitor extends GJDepthFirst<String,Message>{
	
	 String className, methodName;

	 public HashMap<String, ClassSymbol> classes;	//Hash map of classes
	 public MethodSymbol getMethod(String cn, String mn){
		 return classes.get(cn).methods.get(mn);
	 }
	 public static void error(){
			System.out.println("Type error");
			System.exit(0);
		}	
	 public static void error(String option){
			System.out.println("Type error "+option);
			System.exit(0);
		}
	   
	 public VariableSymbol getVariable(String vn, String cn, String mn){
		 return classes.get(cn).getVariable(vn, mn);
	 }
	 
	 public VariableSymbol getVariableInScope(String variableName){
		 return getVariable(variableName, className, methodName);
	 }
	 
	 public boolean isBool(String check){
		 return (check.compareTo("boolean")==0);
	 }
	 public boolean isInt(String check){
		 return (check.compareTo("int")==0);
	 }
	 public boolean isArray(String check){
		 return (check.compareTo("int[]")==0);
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
	   public String visit(MainClass n, Message A) {
	      String _ret=null;
	      className = n.f1.f0.toString();
	      methodName = "main";
	      n.f0.accept(this, A);
	      n.f1.accept(this, new Message(className, null));
	      n.f2.accept(this, A);
	      n.f3.accept(this, A);
	      n.f4.accept(this, A);
	      n.f5.accept(this, A);
	      n.f6.accept(this, A);
	      n.f7.accept(this, A);
	      n.f8.accept(this, A);
	      n.f9.accept(this, A);
	      n.f10.accept(this, A);
	      n.f11.accept(this, new Message(className, methodName));
	      n.f12.accept(this, A);
	      n.f13.accept(this, A);
	      
	      n.f14.accept(this, new Message(className, methodName));
	      n.f15.accept(this, A);
	      n.f16.accept(this, A);
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
	   public String visit(ClassDeclaration n, Message A) {
	      String _ret=null;
	      className = n.f1.f0.toString();
	      n.f0.accept(this, A);
	      n.f1.accept(this, new Message(className, null));
	      n.f2.accept(this, A);
	      n.f3.accept(this, A);
	      n.f4.accept(this, A);
	      n.f5.accept(this, A);
	      return _ret;
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
	   public String visit(ClassExtendsDeclaration n, Message A) {
	      String _ret=null;
	      className = n.f1.f0.toString();
	      n.f0.accept(this, A);
	      n.f1.accept(this, new Message(className, null));
	      n.f2.accept(this, A);
	      n.f3.accept(this, new Message(className, null));
	      n.f4.accept(this, A);
	      n.f5.accept(this, A);
	      n.f6.accept(this, A);
	      n.f7.accept(this, A);
	      return _ret;
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
	   
	   public String visit(MethodDeclaration n, Message A) {		  
		   	  methodName = n.f2.f0.toString();
		   	  
		      n.f0.accept(this, A);	      
		      n.f2.accept(this, A);
		      n.f3.accept(this, A);
		      n.f4.accept(this, A);
		      n.f5.accept(this, A);
		      n.f6.accept(this, A);
		      n.f7.accept(this, A);
		      n.f8.accept(this, A);
		      n.f9.accept(this, A);
		      if(n.f1.accept(this, new Message(className, methodName, Message.IdentifierType.CLASS, Message.ReturnType.NAME)).compareTo(n.f10.accept(this, new Message (className, methodName, Message.IdentifierType.NONE, Message.ReturnType.TYPE)))!=0){
		    	  error("Wrong return");
		      }
		      n.f11.accept(this, A);
		      n.f12.accept(this, A);
		      return null;
		   }

	   /**
	    * f0 -> ArrayType()
	    *       | BooleanType()
	    *       | IntegerType()
	    *       | Identifier()
	    */
	   public String visit(Type n, Message A) {
	      // TA CODE:
	      return n.f0.accept(this, A);
	   }

	   /**
	    * f0 -> "int"
	    * f1 -> "["
	    * f2 -> "]"
	    */
	   public String visit(ArrayType n, Message A) {
	      //TA CODE:
		   return (String)"int[]";
	   }

	   /**
	    * f0 -> "boolean"
	    */
	   public String visit(BooleanType n, Message A) {
		      //TA CODE:
			   return (String)"boolean";
	   }

	   /**
	    * f0 -> "int"
	    */
	   public String visit(IntegerType n, Message A) {
		      //TA CODE:
			   return (String)"int";
	   }   
	   
	   /**
	    * f0 -> Identifier()
	    * f1 -> "="
	    * f2 -> Expression()
	    * f3 -> ";"
	    */
	   public String visit(AssignmentStatement n, Message A) {
	      String _ret=null;
	      String leftType = n.f0.accept(this, new Message(className, methodName, Message.IdentifierType.VARIABLE, Message.ReturnType.TYPE));
	      String  rightType = n.f2.accept(this, new Message (className, methodName, Message.IdentifierType.NONE, Message.ReturnType.TYPE));
	      System.out.println(leftType);
	      if(leftType.compareTo(rightType)!=0)
	    	  error("Mismatching assignment types");
	      n.f1.accept(this, A);	      
	      n.f3.accept(this, A);
	      return _ret;
	   }
	   
	   /**
	    * f0 -> Identifier()
	    * f1 -> "["
	    * f2 -> Expression()
	    * f3 -> "]"
	    * f4 -> "="
	    * f5 -> Expression()
	    * f6 -> ";"
	    */
	   public String visit(ArrayAssignmentStatement n, Message A) {
	      String _ret=null;
	      Message B = new Message(className, methodName, Message.IdentifierType.NONE, Message.ReturnType.TYPE);
	      n.f0.accept(this, new Message(className, methodName));
	      VariableSymbol v = this.getVariableInScope(n.f0.f0.toString());
	      if(!isArray(n.f0.accept(this, new Message(className, methodName, Message.IdentifierType.VARIABLE, Message.ReturnType.TYPE)))) error("Array must be of integer");
	      n.f1.accept(this, A);
	      if(!isInt(n.f2.accept(this, B))){
	    	  error("Index shoould be integer");
	      }
	      n.f3.accept(this, A);
	      n.f4.accept(this, A);	  
	      if(!this.isInt(n.f5.accept(this, B))) error("Expression must be integer when assigned to array");
	      n.f6.accept(this, A);
	      return _ret;
	   }
	   
	   /**
	    * f0 -> "if"
	    * f1 -> "("
	    * f2 -> Expression()
	    * f3 -> ")"
	    * f4 -> Statement()
	    * f5 -> "else"
	    * f6 -> Statement()
	    */
	   public String visit(IfStatement n, Message A) {
	      String _ret=null;
	      Message B = new Message(className, methodName, Message.IdentifierType.NONE, Message.ReturnType.TYPE);
	      n.f0.accept(this, A);
	      n.f1.accept(this, A);
	      
	      if(!isBool(n.f2.accept(this, B))) error("Condition should be boolean");
	      n.f3.accept(this, A);
	      n.f4.accept(this, A);
	      n.f5.accept(this, A);
	      n.f6.accept(this, A);
	      return _ret;
	   }
	   

	   /**
	    * f0 -> "while"
	    * f1 -> "("
	    * f2 -> Expression()
	    * f3 -> ")"
	    * f4 -> Statement()
	    */
	   public String visit(WhileStatement n, Message A) {
		   Message B = new Message(className, methodName, Message.IdentifierType.NONE, Message.ReturnType.TYPE);
	      String _ret=null;
	      n.f0.accept(this, A);
	      n.f1.accept(this, A);
	      if(!isBool(n.f2.accept(this, B))) error("Condition should be boolean");
	      n.f3.accept(this, A);
	      n.f4.accept(this, A);
	      return _ret;
	   }
	   
	   /**
	    * f0 -> "System.out.println"
	    * f1 -> "("
	    * f2 -> Expression()
	    * f3 -> ")"
	    * f4 -> ";"
	    */
	   public String visit(PrintStatement n, Message A) {
	      String _ret=null;
	      Message B = new Message(className, methodName, Message.IdentifierType.NONE, Message.ReturnType.TYPE);
	      n.f0.accept(this, A);
	      n.f1.accept(this, A);
	      if(!isInt(n.f2.accept(this, B))) error("Print value should be integer");
	      
	      n.f3.accept(this, A);
	      n.f4.accept(this, A);
	      return _ret;
	   }
	   
	   
	   /**
	    * f0 -> AndExpression()
	    *       | CompareExpression()
	    *       | PlusExpression()
	    *       | MinusExpression()
	    *       | TimesExpression()
	    *       | ArrayLookup()
	    *       | ArrayLength()
	    *       | MessageSend()
	    *       | PrimaryExpression()
	    */
	   public String visit(Expression n, Message A) {	     
	      return  n.f0.accept(this, A);
	   }
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "&"
	    * f2 -> PrimaryExpression()
	    */
	   public String visit(AndExpression n, Message A) {
		      String _ret="boolean";
		      System.out.println(A.returnType);
		      if(!isBool(n.f0.accept(this, A))) error("Anded should be boolean");
		      
		      n.f1.accept(this, A);
		      if(!isBool(n.f2.accept(this, A))) error("Anded should be boolean");
		      return _ret;
		   }
	   

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "<"
	    * f2 -> PrimaryExpression()
	    */
	   public String visit(CompareExpression n, Message A) {
	      String _ret="int";
	      if(!isInt(n.f0.accept(this, A))) error("Compared should be integer");
	      n.f1.accept(this, A);
	      if(!isInt(n.f2.accept(this, A))) error("Compared should be integer");
	      return _ret;
	   }
	   

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "+"
	    * f2 -> PrimaryExpression()
	    */
	   public String visit(PlusExpression n, Message A) {
		   String _ret="int";
		      if(!isInt(n.f0.accept(this, A))) error("Added should be integer");
		      n.f1.accept(this, A);
		      if(!isInt(n.f2.accept(this, A))) error("Added should be integer");
		      return _ret;
	   }
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "-"
	    * f2 -> PrimaryExpression()
	    */
	   public String visit(MinusExpression n, Message A) {
		   String _ret="int";
		      if(!isInt(n.f0.accept(this, A))) error("Sub should be integer");
		      n.f1.accept(this, A);
		      if(!isInt(n.f2.accept(this, A))) error("Sub should be integer");
		      return _ret;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "*"
	    * f2 -> PrimaryExpression()
	    */
	   public String visit(TimesExpression n, Message A) {
		   String _ret="int";
		      if(!isInt(n.f0.accept(this, A))) error("Starred should be integer");
		      n.f1.accept(this, A);
		      if(!isInt(n.f2.accept(this, A))) error("Starred should be integer");
		      return _ret;
	   }
	   
	   
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "["
	    * f2 -> PrimaryExpression()
	    * f3 -> "]"
	    */
	   public String visit(ArrayLookup n, Message A) {
		   	//TODO!
	      String _ret="int";
	      n.f0.accept(this, A);
	      n.f1.accept(this, A);
	      if(!isInt(n.f2.accept(this, A))) error("Index should be integer");
	      n.f3.accept(this, A);
	      return _ret;
	   }
	   
	   
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "."
	    * f2 -> "length"
	    */
	   public String visit(ArrayLength n, Message A) {
	      String _ret="int";
	      //TODO
	      n.f0.accept(this, A);
	      n.f1.accept(this, A);
	      n.f2.accept(this, A);
	      return _ret;
	   }
	   
	   
	   /**
	    * f0 -> PrimaryExpression()			//HAS TO BE AN OBJECT OF A CLASS
	    * f1 -> "."
	    * f2 -> Identifier()
	    * f3 -> "("
	    * f4 -> ( ExpressionList() )?
	    * f5 -> ")"
	    */
	   public String visit(MessageSend n, Message A) {
	      String _ret=null;
	      
	      String tempClassName = n.f0.accept(this, new Message(className, methodName, Message.IdentifierType.VARIABLE, Message.ReturnType.TYPE));	      
	      String tempMethodName = n.f2.accept(this, new Message(tempClassName, null, Message.IdentifierType.METHOD, Message.ReturnType.NAME));
	      n.f1.accept(this, A);

	      Message B = new Message(tempClassName, tempMethodName);
	      n.f3.accept(this, A);
	      n.f4.accept(this, B); //To match parameters of call
	      
	      if(B.arg != this.getMethod(tempClassName, tempMethodName).formals.size()){
	    	  error("Number of args don't match at "+tempMethodName);
	      }
	      n.f5.accept(this, A);
	      _ret = this.getMethod(tempClassName, tempMethodName).returnType;
	      return _ret;
	   }
	   

	   /**
	    * f0 -> IntegerLiteral()
	    *       | TrueLiteral()
	    *       | FalseLiteral()
	    *       | Identifier()
	    *       | ThisExpression()
	    *       | ArrayAllocationExpression()
	    *       | AllocationExpression()
	    *       | NotExpression()
	    *       | BracketExpression()
	    */
	   public String visit(PrimaryExpression n, Message A) {     
		      
		      return n.f0.accept(this, A);
		   }

		   /**
		    * f0 -> <INTEGER_LITERAL>
		    */
	   public String visit(IntegerLiteral n, Message A) {
	      String _ret="int";
	      n.f0.accept(this, A);
	      return _ret;
	   }
	   
	   
	   
	   /**
	    * f0 -> "true"
	    */
	   public String visit(TrueLiteral n, Message A) {
	      String _ret = "boolean";
	      n.f0.accept(this, A);
	      return _ret;
	   }

	   /**
	    * f0 -> "false"
	    */
	   public String visit(FalseLiteral n, Message A) {
	      String _ret="boolean";
	      n.f0.accept(this, A);
	      return _ret;
	   }
	   

	   /**
	    * f0 -> <IDENTIFIER>
	    */
	   public String visit(Identifier 	n, Message A) {	
	       String _ret = null;
		   n.f0.accept(this, A);
		   if(A == null) return _ret;
		   if(A.returnType == Message.ReturnType.NONE){
			   return _ret;
		   }
	      if(A.returnType == Message.ReturnType.NAME){
	    	  _ret = n.f0.toString();
	      }else if(A.returnType == Message.ReturnType.TYPE){
	    	  if(A.identifierType == Message.IdentifierType.METHOD){
	    		  _ret = this.getMethod(A.className, n.f0.toString()).returnType;
	    	  }else {
	    		  _ret = this.getVariable(n.f0.toString(), A.className, A.methodName).type;
	    	  }
	      }
	      return _ret;
	   }
	   /**
	    * f0 -> "!"
	    * f1 -> Expression()
	    */
	   public String visit(NotExpression n, Message A) {
	      String _ret="boolean";
	      n.f0.accept(this, A);
	      if(!isBool(n.f1.accept(this, A))) error("Notted  should be boolean");
	      return _ret;
	   }
	   
	   public String visit(BracketExpression n, Message A) {
		      
		      n.f0.accept(this, A);
		      String _ret = n.f1.accept(this, A);
		      n.f2.accept(this, A);
		      return _ret;
	  }
	   
	   
	   /**
	    * f0 -> Expression()
	    * f1 -> ( ExpressionRest() )*
	    */
	   public String visit(ExpressionList n, Message A) {
	      String _ret=null;
	      //A contains details about the class and the method whose paremters are going to be evaluated
	      //message with current className and methodName can be omitted actually because for expressions it is understood
	      //that it is being evaluated in the current class and method
	      MethodSymbol m = this.getMethod(A.className, A.methodName);
	      String argType = n.f0.accept(this, new Message(className, methodName, Message.IdentifierType.NONE, Message.ReturnType.TYPE ));
	      if(m.formals.size()==0){
	    	  error("Too many arguments");
	      }
	      if(argType.compareTo(m.formals.elementAt(A.arg++).type)!=0){
	    	  error("Mismcatched argument");
	      }
	      n.f1.accept(this, A);	     
	      return _ret;
	   }

	   /**
	    * f0 -> ","
	    * f1 -> Expression()
	    */
	   public String visit(ExpressionRest n, Message A) {
	      String _ret=null;
	      MethodSymbol m = this.getMethod(A.className, A.methodName);
	      String argType = n.f1.accept(this, new Message(className, methodName, Message.IdentifierType.NONE, Message.ReturnType.TYPE ));
	      if(A.arg >= m.formals.size()){
	    	  error("Too many arguments");
	      }
	      if(argType.compareTo(m.formals.elementAt(A.arg++).type)!=0){
	    	  error("Mismatched types");
	      }
	      return _ret;
	   }
	   
	   
	   
	   /**
	    * f0 -> Type()
	    * f1 -> Identifier()
	    * f2 -> ";"
	    */
	   public String visit(VarDeclaration n, Message A) {
	      String _ret=null;
	      String typeName = n.f0.accept(this, new Message(className, methodName, IdentifierType.DATATYPE, ReturnType.NAME));
	      //To see to it that undefined classes are not used as types
	      if(!this.isArray(typeName) && !this.isBool(typeName) && !this.isInt(typeName) && !classes.containsKey(typeName)){
	    	  error("Undefined class");
	      }
	      n.f1.accept(this, A);
	      n.f2.accept(this, A);
	      return _ret;
	   }
	   

	   /**
	    * f0 -> ArrayType()
	    *       | BooleanType()
	    *       | IntegerType()
	    *       | Identifier()
	    */
	   public String visit(Type n) {
	      // TA CODE:
	      return n.f0.accept(this, new Message(className, methodName, IdentifierType.DATATYPE, ReturnType.NAME));
	   }

	   /**
	    * f0 -> "this"
	    */
	   public String visit(ThisExpression n, Message A) {
	      String _ret=null;
	      n.f0.accept(this, A);
	      if(A == null) return _ret;
		   if(A.returnType == Message.ReturnType.NONE){
			   return _ret;
		   }
	      if(A.returnType == Message.ReturnType.NAME){
	    	  _ret = "this";
	      }else if(A.returnType == Message.ReturnType.TYPE){
	    	  if(A.identifierType == Message.IdentifierType.METHOD){
	    		  error("This is not a method");
	    	  }else {
	    		  _ret = className;
	    	  }
	      }
	      return _ret;
	   }

}
