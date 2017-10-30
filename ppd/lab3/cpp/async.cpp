#include <future>
#include <iostream>
#include <vector>

#include "includes.h"

int **g_A;
int **g_B;
int **g_C;

int N, M, Q;

void add(void) {
	std::vector<std::future<void>> futures;

	auto start = std::chrono::steady_clock::now();

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			futures.push_back (std::async([](int x, int y) { g_C[x][y] = g_A[x][y] + g_B[x][y]; }, i, j));
		}
	}

	for (auto &e : futures) {
		e.get();
	}

	auto end = std::chrono::steady_clock::now();

	auto diff = end - start;
	std::cout << std::chrono::duration <double, std::milli> (diff).count() << " ms" << std::endl;
}

void multiply() {
	std::vector<std::future<void>> futures;

	auto start = std::chrono::steady_clock::now();

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < Q; j++) {

			futures.push_back (std::async([](int x, int y) {
					int sum = 0;

					for (int z = 0; z < M; z++)
						sum += g_A[x][z] + g_B[z][y];

					g_C[x][y] = sum;
				}, i, j));

		}
	}

	for (auto &e : futures) {
		e.get();
	}
	
	auto end = std::chrono::steady_clock::now();

	auto diff = end - start;
	std::cout << std::chrono::duration <double, std::milli> (diff).count() << " ms" << std::endl;
}

int main(void) {
	N = 1000;
	M = 1000;
	Q = 1000;

	g_A = generate_matrix(N, M);
	g_B = generate_matrix(N, M);
	g_C = alloc_matrix(N, M);

	add();
	//multiply();

	return 0;
}
