%ex 3 : Number of occurences of an element in a list
%
%count(L-list,e-element,I-result)
%L- list of elements
%e-constant value, the value we are looking for
%I- result, integer number, the number of occurences
%
%flow model: (i,i,o)
%
%%ex 4 :  Eliminate all elements with 1 occurence
%
%elim(L-initial list, Lc- copy of initial list, Lr- result list)
%flow model: (i,i,o)


count([],_,0).

count([H|T],E,I):-
	H=E,
	count(T,E,I2),
	I is I2+1.

count([H|T],E,I):-
	H\=E,
	count(T,E,I).



elim([],[],[]).
elim([H|T],Lc,Lr):-
	count(Lc,H,N),
	N=1,
	elim(T,Lc,Lr).
elim([H|T],Lc,Lr):-
	count(Lc,H,N),
	N\=1,
	elim(T,Lc,Lr).




