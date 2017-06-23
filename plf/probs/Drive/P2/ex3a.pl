% merge(L1-list_1, L2-list_2, R-result)
% R is obtained by merging the sorted lists L1 and L2
% flow model: (i i o), (i i i)
merge([], L2, L2).
merge(L1, [], L1).
merge([H1|T1], [H2|T2], [H1|T]) :-
	H1 =< H2,
	merge(T1, [H2|T2], T).
merge([H1|T1], [H2|T2], [H2|T]) :-
	H1 > H2,
	merge([H1|T1], T2, T).

% b. For a heterogeneous list, formed from integer numbers and list of
% numbers, merge all sublists with removing the double values.
% [1, [2, 3], 4, 5, [1, 4, 6], 3, [1, 3, 7, 9, 10], 5, [1, 1, 11], 8] =>
% [1, 2, 3, 4, 6, 7, 9, 10, 11].

% just_sublists(L-list of all stuff, R-result list)
% R is obtained from L by keeping only the sublists
% flow model: (i o), (i i)
just_sublists([], []).
just_sublists([H|T], [H|Tr]) :-
	is_list(H),
	just_sublists(T, Tr).
just_sublists([_|T], Tr) :-
	just_sublists(T, Tr).


% merge_all(L-list of lists, R-result list)
% flow model: (i o), (i i)
merge_all([], []).
merge_all([L1], L1).
merge_all([L1,L2], R) :-
	merge(L1, L2, R).
merge_all([L1,L2|T], R) :-
	merge(L1, L2, Hr),
	merge_all([Hr|T], R).

% set(L-list, R-result list); R is obtained from L by removing doubles
% flow model: (i o), (i i)
set([], []).
set([H|T], [H|X]) :-
	remove_elem(H, T, Y),
	set(Y, X).


% main function
% merge_sublists(L-list of all stuff, R-result list)
merge_sublists([], []).
merge_sublists(L, R) :-
	just_sublists(L, SublistsOfL),
	merge_all(SublistsOfL, Result),
	set(Result, R).
