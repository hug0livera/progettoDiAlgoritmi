#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include "array_dyn.h"

/** @author Victor*/
/** @author Nadim*/

Array* create_array(int totalElements){
  Array *array = malloc(sizeof(Array));
  assert(array);
  array -> totalElements = totalElements;
  array -> occupiedSize = 0;
  array -> data = malloc(totalElements * sizeof(array -> data));
  return array; 
}

void* insert_last_element(Array *array, void *data){
  assert(array);
  assert(data);

  //check if array is full
  if(check_if_full(array)){
    double_array_size(array);
  }
  array -> occupiedSize++;
  //calculate starting location of the new element
  void *target = array -> data + (array_length_occupied(array) * sizeof(data));
  memcpy(target, data, sizeof(data));
  return data;
}

void insert_in_position(Array *array, void *data, int position){
  assert(array && position > 0 && position <= array_length_occupied(array) + 1);
  assert(data);
  if (check_if_full(array)) {
    double_array_size(array);
  }
  array -> occupiedSize++;
  int len = array -> occupiedSize;
  while(len > position){
    memcpy(array -> data + (len * sizeof(data)), array -> data + ((len - 1) * sizeof(data)), sizeof(data));
    len--;
  }
  memcpy(array -> data + (len * sizeof(data)), data, sizeof(data));
}



void double_array_size(Array *array){
  assert(array);
  array -> data = realloc(array -> data, array -> totalElements * 2 * sizeof(array -> data));
  assert(array -> data);
  array -> totalElements *= 2;
}

void cancel_element_in_position(Array *array, int position){
  assert(array && position > 0);
  assert(array_length_occupied(array) >= 1);
  if(array_length_occupied(array) == (array_length(array) / 2) + 1){
    array -> totalElements /= 2;
  }
  if(position == array_length_occupied(array)){
    canceled_last_element(array);
  }
  else{
    while(position < array_length_occupied(array)){
      memcpy((array -> data + (position * sizeof(array -> data))) , (array -> data + ((position + 1) * sizeof(array -> data))), sizeof(array -> data));
      position++;
    }
    array -> occupiedSize--;
  }  
}

void canceled_last_element(Array *array){
  assert(array);
  assert(array_length_occupied(array) >= 1);
  if(array_length_occupied(array) == (array_length(array) / 2) + 1){
    array -> totalElements /= 2;
  }
  // void *deleted = array -> data + ((array_length_occupied(array) -1) * array -> elementSize);
  // printf("deleted == %d\n", *(int*)deleted);
  array -> occupiedSize--;
  // return deleted;
}

void* get_data(Array *array, int position){
  assert(array);
  assert(position >= 0 && position < array_length_occupied(array));
  int i = 0;
  while(i < position){
    i++;
  }
  return array -> data + (i * sizeof(array -> data));
}


bool check_if_empty(Array *array){
  return array -> occupiedSize == 0;
}

bool check_if_full(Array *array){
  return array -> occupiedSize == array -> totalElements;
}

int array_length_occupied(Array *array){
  return array -> occupiedSize;
}

int array_length(Array *array){
  return array -> totalElements;
}




void new_iterator(Array *array, Iterator *iterator){
  iterator -> array = array;
  iterator -> base = 0;
}

int has_next(Iterator *iterator){ 
  if(iterator -> base >= array_length_occupied(iterator -> array)){
    return 0;
  }
  return 1;
}


void *next(Iterator *iterator){
  assert(has_next(iterator));
  Array *array;
  array = (Array*)malloc(sizeof(Array));
  array = iterator -> array;
  iterator -> base++;
  return array -> data + (iterator -> base * sizeof(array -> data));
}


void free_iterator(Iterator *iterator){
  free(iterator);
}

void free_array(Array *array){
  free(array);
}

Array *merge(Array *array1, Array *array2, Array *array, int (*comparator)(void *, void *)) {
  array = create_array(array_length_occupied(array1) + array_length_occupied(array2));
  Iterator *it1, *it2;
  it1 = (Iterator *)malloc(sizeof(Iterator));
  it2 = (Iterator *)malloc(sizeof(Iterator));
  new_iterator(array1, it1);
  new_iterator(array2, it2);
  if (check_if_empty(array1)) return array2;
  else if (check_if_empty(array2)) return array1;
  else {
    void *temp1 = next(it1);
    void *temp2 = next(it2);
    while (has_next(it1) && has_next(it2)) {
      if ((*comparator)(temp1, temp2) <= 0) {
        insert_last_element(array, temp1);
        temp1 = next(it1);
      }
      else {
        insert_last_element(array, temp2);
        temp2 = next(it2);
      }
    }
    insert_last_element(array, temp1);
    if (has_next(it1)) {
      while (has_next(it1)) {
        insert_last_element(array, temp1);
        temp1 = next(it1);
      }
      insert_last_element(array, temp1);
    }
    else if (has_next(it2)) {
      while (has_next(it2)) {
        insert_last_element(array, temp2);
        temp2 = next(it2);
      }
      insert_last_element(array, temp2);
    }
  return array;
  }
}

int compare(void* a, void* b){
  int elem1 = *((int *) a);
  int elem2 = *((int *) b);
  if(elem1 >= elem2)
    return -1;
  else
    return 1;
}