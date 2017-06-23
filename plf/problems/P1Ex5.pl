%5. 
%a. Write a predicate to compute the union of two sets.
%b. Write a predicate to determine the set of all the pairs of elements in a list.
%Eg.: L = [a b c d] => [[a b] [a c] [a d] [b c] [b d] [c d]].

%---------a--------

member([El|T],El) :-!.
member([H|T],El):- member(T,El).

union([],B,B).
union([H|T],B,Rez) :- member(B,H) ,union(T,B,Rez),!.
union([H|T],B,[H|Tr]) :- not(member(B,H)),union(T,B,Tr).

%-----------b---------

unElem([],_,[]).
unElem([H|T],El,[[El,H]|Rez]) :- unElem(T,El,Rez).


crossProd([],[]).
crossProd([H|T],RezFin) :- unElem(T,H,Rez), crossProd(T,Tr) ,union(Rez,Tr,RezFin). 