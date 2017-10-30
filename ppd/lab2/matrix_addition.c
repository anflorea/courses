#include "includes.h"

int **g_A;
int **g_B;
int **g_C;
int N, M, T;

// #define LOGGING_MODE

void *matrix_add(void *arg) {
	int id = *(int*)arg;
	int i, j;

	for (i = id; i < N; i += T)
		for (j = 0; j < M; j++)
			g_C[i][j] = g_A[i][j] + g_B[i][j];

	pthread_exit(NULL);
}

void do_addition()
{
	pthread_t thread[T];
	int tid[T];
	int i, j;
	FILE *file;

	unsigned long long oldTime;
	unsigned long long newTime;

	// Create matrixes with random values
	g_A = generate_matrix(N, M);
	sleep(1);
	g_B = generate_matrix(N, M);
	g_C = alloc_matrix(N, M);

	oldTime = getCurrentMillis();

	for (i = 0; i < T; i++) {
		tid[i] = i;
		pthread_create(&thread[i], NULL, matrix_add, &tid[i]);
	}

	for (i = 0; i < T; i++)
		pthread_join(thread[i], NULL);

	newTime = getCurrentMillis();

	printf("Time for a matrix of %d X %d with %d threads is: %llu\n", N, M, T, newTime - oldTime);

#ifdef LOGGING_MODE
	printf("Matrix A:\n\n");
	print_matrix(g_A, N, M);
	printf("\n\nMatrix B:\n\n");
	print_matrix(g_B, N, M);
	printf("\n\nMatrix C:\n\n");
	print_matrix(g_C, N, M);
#endif
}

int main()
{
	srand(time(NULL));

	N = 10000;
	M = 10000;
	T = 1;
	do_addition();

	N = 10000;
	M = 10000;
	T = 2;
	do_addition();

	N = 10000;
	M = 10000;
	T = 3;
	do_addition();

	N = 10000;
	M = 10000;
	T = 20;
	do_addition();

	N = 10000;
	M = 10000;
	T = 200;
	do_addition();

	N = 10000;
	M = 10000;
	T = 5000;
	do_addition();

	N = 10000;
	M = 10000;
	T = 10000;
	do_addition();

	return 0;
}
