%option noyywrap
%{
//AUTHOR : Vaishnavh, CS11B026
#include<stdio.h>
#include "parser.tab.h"
%}
BLANKS [ \t\n\r\f]+
OPERATOR ("&"|"<"|">"|"+"|"-"|"*"|"/")
ONELINE_COMMENT "//".*
COMMENT "/*"(.|\n)*"*/"
BOOLEAN_VALUE "true"|"false"
DIGIT [0-9]
LETTER [a-zA-Z]
IDENTIFIER ({LETTER}|"_")({LETTER}|{DIGIT}|"_")*
INTEGER {DIGIT}+

%%
class { 
	printf("CLASS  %s\n",yytext);
	return CLASS; 
	}
public {
	printf("PUBLIC  %s\n",yytext);
	return PUBLIC; 
	}
void {
	printf("VOID  %s\n",yytext);
	return VOID; 
	}
static {
	printf("STATIC  %s\n",yytext);
	return STATIC;
	}
main { 	
	printf("MAIN  %s\n",yytext);
	return MAIN;
	}
String { 
	printf("STRING  %s\n",yytext);
	return STRING;
	}
System.out.println {
	printf("SYSTEMOUTPRINTLN  %s\n",yytext);
	return SYSTEMOUTPRINTLN; 
	}
extends { 
	printf("EXTENDS  %s\n",yytext);
	return EXTENDS; 
	}
new {
	printf("NEW %s\n",yytext);
	}
return {
	printf("RETURN  %s\n",yytext);
	return RETURN; 
	}
int { 
	printf("INT  %s\n",yytext);
	return INT;
	}
if { 
	printf("IF  %s\n",yytext);
	return IF; 
	}
else {	
	printf("ELSE  %s\n",yytext);
	return ELSE;
	}
while { 
	printf("WHILE  %s\n",yytext);
	return WHILE;
	}
this { 
	printf("THIS  %s\n",yytext);
	return THIS; 
	}
new { 
	printf("NEW  %s\n",yytext); 
	return NEW;
	}
define {
	printf("DEFINE  %s\n",yytext);
	return DEFINE;
	}
length {
	printf("LENGTH  %s\n",yytext);
	return LENGTH;
	}
boolean { 
	printf("BOOLEAN  %s\n",yytext); 
	return BOOLEAN; 
	}
#define {
	printf("DEFINE  %s\n",yytext);
	return HASH_DEFINE; 
	}
"." {
	printf("DOT  %s\n",yytext);
	return DOT;
	}
"," { 
	printf("COMMA  %s\n",yytext);
	return COMMA;
	}
";" { 
	printf("SEMICOLON  %s\n",yytext);
	return SEMICOLON; 
	}
"!" { 
	printf("NOT  %s\n",yytext);
	return NOT;
	}
"=" { 
	printf("ASSIGNMENT  %s\n",yytext);
	return ASSIGNMENT; 
	}
{OPERATOR} {
	printf("OPERATOR  %s\n",yytext);
	return OPERATOR;
	}

"(" { 
	printf("(  %s\n",yytext);
	return LEFT_PARANTHESIS;
	}

")" { 
	printf(")  %s\n",yytext);
	return RIGHT_PARANTHESIS;
	}
"]" {
	printf("]  %s\n",yytext);
	return RIGHT_BRACKET; 
	}
"[" { 
	printf("[  %s\n",yytext);
	return LEFT_BRACKET;
	}
"{" { 
	printf("{  %s\n",yytext);
	return LEFT_BRACE;
	}
"}" { 
	printf("}  %s\n",yytext);
	return RIGHT_BRACE; 
	}

{BOOLEAN_VALUE} {
	printf("BOOLEAN_VALUE  %s\n",yytext);
	return BOOLEAN_VALUE;
	}
{INTEGER} { 
	printf("INTEGER  %s\n",yytext);
	return INTEGER; 
	}
{IDENTIFIER} { 
	printf("IDENTIFIER  %s\n",yytext);
	return IDENTIFIER; 
	} 
 

{ONELINE_COMMENT}|{COMMENT} { 
	printf("COMMENT %s\n",yytext);
	}
{BLANKS}
.
%%

