%Incearca asta dupa: 
%Calculeaza suma alternata a elementelor unei liste. Gen daca ai [1,2,3,4,5] sa obtii 1+2-3+4-55

%altSum(L-initial list,Poz-position in list,Sum-resulted sum)


%altSum([],1,0).

%alteSum([],Sum,Sumf):-
%	write(Sumf),!.


%alteSum([H|T],Sum,Sumf):-
%	Sumf is Sum + H,
%	altSum(T,2,Sumf),
%	alteSum([],0,Sumf).

altSum([],_,0):-!.


%alteSum([],_,0):-!.

altSum([H|T],Poz,Sum):-
	Poz is 1,!,
	Poz1 is Poz + 1,
	altSum(T,Poz1,Sum1),
	Sum is Sum1 + H.

altSum([H|T],Poz,Sum):-
	R is mod(Poz,2),
	write('da'),
	R \= 0,
	Poz1 is Poz + 1,
	altSum(T,Poz1,Sum1),
	Sum is Sum1 - H.
	

altSum([H|T],Poz,Sum):-
    R is mod(Poz,2),
	R = 0,
	%write(Sum1),
	Poz1 is Poz + 1,
	altSum(T,Poz1,Sum1),
	Sum is Sum1 + H.

alternata([],0,_):-!.

alternata([],Rez,_):-
	write(Rez).

alternata([H|T],Rez,0):-
	!,
	alternata(T,Rez1,1),
	Rez is Rez1 + H.

alternata([H|T],Rez,PozCrt):-
	PozCrt mod 2 =:= 0,
	PozCrt1 = PozCrt + 1,
	alternata(T,Rez1,PozCrt1),
	Rez is Rez1 - H.

alternata([H|T],Rez,PozCrt):-
	PozCrt mod 2 =:= 1,
	PozCrt1 = PozCrt + 1,
	alternata(T,Rez1,PozCrt1),
	Rez is Rez1 + H.


	