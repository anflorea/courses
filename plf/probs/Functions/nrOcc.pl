% nrOcc(E-elem, L-list, N-nb of occurences of E in L)
% flow model: (i i o), (i i i)
nrOcc(_, [], 0).
nrOcc(E, [H|T], Nr) :-
	E = H,
	nrOcc(E, T, N),
	Nr is N + 1.

nrOcc(E, [H|T], Nr) :-
	E \= H,
	nrOcc(E, T, Nr).
