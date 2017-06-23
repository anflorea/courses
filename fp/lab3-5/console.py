'''
	John wants to manage his bank account. In order to complete this task, John needs an application to
	store, for a certain month, all the banking transactions which were performed on his account. Each
	transaction will be stored in the application through the following elements: day (of the month in
	which the transaction was made), amount of money transferred into/from the account, the type of the
	transaction (into the account – in or from the account - out), and description of the transaction. Please
	help John to create an application in order to repeatedly execute the following functionalities:
		-Add a new transaction into the list
		-Modify transactions from the list
                -Write the transactions having diferent properties
                -Obtain diferent characteristics of transactions
                -Filter
                -Undo the last operation
'''

from datetime import datetime
from ui import *
from util import *

# ------ Defines -------------------

WELCOME_MESSAGE = "Welcome to the banking transactions manager."
HELP_USSAGE = "For a more detailed help about a certain command, type <command> help."
IO_SET = ['in', 'out']
SYNTAX_ERROR = "Error: syntax error!\n"
UNKNOWN_COMMAND = "Error: Unknown command. Type \'help\' for a list of available commands."
SUCCESS_MESSAGE = "The operation was successful."

# ------ Compute Functions --------

def FindEntryByDescription(day, in_out, descript, data_base):
    '''
    Description: Verifies if the transaction with the in_out and description is in day.
    Input: day, in_out, descript, data_base
        Precondition: day is an int, in_out and descript are strings
    Output: pos
	Postcondition: pos is -1 if the transaction is not in day pos is the position of the transaction if it is found in the list
    '''
    pos = 0
    while (data_base[day][pos][1] != 'eod'):
        if (data_base[day][pos][1] == in_out and data_base[day][pos][2] == descript):
            return (pos)
        pos += 1
    return (-1)

def TestFindEntryByDescription(data_base):
    data_base[13].insert(0, [200, 'in', 'description'])
    assert FindEntryByDescription(13, 'in', 'description', data_base) == 0
    data_base[13].remove(data_base[13][0])
    assert FindEntryByDescription(20, 'out', 'transfer', data_base) == -1
    data_base[25].insert(0, [200, 'out', 'fun description'])
    assert FindEntryByDescription(25, 'out', 'fun description', data_base) == 0
    data_base[25].remove(data_base[25][0])
    data_base[18].insert(0, [666, 'in', 'de'])
    assert FindEntryByDescription(18, 'in', 'de', data_base) == 0
    data_base[18].remove(data_base[18][0])

def compute_add(text, data_base, stack):
    '''
    Description: Add a new transaction to the current day
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    '''
    USAGE = "Usage: add <value>, <in/out>, <description>"
    if ((len(text) == 3) and (len(text[0].split(' ')) == 2)):
        descript = text[2]
        in_out = text[1]
        value = text[0].split(' ')[1]
        if ((in_out in IO_SET) and (value.isdigit()) and (int(value) > 0)):
            if (FindEntryByDescription(datetime.now().day, in_out, descript, data_base) == -1):
                data_base[datetime.now().day].insert(0, [int(value), in_out, descript])
                stack['undo'].append(['add', datetime.now().day, int(value), in_out, descript])
                print(SUCCESS_MESSAGE)
            else:
                print("Error: Transaction already exists.")
        else:
            print(SYNTAX_ERROR, USAGE)
    elif ((len(text) == 1) and (len(text[0].split(' ')) == 2) and (text[0].split(' ')[1] == 'help')):
        print(functions_dictionary['add'][1], '\n', USAGE)
    else:
        print(SYNTAX_ERROR, USAGE)

