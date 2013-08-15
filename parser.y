
%{
#include<stdio.h>
#include "vector.h"
#include "macrovector.h"
#include "macro.h"
#include<string.h>
FILE *yyin;
void yyerror(char *message);

struct MacroArray* macros;
char* getCopy(char *buff){
	char* res = (char*)malloc(strlen(buff)*sizeof(char));
	strcpy(res, buff);
	return res;
}
%}

%union{
	char *word;
	struct Macro* macro;
	struct StringArray* arguments;
	struct StringArray** inputs;
}


//Keywords
%token <word> CLASS
%token <word> PUBLIC
%token <word> STATIC
%token <word> VOID
%token <word> MAIN
%token <word> STRING
%token <word> SYSTEMOUTPRINTLN
%token <word> OUT
%token <word> PRINTLN
%token <word> EXTENDS
%token <word> RETURN
%token <word> INT
%token <word> IF 
%token <word> ELSE
%token <word> WHILE
%token <word> THIS
%token <word> NEW
%token <word> DEFINE
%token <word> LENGTH
%token <word> BOOLEAN
%token <word> HASH_DEFINE
%token <word> EOF_TOKEN
//Operators etc.,
%token <word> DOT
%token <word> COMMA
%token <word> SEMICOLON
%token <word> ASSIGNMENT 
%token <word> NOT
%token <word> OPERATOR

//Brackets
%token <word> LEFT_PARANTHESIS
%token <word> RIGHT_PARANTHESIS
%token <word> LEFT_BRACKET
%token <word> RIGHT_BRACKET
%token <word> LEFT_BRACE
%token <word> RIGHT_BRACE


%token <word> BOOLEAN_VALUE
%token <word> INTEGER
%token <word> IDENTIFIER

%type <macro> MacroDefStatement MacroDefExpression
%type <arguments> Expression PrimaryExpression Expressions ExpressionsList
%type <arguments> IdentifiersList Identifiers
%start Goal 

%%

//MacroDefinition*
MacroDefinitions:
	       MacroDefinitions MacroDefinition
	       |
	       ;
	       
//TypeDeclaration*	       
TypeDeclarations:
		TypeDeclarations TypeDeclaration
		|
		;
		
		
Goal:
    MacroDefinitions MainClass TypeDeclarations 
    ;
    

MainClass:
	 CLASS IDENTIFIER LEFT_BRACE PUBLIC STATIC VOID MAIN LEFT_PARANTHESIS STRING LEFT_BRACKET RIGHT_BRACKET IDENTIFIER RIGHT_PARANTHESIS LEFT_BRACE SYSTEMOUTPRINTLN LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS SEMICOLON RIGHT_BRACE RIGHT_BRACE
	 ;
	 
// (Type Identifier;)*
TypeIdentifierStatements:
		       TypeIdentifierStatements Type IDENTIFIER SEMICOLON 
		       |
		       ;

TypeDeclaration:
	       CLASS IDENTIFIER LEFT_BRACE TypeIdentifierStatements MethodDeclarations RIGHT_BRACE
	       | CLASS IDENTIFIER EXTENDS IDENTIFIER LEFT_BRACE TypeIdentifierStatements MethodDeclarations RIGHT_BRACE
	       ;
//Type Identifier (,Type Identifier)*
TypeIdentifiers:
	       TypeIdentifiers COMMA Type IDENTIFIER
	       | Type IDENTIFIER
	       ;

//((Type Identifier (,Type Identifier)*)?)
TypeIdentifiersList:
		   LEFT_PARANTHESIS TypeIdentifiers RIGHT_PARANTHESIS
		   | LEFT_PARANTHESIS RIGHT_PARANTHESIS
		   ;
//Expression (,Expression)*
Expressions:
	       Expressions COMMA Expression {
	       		$$ = $1;
			cat($$, $3);
	       }
	       | Expression {
	       		$$ = $1;
	       }
	       ;

//((Expression (,Expression)*)?)
ExpressionsList:
		   LEFT_PARANTHESIS Expressions RIGHT_PARANTHESIS {
		   	$$ = $2;
			insert($2, $1, 0);
			append($2, $3)
		   }
		   | LEFT_PARANTHESIS RIGHT_PARANTHESIS {
		   	$$ = new_string_array();
			append($$, $1);
			append($$, $2);
		   }
		   ;

//Identifier (,Identifier)*
Identifiers:
	       Identifiers COMMA IDENTIFIER {
	       		$$ = $1;
			append($$, $3);	
	       }
	       | IDENTIFIER {
	       		$$  = new_string_array();
			append($$, $1);
	       }
	       ;

//((Identifier (,Identifier)*)?)
IdentifiersList:
		   LEFT_PARANTHESIS Identifiers RIGHT_PARANTHESIS{
		   	$$ = $2;
		   }
		   | LEFT_PARANTHESIS RIGHT_PARANTHESIS	   {
		   	$$ = new_string_array();
		   }
		   ;



MethodDeclaration:
		 PUBLIC Type IDENTIFIER TypeIdentifiersList LEFT_BRACE TypeIdentifierStatements Statements RETURN Expression SEMICOLON RIGHT_BRACE 
		;
//MethodDeclarations*
MethodDeclarations:
		  MethodDeclarations MethodDeclaration
		  | 
		  ;
