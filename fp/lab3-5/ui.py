def ReadInput():
    '''
    Read the command that the user imputs.
    Input: - 
    Output: text
        Postcondition: text is a string containg what the user entered.
    '''
    text = input('Please give a command: ')
    return (text)

def SplitInput(text):
    '''
    Split a string into a list of substrings, using ',' as a delimiter.
    Input: text
        Precondition: text is a string
    Output: text_modified
        Postcondition: text_modified is a list of strings
    '''
    text.lower()
    text_modified = text.split(',')
    i = 0
    while (i < len(text_modified)):
        text_modified[i] = text_modified[i].strip()
        text_modified[i] = ' '.join(text_modified[i].split())
        i += 1
    return (text_modified)

def TestSplitInput():
    assert SplitInput('add 100, in, description') == ['add 100', 'in', 'description']
    assert SplitInput('replace 10, 300, out, fun      description') == ['replace 10', '300', 'out', 'fun description']
    assert SplitInput('remove 20    out') == ['remove 20 out']

