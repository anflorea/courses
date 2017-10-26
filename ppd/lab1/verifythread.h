#ifndef VERIFYTHREAD_H
#define VERIFYTHREAD_H

#include "primaryvariable.h"

#include <QThread>
#include <QTimer>
#include <QDebug>

// Worker thread that verifies every 3 seconds that all the
// values of the secondary variables are still correct
class VerifyThread : public QThread
{
public:
    VerifyThread();
    VerifyThread(QList<SecondaryVariable*> *vars);

    void run();

    void timerEvent(QTimerEvent *event);

private:
    QList<SecondaryVariable*> *m_vars;
    QMutex mutex;
};

#endif // VERIFYTHREAD_H
