#include "unity.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "list.h"

static void test_new_list(){
  List list;
  new_list(&list);
}
  
static void test_insert_first_int(){
  List list;
  new_list(&list);
  for(int i = 0; i < 1; i++) insert_first(&list, &i);
  TEST_ASSERT_EQUAL_INT(0,*(int *) element(&list,1));

  for(int j = 1; j < 10; j++) insert_first(&list, &j);
  TEST_ASSERT_EQUAL_INT(9,*(int *) element(&list,1));
  
}

static void test_insert_in_tail_int(){
  List list;
  new_list(&list);
  for(int i = 0; i < 1; i++) insert_in_tail(&list, &i);
  TEST_ASSERT_EQUAL_INT(0,*(int *) element(&list,1));
  
  for(int j = 1; j< 10; j++) insert_in_tail(&list, &j);
  TEST_ASSERT_EQUAL_INT(9,*(int *) element(&list,10));
}

static void test_list_length_int(){
  List list;
  new_list(&list);
  for(int i = 0; i < 1; i++) insert_in_tail(&list, &i);
  TEST_ASSERT_EQUAL_INT(1,list_length(&list));

  for(int j = 1; j< 10; j++) insert_in_tail(&list, &j);
  TEST_ASSERT_EQUAL_INT(10,list_length(&list));
}  

static void test_insert_in_pos_int(){
  List list;
  new_list(&list);
  for(int i = 0; i < 1; i++) insert_in_pos(&list,&i,1);
  TEST_ASSERT_EQUAL_INT(0, *(int *) element(&list, 1));
  
  for(int j = 1; j< 10; j++) insert_in_tail(&list, &j);
  TEST_ASSERT_EQUAL_INT(9, *(int *) element(&list, 10));
}

static void test_cancel_first_int() {
  List list;
  new_list(&list);
  for(int i = 0; i < 2; i++) insert_in_tail(&list,&i);
  cancel_first(&list);
  TEST_ASSERT_EQUAL_INT(1, *(int *) element(&list, 1));

  for(int j = 2; j< 10; j++) insert_in_tail(&list, &j);
  cancel_first(&list);
  TEST_ASSERT_EQUAL_INT(2,*(int *) element(&list, 1));
}

static void test_cancel_this_position_int(){
  List list;
  new_list(&list);
  for(int i = 0; i < 2; i++) insert_in_tail(&list,&i);
  cancel_this_position(&list, 1);
  TEST_ASSERT_EQUAL_INT(1, *(int *) element(&list, 1));
  
  for(int j = 2; j < 10; j++) insert_in_tail(&list, &j);
  cancel_this_position(&list, 5);
  TEST_ASSERT_EQUAL_INT(6, *(int *) element(&list, 5));
  cancel_this_position(&list, 8);
  TEST_ASSERT_EQUAL_INT(8, *(int *) element(&list, 7));

}

static void test_cancel_in_tail_int(){
  List list;
  new_list(&list);
  for(int i = 0; i < 2; i++) insert_in_tail(&list,&i);
  cancel_in_tail(&list);
  TEST_ASSERT_EQUAL_INT(0, *(int *) element(&list, 1));
}

static void test_merge_int(){
  List list1, list2, mergedList;
  new_list(&list1);
  for(int i = 0; i <= 4; i++) insert_in_tail(&list1, &i);
  new_list(&list2);
  for(int i = 5; i <= 10; i++) insert_in_tail(&list2, &i);  
  merge(&list1, &list2, &mergedList, compare);
  TEST_ASSERT_EQUAL_INT(0, *(int *) element(&mergedList, 1));
  TEST_ASSERT_EQUAL_INT(1, *(int *) element(&mergedList, 2));
  TEST_ASSERT_EQUAL_INT(2, *(int *) element(&mergedList, 3));
  TEST_ASSERT_EQUAL_INT(3, *(int *) element(&mergedList, 4));
  TEST_ASSERT_EQUAL_INT(4, *(int *) element(&mergedList, 5));
  TEST_ASSERT_EQUAL_INT(5, *(int *) element(&mergedList, 6));
  TEST_ASSERT_EQUAL_INT(8, *(int *) element(&mergedList, 9));
  TEST_ASSERT_EQUAL_INT(9, *(int *) element(&mergedList, 10));
}

