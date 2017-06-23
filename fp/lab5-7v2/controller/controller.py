"""
    Created on Oct 31, 2015

    @author: aflorea
"""

from domain.domain import *
from sort import *

class PersonController(object):
    def __init__(self, aRepository, mRepository):
        '''
            Description: Constructor for the persons controller. 
        '''
        self._repository = aRepository
        self._maprepo = mRepository

    def readMapFromFile(self, pers_list):
        return (self._maprepo.readFromFile(pers_list))

    def writeMapToFile(self):
        return (self._maprepo.writeToFile())

    def writeToFile(self):
        return (self._repository.writeToFile())

    def readFromFile(self):
        return (self._repository.readFromFile())

    def addPerson(self, my_person):
        '''
            Description: Add a new person to the repository
            Input: self, my_person
                Precondition: my_person is a Persons type object
            Output: 
                Postcondition: error code: 0/1
        '''
        return self._repository.addPerson(my_person)

    def removePersonById(self, personID):
        '''
            Description: Remove a person from the repository
            Input: self, personID
                Precondition: personID is an integer
            Output:
                Postcondition: error code: 0/1
        '''
        return self._repository.removePerson(personID)

    def updatePerson(self, my_person):
        '''
            Description: Update a person from the repository
            Input: self, my_person
                Precondition: my_person is a Persons type object
            Output:
                Postcondition: error code: 0/1
        '''
        return self._repository.updatePerson(my_person)

    def findPersonById(self, personID):
        '''
            Description: Finds a person by the given ID
            Input: self, personID
                Precondition: personID is an integer > 0
            Output:
                Postcondition: error code: 0/1
        '''
        return self._repository.findPersonById(personID)

    def checkPersonById(self, personID):
        '''
            Description: Finds a person by the given ID
            Input: self, personID
                Precondition: personID is an integer > 0
            Output:
                Postcondition: error code: 0/1
        '''
        return self._repository.checkPersonById(personID)

    def getPersonsByName(self, name):
        '''
            Description: Finds persons by name
            Input: self, name
                Precondition: name is a string
            Output: pers_list
                Postcondition: a list of persons
        '''
        list_all = self._repository.get_all()
        pers_list = []
        for i in list_all:
            if name in self._repository.getPersonName(i):
                pers_list.append(i)
        return pers_list

    def get_all(self):
        '''
            Description: Return a list of all the Persons in the repository.
            Input: self
                Precondition: -
            Output: list_all
                Postcondition: list_all is a list of Persons type objects
        '''
        list_all = self._repository.get_all()
        return (list_all)

    def removeActivitiesFromPers(self, ID):
        '''
            Description: Removes all the activities with the given Id, from all the persons
        '''
        self._maprepo.removeActivity(ID)

    def addmap(self, aPerson, aActivity):
        '''
            Description: creates a map between a person and an activity
        '''
        return self._maprepo.addmap(aPerson, aActivity)

    def get_dict(self):
        '''
            Description: Returns the mapping dictionary.
        '''
        return self._maprepo.get_all()

    def getListFromMap(self, aPerson):
        '''
            Description: Returns the list of activities of a Person, or -1 in case of error
            Input: self, aPerson
                Precondition: aPerson is a Persons type object
            Output: new_list/ -1
                Postcondition: new_list is the list of activities of aPerson
        '''
        aMap = self._maprepo.get_all()
        if (aPerson in aMap):
            new_list = []
            for i in aMap[aPerson]:
                new_list.append(i)
            return (new_list)
        else:
            return (-1)

    def getDictFromMap(self, date1, date2):
        '''
            Description: Returns a Dictionary of all Persons that have activities in the [date1, date2] interval
            Input: self, date1, date2
                Precondition: date1 and date2 are dates
            Output: new_dict
                Postcondition: new_dict is a dictionary of lists (of Activities) having Persons as keys
        '''
        adict = self._maprepo.get_all()
        new_dict = {}
        for i in adict:
            for j in adict[i]:
                date = Activities.GetDate(j)
                if (self.datecmp(date, date1) >= 0 and self.datecmp(date, date2) <= 0):
                    if not(i in new_dict):
                        new_dict[i] = []
                    new_dict[i].append(j)
        return (new_dict)

    def sortByDate(self, alist):
        '''
            Description: Performs a sorting alghorithm on a list, using the date as the sorting key
            Input: alist
                Precondition: alist is a list of Activities type objects
            Output: alist
                Postcondition: -
        ok = 1
        while ok:
            ok = 0
            i = 0
            while (i < len(alist) - 1):
                if (sort.datecmp(alist[i].date, alist[i + 1].date) > 0):
                    aux = alist[i]
                    alist[i] = alist[i + 1]
                    alist[i + 1] = aux
                    ok = 1
                i += 1
        return (alist)
        '''
        return (sort.shakeSort(alist))

    def datecmp(self, date1, date2):
        '''
            Description: Compares date1 and date2
            Input: self, date1, date2
                Precondition: date1 and date2 are dates
            Output: date1 - date2
                Postcondition: If date1 > date2, a > 0 number is returned/ 0 is returned if they are equal/ 1 is returned otherwise
        '''
        if (date1 == date2):
            return (0)
        date1 = date1.split('.')
        day1 = int(date1[0])
        month1 = int(date1[1])
        date2 = date2.split('.')
        day2 = int(date2[0])
        month2 = int(date2[1])
        if (month1 == month2):
            return (day1 - day2)
        else:
            return (month1 - month2)

    def appendUndo(self):
        '''
            Description: Adds an undo backup to the Persons repository
            Input: self
                Precondition: -
            Output: - 
        '''
        list_all = self._repository.get_all()
        list_copy = []
        for i in list_all:
            list_copy.append(i)
        return self._repository.appendUndo(list_copy)

    def computeUndo(self):
        '''
            Description: Computes the undo functionality for the Persons Repository
            Input: self
                Precondition: - 
            Output: -
        '''
        return self._repository.computeUndo()

    def delUndo(self):
        '''
            Description: Removes the last undo from the Persons Repository in case of an error in the system
            Input: self
                Precondition: -
            Output: - 
        '''
        return self._repository.delUndo()

    def computeRedo(self):
        '''
            Description: Computes the Redo functionality for the Persons Repository
            Input: self
                Precondition: -
            Output: -
        '''
        list_all = self._repository.get_all()
        list_copy = []
        for i in list_all:
            list_copy.append(i)
        return self._repository.computeRedo(list_copy)

    def appendMapUndo(self):
        '''
            Description: Adds an undo backup to the map dictionary
            Input: self
                Precondition: -
            Output: -
        '''
        list_all = self._maprepo.get_all()
        list_copy = {}
        for i in list_all:
            list_copy[i] = []
            for j in list_all[i]:
                list_copy[i].append(j)
        return self._maprepo.appendUndo(list_copy)

    def computeMapUndo(self):
        '''
            Description: Computes the undo functionality for the map dictionary
            Input: self
                Precondition: -
            Output: -
        '''
        return self._maprepo.computeUndo()

    def delMapUndo(self):
        '''
            Description: Removes the last undo from the map dictionary in case of an error in the system
            Input: self
            Output: -
        '''
        return self._maprepo.delUndo()

    def computeMapRedo(self):
        '''
            Description: Computes the Redo functionality for the Map Dictionary
            Input: self
            Output: -
        '''
        list_all = self._maprepo.get_all()
        list_copy = {}
        for i in list_all:
            list_copy[i] = []
            for j in list_all[i]:
                list_copy[i].append(j)
        return self._maprepo.computeRedo(list_copy)

