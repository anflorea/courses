%5.
% a. Substitute all occurrences of an element of a list with
%all the elements of another list.
% Eg. subst([1,2,1,3,1,4],1,[10,11],X) produces
% X=[10,11,2,10,11,3,10,11,4].
%

%push_back(L,E,R)
%L-list , E- element we append, R- result list
%flow model : (i,i,o)
%
push_back([],E,[E]).
push_back([H|T],E,[H|Tr]):-
	push_back(T,E,Tr).

%push_back(L,S,R)
%L-list , S list we append to L , R- result list
%flow model : (i,i,o)

append_list(L,[],L).
append_list(L,[H|T],R):-
	push_back(L,H,NewList),
	append_list(NewList,T,R).

%subst(L,N,S,C,R)
%LO-original list ,
%N- number we subtitute,
%S list we append to L ,
%C- list we construct -initially empty,
%R- result list
%flow model : (i,i,i,i,o)

subst([],_,_,C,R):-
	R=C.
subst([H|T],N,L,C,R):-
	H=N,
	append_list(C,L,NL),
	subst(T,N,L,NL,R).
subst([H|T],N,L,C,R):-
	H\=N,
	push_back(C,H,NL),
	subst(T,N,L,NL,R).
