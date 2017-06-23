%9.
%a. For a list of integer number, write a predicate to add in list after 1-st, 3-rd, 7-th, 15-th element a given value


member([El|T],El):-!.
member([H|T],El) :- member(T,El).



add([],El,_,[]):-!.
add([H|T],El,I,[H,El|Tr]) :- member([1,3,7,15],I), I2 is I +1 , add(T,El,I2,Tr),!.
add([H|T],El,I,[H|Tr]) :- I2 is I+1 , add(T,El,I2,Tr).

setAdd(L,El,R) :- add(L,El,1,R).