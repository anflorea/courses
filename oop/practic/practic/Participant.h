#pragma once
#include <string>
using namespace std;
class Participant
{
public:
	Participant(string _name, int _score);
	~Participant();
	inline string getName() {
		return name;
	}
	inline int getScore() {
		return score;
	}
	inline void setScore(int _score) {
		score = _score;
	}
	void addToScore(int nbr);
	inline string toString() {
		return name + "," + to_string(score);
	}
private:
	string name;
	int score;
};

