/* A Bison parser, made by GNU Bison 2.5.  */

/* Bison implementation for Yacc-like parsers in C
   
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

/* C LALR(1) parser skeleton written by Richard Stallman, by
   simplifying the original so-called "semantic" parser.  */

/* All symbols defined below should begin with yy or YY, to avoid
   infringing on user name space.  This should be done even for local
   variables, as they might otherwise be expanded by user macros.
   There are some unavoidable exceptions within include files to
   define necessary library symbols; they are noted "INFRINGES ON
   USER NAME SPACE" below.  */

/* Identify Bison output.  */
#define YYBISON 1

/* Bison version.  */
#define YYBISON_VERSION "2.5"

/* Skeleton name.  */
#define YYSKELETON_NAME "yacc.c"

/* Pure parsers.  */
#define YYPURE 0

/* Push parsers.  */
#define YYPUSH 0

/* Pull parsers.  */
#define YYPULL 1

/* Using locations.  */
#define YYLSP_NEEDED 0



/* Copy the first part of user declarations.  */

/* Line 268 of yacc.c  */
#line 2 "parser.y"

#include<stdio.h>
#include "vector.h"
#include "macrovector.h"
#include "macro.h"
#include<string.h>
FILE *yyin;
void yyerror(char *message);
int max_args;
int i;
struct MacroArray* macros;
struct StringArray** list_of_expressions;


/* Line 268 of yacc.c  */
#line 86 "parser.tab.c"

/* Enabling traces.  */
#ifndef YYDEBUG
# define YYDEBUG 0
#endif

/* Enabling verbose error messages.  */
#ifdef YYERROR_VERBOSE
# undef YYERROR_VERBOSE
# define YYERROR_VERBOSE 1
#else
# define YYERROR_VERBOSE 0
#endif

/* Enabling the token table.  */
#ifndef YYTOKEN_TABLE
# define YYTOKEN_TABLE 0
#endif


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

/* Line 293 of yacc.c  */
#line 16 "parser.y"

	char *word;
	struct Macro* macro;
	struct StringArray* list_of_tokens;
	struct StringArray** inputs;



/* Line 293 of yacc.c  */
#line 170 "parser.tab.c"
} YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif


/* Copy the second part of user declarations.  */


/* Line 343 of yacc.c  */
#line 182 "parser.tab.c"

#ifdef short
# undef short
#endif

#ifdef YYTYPE_UINT8
typedef YYTYPE_UINT8 yytype_uint8;
#else
typedef unsigned char yytype_uint8;
#endif

#ifdef YYTYPE_INT8
typedef YYTYPE_INT8 yytype_int8;
#elif (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
typedef signed char yytype_int8;
#else
typedef short int yytype_int8;
#endif

#ifdef YYTYPE_UINT16
typedef YYTYPE_UINT16 yytype_uint16;
#else
typedef unsigned short int yytype_uint16;
#endif

#ifdef YYTYPE_INT16
typedef YYTYPE_INT16 yytype_int16;
#else
typedef short int yytype_int16;
#endif

#ifndef YYSIZE_T
# ifdef __SIZE_TYPE__
#  define YYSIZE_T __SIZE_TYPE__
# elif defined size_t
#  define YYSIZE_T size_t
# elif ! defined YYSIZE_T && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#  include <stddef.h> /* INFRINGES ON USER NAME SPACE */
#  define YYSIZE_T size_t
# else
#  define YYSIZE_T unsigned int
# endif
#endif

#define YYSIZE_MAXIMUM ((YYSIZE_T) -1)

#ifndef YY_
# if defined YYENABLE_NLS && YYENABLE_NLS
#  if ENABLE_NLS
#   include <libintl.h> /* INFRINGES ON USER NAME SPACE */
#   define YY_(msgid) dgettext ("bison-runtime", msgid)
#  endif
# endif
# ifndef YY_
#  define YY_(msgid) msgid
# endif
#endif

/* Suppress unused-variable warnings by "using" E.  */
#if ! defined lint || defined __GNUC__
# define YYUSE(e) ((void) (e))
#else
# define YYUSE(e) /* empty */
#endif

/* Identity function, used to suppress warnings about constant conditions.  */
#ifndef lint
# define YYID(n) (n)
#else
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static int
YYID (int yyi)
#else
static int
YYID (yyi)
    int yyi;
#endif
{
  return yyi;
}
#endif

#if ! defined yyoverflow || YYERROR_VERBOSE

/* The parser invokes alloca or malloc; define the necessary symbols.  */

# ifdef YYSTACK_USE_ALLOCA
#  if YYSTACK_USE_ALLOCA
#   ifdef __GNUC__
#    define YYSTACK_ALLOC __builtin_alloca
#   elif defined __BUILTIN_VA_ARG_INCR
#    include <alloca.h> /* INFRINGES ON USER NAME SPACE */
#   elif defined _AIX
#    define YYSTACK_ALLOC __alloca
#   elif defined _MSC_VER
#    include <malloc.h> /* INFRINGES ON USER NAME SPACE */
#    define alloca _alloca
#   else
#    define YYSTACK_ALLOC alloca
#    if ! defined _ALLOCA_H && ! defined EXIT_SUCCESS && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
#     include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#     ifndef EXIT_SUCCESS
#      define EXIT_SUCCESS 0
#     endif
#    endif
#   endif
#  endif
# endif

# ifdef YYSTACK_ALLOC
   /* Pacify GCC's `empty if-body' warning.  */
#  define YYSTACK_FREE(Ptr) do { /* empty */; } while (YYID (0))
#  ifndef YYSTACK_ALLOC_MAXIMUM
    /* The OS might guarantee only one guard page at the bottom of the stack,
       and a page size can be as small as 4096 bytes.  So we cannot safely
       invoke alloca (N) if N exceeds 4096.  Use a slightly smaller number
       to allow for a few compiler-allocated temporary stack slots.  */
#   define YYSTACK_ALLOC_MAXIMUM 4032 /* reasonable circa 2006 */
#  endif
# else
#  define YYSTACK_ALLOC YYMALLOC
#  define YYSTACK_FREE YYFREE
#  ifndef YYSTACK_ALLOC_MAXIMUM
#   define YYSTACK_ALLOC_MAXIMUM YYSIZE_MAXIMUM
#  endif
#  if (defined __cplusplus && ! defined EXIT_SUCCESS \
       && ! ((defined YYMALLOC || defined malloc) \
	     && (defined YYFREE || defined free)))
#   include <stdlib.h> /* INFRINGES ON USER NAME SPACE */
#   ifndef EXIT_SUCCESS
#    define EXIT_SUCCESS 0
#   endif
#  endif
#  ifndef YYMALLOC
#   define YYMALLOC malloc
#   if ! defined malloc && ! defined EXIT_SUCCESS && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void *malloc (YYSIZE_T); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
#  ifndef YYFREE
#   define YYFREE free
#   if ! defined free && ! defined EXIT_SUCCESS && (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
void free (void *); /* INFRINGES ON USER NAME SPACE */
#   endif
#  endif
# endif
#endif /* ! defined yyoverflow || YYERROR_VERBOSE */


#if (! defined yyoverflow \
     && (! defined __cplusplus \
	 || (defined YYSTYPE_IS_TRIVIAL && YYSTYPE_IS_TRIVIAL)))

/* A type that is properly aligned for any stack member.  */
union yyalloc
{
  yytype_int16 yyss_alloc;
  YYSTYPE yyvs_alloc;
};

/* The size of the maximum gap between one aligned stack and the next.  */
# define YYSTACK_GAP_MAXIMUM (sizeof (union yyalloc) - 1)

/* The size of an array large to enough to hold all stacks, each with
   N elements.  */
# define YYSTACK_BYTES(N) \
     ((N) * (sizeof (yytype_int16) + sizeof (YYSTYPE)) \
      + YYSTACK_GAP_MAXIMUM)

# define YYCOPY_NEEDED 1

/* Relocate STACK from its old location to the new one.  The
   local variables YYSIZE and YYSTACKSIZE give the old and new number of
   elements in the stack, and YYPTR gives the new location of the
   stack.  Advance YYPTR to a properly aligned location for the next
   stack.  */
# define YYSTACK_RELOCATE(Stack_alloc, Stack)				\
    do									\
      {									\
	YYSIZE_T yynewbytes;						\
	YYCOPY (&yyptr->Stack_alloc, Stack, yysize);			\
	Stack = &yyptr->Stack_alloc;					\
	yynewbytes = yystacksize * sizeof (*Stack) + YYSTACK_GAP_MAXIMUM; \
	yyptr += yynewbytes / sizeof (*yyptr);				\
      }									\
    while (YYID (0))

#endif

#if defined YYCOPY_NEEDED && YYCOPY_NEEDED
/* Copy COUNT objects from FROM to TO.  The source and destination do
   not overlap.  */
# ifndef YYCOPY
#  if defined __GNUC__ && 1 < __GNUC__
#   define YYCOPY(To, From, Count) \
      __builtin_memcpy (To, From, (Count) * sizeof (*(From)))
#  else
#   define YYCOPY(To, From, Count)		\
      do					\
	{					\
	  YYSIZE_T yyi;				\
	  for (yyi = 0; yyi < (Count); yyi++)	\
	    (To)[yyi] = (From)[yyi];		\
	}					\
      while (YYID (0))
