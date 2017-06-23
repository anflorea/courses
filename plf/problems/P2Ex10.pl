%10.
%a. For a list of integer numbers, define a predicate to write twice in list every prime number



prime(N,N) :-!.
prime(N,I):- I<N , not(N mod I=:=0) ,I2 is I+1, prime(N,I2).

setPrime(N) :- prime(N,2).


writeL([],[]).
writeL([H|T],[H,H|Tr]):- setPrime(H), writeL(T,Tr),!.
writeL([H|T],[H|Tr]) :- writeL(T,Tr).