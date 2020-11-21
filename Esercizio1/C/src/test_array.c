#include "unity.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "array_dyn.h"


/*Integers test*/
static void test_new_array(){
  Array *array;
  array = create_array(10);
}

static void test_insert_last_int(){
  Array *array;
  array = create_array(10);
  int i;
  for(i = 0; i < 1; i++) insert_last_element(array, &i);
  TEST_ASSERT_EQUAL_INT(0,*(int *)(array -> data + (i * sizeof(array -> data))));
  int j;
  for(j = 1; j< 10; j++) insert_last_element(array, &j);
  TEST_ASSERT_EQUAL_INT(9,*(int *)(array -> data + (j * sizeof(array -> data))));
}

static void test_array_occupied_length_int(){
  Array *array;
  array = create_array(10);
  for(int i = 0; i < 1; i++) insert_last_element(array, &i);
  TEST_ASSERT_EQUAL_INT(1,array_length_occupied(array));

  for(int j = 1; j< 10; j++) insert_last_element(array, &j);
  TEST_ASSERT_EQUAL_INT(10,array_length_occupied(array));
}

static void test_insert_in_pos_int(){
  Array *array;
  array = create_array(10);
  int i;
  for(i = 0; i < 1; i++) insert_in_position(array,&i,1);
  TEST_ASSERT_EQUAL_INT(0, *(int *)(array -> data + (i * sizeof(array -> data))));
  int j;
  for(j = 1; j< 10; j++) insert_in_position(array, &j,j);
  TEST_ASSERT_EQUAL_INT(9, *(int *)(array -> data + ((j-1) * sizeof(array -> data))));
}

static void test_cancel_this_position_int(){
  Array *array;
  array = create_array(10);
  int i;
  for(i = 0; i < 2; i++) insert_last_element(array,&i);
  cancel_element_in_position(array, 1);
  TEST_ASSERT_EQUAL_INT(1, *(int *)(array -> data + (1 * sizeof(array -> data))));
  int j;
  for(j = 2; j < 10; j++) insert_last_element(array, &j);
  cancel_element_in_position(array, 5);
  TEST_ASSERT_EQUAL_INT(6, *(int *)(array -> data + (5 * sizeof(array -> data))));
  cancel_element_in_position(array, 8);
  TEST_ASSERT_EQUAL_INT(9, *(int *)(array -> data + (8 * sizeof(array -> data))));
}

static void test_cancel_in_tail_int(){
  Array *array;
  array = create_array(10);
  int i;
  for(i = 0; i < 2; i++) insert_last_element(array,&i);
  canceled_last_element(array);
  TEST_ASSERT_EQUAL_INT(0, *(int *)(array -> data + (array_length_occupied(array) * sizeof(array -> data))));
}

 /*Strings test*/
static void test_insert_in_tail_strings(){
  Array *array;
  array = create_array(10);
  char *name[] = {"CIAO" };
  int i;
  for(i =0; i<1;i++)insert_last_element(array, &name[i]);
  TEST_ASSERT_EQUAL_STRING("CIAO", *(char **)(array -> data + (i * sizeof(array -> data))));
  char *name2[] = {"A", "B", "C" };
  int j;
  for(j=1; j<3;j++)insert_last_element(array, &name2[j]);
  TEST_ASSERT_EQUAL_STRING("C", *(char **)(array -> data + (j * sizeof(array -> data))));
}

static void test_array_occupied_length_strings(){
  Array *array;
  array = create_array(10);
  char *name[] = {"CIAO" };
  for(int i =0; i<1;i++)insert_last_element(array, &name[i]);
  TEST_ASSERT_EQUAL_INT(1,array_length_occupied(array));

  char *name2[] = {"A", "B", "C" };
  for(int j=0; j<3;j++)insert_last_element(array, &name2[j]);
  TEST_ASSERT_EQUAL_INT(4,array_length_occupied(array));
}

static void test_insert_in_pos_strings(){
  Array *array;
  array = create_array(10);
  char *name[] = {"A", "B", "C"};
  int i;
  for(i = 0; i < 1; i++) insert_in_position(array,&name[i],1);
  TEST_ASSERT_EQUAL_STRING("A", *(char **)(array -> data + (1 * sizeof(array -> data))));
  int j;
  for(j = 1; j< 3; j++) insert_in_position(array, &name[j], j);
  TEST_ASSERT_EQUAL_STRING("C", *(char **)(array -> data + ((j -1) * sizeof(array -> data))));
}

static void test_cancel_this_position_strings(){
  Array *array;
  array = create_array(10);
  char *name[] = {"ciao", "come", "stai"};
  for(int i=0; i<3;i++)insert_last_element(array, &name[i]);
  cancel_element_in_position(array,2);
  TEST_ASSERT_EQUAL_STRING("stai", *(char **)(array -> data + (2 * sizeof(array -> data))));
}

static void test_cancel_in_tail_strings(){
  Array *array;
  array = create_array(10);
  char *name[] = {"ciao", "come"};
  for(int i=0; i<2;i++)insert_last_element(array, &name[i]);
  TEST_ASSERT_EQUAL_INT(2,array_length_occupied(array));
  canceled_last_element(array);
  TEST_ASSERT_EQUAL_INT(1,array_length_occupied(array));
  TEST_ASSERT_EQUAL_STRING("ciao", *(char **)(array -> data + (array_length_occupied(array) * sizeof(array -> data))));
}

int main(){
  UNITY_BEGIN();
  RUN_TEST(test_new_array);
  //integers
  RUN_TEST(test_insert_last_int);
  RUN_TEST(test_array_occupied_length_int);
  RUN_TEST(test_insert_in_pos_int);
  RUN_TEST(test_cancel_this_position_int);
  RUN_TEST(test_cancel_in_tail_int);
  // RUN_TEST(test_merge_int);
  
  //strings
  RUN_TEST(test_insert_in_tail_strings);
  RUN_TEST(test_array_occupied_length_strings);
  RUN_TEST(test_insert_in_pos_strings);
  RUN_TEST(test_cancel_this_position_strings);
  RUN_TEST(test_cancel_in_tail_strings);
  return UNITY_END();
}