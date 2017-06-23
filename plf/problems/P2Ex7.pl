%7.
%a. Determine the position of the maximal element of a linear list.
%Eg.: maxpos([10,14,12,13,14], L) produces L = [2,5].


detMax([],Max,Max):-!.
detMax([H|T],Max,RMax):- H > Max, detMax(T,H,RMax),!.
detMax([H|T],Max,RMax) :- detMax(T,Max,RMax).


detPos([],El,_,[]):-!.
detPos([El|T],El,I,[I|Tr]) :- I2 is I+1 ,detPos(T,El,I2,Tr),!.
detPos([H|T],El,I,Tr) :-I2 is I+1, detPos(T,El,I2,Tr).

detPosMax(L,PosRes) :- detMax(L,-33333,Max) , detPos(L,Max,1,PosRes).
 
 
 