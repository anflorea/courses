# Requirements

Perform the multiplication of 2 polynomials. Use both the regular O(n2) algorithm and the Karatsuba algorithm, and each in both the sequencial form and a parallelized form. Compare the 4 variants.

# Documentation

I solved the two problems using `C++`. The performance is measured on my personal computer with the following config:

```
MacBook Pro (15-inch, mid 2015)
Processor: 2.2 GHz Intel Core i7
Memory: 16 GB 1600 MHZ DDR3
Graphics: Intel Iris Pro 1536 MB
```

## Algorithms

The clasical algotithm requires n2 single-digit products to perform the polynomials multiplication.

The Karatsuba algorithm is implemented recursivly.

## Synchronization

No synchornization is needed because everything is independent in terms of what variables they modify.

## Performance measurements

The programs will tell you the number of clock cycles it took and the number of seconds.

Run `run.sh` to test the programs.
