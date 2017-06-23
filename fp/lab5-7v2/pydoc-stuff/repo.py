"""
    Created on Oct 31, 2015

    @author: aflorea
"""

from domain import *

class GeneralRepository(object):
    def __init__(self):
        '''
            Description: Constructor for the general reopsitory 
        '''
        self._list = []
        self._undo = []
        self._redo = []

    def __len__(self):
        '''
            Description: Computes the length of the _list
        '''
        return len(self._list)

    def get_all(self):
        '''
            Description: Returns a list of all the objects in the repository
            Input: self
                Preconditions: -
            Output: _list
                Postconditions: a list of objects
        '''
        return self._list

    def removeAll(self):
        '''
            Description: removes all the objects from _list
            Input: self
                Preconditions: -
            Output: 1
                Postconditions: -
        '''
        while len(self._list) > 0:
            self._list.pop()
        return (1)

    def appendUndo(self, alist):
        '''
            Description: Adds a new undo backup
            Input: self, alist
                Precondition: alist is a list
            Output: 1
                Postcondition: -
        '''
        self._undo.append(alist)
        self._redo = []
        return (1)

    def computeUndo(self):
        '''
            Description: Computes the undo functionality
            Input: self
                Precondition: -
            Output: 1/0
                Postcondition: 1 on succes / 0 if failed
        '''
        if (len(self._undo) > 0):
            self._redo.append(self._list)
            self._list = self._undo[-1]
            self._undo.pop(-1)
            return (1)
        return (0)

    def delUndo(self):
        '''
            Description: Removes the last undo if there was an error in the system
            Input: self
                Precondition: -
            Output: 1
                Postcondition: -
        '''
        self._undo.pop(-1)
        return (1)

    def computeRedo(self, list_copy):
        '''
            Description: Computes the Redo functionality
            Input: self, list_copy
                Precondition: list_copy is a list
            Output: 1/0
                Postcondition: 1 on succes / 0 if failed
        '''
        if (len(self._redo) > 0):
            self._undo.append(list_copy)
            self._list = self._redo[-1]
            self._redo.pop(-1)
            return (1)
        return (0)

class PersonsRepository(GeneralRepository):
    def addPerson(self, aPerson):
        '''
            Description: Add's a new person to the _list if the person does not already exist
            Input: self, aPerson
                Preconditions: aPerson is a Persons type object
            Output: 0/1
                Postconditions: 1 if aPerson was added, 0 otherwise
        '''
        for i in self._list:
            if (Persons.getID(i) == Persons.getID(aPerson)):
                return (0)
        self._list.append(aPerson)
        return (1)

    def updatePerson(self, my_person):
        '''
            Description: Updates a person from the _list if it's ID already exists
            Input: self, my_person
                Preconditions: my_person is a Persons type object
            Output: 0/1
                Postconditions: 1 if my_person was updated, 0 otherwise
        '''
        for i in self._list:
            if Persons.getID(i) == Persons.getID(my_person):
                Persons.SetName(i, Persons.GetName(my_person))
                Persons.SetPhone_number(i, Persons.GetPhone_number(my_person))
                Persons.SetAddress(i, Persons.GetAddress(my_person))
                return 1
        return 0

    def checkPersonById(self, personID):
        '''
            Description: Check's if the person with the ID personID is in _list
            Input: self, personID
                Preconditions: personID is an integer > 0
            Output: 0/1
                Postconditions: 1 if personID is found in _list, 0 otherwise
        '''
        for i in self._list:
            if Persons.getID(i) == personID:
                return 1
        return 0

    def findPersonById(self, personID):
        '''
            Description: Check's if the person with the ID personID is in _list
            Input: self, personID
                Preconditions: personID is an integer > 0
            Output: i
                Postconditions: if personID is found in _list, the object with that ID is returned, -1 is returned otherwise
        '''
        for i in self._list:
            if Persons.getID(i) == personID:
                return (i)
        return (-1)

    def removePerson(self, personID):
        '''
            Description: Removes a certain person from the repository
            Input: slef, personID
                Preconditions: personID is an integer > 0
            Output: 0/1
                Postconditions: 1 if the person is removed, 0 otherwise
        '''
        for i in self._list:
            if Persons.getID(i) == personID:
                self._list.remove(i)
                return (1)
        return (0)

    def getPersonName(self, aPerson):
        '''
            Description: Returns the name of a person
            Input: self, aPerson
                Precondition: aPerson is a Persons type object
            Output: 
                Postcondition: the name of the persons (string)
        '''
        return Persons.GetName(aPerson)

