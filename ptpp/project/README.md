# Huge Numbers Multiplication

## Huge Numbers

Huge numbers are numbers that are significantly larger than those ordinarily used. The term typically refers to large positive integers, or more generally, large positive real numbers, but it may also be used in other contexts.

## Requirement

Perform the multiplication of two huge numbers.

### Input Data

The input of the program will be taken from a text file. The file will contain two huge numbers on the first two lines of the file, each number on one line. Each number will be followed by a `\n` character and will not contain any characters between it's digits. The huge numbers digits will be in their usual human-readable order, the one that we got used to from mathematics. The file will have at least an empty line after the huge numbers.

### Output Data

The output of the program will be the result of the multiplication of the two huge numbers. The result will be written in a text file. The output number's digits will also be in the usual human-readable order.

## Sequential Implemetation

The sequantial implementation can be found [here](iterative.cpp).  
The program accepts two parameters. The first one is the input file and the second one is the output file (the file in which the result will be stored).  
The huge numbers are kept in memory as C-like int arrays. The length of one huge number can be fount on position `0` of the array. On the next positions of the array, the digits of the number can be found, in reverse order.  

* Example:

  For the input number `123`, the in-memory array will look like this:

|0|1|2|3|
|-|-|-|-|
|3|3|2|1|


The algorithm used to multiply the huge numbers is the classical way. Each digit `i` from the first number is multiplied by each digit `j` from the second number and added to the result on position `i + j - 1`. After this, one more iteration is done to ensure that only single digit numbers are stored on each position.  

The overall complexity of the iterative implementation is:
```
O(n * m)
```

## Parallel Implementation

The parallel implementation can be found [here](parallel.cpp).
The program accepts two parameters. The first one is the input file and the second one is the output file (the file in which the result will be stored).  
The huge numbers are kept in memory as C-like int arrays. The length of one huge number can be fount on position `0` of the array. On the next positions of the array, the digits of the number can be found, in reverse order.  

For computing the result, the program uses the same method as the sequential implementation, exept that it equaly balances the computations that need to be done among all the threads and each thread, after computing it's calculations, adds the result to the final array by syncronizing with a mutex.

The number of threads is stored in the variable T, but it can be easily changed by setting the `NO_THREADS` enviroment variable to a suitable integer.

# Testing

## Tests generation

Test cases can be generated through a shell script that is available [here](generate_tests.sh). Once runned, the script will generate a bunch of test cases in a folder named tests. The script can be personalized as for how many test cases it generates and how much bigger do they get, by modifing the variables `TESTS_NUMBER` and `GROWTH_FACTOR`. The tests generator will generate for each test two huge numbers of the same digits numbers and for each test, it will also generate a file containing the correct solution.

## Tests running

Tests can be run throungh a shell script that is available [here](run_tests.sh). By running the script, the sources for both the iterative and parallel implementation will be compiled. After that, the script will run both implementations for each test case present in the `tests/` folder and will print on the screen the time taken for each test case along with an appropriate error message if the test case result was not successful.

# Conclusions

This project has helped me to better understand parallelization along with it's advantages and disadvantages. As it turns out, even though parallelization is usually the best solution for optimizations, some implementations are better off left iterative as the case for this particular implementation of huge numbers multiplication. Using the classical dummy algorithm requires a lot of simple operations and a parallel implementation can not take advantages of the processors computation power because it requires too much time for syncronizations.  

Of course that there are some implementations of huge numbers multiplication that will perform better when parallelized (some examples of such implementation are [Karatsuba's Algorithm](https://en.wikipedia.org/wiki/Karatsuba_algorithm) or [Fast Fourier transorm](https://en.wikipedia.org/wiki/Fast_Fourier_transform) ), but my purpose of this project was to compare the iterative and parallel implemtations of the classical dummy algorithm.
