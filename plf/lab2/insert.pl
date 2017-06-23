insert(Val, List, 0, [Val | List]).

insert(Val, [H | T], Pos, [H | Tr]) :-
	Pos1 is Pos - 1,
	insert(Val, T, Pos1, Tr).

% insert(Value, [l1..ln], Position) = [Value, l1..ln] if the position is 0
%								  insert(Val, [l2..ln], Position - 1) if the position is > 0
% flow model: (i, i, i, o)
% Val: Value to be inserted
% List: Initial list
% Pos: the position to insert to
% X: Resulted list
