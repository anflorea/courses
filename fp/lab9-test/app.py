'''
    Created on Dec 7, 2015

    @author: aflorea
'''

from ui.ui import *
from repository.repository import *
from controller.controller import *

aRepository = Repository()
aController = Controller(aRepository)
aUI = ui(aController)

aUI.mainMenu()