#  endif
# endif
#endif /* !YYCOPY_NEEDED */

/* YYFINAL -- State number of the termination state.  */
#define YYFINAL  9
/* YYLAST -- Last index in YYTABLE.  */
#define YYLAST   162

/* YYNTOKENS -- Number of terminals.  */
#define YYNTOKENS  40
/* YYNNTS -- Number of nonterminals.  */
#define YYNNTS  23
/* YYNRULES -- Number of rules.  */
#define YYNRULES  58
/* YYNRULES -- Number of states.  */
#define YYNSTATES  159

/* YYTRANSLATE(YYLEX) -- Bison symbol number corresponding to YYLEX.  */
#define YYUNDEFTOK  2
#define YYMAXUTOK   294

#define YYTRANSLATE(YYX)						\
  ((unsigned int) (YYX) <= YYMAXUTOK ? yytranslate[YYX] : YYUNDEFTOK)

/* YYTRANSLATE[YYLEX] -- Bison symbol number corresponding to YYLEX.  */
static const yytype_uint8 yytranslate[] =
{
       0,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     2,     2,     2,     2,
       2,     2,     2,     2,     2,     2,     1,     2,     3,     4,
       5,     6,     7,     8,     9,    10,    11,    12,    13,    14,
      15,    16,    17,    18,    19,    20,    21,    22,    23,    24,
      25,    26,    27,    28,    29,    30,    31,    32,    33,    34,
      35,    36,    37,    38,    39
};

#if YYDEBUG
/* YYPRHS[YYN] -- Index of the first RHS symbol of rule number YYN in
   YYRHS.  */
static const yytype_uint8 yyprhs[] =
{
       0,     0,     3,     6,     7,    10,    11,    15,    37,    42,
      43,    50,    59,    64,    67,    71,    74,    78,    80,    84,
      87,    91,    93,    97,   100,   112,   115,   116,   119,   120,
     124,   126,   128,   130,   134,   140,   145,   153,   159,   167,
     173,   177,   181,   186,   190,   192,   197,   200,   202,   204,
     206,   208,   214,   219,   222,   226,   228,   230,   237
};

/* YYRHS -- A `-1'-separated list of the rules' RHS.  */
static const yytype_int8 yyrhs[] =
{
      43,     0,    -1,    41,    60,    -1,    -1,    42,    46,    -1,
      -1,    41,    44,    42,    -1,     3,    39,    35,     4,     5,
       6,     7,    31,     8,    33,    34,    39,    32,    35,     9,
      31,    58,    32,    27,    36,    36,    -1,    45,    56,    39,
      27,    -1,    -1,     3,    39,    35,    45,    54,    36,    -1,
       3,    39,    12,    39,    35,    45,    54,    36,    -1,    47,
      26,    56,    39,    -1,    56,    39,    -1,    31,    47,    32,
      -1,    31,    32,    -1,    49,    26,    58,    -1,    58,    -1,
      31,    49,    32,    -1,    31,    32,    -1,    51,    26,    39,
      -1,    39,    -1,    31,    51,    32,    -1,    31,    32,    -1,
       4,    56,    39,    48,    35,    45,    55,    13,    58,    27,
      36,    -1,    54,    53,    -1,    -1,    57,    55,    -1,    -1,
      14,    33,    34,    -1,    22,    -1,    14,    -1,    39,    -1,
      35,    55,    36,    -1,     9,    31,    58,    32,    27,    -1,
      39,    28,    58,    27,    -1,    39,    33,    58,    34,    28,
      58,    27,    -1,    15,    31,    58,    32,    57,    -1,    15,
      31,    58,    32,    57,    16,    57,    -1,    17,    31,    58,
      32,    57,    -1,    39,    50,    27,    -1,    59,    30,    59,
      -1,    59,    33,    59,    34,    -1,    59,    25,    21,    -1,
      59,    -1,    59,    25,    39,    50,    -1,    39,    50,    -1,
      38,    -1,    37,    -1,    39,    -1,    18,    -1,    19,    14,
      33,    58,    34,    -1,    19,    39,    31,    32,    -1,    29,
      58,    -1,    31,    58,    32,    -1,    62,    -1,    61,    -1,
      23,    39,    52,    35,    55,    36,    -1,    23,    39,    52,
      31,    58,    32,    -1
};

/* YYRLINE[YYN] -- source line where rule number YYN was defined.  */
static const yytype_uint16 yyrline[] =
{
       0,    78,    78,    79,    84,    85,    90,    95,   100,   101,
     105,   106,   110,   111,   116,   117,   121,   125,   143,   146,
     156,   160,   168,   171,   179,   183,   184,   188,   193,   199,
     205,   209,   213,   220,   225,   232,   238,   248,   255,   266,
     274,   289,   295,   302,   308,   311,   328,   343,   347,   351,
     355,   359,   368,   376,   382,   391,   392,   396,   411
};
#endif

#if YYDEBUG || YYERROR_VERBOSE || YYTOKEN_TABLE
/* YYTNAME[SYMBOL-NUM] -- String name of the symbol SYMBOL-NUM.
   First, the terminals, then, starting at YYNTOKENS, nonterminals.  */
static const char *const yytname[] =
{
  "$end", "error", "$undefined", "CLASS", "PUBLIC", "STATIC", "VOID",
  "MAIN", "STRING", "SYSTEMOUTPRINTLN", "OUT", "PRINTLN", "EXTENDS",
  "RETURN", "INT", "IF", "ELSE", "WHILE", "THIS", "NEW", "DEFINE",
  "LENGTH", "BOOLEAN", "HASH_DEFINE", "EOF_TOKEN", "DOT", "COMMA",
  "SEMICOLON", "ASSIGNMENT", "NOT", "OPERATOR", "LEFT_PARANTHESIS",
  "RIGHT_PARANTHESIS", "LEFT_BRACKET", "RIGHT_BRACKET", "LEFT_BRACE",
  "RIGHT_BRACE", "BOOLEAN_VALUE", "INTEGER", "IDENTIFIER", "$accept",
  "MacroDefinitions", "TypeDeclarations", "Goal", "MainClass",
  "TypeIdentifierStatements", "TypeDeclaration", "TypeIdentifiers",
  "TypeIdentifiersList", "Expressions", "ExpressionsList", "Identifiers",
  "IdentifiersList", "MethodDeclaration", "MethodDeclarations",
  "Statements", "Type", "Statement", "Expression", "PrimaryExpression",
  "MacroDefinition", "MacroDefStatement", "MacroDefExpression", 0
};
#endif

# ifdef YYPRINT
/* YYTOKNUM[YYLEX-NUM] -- Internal token number corresponding to
   token YYLEX-NUM.  */
static const yytype_uint16 yytoknum[] =
{
       0,   256,   257,   258,   259,   260,   261,   262,   263,   264,
     265,   266,   267,   268,   269,   270,   271,   272,   273,   274,
     275,   276,   277,   278,   279,   280,   281,   282,   283,   284,
     285,   286,   287,   288,   289,   290,   291,   292,   293,   294
};
# endif

/* YYR1[YYN] -- Symbol number of symbol that rule YYN derives.  */
static const yytype_uint8 yyr1[] =
{
       0,    40,    41,    41,    42,    42,    43,    44,    45,    45,
      46,    46,    47,    47,    48,    48,    49,    49,    50,    50,
      51,    51,    52,    52,    53,    54,    54,    55,    55,    56,
      56,    56,    56,    57,    57,    57,    57,    57,    57,    57,
      57,    58,    58,    58,    58,    58,    58,    59,    59,    59,
      59,    59,    59,    59,    59,    60,    60,    61,    62
};

/* YYR2[YYN] -- Number of symbols composing right hand side of rule YYN.  */
static const yytype_uint8 yyr2[] =
{
       0,     2,     2,     0,     2,     0,     3,    21,     4,     0,
       6,     8,     4,     2,     3,     2,     3,     1,     3,     2,
       3,     1,     3,     2,    11,     2,     0,     2,     0,     3,
       1,     1,     1,     3,     5,     4,     7,     5,     7,     5,
       3,     3,     4,     3,     1,     4,     2,     1,     1,     1,
       1,     5,     4,     2,     3,     1,     1,     6,     6
};

/* YYDEFACT[STATE-NAME] -- Default reduction number in state STATE-NUM.
   Performed when YYTABLE doesn't specify something else to do.  Zero
   means the default is an error.  */
static const yytype_uint8 yydefact[] =
{
       3,     0,     0,     0,     0,     5,     2,    56,    55,     1,
       0,     0,     6,     0,     0,     0,     0,     4,     0,    23,
      21,     0,     0,    28,     0,     0,     0,    22,    50,     0,
       0,     0,    48,    47,    49,     0,    44,     0,     0,     0,
      28,     0,     0,    28,     0,     9,     0,    20,     0,     0,
      53,     0,     0,    46,    58,     0,     0,     0,     0,     0,
       0,     0,     0,     0,     0,    57,    27,     0,    26,     0,
       0,     0,    54,    19,     0,    17,    43,     0,    49,    41,
       0,     0,     0,     0,    33,     0,     0,    40,     9,    31,
      30,    32,     0,     0,     0,     0,    52,     0,    18,    45,
      42,     0,     0,     0,    35,     0,    26,     0,     0,    10,
      25,     0,     0,    51,    16,    34,    37,    39,     0,     0,
      29,     0,     8,     0,     0,     0,    11,     0,     0,    38,
      36,     0,     0,     0,    15,     0,     0,     9,     0,     0,
      14,    13,    28,     0,     0,    32,     0,     0,    12,     0,
       0,     0,     0,     0,     0,    24,     0,     0,     7
};

