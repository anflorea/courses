'''
    Created on 7 dec, 2015
    Author: aflorea
'''

class   Product(object):
    def __init__(self, tyype, brand, quantity):
        self._type = tyype
        self._brand = brand
        self._quantity = quantity

    def set_quantity(self, quantity):
        self._quantity = quantity
        return (1)

    def get_quantity(self):
        return (self._quantity)

    def set_brand(self, brand):
        self._brand = brand
        return (1)

    def get_brand(self):
        return (self._brand)

    def set_type(self, tyype):
        self._type = tyype
        return (1)

    def get_type(self):
        return (self._type)

    def __repr__(self):
        return "Type: %s, Brand: %s, Quantity: %d" % (self._type, self._brand, self._quantity)
