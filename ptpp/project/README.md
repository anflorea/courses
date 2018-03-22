# Huge Numbers Multiplication

## Huge Numbers

Huge numbers are numbers that are significantly larger than those ordinarily used. The term typically refers to large positive integers, or more generally, large positive real numbers, but it may also be used in other contexts.

## Requirement

Perform the multiplication of two huge numbers.

### Input Data

The input of the program will be taken from a text file. The file will contain two huge numbers on the first two lines of the file, each number on one line. Each number will be followed by a `\n` character and will not contain any characters between it's digits. The file will have at least an empty line after the huge numbers.

### Output Data

The output of the program will be the result of the multiplication of the two huge numbers. The result will be written in a text file.

## Iterative Implemetation

The iterative implementation can be found [here](iterative.cpp).  
The program accepts two parameters. The first one is the input file and the second one is the output file (the file in which the result will be stored).  
The huge numbers are kept in memory as C-like int arrays. The length of one huge number can be fount on position `0` of the array. On the next positions of the array, the digits of the number can be found, in reverse order.  

The algorithm used to multiply the huge numbers is the classical one. Each digit `i` from the first number is multiplied by each digit `j` from the second number and added to the result on position `i + j - 1`. After this, one more iteration is done to ensure that only single digit numbers are stored on each position.  

The overall complexity of the iterative implementation is:
```
O(n * m)
```
