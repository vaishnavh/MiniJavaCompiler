all:	parser.y parser.l
	bison -d parser.y
	flex parser.l
	gcc parser.tab.c lex.yy.c -lfl -parser
	./parser < Example.java
