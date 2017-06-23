% 16.11.2016

% PROLOG 20. Sa se determine pozitiile elementului maxim
% dintr-o lista liniara. De exemplu:
% [10, 14, 12, 13, 14] -> [2, 5]

% max_elem(L:list, E:atom)
% flow model: (i o), (i i)
max_elem([E], E).
max_elem([H1,H2|T], E) :-
	H1 >= H2,
	max_elem([H1|T], E), !.
max_elem([H1,H2|T], E) :-
	H2 > H1,
	max_elem([H2|T], E).

% max_positions(L:list, M:int, I:int, R:list)
% flow model: (i i i o), (i i i i)
max_positions([], _, _, []).
max_positions([M|T], M, I, [I|Tr]) :-
	NewI is I + 1,
	max_positions(T, M, NewI, Tr).
max_positions([H|T], M, I, Tr) :-
	H =\= M,
	NewI is I + 1,
	max_positions(T, M, NewI, Tr).

% main(L:list, R:list)
% flow model: (i o), (i i)
main([], []).
main(L, R) :-
	max_elem(L, M),
	max_positions(L, M, 1, R).