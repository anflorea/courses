#include "verifythread.h"

VerifyThread::VerifyThread()
{

}

VerifyThread::VerifyThread(QList<SecondaryVariable *> *vars)
{
    VerifyThread();
    m_vars = vars;
    startTimer(3000);
}

void VerifyThread::run()
{
}

void VerifyThread::timerEvent(QTimerEvent *event)
{
    bool correct = true;
    foreach(SecondaryVariable *p, *m_vars) {
        int sum = 0;
        mutex.lock();
        foreach(PrimaryVariable *v, p->sumOf) {
            sum += v->getValue();
        }
        mutex.unlock();
        if (sum != p->getValue())
            correct = false;
        qDebug() << sum << " -- " << p->getValue();
    }
    if (correct) {
        qDebug() << "Verify Thread :: All values are correct";
    } else {
        qDebug() << "Verify Thread :: There is an error!!!!";
    }
}
