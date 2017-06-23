%Se se determine toate secventele de numere consecutive

%%push_back(L,E,R)
%L-list , E- element we append, R- result list
%flow model : (i,i,o)

push_back([],E,[E]).
push_back([H|T],E,[H|Tr]):-
	push_back(T,E,Tr).


%detSeq(L-initial list, Pos, C-constructed list, F-final list)

detSeq([],_,[],[]).

detSeq([],_,C,F):-
	F = C.

detSeq([H1,H2,H3|T],Pos,C,F):-
	H2 is H1 + 1,
	H3 is H2 + 1,!,
	write('aici'),
	Pos1 is Pos + 1,
	%eliminatePos([H1,H2,H3|T],Pos,1,[],FL),
	detSeq([H2,H3|T],Pos1,C,F).

detSeq([H1,H2,H3|T],Pos,C,F):-
	H2 is H1 + 1,!,
	write(H1),
	write(H2),
	write(T),
	Pos1 is Pos + 1,
	Pos2 is Pos + 2,
	%eliminatePos([H1,H2,H3|T],Pos,1,[],FL),
	%eliminatePos([H1,H2,H3|T],Poss1,1,[],FL),
	detSeq([H3|T],Pos2,C,F).

detSeq([H1,H2,H3|T],Pos,C,F):-
	push_back(C,H1,NC),
	write('da'),
	Pos1 is Pos + 1,
	detSeq([H2,H3|T],Pos1,NC,F).

detSeq([H1,H2],Pos,C,F):-
	H2 is H1 + 1,!,
	Pos1 is Pos + 1,
	%eliminatePos([H1,H2],Pos,1,[],FL),
	%eliminatePos([H2],Pos1,1,[],FL),
	detSeq([],Pos1,C,F).

detSeq([H1,H2],Pos,C,F):-
	Pos1 is Pos + 1,!,
	write('da'),
	push_back(C,H1,NC),
	push_back(C,H2,NC),
	detSeq([],Pos1,NC,F).

detSeq([H],Pos,C,F):-
	push_back(C,H,NC),
	detSeq([],Pos1,NC,F).







