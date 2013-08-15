#include "macro.h"
void append_arg(struct Macro* z,char *arg){
	append(z->args, arg);
}

void append_token(struct Macro* z, char *tok){
	append(z->expr, tok);
}


void compare(struct Macro* z, char* name){
	return strcmp(z->name, name);
}




