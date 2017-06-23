'''
    Created on Nov 7, 2015

    @author: aflorea
'''

from domain.domain import *
from repository.repo import *
from controller.controller import *

aRepo = ActivityRepository()
pRepo = PersonsRepository()
mRepo = mapRepository()
PersCtr = PersonController(pRepo, mRepo)

def testPersons():
    my_person = Persons(3, 'John', '222222', 'Address')
    assert Persons.getID(my_person) == 3
    assert Persons.GetName(my_person) == 'John'
    assert Persons.GetPhone_number(my_person) == '222222'
    assert Persons.GetAddress(my_person) == 'Address'
    Persons.SetName(my_person, 'Other John')
    assert Persons.GetName(my_person) == 'Other John'
    Persons.SetPhone_number(my_person, '911')
    assert Persons.GetPhone_number(my_person) == '911'
    Persons.SetAddress(my_person, 'Police station')
    assert Persons.GetAddress(my_person) == 'Police station'

def testActivities():
    my_activity = Activities(4, '2.6', '12:42', 'Fun description')
    assert Activities.getID(my_activity) == 4
    assert Activities.GetDate(my_activity) == '2.6'
    assert Activities.GetTime(my_activity) == '12:42'
    assert Activities.GetDescription(my_activity) == 'Fun description'
    Activities.SetDate(my_activity, '1.12')
    assert Activities.GetDate(my_activity) == '1.12'
    Activities.SetTime(my_activity, '13:12')
    assert Activities.GetTime(my_activity) == '13:12'
    Activities.SetDescription(my_activity, 'desc')
    assert Activities.GetDescription(my_activity) == 'desc'

def testAddPerson():
    alist = PersonsRepository()
    person1 = Persons(3, 'John', '222222', 'Address')
    person2 = Persons(4, 'Lawi', '323232', 'Another Address')
    person3 = Persons(2, "Lil' Johny", '123123', 'Addr')
    assert alist.addPerson(person1) == 1
    assert alist.addPerson(person2) == 1
    assert alist.addPerson(person1) == 0
    assert alist.addPerson(person2) == 0
    assert alist.addPerson(person3) == 1

def testUpdatePerson():
    alist = PersonsRepository()
    person1 = Persons(3, 'John', '222222', 'Address')
    person2 = Persons(4, 'Lawi', '323232', 'Another Address')
    person3 = Persons(3, "Lil' Johny", '123123', 'Addr')
    alist.addPerson(person1)
    assert alist.updatePerson(person3) == 1
    assert alist.updatePerson(person2) == 0

def testCheckPersonById():
    alist = PersonsRepository()
    person1 = Persons(3, 'John', '222222', 'Address')
    person2 = Persons(4, 'Lawi', '323232', 'Another Address')
    person3 = Persons(2, "Lil' Johny", '123123', 'Addr')
    alist.addPerson(person1)
    alist.addPerson(person2)
    alist.addPerson(person3)
    assert alist.checkPersonById(3) == 1
    assert alist.checkPersonById(1) == 0
    assert alist.checkPersonById(2) == 1
    assert alist.checkPersonById(4) == 1
    assert alist.checkPersonById(9) == 0
    assert alist.checkPersonById(125) == 0

def testFindPersonById():
    alist = PersonsRepository()
    person1 = Persons(3, 'John', '222222', 'Address')
    person2 = Persons(4, 'Lawi', '323232', 'Another Address')
    person3 = Persons(2, "Lil' Johny", '123123', 'Addr')
    alist.addPerson(person1)
    alist.addPerson(person2)
    alist.addPerson(person3)
    assert alist.findPersonById(3) == person1
    assert alist.findPersonById(9) == -1
    assert alist.findPersonById(2) == person3
    assert alist.findPersonById(4) == person2
    assert alist.findPersonById(13) == -1
    assert alist.findPersonById(223) == -1

def testGet_all():
    alist = PersonsRepository()
    person1 = Persons(3, 'John', '222222', 'Address')
    person2 = Persons(4, 'Lawi', '323232', 'Another Address')
    person3 = Persons(2, "Lil' Johny", '123123', 'Addr')
    alist.addPerson(person1)
    alist.addPerson(person2)
    alist.addPerson(person3)
    assert len(alist.get_all()) == 3

def testAddActivity():
    alist = ActivityRepository()
    activity1 = Activities(1, '2.3', '12:42', 'description')
    activity2 = Activities(5, '1.8', '15:26', 'description2')
    activity3 = Activities(7, '5.6', '18:12', 'description3')
    assert alist.addActivity(activity1) == 1
    assert alist.addActivity(activity2) == 1
    assert alist.addActivity(activity1) == 0
    assert alist.addActivity(activity2) == 0
    assert alist.addActivity(activity3) == 1
    assert alist.addActivity(activity1) == 0

