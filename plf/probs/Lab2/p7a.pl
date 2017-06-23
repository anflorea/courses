%7.
%a. Write a predicate to compute the intersection of two sets.

%%push_back(L,E,R)
%L-list , E- element we append, R- result list
%flow model : (i,i,o)

push_back([],E,[E]).

push_back([H|T],E,[H|Tr]):-
	push_back(T,E,Tr).

member(X, [X|_]).        % member(X, [Head|Tail]) is true if X = Head 
                         % that is, if X is the head of the list
member(X, [_|Tail]) :-   % or if X is a member of Tail,
  member(X, Tail).       % ie. if member(X, Tail) is true.


%inter(L-list 1, L1-list 2,C-list constructed,R-resulted list)

inter([],[],[]).

%inter([],_,R):-
	%R=C.

inter([H|T],L1,[H,R]):-
	member(H,L1),!,
	%push_back(R,H,NR),
	inter(T,L1,R).

inter([H|T],L1,R):-
	inter(T,L1,R).

