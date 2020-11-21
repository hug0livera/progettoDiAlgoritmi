#ifndef __LIST_H
#define __LIST_H

typedef enum {FALSE,TRUE} bool;

/**
* @author nadim, Victor
*initialize funtions prototypes implemented in list.c
*/

//struct for the node 
typedef struct _Node{
  void *data; //any data type is accepted
  struct _Node *next;
  struct _Node *prev;
}Node;

//struct for the list
typedef struct{
  int listLength;
  Node *head;
  Node *tail;
}List;

//iterator struct
typedef struct{
  Node *current;
}Iterator;

/* FUNCTIONS PROTOTYPES */

//create new list
void new_list(List *list);

//insert first
void insert_first(List *list, void *element);

//insert in the tail of the list in O(1)
void insert_in_tail(List *list, void *element);

//insert in ith position in O(n)
void insert_in_pos(List *list, void *element, int position);

//cancel first
void cancel_first(List *list);

//cancel the ith position in O(n)
void cancel_this_position(List *list, int position);

//cancel element in coda of the list in O(1)
void cancel_in_tail(List *list);

//returns the length of the list in O(1)
int list_length(List *list);

//returns the element in a specific position O(n)
void* element(List *list, int position);

//check if the list is empty in O(1)
bool check_if_empty(List *list);

//free the list
void free_list(List *list);

/*
iterator functions
*/
void new_iterator(List *list, Iterator *iterator);
int has_next(Iterator *iterator);
void* next(Iterator *iterator);
void free_iterator(Iterator *iterator);

/*
merge 2 lists that depends on the comparator
comparator returns <= 0 when first parameter comes before second parameter
else returns 1
*/
List* merge(List *list1, List *list2, List *list, int (*comparator) (void*, void*));
int compare(void* a, void* b);

#endif
