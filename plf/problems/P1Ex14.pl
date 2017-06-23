%14.
%a. Write a predicate to test equality of two sets without using the set difference.
%b. Write a predicate to select the n-th element of a given list.


%---------a-------


member([El|T],El) :-!.
member([H|T],El):- member(T,El).


equality([],B) :-!.
equality([H|T],B):- member(B,H), equality(T,B).

setEquality(A,B)  :- equality(A,B),!, equality(B,A).

%----b-----


select([H|T],N,N,H) :-!.
select([H|T],N,I,Value) :- I<N , I2 is I+1, select(T,N,I2,Value).


setSelect(L,N,Value) :- select(L,N,1,Value).