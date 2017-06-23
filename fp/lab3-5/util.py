# ------ Stack to Data Base -------

def init_database(data_base):
    i = 1
    while (i <= 31):
        del data_base[i]
        data_base[i] = [[0, 'eod', 'eod']]
        i += 1

