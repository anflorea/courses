'''
    Created on Jan 5, 2016

    @author: aflorea
'''

class   sort(object):

    def datecmp(date1, date2):
        '''
            Description: Compares date1 and date2
            Input: date1, date2
                Precondition: date1 and date2 are dates
            Output: date1 - date2
                Postcondition: if date1 > date2, a > 0 number is returned/ 0 is returned if they are equal/ a < 0 number is returned otherwise
        '''
        if(date1 == date2):
            return (0)
        date1 = date1.split('.')
        day1 = int(date1[0])
        month1 = int(date1[1])
        date2 = date2.split('.')
        day2 = int(date2[0])
        month2 = int(date2[1])
        if (month1 == month2):
            return (day1 - day2)
        else:
            return (month1 - month2)

    def bubbleSort(alist):
        '''
            Description: Performs a bubbleSort algorithm on the list alist
            Input: alist
                Precondition: alist is a list of Activities type object
            Output: alist
                Postcondition: - 
        '''
        ok = 1
        while ok:
            ok = 0
            i = 0
            while (i < len(alist) - 1):
                if (sort.datecmp(alist[i].date, alist[i + 1].date) > 0):
                    aux = alist[i]
                    alist[i] = alist[i + 1]
                    alist[i + 1] = aux
                    ok = 1
                i += 1
        return (alist)

    def shakeSort(alist):
        '''
            Description: Performs a Cocktail Sort algorithm on the list alist
            Input: alist
                Precondition: alist is a list of Activities type object
            Output: alist
                Postcondition: - 
        '''
        up = range(len(alist) - 1)
        while True:
            for indices in (up, reversed(up)):
                swapped = False
                for i in indices:
                    if (sort.datecmp(alist[i].date, alist[i + 1].date) > 0):
                        alist[i], alist[i + 1] = alist[i + 1], alist[i]
                        swapped = True
                if not swapped:
                    return (alist)
