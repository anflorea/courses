import copy

class Problem:
    def __init__(self):
        self.pieces = []
        file = open('l2in.txt', 'r')
        self.h = self.n = (int)(file.readline())
        self.w = self.n = (int)(file.readline())
        self.n = (int)(file.readline())
        #print(self.n)
        lines  = file.readlines()
        i = 0
        while i < self.n * 2:
            self.pieces.append([[int(x) for x in lines[i].split()], [int(x) for x in lines[i+1].split()]])
            i += 2
        #print(self.pieces)

        self.inState = self.pieces
        self.finState = []

    def valid(self, m):
        #print('\n\nm\n')
        for x in m:
            #print(x)
            for y in x:
                if y > 1:
                    return False
        return True



def floodfill(matrix, x, y):
    if matrix[x][y] == 0:
        matrix[x][y] = 1
        #recursively invoke flood fill on all surrounding cells:
        if x > 0:
            floodfill(matrix,x-1,y)
        if x < len(matrix[y]) - 1:
            floodfill(matrix,x+1,y)
        if y > 0:
            floodfill(matrix,x,y-1)
        if y < len(matrix) - 1:
            floodfill(matrix,x,y+1)

class Controller:
    def __init__(self):
        self.problem = Problem()
        self.ins = [[[0]*self.problem.w for i in range(self.problem.h)], self.problem.pieces]
        #print(self.ins)

    def expand(self, node):
        res = []
        for i in range(self.problem.h-1):
            for j in range(self.problem.w-3):
                c = copy.deepcopy(node[0])
                for k in range(len(node[1][-1][0])):
                    #print(node[1][-1])
                    c[i][j+k] += node[1][-1][0][k]
                    c[i+1][j+k] += node[1][-1][1][k]
                if self.problem.valid(c):
                    p = copy.deepcopy(node[1])
                    #print(p)
                    res.append([c, p[:-1]])

        #print(res)
        return res


    def dfs(self, node):

        toVisit = [node]
        visited = []
        cover = []
        bst = []
        mx = 0
        while len(toVisit) != 0:

            node = toVisit.pop(0)
            #print('node', node)
            if len(node[1]) == 0:
                nd = copy.deepcopy(node)
                cover.append(nd)
                ns = copy.deepcopy(nd[0])
                c = 0
                #print(ns, self.problem.w, self.problem.h)
                for i in range (self.problem.h):
                    for j in range(self.problem.w):
                        if ns[i][j] == 0:
                            c += 1
                            floodfill(ns, i, j)
                if mx < c:
                    mx = c
                    bst = nd
                continue
            visited.append(node)

            children = self.expand(node)
            aux = []

            for x in children:
                    aux.append(x)
            toVisit = aux + toVisit

        return cover, bst

    def solve(self):
        return self.dfs([[[0]*self.problem.w for i in range(self.problem.h)], self.problem.pieces])



if __name__ == '__main__':
    c = Controller()
    cov, bst = c.solve()
    print("all covers")
    for s in cov:
        for l in s[0]:
            print(l)
        print('\n')

    print("best")
    for l in bst[0]:
        print(l)
    print('\n')