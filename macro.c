#include "macro.h"
struct Macro* new_macro(){
	struct Macro* z;
	z = (struct Macro)malloc(sizeof(struct Macro*));
	z->args = new_string_array();
	z->expr = new_string_array();
	z->input = new_string_array();
	return z;
}
void append_arg(struct Macro* z,char *arg){
	append(z->args, arg);
}

void append_token(struct Macro* z, char *tok){
	append(z->expr, tok);
}


void compare(struct Macro* z, char* name){
	return strcmp(z->name, name);
}

void append_input(struct Macro* z, struct StringArray* y){
	append(z->input, y);
}

struct StringArray* substitute(struct Macro* z, struct StringArray** input){
//Input is the list of input arguments given to this macro
//This  will be mapped with its actual arguments
	int i;
	struct StringArray* result = (struct StringArray*)malloc(sizeof(StringArray));
	*result = *(z->expr);
	for(i=0; i<result->size; i++){
		int where = locate(z->args,result->p[i]);
		if(where!=-1){ //An identifier in the expression matches with macros actual argument
			int replacement_size = input->p[i]->size;
			delete(result,i);
			merge(result, i, input->p[i]);
			i+=replacement_size;
		}
	}	
}

