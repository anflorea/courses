
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
        st[len(a)] = 1

def back(vf, st, a, n):
    for i in [0, 1]:
        st[vf] = i
        if (vf == len(a) - 1):
            try_print(st, a, n)
        else:
            back(vf + 1, st, a, n)

def compute_all_subsequences(a, n):
    st = (len(a) + 1) * [0]
    back(0, st, a, n)
    if (st[len(a)]):
        return (1)
    return (0)

def main():
    a = read_sequence()
    n = read_n()
    print ("The subsequences are:")
    ok = compute_all_subsequences(a, n)
    if (ok == 0):
        print ("There is no solution :(")

main()
