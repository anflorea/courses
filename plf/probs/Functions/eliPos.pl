push_back([],E,[E]).
push_back([H|T],E,[H|Tr]):-
	push_back(T,E,Tr).

eliminatePos([],Pos,CPos,[],[]):-!.

eliminatePos([],Pos,CPos,C,FL):-
	FL = C.

eliminatePos([H|T],Pos,CPos,C,FL):-
	CPos =:= Pos,
	Pos1 is Pos + 1,
	write('heeeei'),
	%elimin(H,[H|T],F),
	%write(F),
	eliminatePos(T,Pos,Pos1,C,FL).

eliminatePos([H|T],Pos,CPos,C,FL):-
	CPos \= Pos,
	CPos1 is CPos + 1,
	push_back(C,H,F),
	write(F),
	eliminatePos(T,Pos,CPos1,F,FL).