//(Statement)*
Statements:
	  Statement Statements  {
	       		$$ = $1;
			append($$, $3);	
	       }
	  |{
		$$ = new_string_array();
	  }

	  ;
Type:
    INT LEFT_BRACKET RIGHT_BRACKET {
    	$$ = new_string_array();
	append($$, $1);
	append($$, $2);
	append($$, $3);
    }
    | BOOLEAN {
    	$$ = new_string_array();
	append($$, $1);
    }
    | INT {
    	$$ = new_string_array();
	append($$, $1);
    }
    | IDENTIFIER  {
    	$$ = new_string_array();
	append($$, $1);
    }
    ;

Statement:
	 LEFT_BRACE Statements RIGHT_BRACE{
	 	$$ = $2;
		insert($$, $1, 0);
	 	append($$, $3);
	 }
	 | SYSTEMOUTPRINTLN LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS SEMICOLON{
	 	$$ = $3;
		insert($$, $2 ,0);
		insert($$, $1, 0);
		append($$, $4);
		append($$, $5);
	 }
	 | IDENTIFIER ASSIGNMENT  Expression SEMICOLON{
	 	$$ = $3;
		insert($$, $2, 0);
		insert($$, $1, 0);
		append($$, $4);
	 }
	 | IDENTIFIER LEFT_BRACKET Expression RIGHT_BRACKET ASSIGNMENT Expression SEMICOLON {
	 	$$ = $3;	 
		insert($$, $2, 0);
		insert($$, $1, 0);
		append($$, $4);
		append($$, $5);
		cat($$, $6);
		append($$,$7);
	 }
	 | IF LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS Statement {
	 	$$ = $3;
		insert($$, $2, 0);
		insert($$, $1, 0);
		append($$, $4);
		cat($$, $5);
	 }
	 | IF LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS Statement ELSE Statement{
	 	$$ = $3;
		insert($$, $2, 0);
		insert($$, $1, 0);
		append($$, $4);
		cat($$, $5);
	 	append($$, $6);
	 	cat($$, $7);
	 }
	 | WHILE LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS Statement{
	 	$$ = $3;;
		insert($$, $2, 0);
		insert($$, $1, 0);
		append($$, $4);
		cat($$, $5);
	 }
	 | IDENTIFIER ExpressionsList SEMICOLON{
	 	//macro statement
	 }
	 ;

Expression:
	  PrimaryExpression OPERATOR PrimaryExpression {
	  	$$ = $1;
		append($$, $2);
		cat($$, $3);
	  }
	  | PrimaryExpression LEFT_BRACKET PrimaryExpression RIGHT_BRACKET{
	  	$$ = $1;
		append($$, $2);
		cat($$, $3);
	  	append($$, $4);
	  } 
	  | PrimaryExpression DOT LENGTH{
	  	$$ = $1;
		append($$, $2);
		append($$, $3);
	  }
	  | PrimaryExpression{
	  	$$ = $1;
	  }
	  | PrimaryExpression DOT IDENTIFIER ExpressionsList{
	  	$$ = $1;
		append($$, $1);
		append($$, $2);
		append($$, $3);
		cat($$, $4);
	  }
	  | IDENTIFIER ExpressionsList{
	  	 struct Macro* sub = macro_locate(macros, $1);
		 if(sub == NULL){
		 	yyerror("Invalid Macro");
		 }
	 	 //macro statement call	
	  }
	  ;
PrimaryExpression:
		 INTEGER {
		 	$$ = new_string_array();
			append($$, $1);
		 }
		 | BOOLEAN_VALUE {
		 	$$ = new_string_array();
			append($$, $1);
		 }
		 | IDENTIFIER {
		 	$$ = new_string_array();
			append($$, $1);
		 }
		 | THIS {
		 	$$ = new_string_array();
			append($$, $1);
		 }
		 | NEW INT LEFT_BRACKET Expression RIGHT_BRACKET{
		 	$$ = new_string_array();
			append($$, $1);
			append($$, $2);
			append($$, $3);
			cat($$, $4);
			append($$, $5);
		 }
		 | NEW IDENTIFIER LEFT_PARANTHESIS RIGHT_PARANTHESIS{
		 	$$ = new_string_array();
			append($$, $1);
			append($$, $2);
			append($$, $3);
			append($$, $4);
			
		 }
		 | NOT Expression{
		 	$$ = new_string_array();
			append($$, $1);
			cat($$, $1);
		 }
		 | LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS{
		 	$$ = new_string_array();
			append($$, $1);
			cat($$, $2);
			append($$, $3);
		 }
		 ;
MacroDefinition:
	       MacroDefExpression
	       | MacroDefStatement
	       ;

MacroDefStatement:
		HASH_DEFINE IDENTIFIER IdentifiersList LEFT_BRACE Statements RIGHT_BRACE{
			$$ = new_macro();
			$$->name = $2;
			$$->args = $3;
			macro_append(macros, $$);	
		}
		 ;



MacroDefExpression:
		 HASH_DEFINE  IDENTIFIER IdentifiersList LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS{
		 	$$ = new_macro();
			$$->name = $2;
			$$->args = $3; 
			macro_append(macros, $$);
		 }
		 ;





%%

int main(){
	macros = new_macro_array(); 
	check = new_string_array();
	yyparse();
}



void yyerror(char *message) {
	printf("ERROR PARSING : %s\n",message);	
	exit(-1);
}
