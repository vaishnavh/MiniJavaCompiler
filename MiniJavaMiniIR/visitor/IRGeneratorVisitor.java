package visitor;

import java.util.HashMap;
import java.util.Iterator;

import symboltable.ClassSymbol;
import symboltable.MethodSymbol;
import syntaxtree.AllocationExpression;
import syntaxtree.AndExpression;
import syntaxtree.ArrayAllocationExpression;
import syntaxtree.ArrayAssignmentStatement;
import syntaxtree.ArrayLength;
import syntaxtree.ArrayLookup;
import syntaxtree.AssignmentStatement;
import syntaxtree.ClassDeclaration;
import syntaxtree.ClassExtendsDeclaration;
import syntaxtree.CompareExpression;
import syntaxtree.FalseLiteral;
import syntaxtree.Identifier;
import syntaxtree.IfStatement;
import syntaxtree.IntegerLiteral;
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
import syntaxtree.WhileStatement;

public class IRGeneratorVisitor extends GJNoArguDepthFirst<Integer> {
	public static int tempKey;
	public int methodBegin;
	public int classPos;
	public static int label;
	public enum IdentifierType {
		FUNCTION, LOAD, STORE
	};

	public boolean print;
	public IdentifierType iType;
	public String tempClassName;

	private void print(String message) {
		System.out.print(message);
		System.out.print(' ');
	}

	public static int getNewKey() {
		return tempKey++;
	}

	public static int getNewLabel(){
		return label++;
	}
	public ClassSymbol getClassSymbol() {
		//System.out.println("Looking for "+className);
		return classes.get(className);
	}

	public void printLabel(int lab){
		print("L"+lab);
	}
	private void print(int message) {
		System.out.print(message);
		System.out.print(' ');
	}

	private void newLine(){
		System.out.println();
	}
	public HashMap<String, ClassSymbol> classes;
	public String className, methodName;

	// MAIN CLASS DECLARATION
	/**
	 * f0 -> "class" f1 -> Identifier() f2 -> "{" f3 -> "public" f4 -> "static"
	 * f5 -> "void" f6 -> "main" f7 -> "(" f8 -> "String" f9 -> "[" f10 -> "]"
	 * f11 -> Identifier() f12 -> ")" f13 -> "{" f14 -> PrintStatement() f15 ->
	 * "}" f16 -> "}"
	 */
	public Integer visit(MainClass n) {
		this.print = false;
		this.label = 0;
	
		print("MAIN");
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
		n.f12.accept(this);
		n.f13.accept(this);
		n.f14.accept(this);
		n.f15.accept(this);
		n.f16.accept(this);
		print("\nEND\n");
		return 0;
	}

	// ************** new Identifier () *****************//
	// NEW CLASS OBJECT
	/**
	 * f0 -> "new" f1 -> Identifier() f2 -> "(" f3 -> ")"
	 */
	public Integer visit(AllocationExpression n) {
		n.f0.accept(this);
		this.print = false;
		n.f1.accept(this);
		ClassSymbol classSymbol = classes.get(n.f1.f0.toString());
		tempClassName = n.f1.f0.toString();
		print("BEGIN\n");
		int retClassTemp = getNewKey();
		int functionTable = getNewKey();
		print("MOVE TEMP ");
		print(functionTable);
		print(" HALLOCATE ");
		print(classSymbol.getFunctionTableSize());
		print("\nMOVE TEMP");
		print(retClassTemp);
		print("HALLOCATE");
		print(classSymbol.getSize());
		Iterator<String> iter = classSymbol.methods.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next();
			MethodSymbol m = classSymbol.methods.get(key);
			print("\nHSTORE TEMP");
			print(functionTable);
			print(m.getOffset());
			print(m.getName());

		}
		print("\nHSTORE TEMP"); print(retClassTemp); print("0 TEMP"); print(functionTable);
		for (int i = 1; i <= classSymbol.variables.size(); i++) {
			print("\nHSTORE TEMP");
			print(retClassTemp);
			print(i * 4);
			print(0);
		}

