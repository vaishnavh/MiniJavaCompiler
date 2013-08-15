#include "macrovector.h"
struct MacroArray* new_macro_array(){
	struct MacroArray* z;
	z = (struct MacroArray*)malloc(sizeof(struct MacroArray));
	z->size = 0;
	z->p = NULL;
	return z;
}

void macro_append(struct MacroArray* z, struct Macro* input){
	macro_insert(z, input, macro_end(z));
}

void macro_insert(struct MacroArray* z, struct Macro* input, int index){
	if(macro_in_range(z, index)){ //array is non-empty
		z->size++;
		z->p = (struct Macro**)realloc(z->p,z->size*sizeof(struct Macro*));
		int i = z->size-1;
		for(; i>index; i--){
			z->p[i] = z->p[i-1];
		}
		z->p[i] = input;
	}else if(macro_end(z)==0 && index == 0){ //macro_insert first element
		z->p = (struct Macro**)malloc(sizeof(struct Macro*));
		z->p[0] = input;		
		z->size = 1;	
	}

}

void macro_delete(struct MacroArray* z, int index){
	if(macro_in_range(z, index)){
		int i;
		for(i= index; i<z->size-1; i++){
			z->p[i] = z->p[i+1];
		}
		z->size--;
	}
}

int macro_end(struct MacroArray* z){
	return z->size; //returns the first empty position
}

int macro_in_range(struct MacroArray* z, int p){
	if(macro_end(z)==0) return 0;
	if(p>=0 && p<=macro_end(z)) return 1;
	return 0;
}


void macro_print(struct MacroArray* z){
	int i;	
	for(i=0; i<z->size; i++){
		print_a_macro(z->p[i]);
		printf("\n");
	}
}


struct Macro* macro_locate(struct MacroArray* z, char* cmp){
	int i = 0;
	for( i=0; i<macro_end(z); i++){
		if(compare_macros(z->p[i],cmp)==0){
			return z->p[i];
		}
	}
	return NULL;
}


struct StringArray* evaluate(struct MacroArray* z, char *macro_name, struct StringArray** input){

	struct Macro* which = macro_locate(z, macro_name);
	if(which!=NULL){
		return substitute(which, input);
	}else{
		return NULL;
	}
}