/* YYDEFGOTO[NTERM-NUM].  */
static const yytype_int16 yydefgoto[] =
{
      -1,     1,    12,     2,     5,    68,    17,   135,   132,    74,
      64,    21,    15,   110,    92,    42,    93,    43,    35,    36,
       6,     7,     8
};

/* YYPACT[STATE-NUM] -- Index in YYTABLE of the portion describing
   STATE-NUM.  */
#define YYPACT_NINF -98
static const yytype_int16 yypact[] =
{
     -98,    24,    10,    12,    20,   -98,   -98,   -98,   -98,   -98,
      25,    32,    61,    62,   -24,   -19,    40,   -98,    75,   -98,
     -98,    58,    54,    26,    34,    76,    42,   -98,   -98,    11,
      54,    54,   -98,   -98,    67,    69,   -16,    72,    78,    79,
      26,    80,    64,    26,    55,   -98,   105,   -98,    81,    84,
     -98,    85,    39,   -98,   -98,    35,    68,    68,    54,    54,
      54,    82,    54,    54,    89,   -98,   -98,    86,    -1,    91,
      54,    92,   -98,   -98,    63,   -98,   -98,    67,   -98,   -98,
      93,    94,    96,    97,   -98,    98,    99,   -98,   -98,    90,
     -98,   -98,     0,    95,   122,   101,   -98,    54,   -98,   -98,
     -98,   104,    26,    26,   -98,   108,    -1,   103,    -1,   -98,
     -98,   111,   106,   -98,   -98,   -98,   116,   -98,    54,     3,
     -98,   102,   -98,   109,    26,   113,   -98,   114,   107,   -98,
     -98,    23,   112,   110,   -98,    70,   115,   -98,   117,    -1,
     -98,   -98,     9,   135,   118,    80,   136,   119,   -98,    54,
      54,   121,   123,   120,   124,   -98,   125,   126,   -98
};

/* YYPGOTO[NTERM-NUM].  */
static const yytype_int8 yypgoto[] =
{
     -98,   -98,   -98,   -98,   -98,   -85,   -98,   -98,   -98,   -98,
     -28,   -98,   -98,   -98,    47,   -38,   -97,   -49,   -30,   -37,
     -98,   -98,   -98
};

/* YYTABLE[YYPACT[STATE-NUM]].  What to do in state STATE-NUM.  If
   positive, shift that token.  If negative, reduce the rule which
   number is the opposite.  If YYTABLE_NINF, syntax error.  */
#define YYTABLE_NINF -1
static const yytype_uint8 yytable[] =
{
      50,    51,    61,   106,   108,    66,    53,   108,    19,    55,
       9,   121,    22,    89,    56,    20,    23,    57,    37,    79,
      80,    90,    75,    89,    38,    48,    39,     3,    81,    82,
      83,    90,    85,    86,   136,    37,   109,    89,    91,   126,
      95,    38,   144,    39,    40,    90,    44,     4,   145,    99,
      49,    10,   142,   116,   117,   134,    76,    28,    29,    11,
      13,    40,    91,    14,    16,    41,    18,   114,    30,    45,
      31,    73,    28,    29,    77,   129,    32,    33,    34,    24,
      25,    47,    46,    30,    26,    31,    28,    29,   125,    97,
      27,    32,    33,    34,    67,    98,   139,    30,    52,    31,
      65,    54,   140,    58,   146,    32,    33,    78,    62,    59,
      60,    52,    69,    63,    70,    71,    87,    72,    84,   151,
     152,    88,    94,   107,    96,   104,   101,   100,   102,   103,
     112,   115,   124,   105,   111,   113,   118,   120,   122,   123,
     130,   127,   138,   128,   147,   131,   133,   137,   153,   149,
     150,   156,   143,   119,   141,   154,   155,   148,     0,     0,
       0,   157,   158
};

#define yypact_value_is_default(yystate) \
  ((yystate) == (-98))

#define yytable_value_is_error(yytable_value) \
  YYID (0)

static const yytype_int16 yycheck[] =
{
      30,    31,    40,    88,     4,    43,    34,     4,    32,    25,
       0,   108,    31,    14,    30,    39,    35,    33,     9,    56,
      57,    22,    52,    14,    15,    14,    17,     3,    58,    59,
      60,    22,    62,    63,   131,     9,    36,    14,    39,    36,
      70,    15,   139,    17,    35,    22,    12,    23,    39,    77,
      39,    39,   137,   102,   103,    32,    21,    18,    19,    39,
      35,    35,    39,    31,     3,    39,     4,    97,    29,    35,
      31,    32,    18,    19,    39,   124,    37,    38,    39,    39,
       5,    39,     6,    29,    26,    31,    18,    19,   118,    26,
      32,    37,    38,    39,    39,    32,    26,    29,    31,    31,
      36,    32,    32,    31,   142,    37,    38,    39,    28,    31,
      31,    31,     7,    33,    33,    31,    27,    32,    36,   149,
     150,    35,    31,    33,    32,    27,    32,    34,    32,    32,
       8,    27,    16,    34,    39,    34,    28,    34,    27,    33,
      27,    39,    32,    34,     9,    31,    39,    35,    27,    13,
      31,    27,    35,   106,    39,    32,    36,    39,    -1,    -1,
      -1,    36,    36
};

/* YYSTOS[STATE-NUM] -- The (internal number of the) accessing
   symbol of state STATE-NUM.  */
static const yytype_uint8 yystos[] =
{
       0,    41,    43,     3,    23,    44,    60,    61,    62,     0,
      39,    39,    42,    35,    31,    52,     3,    46,     4,    32,
      39,    51,    31,    35,    39,     5,    26,    32,    18,    19,
      29,    31,    37,    38,    39,    58,    59,     9,    15,    17,
      35,    39,    55,    57,    12,    35,     6,    39,    14,    39,
      58,    58,    31,    50,    32,    25,    30,    33,    31,    31,
      31,    55,    28,    33,    50,    36,    55,    39,    45,     7,
      33,    31,    32,    32,    49,    58,    21,    39,    39,    59,
      59,    58,    58,    58,    36,    58,    58,    27,    35,    14,
      22,    39,    54,    56,    31,    58,    32,    26,    32,    50,
      34,    32,    32,    32,    27,    34,    45,    33,     4,    36,
      53,    39,     8,    34,    58,    27,    57,    57,    28,    54,
      34,    56,    27,    33,    16,    58,    36,    39,    34,    57,
      27,    31,    48,    39,    32,    47,    56,    35,    32,    26,
      32,    39,    45,    35,    56,    39,    55,     9,    39,    13,
      31,    58,    58,    27,    32,    36,    27,    36,    36
};

#define yyerrok		(yyerrstatus = 0)
#define yyclearin	(yychar = YYEMPTY)
#define YYEMPTY		(-2)
#define YYEOF		0

#define YYACCEPT	goto yyacceptlab
#define YYABORT		goto yyabortlab
#define YYERROR		goto yyerrorlab


/* Like YYERROR except do call yyerror.  This remains here temporarily
   to ease the transition to the new meaning of YYERROR, for GCC.
   Once GCC version 2 has supplanted version 1, this can go.  However,
   YYFAIL appears to be in use.  Nevertheless, it is formally deprecated
   in Bison 2.4.2's NEWS entry, where a plan to phase it out is
   discussed.  */

#define YYFAIL		goto yyerrlab
#if defined YYFAIL
  /* This is here to suppress warnings from the GCC cpp's
     -Wunused-macros.  Normally we don't worry about that warning, but
     some users do, and we want to make it easy for users to remove
     YYFAIL uses, which will produce warnings from Bison 2.5.  */
#endif

#define YYRECOVERING()  (!!yyerrstatus)

#define YYBACKUP(Token, Value)					\
do								\
  if (yychar == YYEMPTY && yylen == 1)				\
    {								\
      yychar = (Token);						\
      yylval = (Value);						\
      YYPOPSTACK (1);						\
      goto yybackup;						\
    }								\
  else								\
    {								\
      yyerror (YY_("syntax error: cannot back up")); \
      YYERROR;							\
    }								\
while (YYID (0))


#define YYTERROR	1
#define YYERRCODE	256


/* YYLLOC_DEFAULT -- Set CURRENT to span from RHS[1] to RHS[N].
   If N is 0, then set CURRENT to the empty location which ends
   the previous symbol: RHS[0] (always defined).  */

