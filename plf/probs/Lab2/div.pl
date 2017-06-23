%Greatest common divisor
%gcd(A,B,D)
%A- first number
%B- second number
%D- greatest common div of A and B
%
%Flow model :(i,i,o)

gcd(A,B,D):-
	A=B,
	D=A.
gcd(A,B,D):-
	A<B,
	B1 is B-A,
	gcd(A,B1,D).
gcd(A,B,D):-
	A>B,
	gcd(B,A,D).

%Lowest common multiple with formula

lcm(A,B,M):-
	gcd(A,B,D),
	M is A*B//D.