class ActivityController(object):
    def __init__(self, aRepository):
        '''
            Description: Constructor for the activities repository
        '''
        self._arepository = aRepository
        
    def writeToFile(self):
        return (self._arepository.writeToFile())

    def readFromFile(self):
        return (self._arepository.readFromFile())

    def addActivity(self, my_activity):
        '''
            Description: Adds an activity to the repository
            Input: self, my_activity
                Precondition: my_activity is an Activities type object
            Output:
                Postcondition: error code: 0/1
        '''
        return self._arepository.addActivity(my_activity)

    def removeActivities(self, activityID):
        '''
            Description: Removes an activity from the repository
            Input: self, activityID
                Precondition: activityID is an integer
            Output:
                Postcondition: error code: 0/1
        '''
        return self._arepository.removeActivities(activityID)

    def updateActivity(self, my_activity):
        '''
            Description: Updates an activity from the repository
            Input: self, my_activity
                Precondition: my_activity is an Activities type object
            Output:
                Postcondition: error code: 0/1
        '''
        return self._arepository.updateActivity(my_activity)

    def findActivityById(self, activityID):
        '''
            Description: Finds an activity by the ID
            Input: self, activityID
                Precondition: activityID is an integer > 0
            Output:
                Postcondition: error code: 0/1
        '''
        return self._arepository.findActivityById(activityID)

    def getActivitiesByDate(self, date):
        '''
            Description: Finds all the activities at a given date
            Input: self, date
                Precondition: date is a string (dd.mm)
            Output: alist
                Postcondition: a list of all the activities at that date
        '''
        list_all = self._arepository.get_all()
        alist = []
        for i in list_all:
            if self._arepository.getActivityDate(i) == date:
                alist.append(i)
        return alist

    def get_all(self):
        '''
            Description: Returns a list of all the activities in the repository
            Input: self
                Precondition: -
            Output: list_all
                Postcondition: list_all is a list of Activities type objects
        '''
        list_all = self._arepository.get_all()
        return (list_all)

    def appendUndo(self):
        '''
            Description: Creates a new undo backup for the Activities Repository whenever a command is introduced
            Input: slef
                Precondition: -
            Output: -
        '''
        list_all = self._arepository.get_all()
        list_copy = []
        for i in list_all:
            list_copy.append(i)
        return self._arepository.appendUndo(list_copy)

    def delUndo(self):
        '''
            Description: Removes the last undo, in case of an error in the system
            Input: self 
                Precondition: -
            Output: -
        '''
        return self._arepository.delUndo()

    def computeUndo(self):
        '''
            Description: Computes the Undo functionality for the Activities Repository
            Input: self
                Precondition: -
            Output: 1/0
                Postcondition:
        '''
        return self._arepository.computeUndo()

    def computeRedo(self):
        '''
            Description: Computes the Redo functionality for the Activities Repository
            Input: self
                Precondition: -
            Output: 1/0
                Postcondition: 
        '''
        list_all = self._arepository.get_all()
        list_copy = []
        for i in list_all:
            list_copy.append(i)
        return self._arepository.computeRedo(list_copy)
