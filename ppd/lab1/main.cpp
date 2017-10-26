#include <QCoreApplication>

#include "primaryvariable.h"
#include "mythread.h"
#include "verifythread.h"

#include <QList>
#include <QDebug>
#include <QTime>
#include <QThreadPool>

//#define LOGGING

#define MAX_THREAD_COUNT 5
#define PRIM_VARS_NO 8

int main(int argc, char *argv[])
{
    QCoreApplication a(argc, argv);

    QList<int> initialPrimVars = {1, 2, 3, 4, -5, 6, 7, 10};

    QList<PrimaryVariable*> primVars;
    QList<SecondaryVariable*> secondVars;

    foreach (int i, initialPrimVars) {
        PrimaryVariable *v = new PrimaryVariable(i);
        primVars.append(v);
    }

    SecondaryVariable *aa = new SecondaryVariable({primVars.at(0), primVars.at(1), primVars.at(2)});
    SecondaryVariable *bb = new SecondaryVariable({primVars.at(5), primVars.at(6), primVars.at(7), aa});
    SecondaryVariable *cc = new SecondaryVariable({bb, aa, primVars.at(4)});

    secondVars.append(aa);
    secondVars.append(bb);
    secondVars.append(cc);

#ifdef LOGGING
    qDebug() << "Primary variables values:";
    foreach (PrimaryVariable *v, primVars) {
        qDebug() << v->getValue();
    }

    qDebug() << "----------";

    qDebug() << "Secondary variables values:";
    foreach (SecondaryVariable *v, secondVars) {
        qDebug() << v->getValue();
    }
#endif

//    qsrand(QTime::currentTime());

    VerifyThread *verif = new VerifyThread(&secondVars);
    verif->start();

    MyThread *thread1 = new MyThread(&primVars);
    thread1->start();
    MyThread *thread2 = new MyThread(&primVars);
    thread2->start();
    MyThread *thread3 = new MyThread(&primVars);
    thread3->start();
    MyThread *thread4 = new MyThread(&primVars);
    thread4->start();
    MyThread *thread5 = new MyThread(&primVars);
    thread5->start();

//    MyThread *thread6 = new MyThread(&primVars);
//    thread6->start();
//    MyThread *thread7 = new MyThread(&primVars);
//    thread7->start();
//    MyThread *thread8 = new MyThread(&primVars);
//    thread8->start();
//    MyThread *thread9 = new MyThread(&primVars);
//    thread9->start();
//    MyThread *thread10 = new MyThread(&primVars);
//    thread10->start();
//    MyThread *thread11 = new MyThread(&primVars);
//    thread11->start();




#ifdef LOGGING
    qDebug() << "Primary variables values:";
    foreach (PrimaryVariable *v, primVars) {
        qDebug() << v->getValue();
    }

    qDebug() << "----------";

    qDebug() << "Secondary variables values:";
    foreach (SecondaryVariable *v, secondVars) {
        qDebug() << v->getValue();
    }
#endif

    return a.exec();
}
