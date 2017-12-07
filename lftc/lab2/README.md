# Finite automata

## Options

* Identifiers: a) length at most 8 characters
* Symbol Table: b) separate tables for identifiers, respectively constants
* Symbol Table Organization: b) lexicographically binary tree

## File content description

* The file that describes the finite automata is a **JSON object**
* The JSON object **must** contain:
  1. field **`"alphabet"`**
      - contains a string containing all the letters from the alphabet
  2. field **`"states"`**
      - containing an array of **states**
      - states can be everything from **strings** to **integers** or other **hashable** objects
	  - a "state" object contains:
	  	- **`"label"`** field: the name of the state
		- **`"start"`** field: a boolean that sais if the current state can be a starting one
		- **`"end"`** field: a boolean that sais if the current state can be an ending one
		- **`"transitions"`** field: an array of objects of type:
			- **`"elements"`** field: the elements that can appear in the current state
			- **`"new_state"`** field: the next state that this transition can go to

* Example
```javascript
{
  "alphabet": "abcfeghijklmnopqrstuvwxyz",
  "states": [
  	{
      "label": "q0",
      "start": true,
      "end": false,
	  "transitions": [
	  		{
				"elements": "abcde",
				"new_state": "q2"
			}
		]
    }, {
      "label": "q1",
      "start": false,
      "end": false,
	  "transitions": [
	  		{
				"elements": "fghi",
				"new_state": "exit"
			}
		]
    }, {
      "label": "exit",
      "start": false,
      "end": true,
	  "transitions": []
	}
  ]
}
```

### Integer Literals

An integer literal is a primary expression of the form
* decimal-literal integer-suffix(optional)  (1)
* octal-literal integer-suffix(optional)  (2)
* hex-literal integer-suffix(optional)  (3)
* binary-literal integer-suffix(optional) (4) (since C++14)

## Functions and procedures

* read(filename): reads input from a file and returns the text normalized

* parse(data): parses the input data using finite automatas and returns 2 Binary trees (identifiers and constants) and the program internal form
