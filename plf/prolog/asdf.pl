candidat([H|_], H).
candidat([_|T], E) :-
	candidat(T, E).

generare(_, Cand, 0, Cand).
generare(L, [H|Cand], 0, R) :-
	candidat(L, E),
	not(candidat([H|Cand], E)),
	E > H,
	generare(L, [E, H|Cand], 0, R).
generare(L, [H|Cand], 1, R) :-
	candidat(L, E),
	not(candidat([H|Cand], E)),
	E < H,
	generare(L, [E, H|Cand], 1, R).
generare(L, [H|Cand], 1, R) :-
	candidat(L, E),
	not(candidat([H|Cand], E)),
	E > H,
	generare(L, [E, H|Cand], 0, R).

start(L, Rez) :-
	candidat(L, E1),
	candidat(L, E2),
	E1 < E2,
	generare(L, [E1, E2], 1, Rez).

startAll(L, Rez) :-
	findall(R, start(L, R), Rez).
