multiply([],X,[]).

multiply([H|T],X,[Hr|Tr]) :-
    write(Hr), nl, 
	Hr is H*X,
	multiply(T,X,Tr).