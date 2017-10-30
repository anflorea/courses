#include "includes.h"

// Gets the current system time in milliseconds
unsigned long long getCurrentMillis()
{
	struct timeval tv;
	unsigned long long theTime;

	gettimeofday(&tv, NULL);
	theTime =
	(unsigned long long)(tv.tv_sec) * 1000 + 
	(unsigned long long)(tv.tv_usec) / 1000;

	return theTime;
}

// Prints on the screen a n x m matrix 
void print_matrix(int **matrix, int n, int m)
{
	int i, j;

	i = 0;
	while (i < n)
	{
		j = 0;
		while (j < m)
		{
			printf(j < m - 1 ? "%d " : "%d\n", matrix[i][j]);
			j++;
		}
		i++;
	}
}

// Allocates a n x m matrix and initializes the values with 0
int **alloc_matrix(int n, int m)
{
	int **matrix;
	int	i, j;
	
	matrix = (int**)malloc(sizeof(int*) * (n + 1));
	if (matrix == NULL)
		return NULL;
	i = 0;
	while (i < n)
	{
		matrix[i] = (int*)malloc(sizeof(int) * (m + 1));
		if (matrix[i] == NULL)
			return NULL;
		j = 0;
		while (j < m)
		{
			matrix[i][j] = 0;
			j++;
		}
		i++;
	}
	return matrix;
}

// Generates a n x m matrix with random values between -200 and 200
int **generate_matrix(int n, int m)
{
	int **matrix;
	int	i, j;

	if (n <= 0 || m <= 0)
		return NULL;
	matrix = (int**)malloc(sizeof(int*) * (n + 1));
	if (matrix == NULL)
		return NULL;
	i = 0;
	while (i < n)
	{
		matrix[i] = (int*)malloc(sizeof(int) * (m + 1));
		if (matrix[i] == NULL)
			return NULL;
		j = 0;
		while (j < m)
		{
			// This generates a random value between -200 and 200
			matrix[i][j] = rand() % 401 + (-200);
			j++;
		}
		i++;
	}
	return matrix;
}
