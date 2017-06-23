'''
    Created on Nov 7, 2015

    @author: aflorea
'''

from UI import *
from repo import *
from controller import *

pRepository = PersonsRepository()
aRepository = ActivityRepository()
mRepository = mapRepository()
pController = PersonController(pRepository, mRepository)
aController = ActivityController(aRepository)
aUI = UI(pController, aController)

aUI.mainMenu()
