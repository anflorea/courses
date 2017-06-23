from random import shuffle
import math

n = 10
blocks = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
edges = [3, 2, 5, 7, 6, 10, 8, 9, 11, 1]
color = ["red", "blue", "yellow", "red", "red", "green", "blue", "green", "red", "yellow"]

k = 6

def get_random_tower(k):
	shuffle(blocks)
	return blocks[0:k]

def calculate_f(solution, k):
	res = 0
	for i in range(0, k - 1):
		if (edges[solution[i] - 1] < edges[solution[i + 1] - 1]):
			res += 1
		if (color[solution[i] - 1] == color[solution[i + 1] - 1]):
			res += 1
	
	return res


def generate_mean_and_deviation():
	partials = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
	for i in range(0, n):
		partials[i] = get_random_tower(k)
	theSum = 0
	for i in range(0, n):
		theSum += calculate_f(partials[i], k)

	mean = theSum / n
	print "The mean is: ", mean

	theSum = 0
	for i in range(0, n):
		theSum += (calculate_f(partials[i], k) - mean) ** 2

	std = math.sqrt(theSum / n)

	print "The standard deviation is: ", std

solution1 = get_random_tower(k)
print "random block: ", solution1
print "blocks edges: "
for i in range(0, k):
	print edges[solution1[i] - 1]
print "blocks colors: "
for i in range(0, k):
	print color[solution1[i] - 1]

quality = calculate_f(solution1, k)

if (quality == 0):
	print "It is indeed a viable solution"
else:
	print "It is not a viable solution"

print "measure of quality: ", quality

generate_mean_and_deviation()

