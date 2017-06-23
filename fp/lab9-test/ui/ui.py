'''
    Created on Dec 7, 2015

    @author: aflorea
'''

from os import system
from domain.domain import *

class ui(object):
    def __init__(self, aController):
        self._aController = aController

    def print_menu(self):
        system('clear')
        print("/'''''''''''''''''''''''''''''''''''''''''''\\")
        print("| 1. Add a product                          |")
        print("| 2. Update quantity                        |")
        print("| 3. Find products by type                  |")
        print("| 4. Show all the products                  |")
        print("| 0. Exit the program                       |")
        print("\\,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,/")

    def mainMenu(self):
        errno = 0
        err_msg = ""
        self._aController.readFromFile()
        while True:
            self.print_menu()
            if (errno):
                print("\n\t", err_msg, '\n')
            cmd = input("Give a command: ")
            if (cmd == '0'):
                self._aController.writeToFile()
                return
            elif (cmd == '1'):
                aType = ui.readType()
                aBrand = ui.readBrand()
                aQuantity = ui.readQuantity()
                aProduct = Product(aType, aBrand, aQuantity)
                self._aController.add_product(aProduct)
                self._aController.writeToFile()
            elif (cmd == '2'):
                aType = ui.readType()
                aBrand = ui.readBrand()
                aQuantity = ui.readQuantity()
                ret = self._aController.decreaseQty(aType, aBrand, aQuantity)
                if (ret == 1):
                    self._aController.writeToFile()
                if (ret == 0):
                    err_msg = "Product not found!"
                    errno = 1
                if (ret == 2):
                    err_msg = "Not enough products in stock!"
                    errno = 1
            elif (cmd == '3'):
                aType = ui.readType()
                ret = self._aController.filterByType(aType)
                ui.print_list(ret)
            elif (cmd == '4'):
                list_all = self._aController.get_all()
                ui.print_list(list_all)
            else:
                errno = 1
                err_msg = "Invalid command!"

    @staticmethod
    def readType():
        aType = ""
        while (len(aType) == 0):
            aType = input("Please insert the product's type: ")
        return (aType)
        
    @staticmethod
    def readBrand():
        aBrand = ""
        while (len(aBrand) == 0):
            aBrand = input("Please insert the product's brand: ")
        return aBrand

    @staticmethod
    def readQuantity():
        ok = True
        while ok:
            try:
                aQty = int(input("Please insert the quantity: "))
                if (aQty <= 0):
                    raise
                ok = False
            except:
                print("Please insert a valid quantity!")
        return (aQty)

    @staticmethod
    def print_list(alist):
        system('clear')
        print("\n\n")
        for i in alist:
            print("\t\t", i)
        if (len(alist) == 0):
            print ("\t\tThere are no items in the list!")
        print("\n")
        a = input("Press Enter to go back.")
