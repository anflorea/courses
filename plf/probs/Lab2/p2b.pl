%b. Define a predicate to produce a list of pairs (atom n) from an initial list of atoms. 
%In this initial list atom has n occurrences.
%Eg.: numberatom([1, 2, 1, 2, 1, 3, 1], X) => X = [[1, 4], [2, 2], [3, 1]].


% nrOcc(E-elem, L-list, N-nb of occurences of E in L)
% flow model: (i i o), (i i i)

push_back([],E,[E]).
push_back([H|T],E,[H|Tr]):-
	push_back(T,E,Tr).

nrOcc(_, [], 0).
nrOcc(E, [H|T], Nr) :-
	E = H,
	nrOcc(E, T, N),
	Nr is N + 1.

nrOcc(E, [H|T], Nr) :-
	E \= H,
	nrOcc(E, T, Nr).

%elimin(E-element,L-initial list,R-resulted list)
	
elimin(V,[],[]).

elimin(V,[V|T],L):-
  	!,
  	elimin(V,T,L).

elimin(V,[H|T],[H|L]):-
  	elimin(V,T,L).

pairs([],[],[]).

pairs([],C,FL):-
	FL=C.

pairs([H|T],C,FL):-
	nrOcc(H,T,Nr),
	Nr1 is Nr + 1,
	elimin(H,[H|T],NL),
	L = [H,Nr1],
	%write(L),
	push_back(C,L,NC),
	%write(FIL),
	pairs(NL,NC,FL).	
	