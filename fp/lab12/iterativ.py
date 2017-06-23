
def read_number_of_elem():
    while True:
        try:
            n = int(input("Insert the number of elements in the sequence: "))
            if (n <= 0):
                raise
            return (n)
        except:
            print("Please give a valid input!")

def read_number(a):
    while True:
        try:
            n = int(input())
            for i in a:
                if (n == i):
                    print("This number is already in the sequence! ", end="")
                    raise
            return (n)
        except:
            print("Please give a valid input!")

def read_sequence():
    n = read_number_of_elem()
    print ("Please insert the numbers in the sequence (one at a time):")
    a = []
    for i in range(n):
        a.append(read_number(a))
    return (a)

def read_n():
    while True:
        try:
            n = int(input("Please give the number 'n': "))
            return (n)
        except:
            print("Please give a valid input!")

def try_print(st, a, n):
    summ = 0
    new_a = []
    for i in range(len(a)):
        if (st[i]):
            summ += a[i]
            new_a.append(a[i])
    if (len(new_a) and summ % n == 0):
        print (new_a)
        return (1)
    return (0)

def compute_all_subsequences(a, n):
    st = len(a) * [0] # lista se intializeaza la 0 ([0, 0, 0, 0])
    ok = 1
    final = 0
    while ok:
        i = 0
        # se aduna 1 pe prima pozitie 
        # de exemplu pentru [1, 1, 0, 0] -> [2, 1, 0, 0]
        st[i] += 1
        # trecerea peste unitate
        while (st[i] > 1):
            st[i + 1] += 1
            st[i] = 0
            i += 1
        # trecerea peste unitate face asta [2, 1, 0, 0] -> [0, 2, 0, 0] -> [0, 0, 1, 0]
        res = try_print(st, a, n)
        if (res):
            final = 1
        ok2 = 1
        for i in st:
            if (i == 0):
                ok2 = 0
        if ok2:
            ok = 0
    # return 0 if no subsequence was found, 1 if at least one was found
    return (final)

def main():
    a = read_sequence()
    n = read_n()
    print ("The subsequences are:")
    ok = compute_all_subsequences(a, n)
    if (ok == 0):
        print ("There is no solution :(")

main()
