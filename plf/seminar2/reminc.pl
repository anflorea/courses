remInc([], X, []).
remInc([H | []], X, [H | []]) :-
	H =< X.

remInc([H | []], X, []) :-
	H > X.	
	

remInc([H1 | T], H2, X) :-
	H1 > H2,
	remInc(T, H1, X).

remInc([H1 | T], H2, [H1 | Tr]) :-
	H1 =< H2,
	remInc(T, H1, Tr).

rem([H1, H2 | T], Y) :-
	H1 =< H2,
	remInc(T, H2, Y).

rem([H1, H2 | T], [H1 | X]) :-
	H1 > H2,
	remInc([H2 | T], H1, X).

