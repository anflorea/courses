%Test prolog P1,P2a
%

%push_back(L,E,R)
%L-list , E- element we append, R- result list
%flow model : (i,i,o)
%
push_back([],E,[E]).
push_back([H|T],E,[H|Tr]):-
	push_back(T,E,Tr).

%elimS(L,Pi,Pf,Pc, C, R)
%L-initial list
%Pi- poz of first elem in subsir
%Pf - poz of last elem in seq
%C- constructed list
%R-resul list
%Flow model : i,i,i,i,i,o


elimS([],_,_,_,C,R):-
	R = C.

elimS([_|T],Pi,Pf,Pc,C,R):-
	Pi=<Pc,
	Pc=<Pf,
	%atunci nu adaugam elementul in C
	Pc1 is Pc+1,
	elimS(T,Pi,Pf,Pc1,C,R).

elimS([H|T],Pi,Pf,Pc,C,R):-
	Pi>=Pc,
	push_back(C,H,C1),
	Pc1 is Pc+1,
	elimS(T,Pi,Pf,Pc1,C1,R).

elimS([H|T],Pi,Pf,Pc,C,R):-
	Pf=<Pc,
	push_back(C,H,C1),
	Pc1 is Pc+1,
	elimS(T,Pi,Pf,Pc1,C1,R).

%elim(L,Pc,Pi,Pf,K,C,R)

elim([],_,_,_,_,C,R):-
	R=C.
elim([H|T],Pc,Pi,Pf,K,C,R):-
	H<0,
	Pf1 is Pf+1,
	K1 is K+1,
	Pc1 is Pc+1,
	elim(T,Pc1,Pi,Pf1,K1,C,R).

elim([H|T],Pc,Pi,Pf,K,C,R):-
	H>0,
	K>1,
	elimS(C,Pi,Pf,1,C1),
        Pf1 is Pc-K,
	Pi1 is Pc-K,
	Pc1 is Pc-K,
	Pc1 is Pc-K,
	elim(T,Pc1,Pi1,Pf1,1,C1,R).










