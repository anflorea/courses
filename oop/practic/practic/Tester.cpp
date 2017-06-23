#include "Tester.h"
#include <Exception.h>
#include <cassert>
#include <Repository.h>
#include <Participant.h>
#include <Question.h>
#include <qmessagebox.h>
#include <Controller.h>
#include <qstring.h>
using namespace std;

Tester::Tester()
{
	Repository *r = new Repository("Text.txt");
	Controller *c = new Controller(r);
	assert(c->getQuestions()[0] == Question(1, "q1", "a1", 10));
	assert(c->getQuestions()[1] == Question(2, "q2", "a2", 20));
	assert(c->getQuestions()[2] == Question(3, "q3", "a3", 30));
	assert(c->getQuestions()[3] == Question(4, "q4", "a4", 40));

	c->addQuestion(Question(5, "q5", "a5", 50));
	assert(c->getQuestions()[0] == Question(1, "q1", "a1", 10));
	assert(c->getQuestions()[1] == Question(2, "q2", "a2", 20));
	assert(c->getQuestions()[2] == Question(3, "q3", "a3", 30));
	assert(c->getQuestions()[3] == Question(4, "q4", "a4", 40));
	assert(c->getQuestions()[4] == Question(5, "q5", "a5", 50));

	c->removeQuestion(Question(3, "q3", "a3", 30));
	assert(c->getQuestions()[0] == Question(1, "q1", "a1", 10));
	assert(c->getQuestions()[1] == Question(2, "q2", "a2", 20));
	assert(c->getQuestions()[2] == Question(4, "q4", "a4", 40));
	assert(c->getQuestions()[3] == Question(5, "q5", "a5", 50));

	Participant p = r->getParticipants()[0];
	int score = p.getScore();
	p.addToScore(20);
	int score2 = p.getScore();
	assert(score2 == score + 20);
	delete c;
	delete r;
};

Tester::~Tester()
{
}
