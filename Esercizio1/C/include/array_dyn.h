#ifndef __ARRAYDYN_H
#define __ARRAYDYN_H

typedef enum {FALSE, TRUE} bool;

typedef struct {
  int totalElements;
  int occupiedSize;
  void *data;
}Array;


typedef struct{
  Array *array;
  int base;
}Iterator;

/**
*@author nadim , Victor
*@initialize funtions prototypes implemented in array_dyn.c
*/

//create a new array
Array* create_array(int totalElements);

//push an element onto the end of the array
void* insert_last_element(Array *array, void *data);

// insert an element in n-positon
void insert_in_position(Array *array, void *data, int position);

//double the size when array is full
void double_array_size(Array *array);


//returns the data in a specific positon
void* get_data(Array *array, int position);

//cancel an element in a specific position
void cancel_element_in_position(Array *array, int position); 

//cancel the last element
void canceled_last_element(Array *array);

//check if array is empty
bool check_if_empty(Array *array);

//check if array is full
bool check_if_full(Array *array);

//length of occupied array 
int array_length_occupied(Array *array);

//length of the array
int array_length(Array *array);

//free array
void free_array(Array *array);

Array *merge(Array *array1, Array *array2, Array *array, int (*comparator)(void *, void *));
int compare(void* a, void* b);

/*iterator functions */

//create new iterator
void new_iterator(Array *array, Iterator *iterator);

int has_next(Iterator *iterator);

void *next(Iterator *iterator);

void free_iterator(Iterator *it);

#endif