"""
    Created on Oct 31, 2015

    @author: aflorea
"""

class Controller(object):
    def __init__(self, aRepository):
        self._repository = aRepository

    def addPerson(self, my_person):
        if self._repository.findPersonById(my_person.personID) == 0:
            self._repository.addPerson(my_person)
            return 1
        else:
            return 0

    def addActivity(self, my_activity):
        if self._repository.findPersonById(my_activity.personID) == 1:
            self._repository.addActivity(my_activity)
            return 1
        else:
            return 0

    def removePersonById(self, personID):
        if self._repository.findPersonById(personID) == 1:
            self._repository.removePerson(personID)
            return 1
        else:
            return 0

    def removeActivities(self, personID):
        if self._repository.findPersonById(personID) == 1:
            self._repository.removeActivities(personID)
            return 1
        else:
            return 0

    def updatePerson(self, my_person):
        if (self._repository.findPersonById(my_person.personID) == 1):
            self._repository.updatePerson(my_person)
            return 1
        else:
            return 0

    def updateActivity(self, my_activity):
        if self._repository.findPersonById(my_activity.personID) == 1:
            if self._repository.findActivityByTime(my_activity.personID, my_activity.time)  == 1:
                self._repository.updateActivity(my_activity)
                return 1
        return 0

    def get_all(self):
        list_all = self._repository.get_all()
        return (list_all)

