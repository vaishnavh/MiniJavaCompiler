/* A Bison parser, made by GNU Bison 2.5.  */

/* Bison interface for Yacc-like parsers in C
   
      Copyright (C) 1984, 1989-1990, 2000-2011 Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     CLASS = 258,
     PUBLIC = 259,
     STATIC = 260,
     VOID = 261,
     MAIN = 262,
     STRING = 263,
     SYSTEMOUTPRINTLN = 264,
     OUT = 265,
     PRINTLN = 266,
     EXTENDS = 267,
     RETURN = 268,
     INT = 269,
     IF = 270,
     ELSE = 271,
     WHILE = 272,
     THIS = 273,
     NEW = 274,
     DEFINE = 275,
     LENGTH = 276,
     BOOLEAN = 277,
     HASH_DEFINE = 278,
     EOF_TOKEN = 279,
     DOT = 280,
     COMMA = 281,
     SEMICOLON = 282,
     ASSIGNMENT = 283,
     NOT = 284,
     OPERATOR = 285,
     LEFT_PARANTHESIS = 286,
     RIGHT_PARANTHESIS = 287,
     LEFT_BRACKET = 288,
     RIGHT_BRACKET = 289,
     LEFT_BRACE = 290,
     RIGHT_BRACE = 291,
     BOOLEAN_VALUE = 292,
     INTEGER = 293,
     IDENTIFIER = 294
   };
#endif



#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
{

/* Line 2068 of yacc.c  */
#line 16 "parser.y"

	char *word;
	struct Macro* macro;
	struct StringArray* list_of_tokens;
	struct StringArray** inputs;



/* Line 2068 of yacc.c  */
#line 98 "parser.tab.h"
} YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


