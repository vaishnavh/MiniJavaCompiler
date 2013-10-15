all:	parser.y parser.l vector.c vector.h
	make -f MAKEME
	bison -d parser.y
	flex parser.l
	gcc parser.tab.c lex.yy.c vector.c macrovector.c macro.c -lfl -o parser
	./parser < Example.java
