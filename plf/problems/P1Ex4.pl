%4. 
%a. Write a predicate to determine the difference of two sets.
%b. Write a predicate to add value 1 after every even element from a list.



%---------a-------

member([El|T],El):-!.
member([H|T],El) :- member(T,El).


difference([],B,[]).
difference([H|T],B,[H|Tr]) :- not(member(B,H)), difference(T,B,Tr),!.
difference([H|T],B,Rez) :- difference(T,B,Rez). 


%---------b-----------

add([],[]).
add([H|T],[H|Tr]) :- 1=:= H mod 2 , add(T,Tr),! .
add([H|T],[H,1|Tr]) :- add(T,Tr).