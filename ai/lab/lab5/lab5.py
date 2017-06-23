import numpy as np

class FuzzyVariable:
	def __init__(self):
		self.labels = {}
		self.value = 0

	def toDiscrete(self):
		ret = {}
		graph = self.labels
		value = self.value
		for key in graph.keys():
			for i in range(len(graph[key]) - 1):
				if (graph[key][i][0] <= value and value <= graph[key][i + 1][0]):
					if (graph[key][i][0] == -np.inf):
						ret[key] = graph[key][i][1]
						continue
					if (graph[key][i + 1][0] == np.inf):
						ret[key] = graph[key][i + 1][1]
						continue
					deltaY = graph[key][i + 1][1] - graph[key][i][1]
					deltaX = graph[key][i + 1][0] - graph[key][i][0]
					ret[key] = graph[key][i][1] + ((value - graph[key][i][0]) / deltaX) * deltaY
		return ret

class Temperature(FuzzyVariable):
	def __init__(self, value):
		self.value = value
		self.labels = {
			'cold': [(-np.inf, 1.0), (30.0, 1.0), (50.0, 0.0), (np.inf, 0.0)],
			'cool': [(-np.inf, 0.0), (30.0, 0.0), (50.0, 1.0), (70.0, 0.0), (np.inf, 0.0)],
			'moderate': [(-np.inf, 0.0), (60.0, 0.0), (70.0, 1.0), (80.0, 0.0), (np.inf, 0.0)],
			'hot': [(-np.inf, 0.0), (70.0, 0.0), (90.0, 1.0), (110.0, 0.0), (np.inf, 0.0)],
			'very hot': [(-np.inf, 0.0), (90.0, 0.0), (110.0, 1.0), (np.inf, 1.0)]
		}

class Capacity(FuzzyVariable):
	def __init__(self, value):
		self.value = value
		self.labels = {
			'small': [(-np.inf, 1), (0, 1), (5, 0), (np.inf, 0)],
			'medium': [(-np.inf, 0), (3, 0), (5, 1), (7, 0), (np.inf, 0)],
			'high': [(-np.inf, 0), (5, 0), (10, 1), (np.inf, 1)]
		}

class Power(FuzzyVariable):
	def __init__(self, value):
		self.value = value
		self.labels = {
			'small': [(-np.inf, 1), (0, 1), (10, 0), (np.inf, 0)],
			'medium': [(-np.inf, 0), (5, 0), (10, 1), (15, 0), (np.inf, 0)],
			'high': [(-np.inf, 0), (10, 0), (20, 1), (np.inf, 1)]
		}

class Ruler:
	def __init__(self):
		self.rules = {
			'cold': {
				'small': 'small',
				'medium': 'medium',
				'high': 'high'
			},
			'cool': {
				'small': 'small',
				'medium': 'medium',
				'high': 'high'
			},
			'moderate': {
				'small': 'small',
				'medium': 'small',
				'high': 'small'
			},
			'hot': {
				'small': 'small',
				'medium': 'small',
				'high': 'small'
			},
			'very hot': {
				'small': 'small',
				'medium': 'small',
				'high': 'small'
			}
		}

	def evaluate(self, t, c):
		tempDiscrete = t.toDiscrete()
		capDiscrete = c.toDiscrete()
		resDisc = {}
		print(tempDiscrete)
		print(capDiscrete)
		for tKey, tValue in tempDiscrete.items():
			for cKey, cValue in capDiscrete.items():
				res = self.rules[tKey][cKey]
				val = min(tValue, cValue)
				if res in resDisc:
					resDisc[res] = max(resDisc[res], val)
				else:
					resDisc[res] = val
		return resDisc

class Controller:
	def __init__(self, temperature, capacity):
		self.rules = Ruler()
		self.temp = Temperature(temperature)
		self.cap = Capacity(capacity)

	def solve(self):
		agg = self.rules.evaluate(self.temp, self.cap)
		print(agg)
		print(sorted(list(agg.items()), key = lambda x: x[1])[-1][0])

c = Controller(40.0, 9.0)
c.solve()
