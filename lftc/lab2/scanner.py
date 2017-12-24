import sys

from finiteautomata.finite_automata_json_parser import FiniteAutomataJsonParser
from binarytree import Tree

symbols = ['<<', '>>', '=', '==', '!=', '<=', '>=', '<', '>', ';', '+', '-', '*', '/', '%', '(', ')', '{', '}']
tokens = ['ID', 'CONST', 'if', 'else', 'cout', 'cin', 'while', 'return', 'int', 'float', 'main'] + symbols
alphabet = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'
numeric = '1234567890.'
alphanumeric = alphabet + numeric
symbols_alphabet = '.+*-,><:=()%/; _{}!$'

FILENAME = 'cmmdc.txt'

def read(filename):
    fp = open(filename)
    lines = fp.readlines()
    fp.close()
    data = ''
    for i in range(len(lines)):
        fin = lines[i].strip("\n\t ") + " "
        if fin != ' ':
            data += fin
    return data


def parse(data):
    print "PROGRAM:", data
    d = []
    counter = 0

    parser1 = FiniteAutomataJsonParser()
    finite_automata1 = parser1.parse("finiteautomata/literals.json")
    parser2 = FiniteAutomataJsonParser()
    finite_automata2 = parser2.parse("finiteautomata/id.json")
    parser3 = FiniteAutomataJsonParser()
    finite_automata3 = parser3.parse("finiteautomata/constants.json")
    while data:
        x = finite_automata1.longest_prefix(data)
        y = finite_automata2.longest_prefix(data)
        z = finite_automata3.longest_prefix(data)
        if len(x) == 0 and len(y) == 0 and len(z) == 0:
            print "Syntax Error at {0}!".format(counter)
            sys.exit()
        else:
            if x:
                data = data[len(x):]
                counter += len(x)
                if x != ' ' and x != '\t' and x != '\n':
                    d.append(str(x))
            elif y:
                data = data[len(y):]
                counter += len(y)
                d.append(str(y))
            elif z:
                data = data[len(z):]
                counter += len(z)
                d.append(str(z))
    data = d

    symbol_table_id = Tree()
    symbol_table_constants = Tree()
    program_internal_form = []
    symbol_table_index = 0
    for i in data:
        is_in_symboltable = False
        try:
            is_in_symboltable = symbol_table_id.find(i)
        except KeyError:
            try:
                is_in_symboltable = symbol_table_constants.find(i)
            except KeyError:
                pass

        if i not in tokens and is_in_symboltable is False:
            try:
                float(i)
                symbol_table_constants.add(i, symbol_table_index)
            except ValueError:
                symbol_table_id.add(i, symbol_table_index)
            symbol_table_index += 1
    for i in data:
        if i in tokens:
            program_internal_form.append((tokens.index(i), None))
        else:
            val = None
            tip = None
            try:
                val = symbol_table_constants.find(i)
                tip = 'CONST'
            except KeyError:
                try:
                    val = symbol_table_id.find(i)
                    tip = 'ID'
                except KeyError:
                    pass

            program_internal_form.append((tokens.index(tip), val))
    return symbol_table_id, symbol_table_constants, program_internal_form


if __name__ == '__main__':
    program = read(FILENAME)
    s1, s2, pif = parse(program)
    print "Identifiers symbol table:", s1
    print "Constants symbol table:", s2
    print "Program internal form:", pif
