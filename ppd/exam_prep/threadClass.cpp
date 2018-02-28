



class Thread {

	private:

		void worker_thread() {
			while (!completed) {
				if (queue.isEmpty()) {
					unique_lock<mutex> lck(mtx);
					cv.wait(lck);
				}

				if (!queue.isEmpty()) {
					sum += queue.at(0);
					queue.pop();
				}
			}
		}

	public:
		void startThread() {
			threads.emplace_back([this](){ worker_thread(); });
		}

		void compute(int a) {
			queue.push_back(a);
			cv.notify_one();
		}

		int getValue() {
			completed = true;
			cv.notify_all();
			threads.at(0).join();
			return sum;
		}

	private:

		int sum = 0;
		vector<int> queue;
		bool completed = false;
		condition_variable cv;
		mutex mtx;
		vector<thread> threads;
};
