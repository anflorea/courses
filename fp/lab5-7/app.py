"""
    Created on Oct 31, 2015

    @author: aflorea
"""

from ui.UI import *
from repository.repo import *
from controller.controller import *

aRepository = InMemoryRepository()
aController = Controller(aRepository)
aUI = UI(aController)

aUI.mainMenu()