def compute_insert(text, data_base, stack):
    '''
    Description: Insert a new transaction to a specified day
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    '''
    USAGE = "Usage: insert <day>, <value>, <in/out>, <description>"
    if ((len(text) == 4) and (len(text[0].split(' ')) == 2)):
        descript = text[3]
        in_out = text[2]
        value = text[1]
        day = text[0].split(' ')[1]
        if ((in_out in IO_SET) and (day.isdigit()) and (value.isdigit()) and (int(day) <= 31) and (int(value) > 0) and (int(day) > 0)):
            if (FindEntryByDescription(int(day), in_out, descript, data_base) == -1):
                data_base[int(day)].insert(0, [int(value), in_out, descript])
                stack['undo'].append(['add', int(day), int(value), in_out, descript])
                print(SUCCESS_MESSAGE)
            else:
                print("Error: Transaction already exists.")
        else:
            print(SYNTAX_ERROR, USAGE)
    elif ((len(text) == 1) and (len(text[0].split(' ')) == 2) and (text[0].split(' ')[1] == 'help')):
        print(functions_dictionary['insert'][1], '\n', USAGE)
    else:
        print(SYNTAX_ERROR, USAGE)

def compute_replace(text, data_base, stack):
    '''
    Description: Modify an existing transaction's value that matches a given type (in/out) and description
    Input:text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    '''
    USAGE = "Usage: replace <day>, <in/out>, <description> with <value>"
    if ((len(text) == 3) and (len(text[0].split(' ')) == 2) and (len(text[2].split(' ')) >= 3)):
        day = text[0].split(' ')[1]
        in_out = text[1]
        value = text[2].split(' ')[-1]
        withh = text[2].split(' ')[-2]
        descript = ''
        i = 0
        while (i < len(text[2].split(' ')) - 2):
            descript = descript + text[2].split(' ')[i]
            descript = descript + ' '
            i += 1
        descript = descript[0 : len(descript) - 1]
        if ((day.isdigit()) and (int(day) > 0) and (int(day) <= 31) and (in_out in IO_SET) and (value.isdigit()) and (int(value) > 0) and (withh == 'with')):
            pos = FindEntryByDescription(int(day), in_out, descript, data_base)
            if (pos == -1):
                print("Error: No transaction to replace")
            else:
                data_base[int(day)][pos][0] = int(value)
                stack['undo'].append(['replace', int(day), pos, int(value)])
                print(SUCCESS_MESSAGE)
        else:
            print (SYNTAX_ERROR, USAGE)
    elif ((len(text) == 1) and (len(text[0].split(' ')) == 2) and (text[0].split(' ')[1] == 'help')):
        print(functions_dictionary['replace'][1], '\n', USAGE)
    else:
        print(SYNTAX_ERROR, USAGE)

def remove_elements(start_day, end_day, in_out, less, data_base):
    """
    Description: Remove all the in/out transactions (if in/out it's specified, if not, remove all transactions) that are less that 'less' (if != from -1)
                 from the day start_day untill the day end_day.
    Input: start_dat, end_day, in_out, less, data_base
    	Preconditions: start_day, end_day and less are integers with the values between [1, 31] and start_day <= end_day
    Output: -
    	Postconditions: -
    """
    count = start_day
    while (count <= end_day):
        if (in_out == 'io'):
            data_base.pop(count, None)
            data_base[count] = [[0, 'eod', 'eod']]
        else:
            j = len(data_base[count]) - 1
            while (j >= 0):
                if (data_base[count][j][1] == in_out):
                    if (less == -1):
                        data_base[count].remove(data_base[count][j])
                    elif (data_base[count][j][0] < less):
                        data_base[count].remove(data_base[count][j])
                j -= 1
        count += 1

