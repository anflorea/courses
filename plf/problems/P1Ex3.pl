
%3.
%a. Define a predicate to remove from a list all repetitive elements.
%Eg.: l=[1,2,1,4,1,3,4] => l=[2,3])
%b. Remove all occurrence of a maximum value from a list on integer numbers.


%------a-------

numberOcc([],_,0).
numberOcc([H|T],El,No1) :- El = H, numberOcc(T,El,No), No1 is No +1,!.
numberOcc([H|T],El,No) :- numberOcc(T,El,No).

remove([],Lc,[]).
remove([H|T],Lc,Rez) :- numberOcc(Lc,H,No), No > 1, remove(T,Lc,Rez),!.
remove([H|T],Lc,[H|Tr]) :- numberOcc(Lc,H,No) , No = 1, remove(T,Lc,Tr).

setRemove(L,Rez) :-remove(L,L,Rez).

%---------b---------

getMax([],Max,Max).
getMax([H|T],Max,RealMax) :- H>=Max, getMax(T,H,RealMax),!.
getMax([H|T],Max,RealMax) :- getMax(T,Max,RealMax).

setGetMax(L,Max) :- getMax(L,-32000,Max).


removeAnElem([],_,[]).
removeAnElem([H|T],El,[H|Tr]) :- El \= H , removeAnElem(T,El,Tr),!.
removeAnElem([H|T],El,Rez) :- removeAnElem(T,El,Rez).

removeMax(L,Rez) :- setGetMax(L,Max) , removeAnElem(L,Max,Rez).