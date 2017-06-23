%12.
%a. Define a predicate to add after every element from a list, the divisors of that number



listDiv(1,_,[]):-!.
listDiv(N,N,[]):-!.
listDiv(N,I,[I|Tr]) :- N mod I =:= 0 , I2 is I+1 , listDiv(N,I2,Tr),!.
listDiv(N,I,Tr) :-  I2 is I+1 , listDiv(N,I2,Tr),!.

setListDiv(N,R) :- listDiv(N,2,R).



concat(L,[],L):-!.
concat(L,[H2|T2],[H2|Tr]) :- concat(L,T2,Tr).


add([],[]):-!.

add([H|T],[H|Rez]) :- add(T,Rezz), setListDiv(H,List),concat(Rezz,List,Rez).
