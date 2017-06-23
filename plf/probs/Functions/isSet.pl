notin(A,[]).
notin(A,[B|C]) :- 
	A\=B, 
	notin(A,C).

isSet([]).
isSet([_]).
isSet([A|B]) :- 
	notin(A,B), 
	isSet(B).