def testUpdateActivity():
    alist = ActivityRepository()
    activity1 = Activities(1, '2.3', '12:42', 'description')
    activity2 = Activities(5, '1.8', '15:26', 'description2')
    activity3 = Activities(1, '5.6', '18:12', 'description3')
    assert alist.updateActivity(activity1) == 0
    alist.addActivity(activity1)
    assert alist.updateActivity(activity3) == 1
    assert alist.updateActivity(activity2) == 0

def testRemoveActivities():
    alist = ActivityRepository()
    activity1 = Activities(1, '2.3', '12:42', 'description')
    activity2 = Activities(5, '1.8', '15:26', 'description2')
    activity3 = Activities(7, '5.6', '18:12', 'description3')
    alist.addActivity(activity1)
    alist.addActivity(activity2)
    alist.addActivity(activity3)
    assert alist.removeActivities(5) == 1
    assert alist.removeActivities(5) == 0
    assert alist.removeActivities(1) == 1
    assert alist.removeActivities(9) == 0
    assert alist.removeActivities(7) == 1
    assert alist.removeActivities(20) == 0

def testFindActivityById():
    alist = ActivityRepository()
    activity1 = Activities(1, '2.3', '12:42', 'description')
    activity2 = Activities(5, '1.8', '15:26', 'description2')
    activity3 = Activities(7, '5.6', '18:12', 'description3')
    alist.addActivity(activity1)
    alist.addActivity(activity2)
    alist.addActivity(activity3)
    assert alist.findActivityById(1) == activity1
    assert alist.findActivityById(20) == -1
    assert alist.findActivityById(7) == activity3
    assert alist.findActivityById(2) == -1
    assert alist.findActivityById(5) == activity2
    assert alist.findActivityById(121) == -1

def testGet_allActivities():
    alist = ActivityRepository()
    activity1 = Activities(1, '2.3', '12:42', 'description')
    activity2 = Activities(5, '1.8', '15:26', 'description2')
    activity3 = Activities(7, '5.6', '18:12', 'description3')
    assert len(alist.get_all()) == 0
    alist.addActivity(activity1)
    alist.addActivity(activity2)
    assert len(alist.get_all()) == 2
    alist.addActivity(activity3)
    assert len(alist.get_all()) == 3

def testAddmap():
    activity1 = Activities(1, '2.3', '12:42', 'description')
    activity2 = Activities(5, '1.8', '15:26', 'description2')
    activity3 = Activities(7, '5.6', '18:12', 'description3')
    person1 = Persons(3, 'John', '222222', 'Address')
    person2 = Persons(4, 'Lawi', '323232', 'Another Address')
    person3 = Persons(2, "Lil' Johny", '123123', 'Addr')
    amap = mapRepository()
    assert amap.addmap(person1, activity1) == 1
    assert amap.addmap(person1, activity2) == 1
    assert amap.addmap(person1, activity1) == 0
    assert amap.addmap(person2, activity1) == 1
    assert amap.addmap(person2, activity2) == 1
    assert amap.addmap(person2, activity3) == 1
    assert amap.addmap(person2, activity1) == 0
    assert amap.addmap(person2, activity3) == 0
    assert amap.addmap(person3, activity1) == 1
    assert amap.addmap(person3, activity2) == 1
    assert amap.addmap(person3, activity1) == 0

def testGetActivitiesByDate():
    alist = ActivityRepository()
    activity1 = Activities(1, '2.3', '12:42', 'description')
    activity2 = Activities(5, '1.8', '15:26', 'description2')
    activity3 = Activities(7, '5.6', '18:12', 'description3')
    activity4 = Activities(9, '2.3', '18:12', 'description3')
    activity5 = Activities(10, '2.3', '18:12', 'description3')
    alist.addActivity(activity1)
    alist.addActivity(activity2)
    alist.addActivity(activity3)
    alist.addActivity(activity4)
    alist.addActivity(activity5)
    assert len(alist.getActivitiesByDate('2.3')) == 3
    assert len(alist.getActivitiesByDate('5.6')) == 1
    assert len(alist.getActivitiesByDate('1.8')) == 1
    assert len(alist.getActivitiesByDate('2.2')) == 0

