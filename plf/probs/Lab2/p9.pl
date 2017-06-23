%9.
%a. Insert an element on the position n in a list.
%
%insert(L-list,El- element, N -position, I- current pos, R-result)
%L- initial list
%El - element to insert
%N- position we have to insert in
%I- current position
%R- result list
%
%Flow model : (i,i,i,i,o)
insert([],_,_,_,[]).

insert([H|T],El,N,Pc,[El,H|RL]) :-
		N = Pc,
	        Pc2 is Pc +1,
		insert(T,El,N,Pc2,RL).


insert([H|T],El,N,Pc,[H|RL]) :-
		N \= Pc,
		Pc2 is Pc +1,
		insert(T,El,N,Pc2,RL).


%b. Define a predicate to determine the greatest common divisor
%of all numbers from a list.
%
%Greatest common divisor of 2 numbers
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

%Greatest common divisor of a list
%gcd(L-list,D-div, R- result)
%L- initial list of numbers
%D- partial greatest common divizor
%R- the greatest common divizor
%

gcdList([],D,R):-
	R=D.

gcdList([H|T],D,R):-
	gcd(H,D,Div),
	D2=Div,
	gcdList(T,D2,R).

%main function that is called
gcdAList([H|T],R):-
	gcdList(T,H,R1),
	R=R1.

gcdAList([],R):-
	R=1.

