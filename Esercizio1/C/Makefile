CFLAGS = -I include -I ../Resources/C/Unity

COMMON_DEPS = include/*

all: bin/test_list bin/test_array

clean:
	$(RM) bin/*
	$(RM) build/*


bin/test_list: build/list.o build/test_list.o build/unity.o
	$(CC) build/list.o build/test_list.o build/unity.o -o bin/test_list

bin/test_array: build/array_dyn.o build/test_array.o build/unity.o
	$(CC) build/array_dyn.o build/test_array.o build/unity.o -o bin/test_array

build/unity.o:
	$(CC) $(CFLAGS) -c ../Resources/C/Unity/unity.c -o build/unity.o

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) $(CFLAGS) -c $< -o $@
