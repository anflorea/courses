%a. Determine the successor of a number represented as digits in a list.
%Eg.: [1 9 3 5 9 9] --> [1 9 3 6 0 0]

%flow model(i,i,i,o)
%add1(L,F,LR)
%L-list
%F-flag
%LR-resulted list

push_back([],E,[E]).
push_back([H|T],E,[H|Tr]):-
	push_back(T,E,Tr).

reverse([],[]).
reverse([H|T],L):-
    reverse(T,R),
    append(R,[H],L).


add1([],0,C,LR):-
	LR=C.

add1([],1,C,LR):-
	push_back(C,1,R),
	add1([],0,R,LR).


add1([H|T],F,C,LR):-
	H is 9,
	push_back(C,0,R),
	add1(T,1,R,LR).

add1([H|T],F,C,LR):-
	F is 1,
	H2 is H+1,
	push_back(C,H2,R),
	add1(T,0,R,LR).

add1([H|T],F,C,LR):-
	F is 0,
	push_back(C,H,R),
	add1(T,0,R,LR).

add(L,R):-
	reverse(L,RL),
	add1(RL,1,[],LR),
	reverse(LR,Re),
	R =Re.