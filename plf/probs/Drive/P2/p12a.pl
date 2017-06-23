% divisors(N-number, D-current divisor, R-result list)
% flow (i i o) (i i i)
divisors(N, D, []) :-
	D >= N.
divisors(N, D, [D|Tr]) :-
	N > D,
	0 is N mod D,
	D1 is D	+ 1,
	divisors(N, D1, Tr).
divisors(N, D, Tr) :-
	N > D,
	not(0 is N mod D),
	D1 is D	+ 1,
	divisors(N, D1, Tr).

% cu un argument
divisors(N, R) :-
	divisors(N, 2, R).

% append(L1, L2, R)
% flow (i i o) (i i i)
append([], [], []).
append([], [H2|T2], [H2|R]) :-
	append([], T2, R).
append([H1|T1], L2, [H1|R]) :-
	append(T1, L2, R).


% add_divisors(L-list, R-result list)
add_divisors([], []).
add_divisors([H|T], [H|Tr]) :-
	divisors(H, DivisorsOfH),
	add_divisors(T, ResultOnTail),
	append(DivisorsOfH, ResultOnTail, Tr).


% punct_b(L-list, R-result list)
punct_b([], []).
punct_b([H|T], [Hr|Tr]) :-
	is_list(H),
	add_divisors(H, Hr),
	punct_b(T, Tr).
punct_b([H|T], [H|Tr]) :-
	punct_b(T, Tr).