#define YYRHSLOC(Rhs, K) ((Rhs)[K])
#ifndef YYLLOC_DEFAULT
# define YYLLOC_DEFAULT(Current, Rhs, N)				\
    do									\
      if (YYID (N))                                                    \
	{								\
	  (Current).first_line   = YYRHSLOC (Rhs, 1).first_line;	\
	  (Current).first_column = YYRHSLOC (Rhs, 1).first_column;	\
	  (Current).last_line    = YYRHSLOC (Rhs, N).last_line;		\
	  (Current).last_column  = YYRHSLOC (Rhs, N).last_column;	\
	}								\
      else								\
	{								\
	  (Current).first_line   = (Current).last_line   =		\
	    YYRHSLOC (Rhs, 0).last_line;				\
	  (Current).first_column = (Current).last_column =		\
	    YYRHSLOC (Rhs, 0).last_column;				\
	}								\
    while (YYID (0))
#endif


/* This macro is provided for backward compatibility. */

#ifndef YY_LOCATION_PRINT
# define YY_LOCATION_PRINT(File, Loc) ((void) 0)
#endif


/* YYLEX -- calling `yylex' with the right arguments.  */

#ifdef YYLEX_PARAM
# define YYLEX yylex (YYLEX_PARAM)
#else
# define YYLEX yylex ()
#endif

/* Enable debugging if requested.  */
#if YYDEBUG

# ifndef YYFPRINTF
#  include <stdio.h> /* INFRINGES ON USER NAME SPACE */
#  define YYFPRINTF fprintf
# endif

# define YYDPRINTF(Args)			\
do {						\
  if (yydebug)					\
    YYFPRINTF Args;				\
} while (YYID (0))

# define YY_SYMBOL_PRINT(Title, Type, Value, Location)			  \
do {									  \
  if (yydebug)								  \
    {									  \
      YYFPRINTF (stderr, "%s ", Title);					  \
      yy_symbol_print (stderr,						  \
		  Type, Value); \
      YYFPRINTF (stderr, "\n");						  \
    }									  \
} while (YYID (0))


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_value_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_value_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (!yyvaluep)
    return;
# ifdef YYPRINT
  if (yytype < YYNTOKENS)
    YYPRINT (yyoutput, yytoknum[yytype], *yyvaluep);
# else
  YYUSE (yyoutput);
# endif
  switch (yytype)
    {
      default:
	break;
    }
}


/*--------------------------------.
| Print this symbol on YYOUTPUT.  |
`--------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_symbol_print (FILE *yyoutput, int yytype, YYSTYPE const * const yyvaluep)
#else
static void
yy_symbol_print (yyoutput, yytype, yyvaluep)
    FILE *yyoutput;
    int yytype;
    YYSTYPE const * const yyvaluep;
#endif
{
  if (yytype < YYNTOKENS)
    YYFPRINTF (yyoutput, "token %s (", yytname[yytype]);
  else
    YYFPRINTF (yyoutput, "nterm %s (", yytname[yytype]);

  yy_symbol_value_print (yyoutput, yytype, yyvaluep);
  YYFPRINTF (yyoutput, ")");
}

/*------------------------------------------------------------------.
| yy_stack_print -- Print the state stack from its BOTTOM up to its |
| TOP (included).                                                   |
`------------------------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_stack_print (yytype_int16 *yybottom, yytype_int16 *yytop)
#else
static void
yy_stack_print (yybottom, yytop)
    yytype_int16 *yybottom;
    yytype_int16 *yytop;
#endif
{
  YYFPRINTF (stderr, "Stack now");
  for (; yybottom <= yytop; yybottom++)
    {
      int yybot = *yybottom;
      YYFPRINTF (stderr, " %d", yybot);
    }
  YYFPRINTF (stderr, "\n");
}

# define YY_STACK_PRINT(Bottom, Top)				\
do {								\
  if (yydebug)							\
    yy_stack_print ((Bottom), (Top));				\
} while (YYID (0))


/*------------------------------------------------.
| Report that the YYRULE is going to be reduced.  |
`------------------------------------------------*/

#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yy_reduce_print (YYSTYPE *yyvsp, int yyrule)
#else
static void
yy_reduce_print (yyvsp, yyrule)
    YYSTYPE *yyvsp;
    int yyrule;
#endif
{
  int yynrhs = yyr2[yyrule];
  int yyi;
  unsigned long int yylno = yyrline[yyrule];
  YYFPRINTF (stderr, "Reducing stack by rule %d (line %lu):\n",
	     yyrule - 1, yylno);
  /* The symbols being reduced.  */
  for (yyi = 0; yyi < yynrhs; yyi++)
    {
      YYFPRINTF (stderr, "   $%d = ", yyi + 1);
      yy_symbol_print (stderr, yyrhs[yyprhs[yyrule] + yyi],
		       &(yyvsp[(yyi + 1) - (yynrhs)])
		       		       );
      YYFPRINTF (stderr, "\n");
    }
}

# define YY_REDUCE_PRINT(Rule)		\
do {					\
  if (yydebug)				\
    yy_reduce_print (yyvsp, Rule); \
} while (YYID (0))

/* Nonzero means print parse trace.  It is left uninitialized so that
   multiple parsers can coexist.  */
int yydebug;
#else /* !YYDEBUG */
# define YYDPRINTF(Args)
# define YY_SYMBOL_PRINT(Title, Type, Value, Location)
# define YY_STACK_PRINT(Bottom, Top)
# define YY_REDUCE_PRINT(Rule)
#endif /* !YYDEBUG */


/* YYINITDEPTH -- initial size of the parser's stacks.  */
#ifndef	YYINITDEPTH
# define YYINITDEPTH 200
#endif

/* YYMAXDEPTH -- maximum size the stacks can grow to (effective only
   if the built-in stack extension method is used).

   Do not make this value too large; the results are undefined if
   YYSTACK_ALLOC_MAXIMUM < YYSTACK_BYTES (YYMAXDEPTH)
   evaluated with infinite-precision integer arithmetic.  */

#ifndef YYMAXDEPTH
# define YYMAXDEPTH 10000
#endif


#if YYERROR_VERBOSE

# ifndef yystrlen
#  if defined __GLIBC__ && defined _STRING_H
#   define yystrlen strlen
#  else
/* Return the length of YYSTR.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static YYSIZE_T
yystrlen (const char *yystr)
#else
static YYSIZE_T
yystrlen (yystr)
    const char *yystr;
#endif
{
  YYSIZE_T yylen;
  for (yylen = 0; yystr[yylen]; yylen++)
    continue;
  return yylen;
}
#  endif
# endif

# ifndef yystpcpy
#  if defined __GLIBC__ && defined _STRING_H && defined _GNU_SOURCE
#   define yystpcpy stpcpy
#  else
/* Copy YYSRC to YYDEST, returning the address of the terminating '\0' in
   YYDEST.  */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static char *
yystpcpy (char *yydest, const char *yysrc)
#else
static char *
yystpcpy (yydest, yysrc)
    char *yydest;
    const char *yysrc;
#endif
{
  char *yyd = yydest;
  const char *yys = yysrc;

  while ((*yyd++ = *yys++) != '\0')
    continue;

  return yyd - 1;
}
#  endif
# endif

# ifndef yytnamerr
/* Copy to YYRES the contents of YYSTR after stripping away unnecessary
   quotes and backslashes, so that it's suitable for yyerror.  The
   heuristic is that double-quoting is unnecessary unless the string
   contains an apostrophe, a comma, or backslash (other than
   backslash-backslash).  YYSTR is taken from yytname.  If YYRES is
   null, do not copy; instead, return the length of what the result
   would have been.  */
static YYSIZE_T
yytnamerr (char *yyres, const char *yystr)
{
  if (*yystr == '"')
    {
      YYSIZE_T yyn = 0;
      char const *yyp = yystr;

      for (;;)
	switch (*++yyp)
	  {
	  case '\'':
	  case ',':
	    goto do_not_strip_quotes;

	  case '\\':
	    if (*++yyp != '\\')
	      goto do_not_strip_quotes;
	    /* Fall through.  */
	  default:
	    if (yyres)
	      yyres[yyn] = *yyp;
	    yyn++;
	    break;

	  case '"':
	    if (yyres)
	      yyres[yyn] = '\0';
	    return yyn;
	  }
    do_not_strip_quotes: ;
    }

  if (! yyres)
    return yystrlen (yystr);

  return yystpcpy (yyres, yystr) - yyres;
}
# endif

/* Copy into *YYMSG, which is of size *YYMSG_ALLOC, an error message
   about the unexpected token YYTOKEN for the state stack whose top is
   YYSSP.

   Return 0 if *YYMSG was successfully written.  Return 1 if *YYMSG is
   not large enough to hold the message.  In that case, also set
   *YYMSG_ALLOC to the required number of bytes.  Return 2 if the
   required number of bytes is too large to store.  */
