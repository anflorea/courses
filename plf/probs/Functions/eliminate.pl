%a. Write a predicate to remove all occurrences of a certain atom from a list.

%elimin(E-element,L-initial list,R-resulted list)
	
elimin(E,[],[]).

elimin(E,[E|T],L):-
  	!,
  	elimin(E,T,L).

elimin(E,[H|T],[H|L]):-
  	elimin(E,T,L).