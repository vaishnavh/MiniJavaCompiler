#include<stdio.h>
#include "macro.h"
#include "macrovector.h"
int main(){
	struct Macro* z = new_macro();
	char A[5] = "A", B[5] = "B";
 	append_arg(z, A);
	append_arg(z, B);
	return 0;
}