static int
yysyntax_error (YYSIZE_T *yymsg_alloc, char **yymsg,
                yytype_int16 *yyssp, int yytoken)
{
  YYSIZE_T yysize0 = yytnamerr (0, yytname[yytoken]);
  YYSIZE_T yysize = yysize0;
  YYSIZE_T yysize1;
  enum { YYERROR_VERBOSE_ARGS_MAXIMUM = 5 };
  /* Internationalized format string. */
  const char *yyformat = 0;
  /* Arguments of yyformat. */
  char const *yyarg[YYERROR_VERBOSE_ARGS_MAXIMUM];
  /* Number of reported tokens (one for the "unexpected", one per
     "expected"). */
  int yycount = 0;

  /* There are many possibilities here to consider:
     - Assume YYFAIL is not used.  It's too flawed to consider.  See
       <http://lists.gnu.org/archive/html/bison-patches/2009-12/msg00024.html>
       for details.  YYERROR is fine as it does not invoke this
       function.
     - If this state is a consistent state with a default action, then
       the only way this function was invoked is if the default action
       is an error action.  In that case, don't check for expected
       tokens because there are none.
     - The only way there can be no lookahead present (in yychar) is if
       this state is a consistent state with a default action.  Thus,
       detecting the absence of a lookahead is sufficient to determine
       that there is no unexpected or expected token to report.  In that
       case, just report a simple "syntax error".
     - Don't assume there isn't a lookahead just because this state is a
       consistent state with a default action.  There might have been a
       previous inconsistent state, consistent state with a non-default
       action, or user semantic action that manipulated yychar.
     - Of course, the expected token list depends on states to have
       correct lookahead information, and it depends on the parser not
       to perform extra reductions after fetching a lookahead from the
       scanner and before detecting a syntax error.  Thus, state merging
       (from LALR or IELR) and default reductions corrupt the expected
       token list.  However, the list is correct for canonical LR with
       one exception: it will still contain any token that will not be
       accepted due to an error action in a later state.
  */
  if (yytoken != YYEMPTY)
    {
      int yyn = yypact[*yyssp];
      yyarg[yycount++] = yytname[yytoken];
      if (!yypact_value_is_default (yyn))
        {
          /* Start YYX at -YYN if negative to avoid negative indexes in
             YYCHECK.  In other words, skip the first -YYN actions for
             this state because they are default actions.  */
          int yyxbegin = yyn < 0 ? -yyn : 0;
          /* Stay within bounds of both yycheck and yytname.  */
          int yychecklim = YYLAST - yyn + 1;
          int yyxend = yychecklim < YYNTOKENS ? yychecklim : YYNTOKENS;
          int yyx;

          for (yyx = yyxbegin; yyx < yyxend; ++yyx)
            if (yycheck[yyx + yyn] == yyx && yyx != YYTERROR
                && !yytable_value_is_error (yytable[yyx + yyn]))
              {
                if (yycount == YYERROR_VERBOSE_ARGS_MAXIMUM)
                  {
                    yycount = 1;
                    yysize = yysize0;
                    break;
                  }
                yyarg[yycount++] = yytname[yyx];
                yysize1 = yysize + yytnamerr (0, yytname[yyx]);
                if (! (yysize <= yysize1
                       && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
                  return 2;
                yysize = yysize1;
              }
        }
    }

  switch (yycount)
    {
# define YYCASE_(N, S)                      \
      case N:                               \
        yyformat = S;                       \
      break
      YYCASE_(0, YY_("syntax error"));
      YYCASE_(1, YY_("syntax error, unexpected %s"));
      YYCASE_(2, YY_("syntax error, unexpected %s, expecting %s"));
      YYCASE_(3, YY_("syntax error, unexpected %s, expecting %s or %s"));
      YYCASE_(4, YY_("syntax error, unexpected %s, expecting %s or %s or %s"));
      YYCASE_(5, YY_("syntax error, unexpected %s, expecting %s or %s or %s or %s"));
# undef YYCASE_
    }

  yysize1 = yysize + yystrlen (yyformat);
  if (! (yysize <= yysize1 && yysize1 <= YYSTACK_ALLOC_MAXIMUM))
    return 2;
  yysize = yysize1;

  if (*yymsg_alloc < yysize)
    {
      *yymsg_alloc = 2 * yysize;
      if (! (yysize <= *yymsg_alloc
             && *yymsg_alloc <= YYSTACK_ALLOC_MAXIMUM))
        *yymsg_alloc = YYSTACK_ALLOC_MAXIMUM;
      return 1;
    }

  /* Avoid sprintf, as that infringes on the user's name space.
     Don't have undefined behavior even if the translation
     produced a string with the wrong number of "%s"s.  */
  {
    char *yyp = *yymsg;
    int yyi = 0;
    while ((*yyp = *yyformat) != '\0')
      if (*yyp == '%' && yyformat[1] == 's' && yyi < yycount)
        {
          yyp += yytnamerr (yyp, yyarg[yyi++]);
          yyformat += 2;
        }
      else
        {
          yyp++;
          yyformat++;
        }
  }
  return 0;
}
#endif /* YYERROR_VERBOSE */

/*-----------------------------------------------.
| Release the memory associated to this symbol.  |
`-----------------------------------------------*/

/*ARGSUSED*/
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
static void
yydestruct (const char *yymsg, int yytype, YYSTYPE *yyvaluep)
#else
static void
yydestruct (yymsg, yytype, yyvaluep)
    const char *yymsg;
    int yytype;
    YYSTYPE *yyvaluep;
#endif
{
  YYUSE (yyvaluep);

  if (!yymsg)
    yymsg = "Deleting";
  YY_SYMBOL_PRINT (yymsg, yytype, yyvaluep, yylocationp);

  switch (yytype)
    {

      default:
	break;
    }
}


/* Prevent warnings from -Wmissing-prototypes.  */
#ifdef YYPARSE_PARAM
#if defined __STDC__ || defined __cplusplus
int yyparse (void *YYPARSE_PARAM);
#else
int yyparse ();
#endif
#else /* ! YYPARSE_PARAM */
#if defined __STDC__ || defined __cplusplus
int yyparse (void);
#else
int yyparse ();
#endif
#endif /* ! YYPARSE_PARAM */


/* The lookahead symbol.  */
int yychar;

/* The semantic value of the lookahead symbol.  */
YYSTYPE yylval;

/* Number of syntax errors so far.  */
int yynerrs;


/*----------.
| yyparse.  |
`----------*/

#ifdef YYPARSE_PARAM
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void *YYPARSE_PARAM)
#else
int
yyparse (YYPARSE_PARAM)
    void *YYPARSE_PARAM;
#endif
#else /* ! YYPARSE_PARAM */
#if (defined __STDC__ || defined __C99__FUNC__ \
     || defined __cplusplus || defined _MSC_VER)
int
yyparse (void)
#else
int
yyparse ()

