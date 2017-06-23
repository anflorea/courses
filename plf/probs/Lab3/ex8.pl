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

%b. For a heterogeneous list, formed from integer numbers 
%and list of numbers, determine the successor of a sublist considered as a number.
%[1, [2, 3], 4, 5, [6, 7, 9], 10, 11, [1, 2, 0], 6] =>
%[1, [2, 4], 4, 5, [6, 8, 0], 10, 11, [1, 2, 1], 6]

%is list
is_list1([]).
is_list1([_|T]):-
	is_list1(T).

det_succ([],C,R):-
	R=C.

%cazul in care e sublista primul elem

det_succ([H|T],C,R):-
	is_list(H),
	add(H,HR),
	push_back(C,HR,CR),
	write(CR),
	det_succ(T,CR,R).

%cazul in care e un nr primul element

det_succ([H|T],C,R):-
	push_back(C,H,CR),
	det_succ(T,CR,R).








