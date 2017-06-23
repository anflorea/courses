#include "gui.h"
#include <QtWidgets/QApplication>
#include <Controller.h>
#include <Tester.h>
#include <Repository.h>
#include <iostream>

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	//Tester t;
	Repository *r = new Repository();
	Controller *c = new Controller(r);
	vector <GUI *> windows;
	for (auto it : r->getParticipants()) {
		GUI * w = new GUI(c, it);
		w->show();
		r->attach((Observer*)w);
		windows.push_back(w);
	}
	int ret = a.exec();
	for (auto it : windows)
		delete it;
	delete c;
	delete r;
	return ret;
}
