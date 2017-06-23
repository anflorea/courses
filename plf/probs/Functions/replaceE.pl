%replace(L,E1,E2,C,R)
%L -initial list
%E1, element we search for
%E2- element we replace E1 with
%C- list we construct
%R- result list
%flow model :(i,i,i,i,o)

replace1([],_,_,C,R):-
	R=C.
replace1([H|T],E1,E2,C,R):-
	H=E1,
	push_back(C,E2,NewC),
	replace1(T,E1,E2,NewC,R).
replace1([H|T],E1,E2,C,R):-
	H\=E1,
	push_back(C,H,NewC),
	replace1(T,E1,E2,NewC,R).


replace(L,E1,E2,R):-
	replace1(L,E1,E2,[],Res),
	R=Res.
replace([],_,_,R):-
	R=[].