def compute_remove(text, data_base, stack):
    '''
    Description: Remove certain transactions.
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    '''
    USAGE = "Usage: \n\t-remove <day> <in/out> --Removes all the in/out (or all of them if let the 2nd field is empty) transactions from the specified day\
		\n\t-remove from <day1> to <day2> <in/out> --Removes all the in/out (or all) transactions between days day1 and day2\
		\n\t-remove <in/out> --Removes all the in/out transactions of the month"
    if ((len(text) == 1) and (len(text[0].split(' ')) == 2) and (text[0].split(' ')[1] == 'help')):
        print(functions_dictionary['remove'][1], '\n', USAGE)
    elif ((len(text) == 1) and (len(text[0].split(' ')) > 1)):
        if ((text[0].split(' ')[1] in IO_SET) and (len(text[0].split(' ')) == 2)):
            in_out = text[0].split(' ')[1]
            remove_elements(1, 31, in_out, -1, data_base)
            stack['undo'].append(['remove', 1, 31, in_out, -1])
            print(SUCCESS_MESSAGE)
        elif (text[0].split(' ')[1].isdigit()):
            day = text[0].split(' ')[1]
            if ((int(day) > 0) and (int(day) <= 31)):
                if (len(text[0].split(' ')) == 2):
                    remove_elements(int(day), int(day), 'io', -1, data_base)
                    stack['undo'].append(['remove', int(day), int(day), 'io', -1])
                    print(SUCCESS_MESSAGE)
                elif ((len(text[0].split(' ')) == 3) and (text[0].split(' ')[2] in IO_SET)):
                    in_out = text[0].split(' ')[2]
                    remove_elements(int(day), int(day), in_out, -1, data_base)
                    stack['undo'].append(['remove', int(day), int(day), in_out, -1])
                    print(SUCCESS_MESSAGE)
                else:
                    print(SYNTAX_ERROR, USAGE)
            else:
                print(SYNTAX_ERROR, USAGE)
        elif (text[0].split(' ')[1] == 'from'):
            length = len(text[0].split(' '))
            if (length >= 5):
                fromm = text[0].split(' ')[1]
                day1 = text[0].split(' ')[2]
                too = text[0].split(' ')[3]
                day2 = text[0].split(' ')[4]
                if ((fromm == 'from') and (day1.isdigit()) and (too == 'to') and (day2.isdigit()) and (int(day1) > 0) and (int(day1) <= 31) and (int(day2) > 0) and (int(day2) <=31) and (int(day1) < int(day2))):
                    if (length == 5):
                        remove_elements(int(day1), int(day2), 'io', -1, data_base)
                        stack['undo'].append(['remove', int(day1), int(day2), 'io', -1])
                        print(SUCCESS_MESSAGE)
                    elif (length == 6):
                        in_out = text[0].split(' ')[5]
                        if (in_out in IO_SET):
                            remove_elements(int(day1), int(day2), in_out, -1, data_base)
                            stack['undo'].append(['remove', int(day1), int(day2), in_out, -1])
                            print(SUCCESS_MESSAGE)
                        else:
                            print (SYNTAX_ERROR, USAGE)
                    else:
                        print (SYNTAX_ERROR, USAGE)
                else:
                    print(SYNTAX_ERROR, USAGE)
            else:
                print (SYNTAX_ERROR, USAGE)
        else:
            print(SYNTAX_ERROR, USAGE)
    else:
        print(SYNTAX_ERROR, USAGE)

def compute_filter(text, data_base, stack):
    '''
    Description: Retain only the transactions of a certain type.
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    '''
    USAGE = "Usage: filter <in/out> <value> (if value is missing, it is considered 0)"
    if ((len(text) == 1) and (len(text[0].split()) == 2) and (text[0].split()[1] == 'help')):
        print(functions_dictionary['filter'][1], '\n', USAGE)
    elif ((len(text) == 1) and (len(text[0].split()) >= 2) and (len(text[0].split()) <= 3)):
        in_out = text[0].split()[1]
        if (in_out in IO_SET):
            if (in_out == 'in'):
                other = 'out'
            else:
                other = 'in'
            if (len(text[0].split()) == 3):
                value = text[0].split()[2]
                if (value.isdigit()):
                    value = int(value)
                else:
                    value = 'error'
            else:
                value = 0
            if (value == 'error'):
                print(SYNTAX_ERROR)
            else:
                remove_elements(1, 31, other, -1, data_base)
                remove_elements(1, 31, in_out, value, data_base)
                #stack.append(['remove', 1, 31, other, -1])
                #stack.append(['remove', 1, 31, in_out, value])
                stack['undo'].append(['filter', 1, 31, in_out, value])
                print(SUCCESS_MESSAGE)
        else:
            print(SYNTAX_ERROR, USAGE)
    else:
        print(SYNTAX_ERROR, USAGE)

