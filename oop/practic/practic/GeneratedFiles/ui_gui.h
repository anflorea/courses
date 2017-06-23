/********************************************************************************
** Form generated from reading UI file 'gui.ui'
**
** Created by: Qt User Interface Compiler version 5.6.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_GUI_H
#define UI_GUI_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_GUIClass
{
public:
    QWidget *verticalLayoutWidget;
    QVBoxLayout *verticalLayout;
    QGridLayout *gridLayout_3;
    QLineEdit *questionAnswer;
    QLabel *label_5;
    QLabel *label_4;
    QLineEdit *questionText;
    QLabel *label_3;
    QLabel *label_6;
    QLineEdit *QuestionScore;
    QLineEdit *questionId;
    QPushButton *addQuestionButton;
    QPushButton *removeQuestionButton;
    QListWidget *listQuestions;
    QGridLayout *gridLayout;
    QLabel *label_2;
    QLabel *label;
    QPushButton *answerButton;
    QLineEdit *answerInput;
    QLabel *scoreLabel;

    void setupUi(QWidget *GUIClass)
    {
        if (GUIClass->objectName().isEmpty())
            GUIClass->setObjectName(QStringLiteral("GUIClass"));
        GUIClass->resize(600, 413);
        verticalLayoutWidget = new QWidget(GUIClass);
        verticalLayoutWidget->setObjectName(QStringLiteral("verticalLayoutWidget"));
        verticalLayoutWidget->setGeometry(QRect(10, 20, 581, 381));
        verticalLayout = new QVBoxLayout(verticalLayoutWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        gridLayout_3 = new QGridLayout();
        gridLayout_3->setSpacing(6);
        gridLayout_3->setObjectName(QStringLiteral("gridLayout_3"));
        questionAnswer = new QLineEdit(verticalLayoutWidget);
        questionAnswer->setObjectName(QStringLiteral("questionAnswer"));

        gridLayout_3->addWidget(questionAnswer, 2, 1, 1, 1);

        label_5 = new QLabel(verticalLayoutWidget);
        label_5->setObjectName(QStringLiteral("label_5"));

        gridLayout_3->addWidget(label_5, 1, 0, 1, 1);

        label_4 = new QLabel(verticalLayoutWidget);
        label_4->setObjectName(QStringLiteral("label_4"));

        gridLayout_3->addWidget(label_4, 2, 0, 1, 1);

        questionText = new QLineEdit(verticalLayoutWidget);
        questionText->setObjectName(QStringLiteral("questionText"));

        gridLayout_3->addWidget(questionText, 1, 1, 1, 1);

        label_3 = new QLabel(verticalLayoutWidget);
        label_3->setObjectName(QStringLiteral("label_3"));

        gridLayout_3->addWidget(label_3, 3, 0, 1, 1);

        label_6 = new QLabel(verticalLayoutWidget);
        label_6->setObjectName(QStringLiteral("label_6"));

        gridLayout_3->addWidget(label_6, 0, 0, 1, 1);

        QuestionScore = new QLineEdit(verticalLayoutWidget);
        QuestionScore->setObjectName(QStringLiteral("QuestionScore"));

        gridLayout_3->addWidget(QuestionScore, 3, 1, 1, 1);

        questionId = new QLineEdit(verticalLayoutWidget);
        questionId->setObjectName(QStringLiteral("questionId"));

        gridLayout_3->addWidget(questionId, 0, 1, 1, 1);


        verticalLayout->addLayout(gridLayout_3);

        addQuestionButton = new QPushButton(verticalLayoutWidget);
        addQuestionButton->setObjectName(QStringLiteral("addQuestionButton"));

        verticalLayout->addWidget(addQuestionButton);

        removeQuestionButton = new QPushButton(verticalLayoutWidget);
        removeQuestionButton->setObjectName(QStringLiteral("removeQuestionButton"));

        verticalLayout->addWidget(removeQuestionButton);

        listQuestions = new QListWidget(verticalLayoutWidget);
        listQuestions->setObjectName(QStringLiteral("listQuestions"));

        verticalLayout->addWidget(listQuestions);

        gridLayout = new QGridLayout();
        gridLayout->setSpacing(6);
        gridLayout->setObjectName(QStringLiteral("gridLayout"));
        label_2 = new QLabel(verticalLayoutWidget);
        label_2->setObjectName(QStringLiteral("label_2"));

        gridLayout->addWidget(label_2, 2, 1, 1, 1);

        label = new QLabel(verticalLayoutWidget);
        label->setObjectName(QStringLiteral("label"));

        gridLayout->addWidget(label, 1, 0, 1, 1);

        answerButton = new QPushButton(verticalLayoutWidget);
        answerButton->setObjectName(QStringLiteral("answerButton"));

        gridLayout->addWidget(answerButton, 1, 2, 1, 1);

        answerInput = new QLineEdit(verticalLayoutWidget);
        answerInput->setObjectName(QStringLiteral("answerInput"));

        gridLayout->addWidget(answerInput, 1, 1, 1, 1);

        scoreLabel = new QLabel(verticalLayoutWidget);
        scoreLabel->setObjectName(QStringLiteral("scoreLabel"));

        gridLayout->addWidget(scoreLabel, 2, 2, 1, 1);


        verticalLayout->addLayout(gridLayout);


        retranslateUi(GUIClass);

        QMetaObject::connectSlotsByName(GUIClass);
    } // setupUi

    void retranslateUi(QWidget *GUIClass)
    {
        GUIClass->setWindowTitle(QApplication::translate("GUIClass", "GUI", 0));
        label_5->setText(QApplication::translate("GUIClass", "Text:", 0));
        label_4->setText(QApplication::translate("GUIClass", "Answer:", 0));
        label_3->setText(QApplication::translate("GUIClass", "Score:", 0));
        label_6->setText(QApplication::translate("GUIClass", "Id:", 0));
        addQuestionButton->setText(QApplication::translate("GUIClass", "Add", 0));
        removeQuestionButton->setText(QApplication::translate("GUIClass", "Remove", 0));
        label_2->setText(QApplication::translate("GUIClass", "Score:", 0));
        label->setText(QApplication::translate("GUIClass", "Answer:", 0));
        answerButton->setText(QApplication::translate("GUIClass", "Send", 0));
        scoreLabel->setText(QApplication::translate("GUIClass", "0", 0));
    } // retranslateUi

};

namespace Ui {
    class GUIClass: public Ui_GUIClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_GUI_H
