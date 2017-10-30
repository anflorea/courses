#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <time.h>
#include <sys/time.h>
#include <unistd.h>

int 	**generate_matrix(int n, int m);
int 	**alloc_matrix(int n, int m);
void	print_matrix(int **matrix, int n, int m);
unsigned long long getCurrentMillis();