def compute_balance(text, data_base, stack):
    """
    Description: Compute the account balance on the specified day
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    """
    USAGE = "Usage: balance <day>"
    if (len(text) == 1):
        if (len(text[0].split()) == 2 and text[0].split()[1].isdigit()):
            day = int(text[0].split()[1])
            if (day >= 1 and day <= 31):
                balance = 0
                i = 1
                while (i <= day):
                    j = 0
                    while (data_base[i][j][1] != 'eod'):
                        if (data_base[i][j][1] == 'in'):
                            balance += data_base[i][j][0]
                        else:
                            balance -= data_base[i][j][0]
                        j += 1
                    i += 1
                print("The accounts balance on day", day, "is:", balance)
            else:
                print (SYNTAX_ERROR, USAGE)
        elif (len(text[0].split()) == 2 and (text[0].split()[1] == 'help')):
            print(functions_dictionary['balance'][1], '\n', USAGE)
        else:
            print (SYNTAX_ERROR, USAGE)
    else:
        print (SYNTAX_ERROR, USAGE)

def compute_sum(text, data_base, stack):
    """ 
    Description: Compute the total amount from in/out transactions.
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    """
    USAGE = "Usage: sum <in/out>"
    if (len(text) == 1 and len(text[0].split()) == 2):
        in_out = text[0].split()[1]
        if (in_out == 'help'):
            print (functions_dictionary['sum'][1], '\n', USAGE)
        elif (in_out in IO_SET):
            suma = 0
            i = 1
            while (i <= 31):
                j = 0
                while (data_base[i][j][1] != 'eod'):
                    if (data_base[i][j][1] == in_out):
                        suma += data_base[i][j][0]
                    j += 1
                i += 1
            print("The total amount from", in_out, "transactions is:", suma)
        else:
            print (SYNTAX_ERROR, USAGE)
    else:
        print (SYNTAX_ERROR, USAGE)

def compute_max(text, data_base, stack):
    """ 
    Description: Compute the day with the maximum amount in an in/out transaction.
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    """
    USAGE = "Usage: max <in/out> day"
    if (len(text) == 1 and (len(text[0].split()) == 3)):
        in_out = text[0].split()[1]
        if (in_out in IO_SET and text[0].split()[2] == 'day'):
            maxi = 0
            maxi_day = -1
            i = 1
            while (i <= 31):
                j = 0
                while (data_base[i][j][1] != 'eod'):
                    if (data_base[i][j][0] > maxi and data_base[i][j][1] == in_out):
                        maxi = data_base[i][j][0]
                        maxi_day = i
                    j += 1
                i += 1
            if (maxi_day > 0):
                print ("The day with the maximum ammount in an", in_out,"transaction is:", maxi_day)
            else:
                print ("Error: There are no", in_out, "transactions.")
        else:
            print (SYNTAX_ERROR, USAGE)
    elif (len(text) == 1 and len(text[0].split()) == 2 and text[0].split()[1] == 'help'):
        print(functions_dictionary['max'][1], '\n', USAGE)
    else:
        print (SYNTAX_ERROR, USAGE)

