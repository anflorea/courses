#include <thread>
#include <iostream>
#include <vector>
#include "ctpl_stl.h"

#include "includes.h"

int **g_A;
int **g_B;
int **g_C;

int N, M, Q;

void add(void) {
	ctpl::thread_pool p(4);
	std::vector<std::future<void>> workers;

	auto start = std::chrono::steady_clock::now();

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			workers.push_back( p.push ([i, j](int) { g_C[i][j] = g_A[i][j] + g_B[i][j]; }));
		}
	}

	for (auto &t : workers) {
		t.get();
	}

	auto end = std::chrono::steady_clock::now();

	auto diff = end - start;
	std::cout << std::chrono::duration <double, std::milli> (diff).count() << " ms" << std::endl;
}

void multiply() {
	ctpl::thread_pool p(4);
	std::vector<std::future<void>> workers;

	auto start = std::chrono::steady_clock::now();
	
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < Q; j++) {
			workers.push_back ( p.push ([i, j](int) { 
					int sum = 0;

					for (int z = 0; z < M; z++)
						sum += g_A[i][z] + g_B[z][j];

					g_C[i][j] = sum;
				} ));
		}
	}

	for (auto &t : workers) {
		t.get();
	}

	auto end = std::chrono::steady_clock::now();

	auto diff = end - start;
	std::cout << std::chrono::duration <double, std::milli> (diff).count() << " ms" << std::endl;
}

int main(void) {
	N = 100;
	M = 100;
	Q = 100;

	g_A = generate_matrix(N, M);
	g_B = generate_matrix(N, M);
	g_C = alloc_matrix(N, M);

	//add();
	multiply();

	return 0;
}
