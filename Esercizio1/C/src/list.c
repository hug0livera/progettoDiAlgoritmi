#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include "list.h"

/** @author Nadim*/
/** @author Victor*/

void new_list(List *list) {
  list->listLength = 0;
  list->head = list->tail = NULL;
}

void insert_first(List *list, void *element) {
  Node *nodo = malloc(sizeof(Node));
  nodo->data = malloc((int)sizeof(element));
  memcpy(nodo->data, element, (int)sizeof(element));

  nodo->next = nodo->prev = list->head;
  list->head = nodo;
  if (!list->tail) 
    list->tail = list->head;
  list->listLength++;
}

void insert_in_tail(List *list, void *element) {
  assert(list != NULL);
  Node *nodo = malloc(sizeof(Node));
  nodo->data = malloc((int)sizeof(element));
  nodo->next = NULL;

  memcpy(nodo->data, element, (int)sizeof(element));

  if (list->listLength == 0) 
    insert_first(list, element);
  else {
    list->tail->next = nodo;
    nodo->prev = list->tail;
    list->tail = nodo;
  }
  list->listLength++;
}

void insert_in_pos(List *list, void *element, int position) {
  if (position == 1) 
    insert_first(list, element);
  else if (position == list->listLength) 
    insert_in_tail(list, element);
  else {
    int i = 1;
    Node *new = malloc(sizeof(Node));
    new->data = malloc((int)sizeof(element));
    memcpy(new->data, element, (int)sizeof(element));

    Node *current = list->head;
    while (i < position - 1) {
      i++;
      current = current->next;
    }
    Node *previous = current->next;
    new->next = previous;
    new->prev = current;
    previous->prev = new;
    current->next = new;
    list->listLength++;
  }
}

void cancel_first(List *list) {
  assert(list != NULL && list_length(list) > 0);
  Node *first = list->head;
  first->prev = NULL;
  list->head = first->next;
  free(first->data);
  free(first);
  list->listLength--;
}

void cancel_this_position(List *list, int position) {
  assert(list != NULL);
  assert(position > 0 && position <= list_length(list));
  if (position == 1) 
    cancel_first(list);
  else {
    Node *current = list->head;
    for (int i = 1; i < position - 1; i++) {
      current = current->next;
    }
    if (position == list_length(list)) 
      cancel_in_tail(list);
    else {
      Node *next = current->next->next;
      free(current->next);
      current->next = next;
      current->next->prev = current;
      list->listLength--;
    }
  }
}

void cancel_in_tail(List *list) {
  assert(list != NULL && list_length(list) > 0);
  Node *prev = list->tail->prev;
  Node *tail = list->tail;
  if (list_length(list) == 1) 
    cancel_first(list);
  else {
    prev->next = NULL;
    list->tail = prev;
    free(tail->data);
    free(tail);
    list->listLength--;
  }
}

void *element(List *list, int position) {
  if (position == 1) 
    return list->head->data;
  else if (position == list_length(list)) 
    return list->tail->data;
  else  {
    int i = 1;
    Node *current = list->head;
    while (i != position) {
      i++;
      current = current->next;
    }
    return current->data;
  }
}

int list_length(List *list) {
  if (check_if_empty(list)) 
    return 0;
  return list->listLength - 1;
}

bool check_if_empty(List *list) {
  if (list->listLength == 0) 
    return TRUE;
  return FALSE;
}

void new_iterator(List *list, Iterator *it) {
  it->current = list->head;
  //printf(" %d\n", *(int *)it -> current -> data);
}

int has_next(Iterator *it) {
  if (it->current == NULL) 
    return 0;
  return 1;
}

void *next(Iterator *it) {
  assert(has_next(it));
  //printf(" %d\n", *(int *)it -> current -> data);
  Node *next = it->current;
  it->current = it->current->next;
  return next->data;
}

void free_iterator(Iterator *it) {
  free(it);
}

List *merge(List *list1, List *list2, List *list, int (*comparator)(void *, void *)) {
  new_list(list);
  Iterator *it1, *it2;
  it1 = (Iterator *)malloc(sizeof(Iterator));
  it2 = (Iterator *)malloc(sizeof(Iterator));
  new_iterator(list1, it1);
  new_iterator(list2, it2);
  if (check_if_empty(list1)) return list2;
  else if (check_if_empty(list2)) return list1;
  else {
    void *temp1 = next(it1);
    void *temp2 = next(it2);
    while (has_next(it1) && has_next(it2)) {
      if ((*comparator)(temp1, temp2) <= 0) {
        insert_in_tail(list, temp1);
        temp1 = next(it1);
      }
      else {
        insert_in_tail(list, temp2);
        temp2 = next(it2);
      }
    }
    insert_in_tail(list, temp1);
    if (has_next(it1)) {
      while (has_next(it1)) {
        insert_in_tail(list, temp1);
        temp1 = next(it1);
      }
      insert_in_tail(list, temp1);
    }
    else if (has_next(it2)) {
      while (has_next(it2)) {
        insert_in_tail(list, temp2);
        temp2 = next(it2);
      }
      insert_in_tail(list, temp2);
    }
    return list;
  }
}

int compare(void* a, void* b){
  int elem1 = *((int *) a);
  int elem2 = *((int *) b);
  if(elem1 <= elem2)
    return -1;
  else
    return 1;
}

void free_list(List *list){
  Node *current;
  while(list -> head != NULL) {
    current = list -> head;
    list -> head = current -> next;
    free(current -> data);
    free(current);
  }
}