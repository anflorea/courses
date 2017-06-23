#include "Controller.h"

Controller::Controller(Repository *_r) : r(_r)
{
}

Controller::~Controller()
{
}

void Controller::answerQuestion(Participant &p, Question q, string answer) {
	if (q.getAnswer() == answer) {
		p.addToScore(q.getScore());
	}
	else
		throw Exception("Incorrect answer!");
}