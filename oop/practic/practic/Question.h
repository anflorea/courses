#pragma once
#include <string>
using namespace std;
class Question {
public:
	Question(int _id, string _text, string _answer, int _score);
	~Question();
	inline int getId() {
		return id;
	}
	inline string getText() {
		return text;
	}
	inline string getAnswer() {
		return answer;
	}
	inline int getScore() {
		return score;
	}
	inline string toListItem() {
		return to_string(id) + "  |  " + text + " | " + to_string(score);
	}
	inline string toString() {
		return to_string(id) + "," + text + "," + answer + "," + to_string(score);
	}
	inline bool operator == (const Question &other) {
		return id == other.id && text == other.text && answer == other.answer && score == other.score;
	}
private:
	int	id;
	string text;
	string answer;
	int score;
};