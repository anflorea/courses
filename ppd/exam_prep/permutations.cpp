#include <cstdio>
#include <unistd.h>
#include <pthread.h>
#include <stdlib.h>

using namespace std;

int T = 3; // Nr. threaduri

int N = 6; // Permutation of N

int result = 0;

mutex mtx;

bool pred(vector<int> const& v);

bool validate(vector<int>& v, k) {
	int i;

	for (i = 0; i < v.size(); i++) {
		if (v.at(i) == k)
			return false;
	}
	return true;
}

void backtrack(vector<int>& v) {
	if (v.size() == N) {
		if (pred(v)) {
			unique_lock<mutex> lck(mtx);
			result++;
		}
		return;
	}
	int k;
	for (k = 1; k <= N; k++) {
		if (validate(v, k)) {
			v.push_back(k);
			backtrack(v);
			v.pop_back();
		}
	}
}

void *start_thread(void *arg) {
	int id = *(int*)arg;
	int i;

	for (i = id; i <= N; i += T) {
		vector<int> v;
		v.push_back(i);
		backtrack(v);
	}
}

int main(void) {
	int i;
	int tid[T];
	pthread_t thread[T];

	for (i = 1; i <= T; i++) {
		tid[i] = i;
		pthread_create(&thread[i], NULL, start_thread, &tid[i]);
	}

	for (i = 1; i <= T; i++) {
		pthread_join(thread[i], NULL);
	}

	printf("The job is done: %d permutations satisfy the condition", result);
	return 0;
}