def testGetPersonsByName():
    alist = PersonController()
    person1 = Persons(3, 'John', '222222', 'Address')
    person2 = Persons(4, 'Lawi', '323232', 'Another Address')
    person3 = Persons(2, "Lil' Johny", '123123', 'Addr')
    person4 = Persons(8, "Florea", '112354323123', 'Ad123dr')
    person5 = Persons(9, "Florin", '12312123', 'Addr123s')
    person6 = Persons(15, "Lelea Floare", '12431223', 'Addr3w3')
    alist.addPerson(person1)
    alist.addPerson(person2)
    alist.addPerson(person3)
    alist.addPerson(person4)
    alist.addPerson(person5)
    alist.addPerson(person6)
    assert len(alist.getPersonsByName('Lawi')) == 1
    assert len(alist.getPersonsByName('No name')) == 0
    assert len(alist.getPersonsByName('Flo')) == 3
    assert len(alist.getPersonsByName('John')) == 2
    assert len(alist.getPersonsByName('Floare')) == 1
    assert len(alist.getPersonsByName('#coolname')) == 0

def testDatecmp():
    assert PersCtr.datecmp('2.4','2.4') == 0
    assert PersCtr.datecmp('1.4','2.4') < 0
    assert PersCtr.datecmp('2.9','2.4') > 0
    assert PersCtr.datecmp('1.4','7.9') < 0
    assert PersCtr.datecmp('20.10','2.4') > 0

def testComputeUndo():
    alist = PersonsRepository()
    person1 = Persons(3, 'John', '222222', 'Address')
    person2 = Persons(4, 'Lawi', '323232', 'Another Address')
    person3 = Persons(2, "Lil' Johny", '123123', 'Addr')
    person4 = Persons(8, "Florea", '112354323123', 'Ad123dr')
    person5 = Persons(9, "Florin", '12312123', 'Addr123s')
    alist.addPerson(person1)
    alist.appendUndo(alist._list)
    alist.addPerson(person2)
    alist.addPerson(person3)
    alist.addPerson(person4)
    alist.addPerson(person5)
    alist.computeUndo()
    assert len(alist._undo) == 0
    alist.computeUndo()
    assert len(alist._undo) == 0
    alist.computeUndo()
    assert len(alist._undo) == 0
    alist.computeUndo()
    assert len(alist._undo) == 0
    alist.computeUndo()
    assert len(alist._undo) == 0
   
def testComputeRedo():
    alist = PersonsRepository()
    person1 = Persons(3, 'John', '222222', 'Address')
    person2 = Persons(4, 'Lawi', '323232', 'Another Address')
    person3 = Persons(2, "Lil' Johny", '123123', 'Addr')
    person4 = Persons(8, "Florea", '112354323123', 'Ad123dr')
    person5 = Persons(9, "Florin", '12312123', 'Addr123s')
    alist.addPerson(person1)
    alist.appendUndo(alist._list)
    alist.addPerson(person2)
    alist.addPerson(person3)
    alist.addPerson(person4)
    alist.addPerson(person5)
    alist.computeUndo()
    assert len(alist._undo) == 0
    alist.computeUndo()
    assert len(alist._undo) == 0
    alist.computeUndo()
    assert len(alist._undo) == 0
    alist.computeRedo(alist._list)
    assert len(alist._redo) == 0
    alist.computeRedo(alist._list)
    assert len(alist._redo) == 0

def testsortByDate():
    new_list = []
    activity1 = Activities(1, '2.3', '12:42', 'description')
    activity2 = Activities(5, '1.8', '15:26', 'description2')
    activity3 = Activities(7, '5.6', '18:12', 'description3')
    activity4 = Activities(9, '2.3', '18:12', 'description3')
    activity5 = Activities(10, '5.3', '18:12', 'description3')
    new_list.append(activity1)
    new_list.append(activity2)
    new_list.append(activity3)
    new_list.append(activity4)
    new_list.append(activity5)
    new_list = PersCtr.sortByDate(new_list)
    assert len(new_list) == 5
    assert new_list[0] == activity1
    assert new_list[1] == activity4
    assert new_list[2] == activity5
    assert new_list[3] == activity3
    assert new_list[4] == activity2

def runTests():
    testPersons()
    testActivities()
    testAddPerson()
    testUpdatePerson()
    testCheckPersonById()
    testFindPersonById()
    testGet_all()
    testAddActivity()
    testUpdateActivity()
    testRemoveActivities()
    testFindActivityById()
    testGet_allActivities()
    testAddmap()
    testDatecmp()
    testComputeUndo()
    testComputeRedo()
    testsortByDate()
    print ("All tests passed successfuly!")

runTests()
