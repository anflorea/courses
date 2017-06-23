%
%13.
% a. Given a linear numerical list write a predicate to remove all
% sequences of consecutive values.
%Eg.: remove([1, 2, 4, 6, 7, 8, 10], L) will produce L=[4, 10].
% b. For a heterogeneous list, formed from integer numbers and list of
% numbers; write a predicate to delete from
%every sublist all sequences of consecutive values.
%Eg.: [1, [2, 3, 5], 9, [1, 2, 4, 3, 4, 5, 7, 9], 11, [5, 8, 2], 7] =>
%[1, [5], 9, [4, 7, 9], 11, [5, 8, 2], 7]

%a)
%removecons(L - list, R - result list)
%flow model (i, o)

removecons([],[]).
removecons([H],[H]).
removecons([H1,H2],R):-
	H1+1=:=H2,
	R = [].
removecons([H1,H2,H3|T],R):-
	H1+1=:=H2,
	H2+1=:=H3,
	removecons([H2,H3|T],R).
removecons([H1,H2,H3|T],R):-
	H1+1=:=H2,
	H2+1=\=H3,
	removecons([H3|T],R).
removecons([H1,H2|T],R):-
	H1+1=\=H2,
	removecons([H2|T],RT),
	R = [H1|RT].

%b)
%removeHeterog(L - list, R - result list)
%flow model (i, o)

removeHeterog([],[]).
removeHeterog([H|T],R):-
	is_list(H),
	removeHeterog(T,RT),
	removecons(H,C),
	R = [C|RT].
removeHeterog([H|T],R):-
	removeHeterog(T,RT),
	R = [H|RT].

