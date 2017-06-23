count([], X, 0).

count([H | T], X, Y) :-
	H = X,
	count(T, X, Y1),
	Y is Y1 + 1.

count([H | T], X, Y) :-
	H \= X,
	count(T, X, Y).

remove([], [], X).

remove([H | T], X, L) :-
	count(L, H, R),
	R = 1, 
	remove(T, X, L).

remove([H | T], [H | Tr], L) :-
	count(L, H, R),
	R \= 1,
	remove(T, Tr, L).

remUniq(X, Y) :-
	remove(X, Y, X).
	
	


