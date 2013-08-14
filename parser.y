
%{
#include<stdio.h>
FILE *yyin;
void yyerror(char *message);
%}

%union{
	char* word;	
}


//Keywords
%token CLASS
%token PUBLIC
%token STATIC
%token VOID
%token MAIN
%token STRING
%token SYSTEMOUTPRINTLN
%token OUT
%token PRINTLN
%token EXTENDS
%token RETURN
%token INT
%token IF 
%token ELSE
%token WHILE
%token THIS
%token NEW
%token DEFINE
%token LENGTH
%token BOOLEAN
%token HASH_DEFINE
%token EOF_TOKEN
//Operators etc.,
%token DOT
%token COMMA
%token SEMICOLON
%token ASSIGNMENT 
%token NOT
%token OPERATOR

//Brackets
%token LEFT_PARANTHESIS
%token RIGHT_PARANTHESIS
%token LEFT_BRACKET
%token RIGHT_BRACKET
%token LEFT_BRACE
%token RIGHT_BRACE


%token BOOLEAN_VALUE
%token INTEGER
%token IDENTIFIER
//%start Goal 
//%start Expression
//%start MethodDeclaration
//%start Statements
%start Check
%%

Check:
     Check A C
     | X
     ;

X:
 X A B
 | 
 ;

A:
 IDENTIFIER
 ;

B:
 NOT 
 ;


C:
 ASSIGNMENT
 ;

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
	       Expressions COMMA Expression
	       | Expression
	       ;

//((Expression (,Expression)*)?)
ExpressionsList:
		   LEFT_PARANTHESIS Expressions RIGHT_PARANTHESIS
		   | LEFT_PARANTHESIS RIGHT_PARANTHESIS
		   ;

//Identifier (,Identifier)*
Identifiers:
	       Identifiers COMMA IDENTIFIER
	       | IDENTIFIER
	       ;

//((Identifier (,Identifier)*)?)
IdentifiersList:
		   LEFT_PARANTHESIS Identifiers RIGHT_PARANTHESIS
		   | LEFT_PARANTHESIS RIGHT_PARANTHESIS
		   ;



MethodDeclaration:
		 PUBLIC Type IDENTIFIER TypeIdentifiersList LEFT_BRACE TypeIdentifierStatements { printf("HiHiHi\n");} Statements RETURN Expression SEMICOLON RIGHT_BRACE 
		;
//MethodDeclarations*
MethodDeclarations:
		  MethodDeclarations MethodDeclaration
		  | 
		  ;
//(Statement)*
Statements:
	  Statements Statement { printf("Encountered Statement"); }
	  |	  
	  ;
Type:
    INT LEFT_BRACKET RIGHT_BRACKET
    | BOOLEAN
    | INT
    | IDENTIFIER { printf("Hi01\n");}
    ;
Statement:
	 LEFT_BRACE Statements RIGHT_BRACE
	 | SYSTEMOUTPRINTLN LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS SEMICOLON
	 | IDENTIFIER {printf("Hi\n");} ASSIGNMENT Expression SEMICOLON
	 | IDENTIFIER LEFT_BRACKET Expression RIGHT_BRACKET ASSIGNMENT Expression SEMICOLON
	 | IF LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS Statement
	 | IF LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS Statement ELSE Statement
	 | WHILE LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS Statement
	 | IDENTIFIER ExpressionsList SEMICOLON
	 ;
Expression:
	  PrimaryExpression OPERATOR PrimaryExpression
	  | PrimaryExpression LEFT_BRACKET PrimaryExpression RIGHT_BRACKET 
	  | PrimaryExpression DOT LENGTH
	  | PrimaryExpression
	  | PrimaryExpression DOT IDENTIFIER ExpressionsList
	  | IDENTIFIER ExpressionsList
	  ;
PrimaryExpression:
		 INTEGER
		 | BOOLEAN_VALUE
		 | IDENTIFIER
		 | THIS
		 | NEW INT LEFT_BRACKET Expression RIGHT_BRACKET
		 | NEW IDENTIFIER LEFT_PARANTHESIS RIGHT_PARANTHESIS
		 | NOT Expression
		 | LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS
		 ;
MacroDefinition:
	       MacroDefExpression
	       | MacroDefStatement
	       ;

MacroDefStatement:
		 HASH_DEFINE IDENTIFIER IdentifiersList LEFT_BRACE Statements RIGHT_BRACE
		 ;


MacroDefExpression:
		 HASH_DEFINE IDENTIFIER IdentifiersList LEFT_PARANTHESIS Expression RIGHT_PARANTHESIS
		 ;





%%

int main(){
	yyparse();
}



void yyerror(char *message) {
	printf("ERROR PARSING : %s\n",message);	
	exit(-1);
}
