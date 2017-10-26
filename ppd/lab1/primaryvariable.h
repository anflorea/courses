#ifndef PRIMARYVARIABLE_H
#define PRIMARYVARIABLE_H

#include <QList>
#include <QMutex>
#include <QDebug>

class SecondaryVariable;

// Primary variable type
//  - it has a value that can be changed in a notification with the changeTo() function
//  - it keeps a list of dependencies to notify them when it's value has changed
//
class PrimaryVariable
{
public:
    PrimaryVariable();
    PrimaryVariable(int initialValue);

    // Change the value of the variable
    //   InputValue: the value to change to
    void changeTo(int newValue);

    // Add a new SecondaryVariable type dependency
    //  InputValue: the secondary variable
    void addDependency(SecondaryVariable *v);

    // Returns the value of the variable
    virtual int getValue();

    QList<SecondaryVariable*> dependecies;

    int value;
    QMutex mutex;
};


// Secondary variable type
//  - it has a value that is the sum of the variables in the sumOf list
//  - it keeps a list of dependencies to notify them when it's value has changed
//
class SecondaryVariable : public PrimaryVariable
{
public:
    SecondaryVariable(QList<PrimaryVariable*> primVars);

    // Called when a variable that it depends on has changed it's value
    void notifyChanged(int diff);

    // Returns the value of the variable
    int getValue();

    QList<SecondaryVariable*> dependencies;
    QList<PrimaryVariable*> sumOf;

    int sum = 0;
    QMutex mutex;
};

#endif // PRIMARYVARIABLE_H
