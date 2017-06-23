"""
    Created on Oct 31, 2015

    @author: aflorea
"""

from domain.domain import *

class InMemoryRepository(object):
    def __init__(self):
        self._list = []

    def __len__(self):
        return len(self._list)

    def addPerson(self, aPerson):
        self._list.append(aPerson)

    def updatePerson(self, my_person):
        for i in self._list:
            if i.personID == my_person.personID:
                Persons.SetName(i, my_person.name)
                Persons.SetPhone_number(i, my_person.phone_number)
                Persons.SetAddress(i, my_person.address)

    def addActivity(self, my_activity):
        for i in self._list:
            if i.personID == my_activity.personID:
                i.activities.append(my_activity)

    def updateActivity(self, my_activity):
        for i in self._list:
            if i.personID == my_activity.personID:
                for j in i.activities:
                    if j.time == my_activity.time:
                        Activities.SetTime(j, my_activity.time)
                        Activities.SetDate(j, my_activity.date)
                        Activities.SetDescription(j, my_activity.description)

    def findPersonById(self, personID):
        for i in self._list:
            if i.personID == personID:
                return 1
        return 0

    def findActivityByTime(self, personID, time):
        for i in self._list:
            if i.personID == personID:
                for j in i.activities:
                    if j.time == time:
                        return 1
        return 0

    def get_all(self):
        return self._list

    def removePerson(self, personID):
        for i in self._list:
            if i.personID == personID:
                self._list.remove(i)

    def removeActivities(self, personID):
        for i in self._list:
            if i.personID == personID:
                while (len(i.activities) > 0):
                    i.activities.pop(0)

    def removeAll(self):
        while len(slef._list) > 0:
            self._list.pop()