class ActivityRepository(GeneralRepository):
    def addActivity(self, my_activity):
        '''
            Description: Adds an activity to _list if it doesn't already exist
            Input: self, my_activity
                Preconditions: my_activity is an Activities type object
            Output: 0/1
                Postconditions: 1 if the activity was added with success, 0 otherwise
        '''
        for i in self._list:
            if Activities.getID(i) == Activities.getID(my_activity):
                return (0)
        self._list.append(my_activity)
        return (1)

    def updateActivity(self, my_activity):
        '''
            Description: Updates an activity from _list if it already exists
            Input: self, my_activiy
                Preconditions: my_activity is an Activities type object
            Output: 0/1
                Postconditions: 1 if the activity was updated with success, 0 otherwise
        '''
        for i in self._list:
            if Activities.getID(i) == Activities.getID(my_activity):
                Activities.SetDate(i, Activities.GetDate(my_activity))
                Activities.SetTime(i, Activities.GetTime(my_activity))
                Activities.SetDescription(i, Activities.GetDescription(my_activity))
                return 1
        return 0

    def removeActivities(self, activityID):
        '''
            Description: Removes an activity from _list if it exists
            Input: self, activityID
                Preconditions: activityID is an integer > 0
            Output: 0/1
                Postconditions: 1 if the activity was removed with success, 0 otherwise
        '''
        for i in self._list:
            if Activities.getID(i) == activityID:
                self._list.remove(i)
                return 1
        return 0

    def findActivityById(self, activityID):
        '''
            Description: Finds an activity by the ID
        '''
        for i in self._list:
            if Activities.getID(i) == activityID:
                return (i)
        return (-1)

    def getActivityDate(self, anActivity):
        '''
            Description: Returns the date of an activity
        '''
        return Activities.GetDate(anActivity)

class mapRepository(object):
    def __init__(self):
        '''
            Description: Constructor for the mapping repository.
        '''
        self._map = {}
        self._undo = []
        self._redo = []

    def addmap(self, aPerson, aActivity):
        '''
            Description: Creates a map between a person and an activity
            Input: self, aPerson, aActivity
                Precondition: aPerson and aActivity are Persons and Activities objects
            Output: 0/1
                Postcondition: 1 if the map was created, 0 if it already exists
        '''
        if (aPerson in self._map):
            for i in self._map[aPerson]:
                if i == aActivity:
                    return 0
            self._map[aPerson].append(aActivity)
        else:
            self._map[aPerson] = []
            self._map[aPerson].append(aActivity)
        return 1

    def removeActivity(self, activityID):
        '''
            Description: Removes all the activityID activities from the mapping dictionary
        '''
        for i in self._map:
            j = 0
            while (j < len(self._map[i])):
                if Activities.getID(self._map[i][j]) == activityID:
                    self._map[i].pop(j)
                else:
                    j += 1

    def get_all(self):
        '''
            Description: Returns the mapping dictionary
        '''
        return self._map

    def appendUndo(self, alist):
        '''
            Description: Adds a backup for the undo in the dictionary
            Input: self, alist
                Precondition: alist is a list
            Output: 1
                Postcondition: -
        '''
        self._undo.append(alist)
        self._redo = []
        return (1)

    def computeUndo(self):
        '''
            Description: Computes the undo functionality for the map dictionary
            Input: self
                Precondition: -
            Output: 1/0
                Postcondition: 1 on success / 0 if failed
        '''
        if (len(self._undo) > 0):
            self._redo.append(self._map)
            self._map = self._undo[-1]
            self._undo.pop(-1)
            return (1)
        return (0)

    def delUndo(self):
        '''
            Description: Removes the last undo if there was an error in the system
            Input: self
                Precondition: -
            Output: 1
                Postcondition: -
        '''
        self._undo.pop(-1)
        return (1)

    def computeRedo(self, list_copy):
        '''
            Description: Computes the redo functionality
            Input: self, list_copy
                Precondition: list_copy is a list
            Output: 1/0
                Postcondition: 1 on success / 0 if failed
        '''
        if (len(self._redo) > 0):
            self._undo.append(list_copy)
            self._map = self._redo[-1]
            self._redo.pop(-1)
            return (1)
        return (0)
