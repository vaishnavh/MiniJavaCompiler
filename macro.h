#include <stdlib.h>
#include <stdio.h>
#include "vector.h"
#include <string.h>
#ifndef MACRO_H
#define MACRO_H


struct Macro{
	char* name; //The first identifier
	struct StringArray* args; //Contains list of arguments
	struct StringArray* expr; //Expression to be sustituted
};
struct Macro* new_macro();
void append_arg(struct Macro*, char*);
void append_token(struct Macro*, char *);
void compare(struct Macro*, char*);
void append_input(struct Macro*, struct StringArray*);

struct StringArray* substitute(struct Macro* , struct StringArray** )
#endif
