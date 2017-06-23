# Each individ will have a vector of colors that represents the colors
# of each face and a rotation property
#
# The vector will represent:  TOP(T), FRONT(F), 
#     RIGHT(R), BACK(B), LEFT(L), BOTTOM(BT)
#
# The rotation can be a number from 1 to 24
# 	1: 	T	F	R	B	L	BT
#	2:	T	L	F	R	B	BT
#	3:	T	B	L	F	R	BT
#	4:	T	R	B	L	F	BT
#	5:	F	BT	R	T	L	B
#	6:	F	L	BT	R	T	B
#	7:	F	T	L	BT	R	B
#	8:	F	R	T	L	BT	B
#	9:	BT	B	R	F	L	T
#	10:	BT	L	B	R	F	T
#	11:	BT	F	L	B	R	T
#	12:	BT	R	F	L	B	T
#	13:	B	T	R	BT	L	F
#	14:	B	L	T	R	BT	F
#	15:	B	BT	L	T	R	F
#	16:	B	R	BT	L	T	F
#	17:	L	BT	F	T	B	R
#	18:	L	B	BT	F	T	R
#	19:	L	T	B	BT	F	R
#	20:	L	F	T	B	BT	R
#	21:	R	T	F	BT	B	L
#	22:	R	B	T	F	BT	L
#	23:	R	BT	B	T	F	L
#	24:	R	F	BT	B	T	L
#
#


from enum import Enum
from itertools import groupby
from random import *
import sys
import math

class Color(Enum):
	NONE = 0
	RED = 1
	BLUE = 2
	GREEN = 3
	YELLOW = 4

class Cube:
	top = Color.NONE
	front = Color.NONE
	right = Color.NONE
	back = Color.NONE
	left = Color.NONE
	bottom = Color.NONE

	def	__init__(self, top, front, right, back, left, bottom):
		self.top = top
		self.front = front
		self.right = right
		self.back = back
		self.left = left
		self.bottom = bottom

	def colors(self, rotation):
		if (rotation == 1):
			return [self.top, self.front, self.right, self.back, self.left, self.bottom]
		elif (rotation == 2):
			return [self.top, self.left, self.front, self.right, self.back, self.bottom]
		elif (rotation == 3):
			return [self.top, self.back, self.left, self.front, self.right, self.bottom]
		elif (rotation == 4):
			return [self.top, self.right, self.back, self.left, self.front, self.bottom]
		elif (rotation == 5):
			return [self.front, self.bottom, self.right, self.top, self.left, self.back]
		elif (rotation == 6):
			return [self.front, self.left, self.bottom, self.right, self.top, self.back]
		elif (rotation == 7):
			return [self.front, self.top, self.left, self.bottom, self.right, self.back]
		elif (rotation == 8):
			return [self.front, self.right, self.top, self.left, self.bottom, self.back]
		elif (rotation == 9):
			return [self.bottom, self.back, self.right, self.front, self.left, self.top]
		elif (rotation == 10):
			return [self.bottom, self.left, self.back, self.right, self.front, self.top]
		elif (rotation == 11):
			return [self.bottom, self.front, self.left, self.back, self.right, self.top]
		elif (rotation == 12):
			return [self.bottom, self.right, self.front, self.left, self.back, self.top]
		elif (rotation == 13):
			return [self.back, self.top, self.right, self.bottom, self.left, self.front]
		elif (rotation == 14):
			return [self.back, self.left, self.top, self.right, self.bottom, self.front]
		elif (rotation == 15):
			return [self.back, self.bottom, self.left, self.top, self.right, self.front]
		elif (rotation == 16):
			return [self.back, self.right, self.bottom, self.left, self.top, self.front]
		elif (rotation == 17):
			return [self.left, self.bottom, self.front, self.top, self.back, self.right]
		elif (rotation == 18):
			return [self.left, self.back, self.bottom, self.front, self.top, self.right]
		elif (rotation == 19):
			return [self.left, self.top, self.back, self.bottom, self.front, self.right]
		elif (rotation == 20):
			return [self.left, self.front, self.top, self.back, self.bottom, self.right]
		elif (rotation == 21):
			return [self.right, self.top, self.front, self.bottom, self.back, self.left]
		elif (rotation == 22):
			return [self.right, self.back, self.top, self.front, self.bottom, self.left]
		elif (rotation == 23):
			return [self.right, self.bottom, self.back, self.top, self.front, self.left]
		elif (rotation == 24):
			return [self.right, self.front, self.bottom, self.back, self.top, self.left]
		else:
			return None


