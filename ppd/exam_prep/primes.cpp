#include <cstdio>
#include <cstdlib>
#include <pthread.h>
#include <math.h>

using namespace std;

int		T = 3;
int		N;
int		S;

vector<int> helper;
vector<int> result;

mutex mtx;

bool isPrimeShm(int a) {
	int i;

	for (i = 0; i < helper.size(); i++) {
		if (a % helper.at(i) == 0)
			return false;
	}
	return true;
}

void *worker_thread(void *arg) {
	int id = *(int*)arg;
	int i;

	for (i = S + id; i < N; i += T) {
		if (isPrimeShm(i)) {
			unique_lock<mutex> lck(mtx);
			result.push_back(i);
		}
	}
}


bool isPrime(int a) {
	int i;

	for (i = 2; i <= sqrt(a); i++)
		if (a % i == 0)
			return false;
	return true;
}

int main(void) {
	int i;

	pthread_t threads[T];
	int tid[T];

	S = sqrt(N);

	for (i = 2; i <= s; i++) {
		if (isPrime(i)) {
			helper.push_back(i);
			result.push_back(i);
		}
	}

	for (i = 0; i < T; i++) {
		tid[i] = i;
		pthread_create(&thred[i], NULL, worker_thread, &tid[i]);
	}

	for (i = 0; i < T; i++) {
		pthread_join(thread[i], NULL);
	}

	return 0;
}