#endif
#endif
{
    int yystate;
    /* Number of tokens to shift before error messages enabled.  */
    int yyerrstatus;

    /* The stacks and their tools:
       `yyss': related to states.
       `yyvs': related to semantic values.

       Refer to the stacks thru separate pointers, to allow yyoverflow
       to reallocate them elsewhere.  */

    /* The state stack.  */
    yytype_int16 yyssa[YYINITDEPTH];
    yytype_int16 *yyss;
    yytype_int16 *yyssp;

    /* The semantic value stack.  */
    YYSTYPE yyvsa[YYINITDEPTH];
    YYSTYPE *yyvs;
    YYSTYPE *yyvsp;

    YYSIZE_T yystacksize;

  int yyn;
  int yyresult;
  /* Lookahead token as an internal (translated) token number.  */
  int yytoken;
  /* The variables used to return semantic value and location from the
     action routines.  */
  YYSTYPE yyval;

#if YYERROR_VERBOSE
  /* Buffer for error messages, and its allocated size.  */
  char yymsgbuf[128];
  char *yymsg = yymsgbuf;
  YYSIZE_T yymsg_alloc = sizeof yymsgbuf;
#endif

#define YYPOPSTACK(N)   (yyvsp -= (N), yyssp -= (N))

  /* The number of symbols on the RHS of the reduced rule.
     Keep to zero when no symbol should be popped.  */
  int yylen = 0;

  yytoken = 0;
  yyss = yyssa;
  yyvs = yyvsa;
  yystacksize = YYINITDEPTH;

  YYDPRINTF ((stderr, "Starting parse\n"));

  yystate = 0;
  yyerrstatus = 0;
  yynerrs = 0;
  yychar = YYEMPTY; /* Cause a token to be read.  */

  /* Initialize stack pointers.
     Waste one element of value and location stack
     so that they stay on the same level as the state stack.
     The wasted elements are never initialized.  */
  yyssp = yyss;
  yyvsp = yyvs;

  goto yysetstate;

/*------------------------------------------------------------.
| yynewstate -- Push a new state, which is found in yystate.  |
`------------------------------------------------------------*/
 yynewstate:
  /* In all cases, when you get here, the value and location stacks
     have just been pushed.  So pushing a state here evens the stacks.  */
  yyssp++;

 yysetstate:
  *yyssp = yystate;

  if (yyss + yystacksize - 1 <= yyssp)
    {
      /* Get the current used size of the three stacks, in elements.  */
      YYSIZE_T yysize = yyssp - yyss + 1;

#ifdef yyoverflow
      {
	/* Give user a chance to reallocate the stack.  Use copies of
	   these so that the &'s don't force the real ones into
	   memory.  */
	YYSTYPE *yyvs1 = yyvs;
	yytype_int16 *yyss1 = yyss;

	/* Each stack pointer address is followed by the size of the
	   data in use in that stack, in bytes.  This used to be a
	   conditional around just the two extra args, but that might
	   be undefined if yyoverflow is a macro.  */
	yyoverflow (YY_("memory exhausted"),
		    &yyss1, yysize * sizeof (*yyssp),
		    &yyvs1, yysize * sizeof (*yyvsp),
		    &yystacksize);

	yyss = yyss1;
	yyvs = yyvs1;
      }
#else /* no yyoverflow */
# ifndef YYSTACK_RELOCATE
      goto yyexhaustedlab;
# else
      /* Extend the stack our own way.  */
      if (YYMAXDEPTH <= yystacksize)
	goto yyexhaustedlab;
      yystacksize *= 2;
      if (YYMAXDEPTH < yystacksize)
	yystacksize = YYMAXDEPTH;

      {
	yytype_int16 *yyss1 = yyss;
	union yyalloc *yyptr =
	  (union yyalloc *) YYSTACK_ALLOC (YYSTACK_BYTES (yystacksize));
	if (! yyptr)
	  goto yyexhaustedlab;
	YYSTACK_RELOCATE (yyss_alloc, yyss);
	YYSTACK_RELOCATE (yyvs_alloc, yyvs);
#  undef YYSTACK_RELOCATE
	if (yyss1 != yyssa)
	  YYSTACK_FREE (yyss1);
      }
# endif
#endif /* no yyoverflow */

      yyssp = yyss + yysize - 1;
      yyvsp = yyvs + yysize - 1;

      YYDPRINTF ((stderr, "Stack size increased to %lu\n",
		  (unsigned long int) yystacksize));

      if (yyss + yystacksize - 1 <= yyssp)
	YYABORT;
    }

  YYDPRINTF ((stderr, "Entering state %d\n", yystate));

  if (yystate == YYFINAL)
    YYACCEPT;

  goto yybackup;

/*-----------.
| yybackup.  |
`-----------*/
yybackup:

  /* Do appropriate processing given the current state.  Read a
     lookahead token if we need one and don't already have one.  */

  /* First try to decide what to do without reference to lookahead token.  */
  yyn = yypact[yystate];
  if (yypact_value_is_default (yyn))
    goto yydefault;

  /* Not known => get a lookahead token if don't already have one.  */

  /* YYCHAR is either YYEMPTY or YYEOF or a valid lookahead symbol.  */
  if (yychar == YYEMPTY)
    {
      YYDPRINTF ((stderr, "Reading a token: "));
      yychar = YYLEX;
    }

  if (yychar <= YYEOF)
    {
      yychar = yytoken = YYEOF;
      YYDPRINTF ((stderr, "Now at end of input.\n"));
    }
  else
    {
      yytoken = YYTRANSLATE (yychar);
      YY_SYMBOL_PRINT ("Next token is", yytoken, &yylval, &yylloc);
    }

  /* If the proper action on seeing token YYTOKEN is to reduce or to
     detect an error, take that action.  */
  yyn += yytoken;
  if (yyn < 0 || YYLAST < yyn || yycheck[yyn] != yytoken)
    goto yydefault;
  yyn = yytable[yyn];
  if (yyn <= 0)
    {
      if (yytable_value_is_error (yyn))
        goto yyerrlab;
      yyn = -yyn;
      goto yyreduce;
    }

  /* Count tokens shifted since error; after three, turn off error
     status.  */
  if (yyerrstatus)
    yyerrstatus--;

  /* Shift the lookahead token.  */
  YY_SYMBOL_PRINT ("Shifting", yytoken, &yylval, &yylloc);

  /* Discard the shifted token.  */
  yychar = YYEMPTY;

  yystate = yyn;
  *++yyvsp = yylval;

  goto yynewstate;


/*-----------------------------------------------------------.
| yydefault -- do the default action for the current state.  |
`-----------------------------------------------------------*/
yydefault:
  yyn = yydefact[yystate];
  if (yyn == 0)
    goto yyerrlab;
  goto yyreduce;


/*-----------------------------.
| yyreduce -- Do a reduction.  |
`-----------------------------*/
yyreduce:
  /* yyn is the number of a rule to reduce with.  */
  yylen = yyr2[yyn];

  /* If YYLEN is nonzero, implement the default value of the action:
     `$$ = $1'.

     Otherwise, the following line sets YYVAL to garbage.
     This behavior is undocumented and Bison
     users should not rely upon it.  Assigning to YYVAL
     unconditionally makes the parser a bit smaller, and it avoids a
     GCC warning that YYVAL may be used uninitialized.  */
  yyval = yyvsp[1-yylen];


  YY_REDUCE_PRINT (yyn);
  switch (yyn)
    {
        case 16:

/* Line 1806 of yacc.c  */
#line 121 "parser.y"
    {
	       		(yyval.inputs) = (yyvsp[(1) - (3)].inputs);
	       		(yyval.inputs)[i++] = (yyvsp[(3) - (3)].list_of_tokens);
	       }
    break;

  case 17:

/* Line 1806 of yacc.c  */
#line 125 "parser.y"
    {		
	       		if(list_of_expressions != NULL){
				free(list_of_expressions);
			}
			list_of_expressions = (struct StringArray**)malloc(max_args*sizeof(struct StringArray*));
			int j=0;
			for(; j<max_args; j++){
				list_of_expressions[j] = NULL;
			}
			i = 0;
			list_of_expressions[i++] = (yyvsp[(1) - (1)].list_of_tokens);
			
			(yyval.inputs) = list_of_expressions;
	       }
    break;

  case 18:

/* Line 1806 of yacc.c  */
#line 143 "parser.y"
    {
		   	(yyval.inputs) = (yyvsp[(2) - (3)].inputs);
		   }
    break;

  case 19:

/* Line 1806 of yacc.c  */
#line 146 "parser.y"
    {
			int j=0;
			for(; j<max_args; j++){
				list_of_expressions[j] = NULL;
			}
		   }
    break;

  case 20:

/* Line 1806 of yacc.c  */
#line 156 "parser.y"
    {
	       		(yyval.list_of_tokens) = (yyvsp[(1) - (3)].list_of_tokens);
			append((yyval.list_of_tokens), (yyvsp[(3) - (3)].word));	
	       }
    break;

  case 21:

/* Line 1806 of yacc.c  */
#line 160 "parser.y"
    {
	       		(yyval.list_of_tokens)  = new_string_array();
			append((yyval.list_of_tokens), (yyvsp[(1) - (1)].word));
	       }
    break;

  case 22:

/* Line 1806 of yacc.c  */
#line 168 "parser.y"
    {
		   	(yyval.list_of_tokens) = (yyvsp[(2) - (3)].list_of_tokens);
		   }
    break;

  case 23:

/* Line 1806 of yacc.c  */
#line 171 "parser.y"
    {
		   	(yyval.list_of_tokens) = new_string_array();
		   }
    break;

  case 27:

/* Line 1806 of yacc.c  */
#line 188 "parser.y"
    {
	       		(yyval.list_of_tokens) = (yyvsp[(1) - (2)].list_of_tokens);
			cat((yyval.list_of_tokens), (yyvsp[(2) - (2)].list_of_tokens));
			free((yyvsp[(2) - (2)].list_of_tokens));
	       }
    break;

  case 28:

/* Line 1806 of yacc.c  */
#line 193 "parser.y"
    {
		(yyval.list_of_tokens) = new_string_array();
	  }
    break;

  case 29:

/* Line 1806 of yacc.c  */
#line 199 "parser.y"
    {
    	(yyval.list_of_tokens) = new_string_array();
	append((yyval.list_of_tokens), (yyvsp[(1) - (3)].word));
	append((yyval.list_of_tokens), (yyvsp[(2) - (3)].word));
	append((yyval.list_of_tokens), (yyvsp[(3) - (3)].word));
    }
    break;

  case 30:

/* Line 1806 of yacc.c  */
#line 205 "parser.y"
    {
    	(yyval.list_of_tokens) = new_string_array();
	append((yyval.list_of_tokens), (yyvsp[(1) - (1)].word));
    }
    break;

  case 31:

/* Line 1806 of yacc.c  */
#line 209 "parser.y"
    {
    	(yyval.list_of_tokens) = new_string_array();
	append((yyval.list_of_tokens), (yyvsp[(1) - (1)].word));
    }
    break;

  case 32:

/* Line 1806 of yacc.c  */
#line 213 "parser.y"
    {
    	(yyval.list_of_tokens) = new_string_array();
	append((yyval.list_of_tokens), (yyvsp[(1) - (1)].word));
    }
    break;

  case 33:

/* Line 1806 of yacc.c  */
#line 220 "parser.y"
    {
	 	(yyval.list_of_tokens) = (yyvsp[(2) - (3)].list_of_tokens);
		insert((yyval.list_of_tokens), (yyvsp[(1) - (3)].word), 0);
	 	append((yyval.list_of_tokens), (yyvsp[(3) - (3)].word));
	 }
    break;

  case 34:

/* Line 1806 of yacc.c  */
#line 225 "parser.y"
    {
	 	(yyval.list_of_tokens) = (yyvsp[(3) - (5)].list_of_tokens);
		insert((yyval.list_of_tokens), (yyvsp[(2) - (5)].word) ,0);
		insert((yyval.list_of_tokens), (yyvsp[(1) - (5)].word), 0);
		append((yyval.list_of_tokens), (yyvsp[(4) - (5)].word));
		append((yyval.list_of_tokens), (yyvsp[(5) - (5)].word));
	 }
    break;

  case 35:

/* Line 1806 of yacc.c  */
#line 232 "parser.y"
    {
	 	(yyval.list_of_tokens) = (yyvsp[(3) - (4)].list_of_tokens);
		insert((yyval.list_of_tokens), (yyvsp[(2) - (4)].word), 0);
		insert((yyval.list_of_tokens), (yyvsp[(1) - (4)].word), 0);
		append((yyval.list_of_tokens), (yyvsp[(4) - (4)].word));
	 }
    break;

  case 36:

/* Line 1806 of yacc.c  */
#line 238 "parser.y"
    {
	 	(yyval.list_of_tokens) = (yyvsp[(3) - (7)].list_of_tokens);	 
		insert((yyval.list_of_tokens), (yyvsp[(2) - (7)].word), 0);
		insert((yyval.list_of_tokens), (yyvsp[(1) - (7)].word), 0);
		append((yyval.list_of_tokens), (yyvsp[(4) - (7)].word));
		append((yyval.list_of_tokens), (yyvsp[(5) - (7)].word));
		cat((yyval.list_of_tokens), (yyvsp[(6) - (7)].list_of_tokens));
		append((yyval.list_of_tokens),(yyvsp[(7) - (7)].word));
		free((yyvsp[(6) - (7)].list_of_tokens));
	 }
    break;

  case 37:

/* Line 1806 of yacc.c  */
#line 248 "parser.y"
    {
	 	(yyval.list_of_tokens) = (yyvsp[(3) - (5)].list_of_tokens);
		insert((yyval.list_of_tokens), (yyvsp[(2) - (5)].word), 0);
		insert((yyval.list_of_tokens), (yyvsp[(1) - (5)].word), 0);
		append((yyval.list_of_tokens), (yyvsp[(4) - (5)].word));
		cat((yyval.list_of_tokens), (yyvsp[(5) - (5)].list_of_tokens));
	 }
    break;

  case 38:

/* Line 1806 of yacc.c  */
#line 255 "parser.y"
    {
	 	(yyval.list_of_tokens) = (yyvsp[(3) - (7)].list_of_tokens);
		insert((yyval.list_of_tokens), (yyvsp[(2) - (7)].word), 0);
		insert((yyval.list_of_tokens), (yyvsp[(1) - (7)].word), 0);
		append((yyval.list_of_tokens), (yyvsp[(4) - (7)].word));
		cat((yyval.list_of_tokens), (yyvsp[(5) - (7)].list_of_tokens));
	 	append((yyval.list_of_tokens), (yyvsp[(6) - (7)].word));
	 	cat((yyval.list_of_tokens), (yyvsp[(7) - (7)].list_of_tokens));
		free((yyvsp[(5) - (7)].list_of_tokens));
		free((yyvsp[(7) - (7)].list_of_tokens));
	 }
    break;

  case 39:

/* Line 1806 of yacc.c  */
#line 266 "parser.y"
    {
	 	(yyval.list_of_tokens) = (yyvsp[(3) - (5)].list_of_tokens);;
		insert((yyval.list_of_tokens), (yyvsp[(2) - (5)].word), 0);
		insert((yyval.list_of_tokens), (yyvsp[(1) - (5)].word), 0);
		append((yyval.list_of_tokens), (yyvsp[(4) - (5)].word));
		cat((yyval.list_of_tokens), (yyvsp[(5) - (5)].list_of_tokens));
		free((yyvsp[(5) - (5)].list_of_tokens));
	 }
    break;

  case 40:

/* Line 1806 of yacc.c  */
#line 274 "parser.y"
    {
	 	//macro statement
		 struct Macro* sub = macro_locate(macros, (yyvsp[(1) - (3)].word));
		 if(sub == NULL){
		 	yyerror("Macro not found");
		 }else{
		 	struct StringArray* result = substitute(sub, (yyvsp[(2) - (3)].inputs));
			(yyval.list_of_tokens) = result;
			append((yyval.list_of_tokens),(yyvsp[(3) - (3)].word));
			i = 0;
		 }
	 }
    break;

  case 41:

/* Line 1806 of yacc.c  */
#line 289 "parser.y"
    {
	  	(yyval.list_of_tokens) = (yyvsp[(1) - (3)].list_of_tokens);
		append((yyval.list_of_tokens), (yyvsp[(2) - (3)].word));
		cat((yyval.list_of_tokens), (yyvsp[(3) - (3)].list_of_tokens));
		free((yyvsp[(3) - (3)].list_of_tokens));
	  }
    break;

  case 42:

/* Line 1806 of yacc.c  */
#line 295 "parser.y"
    {
	  	(yyval.list_of_tokens) = (yyvsp[(1) - (4)].list_of_tokens);
		append((yyval.list_of_tokens), (yyvsp[(2) - (4)].word));
		cat((yyval.list_of_tokens), (yyvsp[(3) - (4)].list_of_tokens));
	  	append((yyval.list_of_tokens), (yyvsp[(4) - (4)].word));
		free((yyvsp[(3) - (4)].list_of_tokens));
	  }
    break;

  case 43:

/* Line 1806 of yacc.c  */
#line 302 "parser.y"
    {
	  	(yyval.list_of_tokens) = (yyvsp[(1) - (3)].list_of_tokens);
		append((yyval.list_of_tokens), (yyvsp[(2) - (3)].word));
		append((yyval.list_of_tokens), (yyvsp[(3) - (3)].word));
		
	  }
    break;

  case 44:

/* Line 1806 of yacc.c  */
#line 308 "parser.y"
    {
	  	(yyval.list_of_tokens) = (yyvsp[(1) - (1)].list_of_tokens);
	  }
    break;

  case 45:

/* Line 1806 of yacc.c  */
#line 311 "parser.y"
    {
	  	(yyval.list_of_tokens) = (yyvsp[(1) - (4)].list_of_tokens);
		append((yyval.list_of_tokens), (yyvsp[(2) - (4)].word));
		append((yyval.list_of_tokens), (yyvsp[(3) - (4)].word));
		//Convert expression list to a single array
		int j=0;
		append((yyval.list_of_tokens), "(");
		for(; j<i; j++){
			cat((yyval.list_of_tokens), (yyvsp[(4) - (4)].inputs)[j]);
			if(j+1<i){
				append((yyval.list_of_tokens),",");
			}
		}
		append((yyval.list_of_tokens), ")");
		i = 0;
		
	  }
    break;

  case 46:

/* Line 1806 of yacc.c  */
#line 328 "parser.y"
    {
	  	 struct Macro* sub = macro_locate(macros, (yyvsp[(1) - (2)].word));
		 if(sub == NULL){
		 	yyerror("Macro not found");
		 }else{
		 	struct StringArray* result = substitute(sub, (yyvsp[(2) - (2)].inputs));
			(yyval.list_of_tokens) = result;
			print((yyval.list_of_tokens));
			i = 0;
		 }
		
	 	 //macro statement call	
	  }
    break;

  case 47:

/* Line 1806 of yacc.c  */
#line 343 "parser.y"
    {
		 	(yyval.list_of_tokens) = new_string_array();
			append((yyval.list_of_tokens), (yyvsp[(1) - (1)].word));
		 }
    break;

  case 48:

/* Line 1806 of yacc.c  */
#line 347 "parser.y"
    {
		 	(yyval.list_of_tokens) = new_string_array();
			append((yyval.list_of_tokens), (yyvsp[(1) - (1)].word));
		 }
    break;

  case 49:

/* Line 1806 of yacc.c  */
#line 351 "parser.y"
    {
		 	(yyval.list_of_tokens) = new_string_array();
			append((yyval.list_of_tokens), (yyvsp[(1) - (1)].word));
		 }
    break;

  case 50:

/* Line 1806 of yacc.c  */
#line 355 "parser.y"
    {
		 	(yyval.list_of_tokens) = new_string_array();
			append((yyval.list_of_tokens), (yyvsp[(1) - (1)].word));
		 }
    break;

  case 51:

/* Line 1806 of yacc.c  */
#line 359 "parser.y"
    {
		 	(yyval.list_of_tokens) = new_string_array();
			append((yyval.list_of_tokens), (yyvsp[(1) - (5)].word));
			append((yyval.list_of_tokens), (yyvsp[(2) - (5)].word));
			append((yyval.list_of_tokens), (yyvsp[(3) - (5)].word));
			cat((yyval.list_of_tokens), (yyvsp[(4) - (5)].list_of_tokens));
			append((yyval.list_of_tokens), (yyvsp[(5) - (5)].word));
			free((yyvsp[(4) - (5)].list_of_tokens));
		 }
    break;

  case 52:

/* Line 1806 of yacc.c  */
#line 368 "parser.y"
    {
		 	(yyval.list_of_tokens) = new_string_array();
			append((yyval.list_of_tokens), (yyvsp[(1) - (4)].word));
			append((yyval.list_of_tokens), (yyvsp[(2) - (4)].word));
			append((yyval.list_of_tokens), (yyvsp[(3) - (4)].word));
			append((yyval.list_of_tokens), (yyvsp[(4) - (4)].word));
			
		 }
    break;

  case 53:

/* Line 1806 of yacc.c  */
#line 376 "parser.y"
    {
		 	(yyval.list_of_tokens) = new_string_array();
			append((yyval.list_of_tokens), (yyvsp[(1) - (2)].word));
			cat((yyval.list_of_tokens), (yyvsp[(2) - (2)].list_of_tokens));
			free((yyvsp[(2) - (2)].list_of_tokens));
		 }
    break;

  case 54:

/* Line 1806 of yacc.c  */
#line 382 "parser.y"
    {
		 	(yyval.list_of_tokens) = new_string_array();
			append((yyval.list_of_tokens), (yyvsp[(1) - (3)].word));
			cat((yyval.list_of_tokens), (yyvsp[(2) - (3)].list_of_tokens));
			append((yyval.list_of_tokens), (yyvsp[(3) - (3)].word));
			free((yyvsp[(2) - (3)].list_of_tokens));
		 }
    break;

  case 57:

/* Line 1806 of yacc.c  */
#line 396 "parser.y"
    {
			(yyval.macro) = new_macro();
			(yyval.macro)->name = (yyvsp[(2) - (6)].word);
			(yyval.macro)->args = (yyvsp[(3) - (6)].list_of_tokens);
			(yyval.macro)->expr = (yyvsp[(4) - (6)].word);
			macro_append(macros, (yyval.macro));
			if((yyval.macro)->args->size > max_args){
				max_args = (yyval.macro)->args->size;
			}
		}
    break;

  case 58:

/* Line 1806 of yacc.c  */
#line 411 "parser.y"
    {
		 	(yyval.macro) = new_macro();
			(yyval.macro)->name = (yyvsp[(2) - (6)].word);
			(yyval.macro)->args = (yyvsp[(3) - (6)].list_of_tokens); 
			(yyval.macro)->expr = (yyvsp[(5) - (6)].list_of_tokens);
			macro_append(macros, (yyval.macro));
			if((yyval.macro)->args->size > max_args){
				max_args = (yyval.macro)->args->size;
			}
		 }
    break;



/* Line 1806 of yacc.c  */
#line 2013 "parser.tab.c"
      default: break;
    }
  /* User semantic actions sometimes alter yychar, and that requires
     that yytoken be updated with the new translation.  We take the
     approach of translating immediately before every use of yytoken.
     One alternative is translating here after every semantic action,
     but that translation would be missed if the semantic action invokes
     YYABORT, YYACCEPT, or YYERROR immediately after altering yychar or
     if it invokes YYBACKUP.  In the case of YYABORT or YYACCEPT, an
     incorrect destructor might then be invoked immediately.  In the
     case of YYERROR or YYBACKUP, subsequent parser actions might lead
     to an incorrect destructor call or verbose syntax error message
     before the lookahead is translated.  */
  YY_SYMBOL_PRINT ("-> $$ =", yyr1[yyn], &yyval, &yyloc);

  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);

  *++yyvsp = yyval;

  /* Now `shift' the result of the reduction.  Determine what state
     that goes to, based on the state we popped back to and the rule
     number reduced by.  */

  yyn = yyr1[yyn];

  yystate = yypgoto[yyn - YYNTOKENS] + *yyssp;
  if (0 <= yystate && yystate <= YYLAST && yycheck[yystate] == *yyssp)
    yystate = yytable[yystate];
  else
    yystate = yydefgoto[yyn - YYNTOKENS];

  goto yynewstate;


