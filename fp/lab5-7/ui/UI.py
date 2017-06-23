"""
    Created on Oct 31, 2015

    @author: aflorea
"""

from os import system
from domain.domain import *

class UI(object):
    def __init__(self, aController):
        self._controller = aController

    @staticmethod
    def printMainMenu(error_code, error_msg):
        if (error_code):
            str = error_msg + '\n'
        else:
            str = ''
        str += ' Available commands:\n\n'
        str += '\t1 - Add/remove/update a person\n'
        str += '\t2 - Add/remove an activity\n'
        str += '\t3 - List persons and activities\n'
        str += '\t0 - Exit\n'
        system('clear')
        print (str)

    @staticmethod
    def printPersonMenu():
        str = ' Available commands:\n\n'
        str += '\t1 - Add a person\n'
        str += '\t2 - Remove a person\n'
        str += '\t3 - Update a person\'s informations\n'
        str += '\t0 - Go back\n'
        system('clear')
        print (str)

    @staticmethod
    def printActivitesMenu():
        str = ' Available commands:\n\n'
        str += '\t1 - Add an activity\n'
        str += '\t2 - Remove all the activities from a person\n'
        str += '\t0 - Go back\n'
        system('clear')
        print (str)

    def mainMenu(self):
        error_code = 0
        error_msg = ''
        code = -1
        while True:
            UI.printMainMenu(error_code, error_msg)
            error_code = 0
            cmd = input("Enter command: ")
            if (cmd == '0'):
                system('clear')
                return
            elif (cmd == '1'):
                #Persons
                UI.printPersonMenu()
                cmd2 = input("Enter command: ")
                if (cmd2 == '1'):
                    personID = UI.readID()
                    name = UI.readPersonName()
                    phone_number = UI.readPersonPhone()
                    address = UI.readPersonAddress()
                    my_person = Persons(personID, name, phone_number, address)
                    code = self._controller.addPerson(my_person)
                    error_code = 1
                    if (code == 1):
                        error_msg = "Person added successfuly!"
                    else:
                        error_msg = "Error: ID already exists!"
                elif (cmd2 == '2'):
                    # Remove a person
                    personID = UI.readID()
                    code = self._controller.removePersonById(personID)
                    error_code = 1
                    if (code == 1):
                        error_msg = "Person removed successfuly!"
                    else:
                        error_msg = "Error: ID not found!"
                elif (cmd2 == '3'):
                    # Update a person
                    personID = UI.readID()
                    name = UI.readPersonName()
                    phone_number = UI.readPersonPhone()
                    address = UI.readPersonAddress()
                    aPerson = Persons(personID, name, phone_number, address)
                    code = self._controller.updatePerson(aPerson)
                    error_code = 1
                    if (code == 1):
                        error_msg = "Information updated successfuly!"
                    else:
                        error_msg = "Error: ID not found!"
            elif (cmd == '2'):
                #Activities
                UI.printActivitesMenu()
                cmd2 = input("Enter command: ")
                if (cmd2 == '1'):
                    personID = UI.readID()
                    data = UI.readActivityDate()
                    time = UI.readActivityTime()
                    description = UI.readActivityDescription()
                    my_activity = Activities(personID, data, time, description)
                    code = self._controller.addActivity(my_activity)
                    error_code = 1
                    if (code == 1):
                        error_msg = "Activity added successfuly!"
                    else:
                        error_msg = "Error: ID not found."
                elif (cmd2 == '2'):
                    #Remove activity
                    personID = UI.readID()
                    code = self._controller.removeActivities(personID)
                    error_code = 1
                    if (code == 1):
                        error_msg = "Activities removed!"
                    else:
                        error_msg = "Error: ID not found!"
                """
                elif (cmd2 == ''):
                    personID = UI.readID()
                    date = UI.readActivityDate()
                    time = UI.readActivityTime()
                    description = UI.readActivityDescription()
                    my_activity = Activities(personID, date, time, description)
                    code = self._controller.updateActivity(my_activity)
                    error_code = 1
                    if code == 1:
                        error_msg = "Activity updated successfuly!"
                    else:
                        error_msg = "Error: Activity not found."
                """
            elif (cmd == '3'):
                #List the persons and activities
                system('clear')
                print ("\n\n")
                list_all = self._controller.get_all()
                UI.print_all(list_all)
                cmd2 = input("\nPress Enter to continue.")
            else:
                error_msg = "Error: Please give a valid command!"
                error_code = 1

    @staticmethod
    def readID():
        ok = True
        while ok:
            try:
                personID = int(input("Please give the ID: "))
                ok = False
            except:
                print ("Please insert a valid ID!")
        return personID
    
    @staticmethod
    def readPersonName():
        while True:
            name = input("Please give the person's name: ")
            if (len(name) > 0):
                return name

    @staticmethod
    def readPersonPhone():
        phone_number = 'a'
        while (not(phone_number.isdigit())):
            phone_number = input("Please give the person's phone number: ")
            if (phone_number.isdigit() == False):
                print ("Please give a valid phone number!")
        return phone_number

    @staticmethod
    def readPersonAddress():
        address = input("Please give the person's address: ")
        return address

    @staticmethod
    def readActivityTime():
        while True:
            try:
                hour = int(input("Please give the hour: "))
                if (hour < 0 or hour > 24):
                    a = int('a')
                minute = int(input("Please give the minute: "))
                if (minute < 0 or minute > 60):
                    a = int('a')
                return (str(hour) + ':' + str(minute))
            except ValueError:
                print ("Error: please insert valid data!")

    @staticmethod
    def readActivityDate():
        while True:
            try:
                month = int(input("Please give the month: "))
                if (month < 1 or month > 12):
                    a = int('a')
                day = int(input("Please give the day: "))
                if (day < 1 or day > 31):
                    a = int('a')
                return (str(day) + '.' + str(month))
            except ValueError:
                print ("Error: please insert valid data!")

    @staticmethod
    def readActivityDescription():
        while True:
            description = input("Please give the activity's description: ")
            if (len(description) > 0):
                return description

    @staticmethod
    def print_all(L):
        if len(L) > 0:
            for i in L:
                print (i)
                if (len(i.activities) > 0):
                    print ("\tActivities:")
                    for j in i.activities:
                        print ("\t\t", j)
        else:
            print ("No persons in the list!")
            

