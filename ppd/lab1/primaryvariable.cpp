#include "primaryvariable.h"

PrimaryVariable::PrimaryVariable()
{
}

PrimaryVariable::PrimaryVariable(int initialValue)
{
    value = initialValue;
}

void PrimaryVariable::changeTo(int newValue)
{
    mutex.lock();
    int diff = newValue - value;
    value = newValue;
    mutex.unlock();

    foreach (SecondaryVariable *v, dependecies) {
        v->notifyChanged(diff);
    }
}

void PrimaryVariable::addDependency(SecondaryVariable *v)
{
    dependecies.append(v);
}

int PrimaryVariable::getValue()
{
    return value;
}

SecondaryVariable::SecondaryVariable(QList<PrimaryVariable*> primVars)
{
    foreach (PrimaryVariable *v, primVars) {
        sum += v->getValue();
        sumOf.append(v);
        v->addDependency(this);
    }
}

void SecondaryVariable::notifyChanged(int diff)
{
    mutex.lock();
    qDebug() << "I locked the variable with value: " << getValue();
    sum += diff;
    qDebug() << "I unlocked the variable with value: " << getValue();
    mutex.unlock();

    foreach (SecondaryVariable *v, dependecies) {
        v->notifyChanged(diff);
    }
}

int SecondaryVariable::getValue()
{
    return sum;
}
