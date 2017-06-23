%8.
%a. Write a predicate to determine if a list has even numbers of elements without counting the elements from
%the list.
%b. Write a predicate to delete first occurrence of the minimum number from a list.

%---------a-------

even([]):-!.
even([H1,H2|T]):-even(T),!.

%---------b------

min([],Min,Min):-!.
min([H|T],Min,RealMin) :- H<Min , min(T,H,RealMin),!.
min([H|T],Min,RealMin) :- min(T,Min,RealMin).

setMin(L,RealMin):- min(L,88888,RealMin).


myDelete([],_,El,[]):-!.
myDelete([H|T],1,El,[H|Tr]) :- myDelete(T,1,El,Tr),!.
myDelete([H|T],0,El,[H|Tr]) :- H \=El , myDelete(T,0,El,Tr),!.
myDelete([H|T],0,El,Tr) :- H=El, myDelete(T,1,El,Tr).

setDeleteMin(L,Rez) :- setMin(L,Min) , myDelete(L,0,Min,Rez).