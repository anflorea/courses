import sys

from binarytree import Tree

symbols = ['<<', '>>', '=', '==', '!=', '<=', '>=', '<', '>', ';', '+', '-', '*', '/', '%', '(', ')', '{', '}']
tokens = ['ID', 'CONST', 'if', 'else', 'cout', 'cin', 'while', 'return', 'int', 'float', 'main'] + symbols
alphabet = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'
numeric = '1234567890.'
alphanumeric = alphabet + numeric
symbols_alphabet = '.+*-,><:=()%/; _{}!'

FILENAME = 'sum.txt'

def read(filename):
    fp = open(filename)
    lines = fp.readlines()
    fp.close()
    data = ''
    for i in range(len(lines)):
        fin = lines[i].strip("\n\t ") + " "
        if fin != ' ':
            data += fin
    print data
    return normalize(data).strip()


def normalize(data):
    result = ''
    aux = data
    while aux:
        found = False
        for j in symbols:
            index = aux.find(j)
            if index == 0:
                found = True
                aux = aux[len(j):]
                if result[-1] != ' ':
                    result += ' '
                result += j
                result += ' '
                break
        if found is False:
            result += aux[0]
            aux = aux[1:]
    print result
    r = ''
    for i in result.split(' '):
        if i != '':
            r += i + " "
    return r


def validate_token(token):
    for i in token:
        if i not in alphanumeric + symbols_alphabet:
            print "BAD ALPHABET"
            return "BAD ALPHABET"
    if token not in tokens:
        if token in symbols_alphabet:
            print "BAD TOKEN"
            return "BAD TOKEN"
    if token[0] in numeric:
        try:
            float(token)
        except ValueError:
            print "BAD START"
            return "BAD START"
    if len(token) > 8:
        print "BAD LENGTH"
        return "BAD LENGTH"
    return "OK"


def parse(data):
    symbol_table_id = Tree()
    symbol_table_constants = Tree()
    program_internal_form = []
    symbol_table_index = 0
    data = data.split(" ")
    for i in data:
        if validate_token(i) != "OK":
            print validate_token(i)
            file_data = open(FILENAME).readlines()
            line = 0
            for j in range(len(file_data)):
                if i in file_data[j]:
                    line = j
            print "Lexical error at token '%s' line " % i, line + 1
            sys.exit(0)
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
    print "Identifiers symbol table: ", str(s1)
    print "Constants symbol table: ", str(s2)
    print "Program internal form:", pif
