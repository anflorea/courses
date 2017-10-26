#ifndef MYTHREAD_H
#define MYTHREAD_H

#include "primaryvariable.h"

#include <QThread>
#include <QDebug>


// A worker thread that on an interval between 0-2 seconds it creates
// notifications that change the value of a first variable
class MyThread : public QThread
{
public:
    MyThread();
    MyThread(QList<PrimaryVariable*> *primVars);
    ~MyThread();

    void run();

    void timerEvent(QTimerEvent *event);

private:
    QList<PrimaryVariable*> *m_primVars;
    int m_timerId;
};

#endif // MYTHREAD_H
