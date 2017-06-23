%12.
%a. Write a predicate to substitute in a list a value with all the elements of another list.
%b. Remove the n-th element of a list.


%---------a---------1:34


concatenate(L,[],L):-!.
concatenate(L,[H1|T1],[H1|Tr]):- concatenate(L,T1,Tr).


substit([],El,L,[]).
substit([El|T],El,L,Rez) :-  substit(T,El,L,Tr), concatenate(Tr,L,Rez) ,!.
substit([H|T],El,L,[H|Tr]) :- substit(T,El,L,Tr).


%--------b-------1:45

remove([_|T],N,N,T) :-!.
remove([H|T],N,I,[H|Tr]):-  I=<N-1, I2 is I+1 , remove(T,N,I2,Tr),!.


setRemove(L,N,Rez) :- remove(L,N,1,Rez).