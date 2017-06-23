#ifndef GUI_H
#define GUI_H

#include <QtWidgets/QWidget>
#include "ui_gui.h"
#include <Controller.h>
#include <Participant.h>

class GUI : public QWidget, public Observer
{
	Q_OBJECT

public:
	GUI(Controller *_c, Participant _p, QWidget *parent = 0);
	~GUI();
	void update() {
		populateList();
	}
	public slots:
	void answerQuestion();
	void addQuestion();
	void removeQuestion();
private:
	void updateScore();
	int getSelectedItem();
	void populateList();
	void connectSignalsAndSlots();
	Ui::GUIClass ui;
	Controller *c;
	Participant p;
};

#endif // GUI_H
