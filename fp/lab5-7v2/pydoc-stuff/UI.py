'''
    Created on Nov 7, 2015

    @author: aflorea
'''

from os import system
from domain import *

class   UI(object):
    def __init__(self, pController, aController):
        '''
            Description: Constructor for 
        '''
        self._PersController = pController
        self._ActController = aController

    def print_sort_menu(self):
        system('clear')
        print("X============================MENU===============================X")
        print("# <1>  List the activities for a person ordered alphabetically  #")
        print("# <2>  List the activities for a person ordered by their date   #")
        print("# <3>  List the persons having activities in a certain interval,#")
        print("#                   ordered by date                             #")
        print("# <4>  List the persons having activities in a certain interval,#")
        print("#                   ordered alphabetically by their description #")
        print("# <0>  Back                                                     #")
        print("X===============================================================X\n")
    
    def print_search_activity(self):
        system('clear')
        print("X=================MENU===================X")
        print("# <1>  Search an activity by ID          #")
        print("# <2>  Search an activity by date        #")
        print("# <0>  Back                              #")
        print("X========================================X\n")

    def print_search_person(self):
        system('clear')
        print("X=================MENU===================X")
        print("# <1>  Search a person by ID             #")
        print("# <2>  Search a person by name           #")
        print("# <0>  Back                              #")
        print("X========================================X\n")

    def print_menu(self):
        system('clear')
        print("X=================MENU===================X")
        print("# <1>  Add an activity                   #")
        print("# <2>  Add a person                      #")
        print("# <3>  Remove person                     #")
        print("# <4>  Remove activity                   #")
        print("# <5>  List activities                   #")
        print("# <6>  List persons and their activities #")
        print("# <7>  Add an activity to a person       #")
        print("# <8>  Search an activity                #")
        print("# <9>  Search a person                   #")
        print("# <10> Update an activity                #")
        print("# <11> Update a person                   #")
        print("# <12> Sorting                           #")
        print("# <13> Undo                              #")
        print("# <14> Redo                              #")
        print("# <0>  Exit                              #")
        print("X========================================X\n")

    def mainMenu(self):
        '''
            Description: The main() function
        '''
        error_code = 0
        error_msg = ''
        while True:
            self.print_menu()
            if (error_code):
                print ("\t", error_msg, '\n')
            cmd = input("Please give a command: ")
            if (cmd == '0'):
                return
            elif (cmd == '1'):
                # Add an activity
                UI.appendUndo(self)
                ActivityID = UI.readID()
                date = UI.readActivityDate()
                time = UI.readActivityTime()
                description = UI.readActivityDescription()
                my_activity = Activities(ActivityID, date, time, description)
                code = self._ActController.addActivity(my_activity)
                error_code = 1
                if (code == 1):
                    error_msg = "Activity added successfuly!"
                else:
                    UI.delLastUndo(self)
                    error_msg = "Error: ID already exists!"
            elif (cmd == '2'):
                # Add a person
                UI.appendUndo(self)
                personID = UI.readID()
                name = UI.readPersonName()
                phone_number = UI.readPersonPhone()
                address = UI.readPersonAddress()
                my_person = Persons(personID, name, phone_number, address)
                code = self._PersController.addPerson(my_person)
                error_code = 1
                if (code == 1):
                    error_msg = "Person added successfuly!"
                else:
                    UI.delLastUndo(self)
                    error_msg = "Error: ID already exists!"
            elif (cmd == '3'):
                # Remove a person
                UI.appendUndo(self)
                personID = UI.readID()
                code = self._PersController.removePersonById(personID)
                error_code = 1
                if (code == 1):
                    error_msg = "Person removed successfuly!"
                else:
                    UI.delLastUndo(self)
                    error_msg = "Error: ID not found!"
            elif (cmd == '4'):
                # Remove an activity
                UI.appendUndo(self)
                ActivityID = UI.readID()
                code = self._ActController.removeActivities(ActivityID)
                self._PersController.removeActivitiesFromPers(ActivityID)
                error_code = 1
                if (code == 1):
                    error_msg = "Activity removed successfuly!"
                else:
                    UI.delLastUndo(self)
                    error_msg = "Error: ID not found!"
            elif (cmd == '5'):
                # List all activities
                system('clear')
                print ('\n\n')
                list_all = self._ActController.get_all()
                UI.print_all_list(list_all)
                cmd = input("\nPress Enter to continue.")
            elif (cmd == '6'):
                # List all persons and their activities
                system('clear')
                print ('\n\n')
                list_all = self._PersController.get_all()
                dict_all = self._PersController.get_dict()
                UI.print_all_pers(list_all, dict_all)
                cmd = input("\nPress Enter to continue.")
            elif (cmd == '7'):
                # Add an activity to a person
                UI.appendUndo(self)
                error_code = 1
                if len(self._PersController.get_all()) > 0 and len(self._ActController.get_all()) > 0:
                    print ('First, give the ID of the person.')
                    ok = True
                    while ok:
                        personID = UI.readID()
                        my_person = self._PersController.findPersonById(personID)
                        if (my_person != -1):
                            ok = False
                        else:
                            print ('Error: ID not found!')
                    print ('Give the ID of the activity.')
                    ok = True
                    while ok:
                        activityID = UI.readID()
                        my_activity = self._ActController.findActivityById(activityID)
                        if (my_activity != -1):
                            ok = False
                        else:
                            print ('Error: ID not found!')
                    code = self._PersController.addmap(my_person, my_activity)
                    if (code == 1):
                        error_msg = "Activity successfully added to the person!"
                    else:
                        UI.delLastUndo(self)
                        error_msg = "Error: Activity already added to that person!"
                elif len(self._PersController.get_all()) == 0:
                    UI.delLastUndo(self)
                    error_msg = "There are no persons yet!"
                else:
                    UI.delLastUndo(self)
                    error_msg = "There are no activities yet!"
            elif (cmd == '8'):
                # Search an activity
                self.activity_menu()
            elif (cmd == '9'):
                # Search an activity
                self.person_menu()
            elif (cmd == '10'):
                # Update an activity
                UI.appendUndo(self)
                activityID = UI.readID()
                date = UI.readActivityDate()
                time = UI.readActivityTime()
                description = UI.readActivityDescription()
                my_activity = Activities(activityID, date, time, description)
                code = self._ActController.updateActivity(my_activity)
                error_code = 1
                if (code == 1):
                    error_msg = "Activity updated successfuly!"
                else:
                    UI.delLastUndo(self)
                    error_msg = "Error: ID not found!"
            elif (cmd == '11'):
                # Update a person
                UI.appendUndo(self)
                personID = UI.readID()
                name = UI.readPersonName()
                phone_number = UI.readPersonPhone()
                address = UI.readPersonAddress()
                my_person = Persons(personID, name, phone_number, address)
                code = self._PersController.updatePerson(my_person)
                if (code == 1):
                    error_msg = "Person updated successfuly!"
                else:
                    UI.delLastUndo(self)
                    error_msg = "Error: ID not found!"
            elif (cmd == '12'):
                # Sorting
                self.sorting_menu()
            elif (cmd == '13'):
                # Undo a command
                code = UI.computeUndo(self)
                if (code == 1):
                    error_msg = "Undo: Success!"
                else:
                    error_msg = "Already at oldest change."
            elif (cmd == '14'):
                # Redo a command
                code = UI.computeRedo(self)
                if (code == 1):
                    error_msg = "Redo: Success!"
                else:
                    error_msg = "Nothing to do for Redo!"
            else:
                error_msg = "Command error!"
                error_code = 1

    @staticmethod
    def appendUndo(self):
        self._PersController.appendUndo()
        self._ActController.appendUndo()
        self._PersController.appendMapUndo()

    @staticmethod
    def delLastUndo(self):
        self._PersController.delUndo()
        self._ActController.delUndo()
        self._PersController.delMapUndo()

    @staticmethod
    def computeUndo(self):
        code = self._PersController.computeUndo()
        self._PersController.computeMapUndo()
        self._ActController.computeUndo()
        return code

    @staticmethod
    def computeRedo(self):
        code = self._PersController.computeRedo()
        self._PersController.computeMapRedo()
        self._ActController.computeRedo()
        return code

    def sorting_menu(self):
        '''
            Description: Sub-menu for sorting.
        '''
        error_code = 0
        error_msg = ''
        while True:
            self.print_sort_menu()
            if (error_code):
                print ("\t", error_msg, '\n')
            cmd = input("Please give a command: ")
            if (cmd == '0'):
                return
            elif (cmd == '1' or cmd == '2'):
                # Activities of a person
                personID = UI.readID()
                aPerson = self._PersController.findPersonById(personID)
                if (aPerson != -1):
                    alist = self._PersController.getListFromMap(aPerson)
                    if (alist != -1):
                        if (cmd == '1'):
                            # Sort the list alphabetically
                            alist.sort(key=lambda x: x.description, reverse=False)
                        elif (cmd == '2'):
                            # Sort the list by date
                            alist = self._PersController.sortByDate(alist)
                        system('clear')
                        print ('\n\n')
                        UI.print_all_list(alist)
                        cmd = input("\nPress Enter to continue.")
                    else:
                        error_code = 1
                        error_msg = "This person has no activities!"
                else:
                    error_code = 1
                    error_msg = "ID not found!"
            elif (cmd == '3' or cmd == '4'):
                # Persons that have activities in a certain interval
                print ("Please insert the starting date:")
                date1 = UI.readActivityDate()
                print ("Please insert the ending date:")
                date2 = UI.readActivityDate()
                if (self._PersController.datecmp(date1, date2) <= 0):
                    adict = self._PersController.getDictFromMap(date1, date2)
                    if (adict != -1):
                        if (cmd == '3'):
                            # Sort by date
                            for i in adict:
                                adict[i] = self._PersController.sortByDate(adict[i])
                        if (cmd == '4'):
                            # Sort alphabetically by description
                            for i in adict:
                                adict[i].sort(key=lambda x: x.description, reverse=False)
                        system('clear')
                        print('\n\n')
                        alist = self._PersController.get_all()
                        UI.print_all_pers(alist, adict)
                        cmd = input("\nPress Enter to continue.")
                    else:
                        error_code = 1
                        error_msg = "There are no persons having activities in that interval!"
                else:
                    error_code = 1
                    error_msg = "Error: The starting date should be less than or equal to the end date."
            else:
                error_msg = "Command error!"
                error_code = 1

    def activity_menu(self):
        '''
            Description: Sub-menu for searching an activity.
        '''
        error_code = 0
        error_msg = ''
        while True:
            self.print_search_activity()
            if (error_code):
                print ("\t", error_msg, '\n')
            cmd = input("Please give a command: ")
            if (cmd == '0'):
                return
            if (cmd == '1'):
                # Search an Activity by ID
                activityID = UI.readID()
                my_activity = self._ActController.findActivityById(activityID)
                if (my_activity != -1):
                    system('clear')
                    print ('\n\t', my_activity)
                    a = input ("\nPress Enter to continue.")
                    error_code = 0
                else:
                    error_code = 1
                    error_msg = "Activity not found!"
            elif (cmd == '2'):
                # Search an Activity by Date
                date = UI.readActivityDate()
                date_list = self._ActController.getActivitiesByDate(date)
                if len(date_list) > 0:
                    system('clear')
                    print ('\n')
                    UI.print_all_list(date_list)
                    a = input ("\nPress Enter to continue.")
                else:
                    error_code = 1
                    error_msg = "Activities not found!"
            else:
                error_msg = "Command error!"
                error_code = 1

    def person_menu(self):
        '''
            Description: Sub-menu for searching a person.
        '''
        error_code = 0
        error_msg = ''
        while True:
            self.print_search_person()
            if (error_code):
                print ("\t", error_msg, '\n')
            cmd = input("Please give a command: ")
            if (cmd == '0'):
                return
            elif (cmd == '1'):
                # Search a Person by ID
                personID = UI.readID()
                my_person = self._PersController.findPersonById(personID)
                if (my_person != -1):
                    system('clear')
                    print ('\n\t', my_person)
                    a = input ("\nPress Enter to continue.")
                    error_code = 0
                else:
                    error_code = 1
                    error_msg = "Person not found!"
            elif (cmd == '2'):
                # Search a person by name
                name = UI.readPersonName()
                name_list = self._PersController.getPersonsByName(name)
                if len(name_list) > 0:
                    system('clear')
                    print ('\n')
                    UI.print_all_list(name_list)
                    a = input ("\nPress Enter to continue.")
                else:
                    error_code = 1
                    error_msg = "Persons not found!"
            else:
                error_msg = "Command error!"
                error_code = 1

    @staticmethod
    def readID():
        '''
            Description: Reads an ID
            Input: -
            Output: personID
                Postcondition: personID is an integer > 0
        '''
        ok = True
        while ok:
            try:
                personID = int(input("Please give the ID: "))
                if (personID <= 0):
                    a = int('a')
                ok = False
            except:
                print ("Please insert a valid ID!")
        return personID

    @staticmethod
    def readPersonName():
        '''
            Description: Reads the person's name
            Input: -
            Output: name
                Postcondition: name is a string
        '''
        while True:
            name = input("Please give the person's name: ")
            if (len(name) > 0):
                return name

    @staticmethod
    def readPersonPhone():
        '''
            Description: Reads the person's phone number
            Input: -
            Output: phone_number
                Postcondition: phone_number is a string which contains only digits
        '''
        phone_number = 'a'
        while (not(phone_number.isdigit())):
            phone_number = input("Please give the person's phone number: ")
            if (phone_number.isdigit() == False):
                print ("Please give a valid phone number!")
        return phone_number

    @staticmethod
    def readPersonAddress():
        '''
            Description: Reads the person's address
            Input: -
            Output: address
                Postcondition: address is a string
        '''
        address = input("Please give the person's address: ")
        return address

    @staticmethod
    def readActivityTime():
        '''
            Description: Reads the Activity's time
            Input: -
            Output: time 
                Postcondition: time is a string in the format 'hh:mm'
        '''
        while True:
            try:
                hour = int(input("Please give the hour: "))
                if (hour < 0 or hour > 23):
                    a = int('a')
                minute = int(input("Please give the minute: "))
                if (minute < 0 or minute > 59):
                    a = int('a')
                return (str(hour) + ':' + str(minute))
            except ValueError:
                print ("Error: please insert valid data!")

    @staticmethod
    def readActivityDate():
        '''
            Description: Reads the Activity's date
            Input: -
            Output: date
                Postcondition: date is a string in the format 'dd.mm'
        '''
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
        '''
            Description: Reads the Activity description
            Input: -
            Output: description
                Postcondition: description is a string
        '''
        while True:
            description = input("Please give the activity's description: ")
            if (len(description) > 0):
                return description

    @staticmethod
    def print_all_list(L):
        '''
            Description: Prints the list L
            Input: L
                Precondition: L is a list of objects
            Output: -
        '''
        if len(L) > 0:
            for i in L:
                print ('\t', i)
        else:
            print ("\tNo activities in the list!")

    def print_all_pers(L, D):
        '''
            Description: Prints the list L, and all the Activities for the objects in L
            Input: L, D
                Precondition: L is a list of objects, D is a dictionary of objects
            Output: -
        '''
        if (len(L) > 0):
            for i in L:
                print ('\t', i)
                if i in D and len(D[i]) > 0:
                    print ("\t\tActivities:")
                    for j in D[i]:
                        print ('\t\t', j)
        else:
            print ("\tNo persons in the list!")
