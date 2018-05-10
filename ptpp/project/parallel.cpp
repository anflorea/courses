#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>

int			T = 2;
int			*A, *B, *C;
pthread_mutex_t lock;
const char 	*no_threads_var;

int		*construct_huge(char *str) {
	int		*huge, i, j;

	huge = (int*)malloc(sizeof(int) * (strlen(str) + 1));
	huge[0] = strlen(str);
	for (i = strlen(str) - 1, j = 1; i >= 0; i--, j++) {
		huge[j] = str[i] - '0';
	}
	return huge;
}

void	*do_multiplication(void *arg) {
	int id = *(int*)arg;
	int	i, j, k;

	for (i = 1; i <= A[0]; i++) {
		for (j = id + 1; j <= B[0]; j += T) {
			pthread_mutex_lock(&lock);
			k = i + j - 1;
			C[k] += A[i] * B[j];
			pthread_mutex_unlock(&lock);
		}
	}
	pthread_exit(NULL);
}

void	multiply_huge() {
	int			i, j;
	pthread_t 	thread[T];
	int			tid[T];

	C = (int*)malloc(sizeof(int) * (A[0] + B[0] + 1));
	C[0] = A[0] + B[0] - 1;
	for (i = 1; i <= A[0] + B[0] + 1; i++) {
		C[i] = 0;
	}

	for (i = 0; i < T; i++) {
		tid[i] = i;
		pthread_create(&thread[i], NULL, do_multiplication, &tid[i]);
	}

	for (i = 0; i < T; i++) {
		pthread_join(thread[i], NULL);
	}

	int T = 0;
	for (i = 1; i <= C[0]; i++) {
		C[i] += T;
		T = C[i] / 10;
		C[i] %= 10;
	}

	if (T) {
		C[0] += 1;
		C[C[0]] = T;
	}
}

void	write_huge(FILE *out, int *huge) {
	int		i;

	for (i = huge[0]; i > 0; i--) {
		fprintf(out, "%d", huge[i]);
	}
}

int		main(int argc, char **argv) {
	FILE	*inFile, *outFile;
	char	*str1, *str2;

	if (pthread_mutex_init(&lock, NULL) != 0) {
        printf("\n mutex init failed\n");
        return 1;
    }

	if (argc != 3) {
		printf("usage: %s <input file> <output file>\nnote: To control the number of threads, please set the NO_THREADS env variable.\n", argv[0]);
		exit(0);
	}
	inFile = fopen(argv[1], "r");
	if (inFile == NULL) {
		perror(argv[1]);
		exit(0);
	}
	outFile = fopen(argv[2], "w");
	if (outFile == NULL) {
		perror(argv[2]);
		exit(0);
	}

	no_threads_var = getenv("NO_THREADS");
	if (no_threads_var != NULL) {
		int thrds = atoi(no_threads_var);
		if (thrds > 0)
			T = thrds;
	}

	str1 = (char*)malloc(sizeof(char) * 400000);
	str2 = (char*)malloc(sizeof(char) * 400000);
	fscanf(inFile, "%s%s", str1, str2);
	A = construct_huge(str1);
	B = construct_huge(str2);
	multiply_huge();
	write_huge(outFile, C);	
	fclose(outFile);
	return (0);
}
