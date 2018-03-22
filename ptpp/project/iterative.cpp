#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int		*construct_huge(char *str) {
	int		*huge, i, j;

	huge = (int*)malloc(sizeof(int) * (strlen(str) + 1));
	huge[0] = strlen(str);
	for (i = strlen(str) - 1, j = 1; i >= 0; i--, j++) {
		huge[j] = str[i] - '0';
	}
	return huge;
}

int		*multiply_huge(int *A, int *B) {
	int	*C, i, j, T = 0;

	C = (int*)malloc(sizeof(int) * (A[0] + B[0] + 1));
	C[0] = A[0] + B[0] - 1;
	for (i = 1; i <= A[0] + B[0]; i++) {
		C[i] = 0;
	}
	for (i = 1; i <= A[0]; i++) {
		for (j = 1; j <= B[0]; j++) {
			C[i + j - 1] += A[i] * B[j];
		}
	}
	for (i = 1; i <= C[0]; i++) {
		C[i] += T;
		T = C[i] / 10;
		C[i] %= 10;
	}
	if (T) {
		C[0] += 1;
		C[C[0]] = T;
	}
	return C;
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
	int		*A, *B, *C;

	if (argc != 3) {
		printf("usage: ./multiply <input file> <output file>");
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
	str1 = (char*)malloc(sizeof(char) * 400000);
	str2 = (char*)malloc(sizeof(char) * 400000);
	fscanf(inFile, "%s%s", str1, str2);
	A = construct_huge(str1);
	B = construct_huge(str2);
	C = multiply_huge(A, B);
	write_huge(outFile, C);	
	fclose(outFile);
	return (0);
}
