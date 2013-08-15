#include<stdio.h>
#include<stdlib.h>
#include "macro.h"
#ifndef MACROVECTOR_H
#define MACROVECTOR_H
struct MacroArray{
	int size;
	struct Macro** p;
};
struct MacroArray* new_macro_array();
void macro_insert(struct MacroArray*, struct Macro*, int);
void macro_delete(struct MacroArray*, int);
int macro_end(struct MacroArray*);
int macro_in_range(struct MacroArray*, int);
void macro_print(struct MacroArray*);
int macro_locate(struct MacroArray*, struct Macro*);
void macro_append(struct MacroArray*, struct Macro*);
#endif
