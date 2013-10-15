#include "vector.h"
struct StringArray* new_string_array(){
	struct StringArray* z;
	z = (struct StringArray*)malloc(sizeof(struct StringArray));
	z->size = 0;
	z->p = NULL;
	return z;
}


void insert(struct StringArray* z, char* input, int index){
	if(in_range(z, index)){ //array is non-empty
		z->size++;
		z->p = (char**)realloc(z->p,z->size*sizeof(char*));
		int i = z->size-1;
		for(; i>index; i--){
			z->p[i] = z->p[i-1];
		}
		z->p[i] = input;
	}else if(end(z)==0 && index == 0){ //insert first element
		z->p = (char**)malloc(sizeof(char*));
		z->p[0] = input;		
		z->size = 1;	
	}

}

void delete(struct StringArray* z, int index){
	if(in_range(z, index)){
		int i;
		for(i= index; i<z->size-1; i++){
			z->p[i] = z->p[i+1];
		}
		z->size--;
	}
}

int end(struct StringArray* z){
	return z->size; //returns the first empty position
}

int in_range(struct StringArray* z, int p){
	if(end(z)==0) return 0;
	if(p>=0 && p<=end(z)) return 1;
	return 0;
}


void print(struct StringArray* z){
	int i;	
	for(i=0; i<z->size; i++){
		printf("<%s> ",z->p[i]);
	}
	printf("\n");
}


int locate(struct StringArray* z, char* cmp){
	int i = 0;
	for( i=0; i<end(z); i++){
		if(strcmp(z->p[i],cmp)==0){
			return i;
		}
	}
	return -1;
}


void append(struct StringArray* z, char* input){
	insert(z,input,end(z));
}


void merge(struct StringArray* z, int index, struct StringArray* y){
//inserts y string array at the given position of z
	if(in_range(z, index)){ //array is non-empty
		z->size+= y->size;
		z->p = (char**)realloc(z->p,z->size*sizeof(char*));
		int i = z->size-1;
		for(; i>=index+y->size; i--){
			z->p[i] = z->p[i-y->size];
		}
		for(; i>=index; i--){
			z->p[i] = y->p[i-index];
		}
	}else if(end(z)==0 && index == 0){ //insert first element
	/*	z->size+=y->size;
		z->p =  (char**)malloc(z->size*sizeof(char*));
		int i;
		for(i=0; i<z->size; i++){
			z->p[i] = y->p[i];
		}*/
		z = y;
	}

}



void cat(struct StringArray* z, struct StringArray* y){
	merge(z, z->size, y);
}
