'''
    Created on Dec 7, 2015

    @author: aflorea
'''

from domain.domain import *

class Controller(object):
    def __init__(self, aRepository):
        self._aRepository = aRepository

    def add_product(self, aProduct):
        return (self._aRepository.add_product(aProduct))

    def get_all(self):
        return (self._aRepository.get_all())

    def writeToFile(self):
        return (self._aRepository.writeToFile())

    def readFromFile(self):
        return (self._aRepository.readFromFile())

    def add_product(self, aProduct):
        return (self._aRepository.add_product(aProduct))

    def decreaseQty(self, aType, aBrand, q):
        '''
            Description: Descreases the quantity of a Product by q
            Input: aType, aBrand, q
                Preconditions: aType and aBrand are strings, q is an integer
            Output: 0, 1 or 2
                Postconditions: 0 if the prod was not fonud,
                                2 if q is too big
                                1 on success
        '''
        alist = self._aRepository.get_all()
        aProduct = Product(aType, aBrand, q)
        for i in alist:
            if (Product.get_type(i) == aType and
                    Product.get_brand(i) == aBrand):
                if (q > Product.get_quantity(i)):
                    return (2)
                Product.set_quantity(i, Product.get_quantity(i) - q)
                # TODO self._aRepository.updateQty(aProduct, q)
                return (1)
        return (0)

    def filterByType(self, aType):
        '''
            Description: Return a list of all the products of a specified type
            Input: aType
                Precondition: aType is a string
            Output: new_list
                Postcondition: new_list is a list of Product type objects
        '''
        alist = self._aRepository.get_all()
        new_list = []
        for i in alist:
            if (Product.get_type(i) == aType):
                new_list.append(i)
        new_list.sort(key=lambda x: x._quantity, reverse=False)
        return new_list
