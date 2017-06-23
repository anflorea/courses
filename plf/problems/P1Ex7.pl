%7.
%a. Write a predicate to compute the intersection of two sets.
%b. Write a predicate to create a list ( m , ..., n) of all integer numbers from the interval [m,n]



%-------a-------

member([El|T],El) :-!.
member([H|T],El):- member(T,El).



intersection([],B,[]):-!.
intersection([H|T],B,[H|Tr]):- member(B,H) , intersection(T,B,Tr),!.
intersection([H|T],B,Tr) :- intersection(T,B,Tr).

%--------b----------


create(M,N,N,[N]):-!.
create(M,N,I,[I|Tr]):- I<N , I2 is I+1, create(M,N,I2,Tr).

setCreate(M,N,Rez):- create(M,N,M,Rez). 