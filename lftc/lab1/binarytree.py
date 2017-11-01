class Node:
    def __init__(self, val, key):
        self.l = None
        self.r = None
        self.v = val
        self.k = key

    def __str__(self):
        return str(self.v) + ": " + str(self.k)

class Tree:
    def __init__(self):
        self.root = None

    def __str__(self):
        self.sorted = []
        self.get_inorder(self.root)
        return str(self.sorted)
        
    def get_inorder(self, node):
        if node:
            self.get_inorder(node.l)
            self.sorted.append(str(node))
            self.get_inorder(node.r)

    def getRoot(self):
        return self.root

    def add(self, val, key):
        if(self.root == None):
            self.root = Node(val, key)
        else:
            self._add(val, key, self.root)

    def _add(self, val, key, node):
        if (val < node.v):
            if (node.l != None):
                self._add(val, key, node.l)
            else:
                node.l = Node(val, key)
        else:
            if (node.r != None):
                self._add(val, key, node.r)
            else:
                node.r = Node(val, key)

    def find(self, val):
        if (self.root != None):
            return self._find(val, self.root)
        else:
            raise KeyError(val)

    def _find(self, val, node):
        if(val == node.v):
            return node.k
        elif (val < node.v and node.l != None):
            self._find(val, node.l)
        elif (val > node.v and node.r != None):
            self._find(val, node.r)
        else:
            raise KeyError(val)

    def deleteTree(self):
        # garbage collector will do this for us. 
        self.root = None

    def printTree(self):
        if(self.root != None):
            self._printTree(self.root)

    def _printTree(self, node):
        if(node != None):
            self._printTree(node.l)
            print str(node.v) + ' '
            self._printTree(node.r)
