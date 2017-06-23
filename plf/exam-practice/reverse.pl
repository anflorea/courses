appnd([], E, [E]).

appnd([H | T], E, [H | Tr]) :-
	appnd(T, E, Tr).


reve([], []).

reve([H | T], X) :-
	number(H),
	reve(T, Y),
	appnd(Y, H, X).

reve([H | T], X) :-
	is_list(H),
	reve(H, Hr),
	reve(T, Y),
	appnd(Y, Hr, X).

back(0, _, []).

back(N, [X | T], [X | Tr]) :-
	N > 0,
	N1 is N - 1,
	back(N1, T, Tr).

back(N, [_ | T], Tr) :-
	N > 0,
	back(N, T, Tr).

main(N, L, X) :-
	findall(Y, back(N, L, Y), X).
