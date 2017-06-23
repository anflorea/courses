'''
    Created on Dec 7, 2015

    @author: aflorea
'''

from domain.domain import *
import json

class   Repository(object):
    def __init__(self):
        self._list = []

    def __len__(self):
        return len(self._list)

    def get_all(self):
        '''
            Description: Return a list of all objects
            Input: -
            Output: _list
                Postcondition: _list is a list of objects
        '''
        return (self._list)

    def add_product(self, aProduct):
        for i in self._list:
            if (Product.get_type(i) == Product.get_type(aProduct) and
                    Product.get_brand(i) == Product.get_brand(aProduct)):
                Product.set_quantity(i, Product.get_quantity(i) +
                        Product.get_quantity(aProduct))
                return (0)
        self._list.append(aProduct)
        return (1)

    def updateQty(self, aProduct, q):
        '''
            Description: Updates the quantity for Product aProduct
            Input: aProduct, q
                Precond: aProduct is a Product type object
                        q is an integer
            Output:
        '''
        pass

    def writeToFile(self):
        my_file = open("files/filerepository.txt", "w")
        string = json.dumps(self._list, default=lambda o: o.__dict__, sort_keys=True, indent=4)
        my_file.write(string)
        my_file.close()
        return (1)

    def readFromFile(self):
        my_file = open("files/filerepository.txt", "r")
        string = my_file.read()
        my_dict = json.loads(string)
        for i in my_dict:
            aProd = Product(i['_type'], i['_brand'], i['_quantity'])
            self.add_product(aProd)
        return (1)