def compute_grater(text, data_base, stack):
    """ 
    Description: Writes all the transactions that are grater than the specified value.
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    """
    USAGE = "Usage: grater than <value> (before <day>)"
    if (len(text) == 1):
        if (len(text[0].split()) == 2 and (text[0].split()[1] == 'help')):
            print(functions_dictionary['greater'][1], '\n', USAGE)
        elif (len(text[0].split()) >= 3 and text[0].split()[1] == 'than' and text[0].split()[2].isdigit()):
            value = int(text[0].split()[2])
            if (len(text[0].split()) == 5 and text[0].split()[3] == 'before'):
                day = text[0].split()[4]
                if (day.isdigit() and (int(day) >= 1) and (int(day) <= 31)):
                    day = int(day)
                else:
                    print (SYNTAX_ERROR, USAGE)
                    day = -1
            elif (len(text[0].split()) == 3):
                day = 32
            else:
                print(SYNTAX_ERROR, USAGE)
                day = -1
            if (day >= 0):
                i = 1
                while (i < day):
                    j = 0
                    while (data_base[i][j][1] != 'eod'):
                        if (data_base[i][j][0] > value):
                            print("Day ", i, ':', data_base[i][j])
                        j += 1
                    i += 1
        else:
            print(SYNTAX_ERROR, USAGE)
    else:
        print(SYNTAX_ERROR, USAGE)


def compute_less(text, data_base, stack):
    """ 
    Description: Writes all the transactions that are less than the specified value.
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    """
    USAGE = "Usage: less than <value> (before <day>)"
    if (len(text) == 1):
        if (len(text[0].split()) == 2 and (text[0].split()[1] == 'help')):
            print(functions_dictionary['less'][1], '\n', USAGE)
        elif (len(text[0].split()) >= 3 and text[0].split()[1] == 'than' and text[0].split()[2].isdigit()):
            value = int(text[0].split()[2])
            if (len(text[0].split()) == 5 and text[0].split()[3] == 'before'):
                day = text[0].split()[4]
                if (day.isdigit() and (int(day) >= 1) and (int(day) <= 31)):
                    day = int(day)
                else:
                    print (SYNTAX_ERROR, USAGE)
                    day = -1
            elif (len(text[0].split()) == 3):
                day = 32
            else:
                print(SYNTAX_ERROR, USAGE)
                day = -1
            if (day >= 0):
                all
                i = 1
                while (i < day):
                    j = 0
                    while (data_base[i][j][1] != 'eod'):
                        if (data_base[i][j][0] < value):
                            print("Day ", i, ':', data_base[i][j])
                        j += 1
                    i += 1
        else:
            print(SYNTAX_ERROR, USAGE)
    else:
        print(SYNTAX_ERROR, USAGE)

def compute_sort(text, data_base, stack):
    """ 
    Description: Sorts the total daily transactions.
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    """
    USAGE = "USAGE: sort <asc/desc> <day/type>"
    if (len(text) == 1):
        if (len(text[0].split()) == 2 and text[0].split()[1] == 'help'):
            print (functions_dictionary['sort'][1], '\n', USAGE)
        elif (len(text[0].split()) == 3):
            direction = text[0].split()[1]
            typee = text[0].split()[2]
            TYPE = ['day', 'type']
            DIRECT = ['asc', 'desc']
            if (direction in DIRECT and typee in TYPE):
                if (typee == 'day'):
                    listt = []
                    i = 1
                    balance = 0
                    old_balance = 0
                    while (i <= 31):
                        j = 0
                        while (data_base[i][j][1] != 'eod'):
                            if (data_base[i][j][1] == 'in'):
                                balance += data_base[i][j][0]
                            else:
                                balance -= data_base[i][j][0]
                            j += 1
                        if (balance != old_balance):
                            listt.append([balance, i])
                            old_balance = balance
                        i += 1
                    if (direction == 'asc'):
                        listt.sort()
                    else:
                        listt.sort(reverse=True)
                    i = 0
                    while (i < len(listt)):
                        print (listt[i][0],"RON on day", listt[i][1])
                        i += 1
                else:
                    list_in = []
                    list_out = []
                    balance_in = 0
                    old_balance_in = 0
                    balance_out = 0
                    old_balance_out = 0
                    i = 1
                    while (i <= 31):
                        j = 0
                        while (data_base[i][j][1] != 'eod'):
                            if (data_base[i][j][1] == 'in'):
                                balance_in += data_base[i][j][0]
                            else:
                                balance_out += data_base[i][j][0]
                            j += 1
                        if (balance_in != old_balance_in):
                            list_in.append([balance_in, i])
                            old_balance_in = balance_in
                        if (balance_out != old_balance_out):
                            list_out.append([balance_out, i])
                            old_balance_out = balance_out
                        i += 1
                    if (direction == 'asc'):
                        list_in.sort()
                        list_out.sort()
                    else:
                        list_in.sort(reverse=True)
                        list_out.sort(reverse=True)
                    i = 0
                    print ("The in transactions:")
                    while (i < len(list_in)):
                        print(list_in[i][0], "RON on day", list_in[i][1])
                        i += 1
                    i = 0
                    print ("The out transactions:")
                    while (i < len(list_out)):
                        print(list_out[i][0], "RON on day", list_out[i][1])
                        i += 1
            else:
                print (SYNTAX_ERROR, USAGE)
        else:
            print (SYNTAX_ERROR, USAGE)
    else:
        print (SYNTAX_ERROR, USAGE)

