%1.
%a. Sort a list with removing the double values. 
%E.g.: [4 2 6 2 3 4] --> [2 3 4 6]
%b. For a heterogeneous list, formed from integer numbers 
%and list of numbers, write a predicate to sort every
%sublist with removing the doubles.
%Eg.: [1, 2, [4, 1, 4], 3, 6, [7, 10, 1, 3, 9], 5, [1, 1, 1], 7]
%=> [1, 2, [1, 4], 3, 6, [1, 3, 7, 9, 10], 5, [1], 7].

%domains
  %el = integer
  %list = el*.
  
%predicates
   %elimin(el,list,list) %(i,i,o)
   %inserare(el,list,list)  %(i,i,o)
   %sort(list,list).         %(i,o)

elim(_,[],[]).

elim(E,[E|T],L):-!,
elim(E,T,L).

elim(E, [H|T],[H|L]):-
elim(E,T,L).

inserare(E,[],[E]):-!.

inserare(E,[H|T],[H|L]):-
	E>=H,!,
	inserare(E,T,L).

inserare(E,[H|T],[E|[H|T]]):-!.

sorting([],[]):-!.
sorting([H|T],L):-
	elim(H,T,L1),
	sorting(L1,L2),
	inserare(H,L2,L).
