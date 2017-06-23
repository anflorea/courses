multiply(X, [], []).

multiply(X, [H | T], [Hr | Tr]) :-
	Hr is H * X,
	multiply(X, T, Tr).