def compute_undo(text, data_base, stack):
    """ 
    Description: The last command that has modified the list is cancelled.
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    """
    USAGE = "Usage: undo"
    if (len(text) == 1 and (len(text[0].split()) == 1)):
        if (len(stack['undo']) > 0):
            stack['redo'].append(stack['undo'][len(stack['undo']) - 1])
            stack['undo'].remove(stack['undo'][len(stack['undo']) - 1])
            reinit_database(data_base, stack)
            print(SUCCESS_MESSAGE)
        else:
            print("Error: Already at oldest change.")
    elif (len(text) == 1 and len(text[0].split()) == 2 and text[0].split()[1] == 'help'):
        print(functions_dictionary['undo'][1], '\n', USAGE)
    else:
        print(SYNTAX_ERROR, USAGE)

def compute_redo(text, data_base, stack):
    """ 
    Description: Redoes the last undone command.
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    """
    USAGE = "Usage: redo"
    if (len(text) == 1):
        if (len (text[0].split()) == 1):
            if (len(stack['redo']) > 0):
                stack['undo'].append(stack['redo'][len(stack['redo']) - 1])
                stack['redo'].remove(stack['redo'][len(stack['redo']) - 1])
                reinit_database(data_base, stack)
                print (SUCCESS_MESSAGE)
            else:
                print("Error: Nothing to redo.")
        elif (len(text[0].split()) == 2 and text[0].split()[1] == 'help'):
            print(functions_dictionary['redo'][1], '\n', USAGE)
        else:
            print (SYNTAX_ERROR, USAGE)
    else:
        print (SYNTAX_ERROR, USAGE)

def compute_all(text, data_base, stack): # Print all stored values
    """ 
    Description: Print all the transactions.
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    """
    USAGE = "Usage: all <in/out> (if the type is missing, all the transactions are printed.)"
    if (len(text) == 1 and (len(text[0].split()) == 1)):
        i = 1
        while (i <= 31):
            j = 0
            while (data_base[i][j][1] != "eod"):
                print ("Day ", i, ":", data_base[i][j])
                j = j + 1
            i = i + 1
    elif (len(text) == 1 and (len(text[0].split()) == 2)):
        if (text[0].split()[1] == 'help'):
            print(functions_dictionary['all'][1], '\n', USAGE)
        elif (text[0].split()[1] in IO_SET):
            in_out = text[0].split()[1]
            i = 1
            while (i <= 31):
                j = 0
                while (data_base[i][j][1] != 'eod'):
                    if (data_base[i][j][1] == in_out):
                        print("Day ", i, ':', data_base[i][j])
                    j += 1
                i += 1
        else:
            print (SYNTAX_ERROR, USAGE)
    else:
        print (SYNTAX_ERROR, USAGE)

def exit_program(text, data_base, stack):
    '''
    Description: Quit the application
    '''
    USAGE = "Usage: exit"
    if ((len(text) == 1) and (len(text[0].split(' ')) == 2) and (text[0].split(' ')[1] == 'help')):
        print(functions_dictionary[text[0].split(' ')[0]][1], '\n', USAGE)
    elif ((len(text) == 1) and (len(text[0].split(' ')) == 1)):
        ok = False
    else:
        print(SYNTAX_ERROR, USAGE)

