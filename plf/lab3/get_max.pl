get_max([RES], RES).

get_max([H | T], RES) :-
	get_max(T, X),
	(H > X -> RES = H; RES = X).

% get_max([l1..ln]) = l1 if the list has only one element
%					  MAX(l1, get_max([l2..ln])) if the list has more the one
%					  element
%
% flow model: (i, o)
% First argument: The initial list
% Second argument: The maximum of the list
