%Se se determine cea mai lunga secventa de numere consecutive

%%push_back(L,E,R)
%L-list , E- element we append, R- result list
%flow model : (i,i,o)

push_back([],E,[E]).
push_back([H|T],E,[H|Tr]):-
	push_back(T,E,Tr).


%detSeq(L-initial list, Pos, C-constructed list, F-final list)

detSeq([],_,[],[]):-!.

detSeq([],_,[],F).
	%write(F).

detSeq([H1,H2,H3|T],Pos,C,F):-
	H2 is H1 + 1,
	H3 is H2 + 1,!,
	push_back(C,H1,NC),
	Pos1 is Pos + 1,
	detSeq([H2,H3|T],Pos1,NC,F).

detSeq([H1,H2,H3|T],Pos,C,[NC1|F]):-
	H2 is H1 + 1,!,
	push_back(C,H1,NC),
	push_back(NC,H2,NC1),
	Pos1 is Pos + 1,
	Pos2 is Pos + 2,
	detSeq([H3|T],Pos2,[],F).

detSeq([H1,H2,H3|T],Pos,C,F):-
	Pos1 is Pos + 1,
	detSeq([H2,H3|T],Pos1,C,F).

detSeq([H1,H2],Pos,C,F):-
	H2 is H1 + 1,!,
	Pos1 is Pos + 1,
	push_back(C,H1,NC),
	push_back(NC,H2,NC1),
	detSeq([],Pos1,[],[NC1|F]).

detSeq([H1,H2],Pos,C,F):-
	Pos1 is Pos + 1,!,
	detSeq([],Pos1,NC,F).

detSeq([H],Pos,C,F):-
	detSeq([],Pos,C,F).

%determine the longest sublist from a list of lists

%longSeq(L-list,M-max nr of elements,R-longest)

len([], LenResult):-
    LenResult is 0.

len([X|Y], LenResult):-
    len(Y, L),
    LenResult is L + 1.

length_1(0,[]).
length_1(L+1, [H|T]) :- length_1(L,T).

longSeq([],0,_,_):-!.

longSeq([],M,_,Pos).

longSeq([H|T],M,CPos,Pos):-
	len(H,I),
	I > M,!,
	CPos1 is CPos + 1,

	longSeq(T,I,CPos1,Pos),
	Pos is CPos.

longSeq([H|T],M,CPos,Pos):-
	CPos1 is CPos + 1,!,
	longSeq(T,M,CPos1,Pos).


afis([],_,_,R).

afis([H|T],CPos,Pos,H):-
	CPos =:= Pos,!,	
	afis([],CPos,Pos,R).

afis([H|T],CPos,Pos,R):-
	CPos1 is CPos + 1,!,
	afis(T,CPos1,Pos,R).


mainSeq([],[]):-
	write('da').

mainSeq([],F):-
	write('e'),!.

mainSeq([H|T],F):-
	write('daasaa'),
	detSeq([H|T],1,[],R),!,
	longSeq(R,0,1,Pos),
	afis(R,1,Pos,NR),
	F = NR,
	mainSeq([],F).