# ------ Help Function ------------

def display_help(text, data_base, stack):
    '''
    Description: Display a list of the available commands
    '''
    print("The available commands are:")
    for i in functions_dictionary:
        print ("\t- ", i, '  -  ', functions_dictionary[i][1])


# ------ Pointers to functions ----

functions_dictionary = {
        'help'          : [display_help, 'Print a list of all the available commands.'],
        'exit'          : [exit_program, 'Quit the application.'],
        'add'           : [compute_add, 'Add a new transaction to the current day.'],
        'all'           : [compute_all, 'Print all the stored transaction'],
        'insert'        : [compute_insert, 'Add a new transaction to a specified day.'],
        'replace'       : [compute_replace, 'Modify an existing transaction\'s value'],
        'remove'        : [compute_remove, 'Remove certain transactions.'],
        'filter'        : [compute_filter, 'Retain only the transactions of a certain type that are grater than a given value.'],
        'undo'          : [compute_undo, 'Undo the last command.'],
        'redo'          : [compute_redo, 'Redo the last undone command.'],
        'greater'       : [compute_grater, 'Write all the transactions that are grater than a given value.'],
        'less'          : [compute_less, 'Writes all the transactions that are less than a given value.'],
        'balance'       : [compute_balance, 'Computes the account balance on a given day.'],
        'sum'           : [compute_sum, 'Writes the total ammount from a certain type transactions.'],
        'max'           : [compute_max, 'Writes the day with the maximum amount in a certain type transaction.'],
        'sort'          : [compute_sort, 'Sorts the total daily transactions in an ascending/descending order.']
}

# ------ Input Functions ----------


def ComputeInput(text, data_base, stack):
    '''
    Description: Send the command to the correct function using the functions dictionary
    Input: text, data_base
        Precondition: text is a list of strings
                      data_base is a dictionary of lists
    Output: -
    '''
    first_word = text[0].split(' ')[0]
    if (first_word in functions_dictionary):
        functions_dictionary[first_word][0](text, data_base, stack)
    else:
        print(UNKNOWN_COMMAND)

def reinit_database(data_base, stack):
    init_database(data_base)
    i = 0
    while (i < len(stack['undo'])):
        if (stack['undo'][i][0] == 'add'):
            data_base[stack['undo'][i][1]].insert(0, [stack['undo'][i][2], stack['undo'][i][3], stack['undo'][i][4]])
        elif (stack['undo'][i][0] == 'remove'):
            remove_elements(stack['undo'][i][1], stack['undo'][i][2], stack['undo'][i][3], stack['undo'][i][4], data_base)
        elif (stack['undo'][i][0] == 'replace'):
            data_base[stack['undo'][i][1]][stack['undo'][i][2]][0] = stack['undo'][i][3]
        elif (stack['undo'][i][0] == 'filter'):
            if (stack['undo'][i][3] == 'in'):
                other = 'out'
            else:
                other = 'in'
            remove_elements(stack['undo'][i][1], stack['undo'][i][2], stack['undo'][i][3], stack['undo'][i][4], data_base)
            remove_elements(stack['undo'][i][1], stack['undo'][i][2], other, -1, data_base) 
        i += 1



# ------ Main Function ------------

def main():
    data_base = {}
    i = 1
    while (i <= 31):
        data_base[i] = [[0, 'eod', 'eod']]
        i += 1
    stack = {
            'undo' : [],
            'redo' : []
            }
    ok = True
    print(WELCOME_MESSAGE)
    display_help('help', data_base, stack)
    print(HELP_USSAGE)
    TestFindEntryByDescription(data_base)
    TestSplitInput()
    while (ok == True):
        text = ReadInput()
        if (text == 'exit'):
            ok = False
        else:
            text = SplitInput(text)
            ComputeInput(text, data_base, stack)

