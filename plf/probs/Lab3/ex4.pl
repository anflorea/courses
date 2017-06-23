%4. Definiti un predicat care determina suma a doua numere scrise in
 %reprezentare de lista.
 
adauga_sf(E,[],[E]):-!.
adauga_sf(E,[H|T],[H|L]):-adauga_sf(E,T,L).
	
invers([],[]):-!.
invers([H|T],L):-invers(T,L1), adauga_sf(H,L1,L).

push_back([],E,[E]).
push_back([H|T],E,[H|Tr]):-
	push_back(T,E,Tr).

length([],0):-!.
length([H|T],N):-length(T,N1),
                   N=N1+1.

	
suma([],[],[]):-!.
suma(L,L1,R):-invers(L,LL), invers(L1,LL1), suma_aux(LL,LL1,S,0), invers(S,R).
	
suma_aux([],[],[],_):-!.
%add1([],,C,LR):-
suma_aux(C,[H|T],[M|L],N):-
			C = [],
    		S is H+N,
    		M is S mod 10,
    		N1 is S div 10,!,
    		suma_aux(C,T,L,N1).
suma_aux([H|T],C,[M|L],N):-
			C = [],
    		S is H+N,
    		M is S mod 10,
    		N1 is S div 10,!,
    		suma_aux(T,C,L,N1).
suma_aux([H|T],[H1|T1],[M|L],N):-
			N=0,
    		S is H+H1,
    		M is S mod 10,
    		N1 is S div 10,!,
    		suma_aux(T,T1,L,N1).
suma_aux([H|T],[H1|T1],[M|L],N):-N\=0,
    							S is H+H1+N, 
    							M is S mod 10, 
    							N1 is S div 10,!,
    							suma_aux(T,T1,L,N1).
go(A,-1,NA):-!.
go(A,NR,NA):-
	push_back(A,0,NA),
	NR=NR-1,
	go(A,NR,NA).
putz(A,B,LA,LB):-
	LA=LB,
	%write("Dsad"),
	!.
putz(A,B,LA,LB):-
	LA<LB,
	%write("dsa"),
	NR=LB-LA,
	NB=B,
	go(A,NR,NA).

putz(A,B,LA,LB,NA,NB):-
	LB<LA,
	write("Dsad"),
	NR=LA-LB,
	NA=A,
	go(B,NR,NB).

sumaa(L,L1,R):-
	%write("dsa"),
	length(L,LA),
    length(L1,LB),
    %write(LA),
    putz(L,L1,LA,LB,NA,NB),
    write(NA),
	reverse(NA,RL),
	%write(RL),
	reverse(NB,RL1),
	suma_aux(RL1,RL,Re,0),
	%write(Re),
	R =Re.