static void test_insert_first_strings(){
  List list;
  new_list(&list);
  char *name[] = {"CIAO" };
  for(int i =0; i<1;i++)insert_first(&list, &name[i]);
  TEST_ASSERT_EQUAL_STRING("CIAO", *(char **)element(&list,1));

  char *name2[] = {"HELLO", "HOLA", "ADIOS" };
  for(int j=0; j<3;j++)insert_first(&list, &name2[j]);
  TEST_ASSERT_EQUAL_STRING("ADIOS", *(char **)element(&list,1));
}

static void test_insert_in_tail_strings(){
  List list;
  new_list(&list);
  char *name[] = {"CIAO" };
  for(int i =0; i<1;i++)insert_in_tail(&list, &name[i]);
  TEST_ASSERT_EQUAL_STRING("CIAO", *(char **)element(&list,1));

  char *name2[] = {"HELLO", "HOLA", "ADIOS" };
  for(int j=0; j<3;j++)insert_in_tail(&list, &name2[j]);
  TEST_ASSERT_EQUAL_STRING("ADIOS", *(char **)element(&list,4));
}

static void test_list_length_strings(){
  List list;
  new_list(&list);
  char *name[] = {"CIAO" };
  for(int i =0; i<1;i++)insert_in_tail(&list, &name[i]);
  TEST_ASSERT_EQUAL_INT(1,list_length(&list));

  char *name2[] = {"HELLO", "HOLA", "ADIOS" };
  for(int j=0; j<3;j++)insert_in_tail(&list, &name2[j]);
  TEST_ASSERT_EQUAL_INT(4,list_length(&list));
}

static void test_insert_in_pos_strings(){
  List list;
  new_list(&list);
  char *name[] = {"CIAO", "Hello", "Bye"};
  insert_in_pos(&list,&name ,1);
  TEST_ASSERT_EQUAL_STRING("CIAO", *(char **)element(&list, 1));
  
  int j;
  for(j = 1; j< 3; j++) insert_in_pos(&list, &name[j], j);
  TEST_ASSERT_EQUAL_STRING("Bye", *(char **)element(&list, j-1));
}

static void test_cancel_first_strings(){
  List list;
  new_list(&list);
  char *name[] = {"ciao", "come", "stai"};
  for(int i=0; i<3;i++)insert_in_tail(&list, &name[i]);
  cancel_first(&list);
  TEST_ASSERT_EQUAL_STRING("come", *(char **)element(&list, 1));
}

static void test_cancel_this_position_strings(){
  List list;
  new_list(&list);
  char *name[] = {"ciao", "come", "stai"};
  for(int i=0; i<3;i++)insert_in_tail(&list, &name[i]);
  cancel_this_position(&list,2);
  TEST_ASSERT_EQUAL_STRING("stai", *(char **)element(&list, 2));
}

static void test_cancel_in_tail_strings(){
  List list;
  new_list(&list);
  char *name[] = {"ciao", "come"};
  for(int i=0; i<2;i++)insert_in_tail(&list, &name[i]);
  TEST_ASSERT_EQUAL_INT(2,list_length(&list));
  cancel_in_tail(&list);
  TEST_ASSERT_EQUAL_INT(1,list_length(&list));
  TEST_ASSERT_EQUAL_STRING("ciao", *(char **)element(&list, 1));
}

int main(){
  UNITY_BEGIN();
  RUN_TEST(test_new_list);
  //integers
  RUN_TEST(test_insert_first_int);
  RUN_TEST(test_insert_in_tail_int);
  RUN_TEST(test_list_length_int);
  RUN_TEST(test_insert_in_pos_int);
  RUN_TEST(test_cancel_first_int);
  RUN_TEST(test_cancel_this_position_int);
  RUN_TEST(test_cancel_in_tail_int);
  RUN_TEST(test_merge_int);
  //strings
  RUN_TEST(test_insert_first_strings);
  RUN_TEST(test_insert_in_tail_strings);
  RUN_TEST(test_list_length_strings);
  RUN_TEST(test_insert_in_pos_strings);
  RUN_TEST(test_cancel_first_strings);
  RUN_TEST(test_cancel_this_position_strings);
  RUN_TEST(test_cancel_in_tail_strings);
  return UNITY_END();
}