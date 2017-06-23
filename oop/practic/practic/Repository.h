#pragma once
#include <vector>
#include <Question.h>
#include <Observable.h>
#include <Participant.h>

class Repository : public Observable {
public:
	Repository(string filename = "questions.txt");
	~Repository();
	inline vector<Participant> getParticipants() {
		return participants;
	}
	inline vector<Question> getQuestions() {
		return questions;
	}
	/*
		Description: Adds a new question
		Input: q
			Precondition: q is a question
		Output: -
	*/
	void addQuestion(Question q);
	/*
		Description: Removes a question
		Input: q
			Precondition: q is a question
		Output: -
	*/
	void removeQuestion(Question q);
private:
	void readFromFile(string filename);
	void writeToFile();
	vector<Participant> participants;
	vector<Question> questions;
};