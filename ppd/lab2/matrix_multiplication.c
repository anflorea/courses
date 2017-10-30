/*

Time for a matrix of 2000/2000 X 2000/2000 with 1 threads is: 78057
Time for a matrix of 2000/2000 X 2000/2000 with 2 threads is: 38415
Time for a matrix of 2000/2000 X 2000/2000 with 20 threads is: 19583
Time for a matrix of 2000/2000 X 2000/2000 with 500 threads is: 18802
Time for a matrix of 2000/2000 X 2000/2000 with 2000 threads is: 18669

*/


#include "includes.h"

int **g_A;
int **g_B;
int **g_C;
int N, M, T, Q;

// #define LOGGING_MODE

void *matrix_mult(void *arg) {
	int id = *(int*)arg;
	int i, j, x;

	for (i = id; i < N; i += T)
		for (j = 0; j < Q; j++) {
			
			int sum = 0;
			for (x = 0; x < M; x++)
				sum += g_A[i][x] * g_B[x][j];

			g_C[i][j] = sum;
		}

	pthread_exit(NULL);
}

void do_multiplication()
{
	pthread_t thread[T];
	int tid[T];
	int i, j;
	FILE *file;

	unsigned long long oldTime;
	unsigned long long newTime;

	g_A = generate_matrix(N, M);
	sleep(1);
	g_B = generate_matrix(M, Q);
	g_C = alloc_matrix(N, Q);

	oldTime = getCurrentMillis();

	for (i = 0; i < T; i++) {
		tid[i] = i;
		pthread_create(&thread[i], NULL, matrix_mult, &tid[i]);
	}

	for (i = 0; i < T; i++)
		pthread_join(thread[i], NULL);

	newTime = getCurrentMillis();

	printf("Time for a matrix of %d/%d X %d/%d with %d threads is: %llu\n", N, M, M, Q, T, newTime - oldTime);

#ifdef LOGGING_MODE
	printf("Matrix A:\n\n");
	print_matrix(g_A, N, M);
	printf("\n\nMatrix B:\n\n");
	print_matrix(g_B, M, Q);
	printf("\n\nMatrix C:\n\n");
	print_matrix(g_C, N, Q);
#endif

}

int main(void)
{
	srand(time(NULL));

	N = 2000;
	M = 2000;
	Q = 2000;
	T = 1;
	do_multiplication();

	N = 2000;
	M = 2000;
	Q = 2000;
	T = 2;
	do_multiplication();

	N = 2000;
	M = 2000;
	Q = 2000;
	T = 20;
	do_multiplication();

	N = 2000;
	M = 2000;
	Q = 2000;
	T = 500;
	do_multiplication();

	N = 2000;
	M = 2000;
	Q = 2000;
	T = 2000;
	do_multiplication();

	return 0;
}