/*------------------------------------.
| yyerrlab -- here on detecting error |
`------------------------------------*/
yyerrlab:
  /* Make sure we have latest lookahead translation.  See comments at
     user semantic actions for why this is necessary.  */
  yytoken = yychar == YYEMPTY ? YYEMPTY : YYTRANSLATE (yychar);

  /* If not already recovering from an error, report this error.  */
  if (!yyerrstatus)
    {
      ++yynerrs;
#if ! YYERROR_VERBOSE
      yyerror (YY_("syntax error"));
#else
# define YYSYNTAX_ERROR yysyntax_error (&yymsg_alloc, &yymsg, \
                                        yyssp, yytoken)
      {
        char const *yymsgp = YY_("syntax error");
        int yysyntax_error_status;
        yysyntax_error_status = YYSYNTAX_ERROR;
        if (yysyntax_error_status == 0)
          yymsgp = yymsg;
        else if (yysyntax_error_status == 1)
          {
            if (yymsg != yymsgbuf)
              YYSTACK_FREE (yymsg);
            yymsg = (char *) YYSTACK_ALLOC (yymsg_alloc);
            if (!yymsg)
              {
                yymsg = yymsgbuf;
                yymsg_alloc = sizeof yymsgbuf;
                yysyntax_error_status = 2;
              }
            else
              {
                yysyntax_error_status = YYSYNTAX_ERROR;
                yymsgp = yymsg;
              }
          }
        yyerror (yymsgp);
        if (yysyntax_error_status == 2)
          goto yyexhaustedlab;
      }
