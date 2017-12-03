#include <iostream>
#include <fstream>
#include <vector>
#include <thread>

using namespace std;

const int	maxN = 500005;

int			n;

long long	arr[maxN], m[maxN][maxN];

inline int	computeSum(int start, int end)
{
	if (start == end) {
		m[start][end] = arr[start];
		return arr[start];
	}

	int middle = (start + end) / 2;
	int low = computeSum(start, middle);
	int high = computeSum(middle + 1, end);

	int result = low + high;

	m[start][end] = result;

	return result;
}

int			main(void)
{
	clock_t	t;
	t = clock();

	ifstream fin("sums.in");

	fin >> n;
	for (int i = 0; i < n; i++) {
		fin >> arr[i];
	}

	vector<thread> th;
	for (int i = 0; i < n; i++) {
		th.push_back(thread(computeSum, 0, i));
	}

	for (int i = 0; i < n; i++) {
		th[i].join();
	}

	ofstream fout("sums.out");
	for (int i = 0; i < n; i++) {
		fout << m[0][i] << '\n';
	}

	t = clock() - t;
	cout << "Computing partial sums of an array with " << n
		<< " elements took " << t << " cycles ("
		<< static_cast<float> (t) / CLOCKS_PER_SEC << " seconds)" << endl;
}
