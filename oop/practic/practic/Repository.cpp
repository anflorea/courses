#include "Repository.h"
#include <sstream>
#include <fstream>
#include <algorithm>

Repository::Repository(string filename)
{
	readFromFile(filename);
}

Repository::~Repository()
{
	writeToFile();
}

void Repository::addQuestion(Question q) {
	questions.push_back(q);
	notify();
}

void Repository::removeQuestion(Question q) {
	vector<Question> newq;
	for (auto it : questions) {
		if (!(it == q)) {
			newq.push_back(it);
		}
	}
	questions = newq;
	notify();
}

void Repository::readFromFile(string filename) {
	ifstream fin("participants.txt");
	string line;
	while (getline(fin, line)) {
		stringstream get(line);
		vector <string> all;
		string act;
		while (getline(get, act, ',')) {
			all.push_back(act);
		}
		participants.push_back(Participant(all[0], stoi(all[1])));
	}
	fin.close();
	fin.open(filename);
	while (getline(fin, line)) {
		stringstream get(line);
		vector <string> all;
		string act;
		while (getline(get, act, ',')) {
			all.push_back(act);
		}
		questions.push_back(Question(stoi(all[0]), all[1], all[2], stoi(all[3])));
	}
}

void Repository::writeToFile() {
	ofstream fout("questions.txt");
	for (auto it : questions)
		fout << it.toString() << '\n';
}
