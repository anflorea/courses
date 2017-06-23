get_max([RES], RES).

get_max([H | T], RES) :-
	get_max(T, X),
	(H > X -> RES = H; RES = X).

isList([_ | _]).
isList([]).

isEqual(A, A).


get_pos([], MAX, POS, []).

get_pos([H | T], H, POS, [POS | Tr]) :-
	NEW_POS is POS + 1,
	get_pos(T, H, NEW_POS, Tr).

get_pos([H | T], MAX, POS, LIST) :-
	NEW_POS is POS + 1,
	get_pos(T, MAX, NEW_POS, LIST).



get_max_pos(LIST, RES) :-
	get_max(LIST, X),
	get_pos(LIST, X, 1, RES).

max_pos([], []).

max_pos([H | T], [Hr | Tr]) :-
	%(isList(H) -> get_max_pos(H, X), Hr = X; Hr is H),
	isList(H),
	get_max_pos(H, Hr),
	max_pos(T, Tr).

max_pos([H | T], [H | Tr]) :-
	number(H),
	max_pos(T, Tr).

% max_pos([l1..l2]) = get_max_pos(H), max_pos([l2...ln]) if l1 is a list
%					  max_pos([l2...ln]) if l1 is an integer
%
%
% flow model: (i, o)
% First argument: the initial list
% Second argument: the final result
