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
	//printf("CLASS  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return CLASS; 
	}
public {
	//printf("PUBLIC  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return PUBLIC; 
	}
void {
	//printf("VOID  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return VOID; 
	}
static {
	//printf("STATIC  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return STATIC;
	}
main { 	
	//printf("MAIN  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return MAIN;
	}
String { 
	//printf("STRING  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return STRING;
	}
System.out.println {
	//printf("SYSTEMOUTPRINTLN  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return SYSTEMOUTPRINTLN; 
	}
extends { 
	//printf("EXTENDS  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return EXTENDS; 
	}
return {
	//printf("RETURN  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return RETURN; 
	}
int { 
	//printf("INT  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return INT;
	}
if { 
	//printf("IF  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return IF; 
	}
else {	
	//printf("ELSE  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return ELSE;
	}
while { 
	//printf("WHILE  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return WHILE;
	}
this { 
	//printf("THIS  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return THIS; 
	}
new { 
	//printf("NEW  %s\n",strdup(yytext)); 
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return NEW;
	}
define {
	//printf("DEFINE  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return DEFINE;
	}
length {
	//printf("LENGTH  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return LENGTH;
	}
boolean { 
	//printf("BOOLEAN  %s\n",strdup(yytext)); 
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return BOOLEAN; 
	}
#define {
	//printf("DEFINE  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return HASH_DEFINE; 
	}
"." {
	//printf("DOT  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return DOT;
	}
"," { 
	//printf("COMMA  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return COMMA;
	}
";" { 
	//printf("SEMICOLON  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return SEMICOLON; 
	}
"!" { 
	//printf("NOT  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return NOT;
	}
"=" { 
	//printf("ASSIGNMENT  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return ASSIGNMENT; 
	}
{OPERATOR} {
	//printf("OPERATOR  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return OPERATOR;
	}

"(" { 
	//printf("(  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return LEFT_PARANTHESIS;
	}

")" { 
	//printf(")  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return RIGHT_PARANTHESIS;
	}
"]" {
	//printf("]  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return RIGHT_BRACKET; 
	}
"[" { 
	//printf("[  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return LEFT_BRACKET;
	}
"{" { 
	//printf("{  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return LEFT_BRACE;
	}
"}" { 
	//printf("}  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return RIGHT_BRACE; 
	}

{BOOLEAN_VALUE} {
	//printf("BOOLEAN_VALUE  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return BOOLEAN_VALUE;
	}
{INTEGER} { 
	//printf("INTEGER  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return INTEGER; 
	}
{IDENTIFIER} { 
	//printf("IDENTIFIER  %s\n",strdup(yytext));
	yylval.word = strdup(yytext);
	yylval.word[strlen(yytext)]='\0';
	return IDENTIFIER; 
	} 
 

{ONELINE_COMMENT}|{COMMENT} { 
	//printf("COMMENT %s\n",strdup(yytext));
	}
{BLANKS}
.
%%

