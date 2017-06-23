#pragma once
#include <Repository.h>
#include <Exception.h>
class Controller
{
public:
	Controller(Repository *_r);
	~Controller();
	inline vector <Question> getQuestions() {
		return r->getQuestions();
	}
	/*
		Description: Adds a new question
		Input: question
		Output: -
			Postcondition: throws exception if something goes wrong
	*/
	void addQuestion(Question q) {
		vector<Question> v = r->getQuestions();
		if (q.getText().size() == 0)
			throw Exception("The text area must not be empty.");
		for (auto it : v) {
			if (it.getId() == q.getId())
				throw Exception("A question with the specified id already exists.");
		}
		r->addQuestion(q);
	}
	/*
	Description: Removes a question
	Input: question
	Output: -
	Postcondition: throws exception if something goes wrong
	*/
	void removeQuestion(Question q) {
		r->removeQuestion(q);
	}
	/*
		Description: Verify if an answer given by the user is correct and if it is, it updates the score.
		Input: p, q, answer
			Precondition: p is the participant, q is the question, and the answer is a string
		Output: -
			Postcondition: throws exception if the answer is incorrect
	*/
	void answerQuestion(Participant &p, Question q, string answer);
	Repository *r;
};