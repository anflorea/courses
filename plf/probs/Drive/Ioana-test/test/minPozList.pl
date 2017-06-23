%Afisati lista  pozitiilor minimului dintr-o lista
%[1,2,5,1,6,1] => [1,4,6]


%minim(L- list,  R-result minim)
%flow model: i,o

minimum([E], E).
minimum([H1,H2|T], E) :-
	H1 =< H2,
	minimum([H1|T], E).
minimum([H1,H2|T], E) :-
	H2 < H1,
	minimum([H2|T], E).

%detPoz(E,N,L,R)
%E- element we search
%N- current position
%L- initial list
%R-result list
detPoz(_,_,[],[]).
detPoz(E,N,[E|T],[N|L]):-
	!,
	N1 is N+1,
	detPoz(E,N1,T,L).
detPoz(E,N,[_|T],L):-
	N1 is N+1,
	detPoz(E,N1,T,L).


%main function
minPozList([],[]).
minPozList(L,R):-
	L2=L,
	minimum(L,Min),
	write(Min),
	detPoz(Min,1,L2,R).
