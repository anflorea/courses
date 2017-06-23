%is list
is_list1([]).
is_list1([_|T]):-
	is_list1(T).