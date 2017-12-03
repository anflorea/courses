#include <iostream>
#include <fstream>
#include <vector>
#include <thread>
#include <queue>
#include <condition_variable>
#include <mutex>
#include <tuple>
#include <unistd.h>

using namespace std;

int				n;
int				result[200];
int				resultSize = 0;
vector<string>	nums;

queue <tuple<int, int>> q;
condition_variable		cv;
mutex					mx, mtx;
bool					finished;

inline void	worker_thread()
{
	while (true) {
		unique_lock<mutex> lkk(mtx);
		cv.wait(lkk, []{ return finished || !q.empty(); });
		if (finished)
			break;
		if (q.empty())
			continue;
		tuple<int, int> el = q.front();
		q.pop();
		int index = get<0>(el);
		int value = get<1>(el);

		{
			unique_lock<mutex> lk(mx);
			if (index + 1 > resultSize)
				resultSize = index + 1;
		}

		value += result[index];
		{
			unique_lock<mutex> lk(mx);
			result[index] = value % 10;
		}
		value /= 10;
		while (value > 0) {
			index++;
			{
				unique_lock<mutex> lk(mx);
				q.push(make_tuple(index, value % 10));
			}
			value /= 10;
			cv.notify_one();
		}
	}
}

inline void	compute(int index)
{
	int sum = 0;

	for (int i = 0; i < nums.size(); i++) {
		string nbr;

		nbr = nums.at(i);
		if (nbr.size() > index)
			sum += nbr[index] - '0';
	}
	{
		unique_lock<mutex> lk(mx);
		result[index] += sum % 10;
	}
	sum /= 10;

	if (sum == 0)
		return;

	while (sum > 0) {
		index++;
		{
			unique_lock<mutex> lk(mx);
			q.push(make_tuple(index, sum % 10));
		}
		sum /= 10;
		cv.notify_one();
	}
}

int			main(void)
{
	clock_t t;
	t = clock();

	ifstream fin("add.in");

	int n;
	fin >> n;

	int maxLen = 0;

	for (int i = 0; i < 200; i++)
		result[i] = 0;

	for (int i = 0; i < n; i++) {
		string x;
		fin >> x;
		reverse(x.begin(), x.end());
		if (x.size() > maxLen)
			maxLen = x.size();
		nums.push_back(x);
	}
	resultSize = maxLen;

	thread worker(worker_thread);

	vector<thread> th;
	for (int i = 0; i < maxLen; i++) {
		th.push_back(thread(compute, i));
	}

	for (int i = 0; i < maxLen; i++) {
		th[i].join();
	}

	finished = true;
	cv.notify_one();
	worker.join();

	string sol = "";
	for (int i = resultSize - 1; i >= 0; i--) {
		sol += to_string(result[i]);
	}

	ofstream fout("add.out");

	fout << sol << endl;

	t = clock() - t;
	cout << "Addition of " << n << " numbers took " << t
		<< " cycles (" << static_cast<float> (t) / CLOCKS_PER_SEC << " seconds)" << endl;

	return 0;
}
