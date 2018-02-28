#include <cstdio>
#include <unistd.h>
#include <pthread.h>
#include <stdlib.h>

using namespace std;

int T = 3; // Nr. threaduri

int N = 6; // Number of nodes in the graph

int result = 0;

mutex mtx;

bool isHamiltonianPath(vector<int> const& v);

bool validate(vector<int>& v, k) {
	int i;

	for (i = 0; i < v.size(); i++) {
		if (v.at(i) == k)
			return false;
	}
	return true;
}

void backtrack(vector<int>& v) {
	if (result > 0)
		return;
	if (v.size() == N) {
		if (isHamiltonianPath(v)) {
			unique_lock<mutex> lck(mtx);
			result++;
			printf("Hamiltonian path found: ");
			for (int i = 0; i < v.size(); i++)
				printf("%d ", v.at(i));
			printf("\n");
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

	if (id == 0)
		id += T;
	for (i = id; i <= N; i += T) {
		vector<int> v;
		v.push_back(0);
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

	for (i = 1; i <= T && i <= N; i++) {
		pthread_join(thread[i], NULL);
	}

	return 0;
}
