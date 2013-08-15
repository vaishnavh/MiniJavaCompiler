#include "macrovector.h"
struct MacroArray* new_macro_array(){
	struct MacroArray* z;
	z = (struct MacroArray*)malloc(sizeof(struct MacroArray));
	z->size = 0;
	z->p = NULL;
	return z;
}

void macro_append(struct MacroArray* z, struct Macro* input){
	macro_insert(z, input, end(z));
}

void macro_insert(struct MacroArray* z, struct Macro* input, int index){
	if(macro_in_range(z, index)){ //array is non-empty
		z->size++;
		z->p = (struct Macro**)realloc(z->p,z->size*sizeof(char*));
		int i = z->size-1;
		for(; i>index; i--){
			z->p[i] = z->p[i-1];
		}
		z->p[i] = input;
	}else if(macro_end(z)==0 && index == 0){ //macro_insert first element
		z->p = (struct Macro**)malloc(sizeof(char*));
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
	//	printf("%s ",z->p[i]);
	}
	//printf("\n");
}


int macro_locate(struct MacroArray* z, struct Macro* cmp){
	int i = 0;
	for( i=0; i<macro_end(z); i++){
	//	if(strcmp(z->p[i],cmp)==0){
			return i;
	//	}
	}
	return -1;
}
