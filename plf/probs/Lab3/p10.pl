%divisible(A,B-numbers)
%flow model (i,i)

divisible(A,B):-
	0 is A mod B,!.
divisible(A,B):-
	A > B + 1,
	divisible(A, B + 1).

%isPrime(X-number)
%flow model: (i)

isPrime(2) :- 
	true,!.
isPrime(X) :- 
	X < 2,!,false.
isPrime(X) :- 
	\+(divisible(X, 2)).

%transformSublist(L-list, R-resulted list)
%flow model (i,o)

transformSublist([],[]).

transformSublist([H|T],[H1,H1|R]):-
	isPrime(H),!,
	H1 is H,
	transformSublist(T,R).

transformSublist([H|T],[H1|R]):-
	not(isPrime(H)),!,
	H1 is H,
	transformSublist(T,R).

%modif(L-list, Res-resulted list)
%flow model (i,o)

modif([], []).
modif([H|T], [HR|TR]):-
	is_list(H),
	transformSublist(H, HR),!,
	modif(T, TR).
modif([H|T],[H|TR]):-
	modif(T,TR).
