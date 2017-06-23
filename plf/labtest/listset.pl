% construct(l1..ln, a1..an) =		|  construct(l2..ln, a1..an)  if l1 is already contained in the set
%									|
%									|  construct(l2..ln, a1..an l1) if l1 is not contained in the set
%									|
% flow model: (i, o)
% First Argument: The initial list
% Seccond Argument: The resulted set

member([H | T], H) :- !.
member([H | T], X) :- 
	member(T, X).
	
addLast([], X, [X]) :- !.
addLast([H | T], X, [H | Tr]) :-
	addLast(T, X, Tr).

construct([], X, X) :- !.
construct([H | T], Tr, Rez) :-
	not(member(Tr, H)),
	addLast(Tr, H, Res),
	construct(T, Res, Rez), !.
construct([H | T], Tr, Rez) :-
	construct(T, Tr, Rez).

setConstruct(L, Rez) :-
	construct(L, [], Rez).
