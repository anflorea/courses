# Requirements

1. Given a sequence of n numbers, compute the sums of the first k numbers, for each k between 1 and n. Parallelize the computations, to optimize for low latency on a large number of processors. Use at most 2*n additions, but no more than 2*log(n) additions on each computation path from inputs to an output. Example: if the input sequence is 1 5 2 4, then the output should be 1 6 8 12.

2. Add n big numbers. We want the result to be obtained digit by digit, starting with the least significant one, and as soon as possible. For this reason, you should use n-1 threads, each adding two numbers. Each thread should pass the result to the next thread. Arrange the threads in a binary tree. Each thread should pass the sum to the next thread through a queue, digit by digit.

# Documentation

I solved the two problems using `C++`. The performance is measured on my personal computer with the following config:

```
MacBook Pro (15-inch, mid 2015)
Processor: 2.2 GHz Intel Core i7
Memory: 16 GB 1600 MHZ DDR3
Graphics: Intel Iris Pro 1536 MB
```

## Algorithms

## Syncronization

## Performance measurements

The programs will tell you the number of clock cycles it took and the number of seconds.
