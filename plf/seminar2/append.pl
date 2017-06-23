append([], X, [X]).

append([H | T], X, [H | Tr]) :-
	append(T, X, Tr).
