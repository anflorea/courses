#pragma once
#include <QWidget>
#include "ui_presenter.h"

class Presenter : public QWidget {
	Q_OBJECT

public:
	Presenter(QWidget * parent = Q_NULLPTR);
	~Presenter();

private:
	Ui::Presenter ui;
};