		print("\nRETURN TEMP ");
		print(retClassTemp);
		print("\nEND\n");
		n.f2.accept(this);
		n.f3.accept(this);
		return 0;
	}

	// -----CLASS DEFINITION
	/**
	 * f0 -> "class" f1 -> Identifier() f2 -> "{" f3 -> ( VarDeclaration() )* f4
	 * -> ( MethodDeclaration() )* f5 -> "}"
	 */
	public Integer visit(ClassDeclaration n) {
		n.f0.accept(this);
		this.print = false;
		n.f1.accept(this);
		className = n.f1.f0.toString();
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		return 0;
	}

	public Integer visit(ClassExtendsDeclaration n) {
		n.f0.accept(this);
		n.f1.accept(this);
		className = n.f1.f0.toString();
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		return 0;
	}

	/**
	 * f0 -> "public" f1 -> Type() f2 -> Identifier() f3 -> "(" f4 -> (
	 * FormalParameterList() )? f5 -> ")" f6 -> "{" f7 -> ( VarDeclaration() )*
	 * f8 -> ( Statement() )* f9 -> "return" f10 -> Expression() f11 -> ";" f12
	 * -> "}"
	 */

	public Integer visit(MethodDeclaration n) {
		methodBegin = getNewKey();
		methodName = n.f2.f0.toString();
		MethodSymbol m = classes.get(className).methods.get(methodName);

		this.tempKey = methodBegin + m.localSize();
		newLine();
		print(m.getName());

		print("[");
		print(m.formals.size() + 1);
		print("]");
		newLine();
		print("BEGIN\n");

		n.f0.accept(this);
		n.f1.accept(this);
		this.print = false;
		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		n.f8.accept(this);
		n.f9.accept(this);
		print("\nRETURN");
		n.f10.accept(this);
		n.f11.accept(this);
		n.f12.accept(this);
		print("\nEND\n");
		return 0;
	}

	
	 public Integer visit(PrimaryExpression n) {
	      this.print = true;
	      this.iType = IdentifierType.LOAD;
	      
	      n.f0.accept(this);
	      return 0;
	   }
	 
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "&"
	    * f2 -> PrimaryExpression()
	    */
	   public Integer visit(AndExpression n) {
	      print("TIMES");
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      return 0;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "<"
	    * f2 -> PrimaryExpression()
	    */
	   public Integer visit(CompareExpression n) {
	      print("LT");
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      return 0;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "+"
	    * f2 -> PrimaryExpression()
	    */
	   public Integer visit(PlusExpression n) {
	      print("PLUS");
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      return 0;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "-"
	    * f2 -> PrimaryExpression()
	    */
	   public Integer visit(MinusExpression n) {
	      print("MINUS");
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      return 0;
	   }

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "*"
	    * f2 -> PrimaryExpression()
	    */
	   public Integer visit(TimesExpression n) {
	      print("TIMES");
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      return 0;
	   }

	/**
	 * f0 -> Identifier() f1 -> "=" f2 -> Expression() f3 -> ";"
	 */
	public Integer visit(AssignmentStatement n) {
		this.print = true;
		this.iType = IdentifierType.STORE; // Tells identifier visitor that it is a load
							// identifier call
		
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);

		n.f3.accept(this);
		return 0;
	}


	/**
	 * f0 -> <IDENTIFIER>
	 */
	public Integer visit(Identifier n) {

		n.f0.accept(this);
		if (print == true) {
			if (iType == IdentifierType.LOAD) {
				//Always is this class' attribute
				
				print(getClassSymbol().loadVariable(n.f0.toString(),methodName, 0, this.methodBegin));
				ClassSymbol c = getClassSymbol();
				if(c!=null){
					//WARNING : WE ASSUME PROPER TYPE CHECKING HAS HAPPENED
					if(c.variables.containsKey(n.f0.toString())){
						tempClassName = c.variables.get(n.f0.toString()).type;
					}
				}
			}else if(iType == IdentifierType.STORE){
				//Always is this class' attribute
				print(classes.get(className).storeVariable(n.f0.toString(), methodName, 0, this.methodBegin));
			}else if(iType == IdentifierType.FUNCTION){
				//Just print offset
				print(classes.get(tempClassName).methods.get(n.f0.toString()).getOffset());
			}
		}
		this.print = false;
		return 0;
	}

	   public Integer visit(ThisExpression n) {
		      print("TEMP 0");
		      this.tempClassName = className;
		      n.f0.accept(this);
		      return 0;
		   }
	   /**
	    * f0 -> <INTEGER_LITERAL>
	    */
	   public Integer visit(IntegerLiteral n) {
	     
	      n.f0.accept(this);
	      print(n.f0.toString());
	      return 0;
	   }
	   
	   /**
	    * f0 -> "true"
	    */
	   public Integer visit(TrueLiteral n) {
	      
	      n.f0.accept(this);
	      print('1');
	      return 0;
	   }

	   /**
	    * f0 -> "false"
	    */

	public Integer visit(FalseLiteral n) {
			      
			      n.f0.accept(this);
			      print('0');
			      return 0;
	}
	
	

	   /**
	    * f0 -> "new"
	    * f1 -> "int"
	    * f2 -> "["
	    * f3 -> Expression()
	    * f4 -> "]"
	    */
	   public Integer visit(ArrayAllocationExpression n) {
	      int arrayKey = getNewKey();
	      int iter = getNewKey();
	      int size = getNewKey();
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      print("BEGIN\nMOVE TEMP"); print(size); 
	      print("TIMES");
	      n.f3.accept(this);
	      print("4");
	      print("\nMOVE TEMP");
	      print(arrayKey);
	      print("HALLOCATE PLUS TEMP"); print(size);
	      print("4\nMOVE TEMP"); print(iter);
	      print("4\n");
	      
	      print("\nHSTORE TEMP"); print(arrayKey); print("0"); 
	      print("TEMP"); print(size);
	      
	      int begLabel = getNewLabel();
	      int endLabel = getNewLabel();
	      newLine();
	      printLabel(begLabel);
	      print("CJUMP LT TEMP"); print(iter);
	      print("TEMP"); print(size);
	      //Initializing as zeroes
	      print("\nHSTORE TEMP"); print(arrayKey); print("PLUS TEMP"); print(iter); print("0");
	      print("\nMOVE TEMP"); print(iter); print("PLUS TEMP"); print(iter); print("4");
	      print("\nJUMP"); printLabel(begLabel); printLabel(endLabel);
	      print("\nRETURN TEMP"); print(arrayKey);
	      print("\nEND\n");
	      n.f4.accept(this);
	      return 0;
	   }

	   

	   /**
	    * f0 -> "!"
	    * f1 -> Expression()
	    */
	   public Integer visit(NotExpression n) {
	    
	      n.f0.accept(this);
	      print("MINUS 1");
	      n.f1.accept(this);
	      
	      return 0;
	   }

	   
	   
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "."
	    * f2 -> Identifier()
	    * f3 -> "("
	    * f4 -> ( ExpressionList() )?
	    * f5 -> ")"
	    */
	   public Integer visit(MessageSend n) {
		   int tempClassKey = getNewKey();

		      int fTableKey = getNewKey();
		      int functionKey = getNewKey();
		   print("\nCALL\nBEGIN\nMOVE TEMP");
		   print(tempClassKey);
		   this.print = true;
		   this.iType = IdentifierType.LOAD;
		   //Would have set tempClassName
	      //We predict that the primary expression boils down to a class - either identifier or this
	      n.f0.accept(this);
	      print("\nHLOAD TEMP"); print(fTableKey); print("TEMP"); print(tempClassKey); print("0");
	      this.print = true;
	      this.iType = IdentifierType.FUNCTION;
	      print("\nHLOAD TEMP"); print(functionKey); print("TEMP"); print(fTableKey);
	      n.f1.accept(this); 
	      n.f2.accept(this);//Prints offset! :D
	      print("\nRETURN TEMP"); print(functionKey);
	      print("\nEND\n");
	      print("( TEMP");
	      print(tempClassKey);
	      n.f3.accept(this);
	      n.f4.accept(this);
	      n.f5.accept(this);
	      print(")");
	      return 0;
	   }
	   
	   
	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "."
	    * f2 -> "length"
	    */
	   public Integer visit(ArrayLength n) {
	      //We will expect primary expression to load an array
		  int result = getNewKey();
		   print("\nBEGIN\n HLOAD TEMP"); print(result);
		  
	      n.f0.accept(this); //gets array pointer
	      print("0\n RETURN TEMP"); //loads size
	      print(result); print("\nEND\n");
	      n.f1.accept(this);
	      n.f2.accept(this);
	      return 0;
	   }
	   

	   /**
	    * f0 -> PrimaryExpression()
	    * f1 -> "["
	    * f2 -> PrimaryExpression()
	    * f3 -> "]"
	    */
	   public Integer visit(ArrayLookup n) {
	      this.print = true;
	      this.iType = IdentifierType.LOAD;
	      int result = getNewKey();
	      print("\nBEGIN\nHLOAD TEMP"); print(result);
	      print("PLUS");
		  n.f0.accept(this); //Returns some TEMP X which is the array pointer
		  print("TIMES");
	      n.f1.accept(this);
	      print("4");
	      n.f2.accept(this);
	      n.f3.accept(this);
	      print("4\nRETURN TEMP"); print(result);
	      print("\nEND\n");
	      return 0;
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
	   public Integer visit(ArrayAssignmentStatement n) {
		  print("\nHSTORE");
		  this.print = true;
		  this.iType = IdentifierType.LOAD;
	      n.f0.accept(this);//Return pointer
	      n.f1.accept(this);
	      print("TIMES 4"); //Offsets from pointer
	      n.f2.accept(this);
	      n.f3.accept(this);
	      n.f4.accept(this);
	      this.print = true;
		  this.iType = IdentifierType.LOAD;
	      
	      n.f5.accept(this);
	      n.f6.accept(this);
	      return 0;
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
	   public Integer visit(IfStatement n) {
		  int elseLabel = getNewLabel();
		  int endLabel = getNewLabel();
		  print("\nCJUMP");
	      n.f0.accept(this);
	      n.f1.accept(this);
	      n.f2.accept(this);
	      printLabel(elseLabel);
	      n.f3.accept(this);
	      
	      newLine();
	      n.f4.accept(this);
	      n.f5.accept(this);
	      print("\nJUMP"); printLabel(endLabel); newLine();


	      printLabel(elseLabel);
	      n.f6.accept(this);
	      printLabel(endLabel); print("NOOP");
	      newLine();
	      return 0;
	   }
	   
	   

	   /**
	    * f0 -> "while"
	    * f1 -> "("
	    * f2 -> Expression()
	    * f3 -> ")"
	    * f4 -> Statement()
	    */
	   public Integer visit(WhileStatement n) {
		  int check = getNewLabel();
		  int stop = getNewLabel();
		  newLine();
		  printLabel(check);
	      n.f0.accept(this);
	      n.f1.accept(this);
	      print("CJUMP");
	      n.f2.accept(this);
	      n.f3.accept(this);
	      printLabel(stop);
	      n.f4.accept(this);
	      print("JUMP");
	      printLabel(check); newLine();
	      printLabel(stop); print("NOOP");
	      newLine();
	      return 0;
	   }

	   /**
	    * f0 -> "System.out.println"
	    * f1 -> "("
	    * f2 -> Expression()
	    * f3 -> ")"
	    * f4 -> ";"
	    */
		public Integer visit(PrintStatement n) {
			 print("\nPRINT");
		      n.f0.accept(this);
		      n.f1.accept(this);
		      n.f2.accept(this);
		      n.f3.accept(this);
		      n.f4.accept(this);
		      return 0;
		   }

}
