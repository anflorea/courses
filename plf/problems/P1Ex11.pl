%11.
%a. Write a predicate to substitute an element from a list with another element in the list.
%b. Write a predicate to create the sublist (lm, …, ln) from the list (l1,…, lk).



%-----------a----

substit([],Ex,New,[]):-!.
substit([Ex|T],Ex,New,[New|Tr]) :- substit(T,Ex,New,Tr),!.
substit([H|T],Ex,New,[H|Tr]) :- substit(T,Ex,New,Tr).

%--------b------

create([H|T],M,N,N,[H]):-!.
create([H|T],M,N,I,Tr) :-  I<M, I2 is I + 1, create(T,M,N,I2,Tr),!.
create([H|T],M,N,I,[H|Tr]) :- I<N , I2 is I+1 ,  create(T,M,N,I2,Tr).

setCreate(L,M,N,Rez):- create(L,M,N,1,Rez).