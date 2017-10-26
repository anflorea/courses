#include "mythread.h"

MyThread::MyThread()
{

}

MyThread::MyThread(QList<PrimaryVariable *> *primVars)
{
    MyThread();
    m_primVars = primVars;

    // Start the timer
    int value = qrand() % 2000;
    m_timerId = startTimer(value);
}

MyThread::~MyThread()
{

}

void MyThread::run()
{
}

void MyThread::timerEvent(QTimerEvent *event)
{
    // Kill the timer so it doesn't have the same interval
    killTimer(m_timerId);


    // Get a random variable to change it's value
    int valueToChange = qrand() % m_primVars->length();
    // Get a random value to assign to the variable
    int value = qrand() % 200;
    m_primVars->at(valueToChange)->changeTo(value);

    qDebug() << "Changed value of variable with index " << valueToChange << " to " << value;

    // Create another timer with some other interval
    int newTime = qrand() % 2000;
    m_timerId = startTimer(newTime);
}
