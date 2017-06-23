%%push_back(L,E,R)
%L-list , E- element we append, R- result list
%flow model : (i,i,o)
push_back([],E,[E]).
push_back([H|T],E,[H|Tr]):-
	push_back(T,E,Tr).