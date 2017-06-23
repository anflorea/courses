grater(A, B) :-
	A > B.

less(A, B) :-
	A < B.

put_true(X) :-
	X = true.

test(A, X) :-
	(A == 0 -> put_true(X); X = false).

inner_f(LAST, CURRENT, [], X) :-
	(CURRENT == 2 -> X = true; X = false).

inner_f(LAST, CURRENT, [H | T], X) :-
	(CURRENT == 1 -> (LAST > H -> CURRENT = 1; CURRENT = 2); (CURRENT == 2 -> (LAST < H -> CURRENT = 2; CURRENT = 0))),
	inner_f(H, CURRENT, T, X).

%inner_f(LAST, 1, [H | T], X) :-
%	NEXT = 1,

aspect_vale([], X) :-
	X = false.

aspect_vale([H | T], X) :-
	inner_f(H, 1, T, X).
	
