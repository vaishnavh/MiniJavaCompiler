#include<stdio.h>
#include "macro.h"
#include "macrovector.h"
#include "vector.h"
int main(){
	struct StringArray* check;
	check = new_string_array();
	char A[10] ="B";
	char B[10] = "C";
	append(check, A);
	append(check, B);
	print(check);
	/*
	struct MacroArray* x;
	struct Macro* z = new_macro();
	x = new_macro_array();
	macro_append(x, z);
	char A[5] = "A", B[5] = "B";
 	append_arg(z, A);
	append_arg(z, B);
	z->name = "SUM";
 	append_token(z, "A");
	append_token(z, "+");
	append_token(z, "B");
	struct StringArray** input;
	input = (struct StringArray**)malloc(2*sizeof(struct StringArray*));
	input[0] = new_string_array();
	input[1] = new_string_array();
	append(input[0], "10");
	append(input[0], "-");
	append(input[0], "10");
	append(input[1], "11");
	struct StringArray* result = evaluate(x, z->name, input);
	print(result);*/
	//
	return 0;
/*	struct StringArray *A, *B;
	A = new_string_array();
	B = new_string_array();
	//append(A, "Hi");
	//append(A, "Hello");
	append(B, "Beee");
	append(B, "This");
	merge(A, 0, B);
	print(A);
*/
}