# undef YYSYNTAX_ERROR
#endif
    }



  if (yyerrstatus == 3)
    {
      /* If just tried and failed to reuse lookahead token after an
	 error, discard it.  */

      if (yychar <= YYEOF)
	{
	  /* Return failure if at end of input.  */
	  if (yychar == YYEOF)
	    YYABORT;
	}
      else
	{
	  yydestruct ("Error: discarding",
		      yytoken, &yylval);
	  yychar = YYEMPTY;
	}
    }

  /* Else will try to reuse lookahead token after shifting the error
     token.  */
  goto yyerrlab1;


/*---------------------------------------------------.
| yyerrorlab -- error raised explicitly by YYERROR.  |
`---------------------------------------------------*/
yyerrorlab:

  /* Pacify compilers like GCC when the user code never invokes
     YYERROR and the label yyerrorlab therefore never appears in user
     code.  */
  if (/*CONSTCOND*/ 0)
     goto yyerrorlab;

  /* Do not reclaim the symbols of the rule which action triggered
     this YYERROR.  */
  YYPOPSTACK (yylen);
  yylen = 0;
  YY_STACK_PRINT (yyss, yyssp);
  yystate = *yyssp;
  goto yyerrlab1;


/*-------------------------------------------------------------.
| yyerrlab1 -- common code for both syntax error and YYERROR.  |
`-------------------------------------------------------------*/
yyerrlab1:
  yyerrstatus = 3;	/* Each real token shifted decrements this.  */

  for (;;)
    {
      yyn = yypact[yystate];
      if (!yypact_value_is_default (yyn))
	{
	  yyn += YYTERROR;
	  if (0 <= yyn && yyn <= YYLAST && yycheck[yyn] == YYTERROR)
	    {
	      yyn = yytable[yyn];
	      if (0 < yyn)
		break;
	    }
	}

      /* Pop the current state because it cannot handle the error token.  */
      if (yyssp == yyss)
	YYABORT;


      yydestruct ("Error: popping",
		  yystos[yystate], yyvsp);
      YYPOPSTACK (1);
      yystate = *yyssp;
      YY_STACK_PRINT (yyss, yyssp);
    }

  *++yyvsp = yylval;


  /* Shift the error token.  */
  YY_SYMBOL_PRINT ("Shifting", yystos[yyn], yyvsp, yylsp);

  yystate = yyn;
  goto yynewstate;


/*-------------------------------------.
| yyacceptlab -- YYACCEPT comes here.  |
`-------------------------------------*/
yyacceptlab:
  yyresult = 0;
  goto yyreturn;

/*-----------------------------------.
| yyabortlab -- YYABORT comes here.  |
`-----------------------------------*/
yyabortlab:
  yyresult = 1;
  goto yyreturn;

#if !defined(yyoverflow) || YYERROR_VERBOSE
/*-------------------------------------------------.
| yyexhaustedlab -- memory exhaustion comes here.  |
`-------------------------------------------------*/
yyexhaustedlab:
  yyerror (YY_("memory exhausted"));
  yyresult = 2;
  /* Fall through.  */
#endif

yyreturn:
  if (yychar != YYEMPTY)
    {
      /* Make sure we have latest lookahead translation.  See comments at
         user semantic actions for why this is necessary.  */
      yytoken = YYTRANSLATE (yychar);
      yydestruct ("Cleanup: discarding lookahead",
                  yytoken, &yylval);
    }
  /* Do not reclaim the symbols of the rule which action triggered
     this YYABORT or YYACCEPT.  */
  YYPOPSTACK (yylen);
  YY_STACK_PRINT (yyss, yyssp);
  while (yyssp != yyss)
    {
      yydestruct ("Cleanup: popping",
		  yystos[*yyssp], yyvsp);
      YYPOPSTACK (1);
    }
#ifndef yyoverflow
  if (yyss != yyssa)
    YYSTACK_FREE (yyss);
#endif
#if YYERROR_VERBOSE
  if (yymsg != yymsgbuf)
    YYSTACK_FREE (yymsg);
#endif
  /* Make sure YYID is used.  */
  return YYID (yyresult);
}



/* Line 2067 of yacc.c  */
#line 427 "parser.y"


int main(){
	max_args = 0;
	list_of_expressions = NULL;
	i = 0;
	macros = new_macro_array(); 
	yyparse();
}



void yyerror(char *message) {
	printf("ERROR PARSING : %s\n",message);	
	exit(-1);
}

