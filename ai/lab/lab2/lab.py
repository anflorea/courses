import sys
import copy

class State:
	def __init__(self, state, figures):
		self.figures = figures
		self.board = state

class Problem:
	def __init__(self, fileName):
		self.m_fileName = fileName
		self.figures = []
		self.readFromFile()
		self.initialState = State([[0 for x in range(6)] for y in range(7)], self.figures)
		self.finalState = []

	def readFromFile(self):
		with open(self.m_fileName, 'r') as f:
			self.h = (int)(f.readline())
			self.w = (int)(f.readline())
			self.n = (int)(f.readline())
			lines = f.readlines()
			i = 0
			while i < self.n * 2:
				self.figures.append([[int(x) for x in lines[i].split()], [int(x) for x in lines[i + 1].split()]])
				i += 2
			f.close
			return True
		return False

	def checkIfOk(self, state):
		if 1 in state[len(state) - 1]:
			return False
		column = []
		for i in range(0, len(state)):
			column.append(state[i][len(state[i]) - 1])
		if 1 in column:
			return False
		return True

	def expand(self, state):
		currentState = state.board
		figures = state.figures
		fig = figures[0]

		listStates = []
		for i in range(0, self.h - 1):
			for j in range(0, self.w - 3):
				modifiedState = copy.deepcopy(currentState)
				newState = self.putTetris(modifiedState, fig, i, j)
				if newState is not None:
					if self.checkIfOk(newState) == True:
						correctState = State(newState, figures[1:])
						listStates.append(correctState)
		return listStates

	def putTetris(self, state, figure, x, y):
		for i in range(2):
			for j in range(4):
				if state[x + i][y + j] + figure[i][j] == 2:
					return None
				else:
					state[x + i][y + j] = state[x + i][y + j] + figure[i][j]
		return state

	def heuristic(self, state):
		currentState = state

		height = self.h - 1
		width = self.w - 1

		nrZero = 0

		for i in range(0, height):
			for j in range(0, width):
				if currentState.board[i][j] == 1:
					if currentState.board[i - 1][j] == 0 and i > 0 and i != height and j != width:
						nrZero += 1
					if currentState.board[i][j - 1] == 0 and j > 0 and i != height and j != width:
						nrZero += 1
					if currentState.board[i + 1][j] == 0 and i + 1 != height and j != width:
						nrZero += 1
					if currentState.board[i][j+1] == 0 and i != height and j + 1 != width:
						nrZero += 1
		return nrZero

class Controller:
	def __init__(self, instance):
		self.problem = instance

	def dfs(self):
		start = self.problem.initialState
		found = False
		visited = []
		toVisit = [start]

		while len(toVisit) != 0 and not found:
			node = toVisit.pop(0)
			if len(node.figures) == 0:
				found = True
				self.problem.finalState = node
			else:
				children = self.problem.expand(node)
				visited.append(node)
				aux = []
				for x in children:
					if x not in visited:
						aux.append(x)
				toVisit = aux + toVisit
		return found

	def greedy(self):
		start = self.problem.initialState
		found = False
		visited = []
		toVisit = [start]

		while len(toVisit) != 0 and not found:
			node = toVisit.pop(0)
			if len(node.figures) == 0:
				found = True
				self.problem.finalState = node
			else:
				children = self.problem.expand(node)
				visited.append(node)
				aux = []
				for x in children:
					if x not in visited:
						aux.append(x)
				aux = self.priorityQueue(aux)
				toVisit = toVisit + aux
		return found

	def priorityQueue(self, listStates):
		listStates = sorted(listStates, key=lambda State: self.problem.heuristic(State))
		return listStates

class UI:
	def __init__(self, controller):
		self.controller = controller

	def printMenu(self):
		print("0. Exit")
		print("1. DFS")
		print("2. GBFS")

	def mainMenu(self):
		c = sys.stdin
		while (True):
			self.printMenu()
			print("Please insert a command: ")
			cmd = c.readline()
			if (cmd == "0\n"):
				break
			elif (cmd == "1\n"):
				self.controller.dfs()
				for line in self.controller.problem.finalState.board:
					print(line)
			elif (cmd == "2\n"):
				self.controller.greedy()
				for line in self.controller.problem.finalState.board:
					print(line)
			else:
				print("Unknown command")


problem = Problem("file.txt")
ctrl = Controller(problem)
ui = UI(ctrl)
ui.mainMenu()
