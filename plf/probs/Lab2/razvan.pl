%Pozitile elementului maxim/minim intr-o lista de numere

%elMax(L-list of numbers, M-max)

min_elem([E], E).
min_elem([H1,H2|T], E) :-
	H1 =< H2,
	min_elem([H1|T], E), !.
min_elem([H1,H2|T], E) :-
	H2 < H1,
	min_elem([H2|T], E).


%posMax(L-list,E-max element,cp-current pos,LP-list of positions,FL-final list of pos)

posMin([],_,_,[]).

%posMax([],_,_,FL):-
%	FL=LP.


posMin([M|T],M,C,[C|Tr]):-
	%H = E,
	C1 is C + 1,
	posMin(T,M,C1,Tr).

posMin([H|T],M,C,Tr):-
	H =\= M,
	C1 is C + 1,
	posMin(T,M,C1,Tr).


positions([],[]).

positions(L,F):-
	min_elem(L,M),
	write(M),
	posMin(L,M,1,F).




