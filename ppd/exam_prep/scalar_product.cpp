


int		N; // Vector sizes
int		T; // Nr. of threads

int		currentThreads = 1;

int		v1[N];
int		v2[N];

int 	results[N];

int 	do_product(int start, int end) {
	int s1, s2;
	pthread_t t;
	bool hasChild = false;
	if (start == end) {
		return(v1[start] * v2[start]);
	} else {
		if (currentThreads < T) {
			hasChild = true;
			currentThreads++;
			// Create new thread for start, end / 2
			int *newV = (int*)malloc(sizeof(int) * 2);
			newV[0] = start;
			newV[1] = end / 2;
			pthread_create(t , NULL, worker_thread, newV);
		} else {
			s1 = do_product(start, end / 2);
		}
		s2 = do_product(end / 2 + 1, end);
		if (hasChild) {
			// Syncronize and get result from thread
			void *res;
			pthread_join(t, &res);
			s1 = *(int*)res;
		}
		return (s1 + s2);
	}
}

void	*worker_thread(void *arg) {
	int *pos = (int*)arg;
	int start = pos[0];
	int end = pos[1];
	int result = do_product(start, end);
	pthread_exit(&result);
}

int main(void) {
	int scalarProduct = do_product(0, N - 1);
	printf("The scalar product is: %d", scalarProduct);
	return (0);
}
