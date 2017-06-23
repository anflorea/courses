"""
    Created on Oct 31, 2015

    @author: aflorea
"""

class Persons(object):
    def __init__(self, personID, name, phone_number, address):
        """
            Description: Constructor for Persons class
            Input: personID (int), name (string), phone_number (string), address (string)
        """
        self.personID = personID
        self.name = name
        self.phone_number = phone_number
        self.address = address
        self.activities = []

    def SetName(self, name):
        self.name = name;

    def GetName(slef):
        return self.name

    def SetPhone_number(self, phone_number):
        self.phone_number = phone_number

    def GetPhone_number(self):
        return self.phone_number

    def SetAddress(self, address):
        self.address = address

    def GetAddress(self):
        return self.address

    def SetActivities(self, activity):
        self.activities.append(activity)

    def __repr__(self):
        return "ID: %d, Name: %s, Phone number: %s, Address: %s" % (self.personID, self.name, self.phone_number, self.address)
    

class Activities(object):
    def __init__(self, personID, date, time, description):
        """
            Description: Constructor for Activities class
            Input: personID (int), date (string (dd:mm:yyyy)), time (string (hh:mm)), description (string)
        """
        self.personID = personID
        self.date = date
        self.time = time
        self.description = description

    def __repr__(self):
        minute = int(self.time.split(':')[0])
        hour = int(self.time.split(':')[1])
        return "%s at %02d:%02d on %s" % (self.description, minute, hour, self.date)

    def SetDate(self, date):
        self.date = date

    def GetDate(self):
        return self.date

    def SetTime(self, time):
        self.time = time

    def GetTime(self):
        return self.time

    def SetDescription(self, description):
        self.descriprion = description

    def GetDescription(self):
        return self.description
