%1)A list of numbers and sublists is given.
%For every sublist, compute: li/2 : li-even
                             %li*2 : li-odd

%lh=[2,[3,10,7],7,[20,3,5]] -> lhr=[2,[6,5,14],7,[10,6,10]]

%a) predicate to transform the sublists

%transform l-list, R-resulted list
%flow model (i,o)


transform([],[]).

transform([H|T],[Hr|Tr]):-
	H mod 2 =:=0,
	Hr is H//2,
	write('ya'),
	transform(T,Tr).

transform([H|T],[Hr|Tr]):-
	%H mod 2 =/= 0,
	Hr is H*2,
	transform(T,Tr).


%b)predicate to transform the heterogen. list

%L,R; flow model(i,o)

th([],[]).

th([H|T],[Hr|Tr]):-
	is_list(H),
	transform(H,Hr),
	th(T,Tr).

th([H|T],[H|Tr]):-
	th(T,Tr).
