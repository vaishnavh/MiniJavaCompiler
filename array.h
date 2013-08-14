/* 
 * File:   array.h
 * Author: vaish
 *
 * Created on 31 January, 2013, 2:51 PM
 */
#include <stdlib.h>
#include <stdio.h>
#ifndef ARRAY_H
#define	ARRAY_H

typedef int position;
class array {
public:
    
    array();
    array(const array& orig);
    virtual ~array();
    void DELETE(position);
    void INSERT(int, position);
    int RETRIEVE(position);
    position FIRST();
    position END();
    void MAKENULL();
    position PREVIOUS(position);
    position NEXT(position);
    void PRINTLIST();
    bool INRANGE(position);
    position LOCATE(int);
private:
    int size; //Number of elements in array
    position *p; //Pointer to FIRST element
};
typedef array list;
#endif	/* ARRAY_H */

