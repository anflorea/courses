# Scanner

Considering a small programming language (that we shall call mini-langauge),
you have to write a scanner (lexical analyzer).

## Scanner implementation

The scanner input will be a text file containind the source program, and will produce as output the following:
- PIF - Program Internal Form
- ST - Symbol Table

In addition, the program should be able to determine the lexical errors, specifying
the location, and, if possible, the type of the error.

The scanner assignment will be:
### 1. Identifiers:
* a. length at most 8 characters
### 2. Symbol Table:
* b. separate tables for indentifiers, respectively constants
### 3. Symbol Table Organization:
* b. lexicographically binary tree

## Scanner Documentation

### `read` function

 * `input:` the name of a file
 * reads the content of the file
 * gets rid of the `\n`/`\t` charaters
 * `output:` a string with all the data in the file

### `normalize` function

 * `input:` a string with the file data
 * makes sure each opperator (+, -, <=, etc..) is surrounded by spaces
 * `output:` a string with spaces around the operators

### `validate_token` function

 * `input:` a token from the program
 * makes sure it doesn't contain unknown characters
 * if the token is not a keyword, it assumes it is an id or a constant
 * makes sure the ids names are correct
 * makes sure a token is no more than 8 characters long
 * `output:` "OK" if the token is valid, name of the error otherwise

### `parse` function

 * `input:` a string with the normalizes program data
 * splits the data by spaces
 * for each token it checks if it's valid and adds the ids and constants to binary trees
 * constructs the program internal form
 * `output:` id's binary tree, constant's binary tree, program internal form
