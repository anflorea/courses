#include "gui.h"
#include <qmessagebox.h>
#include <qobject.h>
#include <qwidget.h>
#include <qlistwidget.h>

GUI::GUI(Controller *_c, Participant _p, QWidget *parent)
	: c(_c), p(_p), QWidget(parent)
{
	ui.setupUi(this);
	QWidget::setWindowTitle(QString::fromStdString(p.getName()));
	connectSignalsAndSlots();
	populateList();
	updateScore();
}

GUI::~GUI()
{

}

void GUI::populateList() {
	ui.listQuestions->clear();
	for (auto it : c->getQuestions()) {
		QString s = QString::fromStdString(it.toListItem());
		ui.listQuestions->addItem(new QListWidgetItem(s));
	}
}

void GUI::connectSignalsAndSlots() {
	QObject::connect(ui.answerButton, SIGNAL(clicked()), this, SLOT(answerQuestion()));
	QObject::connect(ui.addQuestionButton, SIGNAL(clicked()), this, SLOT(addQuestion()));
	QObject::connect(ui.removeQuestionButton, SIGNAL(clicked()), this, SLOT(removeQuestion()));
}

void GUI::addQuestion() {
	int id = ui.questionId->text().toInt();
	string text = ui.questionText->text().toStdString();
	string answer = ui.questionAnswer->text().toStdString();
	int score = ui.QuestionScore->text().toInt();
	Question q = Question{ id, text, answer, score };
	try {
		c->addQuestion(q);
		populateList();
	}
	catch (Exception &e) {
		QMessageBox::critical(this, "Add Question", QString(e.what()), QMessageBox::Ok, QMessageBox::Ok);
	}
}

void GUI::removeQuestion() {
	int ind = getSelectedItem();
	if (ind == -1) {
		QMessageBox::critical(this, "Remove Question", "Please select an item from the list in order to remove a question!", QMessageBox::Ok, QMessageBox::Ok);
		return;
	}
	Question q = c->getQuestions()[ind];
	try {
		c->removeQuestion(q);
	}
	catch (Exception &e) {
		QMessageBox::critical(this, "Remove Question", QString(e.what()), QMessageBox::Ok, QMessageBox::Ok);
	}
}

void GUI::answerQuestion() {
	///QMessageBox::information(this, "buton", "buton", QMessageBox::Ok, QMessageBox::Ok);
	string answer = ui.answerInput->text().toStdString();
	int ind = getSelectedItem();
	if (ind == -1) {
		QMessageBox::critical(this, "Answer question", "Please select an item from the list in order to answer a question!", QMessageBox::Ok, QMessageBox::Ok);
		return;
	}
	Question q = c->getQuestions()[ind];
	//ui.answerInput->setText(QString::fromStdString(q.toString()));
	try {
		c->answerQuestion(p, q, answer);
		ui.answerInput->clear();
		updateScore();
	}
	catch (Exception &e) {
		QMessageBox::critical(this, "Answer Question", QString(e.what()), QMessageBox::Ok, QMessageBox::Ok);
	}
}

void GUI::updateScore() {
	ui.scoreLabel->setText(QString::fromStdString(to_string(p.getScore())));
}

int GUI::getSelectedItem() {
	auto v = ui.listQuestions->selectedItems();
	if (v.size() == 0)
		return -1;
	return ui.listQuestions->row(v[0]);
}