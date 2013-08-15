#include <stdlib.h>
#include <stdio.h>
#include "vector.h"
#include <string.h>
#ifndef MACRO_H
#define MACRO_H


struct Macro{
	char* name; //The first identifier
	struct StringArray* args; //Contains list of arguments
	struct StringArray* expr; //Expression to be sustituted in the form of words
	struct StringArray** input;
};
#endif
