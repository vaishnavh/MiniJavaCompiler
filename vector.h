#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#ifndef VECTOR_H
#define VECTOR_H
struct StringArray{
	int size;
	char** p;
};
struct StringArray* new_string_array();
void insert(struct StringArray*, char*, int);
void delete(struct StringArray*, int);
int end(struct StringArray*);
int in_range(struct StringArray*, int);
void print(struct StringArray*);
int locate(struct StringArray*, char*);
void append(struct StringArray*, char*);
void merge(struct StringArray*, int, struct StringArray*);
void cat(struct StringArray*, struct StringArray*);
#endif
