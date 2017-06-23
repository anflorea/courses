gcd2(X, 0, X) :- !.

gcd2(0, X, X) :- !.

gcd2(X, Y, Z) :-
	X =< Y, !,
	D is Y - X,
	gcd2(X, D, Z).

gcd2(X, Y, Z) :-
	gcd2(Y, X, Z).

inner_gcd(GCD, [], GCD).

inner_gcd(GCD, [H | T], X) :-
	gcd2(GCD, H, R),
	inner_gcd(R, T, X).

gcd([], 0).

gcd([H | T], X) :-
	inner_gcd(H, T, X).

% gcd([l1..ln]) = 0 if the list is empty
%				  inner_gcd(l1, [l2..ln]) if the list has at least one element
%
% inner_gcd(GCD, [l1..ln]) = GCD if the list is empty
%							 inner_gcd( gcdOf2(GCD, l1), [l2..ln]) if the list has at least one element
%
% gcdOf2(X, Y) = X if Y is 0
%				 Y if X is 0
%				 gcdOf2(Y, X) if X <= Y
%				 gcdOf2(X, Y - X) otherwise
%
% flow model: (i, o)
% First argument: The initial list
% Second argument: The resulted list