class Individ:
	def __init__(self, size):
		self.size = size
		self.rotations = [randint(1, 24) for _ in range(size)]
		self.f = 0
	
	def fitness(self, problem):
		currentCubes = [problem.cubes[i].colors(self.rotations[i]) for i in range(self.size)]
		result = [list(g) for k, g in groupby(currentCubes, key = lambda x: x[1:5])]
		self.f = max(list(map(len, result)))

	def mutate(self, probability):
		i = randint(0, self.size - 1)
		if random() < probability:
			self.rotations[i] = randint(1, 24)
	
	@staticmethod
	def crossover(mother, father, probability):
		child = Individ(mother.size)
		child.rotations = [mother.rotations[i] if random() < probability else father.rotations[i] for i in range(mother.size)]
		return child

class Population:
	def __init__(self, nrInd, problem):
		self.nrInd = nrInd
		self.problem = problem
		self.array = [Individ(problem.n) for _ in range(nrInd)]

	def evaluate(self):
		for individ in self.array:
			individ.fitness(self.problem)

	def selection(self, maxInd):
		if maxInd < self.nrInd:
			self.nrInd = maxInd
			self.array = sorted(self.array, key = lambda Individ: Individ.f, reverse = True)
			self.array = self.array[:maxInd]

	def reunion(self, other):
		self.nrInd += other.nrInd
		self.array = self.array + other.array

	def best(self, maxInd):
		sortedArray = sorted(self.array, key = lambda Individ: Individ.f, reverse = True)
		return sortedArray[:maxInd]

class Problem:
	def __init__(self, fileName):
		self.n = 0
		self.cubes = []
		self.loadData(fileName)

	def loadData(self, fileName):
		print("Loading data...")
		try:
			f = open(fileName, "r")
			for line in f:
				c = list(map(int, line.split()))
				cube = Cube(Color(c[0]), Color(c[1]), Color(c[2]), Color(c[3]), Color(c[4]), Color(c[5]))
				self.cubes.append(cube)
			self.n = len(self.cubes)
			print("Loaded data: ")
			print("Number of cubes: " + str(self.n))
		except Exception as e:
			print(str(e))
			sys.exit(1)

class Algorithm:
	def __init__(self, fileName, paramFileName = "param.in", nrInd = 50, nrGen = 100):
		self.problem = Problem(fileName)
		self.mutate_probability = 0.5
		self.crossover_probability = 0.5
		self.readParameters(paramFileName)
		print("The crossover probability is: " + str(self.crossover_probability))
		print("The mutation probability is: " + str(self.mutate_probability))

		self.nrInd = nrInd
		self.nrGen = nrGen
		self.population = Population(self.nrInd, self.problem)

		self.average = []

	def readParameters(self, paramFileName):
		try:
			f = open(paramFileName, "r")
			for line in f:
				(key, value) = line.split("=")
				if key == "mutate_probability":
					self.mutate_probability = float(value)
				elif key == "crossover_probability":
					self.crossover_probability = float(value)
				else:
					raise Exception("Corupt key in parameters file")
		except Exception as e:
			print(str(e))
			sys.exit(1)


	def iteration(self):
		parents = range(self.nrInd)
		nrChilds = len(parents) // 2
		offspring = Population(nrChilds, self.problem)
		for i in range(nrChilds):
			offspring.array[i] = Individ.crossover(self.population.array[i << 1], self.population.array[(i << 1) | 1], self.crossover_probability)
			offspring.array[i].mutate(self.mutate_probability)
		offspring.evaluate()
		self.population.reunion(offspring)
		self.population.selection(self.nrInd)

	def run(self):
		for currentIter in range(self.nrGen):
			self.iteration()
			self.statistics(currentIter)
		return (self.population.best(10), self.average)

	def statistics(self, currentIter):
		bst = self.population.best(1)[0]
		self.average.append(bst)
		print("The best fittness for generation " + str(currentIter) + " is: " + str(bst.f))
		

class Application:
	def __init__(self, fileName):
		self.fileName = fileName

	def main(self):
		self.algorithm = Algorithm(self.fileName)
		(best, average) = self.algorithm.run()
		print ("The best is: " + str(best[0].f))
		avg = sum(x.f for x in average) / len(average)
		print ("The average is: " + str(avg))
		print ("The standard deviation is: " + str(math.sqrt(sum(((x.f - avg) ** 2) for x in average) / len(average)))) 


app = Application("cubes1.in")
app.main()
