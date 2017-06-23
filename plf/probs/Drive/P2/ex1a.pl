% Sort a list with removing the double values.
% E.g.: [4 2 6 2 3 4] --> [2 3 4 6]

% remove_elem(E-elem, L-list, R-result list)
% flow model: (i i o), (i i i)
remove_elem(_, [], []).
remove_elem(E, [H|T], Tr) :-
	E = H,
	remove_elem(E, T, Tr).
remove_elem(E, [H|T], [H|Tr]) :-
	E \= H,
	remove_elem(E, T, Tr).

% set(L-list, R-result list); R is obtained from L by removing doubles
% flow model: (i o), (i i)
set([], []).
set([H|T], [H|X]) :-
	remove_elem(H, T, Y),
	set(Y, X).

% insert(E-elem, L-sorted list, R-result list)
% R = the result of inserting the element E into the sorted list L
% flow model: (i i o), (i i i)
insert(E, [], [E]).
insert(E, [H|T], [E|Tr]) :-
	E =< H,
	insert(H, T, Tr).
insert(E, [H|T], [H|Tr]) :-
	E > H,
	insert(E, T, Tr).

% insertion_sort(L-initial list, R-result list)
% flow model: (i o), (i i)
insertion_sort([], []).
insertion_sort([H|T], R) :-
	insertion_sort(T, Rt),
	insert(H, Rt, R).

% sort_with_removing_doubles(L-list, R-result list)
% flow model: (i o), (i i)
sort_with_removing_doubles([], []).
sort_with_removing_doubles(L, R) :-
	set(L, Ls),
	insertion_sort(Ls, R).


% b) For a heterogeneous list, formed from integer numbers and list of
% numbers, write a predicate to sort every sublist with removing the
% doubles.

% Eg.: [1, 2, [4, 1, 4], 3, 6, [7, 10, 1, 3, 9], 5, [1, 1, 1], 7] =>
% [1, 2, [1, 4], 3, 6, [1, 3, 7, 9, 10], 5, [1], 7].

% sort_sublists(L-list, R-result list)
% flow model: (i o), (i i)
sort_sublists([], []).
sort_sublists([H|T], [Hr|Tr]) :-
	is_list(H),
	sort_with_removing_doubles(H, Hr),
	sort_sublists(T, Tr).
sort_sublists([H|T], [H|Tr]) :-
	sort_sublists(T, Tr).






