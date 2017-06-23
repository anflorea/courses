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

    def getID(self):
        '''
            Description: getter for the person ID
        '''
        return self.personID

    def SetName(self, name):
        '''
            Description: setter for the person name
        '''
        self.name = name;

    def GetName(self):
        '''
            Description: getter for the person name
        '''
        return self.name

    def SetPhone_number(self, phone_number):
        '''
            Description: setter for the person phone number
        '''
        self.phone_number = phone_number

    def GetPhone_number(self):
        '''
            Description: getter for the person phone number
        '''
        return self.phone_number

    def SetAddress(self, address):
        '''
            Description: setter for the person address
        '''
        self.address = address

    def GetAddress(self):
        '''
            Description: getter for the person address
        '''
        return self.address

    def __repr__(self):
        '''
            Description: Overrides the repr function for Persons type objects
        '''
        return "ID: %d, Name: %s, Phone number: %s, Address: %s" % (self.personID, self.name, self.phone_number, self.address)


class Activities(object):
    def __init__(self, ID, date, time, description):
        """
            Description: Constructor for Activities class
            Input: personID (int), date (string (dd:mm:yyyy)), time (string (hh:mm)), description (string)
        """
        self.ID = ID
        self.date = date
        self.time = time
        self.description = description

    def __repr__(self):
        '''
            Description: Overrides the repr function for Activities type objects
        '''
        minute = int(self.time.split(':')[0])
        hour = int(self.time.split(':')[1])
        return "ID: %d -- %s at %02d:%02d on %s" % (self.ID, self.description, minute, hour, self.date)

    def getID(self):
        '''
            Description: getter for the activity ID
        '''
        return self.ID

    def SetDate(self, date):
        '''
            Description: setter for the activity date
        '''
        self.date = date

    def GetDate(self):
        '''
            Description: getter for the activity date
        '''
        return self.date

    def SetTime(self, time):
        '''
            Description: setter for the activity time
        '''
        self.time = time

    def GetTime(self):
        '''
            Description: getter for the activity time
        '''
        return self.time

    def SetDescription(self, description):
        '''
            Description: setter for the activity description
        '''
        self.description = description

    def GetDescription(self):
        '''
            Description: getter for the activity description
        '''
        return self.description
