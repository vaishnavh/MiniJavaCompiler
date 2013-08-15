/* 
 * File:   array.cpp
 * Author: vaish
 * 
 * Created on 31 January, 2013, 2:51 PM
 */
#include "array.h"
array::array() {
    size = 0;
    p = NULL;
}

void array::INSERT(int input, position index) {
    if (INRANGE(index)) {
        size++;
        p = (int*) realloc(p, size*sizeof(int)); //The array size is dynamically altered
        //to accommmodate the newcomer
        int i;
        for (i = size-1; i >= index; i--) {
            p[i]=p[i-1]; //Elements are right shifted till the required position
        }
        p[i]=input; //new element is stored
    }
}
void array::DELETE(position index){
    if(INRANGE(index)){
        for(int i=index-1;i<size-1;i++){
            p[i]=p[i+1]; //Elements are right shifted 
        }        
        size--;
    }
}
int array::RETRIEVE(position index) {
    if (size != 0) {
        if (index < 1 || index > size)
            return 0; //In order to avoid an undefined return value
        return p[index - 1];
    }
    return 0;
}

int array::FIRST() {
    return 1;
}

int array::END() {
    return size + 1;
}

void array::MAKENULL() {
    free(p);
    size = 0;
}

int array::PREVIOUS(int index) {
    return index - 1;
}

int array::NEXT(int index) {
    return index + 1;
}

void array::PRINTLIST() {
    for (int i = 0; i < size; i++) {
        printf("%d ", p[i]);
    }
}
//Checks whether the given input is within range of indices of array
bool array::INRANGE(int index) {
    if(END()==FIRST() && index == FIRST()){
        return true;
    }
    if (index < 1 || index > END()) {
        return false;
    }
    return true;
}
int array::LOCATE(int input){
    for(int i=0;i<size;i++){
        if(p[i]==input){
            return i+1;
        }
    }
    return END();
}
array::array(const array& orig) {

}

array::~array() {
    size=0;    
    p = NULL;
}

